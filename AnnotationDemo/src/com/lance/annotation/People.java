package com.lance.annotation;

public interface People {
	public String name();
	public String age();
	
	@Deprecated //��ʱ�ӿ�
	public void work();
}
