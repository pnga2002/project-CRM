package crm_project22.controller;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import crm_project22.config.MysqlConfig;
import crm_project22.entity.NguoiDung;
import crm_project22.service.LoginService;

/**
 * 
 * @author ASUS
 *	Ý nghĩa từng package:
 *		- Controller: là package lưu trữ những file dùng để khai báo đường đẫn và xử lý logic đường dẫn
 *      - service : chuyên sử lý logic code giành cho controller và két quả cuối cùng về cho controller
 *      - responsitory : CHuyên thực hiện câu query và trả về dữ liệu hoặc kết quả từ câu query (Không có xử lý logic code ở đây
 *      - 
 *
 */

/**
 * 
 * @author ASUS
 *	Bước 1: Xác định chức nnawg sẽ làm ở giao diện là gì
 *	Bước 2: Xác định câu query giành cho chức năng đó
 *	Bước 3: Kiểm tra query xem có hoạt động không
 *	Bước 4: Nhận tham số bằng số lượng query cần
 *  Bước 5: CHuẩn bị cấu query ở sẻvlet
 *  Bước 6: Mở kết nối CSDL
 *  Bước 7: Truyền câu query chuẩn bị vào connecttion mới được mở( và truyền tham số nếu có)
 *  Bước 8: Thực thi quẻy
 *  Bước 9: Lấy kết quả từ câu qquery đã thực thi
 *  Bước 10: XỬ lý logic code tương ứng với nghiệp vụ
 *  
 */
/**
 * 
 * @author ASUS
 *
 *  SQL Injection, Insert Batch, Stream, Non Blocking
 */

@WebServlet(name ="loginController", urlPatterns = {"/login"})
public class LoginController extends HttpServlet{
	
	private LoginService loginService = new LoginService();
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		req.getRequestDispatcher("login.jsp").forward(req, resp);
	}
	
	@Override
		protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String email = req.getParameter("email");
		String password  = req.getParameter("password");
		boolean isSuccess = loginService.checkLogin(req,email, password);
		if(isSuccess) {
			resp.sendRedirect("/crm_project22");
			System.out.println("Đăng nhập roi");

			
		} else {
			System.out.println("Đăng nhập thất bại");
		}
		
		
		
		
//		//nhận tham số	
//		String email = req.getParameter("email");
//		String password  = req.getParameter("password");
//		//chuanr bị câu query 
//		String query = "select  * from NguoiDung nd WHERE  nd.email =? and nd.matkhau =?";
//		Connection connection = MysqlConfig.getConnecttion();
//		List<NguoiDung> listNguoiDung = new ArrayList<>();
//		//truyền câu query vào connection 
//		try {
//			PreparedStatement statement = connection.prepareStatement(query);
//			statement.setString(1, email);
//			statement.setString(2, password);
//			
//			//Thực thi câu query 
//			/**
//			 *  executeQuery : Khi câu truy vấn là câu lấy dự liệu select
//			 *  executeQuery : Không phải là câu lấy dữ liệu, INSERT, Delete, UPDATE
//			 */
//			ResultSet resultSet = statement.executeQuery();
//			//duyệt dữ liệu từ resultSet
//			while(resultSet.next()) {
//				NguoiDung nd = new NguoiDung();
////				resultSet.getInt("id");//lấy giá trị của cột id trong CSDL khi duyệt qua từng dòng
//				nd.setId(resultSet.getInt("id"));
//				nd.setFullname(resultSet.getString("fullname"));
//				nd.setEmail(resultSet.getString("email"));
//				nd.setFullname(resultSet.getString("fullname"));
//				listNguoiDung.add(nd);
//			}
//			if(listNguoiDung.size()>0) {
//				System.out.println("Đăng nhập thành công");
//			}
//			else {
//				System.out.println("Đăng nhập thất bại");
//			}
//			
//		} catch (SQLException e) {
//			// TODO Auto-generated catch block
//			System.out.println("Lỗi thực thi câu query " + e.getLocalizedMessage());
//		} finally {
//			if(connection != null) {
//				try {
//					connection.close();
//				} catch (SQLException e) {
//					// TODO Auto-generated catch block
//					System.out.println("Looic đóng kết nối " + e.getLocalizedMessage());
//				}
//			}
//		}
//		
//		req.getRequestDispatcher("login.jsp").forward(req, resp);
		}
}
