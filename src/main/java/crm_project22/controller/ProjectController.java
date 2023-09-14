package crm_project22.controller;

import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import crm_project22.entity.DuAn;
import crm_project22.service.NguoiDungService;
import crm_project22.service.ProjectService;

@WebServlet(name = "projectController", urlPatterns = { "/project", "/project-add", "/project-edit" })
public class ProjectController extends HttpServlet {

	private ProjectService projectService = new ProjectService();
	private NguoiDungService nguoiDungService = new NguoiDungService();

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String path = req.getServletPath();
		switch (path) {
		case "/project": {
			req.setAttribute("listProject", projectService.getAllProject());
			req.getRequestDispatcher("project.jsp").forward(req, resp);
			break;
		}
		case "/project-add": {
			req.setAttribute("listLead", nguoiDungService.getAllLead());
			req.setAttribute("btnAction", "Add Project");
			req.setAttribute("isShow", "none");
			req.setAttribute("action", "/project-add");
			req.getRequestDispatcher("project-add.jsp").forward(req, resp);
			break;
		}
		case "/project-edit": {
			req.setAttribute("btnAction", "Update Project");
			req.setAttribute("isShow", "block");
			req.setAttribute("action", "/project-edit");
			Integer id = Integer.parseInt(req.getParameter("id"));
			DuAn da = projectService.findById(id);
			req.setAttribute("da", da);
			req.setAttribute("listLead", nguoiDungService.getAllLead());
			req.getRequestDispatcher("project-add.jsp").forward(req, resp);
			break;
		}
		}
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String path = req.getServletPath();
		switch (path) {
		case "/project-add": {
			try {
				SimpleDateFormat outputFormat = new SimpleDateFormat("yyyy-MM-dd");
				String tenduan = req.getParameter("ten");
				String mota = req.getParameter("mota");
				Date start = outputFormat.parse(req.getParameter("ngaybd"));
				Date end = outputFormat.parse(req.getParameter("ngaykt"));
				System.out.println(start);
				String formattedStartDate = outputFormat.format(start);
				String formattedEndDate = outputFormat.format(end);
				java.sql.Date sql_start_date = java.sql.Date.valueOf(formattedStartDate);
				java.sql.Date sql_end_date = java.sql.Date.valueOf(formattedEndDate);
				Integer maquanly = Integer.parseInt(req.getParameter("maquanly"));

				projectService.insert(tenduan, mota, sql_start_date, sql_end_date, maquanly);
			} catch (ParseException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

			resp.sendRedirect("/crm_project22/project");
			break;
		}
		case "/project-edit":{
			try {
				Integer id = Integer.parseInt(req.getParameter("id"));
				SimpleDateFormat outputFormat = new SimpleDateFormat("yyyy-MM-dd");
				String tenduan = req.getParameter("ten");
				String mota = req.getParameter("mota");
				Date start = outputFormat.parse(req.getParameter("ngaybd"));
				Date end = outputFormat.parse(req.getParameter("ngaykt"));
				System.out.println(start);
				String formattedStartDate = outputFormat.format(start);
				String formattedEndDate = outputFormat.format(end);
				java.sql.Date sql_start_date = java.sql.Date.valueOf(formattedStartDate);
				java.sql.Date sql_end_date = java.sql.Date.valueOf(formattedEndDate);
				Integer maquanly = Integer.parseInt(req.getParameter("maquanly"));

				boolean isSuccess = projectService.update(id,tenduan, mota, sql_start_date, sql_end_date, maquanly);
				resp.sendRedirect("/crm_project22/project");
			} catch (ParseException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			break;
		}
		default:
			throw new IllegalArgumentException("Unexpected value: " + path);
		}
	}

}
