<%@ page language="java" pageEncoding="gbk"%>
<%@ page import="java.io.*"%>
<%@ page import="com.jspsmart.upload.SmartUpload"%>
<%
	response.reset();
	response.setCharacterEncoding("gbk");
	try {
		String syspath = session.getServletContext().getRealPath("/")
				+ "\\update";
		String name = request.getParameter("filename");
		File file = new File(syspath + "\\" + name);
		long fileLength = file.length();
		if (file.exists()) {
			InputStream fin = new FileInputStream(file);
			try {
				//		response.setContentType("application/unknown");
				// 以下的类型必须要设置，要不不能在android机上正常下载的
				response.setContentType("application/vnd.android.package-archive; charset=gb2312");
				// 设置文件的长度，以便能够正确的显示进度条
				response.setContentLength((int) fileLength);
				//      response.addHeader("Content-Disposition", "attachment; filename="+name);
				//      response.addHeader("Content-Disposition","attachment;filename="+ name, "iso-8859-1");
				response.addHeader("Content-Disposition",
						"attachment;filename=" + name);

				OutputStream outStream = response.getOutputStream();
				int len = 0;
				byte[] bytes = new byte[1024];
				// 将附件对象输出到客户端
				try {
					while ((len = fin.read(bytes)) != -1) {
						outStream.write(bytes, 0, len);
					}
					outStream.flush();

					response.setStatus(response.SC_OK);
					response.flushBuffer();
					//      out.clear();
					//      out = pageContext.pushBody();
				} finally {
					if (out != null) {
						out.close();
					}
					if (outStream != null) {
						outStream.close();
					}
				}
			}
			finally {
				if (fin != null) {
					fin.close();
				}

			}
		} else {
%>
<script charset="gbk" type="text/javascript">
	alert("文件不存在!");
</script>
<%
	}
%>
<%
	} catch (Exception e1) {
		e1.printStackTrace();
%>
<script charset="gbk">
	alert("下载附件出错!");
</script>
<%
	}
%>