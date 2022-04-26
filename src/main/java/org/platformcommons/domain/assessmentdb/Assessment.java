package org.platformcommons.domain.assessmentdb;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;

@Entity
@Table(name = "manager")
@Getter
@Setter
@ToString
public class Assessment {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    private int id;
    private String name;
    private int salary;
}