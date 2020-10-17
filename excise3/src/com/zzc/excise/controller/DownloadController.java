package com.zzc.excise.controller;

import com.zzc.excise.vo.Download;

import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.URLEncoder;
import java.util.List;

/*
 * Author: zzc
 * function: 根据请求的 id 响应对应的文件下载路径
 */
@WebServlet("/Download.do")
public class DownloadController extends HttpServlet {
	private static final long serialVersionUID = 1L;
    /**
     * @see HttpServlet#HttpServlet()
     */
    public DownloadController() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		request.setCharacterEncoding("UTF-8");
		String url_id = request.getParameter("url_id");
		HttpSession session = request.getSession();
		List<Download> list = (List<Download>) session.getAttribute("downloadList");
		for(int i=0;i<list.size();i++) {
			if(list.get(i).getId()==Integer.parseInt(url_id)) {
				DownloadFile(request,response,list.get(i).getPath());				
			}
		}
	}
	
	// 下载文件
	private void DownloadFile(HttpServletRequest request, HttpServletResponse response, String url_name) throws IOException {
		// TODO Auto-generated method stub
		//获取下载的文件的绝对路径
		String path = request.getServletContext().getRealPath("/WEB-INF/files/"+url_name);
		//获取要下载的文件名
		String fileName = path.substring(path.lastIndexOf("\\")+1);
		//设置content-disposition响应头控制浏览器以下载的形式打开文件
		response.setHeader("content-disposition", "attachment;filename=" + URLEncoder.encode(fileName,"UTF-8"));
		
		//获取要下载的文件输入流
		InputStream in = new FileInputStream(path);
		int len = 0;
		//创建数据缓冲区
		byte[] buffer = new byte[1024];
		//通过response对象获取OutputStream流
		ServletOutputStream out = response.getOutputStream();
		//将 FileInputStream流写入到buffer缓冲区
		while((len = in.read(buffer)) != -1) {
			out.write(buffer,0,len);
		}
		in.close();
		out.close();
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
	}

}
