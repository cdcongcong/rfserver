package com.tongwei.servlet.android;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


public class AndroidDownServlet extends HttpServlet
{
	public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
	{
		doPost(request,response);
	}
	
	public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
	{
		try
		{
			request.setCharacterEncoding("utf-8");
			response.setCharacterEncoding("utf-8");
		
			String fileName = "填写自己的";
			File file = new File(fileName);
			long fileLength = file.length();
			InputStream in = new FileInputStream(file);

			if (in != null)
			{
				// 以下的类型必须要设置，要不不能在android机上正常下载的
				response.setContentType("application/vnd.android.package-archive; charset=gb2312");
				// 设置文件的长度，以便能够正确的显示进度条
				response.setContentLength((int) fileLength);
				// 添加文件名字的时候，需要添加对应的后缀
				response.addHeader("Content-Disposition","attachment;filename="+ new String("文件名".getBytes("gb2312"), "iso-8859-1"));
				BufferedInputStream bufferInput = new BufferedInputStream(in);
				ServletOutputStream out = null;
				try
				{
					byte[] buffer = new byte[1024];
					out = response.getOutputStream();
					int len;
					while ((len = bufferInput.read(buffer)) > 0)
					{
						out.write(buffer, 0, len);
					}
					out.flush();
					response.setStatus(response.SC_OK);
					response.flushBuffer();
				}
				catch (IOException e)
				{
					throw e;
				}
				finally
				{
					if (out != null)
					{
						out.close();
					}
					if (bufferInput != null)
					{
						bufferInput.close();
					}
					if (in != null)
					{
						in.close();
					}
				}
			}
		}
		catch (Exception ex)
		{
			ex.printStackTrace();
		}
	}
}
