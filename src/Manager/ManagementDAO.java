package Manager;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import Manager.ManagementDTO;
import Server.ServerChat;

public class ManagementDAO {

	private Connection conn;
	private Statement stmt;
	private ResultSet rs;
	ManagementDTO dto = null;
	private static ManagementDAO DAOobj;

	public ManagementDAO() {
	}

	private boolean connect() {
		boolean result = false;
		try {
			conn = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:orcl", "system", "11111111");
			result = true;
		} catch (Exception e) {
			// TODO: handle exception
			System.out.println("???? ????:" + e.getMessage());
		}
		return result;
	}

	static {
		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");
			System.out.println("연결 성공");
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
			System.out.println("연결 실패");
		}
	}

	public static ManagementDAO getInstance() {
		if (DAOobj == null) {
			DAOobj = new ManagementDAO();
		}
		return DAOobj;
	}

	public boolean Insert(Object obj) {
		dto = new ManagementDTO();
		String[] check = (String[]) obj;
		// System.out.println("goodsDAO에서 완료함");
		int code = Integer.parseInt(check[0]);
		dto.setCode(code);
		dto.setCname(check[1]);
		int cnt = Integer.parseInt(check[2]);
		dto.setCnt(cnt);
		int price = Integer.parseInt(check[3]);
		dto.setPrice(price);
		// System.out.println(dto);
		boolean result = false;
		if (this.connect()) {
			try {
				String sql = "insert into goods values(?,?,?,?)";
				PreparedStatement psmt = conn.prepareStatement(sql);
				psmt.setInt(1, dto.getCode());
				psmt.setString(2, dto.getCname());
				psmt.setInt(3, dto.getCnt());
				psmt.setInt(4, dto.getPrice());
				int r = psmt.executeUpdate();

				if (r > 0) {
					result = true;
				}
				psmt.close();

			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		} else {
			System.out.println("DB연결 실패");
			System.exit(0);
		}

		return result;
	}

	public boolean Update(Object obj) {
		dto = new ManagementDTO();
		String[] check = (String[]) obj;
		// System.out.println("goodsDAO에서 완료함");
		int code = Integer.parseInt(check[0]);
		dto.setCode(code);
		dto.setCname(check[1]);
		int cnt = Integer.parseInt(check[2]);
		dto.setCnt(cnt);
		int price = Integer.parseInt(check[3]);
		dto.setPrice(price);
		// System.out.println(dto);
		boolean result = false;
		if (connect()) {
			try {
				String sql = "update goods set code=?,cname=?,cnt=?,price=? where code=?";
				PreparedStatement psmt = conn.prepareStatement(sql);
				psmt.setInt(1, dto.getCode());
				psmt.setString(2, dto.getCname());
				psmt.setInt(3, dto.getCnt());
				psmt.setInt(4, dto.getPrice());
				int r = psmt.executeUpdate();
				System.out.println(dto.getCode() + "," + dto.getCname() + "," + dto.getCnt() + "," + dto.getPrice());
				if (r > 0) {
					result = true;
				}
				psmt.close();

			} catch (SQLException e) {
				e.printStackTrace();
			}
		} else {
			System.out.println("DB 연결실패");
			System.exit(0);
		}
		return result;
	}

	public ArrayList<String[]> getList() {
		ArrayList<String[]> list = new ArrayList<String[]>();
		String sql = "SELECT * FROM goods";
		if (connect()) {
			try {
				stmt = conn.createStatement();
				if (stmt != null) {
					rs = stmt.executeQuery(sql);
					while (rs.next()) {
						ManagementDTO dto = new ManagementDTO();
						dto.setCode(rs.getInt("code"));
						dto.setCname(rs.getString("cname"));
						dto.setCnt(rs.getInt("cnt"));
						dto.setPrice(rs.getInt("price"));
						list.add(dto.getArray());
						return list;
					}
				}
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

		} else {
			System.out.println("DB 연결실패");
			System.exit(0);
		}
		return list;
	}

	public boolean delinfo(Object obj) {
		boolean result = false;
		dto = new ManagementDTO();
		String[] check = (String[]) obj;
		int code = Integer.parseInt(check[0]);
		dto.setCode(code);
		if (connect()) {
			try {
				String sql = "delete goods where code=?";
				PreparedStatement psmt = conn.prepareStatement(sql);
				psmt.setInt(1, dto.getCode());
				int r = psmt.executeUpdate();
				System.out.println(dto.getCode() + "," + dto.getCname() + "," + dto.getCnt() + "," + dto.getPrice());
				if (r > 0) {
					result = true;
				}
				psmt.close();

			} catch (SQLException e) {
				System.out.println("삭제실패");
			}
		}
		return result;

	}
	public boolean editinfo(ManagementDTO m) {
		boolean result = false;
		if (connect()) {
			try {
				String sql = "update goods set code=?,cname=?,cnt=?,price=? where code=?";
				PreparedStatement psmt = conn.prepareStatement(sql);
				psmt.setInt(1, m.getCode());
				psmt.setString(2, m.getCname());
				psmt.setInt(3, m.getCnt());
				psmt.setInt(4, m.getPrice());
				int r = psmt.executeUpdate();
				System.out.println(dto.getCode() + "," + dto.getCname() + "," + dto.getCnt() + "," + dto.getPrice());
				if (r > 0) {
					result = true;
				}
				psmt.close();

			} catch (SQLException e) {
				e.printStackTrace();
			}
		} else {
			System.out.println("DB 연결실패");
			System.exit(0);
		}
		return result;
	}

}
