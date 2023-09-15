package crm_project22.api;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;

import crm_project22.payload.response.BaseResponse;
import crm_project22.service.ProjectService;
import crm_project22.service.TaskService;

@WebServlet(name="apiTaskController", urlPatterns = {"/api/task/delete"})
public class ApiTaskController extends HttpServlet{
	private Gson gson = new Gson();
	private TaskService  taskService = new TaskService();
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		int id = Integer.parseInt(req.getParameter("id"));
		boolean isSuccess = taskService.deleteById(id);
		BaseResponse response = new BaseResponse();
		response.setStatusCode(200);
		response.setMessage(isSuccess ? "Xóa thành công" : "Xóa thất bại");
		response.setData(isSuccess);
		String json = gson.toJson(response);
		PrintWriter out = resp.getWriter();
		resp.setContentType("application/json");
		resp.setCharacterEncoding("UTF-8");
		out.print(json);
		out.flush();
	}
}
