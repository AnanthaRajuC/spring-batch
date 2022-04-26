package org.platformcommons.batch;

import java.util.List;

import lombok.extern.log4j.Log4j2;
import org.platformcommons.domain.assessmentdb.AssessmentRepository;
import org.springframework.batch.item.ItemWriter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import org.platformcommons.domain.assessmentdb.Assessment;

@Component
@Log4j2
public class AssessmentDbWriter implements ItemWriter<Assessment> {

    @Autowired
    AssessmentRepository assessmentRepository;

    @Override
    public void write(List<? extends Assessment> list) throws Exception {
        for (Assessment data : list) {
            log.info("Assessment Db Writer    : Writing data    : " + data.getId()+" : "+data.getName()+" : "+data.getSalary());
            assessmentRepository.save(data);
        }
    }
}

