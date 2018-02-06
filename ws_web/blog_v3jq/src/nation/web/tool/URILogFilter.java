package nation.web.tool;
 
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
 
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
 
public class URILogFilter implements Filter {
  private Connection con = null;
  private PreparedStatement pstmt = null;
  private String charSet = null;
  private DBOpen dbopen = null;
  private DBClose dbclose = null;
  
  public URILogFilter(){
    dbopen = new DBOpen();
    dbclose = new DBClose();
  }
  
  // ��Ĺ ���۽� �ڵ� ����Ǿ� �ʱ�ȭ(�غ� �۾�) ����
  @Override
  public void init(FilterConfig config) throws ServletException {
    // /WEB-INF/web.xml������ <init-param>�±��� ��
    charSet = config.getInitParameter("charSet");
 
    System.out.println("������������������������");
    System.out.println(" URI Logger start...");
    System.out.println("������������������������");
  }
 
  // URL, �̹����� ���� ������ ������ �߻��ϸ� �ڵ� ����
  @Override
  public void doFilter(ServletRequest servletRequest, ServletResponse response, FilterChain chain)
      throws IOException, ServletException {
    HttpServletRequest request = (HttpServletRequest) servletRequest;
    
    // ------------------------------------------------------------------------------
    // session�� ��� ��
    // ------------------------------------------------------------------------------
    // ���ο� ������ ���������ʰ� ������ ���� ��ü�� ��ȯ
    HttpSession session = request.getSession(true);
    // System.out.println("session: " + session);
    
    String email = (String)session.getAttribute("email");
    if (email == null) {
      email = "Guest";
    }
    // ------------------------------------------------------------------------------
    
    // �ѱ� ���ڼ� ����
    request.setCharacterEncoding(charSet);
 
    // ��û uri ���� �κ�, �������� ������ �ּ�
    String uri = request.getRequestURI();
 
    String ip = request.getRemoteAddr();
    System.out.println("ip: " + ip);
 
    try {
      con = dbopen.getConnection();
 
      StringBuffer sql = new StringBuffer();
      sql.append(" INSERT INTO uriLog(id, ip, uri, uridate)");
      sql.append(" VALUES(?, ?, ?, now())");
      
      pstmt = con.prepareStatement(sql.toString());
      pstmt.setString(1, email);
      pstmt.setString(2, ip);
      pstmt.setString(3, uri);
 
      pstmt.executeUpdate();
    } catch (Exception e) {
      System.out.println(e.toString());
    } finally {
      dbclose.close(con, pstmt);
    }
 
    // �ٸ� ���� ����, ��û JSP ����
    chain.doFilter(request, response);
  }
 
  public void destroy() {
 
  }
}