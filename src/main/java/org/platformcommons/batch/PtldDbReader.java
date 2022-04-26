package org.platformcommons.batch;

import java.sql.ResultSet;
import java.sql.SQLException;

import javax.sql.DataSource;

import lombok.extern.log4j.Log4j2;
import org.springframework.batch.item.ItemReader;
import org.springframework.batch.item.database.JdbcCursorItemReader;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Component;

import org.platformcommons.domain.ptld.AssessmentPtld;

@Component
@Log4j2
public class PtldDbReader extends JdbcCursorItemReader<AssessmentPtld> implements ItemReader<AssessmentPtld>{

    public PtldDbReader(@Autowired DataSource primaryDataSource) {
        log.info("Ptld Db Reader : Reading Data");
        setDataSource(primaryDataSource);
        setSql("SELECT id, name, salary FROM employee");
        setFetchSize(100);
        setRowMapper(new EmployeeRowMapper());
    }

    public class EmployeeRowMapper implements RowMapper<AssessmentPtld> {
        @Override
        public AssessmentPtld mapRow(ResultSet rs, int rowNum) throws SQLException {
            log.info("Mapping result set to source table object");
            AssessmentPtld assessmentPtld = new AssessmentPtld();
            assessmentPtld.setId(rs.getInt("id"));
            assessmentPtld.setName(rs.getString("name"));
            assessmentPtld.setSalary(rs.getInt("salary"));
            return assessmentPtld;
        }
    }
}
