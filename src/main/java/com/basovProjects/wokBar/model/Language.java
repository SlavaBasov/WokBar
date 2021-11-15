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

    @Column(name = "is_default_locale")
    private Boolean defaultLocal;

    public Language() {
    }

    public Language(String name) {
        this.name = name;
    }

    public Language(String name, Boolean defaultLocal) {
        this.name = name;
        this.defaultLocal = defaultLocal;
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

    public Boolean isDefaultLocal() {
        return defaultLocal;
    }

    public void setDefaultLocal(Boolean defaultLocal) {
        this.defaultLocal = defaultLocal;
    }

    @Override
    public String toString() {
        return "Language{" +
                "id='" + id + '\'' +
                ", name='" + name + '\'' +
                ", isDefaultLocal=" + defaultLocal +
                '}';
    }
}
