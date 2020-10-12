/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package jptvr19library;

import tools.savers.HistorySaver;
import tools.creators.LibraryManager;
import tools.savers.BookSaver;
import tools.creators.CreatorReader;
import entity.Book;
import entity.History;
import entity.Reader;
import java.util.Scanner;
import tools.creators.CreatorBook;
import tools.savers.ReaderSaver;

/**
 *
 * @author Melnikov
 */
class App {
    private Book[] books = new Book[10];
    private Reader[] readers = new Reader[10];
    private History[] histories = new History[10];

    public App() {
        BookSaver bookSaver = new BookSaver();
        books = bookSaver.loadBooks();
        ReaderSaver readerSaver = new ReaderSaver();
        readers = readerSaver.loadReaders();
        HistorySaver historySaver = new HistorySaver();
        histories = historySaver.loadHistories();
    }
    
    public void run(){
        boolean repeat = true;
        System.out.println("--- Библиотека ---");
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
                    CreatorBook creatorBook = new CreatorBook();
                    Book book = creatorBook.getBook();
                    for (int i = 0; i < books.length; i++) {
                        if(books[i] == null){
                            books[i] = book;
                            break;
                        }
                    }
                    BookSaver bookSaver = new BookSaver();
                    bookSaver.saveBooks(books);
                    System.out.println("Создана книга: "+book.getName());
                    //System.out.println(book.toString());
                    break;
                case "2":
                    System.out.println("--- Список книг ---");
                    for (int i = 0; i < books.length; i++) {
                        if(books[i] != null){
                            System.out.println(i+1+". " + books[i].toString());
                        }
                    }
                    break;
                case "3":
                    System.out.println("--- Зарегистрировать читателя ---");
                    CreatorReader creatorReader = new CreatorReader();
                    Reader reader = creatorReader.getReader();
                    System.out.println("Имя читателя: "
                            +reader.getFirstname()
                            +" "
                            + reader.getLastname()
                    );
                    for (int i = 0; i < readers.length; i++) {
                        if(readers[i] == null){
                            readers[i] = reader;
                            break;
                        }
                    }
                    ReaderSaver readerSaver = new ReaderSaver();
                    readerSaver.saveReaders(readers);
                    break;
                case "4":
                    System.out.println("--- Список читателей ---");
                    for (int i = 0; i < readers.length; i++) {
                        if(readers[i] != null){
                            System.out.println(i+1+". " + readers[i].toString());
                        }
                    }
                    break;
                case "5":
                    System.out.println("--- Выдать книгу читателю ---");
                    LibraryManager libraryManager = new LibraryManager();
                    History history = libraryManager.takeOnBook(books, readers);
                    for (int i = 0; i < histories.length; i++) {
                        if (histories[i] == null) {
                            histories[i] = history;
                            break;
                        }
                    }
                    HistorySaver historySaver = new HistorySaver();
                    historySaver.saveHistories(histories);
                    break;
                case "6":
                    System.out.println("--- Вернуть книгу в библиотеку ---");
                    libraryManager = new LibraryManager();
                    libraryManager.returnBook(histories);
                    historySaver = new HistorySaver();
                    historySaver.saveHistories(histories);
                    break;
                case "7":
                    System.out.println("--- Список выданных книг ---");
                    for (int i = 0; i < histories.length; i++) {
                        if(histories[i] != null && histories[i].getReturnDate()==null){
                            System.out.printf("%d. Книгу \"%s\" читает %s %s%n" 
                                    ,i+1
                                    ,histories[i].getBook().getName()
                                    ,histories[i].getReader().getFirstname()
                                    ,histories[i].getReader().getLastname()
                            );
                        }
                    }
                    break;
                default:
                    System.out.println("Нет такой задачи.");
            }
        }while(repeat);
    }
}
