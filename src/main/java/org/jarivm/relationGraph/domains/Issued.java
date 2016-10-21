package org.jarivm.relationGraph.domains;

import org.neo4j.ogm.annotation.EndNode;
import org.neo4j.ogm.annotation.GraphId;
import org.neo4j.ogm.annotation.RelationshipEntity;
import org.neo4j.ogm.annotation.StartNode;
import org.neo4j.ogm.annotation.typeconversion.DateString;

import java.util.Date;

/**
 * @author Jari Van Melckebeke
 * @since 30.09.16
 */
@RelationshipEntity(type = "Issued")
public class Issued {
    public Issued(Client client, Project project, Date dateGiven, Long nProjectForClient) {
        this.client = client;
        this.project = project;
        this.dateGiven = dateGiven;
        this.nProjectForClient = nProjectForClient;
    }

    public Issued(Client client, Project project) {
        this.client = client;
        this.project = project;
    }

    public Issued() {

    }

    @GraphId
    private Long id;

    @StartNode
    private Client client;
    @EndNode
    private Project project;

    @DateString(value = "yyyy-MM-dd")
    private Date dateGiven;

    private Long nProjectForClient;

    public Client getClient() {
        return client;
    }

    public void setClient(Client client) {
        this.client = client;
    }

    public Project getProject() {
        return project;
    }

    public void setProject(Project project) {
        this.project = project;
    }

    public Date getDateGiven() {
        return dateGiven;
    }

    public void setDateGiven(Date dateGiven) {
        this.dateGiven = dateGiven;
    }

    public Long getnProjectForClient() {
        return nProjectForClient;
    }

    public void setnProjectForClient(Long nProjectForClient) {
        this.nProjectForClient = nProjectForClient;
    }

    @Override
    public String toString() {
        return "Issued{" +
                "client=" + client +
                ", project=" + project +
                ", dateGiven=" + dateGiven +
                ", nProjectForClient=" + nProjectForClient +
                '}';
    }
}
