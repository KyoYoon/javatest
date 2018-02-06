package nation.web.category4;

import java.util.ArrayList;

public class CategoryProc {
  private CategoryDAO categoryDAO;
  
  public CategoryProc() {
    categoryDAO = new CategoryDAO();
  }
  
  /**
   * ī�װ� ���
   * @param categoryVO
   * @return ��ϵ� ���� 1 or 0
   */
  public int insert(CategoryVO categoryVO){
    int count = categoryDAO.insert(categoryVO);
    
    return count;
  } 
  
  /**
   * ��ü ���
   * @return
   */
  public ArrayList<CategoryVO> list() {
    ArrayList<CategoryVO> list = categoryDAO.list();
    
    return list;
  }  
  
  public CategoryVO read(int categoryno){
    CategoryVO categoryVO = categoryDAO.read(categoryno);
    
    return categoryVO;
  }  

  /**
   * Category ����
   * @param CategoryVO
   * @return ����� ����
   */
  public int update(CategoryVO categoryVO){ // call by reference
    int count = 0;                     // ó���� ���ڵ� ����
    
    count = categoryDAO.update(categoryVO);
    
    return count;
  }  

  /**
   * Category ����
   * @param categoryno
   * @return ������ ����
   */
  public int delete(int categoryno){ 
    int count = 0;                     // ó���� ���ڵ� ����
    
    count = categoryDAO.delete(categoryno);
    
    return count;
  } 
  
  public int high(int categoryno){ // call by reference
    int count = 0;                     // ó���� ���ڵ� ����
    
    count = categoryDAO.high(categoryno);
    
    return count;
  }

  public int low(int categoryno){ // call by reference
    int count = 0;                     // ó���� ���ڵ� ����
    
    count = categoryDAO.low(categoryno);
    
    return count;
  }  

  /**
   * �Խ��� ��� ����
   * @param categoryVO
   * @return ������ ����
   */
  public int show_hide(int categoryno, String visible){
    int count = 0;
    if (visible.equals("Y")) {
      count = categoryDAO.hide(categoryno); // N
    } else {
      count = categoryDAO.show(categoryno); // Y
    }
    
    return count;
  }  
  
  /**
   * ������ �ִ밪 ���� 
   * @return
   */
  public int getMaxSeqno() {
    int maxSeqno = 0;
    
    maxSeqno = categoryDAO.getMaxSeqno();
    
    return maxSeqno;
    
  }

  /**
   * ���� �Խ��� �ۼ��� ����
   * @param categoryno
   * @return
   */
  public int increaseCnt(int categoryno) { // call by reference
    int count = 0;                     // ó���� ���ڵ� ����
    
    count = categoryDAO.increaseCnt(categoryno);
    
    return count;
  }
 
  /**
   * ���� �Խ��� �ۼ��� ����
   * @param categoryno
   * @return
   */
  public int decreaseCnt(int categoryno) { // call by reference
    int count = 0;                     // ó���� ���ڵ� ����
    
    count = categoryDAO.decreaseCnt(categoryno);
    
    return count;
  }
    
  
}