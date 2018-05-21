#Java注解
	
	2018/4/27 14:25:32 
	注解概念在java1.5中提出，目前最新java版本1.8默认支持注解

参考：[java 注解妙用](http://www.importnew.com/23564.html)

##	1.Java注解分类
### 1.1 按来源分类
- JDK注解 

		@Override 重写
		@Deprecated 过时
		@Suppvisewarnings 忽略警告

- Java第三方框架定义的注解
eg： Spring注解

		@Autowired
		@Service
		@Repository等

- 自定义注解

### 1.2按运行机制分类

-	源码注解	
		
			只在源码中存在，编译成.class文件就不存在了；
			@Retention(RetentionPolicy.SOURCE)
			eg:@Override 
-	编译时注解

			在运行阶段还起作用，甚至会影响运行逻辑的注解。
			@Retention(RetentionPolicy.RUNTIME)
			eg:@Autowired自动注入
    

-	元注解
	
			注解的注解叫元注解
			eg:@Target,@Documented,@Retention

##	2.Java自定义注解

##	3.Java注解的使用

		语法：@<注解名>(<成员名1>=<成员值1>,<成员名1>=<成员值1>,…)
		