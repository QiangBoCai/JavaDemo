package com.lance.annotation;

public interface People {
	public String name();
	public String age();
	
	@Deprecated //过时接口
	public void work();
}
