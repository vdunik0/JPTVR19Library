/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entity.dbcontrollers;

import entity.History;
import entity.User;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;

/**
 *
 * @author pupil
 */
public class HistoryDBController {
     private EntityManagerFactory emf = Persistence.createEntityManagerFactory("JPTVR19LibraryPU");
    private EntityManager em = emf.createEntityManager();
    private EntityTransaction tx = em.getTransaction();
    
    public void create(History history) {
        tx.begin();
        em.persist(history);
        tx.commit();
    }

    public List<History> findAll() {
        try {
            return em.createQuery("SELECT h FROM History h")
                .getResultList();
        } catch (Exception e) {
            return null;
        }
        }

    public List<History> findAll(User loginedUser) {
        try {
            return em.createQuery("SELECT h FROM History h WHERE h.reader = :reader AND h.returnDate = NULL")
                .getResultList();
        } catch (Exception e) {
            return null;
        }
        }
    }
    }
