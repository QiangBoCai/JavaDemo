package com.lance.annotation;

import java.lang.annotation.Annotation;
import java.lang.reflect.Method;

@Description("i am class annotation")
public class Test {
	@SuppressWarnings("deprecation") //忽略deprecation警告
	public static void main(String[] args){
		People people = new Child();
		people.work();
		String d =Color();
		System.out.println(d);
		
		try {
			//使用类加载器加载类
			Class c= Class.forName("com.lance.annotation.Test");
			Method[] methods = c.getMethods();
			//找到类上面的注解
			boolean isExist= c.isAnnotationPresent(Description.class);
			if(isExist){
				Description des = (Description) c.getAnnotation(Description.class);
				System.out.println(des.value());
				for(Method method :methods)
				{
					if(method.isAnnotationPresent(Description.class))
					{
						Description desMethod = method.getAnnotation(Description.class);
						System.out.println(desMethod);
						System.out.println(desMethod.value());
					}
				}
			}
			Class clazz= Class.forName(Test.class.getName());
			
			Annotation annotation = clazz.getAnnotation(Description.class);
			if(annotation!=null){
				Description description = (Description) annotation;
				String value = description.value();
				System.out.println(value);
			}
			
			for(Method method :methods){
				Annotation[] annotations = method.getAnnotations();
				for(Annotation anno:annotations){
					if(anno instanceof Description ){
						Description description = (Description) anno;
						System.out.println(description.value());
					}
				}
			}
			
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	
	@Description("i am methed annotation")
	public static String Color(){
		return "red";
	}
}
