/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ngo.friendship.projecus.repo;

import java.util.List;
import java.util.Map;
import java.util.UUID;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

@Repository
public class StakeholderRepo {

    public final static String PARAM_VENDORS = "VENDORS";

    @Autowired
    JdbcTemplate db;

    public List<Map<String, Object>> entityContactList(UUID sys_entity_id, String sys_entity_code, String cstatus) {    	
        StringBuilder sql = new StringBuilder();
        sql.append(" select cc.cms_contact_id, ccd.title, ccd.first_name, ccd.last_name,ccd.job_title,  ");
        sql.append(" ccd.job_title,ccd.mobile_self, ccd.mobile_urgent, ccd.address, cc.email_1  ");
        if(sys_entity_code.equals(PARAM_VENDORS)){
            sql.append(" ,ccd.supplier_types,ccd.vendor_criteria, ccd.product_category, ccd.contact_person  ");
        }
        sql.append(" from cms.cms_contact_dtl ccd ");
        sql.append(" join cms.cms_contact cc on cc.cms_contact_id = ccd.cms_contact_id ");
        sql.append(" where ccd.status = '").append(cstatus).append("' ");
        sql.append(" and ccd.sys_entity_id = ? ");
        sql.append(" group by cc.cms_contact_id,ccd.cms_contact_dtl_id ");
        sql.append(" order by ccd.first_name, ccd.last_name asc ");
        return db.queryForList(sql.toString(), sys_entity_id);
    }  

}
