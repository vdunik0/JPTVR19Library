/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ui;

import entity.Book;
import entity.History;
import entity.Reader;
import entity.User;
import java.util.List;
import java.util.Scanner;
import tools.creators.BookManager;
import tools.creators.LibraryManager;
import tools.creators.ReaderManager;
import tools.savers.SaverToFile;

/**
 *
 * @author pupil
 */
public class ReaderUI {
    private BookManager bookManager = new BookManager();
    private ReaderManager readerManager = new ReaderManager();
    private LibraryManager libraryManager = new LibraryManager();
    private SaverToFile saverToFile = new SaverToFile(); //private BookSaver bookSaver = new BookSaver();
        
    public void getReaderUI(List<Reader> listReaders,List<User> listUsers, List<Book> listBooks, List<History> listHistories){
        boolean repeat = true;
        do{
            System.out.println("Задачи: ");
            System.out.println("0. Выйти из программы");
            System.out.println("1. Список книг");
            System.out.println("2. Выдать книгу читателю");
            System.out.println("3. Вернуть книгу в библиотеку");
            System.out.print("Выберите задачу: ");
            Scanner scanner = new Scanner(System.in);
            String task = scanner.nextLine();
            switch (task) {
                case "0":
                    System.out.println("--- конец программы ---");
                    repeat = false;
                    break;
                case "1":
                    System.out.println("--- Список книг ---");
                    bookManager.printListBooks(listBooks);
                    break;
                case "2":
                    System.out.println("--- Выдать книгу читателю ---");
                    History history = libraryManager.takeOnBook(listBooks, listReaders);
                    libraryManager.addHistoryToArray(history,listHistories);
                    saverToFile.save(listHistories, "histories");
                    break;
                case "3":
                    System.out.println("--- Вернуть книгу в библиотеку ---");
                    libraryManager = new LibraryManager();
                    libraryManager.returnBook(listHistories);
                    saverToFile.save(listHistories,"histories");
                    break;
                default:
                    System.out.println("Нет такой задачи.");
            }
        }while(repeat);
    }
}