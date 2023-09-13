package crm_project22.api;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;

import crm_project22.entity.LoaiThanhVien;
import crm_project22.payload.response.BaseResponse;
import crm_project22.service.RoleService;
@WebServlet(name="apiRoleController",urlPatterns = {"/api/role/delete"})
public class ApiRoleController extends HttpServlet{
	
	private Gson gson = new Gson();
	private RoleService roleService = new RoleService();
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		int id = Integer.parseInt(req.getParameter("id"));
		boolean isSuccess = roleService.deleteById(id);
		BaseResponse response = new BaseResponse();
		response.setStatusCode(200);
		response.setMessage(isSuccess ? "Xóa thành công" : "Xóa thất bại");
		response.setData(isSuccess);
		String json = gson.toJson(response);
		
//		String json = "{\"id\":2,\"ten\":\"ADMIN\",\"mota\":\"TestDulieu\"}";
//		
//		LoaiThanhVien loaiThanhVien = gson.fromJson(json, LoaiThanhVien.class);
		
//		LoaiThanhVien loaiThanhVien = new LoaiThanhVien();
//		loaiThanhVien.setId(2);
//		loaiThanhVien.setMota("Test Du lieu");
//		loaiThanhVien.setTen("ADMIN");
		
//		String dataJSON = gson.toJson(loaiThanhVien);
		
		PrintWriter out = resp.getWriter();
		resp.setContentType("application/json");
		resp.setCharacterEncoding("UTF-8");
		out.print(json);
		out.flush();
	}
}
