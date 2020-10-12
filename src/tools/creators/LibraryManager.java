/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tools.creators;

import entity.Book;
import entity.History;
import entity.Reader;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.Scanner;

/**
 *
 * @author Melnikov
 */
public class LibraryManager {
    private Scanner scanner = new Scanner(System.in);

    public History takeOnBook(Book[] books, Reader[] readers) {
        History history = new History();
        // Вывести список читателей
        // Попросить пользователя выбрать номер читателя
        // По номеру читателя взять конкретного читателя из массива
        // Тоже самое проделать для читателя.
        // Инициировать history и отдать его return
        System.out.println("--- Список читателей ---");
        for (int i = 0; i < readers.length; i++) {
            if(readers[i] != null){
                System.out.println(i+1+". " + readers[i].toString());
            }
        }
        System.out.print("Выберите номер читателя: ");
        int readerNumber = scanner.nextInt();
        scanner.nextLine();
        Reader reader = readers[readerNumber-1];
        history.setReader(reader);
        System.out.println("--- Список книг ---");
        for (int i = 0; i < books.length; i++) {
            if(books[i] != null){
                System.out.println(i+1+". " + books[i].toString());
            }
        }
        System.out.print("Выберите номер книги: ");
        int bookNumber = scanner.nextInt();
        scanner.nextLine();
        Book book = books[bookNumber-1];
        history.setBook(book);
        Calendar calendar = new GregorianCalendar();
        history.setGiveOutDate(calendar.getTime());
        return history;
    }

    public void returnBook(History[] histories) {
        System.out.println("--- Список выданных книг ---");
        for (int i = 0; i < histories.length; i++) {
            if(histories[i] != null && histories[i].getReturnDate() == null){
                System.out.printf("%d. Книгу \"%s\" читает %s %s%n" 
                        ,i+1
                        ,histories[i].getBook().getName()
                        ,histories[i].getReader().getFirstname()
                        ,histories[i].getReader().getLastname()
                );
            }
        }
        System.out.print("Выберите номер возвращаемой книги: ");
        int historyNumber = scanner.nextInt();
        scanner.nextLine();
        Calendar calendar = new GregorianCalendar();
        histories[historyNumber-1].setReturnDate(calendar.getTime());
    }
    
}
