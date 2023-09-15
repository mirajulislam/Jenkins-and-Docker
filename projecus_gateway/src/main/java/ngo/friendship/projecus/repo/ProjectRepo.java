package ngo.friendship.projecus.repo;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

@Repository
public class ProjectRepo {

    @Autowired
    NamedParameterJdbcTemplate db;
    @Autowired
    JdbcTemplate jdb;

    public List<Map<String, Object>> getFIlterProjectList(Map<String, Object> model) {
        StringBuilder sb = new StringBuilder();
        sb.append(" select pp.* ");
        sb.append("  , ppte.start_date ");
        sb.append("  , ppte1.end_date ");
        sb.append("  from pis_project pp  ");
        sb.append("  inner join ( select project_id, start_date as start_date from pis_project_time_extention ppte  ");
        sb.append("               where  sl = (select min(sl) from pis_project_time_extention where project_id =ppte.project_id)  ");
        sb.append("             ) ppte on ppte.project_id = pp.project_id  ");
        sb.append("  inner join (  select project_id, end_date as end_date   from pis_project_time_extention ppte1   ");
        sb.append("                where   sl = (select max(sl) from pis_project_time_extention where project_id =ppte1.project_id)   ");
        sb.append("             ) ppte1 on ppte1.project_id = pp.project_id ");
        sb.append("  where true ");
        if (model.get("project_activity_status") != null) {
            sb.append("and pp.active = :project_activity_status ");
        }
        if (model.get("project_code") != null) {
            sb.append("and pp.project_code = :project_code ");
        }
        if (model.get("project_id") != null) {
            sb.append("and pp.project_id = :project_id ");
        }
        if (model.get("project_name") != null) {
            sb.append("and pp.project_name = :project_name ");
        }   
        
        sb.append(" order by pp.project_sector_name asc, ppte1.end_date asc ");
        return db.queryForList(sb.toString(), model);
    }
    
    public List<Map<String, Object>> getProjectMemberList(Integer projectId) {
        StringBuilder sb = new StringBuilder();
        sb.append(" select DISTINCT pm.stakeholder_category_id catagory_id,pm.stakeholder_id employee_id"
        		+ ", pm.employee_id employee_office_id,pm.employee_name "
        		+ ", se.name catagory_name from pis_project_member pm "
        		+ "inner join system_entity se on se.id = pm.stakeholder_category_id "
        		+ "where project_id = ? and  pm.active=true ");
        return jdb.queryForList(sb.toString(), projectId);
    }

	public void updateProjectBySync(Map<String, Object> model) {
		StringBuilder sb = new StringBuilder();
        sb.append(" Update pis_project set  ");
        sb.append(" is_sync= true ");
        sb.append(" where project_id= :project_id ");
        db.update(sb.toString(), model);
	}

	public List<Map<String, Object>> getSyncProjectList(Map<String, Object> model) {
		StringBuilder sb = new StringBuilder();
		sb.append(" select pp.*  ");
		sb.append(" , ppte.start_date  ");
		sb.append(" , ppte1.end_date  ");
		sb.append(" , GROUP_CONCAT(pd.donor_name SEPARATOR ' & ') AS donor_name ");
		sb.append(" from pis_project pp   ");
		sb.append(" inner join ( select project_id, start_date as start_date from pis_project_time_extention ppte   ");
		sb.append("              where  sl = (select min(sl) from pis_project_time_extention where project_id =ppte.project_id)   ");
		sb.append("            ) ppte on ppte.project_id = pp.project_id   ");
		sb.append(" inner join (  select project_id, end_date as end_date   from pis_project_time_extention ppte1    ");
		sb.append("               where   sl = (select max(sl) from pis_project_time_extention where project_id =ppte1.project_id)    ");
		sb.append("            ) ppte1 on ppte1.project_id = pp.project_id  ");
		sb.append(" JOIN pis_project_donor ppd on ppd.project_id = pp.project_id  ");
		sb.append(" JOIN pis_donor pd on pd.donor_id = ppd.donor_id  ");
		sb.append(" where true  ");
		
		if (model.get("project_activity_status") != null) {
            sb.append("and pp.active = :project_activity_status ");
        }
        if (model.get("project_code") != null) {
            sb.append("and pp.project_code = :project_code ");
        }
        if (model.get("project_id") != null) {
            sb.append("and pp.project_id = :project_id ");
        }
        if (model.get("project_name") != null) {
            sb.append("and pp.project_name = :project_name ");
        }      
        if (model.get("date_range") != null) {        	
            sb.append(" and pp.created_on > :date_range or pp.updated_on >:date_range");
        } 
		sb.append(" GROUP by pp.project_id ");
		
		return db.queryForList(sb.toString(), model);
	}
}
