/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package factory;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

/**
 *
 * @author pupil
 */
public class ConnetSingleton {
    private EntityManagerFactory emf;
    private EntityManager em;
    private static ConnetSingleton instance;
    private ConnetSingleton(){
        emf = Persistence.createEntityManagerFactory("JPYVR19Library");
        em = emf.createEntityManager();
    }
    public static ConnetSingleton getInstance(){
        if(instance==null){
            instance = new ConnetSingleton();
        }
        return instance;
    }
    public EntityManager getEntityManager(){
        return em;
    }
    public EntityManagerFactory getEntityManagerFactory(){
        return emf;
    }
        
}
