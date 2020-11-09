/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entity;

import java.io.Serializable;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

/**
 *
 * @author pupil
 */
@Entity
public class Book implements Serializable{
    @Id
    @GeneratedValue (strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private String author;
    private Integer publishedYear;
    private String isbn;

    public Book() {
    }

    public Book(Long id, String name, String author, Integer publishedYear, String isbn) {
        this.name = name;
        this.author = author;
        this.publishedYear = publishedYear;
        this.isbn = isbn;
    }
    public Book(String name, String author, String publishedYear) {
        this.name = name;
        this.author = author;
        setPublishedYear(publishedYear);
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public Integer getPublishedYear() {
        return publishedYear;
    }

    public void setPublishedYear(Integer publishedYear) {
        this.publishedYear = publishedYear;
    }
    public void setPublishedYear(String publishedYear) {
        try {
            int publishedYearInt = Integer.parseInt(publishedYear);
            this.publishedYear = publishedYearInt;
            System.out.println("Строка "+publishedYear+" успешно преобразована в число.");
        } catch (Exception e) {
            System.out.println("Введены не цифры. Поле не изменено");
        }
        
    }
    public String getIsbn() {
        return isbn;
    }

    public void setIsbn(String isbn) {
        this.isbn = isbn;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
    

    @Override
    public String toString() {
        return "Book{" 
                + "name=" + name 
                + ", author=" + author 
                + ", publishedYear=" + publishedYear 
                + '}';
    }
}