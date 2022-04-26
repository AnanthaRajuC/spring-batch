package org.platformcommons.domain.ptld;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import lombok.experimental.FieldDefaults;

import javax.persistence.*;

@Entity
@Table(name = "assessment_ptld")
@Getter
@Setter
@ToString
@FieldDefaults(level= AccessLevel.PRIVATE)
public class AssessmentPtld {
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Id
    int id;

    @Column(name="farmer_name", nullable=true)
    String farmerName;

    @Column(name="owning_entity_id", nullable=true)
    int owningEntityId;
}