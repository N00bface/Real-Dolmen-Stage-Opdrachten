package org.jarivm.relationGraph.domains;

import org.neo4j.ogm.annotation.GraphId;
import org.neo4j.ogm.annotation.NodeEntity;
import org.neo4j.ogm.annotation.Relationship;
import org.neo4j.ogm.annotation.typeconversion.DateString;
import org.springframework.stereotype.Component;

import java.util.Date;

/**
 * @author Jari Van Melckebeke
 * @since 20.09.16
 */
@NodeEntity(label = "Employee")
@Component
public class Employee {
    public Character getGender() {
        return gender;
    }

    public void setGender(Character gender) {
        this.gender = gender;
    }

    public Long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

//    public Date getStartDateOfWork() {
//        return startDateOfWork;
//    }
//
//    public void setStartDateOfWork(Date startDateOfWork) {
//        this.startDateOfWork = startDateOfWork;
//    }

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

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public WorkedOn getWorkedOn() {
        return workedOn;
    }

    public void setWorkedOn(WorkedOn workedOn) {
        this.workedOn = workedOn;
    }

    public Date getStartDateOfWork() {
        return startDateOfWork;
    }

    public void setStartDateOfWork(Date startDateOfWork) {
        this.startDateOfWork = startDateOfWork;
    }

    @GraphId
    private Long id;

    private String surname;
    private String name;
    private String email;
    private Character gender;

    private Long experience;
    private Long age;
    @DateString(value = "MM/dd/yyyy")
    private Date startDateOfWork;

    @Relationship(type = "WorkedOn")
    private WorkedOn workedOn;

    public Employee() {

    }

    public Employee(String name, String first_name) {
        this.surname = name;
        this.name = first_name;
    }

    //structure: name, surname,email, Gender, startDateOfWork, Age, experience,
    // total_efficiency, start_date_project,end_date_project, on_time, role_on_project,
    // n_of_project, deathline, name_of_project, version, cost, client_experience, client_name
    public Employee(String surname, String name, String email, Long age) {
        this.surname = surname;
        this.name = name;
        this.email = email;
        this.age = age;
//        this.startDateOfWork = startDateOfWork;
    }

    @Override
    public String toString() {
        return "Employee{" +
                "id=" + id +
                ", last_name='" + surname + '\'' +
                ", first_name='" + name + '\'' +
                ", experience=" + experience +
                ", age=" + age +
//                ", start_date_of_work=" + startDateOfWork +
                '}';
    }
}
