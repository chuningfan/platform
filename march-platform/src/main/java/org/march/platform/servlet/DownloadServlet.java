package org.march.platform.servlet;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class DownloadServlet extends HttpServlet {

	/**
	 * 
	 */
	private static final long serialVersionUID = 8230025330468886329L;
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		downloadFile(req, resp);
	}
	private void downloadFile(HttpServletRequest request, HttpServletResponse response) throws IOException {
		String fileName = request.getParameter("fileName");
		String strDirPath = request.getSession().getServletContext().getRealPath("/");
		String basePath = strDirPath.replace(request.getContextPath().substring(1) + "\\", "") + "DemoUpload";
		File file = new File(basePath + "/" + fileName);
		if (file.isFile()) {
			try {
				@SuppressWarnings("resource")
				FileInputStream fis = new FileInputStream(file);
				byte[] b = new byte[fis.available()]; 
				response.setCharacterEncoding("utf-8");  
		        response.setHeader("Content-Disposition","attachment; filename="+fileName+""); 
		        try(ServletOutputStream out =response.getOutputStream()){
		        	out.write(b);  
		            out.flush(); 
		        }
			} catch (Exception e){
				System.out.println(e.getMessage());
			}
		}	
	}
	
	
}
