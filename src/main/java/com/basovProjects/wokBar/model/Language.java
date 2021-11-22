package com.basovProjects.wokBar.model;


import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "languages")
public class Language {

    @Id
    private String id;

    @Column(name = "name")
    private String name;

    public Language() {
    }

    public Language(String name) {
        this.name = name;
    }

    public String getId() {
        return id;
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

    @Override
    public String toString() {
        return "Language{" +
                "id='" + id + '\'' +
                ", name='" + name + '\'' +
                '}';
    }
}
