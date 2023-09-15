package crm_project22.controller;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import crm_project22.service.NguoiDungService;
import crm_project22.service.ProjectService;
import crm_project22.service.StatusService;
import crm_project22.service.TaskService;

@WebServlet(name ="taskController", urlPatterns = {"/task", "/task-add", "/task-edit"})
public class TaskController extends HttpServlet{
	private TaskService taskService = new TaskService();
	private StatusService statusService = new StatusService();
	private ProjectService projectService = new ProjectService();
	private NguoiDungService nguoiDungService = new NguoiDungService();
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String path = req.getServletPath();
		switch (path) {
		case "/task": {
			req.setAttribute("listTask", taskService.getAllTask());
			req.getRequestDispatcher("task.jsp").forward(req, resp);
			break;
		}
		case "/task-add": {
			req.setAttribute("btnAction", "Add Task");
			req.setAttribute("isShow", "none");
			req.setAttribute("action", "/task-add");
			
			req.setAttribute("listUser", nguoiDungService.getAllNguoiDung());
			req.setAttribute("listStatus", statusService.getAllStatus());
			req.setAttribute("listProject", projectService.getAllProject());
			req.getRequestDispatcher("task-add.jsp").forward(req, resp);
			break;
		}
		case "/task-edit": {
			req.setAttribute("btnAction", "Update Task");
			req.setAttribute("isShow", "block");
			req.setAttribute("action", "/task-edit");
			Integer id = Integer.parseInt(req.getParameter("id"));
			
			req.setAttribute("cv", taskService.findById(id));
			req.setAttribute("listUser", nguoiDungService.getAllNguoiDung());
			req.setAttribute("listStatus", statusService.getAllStatus());
			req.setAttribute("listProject", projectService.getAllProject());
			req.getRequestDispatcher("task-add.jsp").forward(req, resp);
			break;
		}
		default:
			throw new IllegalArgumentException("Unexpected value: " + path);
		}
	}
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String path = req.getServletPath();
		switch (path) {
		case "/task-add": {
			try {
				Integer maduan = Integer.parseInt(req.getParameter("maduan"));
				String ten = req.getParameter("ten");
				String mota = req.getParameter("mota");
				Integer manguoidung = Integer.parseInt(req.getParameter("nguoithuchien"));
				
				//get date format
				SimpleDateFormat outputFormat = new SimpleDateFormat("yyyy-MM-dd");
				Date start = outputFormat.parse(req.getParameter("ngaybd"));
				Date end = outputFormat.parse(req.getParameter("ngaykt"));
				String formattedStartDate = outputFormat.format(start);
				String formattedEndDate = outputFormat.format(end);
				java.sql.Date sql_start_date = java.sql.Date.valueOf(formattedStartDate);
				java.sql.Date sql_end_date = java.sql.Date.valueOf(formattedEndDate);
				Integer matrangthai = Integer.parseInt(req.getParameter("trangthai"));
				
				boolean isSuccess = taskService.insert(maduan, ten, mota, manguoidung, sql_start_date, sql_end_date, matrangthai);
				resp.sendRedirect("/crm_project22/task");
			} catch (Exception e) {
				// TODO: handle exception
			}
			break;
		}
		case "/task-edit": {
			try {
				Integer id = Integer.parseInt(req.getParameter("idCV"));
				Integer maduan = Integer.parseInt(req.getParameter("maduan"));
				String ten = req.getParameter("ten");
				String mota = req.getParameter("mota");
				Integer manguoidung = Integer.parseInt(req.getParameter("nguoithuchien"));
				
				//get date format
				SimpleDateFormat outputFormat = new SimpleDateFormat("yyyy-MM-dd");
				Date start = outputFormat.parse(req.getParameter("ngaybd"));
				Date end = outputFormat.parse(req.getParameter("ngaykt"));
				String formattedStartDate = outputFormat.format(start);
				String formattedEndDate = outputFormat.format(end);
				java.sql.Date sql_start_date = java.sql.Date.valueOf(formattedStartDate);
				java.sql.Date sql_end_date = java.sql.Date.valueOf(formattedEndDate);
				Integer matrangthai = Integer.parseInt(req.getParameter("trangthai"));
				
				boolean isSuccess = taskService.update(id,maduan, ten, mota, manguoidung, sql_start_date, sql_end_date, matrangthai);
				resp.sendRedirect("/crm_project22/task");
			} catch (Exception e) {
				// TODO: handle exception
			}
			break;
		}
		default:
			throw new IllegalArgumentException("Unexpected value: " + path);
		}
	}
}
