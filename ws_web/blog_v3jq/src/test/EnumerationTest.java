package test;
 
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.Iterator;  // Interface
import java.util.Vector;
 
public class EnumerationTest {
 
  public static void main(String[] args) {
 
    ArrayList<String> list = new ArrayList<String>();
    list.add("���׶�");
    list.add("��Ÿ����");
    list.add("�غ����");
    
    Iterator<String> iter = list.iterator(); // Iterator ��ü ����
    
    while(iter.hasNext()){ // ���� ��Ұ� �ִ��� üũ
      System.out.println(iter.next()); // ���� ��� ����
    }
    
    System.out.println("--------------------");
 
    Vector<String> list2 = new Vector<String>();
    list2.add("���׶�");
    list2.add("��Ÿ����");
    list2.add("�غ����");
 
    Enumeration<String> enumeration = list2.elements();
    
    while(enumeration.hasMoreElements()){  // ���� ��Ұ� �ִ��� üũ
      System.out.println(enumeration.nextElement());  // ���� ��� ����
    }
  }
 
}