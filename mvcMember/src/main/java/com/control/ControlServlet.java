package com.control;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Properties;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

//@WebServlet("*.do")
public class ControlServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;	// 시리얼 버전 (직렬화)
	private Map<String, Object> map = new HashMap<String, Object>();
	
	@Override
	   public void init(ServletConfig config) throws ServletException {
	      String propertyConfig = config.getInitParameter("propertyConfig");
	      System.out.println("propertyConfig = " + propertyConfig);
	      
	      //String realFolder = config.getServletContext().getRealPath("/WEB-INF");
	      //String realPath = realFolder + "/" + propertyConfig;
	      //System.out.println("realPath = " +realPath);
	      
	      FileInputStream fin = null;	// 파일을 바이트 단위로 읽어주는 클래스
	      Properties properties = new Properties(); // 파일의 내용을 읽어서 키-값의 형태로 분류해서 맵에 보관한다
	      
	      try {
	         fin = new FileInputStream(propertyConfig);
	         //fin = new FileInputStream(realPath);
	         
	         properties.load(fin);	// 파일의 내용을 읽어서 키-값의 형태로 분류해서 맵에 보관한다
	         System.out.println("properties = " + properties);
	         
	      } catch (IOException e) {
	         e.printStackTrace();
	         
	      } finally {
	         try {
	            fin.close();
	         } catch (IOException e) {
	            e.printStackTrace();
	         }
	      }
	      System.out.println();
	      
	      
	      
	      // https://m.blog.naver.com/PostView.naver?isHttpsRedirect=true&blogId=ilovejsp&logNo=100130414494
	      Iterator it = properties.keySet().iterator();	// properties 안에 있는 key 값들만 불러온다.
	      												// 컬렉션에 저장된 요소를 읽어오는 방법 = Iterator 인터페이스
	      while (it.hasNext()) {	// iterator에 다음 요소가 있으면
	         String key = (String)it.next();	// 다음 요소를 불러와라
	         System.out.println("key = " + key);
	         
	         String className = properties.getProperty(key);
	         System.out.println("className = " + className);
	         
	         try {
	            Class<?> classType = Class.forName(className);	// JVM에게 해당 클래스의 정보를 로드한다
	            Object ob = classType.newInstance();
	            
	            System.out.println("ob = " + ob);
	            
	            map.put(key, ob);
	            
	         } catch (ClassNotFoundException e) {
	            e.printStackTrace();
	         } catch (InstantiationException e) {
	            e.printStackTrace();
	         } catch (IllegalAccessException e) {
	            e.printStackTrace();
	         } catch (IllegalArgumentException e) {
	            e.printStackTrace();
	         } 
	         
	         System.out.println();
	      }//end while
	}//end init

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		execute(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		execute(request, response);
	}
	
	protected void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// post 방식일때는 한글처리가 자동으로 안된다. 그러니까 직접 해줘야 함ㅇㅇ
		if ( request.getMethod().equals("POST") ) {	// 반드시 대문자로 써야댐
			request.setCharacterEncoding("UTF-8");
		}
		
		String category = request.getServletPath();  // '/member/writeForm.do' 만 짤라온다
		System.out.println("category = " + category);	
		
		
		// 이 밑부분은 01/06 에 나갈꺼임---------------------------------------------------------------
		CommandProcess com = (CommandProcess)map.get(category); // member.service.WriteFormService
	    String view = null;
	      
	    try {
	       view = com.requestPro(request, response); // "/member/writeForm.jsp"
	    } catch (Throwable e) {
	       e.printStackTrace();
	    }
	      
	    // forward
	    RequestDispatcher dispatcher = request.getRequestDispatcher(view);
	    dispatcher.forward(request, response); // 제어권 넘기기
	      
	}
}
