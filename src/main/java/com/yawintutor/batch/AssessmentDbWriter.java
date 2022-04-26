package com.yawintutor.batch;

import java.util.List;

import lombok.extern.log4j.Log4j2;
import org.springframework.batch.item.ItemWriter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.yawintutor.domain.secondary.Manager;
import com.yawintutor.domain.secondary.ManagerRepository;

@Component
@Log4j2
public class AssessmentDbWriter implements ItemWriter<Manager> {

    @Autowired
    ManagerRepository managerRepository ;

    @Override
    public void write(List<? extends Manager> list) throws Exception {
        for (Manager data : list) {
            log.info("Assessment Db Writer    : Writing data    : " + data.getId()+" : "+data.getName()+" : "+data.getSalary());
            managerRepository.save(data);
        }
    }
}

