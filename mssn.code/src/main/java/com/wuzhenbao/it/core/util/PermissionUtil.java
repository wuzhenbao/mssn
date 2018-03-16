package com.wuzhenbao.it.core.util;

import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;

import com.wuzhenbao.it.core.vo.PermissionVO;


public class PermissionUtil {
	public static List<PermissionVO> initPremission(String packageName) throws ClassNotFoundException{
		List<PermissionVO> permissList = new ArrayList<PermissionVO>();
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
						permissList.add(permiss);
					}
				}
			}
		}
		return permissList;
	}
}
