package oeg.lq.test;

import java.sql.SQLException;

import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.mail.EmailException;
import org.junit.Test;
import org.lq.bean.User;
import org.lq.util.DBManager;
import org.lq.util.EmailUtil;

public class EmailUtilTest {
	@Test
	public void testSend() throws EmailException {
		String html = "<html><head></head><body><h1><a href='http://www.163.com'>163 address</a></h1></body></html>";
		
		EmailUtil.send(html, "1933840036@qq.com");
	
	}
	
}	
