/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entity;

import java.io.Serializable;
import java.util.Date;

/**
 *
 * @author Melnikov
 */
public class History implements Serializable{
    private Book book;
    private Reader reader;
    private Date giveOutDate;
    private Date returnDate;

    public History() {
    }

    public History(Book book, Reader reader, Date giveOutDate, Date returnDate) {
        this.book = book;
        this.reader = reader;
        this.giveOutDate = giveOutDate;
        this.returnDate = returnDate;
    }

    public Date getReturnDate() {
        return returnDate;
    }

    public void setReturnDate(Date returnDate) {
        this.returnDate = returnDate;
    }

    public Book getBook() {
        return book;
    }

    public void setBook(Book book) {
        this.book = book;
    }

    public Reader getReader() {
        return reader;
    }

    public void setReader(Reader reader) {
        this.reader = reader;
    }

    public Date getGiveOutDate() {
        return giveOutDate;
    }

    public void setGiveOutDate(Date giveOutDate) {
        this.giveOutDate = giveOutDate;
    }

    @Override
    public String toString() {
        return "History{" 
                + "book=" + book.getName()
                + ", reader=" + reader.getLastname()
                + ", giveOutDate=" + giveOutDate
                + ", returnDate=" + returnDate
                + '}';
    }
    
}
