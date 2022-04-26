package org.platformcommons.batch;

import lombok.extern.log4j.Log4j2;
import org.platformcommons.domain.assessmentdb.Assessment;
import org.springframework.batch.item.ItemProcessor;
import org.springframework.stereotype.Component;

import org.platformcommons.domain.ptld.AssessmentPtld;

@Component
@Log4j2
public class AssessmentDataProcessor implements ItemProcessor<AssessmentPtld, Assessment> {

    @Override
    public Assessment process(AssessmentPtld emp) throws Exception {
        log.info("Assessment Data Processor : Processing data : "+emp.toString());
        Assessment assessment = new Assessment();
        //assessment.setId(emp.getId());
        assessment.setFarmer_name(emp.getFarmer_name().toUpperCase());
        assessment.setOwning_entity_id(emp.getOwning_entity_id());
        return assessment;
    }
}
