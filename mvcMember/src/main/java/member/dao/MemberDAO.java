package member.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

import member.bean.MemberDTO;

// DAO - Data Access Object
public class MemberDAO {
	private Connection conn;
	private PreparedStatement pstmt;
	private ResultSet rs;
	
	private static MemberDAO instance; // 싱글톤
	// static으로 잡으면 메모리에는 딱 한번밖에 잡히지 않는다.
	
	private DataSource ds;
	public static MemberDAO getInstance() {   // 01/06 1057
		synchronized (MemberDAO.class) {
			if (instance == null) {
				// 생성됐을때 딱 한번만 null 값이다.
				instance = new MemberDAO();
			}
		}
		return instance;
	}
	public MemberDAO() {
		try {
			Context context = new InitialContext();
			// Tomcat의 경우 'java:comp/env/' 를 꼭 써줘야 한다.
			ds = (DataSource)context.lookup("java:comp/env/jdbc/oracle"); // 자식 = 부모라서 캐스팅 해줘야 한다
			
		} catch (NamingException e) {
			e.printStackTrace();
		}
	}
	
	public String login(String id, String pwd) {
		String name = null;
		String sql = "select * from member where id = ? and pwd = ?";
		
		
		try {
			// 접속
			conn = ds.getConnection();  // 호출
			
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, id);
			pstmt.setString(2, pwd);
			
			rs = pstmt.executeQuery();
			
			if (rs.next()) {
				name = rs.getString("name");
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				if(rs!=null) {rs.close();}
				if(rs!=null) {pstmt.close();}
				if(rs!=null) {conn.close();}
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		
		return name;
	}
	
	public int write(MemberDTO m) {
		System.out.println("DAO write() 실행");
//		System.out.printf("%s %s %s %s %s %s %s %s %s %s %s %s\n", name, id, pwd, gender, email1, email2, tel1, tel2, tel3, zipcode, addr1, addr2);
		String sql = "INSERT INTO member(name, id, pwd, gender, email1, email2, tel1, tel2, tel3, zipcode, addr1, addr2) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, sysdate)";
		int resultRow = -1;
		
		
		try {
			conn = ds.getConnection();
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, m.getName());
			pstmt.setString(2, m.getId());
			pstmt.setString(3, m.getPwd());
			pstmt.setString(4, m.getGender());
			pstmt.setString(5, m.getEmail1());
			pstmt.setString(6, m.getEmail2());
			pstmt.setString(7, m.getTel1());
			pstmt.setString(8, m.getTel2());
			pstmt.setString(9, m.getTel3());
			pstmt.setString(10, m.getZipcode());
			pstmt.setString(11, m.getAddr1());
			pstmt.setString(12, m.getAddr2());
			
			resultRow = pstmt.executeUpdate();
			System.out.println("변화된 row의 갯수: " + resultRow);
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				pstmt.close();
				conn.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return resultRow;
	}
	public boolean isExistId(String id) {
		boolean exist = false;
		String sql = "SELECT * FROM member WHERE id = ?";
		
		try {
			conn = ds.getConnection();
			
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, id);
			
			ResultSet rs = pstmt.executeQuery();
			
			if (rs.next()) {
				exist = true;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				if(rs!=null) {rs.close();}
				if(rs!=null) {pstmt.close();}
				if(rs!=null) {conn.close();}
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		
		return exist;
	}
	
	public MemberDTO selectOne(String id) {
		String sql = "SELECT * FROM member where id = ?";
		MemberDTO m = null;
		
		try {
			conn = ds.getConnection();
			
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, id);
			
			ResultSet rs = pstmt.executeQuery();
			if (rs.next()) {
				// name, id, pwd, gender, email1, email2, tel1, tel2, 
				// tel3, zipcode, addr1, addr2
				m = new MemberDTO();
				m.setName(rs.getString("name"));
				m.setId(rs.getString("id"));
				m.setPwd(rs.getString("pwd"));
				m.setGender(rs.getString("gender"));
				m.setEmail1(rs.getString("email1"));
				m.setEmail2(rs.getString("email2"));
				m.setTel1(rs.getString("tel1"));
				m.setTel2(rs.getString("tel2"));
				m.setTel3(rs.getString("tel3"));
				m.setZipcode(rs.getString("zipcode"));
				m.setAddr1(rs.getString("addr1"));
				m.setAddr2(rs.getString("addr2"));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				if(rs != null) {rs.close();}
				if(pstmt != null) {pstmt.close();}
				if(conn != null) {conn.close();}
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		
		return m;
	}
	
	public int update(MemberDTO m) {
		// name, id, pwd, gender, email1, email2, tel1, tel2, 
		// tel3, zipcode, addr1, addr2
		int resultSet = -1;
		String sql = "UPDATE member SET name = ?"
									+ ", pwd = ?"
									+ ", gender = ?"
									+ ", email1 = ?"
									+ ", email2 = ?"
									+ ", tel1 = ?"
									+ ", tel2 = ?"
									+ ", tel3 = ?"
									+ ", zipcode = ?"
									+ ", addr1 = ?"
									+ ", addr2 = ?"
									+ ", logtime = sysdate WHERE id = ?";
		
		try {
			conn = ds.getConnection();
			
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, m.getName());
			pstmt.setString(2, m.getPwd());
			pstmt.setString(3, m.getGender());
			pstmt.setString(4, m.getEmail1());
			pstmt.setString(5, m.getEmail2());
			pstmt.setString(6, m.getTel1());
			pstmt.setString(7, m.getTel2());
			pstmt.setString(8, m.getTel3());
			pstmt.setString(9, m.getZipcode());
			pstmt.setString(10, m.getAddr1());
			pstmt.setString(11, m.getAddr2());
			pstmt.setString(12, m.getId());
			
			resultSet = pstmt.executeUpdate();
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				if(pstmt != null) {pstmt.close();}
				if(conn != null) {conn.close();}
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return resultSet;
	}
}
