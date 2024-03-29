package org.module_11.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;

@Data
@Entity
@Table(name="Planet")
public class Planet {
    @Id
    private String id;
    @Column
    private String name;

    public Planet(String id, String name) {
        this.id = id;
        this.name = name;
    }

    public Planet() {
    }

    public String getId() {
        return id;
    }

    @Override
    public String toString() {
        return "Planet{" +
                "id='" + id + '\'' +
                ", name='" + name + '\'' +
                '}';
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}