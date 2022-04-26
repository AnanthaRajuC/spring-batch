package org.platformcommons.domain.secondary;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;

@Entity
@Table(name = "manager")
@Getter
@Setter
@ToString
public class Manager {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    private int id;
    private String name;
    private int salary;
}