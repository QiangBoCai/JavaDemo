package com.lance.annotation;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Inherited;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target({ElementType.METHOD,ElementType.TYPE})//ע�������򣺿���������Щ�ط�
/* TYPE :�࣬�ӿ�
 * METHOD:����
 * CONSTRUCTOR:���췽������
 * FIELD �ֶ�����
 * ...
 */
@Retention(RetentionPolicy.RUNTIME)//ע����������
/*
 * SOURCE:Դ����ʾ������ʱ����
 * CLASS:����ʱ��¼��.class�У�����ʱ����
 * RUNTIME:����ʱ��Ч������ͨ�������ȡ
 */
@Documented //����javadocʱ����ע��
@Inherited //ע�����û�г�Ա���б�ʶע�⣬��������ע��̳�
public @interface Description {//@interface�ؼ��ֶ���һ��ע�⣬ע�ⲻ�ǽӿ�
	//String desc() default ""; //desc()���ƽӿ�����ķ�����������ע��������ֻ��һ����Ա������һ�����޲����쳣�ķ�ʽ������
	//String author();//��Ա����ֻ���û�������byte,short,char,int,long,float,double,boolean���ֻ�����������
					     //��String,Enum,Class,annotations����������
						  //  ��һ,ֻ����public��Ĭ��(default)����������Ȩ����,Ĭ����default
	//int age() default 18; //��Ա��������ʹ��default ָ��һ��Ĭ��ֵ,ʹ�ø�ֵ�ȺŸ�ֵ�������Ա֮�䣬���Ÿ���
	  String value();					//���ֻ��һ����Ա�����Ա������ȡ��Ϊvalue(); ʹ�õ�ʱ����Ժ��Գ�Ա���͸�ֵ�Ⱥ� eg:@SuppressWarnings("deprecation")
	
}
