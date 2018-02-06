package nation.web.pds4;

import java.util.ArrayList;
import nation.web.tool.*;

public class Pds4Proc {
  private Pds4DAO pds4DAO;
  
  public Pds4Proc() {
    pds4DAO = new Pds4DAO();
  }
  
  /**
   * ���
   * @param pds4VO
   * @return ����� ����
   */
  public int insert(Pds4VO pds4VO){ // call by reference
    int count = 0;                     // ó���� ���ڵ� ����
    count = pds4DAO.insert(pds4VO);
    
    return count;
  }

  /**
   * ��� ���ڵ�
   * 
   * @return
   */
  public ArrayList<Pds4VO> list() {
    ArrayList<Pds4VO> list = null;
    
    list = pds4DAO.list();
    
    return list;
    
  }   

  /**
   * ī�װ��� ���ڵ�
   * 
   * @return
   */
  public ArrayList<Pds4VO> list_category(int categoryno) {
    ArrayList<Pds4VO> list = null;
    
    list = pds4DAO.list_category(categoryno);
    
    return list;
    
  }  

  /**
   * ��ȸ
   * 
   * @return
   */
  public Pds4VO read(int pdsno){
    Pds4VO pds4VO = null;
    
    pds4DAO.increaseCnt(pdsno);  // ��ȸ�� ����
    pds4VO = pds4DAO.read(pdsno);
    
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
    
    count = pds4DAO.passwordCheck(pdsno, passwd);
    
    return count;
  }  

  /**
   * �� ����
   * @param pds4VO
   * @return ����� ����
   */
  public int update(Pds4VO pds4VO){ // call by reference
    int count = 0;                     // ó���� ���ڵ� ����
    
    count = pds4DAO.update(pds4VO);
    
    return count;
  }  

  /**
   * Visible ��� ����
   * @param pds4VO
   * @return ����� ����
   */
  public int show_hide(int pdsno, String visible){ // call by reference
    int count = 0;                     // ó���� ���ڵ� ����
    
    if (visible.equals("Y")) {
      pds4DAO.show_hide(pdsno, "N");
    } else {
      pds4DAO.show_hide(pdsno, "Y");
    }
    return count;
    
  }  

  /**
   * ���ڵ庰�� ���� ���� ����
   * @param pdsno
   * @return
   */
  public int delete_file1(int pdsno) {
    int count = 0;                     // ó���� ���ڵ� ����
    
    Pds4VO pds4VO = new Pds4VO();
    pds4VO.setFile1("");
    pds4VO.setFstor1("");
    pds4VO.setThumb("");
    pds4VO.setSize1(0);
    pds4VO.setPdsno(pdsno);
    
    count = pds4DAO.update_file1(pds4VO);
    
    return count;
  }  

  /**
   * ���� �ű� ���
   * @param pdsno
   * @return
   */
  public int create_file1(Pds4VO pds4VO) {
    int count = 0;                     // ó���� ���ڵ� ����
    
    count = pds4DAO.update_file1(pds4VO);
    
    return count;
  }
  
  /**
   * �� ����
   * @param 
   * @return ó���� ����
   */
  public int delete(int pdsno){ 
    int count = 0;                     // ó���� ���ڵ� ����
    
    count = pds4DAO.delete(pdsno);
    
    return count;
  }  

  /**
   * Map ����
   * @param pdsno
   * @return
   */
  public int create_map(Pds4VO pds4VO) {
    int count = 0;                     // ó���� ���ڵ� ����
    
    count = pds4DAO.update_map(pds4VO);
    
    return count;
    
  }  

  /**
   * Map ����
   * @param pdsno
   * @return
   */
  public int replace_map(Pds4VO pds4VO) {
    int count = 0;                     // ó���� ���ڵ� ����
    
    count = pds4DAO.update_map(pds4VO);
    
    return count;
    
  }  

  /**
   * Map ����
   * @param pdsno
   * @return
   */
  public int delete_map(int pdsno) {
    int count = 0;                     // ó���� ���ڵ� ����
    
    Pds4VO pds4VO = new Pds4VO();
    pds4VO.setPdsno(pdsno);
    pds4VO.setMap(""); // ����
    
    count = pds4DAO.update_map(pds4VO);
    
    return count;
 
  }  

  /**
   * youtube ��� 
   * @param pdsno
   * @return
   */
  public int create_youtube(Pds4VO pds4VO) {
    int count = 0;                     // ó���� ���ڵ� ����
    
    count = pds4DAO.update_youtube(pds4VO);
    
    return count;
    
  }

  /**
   * youtube ����
   * @param pds4VO
   * @return
   */
  public int replace_youtube(Pds4VO pds4VO) {
    int count = 0;                     // ó���� ���ڵ� ����
    
    count = pds4DAO.update_youtube(pds4VO);
    
    return count;
    
  }
  
  /**
   * youtube ����
   * @param pdsno
   * @return
   */
  public int delete_youtube(int pdsno) {
    int count = 0;                     // ó���� ���ڵ� ����
    
    Pds4VO pds4VO = new Pds4VO();
    pds4VO.setPdsno(pdsno);
    pds4VO.setYoutube(""); // ����
    
    count = pds4DAO.update_youtube(pds4VO);
    
    return count;
 
  }  

  /**
   * �ű� MP3 ���
   * @param pdsno
   * @return
   */
  public int create_mp3(Pds4VO pds4VO) {
    int count = 0;                     // ó���� ���ڵ� ����
    
    count = pds4DAO.update_mp3(pds4VO);
    
    return count;
  }  

  /**
   * �ű� MP4 ���
   * @param pdsno
   * @return
   */
  public int create_mp4(Pds4VO pds4VO) {
    int count = 0;                     // ó���� ���ڵ� ����
    
    count = pds4DAO.update_mp4(pds4VO);
    
    return count;
  }  

  /**
   * �˻��� ��� ���ڵ�
   * 
   * @return
   */
  public ArrayList<Pds4VO> list(String col, String word) {
    ArrayList<Pds4VO> list = null;
    
    list = pds4DAO.list(col, word);
    
    for (int index=0; index < list.size(); index++) {
      // System.out.println("list.get(index).getMp3(): " + list.get(index).getMp3());
      Pds4VO pds4VO = list.get(index);
      
      String title = Tool.textLength(pds4VO.getTitle(), 10);
      pds4VO.setTitle(title);
    }  
    
    return list;
    
  }
 
  /**
   * ī�װ����� �˻��� ���ڵ�
   * 
   * @return
   */
  public ArrayList<Pds4VO> list_category(int categoryno, String col, String word) {
 
    ArrayList<Pds4VO> list = null;
    
    list = pds4DAO.list_category(categoryno, col, word);
    
    for (int index=0; index < list.size(); index++) {
      // System.out.println("list.get(index).getMp3(): " + list.get(index).getMp3());
      Pds4VO pds4VO = list.get(index);
      
      String title = Tool.textLength(pds4VO.getTitle(), 10);
      pds4VO.setTitle(title);
    }  
    
    return list;
    
  }  

  /**
   * ī�װ����� �˻��� ���ڵ� �Ǽ� 
   * 
   * @param categoryno ī�װ���ȣ 
   * @param col �˻��з� 
   * @param word �˻��� 
   * @return count
   */
  public int count(int categoryno, String col, String word) {
    int count = 0;
    
    count = pds4DAO.count(categoryno, col, word);
    
    return count;
  }  

  /**
   * ī�װ����� �˻� �� ����¡�� ������ ���, SELECT�� ����߿�
   * ����¡ ����� �̿��Ͽ� Ư�� ���ڵ� �κ�(10��)�� �����ɴϴ�. 
   *  1 ������: (nowPage 1 - 1) *  10 �� 0 ~ 9 
   *  2 ������: (nowPage 2 - 1) *  10 �� 10 ~ 19 
   *  3 ������: (nowPage 3 - 1) *  10 �� 20 ~ 29 
   *  4 ������: (nowPage 4 - 1) *  10 �� 30 ~ 39 
   *  5 ������: (nowPage 5 - 1) *  10 �� 40 ~ 49  
   * @param categoryno ī�װ� ��ȣ
   * @param col �˻� �÷�
   * @param word �˻���
   * @param nowPage ����������
   * @param offset skip�� ���ڵ� ����
   * @param recordPerPage �������� ����� ���ڵ� ����
   * @return ArrayList<Pds4VO> �˻� ���
   */
  public ArrayList<Pds4VO> list_category(
      int categoryno, 
      String col, 
      String word, 
      int nowPage,
      int recordPerPage) {
    ArrayList<Pds4VO> list = new ArrayList<Pds4VO>();
 
    col = Tool.checkNull(col);
    word = Tool.checkNull(word);
    int offset  = (nowPage - 1) * recordPerPage; // skip�� ���ڵ� ��
    
    list = pds4DAO.list_category(categoryno, col, word, offset, recordPerPage);
    
    return list;
  }  

  /**
   * ī�װ��� ���ڵ� ����
   * 
   * @return
   */
  public int countByCategory(int categoryno) {
    int count = 0;
    
    count = pds4DAO.countByCategory(categoryno);
    
    return count;
  }  
  
}