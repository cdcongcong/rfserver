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
				// ���µ����ͱ���Ҫ���ã�Ҫ��������android�����������ص�
				response.setContentType("application/vnd.android.package-archive; charset=gb2312");
				// �����ļ��ĳ��ȣ��Ա��ܹ���ȷ����ʾ������
				response.setContentLength((int) fileLength);
				//      response.addHeader("Content-Disposition", "attachment; filename="+name);
				//      response.addHeader("Content-Disposition","attachment;filename="+ name, "iso-8859-1");
				response.addHeader("Content-Disposition",
						"attachment;filename=" + name);

				OutputStream outStream = response.getOutputStream();
				int len = 0;
				byte[] bytes = new byte[1024];
				// ����������������ͻ���
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
	alert("�ļ�������!");
</script>
<%
	}
%>
<%
	} catch (Exception e1) {
		e1.printStackTrace();
%>
<script charset="gbk">
	alert("���ظ�������!");
</script>
<%
	}
%>