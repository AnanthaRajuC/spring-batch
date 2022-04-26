package org.platformcommons.domain.ptld;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;

@Entity
@Table(name = "employee")
@Getter
@Setter
@ToString
public class AssessmentPtld {
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Id
    private int id;
    private String name;
    private int salary;
}