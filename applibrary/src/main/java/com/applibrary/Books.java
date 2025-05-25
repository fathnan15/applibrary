package com.applibrary;

public class Books {
    private String title;
    private String author;
    private String Category;
    private boolean isAvailable;

    public Books(String title, String author, String category, boolean isAvailable) {
        this.title = title;
        this.author = author;
        this.Category = category;
        this.isAvailable = true;
    }

    public String getTitle() {
        return title;
    }

    public String getAuthor() {
        return author;
    }

    public String getCategory() {
        return Category;
    }

    public boolean isAvailable() {
        return isAvailable;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public void setCategory(String category) {
        this.Category = category;
    }

    public void setAvailable(boolean available) {
        this.isAvailable = available;
    }
}
