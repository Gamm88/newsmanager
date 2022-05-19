package ru.news24.newsmanager.model;

import javax.persistence.*;

@Entity
@Table(name = "news")
public class News {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "news_id")
    private long id;

    @Column(name = "name")
    private String name;

    @Column(name = "short_description")
    private String shortDescription;

    @Column(name = "description")
    private String description;

    @Column(name = "id_news_type")
    private Long idNewsType;

    @ManyToOne
    @JoinColumn(name = "news_type_id")
    private NewsType newsType;

    public News() {
    }

    public News(String name, String shortDescription, String description, Long idNewsType) {
        this.name = name;
        this.shortDescription = shortDescription;
        this.description = description;
        this.idNewsType = idNewsType;
    }

    public long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getShortDescription() {
        return shortDescription;
    }

    public void setShortDescription(String shortDescription) {
        this.shortDescription = shortDescription;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Long getIdNewsType() {
        return idNewsType;
    }

    public void setIdNewsType(Long idNewsType) {
        this.idNewsType = idNewsType;
    }

    public NewsType getNewsType() {
        return newsType;
    }

    public void setNewsType(NewsType newsType) {
        this.newsType = newsType;
    }
}
