package org.module_11.entity;

import jakarta.persistence.*;
import lombok.Data;

import java.sql.Date;

@Data
@Entity
@Table(name="Ticket")
public class Ticket {
    @Id
    @Column(name = "id", nullable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(name = "created_at", insertable = false, updatable = false)
    private Date createdAt;

    @ManyToOne(fetch = FetchType.EAGER, targetEntity = Client.class)
    @JoinColumn(name = "client_id",  referencedColumnName = "id", nullable = false)
    private Client clientId;

    @ManyToOne(fetch = FetchType.EAGER, targetEntity = Planet.class)
    @JoinColumn(name = "from_planet_id", referencedColumnName = "id", nullable = false)
    private Planet from;

    @ManyToOne(fetch = FetchType.EAGER, targetEntity = Planet.class)
    @JoinColumn(name = "to_planet_id",referencedColumnName = "id", nullable = false)
    private Planet to;

    public Ticket() {
    }

    public Ticket(long id, Date createdAt, Client clientId, org.module_11.entity.Planet fromPlanet, org.module_11.entity.Planet toPlanet) {
        this.id = id;
        this.createdAt = createdAt;
        this.clientId = clientId;
        this.from = fromPlanet;
        this.to = toPlanet;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public Date getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Date createdAt) {
        this.createdAt = createdAt;
    }

    public Client getClientId() {
        return clientId;
    }

    public void setClientId(Client clientId) {
        this.clientId = clientId;
    }

    public Planet getFrom() {
        return from;
    }

    public void setFrom(Planet from) {
        this.from = from;
    }

    public Planet getTo() {
        return to;
    }

    public void setTo(Planet to) {
        this.to = to;
    }

    @Override
    public String toString() {
        return "Ticket{" +
                "id=" + id +
                ", createdAt=" + createdAt +
                ", clientId=" + clientId +
                ", fromPlanet=" + from +
                ", toPlanet=" + to +
                '}';
    }
}