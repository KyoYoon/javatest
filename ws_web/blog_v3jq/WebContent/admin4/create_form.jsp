<%@ page contentType="text/html; charset=UTF-8" %>
<%@ include file="./auth.jsp" %> 
<%@ include file="./ssi.jsp" %>
 
<!DOCTYPE html> 
<html lang="ko"> 
<head> 
<meta charset="UTF-8"> 
<title>관리자 가입</title> 
 
<link href="../css/style.css" rel="Stylesheet" type="text/css">
 
<script type="text/javascript">
function email_duplicate() {
  var url = './email_proc.jsp?email=' + frm.email.value; // 문자열: '', ""
  var win = window.open(url, '중복 이메일 검사', 'width=500px, height=350px');
  
  var x = (screen.width - 500) / 2;    /* // 1000 - 500 = 500 / 2 = 250 */
  var y = (screen.height - 450) / 2;   /* // 800 - 450 = 350 / 2 = 175 */
  
  win.moveTo(x, y); //  화면을 가운데로 이동
  
}
</script>
 
</head> 
<body>
<DIV class='container'>
<jsp:include page="/menu/top.jsp" flush='false' />
<DIV class='content'>
 
<DIV class='aside_menu'>
  <ASIDE style='float: left;'>관리자 ＞ 등록</ASIDE> 
  <ASIDE style='float: right;'>
    <A href='./list.jsp'>목록</A> <span class='menu_divide'> |</span> 
  </ASIDE> 
  <ASIDE style='float: right;'>
  </ASIDE> 
  <DIV class='menu_line' style='clear: both;'></DIV>
</DIV>
 
<FORM name='frm' method='POST' action='./create_proc.jsp'>
  <fieldset class='fieldset_no_line'>
    <ul>
      <li class='li_none'>
        <label for='email'>이메일</label>
        <input type='email' name='email' id='email' required="required" value='test@gmail.com' style='width: 35%;'>
        [<A href='javascript: email_duplicate();'>이메일 중복 검사</A>] 
      </li>
      <li class='li_none'>
        <label for='passwd'>패스워드</label>
        <input type='password' name='passwd' id='passwd' value='123'>
      </li>
 
      <li class='li_right'>
        <button type='submit'>등록</button>
        <button type='button' onclick='history.back();'>취소</button>
        
      </li>
    </ul>
  </fieldset>
</FORM>
 
</DIV> <!-- content END -->
<jsp:include page="/menu/bottom.jsp" flush='false' />
</DIV> <!-- container END -->
</body>
</html> 