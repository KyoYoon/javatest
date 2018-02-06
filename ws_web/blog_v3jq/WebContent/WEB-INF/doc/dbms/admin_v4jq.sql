1. ���̺� ����
1) ���� ���Խ� Master�� ����, ���ĺ��� ������� ���� �����ϸ�
   Master������ �������ؾ� �α��� ����.
 
2) act: ��Ȱ�� ���� 
   M: ������, Y: �α��� ����, N: �α��� �Ұ�, H: ���� ���, G: �մ�

DROP TABLE admin4; 
 
CREATE TABLE admin4 (
  admin4no INT NOT NULL AUTO_INCREMENT, -- ������ ��ȣ, ���ڵ带 �����ϴ� �÷� 
  email       VARCHAR(50)  UNIQUE NOT NULL, -- �ѹ� ��ϵ� email�� �ߺ� �ȵ�   
  passwd    VARCHAR(20)  NOT NULL, -- �н�����, ������ ���� 
  auth         VARCHAR(20) NOT NULL, -- ����Ű, ABC + ����ð� 13��
  act           CHAR(1)         DEFAULT 'N' NOT NULL, -- M: ������, Y: �α��� ����, N: �α��� �Ұ�, H: ���� ���, G: �մ�
  confirm    CHAR(1)         DEFAULT 'N' NOT NULL, -- �̸��� ��ũ Ŭ�� ����, Y:Ŭ��, N:Ŭ������
  mdate      DATETIME       NOT NULL, -- ������
  PRIMARY KEY (admin4no)                
); 
 
 
�� ���� ���� 
- NOT NULL: INSERT SQL���� �÷��� �� �ʼ� �Է�. 
- UNIQUE  : �÷��� �ߺ��� ���� �� �� ����. 
- PRIMARY KEY (id): �⺻������ UNIQUE�� ������ �����ϸ鼭 
  ���ڵ带 �����ϴ� ���� �÷��� ��. 
 
 
 
2. INSERT, ���� ������ �߰� 
- Duplicate entry 'test1@mail.com' for key 'email': �̸��� �ߺ� ��� �Ұ���
 
1) �ߺ� �̸��� �˻� ���� SQL 
    -- 0: �ߺ� �ƴ�, 1: �ߺ�  
    SELECT COUNT(email) as cnt
    FROM admin4
    WHERE email = 'test1@mail.com';
 
    cnt
    ---
     1
 
2) MASTER ������ ��ȸ
    SELECT COUNT(admin4no) as cnt
    FROM admin4
    WHERE act = 'M';
 
    cnt
    ---
    0
 
3) ������ ���
   -- Master ����
   INSERT INTO admin4(email, passwd, auth, act, confirm, mdate)   
   VALUES('test1@gmail.com', '1234', 'AXD0123456789012', 'M', 'Y', now());
 
   INSERT INTO admin4(email, passwd, auth, act, confirm, mdate)   
   VALUES('test2@gmail.com', '1234', 'AXD0123456789012', 'N', 'N', now());
   
   INSERT INTO admin4(email, passwd, auth, act, confirm, mdate)   
   VALUES('test3@gmail.com', '1234', 'AXD0123456789012', 'N', 'N', now());
 
4) �̸��� ����
  UPDATE admin4
  SET confirm = 'Y'
  WHERE email='test1@gmail.com' AND auth='AXD0123456789012';
 
    
3. ���
SELECT * FROM admin4;
 
1) ȸ�� ��ü ��� 
   SELECT admin4no, email, passwd, auth, act, confirm, mdate
   FROM admin4
   ORDER BY email ASC;
 
 admin4no email                  passwd auth             act confirm mdate
 -------- ---------------------- ------ ---------------- --- ------- ---------------------
        4 testcell2012@gmail.com 1234   ECB1461122430077 M   Y       2016-04-20 12:20:30.0
        5 testcell2013@gmail.com 1234   YXT1461122564047 H   N       2016-04-20 12:22:44.0
 
        
2) ������ ����(admin4DAO: int updateAct(int admin4no, String act))
UPDATE admin4 
SET  act='Y'
WHERE admin4no=1;
 
 
4. ȸ�� ���� ���� 
   SELECT admin4no, email, passwd, auth, act, confirm, mdate
   FROM admin4
   WHERE admin4no = 1;
   
 
5. �н����� ����
1) ���� �н����� �˻�
- DAO: public int countPasswd(int admin4no, String passwd){ ... }
SELECT count(*) as cnt
FROM admin4
WHERE admin4no = 1 AND passwd='123';
 
2) �н����� ����
- DAO: public int updatePasswd(int admin4no, String passwd){ ... }
UPDATE admin4
SET passwd=''
WHERE admin4no=1;
 
 
6.  ȸ�� ���� ���� 
 
UPDATE admin4
SET email=''
WHERE admin4no = 1;
 
 
7. 'admin4' ȸ�� ���� 
DELETE FROM admin4;
 
DELETE FROM admin4
WHERE admin4no = 6;
 
 
8. �α��� ���� SQL 
- DAO: public int login(String email, String passwd){ ... }
 
- �α��� üũ
SELECT count(*) as cnt
FROM admin4
WHERE email = 'test1@gmail.com' AND passwd='1234';
 
- Email ������ �̿��� ��ȸ
SELECT admin4no, email, passwd, auth, act, confirm, mdate
FROM admin4
WHERE email = 'test1@gmail.com';
 
     
9. �˻��� ��ü ���ڵ� ��
   - LIKE    : ��Ȯ�ϰ� ��ġ���� �ʾƵ� ��� 
   - =(equal): ��Ȯ�ϰ� ��ġ�ؾ� ��� 
   - �˻��� ���� �ʴ� ���, ��ü ��� ��� 
 
      
10. �˻� ���(S:Search List)  
 
 
11. ����¡
   - ����� ����¡ ������ �ʼ��� �մϴ�.