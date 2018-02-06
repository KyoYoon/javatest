1. url2 ���̺� ���� 
- URL ������ ���� Ű��
1: JAVA
2: JSP
3: HTML5
4: CSS
5: Javascript
6. MySQL
 
 
1) url ���̺��� ����
   - ����� �����
   - PRIMARY KEY(urlno): urlno�÷��� ���� 2���̻� ��ϵǴ� ���� ����.
   
   CREATE TABLE url2( 
     url2no  INT                NOT NULL AUTO_INCREMENT, -- URL ��ȣ
     code   INT                 NOT NULL, -- ���� �ڵ�
     title    VARCHAR(100) NOT NULL, -- �� ����Ʈ ����
     web    VARCHAR(200) NOT NULL, -- URL �ּ�
     rdate  DATETIME        NOT NULL, -- �����
     PRIMARY KEY(url2no)
   );  
 
 
2) ���̺� ����
   DROP TABLE url2;
 
  
3) ���(Primary Key�� ����� �÷��� ������ ��.)
   INSERT INTO url2(code, title, web, rdate)
   VALUES(1, 'Google', 'http://www.google.co.kr', now());
 
   INSERT INTO url2(code, title, web, rdate)
   VALUES(1, 'Oracle', 'http://www.oracle.com', now());
   
   INSERT INTO url2(code, title, web, rdate)
   VALUES(3, 'W3Schools', 'https://www.w3schools.com', now());
   
   INSERT INTO url2(code, title, web, rdate)
   VALUES(4, 'CSS', 'https://www.w3schools.com', now());
   
   INSERT INTO url2(code, title, web, rdate)
   VALUES(6, 'MySQL', 'https://www.mysql.com', now());
   
4) ���
   SELECT url2no, code, title, web, rdate
   FROM url2 
   ORDER BY code ASC;
 
 url2no code title     web                       rdate
 ------ ---- --------- ------------------------- ---------------------
      1    1 Google    http://www.google.co.kr   2017-09-13 10:54:40.0
      2    1 Oracle    http://www.oracle.com     2017-09-13 10:55:20.0
      3    3 W3Schools https://www.w3schools.com 2017-09-13 10:56:39.0
      4    4 CSS       https://www.w3schools.com 2017-09-13 10:57:30.0
      5    6 MySQL     https://www.mysql.com     2017-09-13 10:57:57.0
 
5) ���������� ���
   INSERT INTO url2(code, title, web, rdate)
   VALUES(100, 'Daum', 'http://www.daum.net', now());
 
   INSERT INTO url2(code, title, web, rdate)
   VALUES(150, 'KMA', 'http://www.kma.go.kr', now());
   
   INSERT INTO url2(code, title, web, rdate)
   VALUES(2000000, 'KMA', 'http://www.daum.net', now());  
 
   SELECT url2no, code, title, web, SUBSTRING(rdate, 1, 16) as rdate
   FROM url2 
   ORDER BY code ASC;
 
 url2no code title     web                       rdate
 ------ ---- --------- ------------------------- ----------------
      1    1 Google    http://www.google.co.kr   2017-09-13 10:54
      2    1 Oracle    http://www.oracle.com     2017-09-13 10:55
      3    3 W3Schools https://www.w3schools.com 2017-09-13 10:56
      4    4 CSS       https://www.w3schools.com 2017-09-13 10:57
      5    6 MySQL     https://www.mysql.com     2017-09-13 10:57
      6  100 Daum      http://www.daum.net       2017-09-13 11:03
      7  150 KMA       http://www.kma.go.kr      2017-09-13 11:04
 
6) ������
 
�� DBA�� SQL�󿡼� �ڹ� �����ڰ� ������ 6���� �ڵ带 ������ ���� ��� ������
    ������ �߻���.
- URL ������ ���� Ű��
1: JAVA
2: JSP
3: HTML5
4: CSS
5: Javascript
6. MySQL
100, 150 ???
 
   INSERT INTO url2(code, title, web, rdate)
   VALUES(2000000, 'Daum', 'http://www.daum.net', now());
 
   SELECT url2no, code, title, web, SUBSTRING(rdate, 1, 16) as rdate
   FROM url2 
   ORDER BY code ASC;  
 
    url2no code    title     web                       rdate
 ------ ------- --------- ------------------------- ----------------
      1       1 Google    http://www.google.co.kr   2017-09-13 10:54
      2       1 Oracle    http://www.oracle.com     2017-09-13 10:55
      3       3 W3Schools https://www.w3schools.com 2017-09-13 10:56
      4       4 CSS       https://www.w3schools.com 2017-09-13 10:57
      5       6 MySQL     https://www.mysql.com     2017-09-13 10:57
      6     100 Daum      http://www.daum.net       2017-09-13 11:03
      7     150 KMA       http://www.kma.go.kr      2017-09-13 11:04
      8 2000000 Daum      http://www.daum.net       2017-09-13 11:43
      
   INSERT INTO url2(code, title, web, rdate)
   VALUES(2017, 'Daum', 'http://www.daum.net', now());
 
   SELECT url2no, code, title, web, SUBSTRING(rdate, 1, 16) as rdate
   FROM url2 
   ORDER BY code ASC;  
 
    url2no code    title     web                       rdate
 ------ ------- --------- ------------------------- ----------------
      1       1 Google    http://www.google.co.kr   2017-09-13 10:54
      2       1 Oracle    http://www.oracle.com     2017-09-13 10:55
      3       3 W3Schools https://www.w3schools.com 2017-09-13 10:56
      4       4 CSS       https://www.w3schools.com 2017-09-13 10:57
      5       6 MySQL     https://www.mysql.com     2017-09-13 10:57
      6     100 Daum      http://www.daum.net       2017-09-13 11:03
      7     150 KMA       http://www.kma.go.kr      2017-09-13 11:04
      9    2017 Daum      http://www.daum.net       2017-09-13 11:45
      8 2000000 Daum      http://www.daum.net       2017-09-13 11:43
      
   INSERT INTO url2(code, title, web, rdate)
   VALUES(123456789, 'Daum', 'http://www.daum.net', now());
 
   SELECT url2no, code, title, web, SUBSTRING(rdate, 1, 16) as rdate
   FROM url2 
   ORDER BY code ASC;  
 
    url2no code      title     web                       rdate
 ------ --------- --------- ------------------------- ----------------
      1         1 Google    http://www.google.co.kr   2017-09-13 10:54
      2         1 Oracle    http://www.oracle.com     2017-09-13 10:55
      3         3 W3Schools https://www.w3schools.com 2017-09-13 10:56
      4         4 CSS       https://www.w3schools.com 2017-09-13 10:57
      5         6 MySQL     https://www.mysql.com     2017-09-13 10:57
      6       100 Daum      http://www.daum.net       2017-09-13 11:03
      7       150 KMA       http://www.kma.go.kr      2017-09-13 11:04
      9      2017 Daum      http://www.daum.net       2017-09-13 11:45
      8   2000000 Daum      http://www.daum.net       2017-09-13 11:43
     10 123456789 Daum      http://www.daum.net       2017-09-13 11:47
 
�� JSP������1 ~ 6���� �����Ͽ� ��½� 1~6�� �ƴϸ� ���ڵ� ������.
�� DBA�� SQL ��Ͻ� code ��ȣ�� ���� �������� �� �� ����. 
 
7) �θ����̺�, �ڽ� ���̺���� ����ȭ
  DELETE FROM url2cate;
  DROP TABLE url2cate;

   CREATE TABLE url2cate( 
     url2cateno  INT          NOT NULL AUTO_INCREMENT, -- URL ��ȣ
     title    VARCHAR(100) NOT NULL, -- �� ����Ʈ ����
     rdate  DATETIME        NOT NULL, -- �����
     PRIMARY KEY(url2cateno)
   );
   
   INSERT INTO url2cate(title, rdate) VALUES('JAVA', now());
   INSERT INTO url2cate(title, rdate) VALUES('JSP', now());
   INSERT INTO url2cate(title, rdate) VALUES('HTML5', now());
   INSERT INTO url2cate(title, rdate) VALUES('CSS', now());
   INSERT INTO url2cate(title, rdate) VALUES('Javascript', now());
   INSERT INTO url2cate(title, rdate) VALUES('MySQL', now());
   
   SELECT url2cateno, title, SUBSTRING(rdate, 1, 16) as rdate
   FROM url2cate
   ORDER BY url2cateno ASC;
   
 url2cateno title      rdate
 ---------- ---------- ----------------
          1 JAVA       2017-09-13 11:56
          2 JSP        2017-09-13 11:56
          3 HTML5      2017-09-13 11:56
          4 CSS        2017-09-13 11:56
          5 Javascript 2017-09-13 11:56
          6 MySQL      2017-09-13 11:56
 
8) �ڽ� ���̺��� ����
   DROP TABLE url2;
   
   CREATE TABLE url2( 
     url2no  INT                NOT NULL AUTO_INCREMENT, -- URL ��ȣ
     url2cateno  INT          NOT NULL, -- Category ��ȣ, ���� �ڵ�
     title    VARCHAR(100) NOT NULL, -- �� ����Ʈ ����
     web    VARCHAR(200) NOT NULL, -- URL �ּ�
     rdate  DATETIME        NOT NULL, -- �����
     PRIMARY KEY(url2no),
     FOREIGN KEY(url2cateno) REFERENCES url2cate(url2cateno)
   ) COMMENT='URL ������';  
 
   INSERT INTO url2(url2cateno, title, web, rdate)
   VALUES(1, 'Google', 'http://www.google.co.kr', now());
   
   INSERT INTO url2(url2cateno, title, web, rdate)
   VALUES(1, 'Oracle', 'http://www.oracle.com', now());
   
   INSERT INTO url2(url2cateno, title, web, rdate)
   VALUES(2, 'Eclipse', 'http://www.eclipse.org', now());
   
   SELECT url2no, url2cateno, title, web, SUBSTRING(rdate, 1, 16) as rdate
   FROM url2 
   ORDER BY url2cateno ASC;
   
 url2no url2cateno title   web                     rdate
 ------ ---------- ------- ----------------------- ----------------
      1          1 Google  http://www.google.co.kr 2017-09-13 12:10
      2          1 Oracle  http://www.oracle.com   2017-09-13 12:11
      3          2 Eclipse http://www.eclipse.org  2017-09-13 12:12
 
9) ��ϵ��� ���� url �ڵ��� ���
   INSERT INTO url2(url2cateno, title, web, rdate)
   VALUES(1000, 'Google', 'http://www.google.co.kr', now());
 
   INSERT INTO url2(url2cateno, title, web, rdate)
   VALUES(2017, 'Google', 'http://www.google.co.kr', now());
   
   Cannot add or update a child row: a foreign key constraint fails (`javadb`.`url2`, CONSTRAINT `url2_ibfk_1` FOREIGN KEY (`url2cateno`) REFERENCES `url2cate` (`url2cateno`))
   ��ϵ��� ���� �ڵ� ������� �߻��� ����.
   
   ���� ����Ϸ��� �ڵ尡 ������ ���� �θ� ���̺�(url2cate)�� �ڵ带 ����ϰ�
   �ڽ� ���̺�(url2)�� �����͸� ����մϴ�. 
   
   DBA�� ������ �ڵ�ܿ��� ��� �Ұ����մϴ�.
   JSP �����ڴ� �ڵ带 �ٷ�� JSP applicaton�� �߰� �����ؾ��մϴ�.
 
10) �θ� ���̺��� ���ڵ� ����
  DELETE FROM url2cate WHERE url2cateno = 1;
  Cannot delete or update a parent row: a foreign key constraint fails (`javadb`.`url2`, CONSTRAINT `url2_ibfk_1` FOREIGN KEY (`url2cateno`) REFERENCES `url2cate` (`url2cateno`))
 
�θ����̺��� �ڵ尡 ���̰� ������ ������ �ȵ����� ���̴� ���� ���� ã�� �����մϴ�.
�̷� ������ �ǹ������� ��κ� ���� ó���� ���� ������ ��ġ���� �մϴ�.
 
�� FK �ڽ� ���̺� ����
   DELETE FROM url2 WHERE url2cateno = 1;
 
�� PK �θ� ���̺� ����
   DELETE FROM url2cate WHERE url2cateno=1;
 
�� PK �θ� ���̺� ���
   SELECT url2cateno, title, SUBSTRING(rdate, 1, 16) as rdate
   FROM url2cate
   ORDER BY url2cateno ASC;
   
 url2cateno title      rdate
 ---------- ---------- ----------------
          2 JSP        2017-09-13 11:56
          3 HTML5      2017-09-13 11:56
          4 CSS        2017-09-13 11:56
          5 Javascript 2017-09-13 11:56
          6 MySQL      2017-09-13 11:56
          
11) JAVA ���� URL�� ���� ���� ���
   SELECT url2no, url2cateno, title, web, SUBSTRING(rdate, 1, 16) as rdate
   FROM url2 
   WHERE url2cateno = 1
   ORDER BY url2cateno ASC;
 
12) JSP ���� URL�� ���� ���� ���
   SELECT url2no, url2cateno, title, web, SUBSTRING(rdate, 1, 16) as rdate
   FROM url2 
   WHERE url2cateno = 2
   ORDER BY url2cateno ASC;
 
13) CSS ���� URL�� ���� ���� ���
   SELECT url2no, url2cateno, title, web, SUBSTRING(rdate, 1, 16) as rdate
   FROM url2 
   WHERE url2cateno = 3
   ORDER BY url2cateno ASC;   