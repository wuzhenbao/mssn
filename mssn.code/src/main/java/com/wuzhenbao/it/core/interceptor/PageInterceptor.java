package com.wuzhenbao.it.core.interceptor;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.Map;
import java.util.Properties;

import org.apache.ibatis.executor.parameter.ParameterHandler;
import org.apache.ibatis.executor.statement.RoutingStatementHandler;
import org.apache.ibatis.executor.statement.StatementHandler;
import org.apache.ibatis.mapping.BoundSql;
import org.apache.ibatis.mapping.MappedStatement;
import org.apache.ibatis.mapping.ParameterMapping;
import org.apache.ibatis.plugin.Interceptor;
import org.apache.ibatis.plugin.Intercepts;
import org.apache.ibatis.plugin.Invocation;
import org.apache.ibatis.plugin.Plugin;
import org.apache.ibatis.plugin.Signature;
import org.apache.ibatis.scripting.defaults.DefaultParameterHandler;

import com.wuzhenbao.it.core.vo.Pager;
import com.wuzhenbao.it.core.util.ReflectUtil;

@Intercepts({ @Signature(method = "prepare", type = StatementHandler.class, args = { Connection.class }) })
public class PageInterceptor implements Interceptor {

	private String dataBaseType;

	@SuppressWarnings("unchecked")
	public Object intercept(Invocation invocation) throws Throwable {
		try {
			 if(!"oracle".equalsIgnoreCase(dataBaseType)) {
				 return invocation.proceed();
			 }
			RoutingStatementHandler handler = (RoutingStatementHandler) invocation
					.getTarget();
			// 通过反射获取到当前RoutingStatementHandler对象的delegate属性
			StatementHandler delegate = (StatementHandler) ReflectUtil
					.getFieldValue(handler, "delegate");
			// 获取到当前StatementHandler的
			// boundSql，这里不管是调用handler.getBoundSql()还是直接调用delegate.getBoundSql()结果是一样的，因为之前已经说过了
			// RoutingStatementHandler实现的所有StatementHandler接口方法里面都是调用的delegate对应的方法。
			BoundSql boundSql = delegate.getBoundSql();
			// 拿到当前绑定Sql的参数对象，就是我们在调用对应的Mapper映射语句时所传入的参数对象
			Object po = boundSql.getParameterObject();
			if (po instanceof Map) {
				Map<String, Object> m = (Map<String, Object>) po;
				Object obj = m.get("p");
				// 这里我们简单的通过传入的是Page对象就认定它是需要进行分页操作的。
				if (obj instanceof Pager) {
					Pager page = (Pager) obj;
					// 通过反射获取delegate父类BaseStatementHandler的mappedStatement属性
					MappedStatement mappedStatement = (MappedStatement) ReflectUtil
							.getFieldValue(delegate, "mappedStatement");
					// 拦截到的prepare方法参数是一个Connection对象
					Connection connection = (Connection) invocation.getArgs()[0];
					// 获取当前要执行的Sql语句，也就是我们直接在Mapper映射语句中写的Sql语句
					String sql = boundSql.getSql();
					// 给当前的page参数对象设置总记录数
					this.setTotalRecord(m, page, mappedStatement, connection);
					// 获取分页Sql语句
					String pageSql = this.getPageSql(page, sql);
					// 利用反射设置当前BoundSql对应的sql属性为我们建立好的分页Sql语句
					ReflectUtil.setFieldValue(boundSql, "sql", pageSql);
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return invocation.proceed();
	}

	public Object plugin(Object target) {
		return Plugin.wrap(target, this);
	}

	public void setProperties(Properties properties) {
		this.dataBaseType = properties.getProperty("dataBaseType");
	}

	/**
	 * 根据page对象获取对应的分页查询Sql语句，这里只做了两种数据库类型，Mysql和Oracle 其它的数据库都 没有进行分页
	 * 
	 * @param page
	 *            分页对象
	 * @param sql
	 *            原sql语句
	 * @return
	 */
	private String getPageSql(Pager page, String sql) {
		StringBuffer sqlBuffer = new StringBuffer(sql);
		 if("oracle".equalsIgnoreCase(dataBaseType)) {
			 return getOraclePageSql(page, sqlBuffer);
		 }
		 return sqlBuffer.toString();
	}

	/**
	 * 获取Oracle数据库的分页查询语句
	 * 
	 * @param page
	 *            分页对象
	 * @param sqlBuffer
	 *            包含原sql语句的StringBuffer对象
	 * @return Oracle数据库的分页查询语句
	 */
	private String getOraclePageSql(Pager page, StringBuffer sqlBuffer) {
		if(this.dataBaseType == "oracle"){
			// 计算第一条记录的位置，Oracle分页是通过rownum进行的，而rownum是从1开始的
			int offset = (page.getPageNo() - 1) * page.getPageSize() + 1;
			sqlBuffer.insert(0, "select ru.*, rownum r from (")
					.append(") ru where rownum < ")
					.append(offset + page.getPageSize());
			sqlBuffer.insert(0, "select tp.* from (").append(") tp where r >= ")
					.append(offset);
			// 上面的Sql语句拼接之后大概是这个样子：
			// select * from (select u.*, rownum r from (select * from t_user) u
			// where rownum < 31) where r >= 16
			return sqlBuffer.toString();
		}else{
			//如果是mysql，sql语句应该是这样： SELECT * FROM T_SYS_CATALOG T WHERE T.CATALOG_ID LIMIT 0,5;
			return sqlBuffer.toString();
		}
	}

	/**
	 * 给当前的参数对象page设置总记录数
	 * 
	 * @param page
	 *            Mapper映射语句对应的参数对象
	 * @param mappedStatement
	 *            Mapper映射语句
	 * @param connection
	 *            当前的数据库连接
	 */
	private void setTotalRecord(Object pm, Pager page,
			MappedStatement mappedStatement, Connection connection) {
		try {
			// 获取对应的BoundSql，这个BoundSql其实跟我们利用StatementHandler获取到的BoundSql是同一个对象。
			// delegate里面的boundSql也是通过mappedStatement.getBoundSql(paramObj)方法获取到的。
			BoundSql boundSql = mappedStatement.getBoundSql(pm);
			// 获取到我们自己写在Mapper映射语句中对应的Sql语句
			String sql = boundSql.getSql();
			// 通过查询Sql语句获取到对应的计算总记录数的sql语句
			String countSql = this.getCountSql(sql);
			// 通过BoundSql获取对应的参数映射
			List<ParameterMapping> parameterMappings = boundSql
					.getParameterMappings();
			// 利用Configuration、查询记录数的Sql语句countSql、参数映射关系parameterMappings和参数对象page建立查询记录数对应的BoundSql对象。
			BoundSql countBoundSql = new BoundSql(
					mappedStatement.getConfiguration(), countSql,
					parameterMappings, pm);
			// 通过mappedStatement、参数对象page和BoundSql对象countBoundSql建立一个用于设定参数的ParameterHandler对象
			ParameterHandler parameterHandler = new DefaultParameterHandler(
					mappedStatement, pm, countBoundSql);
			// 通过connection建立一个countSql对应的PreparedStatement对象。
			PreparedStatement pstmt = null;
			ResultSet rs = null;
			try {
				pstmt = connection.prepareStatement(countSql);
				// 通过parameterHandler给PreparedStatement对象设置参数
				parameterHandler.setParameters(pstmt);
				// 之后就是执行获取总记录数的Sql语句和获取结果了。
				rs = pstmt.executeQuery();
				if (rs.next()) {
					int totalRecord = rs.getInt(1);
					// 给当前的参数page对象设置总记录数
					page.setTotalRecord(totalRecord);
				}
			} catch (SQLException e) {
				e.printStackTrace();
			} finally {
				try {
					if (rs != null)
						rs.close();
					if (pstmt != null)
						pstmt.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
		} catch (Exception e1) {
			e1.printStackTrace();
		}
	}

	/**
	 * 根据原Sql语句获取对应的查询总记录数的Sql语句
	 * 
	 * @param sql
	 * @return
	 */
	private String getCountSql(String sql) {
		return "select count(1) from (" + sql + ") tp";
	}
}
