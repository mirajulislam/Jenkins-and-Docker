package ngo.friendship.projecus.controller;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import io.swagger.annotations.ApiOperation;
import ngo.friendship.projecus.config.AppResponse;
import ngo.friendship.projecus.service.ProjectService;

@RestController
@RequestMapping("/projecus")
public class ProjecusController {
	@Autowired
	private ProjectService projectService;
	
	@ApiOperation(value = "Get project list by 'project_activity_status' or 'project_name' or 'project_id' or 'project_code' ",
            notes = "Demo request: {\r\n"
            		+ "    \"project_activity_status\": true,\r\n"
            		+ "    \"project_name\":\"IST\",\r\n"
            		+ "    \"project_id\": 53,\r\n"
            		+ "    \"project_code\":\"IST1234\"\r\n"
            		+ "}")

	@RequestMapping(value = "/get-filter-project", method = RequestMethod.POST)
    public AppResponse<Map<String, Object>> getProjectFilterList(@RequestBody Map<String, Object> model) {
        try {
            return AppResponse.build(HttpStatus.OK).body(projectService.getProjectFilterList(model));
        } catch (Exception ex) {
            return AppResponse.build(HttpStatus.INTERNAL_SERVER_ERROR).message(ex.getMessage());
        }
    }
	
	@ApiOperation(value = "Get project list for sync by 'date_range'",
            notes = "Demo request:  {\r\n"
            		+ "    \"date_range\":\"2023-04-14\"}")
	
	@RequestMapping(value = "/project-list-and-sync", method = RequestMethod.POST)
    public AppResponse<Map<String, Object>> getProjectWithDataSync(@RequestBody Map<String, Object> model) {
        try {
            return AppResponse.build(HttpStatus.OK).body(projectService.getProjectListAndSync(model));
        } catch (Exception ex) {
            return AppResponse.build(HttpStatus.INTERNAL_SERVER_ERROR).message(ex.getMessage());
        }
    }
	
	@ApiOperation(value = "Project member view by 'project_id'",
            notes = "Demo request:  {\r\n"
            		+ "    \"project_id\":\"44\"}")
	@RequestMapping(value = "/get-project-member_list", method = RequestMethod.POST)
    public AppResponse<Map<String, Object>> getProjectMemberList(@RequestBody Map<String, Object> model) {
        try {
            return AppResponse.build(HttpStatus.OK).body(projectService.getProjectMemberList(model));
        } catch (Exception ex) {
            return AppResponse.build(HttpStatus.INTERNAL_SERVER_ERROR).message(ex.getMessage());
        }
    }
}
