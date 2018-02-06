<%@ page contentType="text/html; charset=UTF-8" %> 
 
<%@ include file = "./ssi.jsp"  %>
 
<%
int categoryno = Integer.parseInt(request.getParameter("categoryno"));
CategoryVO categoryVO = categoryProc.read(categoryno);
String category_title = categoryVO.getTitle();
 
int pdsno = Integer.parseInt(request.getParameter("pdsno"));
Pds4VO pds4VO = pds4Proc.read(pdsno); // 조회

out.println("listFile:" + listFile);
out.println();
out.println("nowPage: " + nowPage);
%>
<!DOCTYPE html> 
<html lang="ko"> 
<head> 
<meta charset="UTF-8"> 
<title></title> 
 
<link href="../css/style.css" rel="Stylesheet" type="text/css">
 
</head> 
 
<body>
<DIV class='container'>
<jsp:include page="/menu/top.jsp" flush='false' />
<DIV class='content'>  
 
<DIV class='aside_menu'>
  <ASIDE style='float: left;'><A href='../category4/list.jsp'>게시판</A>＞<A href='./list_category_grid1.jsp?categoryno=<%=categoryno %>&category_title=<%=category_title %>&col=<%=col%>&word=<%=word%>'><%=category_title %></A></ASIDE> 
  <ASIDE style='float: right;'>
    <A href='./list_category_table2.jsp?categoryno=<%=categoryno %>&category_title=<%=category_title %>&col=<%=col%>&word=<%=word%>&nowPage=<%=nowPage%>&recordPerPage=<%=recordPerPage %>' >목록형</A> <span class='menu_divide'> |</span> 
    <A href='./list_category_grid1.jsp?categoryno=<%=categoryno %>&category_title=<%=category_title %>&col=<%=col%>&word=<%=word%>&nowPage=<%=nowPage%>&recordPerPage=<%=recordPerPage %>' >앨범형</A> <span class='menu_divide'> |</span>
    <A href='./list_read.jsp'>펼쳐보기</A>
  </ASIDE> 
  <DIV class='menu_line' style='clear: both;'></DIV>
</DIV>
 
<FORM name='frm' method='POST' action='./create_proc.jsp' 
           enctype='multipart/form-data'>
  <input type='hidden' name='categoryno' value='<%=categoryno %>'>
           
  <fieldset class='fieldset_no_line'>
    <ul>
      <li class='li_none'>
        <span style="font-weight: bold;">
          <%=pds4VO.getTitle() %> (<%=pds4VO.getRdate() %>) |
          조회수: <%=pds4VO.getCnt() %>  
          <%
          ServletContext context = request.getSession().getServletContext();
          
          String realPath = context.getRealPath("/pds4/storage/"+pds4VO.getFstor1());
          boolean fileExists = new File(realPath).exists();
          if (pds4VO.getSize1() > 0) { // 서버에 파일이 존재하면
          %>
          | 파일명: <A href='<%=request.getContextPath()%>/download?dir=/pds4/storage&filename=<%=pds4VO.getFstor1() %>'><%=pds4VO.getFile1() %></A> (<%=Tool.unit(pds4VO.getSize1()) %>)
          <%
          }
          %>
        </span>
      </li>
      <li class='li_none'>
        <%
        if (Tool.isImage(pds4VO.getFstor1())) {
        %>
          <DIV style='float: left; margin: 10px 10px 0px 0px;'>
            <IMG src='./storage/<%=pds4VO.getFstor1() %>'>
          </DIV>
        <%  
        }
        %>
        <DIV><%=Tool.convertChar(pds4VO.getContent()) %></DIV>
      </li> 
 
       <%
      String youtube = Tool.checkNull(pds4VO.getYoutube());
      if (youtube.trim().length() > 0) { // 유투브 비디오가 있는 경우만 출력
      %>
      <li class='li_none'>
        <!-- 가운데 정렬 설정 -->
        <DIV style='width: 854px; margin: 0px auto;'>
          <%=pds4VO.getYoutube() %>
        </DIV>
      </li>  
      <%
      }
      %>
       
      <%
      if (pds4VO.getMap() != null) {
      // 지도 출력 시작 ------------->
      if (pds4VO.getMap().trim().length() > 0) { // 지도가 있는 경우만 출력
      %>
      <li class='li_none'>
        <!-- 가운데 정렬 설정 -->
        <DIV style='width:520px; margin: 0px auto;'>
          <%=pds4VO.getMap() %>
        </DIV>
      </li>  
      <%
      }
      }
      // --------------> 지도 출력 종료 
      %>
      
      <%
      String mp4 = Tool.checkNull(pds4VO.getMp4());
      if (mp4.trim().length() > 0) { // MP4 파일이 있는 경우만 출력
      %>
        <li class='li_none' style='font-size: 0.8em;'>
          <VIDEO controls src='./storage/<%=mp4 %>' style='width: 80%;'></VIDEO>
        </li>
      <%
      }
      %>
      
      <li class='li_none' style='font-size: 0.8em;'>
        <%=pds4VO.getRname() %>(<%=pds4VO.getEmail() %>) <%=pds4VO.getWeb() %>
        <%
        String mp3 = Tool.checkNull(pds4VO.getMp3());
        if (mp3.trim().length() > 0) { // MP3 파일이 있는 경우만 출력
        %>
        <DIV style='display: table; height: 100px;'>
          <DIV style='display: table-cell; vertical-align: middle; height: 20px;'>
          <AUDIO controls autoplay="autoplay">
            <source src="./storage/<%=pds4VO.getMp3() %>" type="audio/mp3">
          </AUDIO>
          </DIV>
        </DIV>
        <%
        }
        %>  
      </li>
      
      
    </ul>
  </fieldset>
 
  <div class='bottom_menu' style='width: 90%; font-size: 0.8em;'>
    <A href='./list_category_table2.jsp?categoryno=<%=categoryno %>&category_title=<%=category_title %>&col=<%=col%>&word=<%=word%>&nowPage=<%=nowPage%>&recordPerPage=<%=recordPerPage %>' ><span class='bottom_menu_item'>목록형</span></A> <span class='menu_divide'> |</span> 
    <A href='./list_category_grid1.jsp?categoryno=<%=categoryno %>&category_title=<%=category_title %>&col=<%=col%>&word=<%=word%>&nowPage=<%=nowPage%>&recordPerPage=<%=recordPerPage %>' ><span class='bottom_menu_item'>앨범형</span></A> <span class='menu_divide'> |</span>
    <A href='./list_read.jsp'><span class='bottom_menu_item'>펼쳐보기</span></A><span class='menu_divide'> |</span>  
 <%
  if (Tool.isMaster(request) || Tool.isAdmin(request)) {
  %>    
    <A href='./update_form.jsp?pdsno=<%=pdsno%>&categoryno=<%=categoryno%>&col=<%=col%>&word=<%=word%>&nowPage=<%=nowPage%>&recordPerPage=<%=recordPerPage %>&listFile=<%=listFile %>'><span class='bottom_menu_item'>수정</span></A><span class='menu_divide'> |</span>
    <A href='./delete_form.jsp?pdsno=<%=pdsno%>&categoryno=<%=categoryno%>&nowPage=<%=nowPage%>&recordPerPage=<%=recordPerPage %>&listFile=<%=listFile %>'><span class='bottom_menu_item'>삭제</span></A><span class='menu_divide'> |</span>
    <A href='./create_form.jsp?categoryno=<%=categoryno%>&category_title=<%=category_title %>&nowPage=<%=nowPage%>&recordPerPage=<%=recordPerPage %>&listFile=<%=listFile %>'><span class='bottom_menu_item'>등록</span></A>
  <%
  }
  %>    
  </div>
</FORM>
 
</DIV> <!-- content END -->
<jsp:include page="/menu/bottom.jsp" flush='false' />
</DIV> <!-- container END -->
 
</body>
</html> 