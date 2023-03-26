package com.labwork01.app.book.model;

import com.labwork01.app.author.model.Author;
import com.labwork01.app.genre.model.Genre;
import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Entity
public class Book {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    @Column(nullable = false)
    private String name;
    @Column(nullable = false)
    private String description;
    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(name = "books_genres",
    joinColumns = @JoinColumn(name = "book_id"),
    inverseJoinColumns = @JoinColumn(name = "genre_id"))
    private List<Genre> genres;
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "author_id", nullable = false)
    private Author author;
    public Book() {
    }

    public Book(String name, String description) {
        this.name = name;
        this.description = description;
    }
    public void setAuthor(Author author){
        if(this.author != null){
            this.author.getBooks().remove(this);
        }
        this.author = author;
        if (!author.getBooks().contains(this)) {
            author.getBooks().add(this);
        }
    }
    public Author getAuthor() {
        return author;
    }
    public List<Genre> getGenres() {
        return genres;
    }

    public void addGenre(Genre genre) {
        if (genres == null){
            genres = new ArrayList<>();
        }
        this.genres.add(genre);
        if (!genre.getBooks().contains(this)) {
            genre.getBooks().add(this);
        }
    }
    public void removeGenre(Genre genre){
        if(genres != null){
            genres.remove(genre);
        }
    }
    public Long getId() {
        return id;
    }
    public String[] getGenresName(){
        String[] result = new String[genres.size()];
        for(int i = 0; i < genres.size(); ++i){
            result[i] = genres.get(i).getName();
        }
        return result;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Book book = (Book) o;
        return Objects.equals(id, book.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }

    @Override
    public String toString() {
        return "Book{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", description='" + description + '\'' +
                ", author='" + author.toString() + '\'' +
                ", genres='" + String.join(", ",getGenresName()) + '\'' +
                '}';
    }
}
