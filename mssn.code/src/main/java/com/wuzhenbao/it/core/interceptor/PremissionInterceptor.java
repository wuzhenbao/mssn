package com.wuzhenbao.it.core.interceptor;

import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;

import com.wuzhenbao.it.core.util.ClassUtil;
import com.wuzhenbao.it.core.util.OperationPermission;
import com.wuzhenbao.it.core.util.OperationSource;
import com.wuzhenbao.it.core.vo.PermissionVO;


public class PremissionInterceptor {
	private String packageName;
	
	public String getPackageName() {
		return packageName;
	}
	public void setPackageName(String packageName) {
		this.packageName = packageName;
	}
	private static List<PermissionVO> permissionList = new ArrayList<PermissionVO>();
	public void initPremission() throws ClassNotFoundException{
		List<Class<?>> classList = ClassUtil.getClasses(packageName);
		for (Class<?> cls : classList) {
			if(cls.isAnnotationPresent(OperationSource.class)){
				OperationSource os = cls.getAnnotation(OperationSource.class);
				for(Method m : Class.forName(cls.getName()).getMethods()){
					if(m.isAnnotationPresent(OperationPermission.class)){
						OperationPermission op = m.getAnnotation(OperationPermission.class);
						PermissionVO permiss = new PermissionVO();
						permiss.setCls(cls);
						permiss.setPermissDesc(os.desc());
						permiss.setPermissCode(os.code());
						permiss.setOperationCode(os.code());
						permiss.setOperationDesc(os.desc());
						permissionList.add(permiss);
					}
				}
			}
		}
	}
}
