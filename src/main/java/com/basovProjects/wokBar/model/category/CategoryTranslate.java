package com.basovProjects.wokBar.model.category;

import com.basovProjects.wokBar.model.Language;

import javax.persistence.*;

@Entity
@Table(name = "category_translate")
public class CategoryTranslate {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "category_id")
    private Category category;

    @ManyToOne
    @JoinColumn(name = "language_id")
    private Language language;

    @Column(name = "name")
    private String name;

    public CategoryTranslate() {
    }

    public CategoryTranslate(Category category, Language language, String name) {
        this.category = category;
        this.language = language;
        this.name = name;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Category getCategory() {
        return category;
    }

    public void setCategory(Category category) {
        this.category = category;
    }

    public Language getLanguage() {
        return language;
    }

    public void setLanguage(Language language) {
        this.language = language;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "CategoryTranslate{" +
                "id=" + id +
                ", category=" + category +
                ", language=" + language +
                ", name='" + name + '\'' +
                '}';
    }
}
