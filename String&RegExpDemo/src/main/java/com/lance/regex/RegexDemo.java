package com.lance.regex;

import java.util.regex.Pattern;

public class RegexDemo {
	
	public static void main(String[] args)
	{
		String content = "(hello)";
		String pattern = "\\(hello\\)";
		
		boolean isMatch = Pattern.matches(pattern, content);
		System.out.println("ismatch:"+isMatch);
		content = "https://github\\.com/(\\w+)/.*";
		System.out.println("content:"+content);
	}
}
