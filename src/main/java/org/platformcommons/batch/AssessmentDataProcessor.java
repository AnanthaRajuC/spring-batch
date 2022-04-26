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
    public Assessment process(AssessmentPtld assessmentPtld) throws Exception {
        log.info("Assessment Data Processor : Processing data : "+assessmentPtld.toString());

        Assessment assessment = new Assessment();
        assessment.setFarmerName(assessmentPtld.getFarmerName().toUpperCase());
        assessment.setOwningEntityId(assessmentPtld.getOwningEntityId());

        return assessment;
    }
}
