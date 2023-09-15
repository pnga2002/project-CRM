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
import crm_project22.entity.NguoiDung;
import crm_project22.entity.Task;
import crm_project22.entity.TrangThai;

public class TaskRepositoty {
	public List<Task> getAllTask(){
		List<Task> list = new ArrayList<>();
		String query = "select * from CongViec cv \r\n"
				+ "join NguoiDung nd on nd.id = cv.manguoidung \r\n"
				+ "join DuAn da on da.id = cv.maduan \r\n"
				+ "join TrangThai tt on tt.idTrangThai =cv.matrangthai";
		Connection con = MysqlConfig.getConnecttion();
		try {
			PreparedStatement statement = con.prepareStatement(query);
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
				TrangThai tt = new TrangThai();
				tt.setId(result.getInt("tt.idtrangthai"));
				tt.setTen(result.getString("tt.ten"));
				t.setTrangthai(tt);
				list.add(t);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return list;
	}
	public int insert(int maduan, String ten, String mota, int manguoidung, Date ngaybatdau, Date ngayketthuc, int matrangthai) {
		int count = 0;
		String query = "INSERT INTO CongViec (ten, mota, ngaybatdau, ngayketthuc, maduan, matrangthai,manguoidung)\r\n"
				+ "VALUES(?, ?, ?, ?, ?, ?, ?)";
		Connection con = MysqlConfig.getConnecttion();
		try {
			PreparedStatement statement = con.prepareStatement(query);
			statement.setString(1, ten);
			statement.setString(2, mota);
			statement.setDate(3, ngaybatdau);
			statement.setDate(4, ngayketthuc);
			statement.setInt(5, maduan);
			statement.setInt(6, matrangthai);
			statement.setInt(7, manguoidung);
			count  = statement.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return count;
	}
	public Task findById(int id) {
		Task t = new Task();
		String query = "select * from CongViec cv \r\n"
				+ "join NguoiDung nd on nd.id = cv.manguoidung \r\n"
				+ "join DuAn da on da.id = cv.maduan \r\n"
				+ "join TrangThai tt on tt.idTrangThai =cv.matrangthai where cv.idCV= ?";
		Connection con = MysqlConfig.getConnecttion();
		try {
			PreparedStatement statement = con.prepareStatement(query);
			statement.setInt(1, id);
			ResultSet result = statement.executeQuery();
			while(result.next()) {
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
				TrangThai tt = new TrangThai();
				tt.setId(result.getInt("tt.idtrangthai"));
				tt.setTen(result.getString("tt.ten"));
				t.setTrangthai(tt);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return t;
	}
	public int update(int id, int maduan, String ten, String mota, int manguoidung, Date ngaybatdau, Date ngayketthuc, int matrangthai) {
		int count = 0;
		String query = "UPDATE CongViec \r\n"
				+ "SET ten = ?, mota =?, ngaybatdau =?, ngayketthuc =?, maduan =?, matrangthai =?, manguoidung =?\r\n"
				+ "WHERE idCV = ?";
		Connection con = MysqlConfig.getConnecttion();
		try {
			PreparedStatement statement = con.prepareStatement(query);
			statement.setString(1, ten);
			statement.setString(2, mota);
			statement.setDate(3, ngaybatdau);
			statement.setDate(4, ngayketthuc);
			statement.setInt(5, maduan);
			statement.setInt(6, matrangthai);
			statement.setInt(7, manguoidung);
			statement.setInt(8, id);
			count  = statement.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return count;
	}
	public int deleteById(int id) {
		int count = 0;
		String query = "DELETE FROM CongViec WHERE idCV=?";
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
}
