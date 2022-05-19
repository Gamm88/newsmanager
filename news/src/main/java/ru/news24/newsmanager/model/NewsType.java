package ru.news24.newsmanager.model;

import javax.persistence.*;

@Entity
@Table(name = "newstype")
public class NewsType {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "news_type_id")
    private long id;

    @Column(name = "name")
    private String name;

    @Column(name = "color")
    private String color;

    public NewsType() {

    }

    public NewsType(String name, String color) {
        this.name = name;
        this.color = color;
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

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

}
