package crm_project22.service;

import java.sql.Date;
import java.util.List;

import crm_project22.entity.Task;
import crm_project22.repository.TaskRepositoty;

public class TaskService {
	private TaskRepositoty taskRepositoty =new TaskRepositoty();
	public List<Task> getAllTask(){
		System.out.println(taskRepositoty.getAllTask().size());
		return taskRepositoty.getAllTask();
	}
	public boolean insert(int maduan, String ten, String mota, int manguoidung, Date ngaybatdau, Date ngayketthuc, int matrangthai) {
		int count =taskRepositoty.insert(maduan, ten, mota, manguoidung, ngaybatdau, ngayketthuc, matrangthai);
		return count > 0;
	}
	public Task findById(int id) {
		return taskRepositoty.findById(id);
	}
	public boolean update(int id, int maduan, String ten, String mota, int manguoidung, Date ngaybatdau, Date ngayketthuc, int matrangthai) {
		int count = taskRepositoty.update(id, maduan, ten, mota, manguoidung, ngaybatdau, ngayketthuc, matrangthai);
		return count >0;
	}
	public boolean deleteById(int id) {
		int count = taskRepositoty.deleteById(id);
		return count > 0;
	}
}
