package ngo.friendship.projecus.service;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import ngo.friendship.projecus.repo.ProjectRepo;

@Service
public class ProjectService {
	
	@Autowired
   private ProjectRepo projectRepo;

	public List<Map<String, Object>> getProjectFilterList(Map<String, Object> model) {
		return projectRepo.getFIlterProjectList(model);
	}
	public List<Map<String, Object>> getProjectListAndSync(Map<String, Object> model) {
		List<Map<String, Object>> dataList =  projectRepo.getSyncProjectList(model);
		return dataList;
	}

	public List<Map<String, Object>> getProjectMemberList(Map<String, Object> model) {
		Integer projectId = Integer.parseInt(model.get("project_id").toString());
        return projectRepo.getProjectMemberList(projectId);
    }

}
