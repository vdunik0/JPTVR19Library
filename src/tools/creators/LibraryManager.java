/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tools.creators;

import entity.dbcontrollers.HistoryDBController;
import entity.Book;
import entity.History;
import entity.Reader;
import entity.User;
import entity.dbcontrollers.BookDBController;
import entity.dbcontrollers.ReaderDBController;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.List;
import java.util.Scanner;
import jptvr19library.App;
import security.SecureManager;
import tools.creators.BookManager;
import tools.creators.ReaderManager;
import tools.savers.SaverToFile;

/**
 *
 * @author pupil
 */
public class LibraryManager {
    private Scanner scanner = new Scanner(System.in);
    private ReaderManager readerManager = new ReaderManager();
    private BookManager bookManager = new BookManager();

    public History takeOnBook() {
        History history = new History();
        // Вывести список читателей
        // Попросить пользователя выбрать номер читателя
        // По номеру читателя взять конкретного читателя из массива
        // Тоже самое проделать для читателя.
        // Инициировать history и отдать его return
        User loggedInUser = App.loginedUser;
        Reader reader = null;
        ReaderDBController readerBController = new ReaderDBController();
        List<Reader> listReaders = readerBController.findAll();
        if("READER".equals(loggedInUser.getRole())){
            reader = loggedInUser.getReader();
        }else if("MANAGER".equals(loggedInUser.getRole())){
            System.out.println("--- Список читателей ---");
            readerManager.printListReaders();
            System.out.print("Выберите номер читателя: ");
            int readerNumber = scanner.nextInt();
            scanner.nextLine();
            reader = listReaders.get(readerNumber-1);
        }
        history.setReader(reader);
        BookDBController bookDBController = new BookDBController();
        List<Book> listBooks = bookDBController.findAll();
        bookManager.printListBooks();
        System.out.print("Выберите номер книги: ");
        int bookNumber = scanner.nextInt();
        scanner.nextLine();
        Book book = listBooks.get(bookNumber-1);
        history.setBook(book);
        Calendar calendar = new GregorianCalendar();
        history.setGiveOutDate(calendar.getTime());
        this.printHistory(history);
        return history;
    }

    public void returnBook() {
        System.out.println("--- Список выданных книг ---");
        HistoryDBController historyDBController = new HistoryDBController();
        List<History> listHistories = historyDBController.findAll();
        for (int i = 0; i < listHistories.size(); i++) {
            if("MANAGER".equals(App.loginedUser.getRole())){
                if(listHistories.get(i) != null && listHistories.get(i).getReturnDate() == null){
                    System.out.printf("%d. Книгу \"%s\" читает %s %s%n" 
                            ,i+1
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
                            ,i+1
                            ,listHistories.get(i).getBook().getName()
                            ,listHistories.get(i).getReader().getFirstname()
                            ,listHistories.get(i).getReader().getLastname()
                    );
                }
            }
        }
        System.out.print("Выберите номер возвращаемой книги: ");
        int historyNumber = scanner.nextInt();
        scanner.nextLine();
        Calendar calendar = new GregorianCalendar();
        listHistories.get(historyNumber-1).setReturnDate(calendar.getTime());
    }

    public void addHistoryToArray(History history, List<History> listHistories) {
        listHistories.add(history);
    }

    private void printHistory(History history) {
        System.out.printf("Книга \"%s\" выдана %s %s%n"
                ,history.getBook().getName()
                ,history.getReader().getFirstname()
                ,history.getReader().getLastname()
        );
    }

    public void printListReadBooks() {
        HistoryDBController historyDBController = new HistoryDBController();
        List<History> listHistories = historyDBController.findAll();
        for (int i = 0; i < listHistories.size(); i++) {
            if(listHistories.get(i) != null && listHistories.get(i).getReturnDate()==null){
                System.out.printf("%d. Книгу \"%s\" читает %s %s%n" 
                        ,i+1
                        ,listHistories.get(i).getBook().getName()
                        ,listHistories.get(i).getReader().getFirstname()
                        ,listHistories.get(i).getReader().getLastname()
                );
            }
        }
    }
    
}
