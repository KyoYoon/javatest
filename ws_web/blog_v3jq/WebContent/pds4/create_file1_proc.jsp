<%@ page contentType="text/html; charset=UTF-8" %>
<%@ include file="../admin4/auth.jsp" %>  
<%@ include file="./ssi.jsp" %>
 
<%
String tempDir = application.getRealPath("/pds4/temp"); // WebContent 기준
String upDir = application.getRealPath("/pds4/storage");
// request, 메모리에 저장할 크기, 업로드할 최대 파일 크기, 임시 저장 폴더
Upload upload = new Upload(request, -1, 10485760, tempDir);
 
int categoryno = Integer.parseInt(upload.getParameter("categoryno"));
CategoryVO categoryVO = categoryProc.read(categoryno);
String category_title = categoryVO.getTitle();
int count = 0;

col = Tool.checkNull(upload.getParameter("col"));
word = upload.toKor(Tool.checkNull(upload.getParameter("word")));

%>
 
<!DOCTYPE html>
<html lang="ko">
<head>
<meta charset="UTF-8">
<title></title>
<link href="../css/style.css" rel='Stylesheet' type='text/css'>
</head>
<body>
<DIV class='container'>
<jsp:include page="/menu/top.jsp" flush='false' />
<DIV class='content'>
 
  <DIV class='aside_menu'>
    <ASIDE style='float: left;'>게시판＞<%=category_title %>＞ 수정 > 신규 파일 등록</ASIDE> 
    <ASIDE style='float: right;'>
    <A href='./list_category_table2.jsp?categoryno=<%=categoryno %>&category_title=<%=category_title %>&col=<%=col%>&word=<%=word%>'>목록형</A> <span class='menu_divide'> |</span> 
    <A href='./list_category_grid1.jsp?categoryno=<%=categoryno %>&category_title=<%=category_title %>&col=<%=col%>&word=<%=word%>'>앨범형</A> <span class='menu_divide'> |</span>
    <A href='./list_read.jsp'>펼쳐보기</A>
    </ASIDE> 
    <DIV class='menu_line' style='clear: both;'></DIV>
  </DIV>
 
  <DIV class='message'>
<%
int pdsno = Integer.parseInt(upload.getParameter("pdsno"));
String passwd  = upload.toKor(upload.getParameter("passwd"));
 
if (pds4Proc.passwordCheck(pdsno, passwd) == 1) { // 패스워드 일치
  Pds4VO pds4VO = new Pds4VO();
  pds4VO.setPdsno(pdsno);  
  
  //<input type='file' name='file1' style='width: 100%;'>
  FileItem fileItem = upload.getFileItem("file1");
  String file1 = "";   // 사용자가 전송한 파일명
  String fstor1 = ""; // 실제 서버상의 디스크에 저장되는 파일명
  String thumb = ""; // preview mini 이미지
  long size1 = 0;      // 원본 파일의 크기
 
  file1 = fileItem.getName(); // 원본 파일명
  pds4VO.setFile1(file1);
 
  size1 = fileItem.getSize(); // 파일 크기
  
  if (size1 > 0) {
    if (size1 < 10485760) { // 1 ~ 10485760 byte
      fstor1 = upload.saveFile(fileItem, upDir); // Tomcat이 전송받은 파일을 서버에 저장
      pds4VO.setFstor1(fstor1);
      pds4VO.setSize1(size1);
      // 업로드 디렉토리, 소스 파일명, width, height
       if (Tool.isImage(fstor1)) { // 이미지일 때만 thumbnail image를 생성해라.
         pds4VO.setThumb(Tool.preview(upDir, fstor1, 200, 150)); // Thumbnail 이미지 생성
      }
      
      count = pds4Proc.create_file1(pds4VO); // DBMS 적용
      
      if (count == 1) {
        %>
          <span class='span_info'>
            자료를 등록했습니다.
          </span>
        <%    
        } else { // 모든 조건이 일치했으나 예외가 발생한경우
        %>
          <span class='span_warning'>
            자료 등록에 실패했습니다. 다시 시도해 주세요.
          </span>
        <%
        }
    } else {
  %>
      <span class='span_warning'>
      전송 파일의 크기는 10 MB를 넘을 수 없습니다. 다시 시도해주세요.
      </span>
  <%    
    }
  } else { // 파일이 없는 경우
  %>
    <span class='span_warning'>
      전송 파일이 없습니다. 다시 시도해주세요.
    </span> 
  <%    
  }
} else {
  %>
  <span class='span_warning'>
    패스워드가 일치하지 않습니다. 다시 시도해주세요.
  </span> 
<%     
}
%>
  </DIV>  
 
  <DIV class='bottom_menu'>
    <%
    if (count == 0) {
    %>
      <button type='button' onclick="history.back()">다시 시도</button>  
    <%  
    } else {
    %>
      <button type='button' onclick="location.href='./update_form.jsp?categoryno=<%=categoryno %>&pdsno=<%=pdsno %>&col=<%=col%>&word=<%=word%>'">계속 수정 [글 수정화면으로 돌아가기]</button>
    <%  
    }
    %>   
    <button type='button' onclick="location.href=''./list_category_grid1.jsp?categoryno=<%=categoryno %>&category_title=<%=category_title %>&col=<%=col%>&word=<%=word%>'">목록</button>
  </DIV>
  
</DIV> <!-- content END -->
<jsp:include page="/menu/bottom.jsp" flush='false' />
</DIV> <!-- container END -->
 
</body>
</html>