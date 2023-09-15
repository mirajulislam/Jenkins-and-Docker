package ngo.friendship.projecus.config;

import javax.sql.DataSource;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.jdbc.core.JdbcTemplate;
/**
 *
 * @author Zakaria Imtiaz
 */

@Configuration
public class DBConfig {

    @Bean(name = "pisDB")
    @Primary
    @ConfigurationProperties(prefix = "msq.pis")
    public DataSource mysqlSpsDataSource() {
        return DataSourceBuilder.create().build();
    }

    @Bean(name = "pisTemplete")
    public JdbcTemplate mysqlSpsJdbcTemplate(@Qualifier("pisDB") DataSource dsCustomMySQL) {
        return new JdbcTemplate(dsCustomMySQL);
    }
}
