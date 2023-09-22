package crm_project22.controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import crm_project22.entity.NguoiDung;
import crm_project22.entity.Task;
import crm_project22.service.NguoiDungService;
import crm_project22.service.RoleService;

@WebServlet(name = "userController", urlPatterns = {"/user","/user-add","/user-edit", "/user-detail","/profile"})
public class UserController extends HttpServlet{
	
	private NguoiDungService nguoiDungService = new NguoiDungService();
	private RoleService roleService = new RoleService();
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String path = req.getServletPath();
		switch (path) {
		case "/user":{
			req.setAttribute("listUser", nguoiDungService.getAllNguoiDung());
			req.getRequestDispatcher("user-table.jsp").forward(req, resp);
			break;
		}
		case "/user-add":{
			req.setAttribute("listRole", roleService.getAllLoaiThanhVien());
			req.setAttribute("btnAction", "Add User");
			req.setAttribute("isShow", "none");
			req.setAttribute("action", "/user-add");
			req.getRequestDispatcher("user-add.jsp").forward(req, resp);
			break;
		}
		case "/user-edit":{
			req.setAttribute("btnAction", "Update User");
			req.setAttribute("isShow", "block");
			req.setAttribute("action", "/user-edit");
			Integer id = Integer.parseInt(req.getParameter("id"));
			NguoiDung nd = nguoiDungService.findById(id);
			req.setAttribute("nd", nd);
			req.setAttribute("listRole", roleService.getAllLoaiThanhVien());
			req.getRequestDispatcher("user-add.jsp").forward(req, resp);
			break;
		}
		case "/user-detail":{
			Integer id = Integer.parseInt(req.getParameter("id"));
			List<Task> listNew = nguoiDungService.getTaskByUserId(id, 8);
			List<Task> listDoing = nguoiDungService.getTaskByUserId(id, 9);
			List<Task> listDone = nguoiDungService.getTaskByUserId(id, 10);
			req.setAttribute("listNew", listNew);
			req.setAttribute("listDoing", listDoing);
			req.setAttribute("listDone", listDone);
			int countNew = listNew.size();
			int countDoing = listDoing.size();
			int countDone = listDone.size();
			int total = countDoing + countDone + countNew;
			req.setAttribute("percentNew", (countNew*100/total));
			req.setAttribute("percentDoing", (countDoing*100/total));
			req.setAttribute("percentDone", (countDone*100/total));
			NguoiDung nd = nguoiDungService.findById(id);
			req.setAttribute("nd", nd);
			req.getRequestDispatcher("user-detail.jsp").forward(req, resp);
			
			break;
		}
		case "/profile":{

			HttpSession session = req.getSession();
			Integer userID = (Integer) session.getAttribute("userID");
			System.out.println(userID);
			NguoiDung nd = nguoiDungService.findById(userID);
			List<Task> listNew = nguoiDungService.getTaskByUserId(userID, 8);
			List<Task> listDoing = nguoiDungService.getTaskByUserId(userID,9);
			List<Task> listDone = nguoiDungService.getTaskByUserId(userID, 10);
			
			int countNew = listNew.size();
			int countDoing = listDoing.size();
			int countDone = listDone.size();
			int total = countDoing + countDone + countNew;
			
			req.setAttribute("list", nguoiDungService.getAllTaskByUserId(userID));
			
			req.setAttribute("nd", nd);
			
			req.setAttribute("percentNew", (countNew*100/total));
			req.setAttribute("percentDoing", (countDoing*100/total));
			req.setAttribute("percentDone", (countDone*100/total));
			
			req.getRequestDispatcher("profile.jsp").forward(req, resp);
			break;
		}
		}
	}
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String path = req.getServletPath();
		switch (path) {
		case "/user":{
			req.getRequestDispatcher("user-table.jsp").forward(req, resp);
			break;
		}
		case "/user-add":{
			String email = req.getParameter("email");
			String matkhau = req.getParameter("matkhau");
			String fullname = req.getParameter("fullname");
			String diachi = req.getParameter("diachi");
			String soDienThoai = req.getParameter("sodienthoai");
			Integer id_loaiThanhVien = Integer.parseInt(req.getParameter("id_loaiThanhVien"));
			boolean isSuccess = nguoiDungService.insert(email, matkhau, fullname, diachi, soDienThoai, id_loaiThanhVien);
			req.setAttribute("isSuccess", isSuccess);
			System.out.println(isSuccess);
			resp.sendRedirect("/crm_project22/user");
			break;
		}
		case "/user-edit":{
			Integer id = Integer.parseInt(req.getParameter("id"));
			String email = req.getParameter("email");
			String matkhau = req.getParameter("matkhau");
			String fullname = req.getParameter("fullname");
			String diachi = req.getParameter("mota");
			String soDienThoai = req.getParameter("mota");
			Integer id_loaiThanhVien = Integer.parseInt(req.getParameter("id_loaiThanhVien"));
			boolean isSuccess = nguoiDungService.update(id,email,matkhau,fullname,diachi,soDienThoai,id_loaiThanhVien);
			req.setAttribute("isSuccess", isSuccess);
			resp.sendRedirect("/crm_project22/user");
		}
		}
	}
}
