package com.control;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public interface CommandProcess {
	// 추상 메소드 01/05 1412
	public String requestPro(HttpServletRequest request, HttpServletResponse response) throws Throwable;
	// Exception 의 부모는 Throwable 이다.
}
