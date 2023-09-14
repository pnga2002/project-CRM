package crm_project22.controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import crm_project22.entity.LoaiThanhVien;
import crm_project22.service.RoleService;

@WebServlet(name = "roleController", urlPatterns = {"/role-add","/role","/role-edit"})
public class RoleController extends HttpServlet{
	private RoleService roleService = new RoleService();
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String path = req.getServletPath();
		switch (path) {
		case "/role-add": {

			req.setAttribute("btnAction", "Add Role");
			req.setAttribute("action", "/role-add");
			req.setAttribute("isShow", "none");
			req.getRequestDispatcher("role-add.jsp").forward(req, resp);
			break;
		}
		case "/role": {
			req.setAttribute("listRole", roleService.getAllLoaiThanhVien());
			req.getRequestDispatcher("role-table.jsp").forward(req, resp);
			break;
		}
		case "/role-edit":{
			req.setAttribute("btnAction", "Update Role");
			req.setAttribute("isShow", "block");
			Integer id = Integer.parseInt(req.getParameter("id"));
			LoaiThanhVien ltv = roleService.findById(id);
			req.setAttribute("ltv", ltv);
			req.setAttribute("action", "/role-edit");
			req.getRequestDispatcher("role-add.jsp").forward(req, resp);
			break;
		}
		default:
			throw new IllegalArgumentException("Unexpected value: " + path);
		}
		
	}
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String path  = req.getServletPath();
		switch(path) {
		case "/role":{
			req.getRequestDispatcher("role-table.jsp").forward(req, resp);
			break;
		}
		case "/role-add":{
			String ten = req.getParameter("ten");
			String mota = req.getParameter("mota");
			boolean isSuccess = roleService.insert(ten, mota);
			req.setAttribute("isSuccess", isSuccess);
			

			resp.sendRedirect("/crm_project22/role");			
			break;
		}
		case "/role-edit":{
			
			Integer id = Integer.parseInt(req.getParameter("id"));
			System.out.println(id);
			String ten = req.getParameter("ten");
			String mota = req.getParameter("mota");
			boolean isSuccess = roleService.update(id, ten, mota);
			req.setAttribute("isSuccess", isSuccess);
			

			resp.sendRedirect("/crm_project22/role");			
			break;
		}

		}
	}
}
