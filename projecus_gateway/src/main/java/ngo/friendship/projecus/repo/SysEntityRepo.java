package ngo.friendship.projecus.repo;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

@Repository
public class SysEntityRepo{
    @Autowired
    NamedParameterJdbcTemplate nDB;
    
    @Autowired
    JdbcTemplate db;
    
    public List<Map<String, Object>> findAll() {
        StringBuilder sql = new StringBuilder();
        sql.append(" SELECT tc.sys_entity_id, tc.sys_entity_name, tc.sys_entity_code, tc.note, tc.active ");
        sql.append(" FROM sys_entity tc ");
        sql.append(" order by tc.sys_entity_name ASC ");
        return db.queryForList(sql.toString());
    }
    

    public Map<String, Object> findById(UUID id) {
        StringBuilder sql = new StringBuilder();
        sql.append(" SELECT tc.sys_entity_id, tc.sys_entity_name, tc.sys_entity_code, tc.note, tc.active ");
        sql.append(" FROM sys_entity tc ");
        sql.append(" where tc.sys_entity_id=:sys_entity_id ");
        Map<String, Object> params = new HashMap<>();
        params.put("sys_entity_id", id);
        return nDB.queryForMap(sql.toString(), params);
    }
    
    public Map<String, Object> findByName(String name) {
        StringBuilder sql = new StringBuilder();
        sql.append(" SELECT tc.sys_entity_id, tc.sys_entity_name, tc.sys_entity_code, tc.note, tc.active ");
        sql.append(" FROM sys_entity tc ");
        sql.append(" where tc.sys_entity_name=:sys_entity_name ");
        Map<String, Object> params = new HashMap<>();
        params.put("sys_entity_name", name);
        return nDB.queryForMap(sql.toString(), params);
    }
    
    public Map<String, Object> findByCode(String code) {
        StringBuilder sql = new StringBuilder();
        sql.append(" SELECT tc.sys_entity_id, tc.sys_entity_name, tc.sys_entity_code, tc.note, tc.active ");
        sql.append(" FROM cms.sys_entity tc ");
        sql.append(" where tc.sys_entity_code=:sys_entity_code ");
        Map<String, Object> params = new HashMap<>();
        params.put("sys_entity_code", code);
        return nDB.queryForMap(sql.toString(), params);
    }
}
