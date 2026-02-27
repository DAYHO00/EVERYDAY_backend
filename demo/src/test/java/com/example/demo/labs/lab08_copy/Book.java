package com.example.demo.labs.lab08_copy;

public class Book {
    
    private final String name;

    private final Author author;

    public Book(String name, Author author){
        this.name=name;
        this.author=author;
    }

    public Book shallowCopy(){
        return new Book(this.name, this.author);
    }

    public Book deepCopy(){
        Author copiedAuthor = new Author(this.author.getName());
        return new Book(this.name, copiedAuthor);
    }

    public void changeAuthorName(String newName){
        this.author.setName(newName);
    }

    public Author getAuthor(){
        return author;
    }
    @Override
    public String toString(){
        return "Book{name='" + name + "', author=" + author + "}";
    }
}
