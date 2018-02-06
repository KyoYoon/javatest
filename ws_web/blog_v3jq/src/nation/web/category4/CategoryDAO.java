package nation.web.category4;
 
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import nation.web.tool.DBClose;
import nation.web.tool.DBOpen;
 
public class CategoryDAO {
  private Connection con = null;              // DBMS ����   
  private PreparedStatement pstmt = null; // SQL ����
  private ResultSet rs = null;                   // SELECT ����� ����
  private StringBuffer sql = null;              // SQL ����
  
  private DBOpen dbopen = null;
  private DBClose dbclose = null;
  
  public CategoryDAO() {
    dbopen = new DBOpen();
    dbclose = new DBClose();
  }
  
  /**
   * ī�װ� ���
   * @param categoryVO
   * @return ��ϵ� ���� 1 or 0
   */
  public int insert(CategoryVO categoryVO){ // call by reference
    int count = 0;                     // ó���� ���ڵ� ����
    try {
      con = dbopen.getConnection();
      sql = new StringBuffer();
      sql.append(" INSERT INTO category(title, seqno, visible, ids, cnt)");
      sql.append(" VALUES(?, ?, ?, ?, 0)");
      
      pstmt = con.prepareStatement(sql.toString());
      pstmt.setString(1, categoryVO.getTitle());
      pstmt.setInt(2, categoryVO.getSeqno());
      pstmt.setString(3, categoryVO.getVisible());
      pstmt.setString(4, categoryVO.getIds());
      count = pstmt.executeUpdate();
      
    } catch (SQLException e) {
      e.printStackTrace();
    } finally {
      dbclose.close(con, pstmt);
    }
    
    return count;
  }
  
  /**
   * ��ü ���
   * @return
   */
  public ArrayList<CategoryVO> list(){
    ArrayList<CategoryVO> list = new ArrayList<CategoryVO>();
    
    try {
      con = dbopen.getConnection();
      sql = new StringBuffer();
      sql.append(" SELECT categoryno, title, seqno, visible, ids, cnt");
      sql.append(" FROM category");
      sql.append(" ORDER BY seqno ASC");
      
      pstmt = con.prepareStatement(sql.toString());
      rs = pstmt.executeQuery();
      while(rs.next()) { // ���ڵ� ������ŭ vo ��ü ����
        CategoryVO categoryVO = new CategoryVO();
        categoryVO.setCategoryno(rs.getInt("categoryno"));
        categoryVO.setTitle(rs.getString("title"));
        categoryVO.setSeqno(rs.getInt("seqno"));
        categoryVO.setVisible(rs.getString("visible"));
        categoryVO.setIds(rs.getString("ids"));
        categoryVO.setCnt(rs.getInt("cnt"));
        list.add(categoryVO);
      }
    } catch (SQLException e) {
      e.printStackTrace();
    } finally {
      dbclose.close(con, pstmt, rs);
    }
      
    return list;
  }  
  
  /**
   * ��ȸ
   * @param categoryno Category ��ȣ
   * @return
   */
  public CategoryVO read(int categoryno){
    CategoryVO categoryVO = new CategoryVO();
    
    try {
      con = dbopen.getConnection();
      sql = new StringBuffer();
      sql.append(" SELECT categoryno, title, seqno, visible, ids, cnt");
      sql.append(" FROM category");
      sql.append(" WHERE categoryno = ?");
      
      pstmt = con.prepareStatement(sql.toString());
      pstmt.setInt(1, categoryno);
      
      rs = pstmt.executeQuery();
      if (rs.next()) { // ���ڵ� ������ŭ newsVO ��ü ����
        categoryVO.setCategoryno(rs.getInt("categoryno"));
        categoryVO.setTitle(rs.getString("title"));
        categoryVO.setSeqno(rs.getInt("seqno"));
        categoryVO.setVisible(rs.getString("visible"));
        categoryVO.setIds(rs.getString("ids"));
        categoryVO.setCnt(rs.getInt("cnt"));
      }
    } catch (SQLException e) {
      e.printStackTrace();
    } finally {
      dbclose.close(con, pstmt, rs);
    }
      
    return categoryVO;
    
  }  

  /**
   * Category ����
   * @param CategoryVO
   * @return ����� ����
   */
  public int update(CategoryVO categoryVO){ // call by reference
    int count = 0;                     // ó���� ���ڵ� ����
    try {
      con = dbopen.getConnection();
      sql = new StringBuffer();
      sql.append(" UPDATE category");
      sql.append(" SET title=?, seqno=?, visible=?, ids=?");
      sql.append(" WHERE categoryno=?");
      
      pstmt = con.prepareStatement(sql.toString());
      pstmt.setString(1, categoryVO.getTitle());
      pstmt.setInt(2, categoryVO.getSeqno());
      pstmt.setString(3, categoryVO.getVisible());
      pstmt.setString(4, categoryVO.getIds());
      pstmt.setInt(5, categoryVO.getCategoryno());
      count = pstmt.executeUpdate();
      
    } catch (SQLException e) {
      e.printStackTrace();
    } finally {
      dbclose.close(con, pstmt);
    }
    
    return count;
  }  

  /**
   * Category ����
   * @param categoryno
   * @return ������ ����
   */
  public int delete(int categoryno){ 
    int count = 0;                         // ó���� ���ڵ� ����
    try {
      con = dbopen.getConnection();
      sql = new StringBuffer();
      sql.append(" DELETE FROM category");
      sql.append(" WHERE categoryno=?");
      
      pstmt = con.prepareStatement(sql.toString());
      pstmt.setInt(1, categoryno);
      count = pstmt.executeUpdate();
      
    } catch (SQLException e) {
      e.printStackTrace();
    } finally {
      dbclose.close(con, pstmt);
    }
    
    return count;
  } 
  
  public int high(int categoryno) { // �켱���� ����, 1 ����
    int count = 0;
    
    try {
      con = dbopen.getConnection();
      sql = new StringBuffer();
      sql.append(" UPDATE category");
      sql.append(" SET seqno = seqno - 1");
      sql.append(" WHERE categoryno=?");
      
      pstmt = con.prepareStatement(sql.toString());
      pstmt.setInt(1, categoryno);
      
      count = pstmt.executeUpdate();
      
    } catch (SQLException e) {
      e.printStackTrace();
    } finally {
      dbclose.close(con, pstmt);
    }
    
    return count;
  }
  
  public int low(int categoryno) { // �켱���� ����, 1 ���ϱ�
    int count = 0;
    
    try {
      con = dbopen.getConnection();
      sql = new StringBuffer();
      sql.append(" UPDATE category");
      sql.append(" SET seqno = seqno + 1");
      sql.append(" WHERE categoryno=?");
      
      pstmt = con.prepareStatement(sql.toString());
      pstmt.setInt(1, categoryno);
      
      count = pstmt.executeUpdate();
      
    } catch (SQLException e) {
      e.printStackTrace();
    } finally {
      dbclose.close(con, pstmt);
    }    
    
    return count;
  }

  /**
   * �Խ��� ���
   * @param categoryno
   * @return ������ ����
   */
  public int show(int categoryno){
    int count = 0;                     // ó���� ���ڵ� ����
    try {
      con = dbopen.getConnection();
      sql = new StringBuffer();
      sql.append(" UPDATE category");
      sql.append(" SET visible='Y'");
      sql.append(" WHERE categoryno=?");
      
      pstmt = con.prepareStatement(sql.toString());
      pstmt.setInt(1, categoryno);
      
      count = pstmt.executeUpdate();
      
    } catch (SQLException e) {
      e.printStackTrace();
    } finally {
      dbclose.close(con, pstmt);
    }
    
    return count;
  }
  
  /**
   * �Խ��� ����
   * @param categoryno
   * @return ������ ����
   */
  public int hide(int categoryno){
    int count = 0;                     // ó���� ���ڵ� ����
    try {
      con = dbopen.getConnection();
      sql = new StringBuffer();
      sql.append(" UPDATE category");
      sql.append(" SET visible='N'");
      sql.append(" WHERE categoryno=?");
      
      pstmt = con.prepareStatement(sql.toString());
      pstmt.setInt(1, categoryno);
      
      count = pstmt.executeUpdate();
      
    } catch (SQLException e) {
      e.printStackTrace();
    } finally {
      dbclose.close(con, pstmt);
    }
    
    return count;
  }
  
  /**
   * ������ �ִ밪 ���� 
   * @return
   */
  public int getMaxSeqno() {
    int maxSeqno = 0;
    
    try {
      con = dbopen.getConnection();
      sql = new StringBuffer();
      sql.append(" SELECT MAX(seqno)+1 as seqno");
      sql.append(" FROM category");
      
      
      pstmt = con.prepareStatement(sql.toString());
      
      
      rs = pstmt.executeQuery();
      if (rs.next()) { // ���ڵ� ������ŭ newsVO ��ü ����
        maxSeqno = rs.getInt("seqno");
      }
    } catch (SQLException e) {
      e.printStackTrace();
    } finally {
      dbclose.close(con, pstmt, rs);
    }    
    
    return maxSeqno;
  }

  /**
   * ���� �Խ��� �ۼ��� ����
   * @param categoryno
   * @return
   */
  public int increaseCnt(int categoryno) { // call by reference
    int count = 0;                     // ó���� ���ڵ� ����
    try {
      con = dbopen.getConnection();
      sql = new StringBuffer();
      sql.append(" UPDATE category");
      sql.append(" SET cnt = cnt + 1");
      sql.append(" WHERE categoryno=?");
      
      pstmt = con.prepareStatement(sql.toString());
      pstmt.setInt(1, categoryno);
  
      count = pstmt.executeUpdate();
      
    } catch (SQLException e) {
      e.printStackTrace();
    } finally {
      dbclose.close(con, pstmt);
    }
    
    return count;
  }
 
  /**
   * ���� �Խ��� �ۼ��� ����
   * @param categoryno
   * @return
   */
  public int decreaseCnt(int categoryno) { // call by reference
    int count = 0;                     // ó���� ���ڵ� ����
    try {
      con = dbopen.getConnection();
      sql = new StringBuffer();
      sql.append(" UPDATE category");
      sql.append(" SET cnt = cnt - 1");
      sql.append(" WHERE categoryno=?");
      
      pstmt = con.prepareStatement(sql.toString());
      pstmt.setInt(1, categoryno);
  
      count = pstmt.executeUpdate();
      
    } catch (SQLException e) {
      e.printStackTrace();
    } finally {
      dbclose.close(con, pstmt);
    }
    
    return count;
  }  
  
}