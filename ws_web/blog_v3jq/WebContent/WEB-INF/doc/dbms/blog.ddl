DROP TABLE IF EXISTS category;

/**********************************/
/* Table Name: ī�װ� */
/**********************************/
CREATE TABLE category(
		categoryno                    		INT(10)		 NOT NULL		 PRIMARY KEY AUTO_INCREMENT COMMENT 'ī�װ���ȣ',
		title                         		VARCHAR(30)		 NOT NULL COMMENT '�Խ��� ����',
		seqno                         		INT(10)		 DEFAULT 0		 NOT NULL COMMENT '��� ����',
		visible                       		CHAR(1)		 DEFAULT 'Y'		 NOT NULL COMMENT '��� ����',
		ids                           		VARCHAR(200)		 DEFAULT 'admin'		 NOT NULL COMMENT '���� ����',
		cnt                           		INT(10)		 DEFAULT 0		 NOT NULL COMMENT '��ϵ� �ڷ��'
) COMMENT='ī�װ�';

