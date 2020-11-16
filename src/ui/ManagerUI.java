/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ui;

import entity.dbcontrollers.BookDBController;
import entity.Book;
import entity.History;
import entity.Reader;
import entity.User;
import entity.dbcontrollers.HistoryDBController;
import entity.dbcontrollers.UserDBController;
import java.util.List;
import java.util.Scanner;
import tools.creators.BookManager;
import tools.creators.LibraryManager;
import tools.creators.ReaderManager;
import tools.creators.UserManager;
import tools.savers.SaveInterface;
import tools.savers.SaverToFile;

/**
 *
 * @author pupil
 */
public class ManagerUI {
    private BookManager bookManager = new BookManager();
    private ReaderManager readerManager = new ReaderManager();
    private UserManager userManager = new UserManager();
    private LibraryManager libraryManager = new LibraryManager();
    private HistoryDBController historyDBController = new HistoryDBController();
    private UserDBController userDBController = new UserDBController();
   
    
    public void getManagerUI(){
        boolean repeat = true;
        do{
            System.out.println("Задачи: ");
            System.out.println("0. Выйти из программы");
            System.out.println("1. Добавить новую книгу");
            System.out.println("2. Список книг");
            System.out.println("3. Зарегистрировать читателя");
            System.out.println("4. Список читателей");
            System.out.println("5. Выдать книгу читателю");
            System.out.println("6. Вернуть книгу в библиотеку");
            System.out.println("7. Список выданных книг");
            System.out.print("Выберите задачу: ");
            Scanner scanner = new Scanner(System.in);
            String task = scanner.nextLine();
            switch (task) {
                case "0":
                    System.out.println("--- конец программы ---");
                    repeat = false;
                    break;
                case "1":
                    System.out.println("--- Добавить новую книгу ---");
                    Book book = bookManager.createBook();
                    BookDBController bookDBController = new BookDBController();
                    bookDBController.create(book);
                    break;
                case "2":
                    System.out.println("--- Список книг ---");
                    bookManager.printListBooks();
                    break;
                case "3":
                    System.out.println("--- Зарегистрировать читателя ---");
                    User user = userManager.createUser();
                    userDBController.create(user);
                    break;
                case "4":
                    System.out.println("--- Список читателей ---");
                    readerManager.printListReaders();
                    break;
                case "5":
                    System.out.println("--- Выдать книгу читателю ---");
                    History history = libraryManager.takeOnBook();
                    historyDBController.create(history);
                    break;
                case "6":
                    System.out.println("--- Вернуть книгу в библиотеку ---");
                    libraryManager = new LibraryManager();
                    libraryManager.returnBook();
                    historyDBController.create(history);
                    break;
                case "7":
                    System.out.println("--- Список выданных книг ---");
                    libraryManager.printListReadBooks();
                    break;
                default:
                    System.out.println("Нет такой задачи.");
            }
        }while(repeat);
    }
}