package crm_project22.repository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import crm_project22.config.MysqlConfig;
import crm_project22.entity.LoaiThanhVien;
import crm_project22.entity.NguoiDung;

public class LoaiNguoiDungRepository {
	public int insert(String ten,String mota) {
		int count=0;
		String query = "INSERT  into LoaiThanhVien (ten, mota) values(?, ?)";
		Connection connection = MysqlConfig.getConnecttion();
		//truyền câu query vào connection 
		try {
			PreparedStatement statement = connection.prepareStatement(query);
			statement.setString(1, ten);
			statement.setString(2, mota);
			//Thực thi câu query 
			count = statement.executeUpdate();
			
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			System.out.println("Lỗi thêm dữ liệu " + e.getLocalizedMessage());
		}  finally {
			if(connection != null) {
				try {
					connection.close();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					System.out.println("Lỗi đóng kết nối " + e.getLocalizedMessage());
				}
			}
		}
		return count;
	}
	public List<LoaiThanhVien> getAllLoaiThanhVien() {
		List<LoaiThanhVien> list = new ArrayList<>();
		String query= "SELECT * FROM LoaiThanhVien";
		Connection con = MysqlConfig.getConnecttion();
		try {
			PreparedStatement statement = con.prepareStatement(query);
			ResultSet resultSet = statement.executeQuery();
			while(resultSet.next()) {
				LoaiThanhVien loaiThanhVien = new LoaiThanhVien();
				loaiThanhVien.setId(resultSet.getInt("id"));
				loaiThanhVien.setTen(resultSet.getString("ten"));
				loaiThanhVien.setMota(resultSet.getString("mota"));
				list.add(loaiThanhVien);
			}
		} catch (Exception e) {
			// TODO: handle exception
		} finally {
			try {
				con.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				System.out.println("Looic đóng kết nối " + e.getLocalizedMessage());
			}
		}
		return list;
	}
	public int deleteById(int id) {
		int count=0;
		String query = "DELETE FROM LoaiThanhVien WHERE id = ?";
		Connection con = MysqlConfig.getConnecttion();
		try {
			PreparedStatement statement = con.prepareStatement(query);
			statement.setInt(1, id);
			
			count = statement.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return count;
	}
	public int update(int id, String ten, String mota) {
		int count = 0;
		String query = "UPDATE LoaiThanhVien\r\n"
				+ "SET ten = ?, mota = ? \r\n"
				+ "WHERE id = ?";
		Connection con = MysqlConfig.getConnecttion();
		try {
			PreparedStatement statement = con.prepareStatement(query);
			statement.setString(1, ten);
			statement.setString(2, mota);
			statement.setInt(3, id);
			count = statement.executeUpdate();
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return count;
	}
	public LoaiThanhVien findById(int id) {
		LoaiThanhVien loaiThanhVien = new LoaiThanhVien();
		String query = "SELECT * FROM LoaiThanhVien WHERE id = ?";
		Connection con = MysqlConfig.getConnecttion();
		try {
			PreparedStatement statement = con.prepareStatement(query);
			statement.setInt(1, id);
			ResultSet resultSet = statement.executeQuery();
			while(resultSet.next()) {
				loaiThanhVien.setId(resultSet.getInt("id"));
				loaiThanhVien.setTen(resultSet.getString("ten"));
				loaiThanhVien.setMota(resultSet.getString("mota"));
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return loaiThanhVien;
		
	}
}
