package org.jarivm.relationGraph.domains;

import org.neo4j.ogm.annotation.GraphId;
import org.neo4j.ogm.annotation.NodeEntity;
import org.neo4j.ogm.annotation.typeconversion.DateString;

import java.util.Date;

/**
 * @author Jari Van Melckebeke
 * @since 20.09.16
 */
@NodeEntity
public class Employee {
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

    public void setId(Long id) {
        this.id = id;
    }

    public Long getExperience() {
        return experience;
    }

    public void setExperience(Long experience) {
        this.experience = experience;
    }

    public Long getAge() {
        return age;
    }

    public void setAge(Long age) {
        this.age = age;
    }

    public Date getDateOfRetirement() {
        return dateOfRetirement;
    }

    public void setDateOfRetirement(Date dateOfRetirement) {
        this.dateOfRetirement = dateOfRetirement;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    @GraphId
    private Long id;

    private String name;
    private String surname;
    private String email;

    private Long experience;
    private Long age;

    @DateString(value = "yyyy-MM-dd")
    private Date dateOfBirth;
    @DateString(value = "yyyy-MM-dd")
    private Date dateOfAssigment;
    @DateString(value = "yyyy-MM-dd")
    private Date dateOfRetirement;

    public Employee() {

    }

    public Employee(String name, String surname) {
        this.name = name;
        this.surname = surname;
    }

    //structure: first_name, last_name,email, Gender, start_date_of_work, Age, experience,
    // total_efficiency, start_date_project,end_date_project, on_time, role_on_project,
    // n_of_project, deathline, name_of_project, version, cost, client_experience, client_name
    public Employee(String name, String surname, String email, Date dateOfBirth, Long age, Date dateOfAssigment) {
        this.name = name;
        this.surname = surname;
        this.email = email;
        this.age = age;
        this.dateOfBirth = dateOfBirth;
        this.dateOfAssigment = dateOfAssigment;
    }

    @Override
    public String toString() {
        return "Employee{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", surname='" + surname + '\'' +
                ", experience=" + experience +
                ", age=" + age +
                ", dateOfBirth=" + dateOfBirth +
                ", dateOfAssigment=" + dateOfAssigment +
                ", dateOfRetirement=" + dateOfRetirement +
                '}';
    }
}
