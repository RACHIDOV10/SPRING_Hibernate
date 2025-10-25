/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package services;

import dao.IDao;
import entities.Salle;
import java.util.List;
import org.hibernate.HibernateException;
import util.HibernateUtil;
import org.hibernate.Session;
import org.hibernate.Transaction;


        

public class SalleService implements IDao<Salle>{

    @Override
    public boolean create(Salle o) {
        Session session= null;
        Transaction tr=null;
        boolean etat =false;
        
        try {
            session = HibernateUtil.getSessionFactory().openSession();
            tr =session.beginTransaction();
            session.save(o);
            tr.commit();
            etat=true;
        } catch (HibernateException e) {
            if(tr != null){
                tr.rollback();
            etat = false;
            }
        } finally{
            if (session != null)
                session.close();
        }
        return etat;
    }

    @Override
    public boolean update(Salle o) {
        Session session= null;
        Transaction tr=null;
        boolean etat =false;
        
        try {
            session = HibernateUtil.getSessionFactory().openSession();
            tr =session.beginTransaction();
            session.update(o);
            tr.commit();
            etat=true;
        } catch (HibernateException e) {
            if(tr != null){
                tr.rollback();
            etat = false;
            }
        } finally{
            if (session != null)
                session.close();
        }
        return etat;
    }

    @Override
    public boolean delete(Salle o){
        Session session= null;
        Transaction tr=null;
        boolean etat =false;
        
        try {
            session = HibernateUtil.getSessionFactory().openSession();
            tr =session.beginTransaction();
            session.delete(o);
            tr.commit();
            etat=true;
        } catch (HibernateException e) {
            if(tr != null){
                tr.rollback();
            etat = false;
            }
        } finally{
            if (session != null)
                session.close();
        }
        return etat;
    }

    @Override
    public List<Salle> findAll() {
        Session session= null;
        Transaction tr=null;
        List<Salle> salles=null;
        
        try {
            session = HibernateUtil.getSessionFactory().openSession();
            tr =session.beginTransaction();
            salles=session.createQuery("from Salle").list();
            tr.commit();
        } catch (HibernateException e) {
            if(tr != null){
                tr.rollback();
            }
        } finally{
            if (session != null)
                session.close();
        }
        return salles;
    }@Override
    public Salle findById(int id) {
        Session session= null;
        Transaction tr=null;
        Salle salle =null;
        
        try {
            session = HibernateUtil.getSessionFactory().openSession();
            tr =session.beginTransaction();
            salle= (Salle) session.get(Salle.class,id);
            tr.commit();
        } catch (HibernateException e) {
            if(tr != null){
                tr.rollback();
            }
        } finally{
            if (session != null)
                session.close();
        }
        return salle;
    }

    
    
    
    
    
    
}
