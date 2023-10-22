package pl.davidduke.library.models;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;

public class Book {
    private int id;

    @NotEmpty(message = "The title field cannot be empty!")
    @Size(min = 2, max = 100, message = "The length of the title must be from 2 to 100 characters!")
    private String title;

    @NotEmpty(message = "The author field cannot be empty!")
    @Size(min = 2, max = 100, message = "The length of the author must be from 2 to 100 characters!")
    private String author;

    @Min(value = 1500, message = "Year of publication must be greater than 1500!")
    private int publicationYear;

    public Book() {
        super();
    }


    public Book(int id, String title, String author, int publicationYear) {
        this.id = id;
        this.title = title;
        this.author = author;
        this.publicationYear = publicationYear;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
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

    public int getPublicationYear() {
        return publicationYear;
    }

    public void setPublicationYear(int publicationYear) {
        this.publicationYear = publicationYear;
    }
}
