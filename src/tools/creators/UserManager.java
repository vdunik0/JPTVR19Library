/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tools.creators;

import entity.Book;
import entity.Reader;
import entity.User;
import java.util.Scanner;

/**
 *
 * @author Melnikov
 */
public class UserManager {
        private Scanner scanner = new Scanner(System.in);

    public User createUser() {
        ReaderManager readerManager = new ReaderManager();
        Reader reader = readerManager.createReader();
        User user = new User();
        System.out.println("--- Создание пользователя ---");
        System.out.print("Введите логин: ");
        user.setLogin(scanner.nextLine());
        System.out.print("Введите пароль: ");
        user.setPassword(scanner.nextLine());
        System.out.print("Укажите роль: ");
        user.setRole(scanner.nextLine());
        user.setReader(reader);
        System.out.println("Пользователь создан: "+user.toString());
        return user;
    }

    public void addUserToArray(User user, User[] users) {
        for (int i = 0; i < users.length; i++) {
            if(users[i] == null){
                users[i] = user;
                break;
            }
        }
    }

    public void printListUsers(User[] users) {
        for (int i = 0; i < users.length; i++) {
            if(users[i] != null){
                System.out.println(i+1+". " + users[i].toString());
            }
        }   
    }
    
}
