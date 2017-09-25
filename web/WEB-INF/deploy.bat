set Axis_Lib=./lib
set Java_Cmd=java -Djava.ext.dirs=%Axis_Lib%
set Axis_Servlet=http://localhost:9400/cqjsrfwbs/servlet/AxisServlet
%Java_Cmd% org.apache.axis.client.AdminClient -l%Axis_Servlet% deploy.wsdd