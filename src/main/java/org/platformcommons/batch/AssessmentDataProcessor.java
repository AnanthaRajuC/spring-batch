package org.platformcommons.batch;

import lombok.extern.log4j.Log4j2;
import org.springframework.batch.item.ItemProcessor;
import org.springframework.stereotype.Component;

import org.platformcommons.domain.ptld.Employee;
import org.platformcommons.domain.assessmentdb.Manager;

@Component
@Log4j2
public class AssessmentDataProcessor implements ItemProcessor<Employee, Manager> {

    @Override
    public Manager process(Employee emp) throws Exception {
        log.info("Assessment Data Processor : Processing data : "+emp.toString());
        Manager manager = new Manager();
        //manager.setId(emp.getId());
        manager.setName(emp.getName().toUpperCase());
        manager.setSalary(emp.getSalary());
        return manager;
    }
}
