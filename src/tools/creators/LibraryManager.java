/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tools.creators;

import factory.FactoryFacade;
import entity.facade.HistoryFacade;
import entity.Book;
import entity.History;
import entity.Reader;
import entity.User;
import entity.facade.BookFacade;
import entity.facade.ReaderFacade;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.List;
import java.util.Scanner;
import jptvr19library.App;
import security.SecureManager;
import tools.creators.BookManager;
import tools.creators.ReaderManager;

/**
 *
 * @author pupil
 */
public class LibraryManager {
    private Scanner scanner = new Scanner(System.in);
    private ReaderManager readerManager = new ReaderManager();
    private BookManager bookManager = new BookManager();
    private ReaderFacade readerDBController = FactoryFacade.getReaderFacade();
    private BookFacade bookFacade = FactoryFacade.getBookFacade();
    private HistoryFacade historyFacade = FactoryFacade.getHistoryFacade();

    public History takeOnBook() {
        History history = new History();
        // Вывести список читателей
        // Попросить пользователя выбрать номер читателя
        // По номеру читателя взять конкретного читателя из массива
        // Тоже самое проделать для читателя.
        // Инициировать history и отдать его return
        User loggedInUser = App.loginedUser;
        Reader reader = null;
        
        if("READER".equals(loggedInUser.getRole())){
            reader = loggedInUser.getReader();
        }else if("MANAGER".equals(loggedInUser.getRole())){
            System.out.println("--- Список читателей ---");
            readerManager.printListReaders();
            System.out.print("Выберите номер читателя: ");
            Long readerNumber = scanner.nextLong();
            scanner.nextLine();
            reader = readerDBController.find(readerNumber);
        }
        history.setReader(reader);
        bookManager.printListBooks();
        System.out.print("Выберите номер книги: ");
        Long bookNumber = scanner.nextLong();
        scanner.nextLine();
        Book book = bookFacade.find(bookNumber);
        history.setBook(book);
        Calendar calendar = new GregorianCalendar();
        history.setGiveOutDate(calendar.getTime());
        historyFacade.create(history);
        this.printHistory(history);
        return history;
    }

    public void returnBook() {
        System.out.println("--- Список выданных книг ---");
        List<History> listHistories = historyFacade.findReadAll(App.loginedUser.getReader(), Boolean.TRUE);
        for (int i = 0; i < listHistories.size(); i++) {
            if("MANAGER".equals(App.loginedUser.getRole())){
                if(listHistories.get(i) != null && listHistories.get(i).getReturnDate() == null){
                    System.out.printf("%d. Книгу \"%s\" читает %s %s%n" 
                            ,listHistories.get(i).getId()
                            ,listHistories.get(i).getBook().getName()
                            ,listHistories.get(i).getReader().getFirstname()
                            ,listHistories.get(i).getReader().getLastname()
                    );
                }
            }else if("READER".equals(App.loginedUser.getRole())){
                if(listHistories.get(i) != null 
                        && listHistories.get(i).getReader().equals(App.loginedUser.getReader())
                        && listHistories.get(i).getReturnDate() == null){
                    System.out.printf("%d. Книгу \"%s\" читает %s %s%n" 
                            ,listHistories.get(i).getId()
                            ,listHistories.get(i).getBook().getName()
                            ,listHistories.get(i).getReader().getFirstname()
                            ,listHistories.get(i).getReader().getLastname()
                    );
                }
            }
        }
        System.out.print("Выберите номер возвращаемой книги: ");
        Long historyNumber = scanner.nextLong();
        scanner.nextLine();
        Calendar calendar = new GregorianCalendar();
        History history = historyFacade.find(historyNumber);
        history.setReturnDate(calendar.getTime());
        historyFacade.edit(history);
    }

    private void printHistory(History history) {
        System.out.printf("Книга \"%s\" выдана %s %s%n"
                ,history.getBook().getName()
                ,history.getReader().getFirstname()
                ,history.getReader().getLastname()
        );
    }

    public void printListReadBooks() {
        List<History> listHistories = historyFacade.findReadAll(App.loginedUser.getReader(),true);
        for (int i = 0; i < listHistories.size(); i++) {
            if(listHistories.get(i) != null && listHistories.get(i).getReturnDate()==null){
                System.out.printf("%d. Книгу \"%s\" читает %s %s%n" 
                        ,listHistories.get(i).getId()
                        ,listHistories.get(i).getBook().getName()
                        ,listHistories.get(i).getReader().getFirstname()
                        ,listHistories.get(i).getReader().getLastname()
                );
            }
        }
    }
    
}