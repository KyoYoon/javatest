<%@ page contentType="text/html; charset=UTF-8" %>
 
<%@ page import="java.util.ArrayList" %>
 
<%@ page import="nation.web.category4.CategoryProc" %>
<%@ page import="nation.web.category4.CategoryVO" %>
<%@ page import="nation.web.tool.Tool" %> 
 
<%
CategoryProc categoryProc = new CategoryProc();
%>
 
<%
String root = request.getContextPath();
// out.println("root: " + root);
%>
 
<!-- 화면 상단 메뉴 --> 
<DIV class='top_menu_label'>Travel blog 0.6</DIV>
<DIV id='top_menu'
        style="background-image: url('<%=root %>/menu/images/top_image.jpg')" >
  <NAV class='top_menu_list'>
    <A class='menu_link' href='<%=root %>/index.jsp'>HOME</A> <span class='top_menu1'> | </span>
    
    <A class='menu_link' href='<%=root %>/notice3/list.jsp'>공지사항</A> <span class='top_menu1'> | </span>
   

   

    <%
    ArrayList<CategoryVO> list = categoryProc.list();
    for (int index=0; index < list.size(); index++) {
      CategoryVO categoryVO = list.get(index);
  
      int categoryno = categoryVO.getCategoryno();
      String title = categoryVO.getTitle();
      %>
        <A class='menu_link'  href='<%=root %>/pds4/list_category_grid1.jsp?categoryno=<%=categoryno %>&category_title=<%=categoryVO.getTitle() %>'><%=categoryVO.getTitle() %></A>
        <span class='top_menu1'> | </span>
      <%  
    }
    %>
    <A class='menu_link'  href='<%=root %>/visitor4/list.jsp'>방명록</A> <span class='top_menu1'> | </span>
 
<%
if (Tool.isAdmin(request)){
%>
  [관리자: <%=session.getAttribute("email") %>]    
  <A class='menu_link'  href='<%=root %>/category4/list.jsp'>Category</A><span class='top_menu1' > |</span>
  <% 
  if (Tool.isMaster(request)) { %>
    <A class='menu_link'  href='<%=root %>/admin4/list.jsp'>Admin list</A><span class='top_menu1' > |</span>
    <A class='menu_link'  href='<%=root %>/log/list.jsp'>Log</A><span class='top_menu1' > |</span>
  <% 
  } %>
  <a class='menu_link' href='<%=root %>/admin4/logout_proc.jsp'>Logout</a> 
<%
}else{ // 로그인을 하지 않으면 
%>
  <a class='menu_link' href='<%=root %>/admin4/login_ck_form.jsp'>Admin</a>
<%
}
%>
 
  </NAV>
</DIV>