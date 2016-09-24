package org.jarivm.relationGraph.objects.domains;

import org.neo4j.ogm.annotation.GraphId;
import org.neo4j.ogm.annotation.NodeEntity;

import java.util.Date;

/**
 * @author Jari Van Melckebeke
 * @since 20.09.16
 */
@NodeEntity
public class Employee {
    @GraphId
    private Long id;

    public Long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public Date getDateOfBirth() {
        return dateOfBirth;
    }

    public void setDateOfBirth(Date dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
    }

    public Date getDateOfAssigment() {
        return dateOfAssigment;
    }

    public void setDateOfAssigment(Date dateOfAssigment) {
        this.dateOfAssigment = dateOfAssigment;
    }

    private String name;
    private String surname;

    private Date dateOfBirth;
    private Date dateOfAssigment;

    public Employee() {

    }

    public Employee(String name, String surname) {
        this.name = name;
        this.surname = surname;
    }






}
