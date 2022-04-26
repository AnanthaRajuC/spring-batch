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

    public PtldDbReader(@Autowired DataSource ptldDataSource) {
        log.info("Ptld Db Reader : Reading Data");
        setDataSource(ptldDataSource);
        setSql("SELECT id, farmer_name, owning_entity_id FROM assessment_ptld");
        setFetchSize(100);
        setRowMapper(new PtldAssessmentRowMapper());
    }

    public class PtldAssessmentRowMapper implements RowMapper<AssessmentPtld> {
        @Override
        public AssessmentPtld mapRow(ResultSet rs, int rowNum) throws SQLException {
            log.info("Mapping result set to source table object");
            AssessmentPtld assessmentPtld = new AssessmentPtld();
            assessmentPtld.setId(rs.getInt("id"));
            assessmentPtld.setFarmerName(rs.getString("farmer_name"));
            assessmentPtld.setOwningEntityId(rs.getInt("owning_entity_id"));
            return assessmentPtld;
        }
    }
}
