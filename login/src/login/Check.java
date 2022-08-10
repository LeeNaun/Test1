package login;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import javax.management.loading.PrivateClassLoader;

import login2.User;

public class Check {
	
	private Connection conn() throws Exception {
		String url="jdbc:oracle:thin:@192.168.0.11:1521:xe";	
		Class.forName("oracle.jdbc.driver.OracleDriver");		//1. 데이터베이스 드라이버를 로딩한다.
		return DriverManager.getConnection(url, "scott", "tiger"); //2. 연결하여 커넥션 객체를 생성한다.
	}
	
	private void connClose(ResultSet rs,PreparedStatement stmt,Connection con) {
		try { if(rs!=null)  rs.close(); 	}  catch (Exception e) {}
		try	{ if(stmt!=null) stmt.close();  }  catch (Exception e) {}
		try	{ if(con!=null) con.close();	}  catch (Exception e) {}
	}
	
	public int login(String id, String pw){
		//DB에 접속 후 id와 pw를 가지고 확인해서 
			
		//String url="jdbc:oracle:thin:@localhost:1521:xe";	
		int flag=0;
		ResultSet rs=null;
		PreparedStatement stmt=null;
		Connection con=null;
		
		
		try {
//			Class.forName("oracle.jdbc.driver.OracleDriver");		//1. 데이터베이스 드라이버를 로딩한다.
			con=conn();  //DriverManager.getConnection(url, "scott", "tiger"); //2. 연결하여 커넥션 객체를 생성한다.
//			System.out.println("***********");
//			System.out.println("DB접속 성공");
//			System.out.println("***********");
			stmt=con.prepareStatement("select * from tbl_user where id=?"); //3.생성된 커넥션 개체를 가지고 Statement 객체를 생성한다.
			//4. Statement 객체를 가지고 sql문 작업을 진행한다.
			
			//삽입, 삭제, 갱신 시에는 .executeUpate() 리턴이 정수(정수가 의미하는 몇 개가 처리되었는지)
			stmt.setString(1,id);
			
			rs=stmt.executeQuery();	
			//sql문(select)실행하기
			if(rs.next()) { //한 행이 있다면(즉, 아이디가 있다면)
				String dbPw=rs.getString("pw"); //db의 결과값 중 컬럼이 pw인 값을 읽는다.
				
				
				if(pw.equals(dbPw)) {
					flag=3;
				}else
					flag=2;
				
			}else {
				flag=1;
			}
			
//			if(flag==1)
//				System.out.println("아이디가 틀렸다.");
//			if(flag==2)
//				System.out.println("패스워드가 틀렸다.");
//			if(flag==3)
//				System.out.println("로그인 성공");
//			if(flag==0)
//				System.out.println("DB접속 오류");
			
		} catch (Exception e) {
			System.out.println("예외발생");
			e.printStackTrace();
		} finally { //무조건 실행이 되어야 하는 부분
			connClose(rs,stmt,con);
		
		}
		
	
		//1~3까지 값을 return 주면 된다.
		return flag;
	}
	//추가
	 List<User> list()
	  {
	    ResultSet rs = null;
	    PreparedStatement stmt = null;
	    Connection con = null;
	    List list = new ArrayList();
	    try
	    {
	      con = conn();
	      stmt = con.prepareStatement("select * from tbl_user");
	      rs = stmt.executeQuery();
	      while (rs.next()) {
	        User u = new User();
	        u.id = rs.getString(1);
	        u.pw = rs.getString(2);
	        list.add(u);
	      }
	    } catch (Exception ex) {
	      ex.printStackTrace();
	      System.out.println("음..");
	    } finally {
	      connClose(rs, stmt, con);
	    }

	    return list;
	  }
//추가
	 
	
	void signUp(String id, String pw) {
		//DB에 접속 후 id와 pw를 가지고 확인해서 
				
				
				PreparedStatement stmt=null;
				Connection con=null;
				
				
				try {
					
					con=conn();
//					System.out.println("***********");
//					System.out.println("DB접속 성공");
//					System.out.println("***********");
//					stmt=con.prepareStatement("insert into tbl_user(id,pw) values (?,?)"); //3.생성된 커넥션 개체를 가지고 Statement 객체를 생성한다.
					//4. Statement 객체를 가지고 sql문 작업을 진행한다.
					
					//삽입, 삭제, 갱신 시에는 .executeUpate() 리턴이 정수(정수가 의미하는 몇 개가 처리되었는지)
					stmt.setString(1,id);
					stmt.setString(2,pw);
					stmt.executeUpdate();	//sql문(select)실행하기
					
				} catch (Exception e) {
					System.out.println("예외발생");
					e.printStackTrace();
				} finally { //무조건 실행이 되어야 하는 부분
					connClose(null,stmt,con);
						
				}
				
			
				//1~3까지 값을 return 주면 된다.
				
		
	}
	
	int delete(String id, String pw) {
		//DB에 접속 후 id와 pw를 가지고 확인해서 
				
				
				PreparedStatement stmt=null;
				Connection con=null;
				int count=0;
				
				try {
					
					con=conn();
//					System.out.println("***********");
//					System.out.println("DB접속 성공");
//					System.out.println("***********");
					stmt=con.prepareStatement("delete tbl_user where id=? and pw=?"); //3.생성된 커넥션 개체를 가지고 Statement 객체를 생성한다.
//					stmt2=con.prepareStatement("delete tbl_user where pw=?");
					//4. Statement 객체를 가지고 sql문 작업을 진행한다.
					
					//삽입, 삭제, 갱신 시에는 .executeUpate() 리턴이 정수(정수가 의미하는 몇 개가 처리되었는지)
					stmt.setString(1,id);
					stmt.setString(2,pw);	//sql문(select)실행하기
					count=stmt.executeUpdate(); //count 몇개가 삭제되었는지
					
				} catch (Exception e) {
					System.out.println("예외발생");
					e.printStackTrace();
				} finally { //무조건 실행이 되어야 하는 부분
					connClose(null,stmt,con);
						
				}
				return count;
				
		
	}


}
