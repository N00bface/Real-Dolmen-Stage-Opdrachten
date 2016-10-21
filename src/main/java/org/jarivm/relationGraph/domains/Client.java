package org.jarivm.relationGraph.domains;

import org.neo4j.ogm.annotation.GraphId;
import org.neo4j.ogm.annotation.NodeEntity;
import org.neo4j.ogm.annotation.Property;
import org.neo4j.ogm.annotation.Relationship;
import org.neo4j.ogm.annotation.typeconversion.DateString;
import org.springframework.stereotype.Component;

import java.util.Date;
import java.util.LinkedHashMap;

/**
 * @author Jari Van Melckebeke
 * @since 20.09.16
 */
@NodeEntity(label = "Employee")
@Component
public class Client {
    @GraphId
    private Long id;

    @Relationship(type = "IS_SECTOR_FOR", direction = Relationship.INCOMING)
    private Sector sector;
    private String name;
    private Long experienceWithRealDolmen;
    private Double avgScoreForProject;

    @DateString(value = "yyyy-MM-dd")
    private Date dateOfFounding;
    @DateString(value = "yyyy-MM-dd")
    private Date dateOfFirstCooperation;

    public Client(String name) {
        this.name = name;
    }

    public Client() {

    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Sector getSector() {
        return sector;
    }

    public void setSector(Sector sector) {
        this.sector = sector;
    }

    public Long getExperienceWithRealDolmen() {
        return experienceWithRealDolmen;
    }

    public void setExperienceWithRealDolmen(Long experienceWithRealDolmen) {
        this.experienceWithRealDolmen = experienceWithRealDolmen;
    }

    public Double getAvgScoreForProject() {
        return avgScoreForProject;
    }

    public void setAvgScoreForProject(Double avgScoreForProject) {
        this.avgScoreForProject = avgScoreForProject;
    }

    public Date getDateOfFounding() {
        return dateOfFounding;
    }

    public void setDateOfFounding(Date dateOfFounding) {
        this.dateOfFounding = dateOfFounding;
    }

    public Date getDateOfFirstCooperation() {
        return dateOfFirstCooperation;
    }

    public void setDateOfFirstCooperation(Date dateOfFirstCooperation) {
        this.dateOfFirstCooperation = dateOfFirstCooperation;
    }

    @Override
    public String toString() {
        return "Client{" +
                "id=" + id +
                ", sector=" + sector +
                ", name='" + name + '\'' +
                ", experienceWithRealDolmen=" + experienceWithRealDolmen +
                ", avgScoreForProject=" + avgScoreForProject +
                ", dateOfFounding=" + dateOfFounding +
                ", dateOfFirstCooperation=" + dateOfFirstCooperation +
                '}';
    }
}
