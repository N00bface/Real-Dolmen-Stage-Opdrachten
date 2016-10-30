package org.jarivm.relationGraph.domains;

import org.neo4j.ogm.annotation.GraphId;
import org.neo4j.ogm.annotation.NodeEntity;
import org.neo4j.ogm.annotation.Relationship;
import org.neo4j.ogm.annotation.typeconversion.DateString;
import org.neo4j.ogm.utils.RelationshipUtils;
import org.springframework.expression.spel.ast.FloatLiteral;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Set;

/**
 * @author Jari Van Melckebeke
 * @since 20.09.16
 */
@NodeEntity
public class Project {

    @GraphId
    private Long id;

    private Float cost;
    private Float scoreFromClient;
    private String name;
    private String version;
    private String language;

    @DateString(value = "yyyy-MM-dd")
    private Date dateStarted;
    @DateString(value = "yyyy-MM-dd")
    private Date dateFinished;

    @Relationship(direction = Relationship.INCOMING, type = "WorkedOn")
    private Set<WorkedOn> workedOn;

    @Relationship(direction = Relationship.INCOMING, type = "Issued")
    private Client client;

    public Project(String name) {
        this.name = name;
    }


    public Project() {
    }

    public Set<WorkedOn> getWorkedOn() {
        return workedOn;
    }

    public void setWorkedOn(Set<WorkedOn> workedOn) {
        this.workedOn = workedOn;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setCost(Float cost) {
        this.cost = cost;
    }

    public Float getScoreFromClient() {
        return scoreFromClient;
    }

    public void setScoreFromClient(Float scoreFromClient) {
        this.scoreFromClient = scoreFromClient;
    }

    public String getVersion() {
        return version;
    }

    public void setVersion(String version) {
        this.version = version;
    }

    public Long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public Float getCost() {
        return cost;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Date getDateStarted() {
        return dateStarted;
    }

    public void setDateStarted(Date dateStarted) {
        this.dateStarted = dateStarted;
    }

    public Date getDateFinished() {
        return dateFinished;
    }

    public void setDateFinished(Date dateFinished) {
        this.dateFinished = dateFinished;
    }

    public String getLanguage() {
        return language;
    }

    public void setLanguage(String language) {
        this.language = language;
    }

    public Client getClient() {
        return client;
    }

    public void setClient(Client client) {
        this.client = client;
    }

    @Override
    public String toString() {
        return "Project{" +
                "id=" + id +
                ", cost=" + cost +
                ", scoreFromClient=" + scoreFromClient +
                ", name='" + name + '\'' +
                ", version='" + version + '\'' +
                ", language='" + language + '\'' +
                ", dateStarted=" + dateStarted +
                ", dateFinished=" + dateFinished +
                ", workedOn=" + workedOn +
                ", client=" + client +
                '}';
    }
}
