package com.sergiocarvajal.prueba_back_iberpixel.Model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.PastOrPresent;

import java.time.LocalDate;

@Entity
@Table(name = "books")
public class Book {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    @NotNull(message = "Id cant be null")
    private Long id;

    @Column(name = "title", nullable = false, length = 200)
    @NotNull(message = "title cant be null")
    private String title;

    @Column(name = "author", nullable = false, length = 100)
    @NotNull(message = "Author's name cant be null")
    private String author;

    @Column(name = "is_read", nullable = false)
    @NotNull(message = "isRead can not be null")
    private Boolean isRead = false;

    @Column(name = "created_at", nullable = false)
    @PastOrPresent
    @NotNull(message = "createdAt cant be null")
    private LocalDate createdAt;

    public Long getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public Boolean getIsRead() {
        return isRead;
    }

    public void setIsRead(Boolean isRead) {
        this.isRead = isRead;
    }

    public LocalDate getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(LocalDate createdAt) {
        this.createdAt = createdAt;
    }

}