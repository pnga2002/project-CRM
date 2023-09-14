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

/**
 * 
 * @author ASUS
 *  Tên class của repository sẽ tạo theo tên bảng, để quản lý tất cả ccas câu query liên quan tới bẳng đó
 *  findBy : Giành cho câu select có điều kiện where
 */
public class NguoiDungRepository {
	public List<NguoiDung> findByEmailAndPassword(String email, String password){
		String query = "select  * from NguoiDung nd \n"
				+ "join LoaiThanhVien ltv on ltv.id=nd.id_loaithanhvien\n"
				+ "WHERE  nd.email =? and nd.matkhau =?";
		Connection connection = MysqlConfig.getConnecttion();
		List<NguoiDung> listNguoiDung = new ArrayList<>();
		//truyền câu query vào connection 
		try {
			PreparedStatement statement = connection.prepareStatement(query);
			statement.setString(1, email);
			statement.setString(2, password);
			
			//Thực thi câu query 
			/**
			 *  executeQuery : Khi câu truy vấn là câu lấy dự liệu select
			 *  executeQuery : Không phải là câu lấy dữ liệu, INSERT, Delete, UPDATE
			 */
			ResultSet resultSet = statement.executeQuery();
			//duyệt dữ liệu từ resultSet
			while(resultSet.next()) {
				NguoiDung nd = new NguoiDung();
//				resultSet.getInt("id");//lấy giá trị của cột id trong CSDL khi duyệt qua từng dòng
				nd.setId(resultSet.getInt("id"));
				nd.setFullname(resultSet.getString("fullname"));
				nd.setEmail(resultSet.getString("email"));
				LoaiThanhVien loaiThanhVien = new LoaiThanhVien();
				loaiThanhVien.setTen(resultSet.getString("ten"));
				nd.setLoaiThanhVien(loaiThanhVien);
				listNguoiDung.add(nd);
			}
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			System.out.println("Lỗi thực thi câu query " + e.getLocalizedMessage());
		} finally {
			if(connection != null) {
				try {
					connection.close();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					System.out.println("Looic đóng kết nối " + e.getLocalizedMessage());
				}
			}
		}
		return listNguoiDung;
	}
	public List<NguoiDung> getAllNguoiDung(){
		List<NguoiDung> list = new ArrayList<>();
		String query = "select * from NguoiDung nd join LoaiThanhVien ltv on ltv.id=nd.id_loaithanhvien";
		Connection con = MysqlConfig.getConnecttion();
		try {
			PreparedStatement statement = con.prepareStatement(query);
			ResultSet result = statement.executeQuery();
			while(result.next()) {
				NguoiDung nd = new NguoiDung();
				nd.setId(result.getInt("nd.id"));
				nd.setDiaChi(result.getString("diachi"));
				nd.setEmail(result.getString("email"));
				nd.setMatKhau(result.getString("matkhau"));
				nd.setFullname(result.getString("fullname"));
				nd.setSoDienThoai(result.getString("soDienThoai"));
				LoaiThanhVien loaiThanhVien = new LoaiThanhVien();
				loaiThanhVien.setTen(result.getString("ten"));
				nd.setLoaiThanhVien(loaiThanhVien);
				
				list.add(nd);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			try {
				con.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				System.out.println("Looic đóng kết nối " + e.getLocalizedMessage());
			}
		}
		return list;
	}
	public int insert(String email, String matKhau, String fullname, String diaChi, String soDienThoai, int id_loaiThanhVien) {
		int count = 0;
		String query = "INSERT INTO NguoiDung (email, matkhau, fullname, diachi, soDienThoai, id_loaithanhvien) value(?, ?, ?, ?, ?, ?)";
		Connection con = MysqlConfig.getConnecttion();
		try {
			PreparedStatement statement = con.prepareStatement(query);
			statement.setString(1, email);
			statement.setString(2, matKhau);
			statement.setString(3, fullname);
			statement.setString(4, diaChi);
			statement.setString(5, soDienThoai);
			statement.setInt(6, id_loaiThanhVien);
			count  = statement.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			System.out.println("Lỗi thêm dữ liệu " + e.getLocalizedMessage());
		}finally {
			if(con != null) {
				try {
					con.close();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					System.out.println("Lỗi đóng kết nối " + e.getLocalizedMessage());
				}
			}
		}
		
		
		return count;
	}
	public int deleteById(int id) {
		int count = 0;
		String query = "DELETE FROM NguoiDung WHERE id=?";
		Connection con = MysqlConfig.getConnecttion();
		try {
			PreparedStatement statement = con.prepareStatement(query);
			statement.setInt(1, id);
			count = statement.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			if(con != null) {
				try {
					con.close();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					System.out.println("Lỗi đóng kết nối " + e.getLocalizedMessage());
				}
			}
		}
		return count;
	}
	
	public int update(int id, String email, String matKhau, String fullname, String diaChi, String soDienThoai, int id_loaiThanhVien) {
		int count = 0;
		String query = "UPDATE NguoiDung nd\r\n"
				+ "SET nd.email = ?, nd.matkhau =?, nd.fullname =?, nd.diachi =?, nd.soDienThoai =?, nd.id_loaithanhvien =?\r\n"
				+ "WHERE nd.id = ?";
		Connection con = MysqlConfig.getConnecttion();
		try {
			PreparedStatement statement = con.prepareStatement(query);
			statement.setString(1, email);
			statement.setString(2, matKhau);
			statement.setString(3, fullname);
			statement.setString(4, diaChi);
			statement.setString(5, soDienThoai);
			statement.setInt(6, id_loaiThanhVien);
			statement.setInt(7, id);
			
			count = statement.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return count;
	}
	
	public NguoiDung findById(int id) {
		NguoiDung nd = new NguoiDung();
		String query = "select * from NguoiDung nd join LoaiThanhVien ltv on ltv.id=nd.id_loaithanhvien where nd.id = ?";
		Connection con = MysqlConfig.getConnecttion();
		try {
			PreparedStatement statement = con.prepareStatement(query);
			statement.setInt(1, id);
			ResultSet resultSet = statement.executeQuery();
			while(resultSet.next()) {
				nd.setId(resultSet.getInt("nd.id"));
				nd.setEmail(resultSet.getString("email"));
				nd.setMatKhau(resultSet.getString("matkhau"));
				nd.setFullname(resultSet.getString("fullname"));
				nd.setDiaChi(resultSet.getString("diachi"));
				nd.setSoDienThoai(resultSet.getString("soDienThoai"));
				LoaiThanhVien loaiThanhVien = new LoaiThanhVien();
				loaiThanhVien.setTen(resultSet.getString("ten"));
				loaiThanhVien.setId(resultSet.getInt("ltv.id"));
				nd.setLoaiThanhVien(loaiThanhVien);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return nd;
	}

	public List<NguoiDung> getAllLead(){
		List<NguoiDung> list = new ArrayList<>();
		String query = "select * from NguoiDung nd join LoaiThanhVien ltv on ltv.id=nd.id_loaithanhvien where ltv.ten='LEAD'";
		Connection con = MysqlConfig.getConnecttion();
		try {
			PreparedStatement statement = con.prepareStatement(query);
			ResultSet result = statement.executeQuery();
			while(result.next()) {
				NguoiDung nd = new NguoiDung();
				nd.setId(result.getInt("nd.id"));
				nd.setFullname(result.getString("fullname"));
				LoaiThanhVien loaiThanhVien = new LoaiThanhVien();
				loaiThanhVien.setTen(result.getString("ten"));
				nd.setLoaiThanhVien(loaiThanhVien);
				list.add(nd);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			try {
				con.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				System.out.println("Looic đóng kết nối " + e.getLocalizedMessage());
			}
		}
		return list;
	}
}
