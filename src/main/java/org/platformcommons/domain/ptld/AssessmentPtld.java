package org.platformcommons.domain.ptld;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;

@Entity
@Table(name = "assessment_ptld")
@Getter
@Setter
@ToString
public class AssessmentPtld {
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Id
    private int id;
    private String farmer_name;
    private int owning_entity_id;
}