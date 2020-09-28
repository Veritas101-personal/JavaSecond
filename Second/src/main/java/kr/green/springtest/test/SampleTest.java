package kr.green.springtest.test;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("file:src/main/webapp/WEB-INF/spring/root-context.xml")
public class SampleTest {

	@Test
	public void testExist() {
		Connection con = null;


		try {
			Class.forName("com.mysql.jdbc.Driver");
			con =
					DriverManager.getConnection("jdbc:mysql://localhost:3306/spring?useUnicode=true&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=UTC","root","cjgreen");
			System.out.println("성공");
			Statement stmt = con.createStatement();
			
		}catch(Exception e) {
			System.out.println("실패");
			System.out.println(e.getMessage());
		}

	}
}
