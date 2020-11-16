/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package jptvr19library;

import tools.savers.SaverToBase;
import ui.ReaderUI;
import ui.ManagerUI;
import tools.creators.ReaderManager;
import entity.Book;
import entity.History;
import entity.Reader;
import entity.User;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import security.SecureManager;
import tools.creators.BookManager;
import tools.savers.SaveInterface;
import tools.savers.SaverToFile;

/**
 *
 * @author pupil
 */
public class App {
    private List<Book> listBooks = new ArrayList<>();
    private List<Reader> listReaders = new ArrayList<>();
    private List<History> listHistories = new ArrayList<>();
    private List<User> listUsers = new ArrayList<>();
    private SecureManager secureManager = new SecureManager();
  //  private SaveInterface saver = new SaverToFile();
    private SaveInterface saver = new SaverToBase();
    
    public static User loginedUser;
    
    public App(String param) {
        if("base".equals(param)){
            this.saver =  new SaverToBase();
            System.out.println("Программа сохраняет в базу.");
        }else if("file".equals(param)){
            this.saver = new SaverToFile();
            System.out.println("Программа сохраняет в файлы.");
        }else{
            this.saver =  new SaverToBase();
            System.out.println("Программа сохраняет в базу.");
        }
        listBooks = saver.load("books");
        listReaders = saver.load("readers");
        listHistories = saver.load("histories");
        listUsers = saver.load("users");
    }
    
    public void run(){
        boolean repeat = true;
        System.out.println("--- Библиотека ---");
        this.loginedUser = secureManager.checkTask(listUsers,listReaders,saver);
        if(SecureManager.role.MANAGER.toString().equals(this.loginedUser.getRole())){
            ManagerUI managerUI = new ManagerUI();
            managerUI.getManagerUI(listReaders, listUsers, listBooks, listHistories, saver);
        }else if(SecureManager.role.READER.toString().equals(this.loginedUser.getRole())){
            ReaderUI readerUI = new ReaderUI();
            readerUI.getReaderUI(listReaders, listUsers, listBooks, listHistories, saver);
        }
    }
}