package com.library.system.entity;

public class Book {
    private String id;
    private String title;
    private String author;
    private String isbn;
    private int inventory;

    public Book() {}

    public Book(String id, String title, String author, String isbn, int inventory) {
        this.id = id;
        this.title = title;
        this.author = author;
        this.isbn = isbn;
        this.inventory = inventory;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return title;
    }

    public void setName(String name) {
        this.title = name;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public int getInventory() {
        return inventory;
    }

    public void setInventory(int inventory) {
        this.inventory = inventory;
    }

    public String getIsbn() {
        return isbn;
    }

    public void setIsbn(String isbn) {
        this.isbn = isbn;
    }

    @Override
    public String toString() {
        return "Book{" +
                "id='" + id + '\'' +
                ", name='" + title + '\'' +
                ", author='" + author + '\'' +
                ", isbn='" + isbn + '\'' +
                ", inventory=" + inventory +
                '}';
    }
}
