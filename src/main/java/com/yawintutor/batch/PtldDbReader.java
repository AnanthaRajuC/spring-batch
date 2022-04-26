package com.yawintutor.batch;

import java.sql.ResultSet;
import java.sql.SQLException;

import javax.sql.DataSource;

import lombok.extern.log4j.Log4j2;
import org.springframework.batch.item.ItemReader;
import org.springframework.batch.item.database.JdbcCursorItemReader;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Component;

import com.yawintutor.domain.primary.Employee;

@Component
@Log4j2
public class PtldDbReader extends JdbcCursorItemReader<Employee> implements ItemReader<Employee>{

    public PtldDbReader(@Autowired DataSource primaryDataSource) {
        log.info("Ptld Db Reader : Reading Data");
        setDataSource(primaryDataSource);
        setSql("SELECT id, name, salary FROM employee");
        setFetchSize(100);
        setRowMapper(new EmployeeRowMapper());
    }

    public class EmployeeRowMapper implements RowMapper<Employee> {
        @Override
        public Employee mapRow(ResultSet rs, int rowNum) throws SQLException {
            log.info("Mapping result set to source table object");
            Employee employee  = new Employee();
            employee.setId(rs.getInt("id"));
            employee.setName(rs.getString("name"));
            employee.setSalary(rs.getInt("salary"));
            return employee;
        }
    }
}
