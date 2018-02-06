<%@ page contentType="text/html; charset=UTF-8" %>
 
<%@ page import="java.text.DecimalFormat" %>
<%@ page import="java.util.Date" %>
<%@ page import="java.util.ArrayList" %>
<%@ page import="java.text.SimpleDateFormat" %>
<%@ page import="java.io.File" %>
<%@ page import="javax.servlet.ServletContext" %>
 
 
<%@ page import="nation.web.tool.Tool" %>
<%@ page import="nation.web.pds4.Paging" %>
 
<%@ page import="nation.web.category4.CategoryProc" %>
<%@ page import="nation.web.category4.CategoryVO" %>
 
<%@ page import="nation.web.pds4.Pds4Proc" %>
<%@ page import="nation.web.pds4.Pds4VO" %>
<%@ page import="nation.web.tool.Upload" %>

<%@ page import="org.apache.commons.fileupload.DiskFileUpload" %>  
<%@ page import="org.apache.commons.fileupload.FileItem" %>  
<%@ page import="org.apache.commons.fileupload.FileUpload" %>  
<%@ page import="org.apache.commons.fileupload.FileUploadException" %>  
 
<% 
String root = request.getContextPath();
request.setCharacterEncoding("utf-8"); 
 
DecimalFormat df = new DecimalFormat((char)65510 + " #,###,### " + (char)50896);
SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
String date = sdf.format(new Date());
%>
 
<%
CategoryProc categoryProc = new CategoryProc();
Pds4Proc pds4Proc = new Pds4Proc();

String col = Tool.checkNull(request.getParameter("col"));
String word = Tool.checkNull(request.getParameter("word"));


//페이지당 출력할 레코드 갯수
int recordPerPage =  0;
if (Tool.checkNull(request.getParameter("recordPerPage")).equals("")) {
  recordPerPage = 4;
} else {

  recordPerPage = Integer.parseInt(request.getParameter("recordPerPage"));

}

//현재 페이지
int nowPage =  0;
if (Tool.checkNull(request.getParameter("nowPage")).equals("")) {
nowPage = 1; // 현재 페이지 1부터 시작
} else {
nowPage = Integer.parseInt(request.getParameter("nowPage"));
}

//목록 파일
String listFile = "";
if (request.getParameter("listFile") == null) {
listFile = "list_category_grid1.jsp";
} else {
listFile = request.getParameter("listFile");
}
%>