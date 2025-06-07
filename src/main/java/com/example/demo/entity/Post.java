package com.example.demo.entity;

import java.time.LocalDateTime;

public class Post {
    private Integer id;
    private String title;
    private String kategorie;
    private String content;
    private LocalDateTime createdAt;  // DB의 created_at 컬럼과 매핑

    // 기본 생성자
    public Post() {}

    // 전체 생성자 (필요 시)
    public Post(Integer id, String title, String kategorie, String content, LocalDateTime createdAt) {
        this.id = id;
        this.title = title;
        this.kategorie = kategorie;
        this.content = content;
        this.createdAt = createdAt;
    }

    // getter / setter
    public Integer getId() {
        return id;
    }
    public void setId(Integer id) {
        this.id = id;
    }
    public String getTitle() {
        return title;
    }
    public void setTitle(String title) {
        this.title = title;
    }
    public String getKategorie() {
        return kategorie;
    }
    public void setKategorie(String kategorie) {
        this.kategorie = kategorie;
    }
    public String getContent() {
        return content;
    }
    public void setContent(String content) {
        this.content = content;
    }
    public LocalDateTime getCreatedAt() {
        return createdAt;
    }
    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }
}
