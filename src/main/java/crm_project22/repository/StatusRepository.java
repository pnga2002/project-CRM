package crm_project22.repository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import crm_project22.config.MysqlConfig;
import crm_project22.entity.TrangThai;

public class StatusRepository {
	public List<TrangThai> getAllStatus() {
		List<TrangThai> list = new ArrayList<>();
		String query = "SELECT * FROM TrangThai";
		Connection con = MysqlConfig.getConnecttion();
		try {
			PreparedStatement statement = con.prepareStatement(query);
			ResultSet result = statement.executeQuery();
			while(result.next()) {
				TrangThai tt = new TrangThai();
				tt.setId(result.getInt("idTrangThai"));
				tt.setTen(result.getString("ten"));
				list.add(tt);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return list;
	}
}
