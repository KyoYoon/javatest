package nation.web.pds4;
 
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import nation.web.tool.DBClose;
import nation.web.tool.DBOpen;
 
public class Pds4DAO {
  private Connection con = null;              // DBMS ����   
  private PreparedStatement pstmt = null; // SQL ����
  private ResultSet rs = null;                   // SELECT ����� ����
  private StringBuffer sql = null;              // SQL ����
  
  private DBOpen dbopen = null;
  private DBClose dbclose = null;
  
  public Pds4DAO() {
    dbopen = new DBOpen();
    dbclose = new DBClose();
  }

  /**
   * ���
   * @param pds4VO
   * @return ����� ����
   */
  public int insert(Pds4VO pds4VO){ // call by reference
    int count = 0;                     // ó���� ���ڵ� ����
    try {
      con = dbopen.getConnection();
      sql = new StringBuffer();
      sql.append(" INSERT INTO pds4(categoryno, rname, email, title, content,");
      sql.append(" passwd, cnt, rdate, web, file1, fstor1, thumb, size1, map, youtube, mp3, size2, mp4, size3, ip, visible)");
      sql.append(" VALUES(?, ?, ?, ?, ?, ?, 0, now(), ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)");
      
      pstmt = con.prepareStatement(sql.toString());
      pstmt.setInt(1, pds4VO.getCategoryno());
      pstmt.setString(2, pds4VO.getRname());
      pstmt.setString(3, pds4VO.getEmail());
      pstmt.setString(4, pds4VO.getTitle());
      pstmt.setString(5, pds4VO.getContent());
      pstmt.setString(6, pds4VO.getPasswd());
      pstmt.setString(7, pds4VO.getWeb());
      pstmt.setString(8, pds4VO.getFile1());
      pstmt.setString(9, pds4VO.getFstor1()); // �������� ���� �̹��� �̸� 
      pstmt.setString(10, pds4VO.getThumb()); // �������� Thumbnail �̹��� �̸�
      pstmt.setLong(11, pds4VO.getSize1());
      pstmt.setString(12, pds4VO.getMap()); // ���� 
      pstmt.setString(13,  pds4VO.getYoutube()); // ������ 
      pstmt.setString(14, pds4VO.getMp3()); // mp3 ���� �̸� 
      pstmt.setLong(15, pds4VO.getSize2()); // mp3 ���� ������ 
      pstmt.setString(16, pds4VO.getMp4()); // mp4 ���� �̸�   
      pstmt.setLong(17, pds4VO.getSize3()); // mp4 ���� ������ 
      pstmt.setString(18, pds4VO.getIp());
      pstmt.setString(19, pds4VO.getVisible());
      
      count = pstmt.executeUpdate();
      
    } catch (SQLException e) {
      e.printStackTrace();
    } finally {
      dbclose.close(con, pstmt);
    }
    
    return count;
  }  

  /**
   * ��� ���ڵ�
   * 
   * @return
   */
  public ArrayList<Pds4VO> list() {
    ArrayList<Pds4VO> list = new ArrayList<Pds4VO>();
 
    try {
      con = dbopen.getConnection();
 
      sql = new StringBuffer();
      sql.append(" SELECT pdsno, categoryno, rname, email, title, content, passwd, cnt,");
      sql.append("            SUBSTRING(rdate, 1, 10) as rdate, web, file1, fstor1,");
      sql.append("            thumb, size1, map, ip, visible");
      sql.append(" FROM pds4");
      sql.append(" ORDER BY pdsno DESC");
 
      pstmt = con.prepareStatement(sql.toString());
      rs = pstmt.executeQuery(); // SELECT
 
      while (rs.next() == true) {
        Pds4VO pds4VO = new Pds4VO();
        pds4VO.setPdsno(rs.getInt("pdsno")); // DBMS -> JAVA ��ü
        pds4VO.setCategoryno(rs.getInt("categoryno"));
        pds4VO.setRname(rs.getString("rname"));
        pds4VO.setEmail(rs.getString("email"));
        pds4VO.setTitle(rs.getString("title"));
        pds4VO.setContent(rs.getString("content"));
        pds4VO.setPasswd(rs.getString("passwd"));
        pds4VO.setCnt(rs.getInt("cnt"));
        pds4VO.setRdate(rs.getString("rdate"));
        pds4VO.setWeb(rs.getString("web"));
        pds4VO.setFile1(rs.getString("file1"));
        pds4VO.setFstor1(rs.getString("fstor1"));
        pds4VO.setThumb(rs.getString("thumb"));
        pds4VO.setSize1(rs.getLong("size1"));
        pds4VO.setMap(rs.getString("map"));
        pds4VO.setIp(rs.getString("ip"));
        pds4VO.setVisible(rs.getString("visible"));
 
        list.add(pds4VO);
      }
 
    } catch (Exception e) {
      e.printStackTrace();
    } finally {
      dbclose.close(con, pstmt, rs);
    }
 
    return list;
  }  

  /**
   * ���޹��� category ��ȣ�� �ش��ϴ� ���ڵ常 �����ش�.
   * 
   * @return
   */
  public ArrayList<Pds4VO> list_category(int categoryno) {
    ArrayList<Pds4VO> list = new ArrayList<Pds4VO>();
 
    try {
      con = dbopen.getConnection();
 
      sql = new StringBuffer();
      sql.append(" SELECT pdsno, categoryno, rname, email, title, content, passwd, cnt,");
      sql.append("            SUBSTRING(rdate, 1, 10) as rdate, web, file1, fstor1,");
      sql.append("            thumb, size1, ip, visible");
      sql.append(" FROM pds4");
      sql.append(" WHERE categoryno = ?");
      sql.append(" ORDER BY pdsno DESC");
 
      pstmt = con.prepareStatement(sql.toString());
      pstmt.setInt(1,  categoryno);
      rs = pstmt.executeQuery(); // SELECT
 
      while (rs.next() == true) {
        Pds4VO pds4VO = new Pds4VO();
        pds4VO.setPdsno(rs.getInt("pdsno")); // DBMS -> JAVA ��ü
        pds4VO.setCategoryno(rs.getInt("categoryno"));
        pds4VO.setRname(rs.getString("rname"));
        pds4VO.setEmail(rs.getString("email"));
        pds4VO.setTitle(rs.getString("title"));
        pds4VO.setContent(rs.getString("content"));
        pds4VO.setPasswd(rs.getString("passwd"));
        pds4VO.setCnt(rs.getInt("cnt"));
        pds4VO.setRdate(rs.getString("rdate"));
        pds4VO.setWeb(rs.getString("web"));
        pds4VO.setFile1(rs.getString("file1"));
        pds4VO.setFstor1(rs.getString("fstor1"));
        pds4VO.setThumb(rs.getString("thumb"));
        pds4VO.setSize1(rs.getLong("size1"));
        pds4VO.setIp(rs.getString("ip"));
        pds4VO.setVisible(rs.getString("visible"));
 
        list.add(pds4VO);
      }
 
    } catch (Exception e) {
      e.printStackTrace();
    } finally {
      dbclose.close(con, pstmt, rs);
    }
 
    return list;
  }  

  /**
   * ���
   * @param pds4VO
   * @return ����� ����
   */
  public void increaseCnt(int pdsno){ // call by value
    int count = 0;                            // ó���� ���ڵ� ����
    try {
      con = dbopen.getConnection();
      sql = new StringBuffer();
      sql.append(" UPDATE pds4");
      sql.append(" SET cnt = cnt + 1");
      sql.append(" WHERE pdsno=?");
      
      pstmt = con.prepareStatement(sql.toString());
      pstmt.setInt(1, pdsno);
      
      count = pstmt.executeUpdate();
      
    } catch (SQLException e) {
      e.printStackTrace();
    } finally {
      dbclose.close(con, pstmt);
    }
 
  }  

  /**
   * ��ȸ
   * 
   * @return
   */
  public Pds4VO read(int pdsno){
    Pds4VO pds4VO = new Pds4VO();
    
    try {
      con = dbopen.getConnection();
 
      sql = new StringBuffer();
      sql.append(" SELECT pdsno, categoryno, rname, email, title, content, passwd, cnt,");
      sql.append("            SUBSTRING(rdate, 1, 16) as rdate, web, file1, fstor1,");
      sql.append("            thumb, size1, map, youtube, mp3, size2, mp4, size3, ip, visible");
      sql.append(" FROM pds4");
      sql.append(" WHERE pdsno = ?");
 
      pstmt = con.prepareStatement(sql.toString());
      pstmt.setInt(1,  pdsno);
      rs = pstmt.executeQuery(); // SELECT
 
      if (rs.next() == true) {
        pds4VO.setPdsno(rs.getInt("pdsno")); // DBMS -> JAVA ��ü
        pds4VO.setCategoryno(rs.getInt("categoryno"));
        pds4VO.setRname(rs.getString("rname"));
        pds4VO.setEmail(rs.getString("email"));
        pds4VO.setTitle(rs.getString("title"));
        pds4VO.setContent(rs.getString("content"));
        pds4VO.setPasswd(rs.getString("passwd"));
        pds4VO.setCnt(rs.getInt("cnt"));
        pds4VO.setRdate(rs.getString("rdate"));
        pds4VO.setWeb(rs.getString("web"));
        pds4VO.setFile1(rs.getString("file1"));
        pds4VO.setFstor1(rs.getString("fstor1"));
        pds4VO.setThumb(rs.getString("thumb"));
        pds4VO.setSize1(rs.getLong("size1"));
        pds4VO.setMap(rs.getString("map"));
        pds4VO.setYoutube(rs.getString("youtube"));
        pds4VO.setMp3(rs.getString("mp3"));
        pds4VO.setSize2(rs.getLong("size2"));
        pds4VO.setMp4(rs.getString("mp4"));
        pds4VO.setSize3(rs.getLong("size3"));
        pds4VO.setIp(rs.getString("ip"));
        pds4VO.setVisible(rs.getString("visible"));
      }
 
    } catch (Exception e) {
      e.printStackTrace();
    } finally {
      dbclose.close(con, pstmt, rs);
    }
 
    return pds4VO;
  }  

  /**
   * �н����带 �˻��մϴ�.
   * @param pdsno
   * @param passwd
   * @return
   */
  public int passwordCheck(int pdsno, String passwd){
    int count = 0;
    
    try {
      con = dbopen.getConnection();
 
      sql = new StringBuffer();
      sql.append(" SELECT count(*) as cnt");
      sql.append(" FROM pds4");
      sql.append(" WHERE pdsno=? AND passwd=?");
 
      pstmt = con.prepareStatement(sql.toString());
      pstmt.setInt(1,  pdsno);
      pstmt.setString(2,  passwd);
      rs = pstmt.executeQuery(); // SELECT
 
      if (rs.next() == true) {
        count = rs.getInt("cnt"); // DBMS -> JAVA ��ü
      }
 
    } catch (Exception e) {
      e.printStackTrace();
    } finally {
      dbclose.close(con, pstmt, rs);
    }
 
    return count;
  }  

  /**
   * �� ����
   * @param pds4VO
   * @return ����� ����
   */
  public int update(Pds4VO pds4VO){ // call by reference
    int count = 0;                     // ó���� ���ڵ� ����
    try {
      con = dbopen.getConnection();
      sql = new StringBuffer();
      sql.append(" UPDATE pds4");
      sql.append(" SET rname=?, email=?, title=?, content=?, web=?");
      sql.append(" WHERE pdsno=?");
      
      pstmt = con.prepareStatement(sql.toString());
      pstmt.setString(1, pds4VO.getRname());
      pstmt.setString(2, pds4VO.getEmail());
      pstmt.setString(3, pds4VO.getTitle());
      pstmt.setString(4, pds4VO.getContent());
      pstmt.setString(5, pds4VO.getWeb());
      pstmt.setInt(6, pds4VO.getPdsno());
      
      count = pstmt.executeUpdate();
      
    } catch (SQLException e) {
      e.printStackTrace();
    } finally {
      dbclose.close(con, pstmt);
    }
    
    return count;
  }  

  /**
   * Visible ��� ����
   * @param pds4VO
   * @return ����� ����
   */
  public int show_hide(int pdsno, String visible){ // call by reference
    int count = 0;                     // ó���� ���ڵ� ����
    try {
      con = dbopen.getConnection();
      sql = new StringBuffer();
      sql.append(" UPDATE pds4");
      sql.append(" SET visible = ?");
      sql.append(" WHERE pdsno=?");
      
      pstmt = con.prepareStatement(sql.toString());
      pstmt.setString(1, visible);
      pstmt.setInt(2, pdsno);
      
      count = pstmt.executeUpdate();
      
    } catch (SQLException e) {
      e.printStackTrace();
    } finally {
      dbclose.close(con, pstmt);
    }
    
    return count;
  }  

  /**
   * ���� ����
   * @param pdsno
   * @return
   */
  public int update_file1(Pds4VO pds4VO) {
    int count = 0;                     // ó���� ���ڵ� ����
    try {
      con = dbopen.getConnection();
      sql = new StringBuffer();
      sql.append(" UPDATE pds4");
      sql.append(" SET file1=?, fstor1=?, thumb=?, size1=?");
      sql.append(" WHERE pdsno=?");
      
      pstmt = con.prepareStatement(sql.toString());
      pstmt.setString(1, pds4VO.getFile1());
      pstmt.setString(2, pds4VO.getFstor1());
      pstmt.setString(3, pds4VO.getThumb());
      pstmt.setLong(4,  pds4VO.getSize1());
      pstmt.setInt(5,  pds4VO.getPdsno());
      
      count = pstmt.executeUpdate();
      
    } catch (SQLException e) {
      e.printStackTrace();
    } finally {
      dbclose.close(con, pstmt);
    }
    
    return count;
    
    
  }  

  /**
   * �� ����
   * @param 
   * @return ó���� ����
   */
  public int delete(int pdsno){ 
    int count = 0;                     // ó���� ���ڵ� ����
    try {
      con = dbopen.getConnection();
      sql = new StringBuffer();
      sql.append(" DELETE FROM pds4");
      sql.append(" WHERE pdsno=?");
      
      pstmt = con.prepareStatement(sql.toString());
      pstmt.setInt(1, pdsno);
      
      count = pstmt.executeUpdate();
      
    } catch (SQLException e) {
      e.printStackTrace();
    } finally {
      dbclose.close(con, pstmt);
    }
    
    return count;
  }  

  /**
   * Map ���� �� ����
   * @param pdsno
   * @return
   */
  public int update_map(Pds4VO pds4VO) {
    int count = 0;                     // ó���� ���ڵ� ����
    try {
      con = dbopen.getConnection();
      sql = new StringBuffer();
      sql.append(" UPDATE pds4");
      sql.append(" SET map=?");
      sql.append(" WHERE pdsno=?");
      
      pstmt = con.prepareStatement(sql.toString());
      pstmt.setString(1, pds4VO.getMap());
      pstmt.setInt(2,  pds4VO.getPdsno());
      
      count = pstmt.executeUpdate();
      
    } catch (SQLException e) {
      e.printStackTrace();
    } finally {
      dbclose.close(con, pstmt);
    }
    
    return count;
  }  

  /**
   * Youtube ���� �� ����
   * @param pdsno
   * @return
   */
  public int update_youtube(Pds4VO pds4VO) {
    int count = 0;                     // ó���� ���ڵ� ����
    try {
      con = dbopen.getConnection();
      sql = new StringBuffer();
      sql.append(" UPDATE pds4");
      sql.append(" SET youtube=?");
      sql.append(" WHERE pdsno=?");
      
      pstmt = con.prepareStatement(sql.toString());
      pstmt.setString(1, pds4VO.getYoutube());
      pstmt.setInt(2,  pds4VO.getPdsno());
      
      count = pstmt.executeUpdate();
      
    } catch (SQLException e) {
      e.printStackTrace();
    } finally {
      dbclose.close(con, pstmt);
    }
    
    return count;
  }
  
  /**
   * MP3 ���� ����
   * @param pdsno
   * @return
   */
  public int update_mp3(Pds4VO pds4VO) {
    int count = 0;                     // ó���� ���ڵ� ����
    try {
      con = dbopen.getConnection();
      sql = new StringBuffer();
      sql.append(" UPDATE pds4");
      sql.append(" SET mp3=?, size2=?");
      sql.append(" WHERE pdsno=?");
      
      pstmt = con.prepareStatement(sql.toString());
      pstmt.setString(1, pds4VO.getMp3());
      pstmt.setLong(2, pds4VO.getSize2());
      pstmt.setInt(3,  pds4VO.getPdsno());
      
      count = pstmt.executeUpdate();
      
    } catch (SQLException e) {
      e.printStackTrace();
    } finally {
      dbclose.close(con, pstmt);
    }
    
    return count;
 
  }  

  /**
   * MP4 ���� ����
   * @param pdsno
   * @return
   */
  public int update_mp4(Pds4VO pds4VO) {
    int count = 0;                     // ó���� ���ڵ� ����
    try {
      con = dbopen.getConnection();
      sql = new StringBuffer();
      sql.append(" UPDATE pds4");
      sql.append(" SET mp4=?, size3=?");
      sql.append(" WHERE pdsno=?");
      
      pstmt = con.prepareStatement(sql.toString());
      pstmt.setString(1, pds4VO.getMp4());
      pstmt.setLong(2, pds4VO.getSize3());
      pstmt.setInt(3,  pds4VO.getPdsno());
      
      count = pstmt.executeUpdate();
      
    } catch (SQLException e) {
      e.printStackTrace();
    } finally {
      dbclose.close(con, pstmt);
    }
    
    return count;
 
  }  

  /**
   * �˻��� ��� ���ڵ�
   * 
   * @return
   */
  public ArrayList<Pds4VO> list(String col, String word) {
    ArrayList<Pds4VO> list = new ArrayList<Pds4VO>();
  
    try {
      con = dbopen.getConnection();
  
      sql = new StringBuffer();
      sql.append(" SELECT pdsno, categoryno, rname, email, title, content, passwd, cnt,");
      sql.append("            SUBSTRING(rdate, 1, 10) as rdate, web, file1, fstor1,");
      sql.append("            thumb, size1, map, youtube, mp3, size2, mp4, size3, ip, visible");
      sql.append(" FROM pds4");
      
      if (col.equals("rname")) {
        sql.append(" WHERE rname LIKE ?");
        sql.append(" ORDER BY pdsno DESC");
        pstmt = con.prepareStatement(sql.toString());
        pstmt.setString(1, "%" + word + "%");
      } else if (col.equals("title")) {
        sql.append(" WHERE title LIKE ?");
        sql.append(" ORDER BY pdsno DESC");
        pstmt = con.prepareStatement(sql.toString());
        pstmt.setString(1, "%" + word + "%");
      } else if (col.equals("content")) {
        sql.append(" WHERE content LIKE ?");
        sql.append(" ORDER BY pdsno DESC");
        pstmt = con.prepareStatement(sql.toString());
        pstmt.setString(1, "%" + word + "%");
      } else if (col.equals("title_content")) {
        sql.append(" WHERE title LIKE ? OR content LIKE ?");
        sql.append(" ORDER BY pdsno DESC");
        pstmt = con.prepareStatement(sql.toString());
        pstmt.setString(1, "%" + word + "%");
        pstmt.setString(2, "%" + word + "%");
      } else { // �˻����� �ʴ� ���
        sql.append(" ORDER BY pdsno DESC");
        pstmt = con.prepareStatement(sql.toString());
      }
 
      rs = pstmt.executeQuery(); // SELECT
  
      while (rs.next() == true) {
        Pds4VO pds4VO = new Pds4VO();
        pds4VO.setPdsno(rs.getInt("pdsno")); // DBMS -> JAVA ��ü
        pds4VO.setCategoryno(rs.getInt("categoryno"));
        pds4VO.setRname(rs.getString("rname"));
        pds4VO.setEmail(rs.getString("email"));
        pds4VO.setTitle(rs.getString("title"));
        pds4VO.setContent(rs.getString("content"));
        pds4VO.setPasswd(rs.getString("passwd"));
        pds4VO.setCnt(rs.getInt("cnt"));
        pds4VO.setRdate(rs.getString("rdate"));
        pds4VO.setWeb(rs.getString("web"));
        pds4VO.setFile1(rs.getString("file1"));
        pds4VO.setFstor1(rs.getString("fstor1"));
        pds4VO.setThumb(rs.getString("thumb"));
        pds4VO.setSize1(rs.getLong("size1"));
        pds4VO.setMap(rs.getString("map"));
        pds4VO.setYoutube(rs.getString("youtube"));
        pds4VO.setMp3(rs.getString("mp3"));
        pds4VO.setSize2(rs.getLong("size2"));
        pds4VO.setMp4(rs.getString("mp4"));
        pds4VO.setSize3(rs.getLong("size3"));
        pds4VO.setIp(rs.getString("ip"));
        pds4VO.setVisible(rs.getString("visible"));
  
        list.add(pds4VO);
      }
  
    } catch (Exception e) {
      e.printStackTrace();
    } finally {
      dbclose.close(con, pstmt, rs);
    }
  
    return list;
  }
 
  /**
   * ī�װ����� �˻��� ���ڵ�
   * 
   * @return
   */
  public ArrayList<Pds4VO> list_category(int categoryno, String col, String word) {
    ArrayList<Pds4VO> list = new ArrayList<Pds4VO>();
  
    try {
      con = dbopen.getConnection();
  
      sql = new StringBuffer();
      sql.append(" SELECT pdsno, categoryno, rname, email, title, content, passwd, cnt,");
      sql.append("            SUBSTRING(rdate, 1, 16) as rdate, web, file1, fstor1,");
      sql.append("            thumb, size1, map, youtube, mp3, size2, mp4, size3, ip, visible");
      sql.append(" FROM pds4");
      
      if (col.equals("rname")) {
        sql.append(" WHERE categoryno = ? AND rname LIKE ?");
        sql.append(" ORDER BY pdsno DESC");
        pstmt = con.prepareStatement(sql.toString());
        pstmt.setInt(1, categoryno);
        pstmt.setString(2, "%" + word + "%");
      } else if (col.equals("title")) {
        sql.append(" WHERE categoryno = ? AND  title LIKE ?");
        sql.append(" ORDER BY pdsno DESC");
        pstmt = con.prepareStatement(sql.toString());
        pstmt.setInt(1, categoryno);
        pstmt.setString(2, "%" + word + "%");
      } else if (col.equals("content")) {
        sql.append(" WHERE categoryno = ? AND  content LIKE ?");
        sql.append(" ORDER BY pdsno DESC");
        pstmt = con.prepareStatement(sql.toString());
        pstmt.setInt(1, categoryno);
        pstmt.setString(2, "%" + word + "%");
      } else if (col.equals("title_content")) {
        sql.append(" WHERE categoryno = ? AND title LIKE ? OR content LIKE ?");
        sql.append(" ORDER BY pdsno DESC");
        pstmt = con.prepareStatement(sql.toString());
        pstmt.setInt(1, categoryno);
        pstmt.setString(2, "%" + word + "%");
        pstmt.setString(3, "%" + word + "%");
      } else { // �˻����� �ʴ� ���
        sql.append(" WHERE categoryno = ?");
        sql.append(" ORDER BY pdsno DESC");
        pstmt = con.prepareStatement(sql.toString());
        pstmt.setInt(1, categoryno);
      }
  
      rs = pstmt.executeQuery(); // SELECT
  
      while (rs.next() == true) {
        Pds4VO pds4VO = new Pds4VO();
        pds4VO.setPdsno(rs.getInt("pdsno")); // DBMS -> JAVA ��ü
        pds4VO.setCategoryno(rs.getInt("categoryno"));
        pds4VO.setRname(rs.getString("rname"));
        pds4VO.setEmail(rs.getString("email"));
        pds4VO.setTitle(rs.getString("title"));
        pds4VO.setContent(rs.getString("content"));
        pds4VO.setPasswd(rs.getString("passwd"));
        pds4VO.setCnt(rs.getInt("cnt"));
        pds4VO.setRdate(rs.getString("rdate"));
        pds4VO.setWeb(rs.getString("web"));
        pds4VO.setFile1(rs.getString("file1"));
        pds4VO.setFstor1(rs.getString("fstor1"));
        pds4VO.setThumb(rs.getString("thumb"));
        pds4VO.setSize1(rs.getLong("size1"));
        pds4VO.setMap(rs.getString("map"));
        pds4VO.setYoutube(rs.getString("youtube"));
        pds4VO.setMp3(rs.getString("mp3"));
        pds4VO.setSize2(rs.getLong("size2"));
        // System.out.println("rs.getString(\"mp3\"): " + rs.getString("mp3"));
        // System.out.println("pds4VO.getMp3(\"mp3\"): " + pds4VO.getMp3());
        pds4VO.setMp4(rs.getString("mp4"));
        pds4VO.setSize3(rs.getLong("size3"));
        pds4VO.setIp(rs.getString("ip"));
        pds4VO.setVisible(rs.getString("visible"));
  
        list.add(pds4VO);
      }
  
    } catch (Exception e) {
      e.printStackTrace();
    } finally {
      dbclose.close(con, pstmt, rs);
    }
  
    return list;
  }  

  /**
   * ī�װ����� �˻��� ���ڵ� �Ǽ� ����
   * 
   * @return count 
   */
  public int count(int categoryno, String col, String word) {
    int count = 0;
  
    try {
      con = dbopen.getConnection();
  
      sql = new StringBuffer();
      sql.append(" SELECT COUNT(pdsno) as cnt ");
      sql.append(" FROM pds4");
      
      if (col.equals("rname")) {
        sql.append(" WHERE categoryno = ? AND rname LIKE ?");
        sql.append(" ORDER BY pdsno DESC");
        pstmt = con.prepareStatement(sql.toString());
        pstmt.setInt(1, categoryno);
        pstmt.setString(2, "%" + word + "%");
      } else if (col.equals("title")) {
        sql.append(" WHERE categoryno = ? AND  title LIKE ?");
        sql.append(" ORDER BY pdsno DESC");
        pstmt = con.prepareStatement(sql.toString());
        pstmt.setInt(1, categoryno);
        pstmt.setString(2, "%" + word + "%");
      } else if (col.equals("content")) {
        sql.append(" WHERE categoryno = ? AND  content LIKE ?");
        sql.append(" ORDER BY pdsno DESC");
        pstmt = con.prepareStatement(sql.toString());
        pstmt.setInt(1, categoryno);
        pstmt.setString(2, "%" + word + "%");
      } else if (col.equals("title_content")) {
        sql.append(" WHERE categoryno = ? AND (title LIKE ? OR content LIKE ?)");
        sql.append(" ORDER BY pdsno DESC");
        pstmt = con.prepareStatement(sql.toString());
        pstmt.setInt(1, categoryno);
        pstmt.setString(2, "%" + word + "%");
        pstmt.setString(3, "%" + word + "%");
      } else { // �˻����� �ʴ� ���
        sql.append(" WHERE categoryno = ?");
        sql.append(" ORDER BY pdsno DESC");
        pstmt = con.prepareStatement(sql.toString());
        pstmt.setInt(1, categoryno);
      }
  
      rs = pstmt.executeQuery(); // SELECT
  
      if (rs.next() == true) {
        count = rs.getInt("cnt");
      }
  
    } catch (Exception e) {
      e.printStackTrace();
    } finally {
      dbclose.close(con, pstmt, rs);
    }
  
    return count;
  }  

  /**
   * ī�װ����� �˻� �� ����¡�� ������ ���, SELECT�� ����߿�
   * ����¡ ����� �̿��Ͽ� Ư�� ���ڵ� �κ�(10��)�� �����ɴϴ�. 
   * @param categoryno ī�װ� ��ȣ
   * @param col �˻� �÷�
   * @param word �˻���
   * @param offset skip�� ���ڵ� ����
   * @param recordPerPage �������� ����� ���ڵ� ����
   * @return �˻� ���
   */
  public ArrayList<Pds4VO> list_category(
      int categoryno, 
      String col, 
      String word, 
      int offset,
      int recordPerPage) {
    ArrayList<Pds4VO> list = new ArrayList<Pds4VO>();
 
    try {
      con = dbopen.getConnection();
  
      sql = new StringBuffer();
      sql.append(" SELECT pdsno, categoryno, rname, email, title, content, passwd, cnt,");
      sql.append("            SUBSTRING(rdate, 1, 16) as rdate, web, file1, fstor1,");
      sql.append("            thumb, size1, map, youtube, mp3, size2, mp4, size3, ip, visible");
      sql.append(" FROM pds4");
      
      if (col.equals("rname")) {
        sql.append(" WHERE categoryno = ? AND rname LIKE ?");
        sql.append(" ORDER BY pdsno DESC");
        // sql.append(" LIMIT " + offset + ", " + record_per_page);
        sql.append(" LIMIT ?, ?");
        pstmt = con.prepareStatement(sql.toString());
        pstmt.setInt(1, categoryno);
        pstmt.setString(2, "%" + word + "%");
        pstmt.setInt(3, offset);
        pstmt.setInt(4, recordPerPage);
      } else if (col.equals("title")) {
        sql.append(" WHERE categoryno = ? AND  title LIKE ?");
        sql.append(" ORDER BY pdsno DESC");
        sql.append(" LIMIT ?, ?");
        pstmt = con.prepareStatement(sql.toString());
        pstmt.setInt(1, categoryno);
        pstmt.setString(2, "%" + word + "%");
        pstmt.setInt(3, offset);
        pstmt.setInt(4, recordPerPage);
      } else if (col.equals("content")) {
        sql.append(" WHERE categoryno = ? AND  content LIKE ?");
        sql.append(" ORDER BY pdsno DESC");
        sql.append(" LIMIT ?, ?");
        pstmt = con.prepareStatement(sql.toString());
        pstmt.setInt(1, categoryno);
        pstmt.setString(2, "%" + word + "%");
        pstmt.setInt(3, offset);
        pstmt.setInt(4, recordPerPage);
      } else if (col.equals("title_content")) {
        sql.append(" WHERE categoryno = ? AND (title LIKE ? OR content LIKE ?)");
        sql.append(" ORDER BY pdsno DESC");
        sql.append(" LIMIT ?, ?");
        pstmt = con.prepareStatement(sql.toString());
        pstmt.setInt(1, categoryno);
        pstmt.setString(2, "%" + word + "%");
        pstmt.setString(3, "%" + word + "%");
        pstmt.setInt(4, offset);
        pstmt.setInt(5, recordPerPage);
      } else { // �˻����� �ʴ� ���
        sql.append(" WHERE categoryno = ?");
        sql.append(" ORDER BY pdsno DESC");
        sql.append(" LIMIT ?, ?");
        pstmt = con.prepareStatement(sql.toString());
        pstmt.setInt(1, categoryno);
        pstmt.setInt(2, offset);
        pstmt.setInt(3, recordPerPage);
      }
  
      rs = pstmt.executeQuery(); // SELECT
  
      while (rs.next() == true) {
        Pds4VO pds4VO = new Pds4VO();
        pds4VO.setPdsno(rs.getInt("pdsno")); // DBMS -> JAVA ��ü
        pds4VO.setCategoryno(rs.getInt("categoryno"));
        pds4VO.setRname(rs.getString("rname"));
        pds4VO.setEmail(rs.getString("email"));
        pds4VO.setTitle(rs.getString("title"));
        pds4VO.setContent(rs.getString("content"));
        pds4VO.setPasswd(rs.getString("passwd"));
        pds4VO.setCnt(rs.getInt("cnt"));
        pds4VO.setRdate(rs.getString("rdate"));
        pds4VO.setWeb(rs.getString("web"));
        pds4VO.setFile1(rs.getString("file1"));
        pds4VO.setFstor1(rs.getString("fstor1"));
        pds4VO.setThumb(rs.getString("thumb"));
        pds4VO.setSize1(rs.getLong("size1"));
        pds4VO.setMap(rs.getString("map"));
        pds4VO.setYoutube(rs.getString("youtube"));
        pds4VO.setMp3(rs.getString("mp3"));
        pds4VO.setSize2(rs.getLong("size2"));
        // System.out.println("rs.getString(\"mp3\"): " + rs.getString("mp3"));
        // System.out.println("pds4VO.getMp3(\"mp3\"): " + pds4VO.getMp3());
        pds4VO.setMp4(rs.getString("mp4"));
        pds4VO.setSize3(rs.getLong("size3"));
        pds4VO.setIp(rs.getString("ip"));
        pds4VO.setVisible(rs.getString("visible"));
  
        list.add(pds4VO);
      }
  
    } catch (Exception e) {
      e.printStackTrace();
    } finally {
      dbclose.close(con, pstmt, rs);
    }
  
    return list;
  }  

  /**
   * ī�װ��� ���ڵ� ����
   * 
   * @return
   */
  public int countByCategory(int categoryno) {
    int count = 0;
  
    try {
      con = dbopen.getConnection();
  
      sql = new StringBuffer();
      sql.append(" SELECT COUNT(pdsno) as cnt ");
      sql.append(" FROM pds4");
      sql.append(" WHERE categoryno = ?");
      pstmt = con.prepareStatement(sql.toString());
      pstmt.setInt(1, categoryno);
  
      rs = pstmt.executeQuery(); // SELECT
  
      if (rs.next() == true) {
        count = rs.getInt("cnt");
      }
  
    } catch (Exception e) {
      e.printStackTrace();
    } finally {
      dbclose.close(con, pstmt, rs);
    }
  
    return count;
  }   
  
}