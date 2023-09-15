package crm_project22.service;

import java.util.List;

import crm_project22.entity.TrangThai;
import crm_project22.repository.StatusRepository;

public class StatusService {
	private StatusRepository statusRepository = new StatusRepository();
	public List<TrangThai> getAllStatus(){
		return statusRepository.getAllStatus();
	}
}
