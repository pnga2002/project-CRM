package crm_project22.repository;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import crm_project22.config.MysqlConfig;
import crm_project22.entity.DuAn;
import crm_project22.entity.LoaiThanhVien;
import crm_project22.entity.NguoiDung;
import crm_project22.entity.Task;
import crm_project22.entity.TrangThai;

public class ProjectRepository {
	public List<DuAn> getAllProject(){
		List<DuAn> list = new ArrayList<>();
		String query = "SELECT * FROM DuAn da \r\n"
				+ "JOIN NguoiDung nd ON da.maquanly = nd.id\r\n"
				+ "JOIN TrangThai tt on da.matrangthai = tt.idTrangThai";
		Connection con = MysqlConfig.getConnecttion();
		try {
			PreparedStatement statement = con.prepareStatement(query);
			ResultSet result = statement.executeQuery();
			while(result.next()) {
				DuAn da = new DuAn();
				da.setId(result.getInt("da.id"));
				da.setMota(result.getString("mota"));
				da.setTenduan(result.getString("tenduan"));
				da.setNgaybatdau(result.getDate("ngaybatdau"));
				da.setNgayketthuc(result.getDate("ngayketthuc"));
				NguoiDung nd = new NguoiDung();
				nd.setId(result.getInt("nd.id"));
				nd.setFullname(result.getString("fullname"));
				da.setQuanly(nd);
				TrangThai tt = new TrangThai();
				tt.setId(result.getInt("idTrangThai"));
				tt.setTen(result.getString("tt.ten"));
				da.setTrangthai(tt);
				list.add(da);
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
	public DuAn findById(int id) {
		DuAn da = new DuAn();
		String query = "SELECT * FROM DuAn da \r\n"
				+ "JOIN NguoiDung nd ON da.maquanly = nd.id\r\n"
				+ "JOIN TrangThai tt on da.matrangthai = tt.idTrangThai WHERE da.id=?";
		Connection con  = MysqlConfig.getConnecttion();
		try {
			PreparedStatement statement = con.prepareStatement(query);
			statement.setInt(1, id);
			ResultSet result = statement.executeQuery();
			while(result.next()) {
				da.setId(result.getInt("da.id"));
				da.setMota(result.getString("mota"));
				da.setTenduan(result.getString("tenduan"));
				da.setNgaybatdau(result.getDate("ngaybatdau"));
				da.setNgayketthuc(result.getDate("ngayketthuc"));
				NguoiDung nd = new NguoiDung();
				nd.setId(result.getInt("nd.id"));
				nd.setFullname(result.getString("fullname"));
				da.setQuanly(nd);
				TrangThai tt = new TrangThai();
				tt.setId(result.getInt("idTrangThai"));
				tt.setTen(result.getString("tt.ten"));
				da.setTrangthai(tt);
			}
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return da;
		
	}
	public int insert (String tenduan, String mota, Date ngaybatdau, Date ngayketthuc, int maquanly) {
		int count = 0;
		String query = "INSERT INTO DuAn (tenduan, mota, ngaybatdau, ngayketthuc, maquanly, matrangthai) VALUES (?, ?, ?, ?, ?, 8)";
		Connection con = MysqlConfig.getConnecttion();
		try {
			PreparedStatement statement = con.prepareStatement(query);
			statement.setString(1, tenduan);
			statement.setString(2, mota);
			statement.setDate(3, ngaybatdau);
			statement.setDate(4, ngayketthuc);
			statement.setInt(5, maquanly);
			count  = statement.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return count;
	}
	public int update(int id, String tenduan, String mota, Date ngaybatdau, Date ngayketthuc, int maquanly) {
		int count = 0;
		String query = "UPDATE DuAn da\r\n"
				+ "SET da.tenduan = ?, da.mota =?, da.ngaybatdau =?, da.ngayketthuc =?, da.maquanly =?\r\n"
				+ "WHERE da.id = ?";
		Connection con = MysqlConfig.getConnecttion();
		try {
			PreparedStatement statement = con.prepareStatement(query);
			statement.setString(1, tenduan);
			statement.setString(2, mota);
			statement.setDate(3, ngaybatdau);
			statement.setDate(4, ngayketthuc);
			statement.setInt(5, maquanly);
			statement.setInt(6, id);
			count  = statement.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return count;
	}
	public int deleteById(int id) {
		int count = 0;
		String query = "DELETE FROM DuAn WHERE id=?";
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
	
	public List<Task> getTaskNewInProjectByID(int id, int idtt){
		List<Task> list = new ArrayList<>();
		
		String query = "SELECT * from CongViec cv\r\n"
				+ "join DuAn da on da.id  = cv.maduan \r\n"
				+ "join NguoiDung nd ON cv.manguoidung  = nd.id \r\n"
				+ "where da.id=? and cv.matrangthai  = ?";
		Connection con = MysqlConfig.getConnecttion();
		try {
			PreparedStatement statement = con.prepareStatement(query);
			statement.setInt(1, id);
			statement.setInt(2, idtt);
			ResultSet result = statement.executeQuery();
			while(result.next()) {
				Task t = new Task();
				t.setIdCV(result.getInt("cv.idCV"));
				t.setMota(result.getString("cv.mota"));
				t.setNgaybatdau(result.getDate("cv.ngaybatdau"));
				t.setNgayketthuc(result.getDate("cv.ngayketthuc"));
				t.setTen(result.getString("cv.ten"));
				DuAn da = new DuAn();
				da.setId(result.getInt("da.id"));
				da.setTenduan(result.getString("da.tenduan"));
				t.setDuan(da);
				NguoiDung nd = new NguoiDung();
				nd.setId(result.getInt("nd.id"));
				nd.setFullname(result.getString("fullname"));
				t.setNguoidung(nd);
				list.add(t);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return list;
		
	}
}
