/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package services;

import dao.IDao;
import entities.Machine;
import java.util.Date;
import java.util.List;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;
import util.HibernateUtil;

/**
 *
 * @author Admin
 */
public class MachineService implements IDao<Machine>{

    @Override
    public boolean create(Machine o) {
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
    public boolean update(Machine o) {
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
    public boolean delete(Machine o){
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
    public List<Machine> findAll() {
        Session session= null;
        Transaction tr=null;
        List<Machine> machines=null;
        
        try {
            session = HibernateUtil.getSessionFactory().openSession();
            tr =session.beginTransaction();
            machines=session.createQuery("from Machine").list();
            tr.commit();
        } catch (HibernateException e) {
            if(tr != null){
                tr.rollback();
            }
        } finally{
            if (session != null)
                session.close();
        }
        return machines;
    }

    @Override
    public Machine findById(int id) {
        Session session= null;
        Transaction tr=null;
        Machine machine =null;
        
        try {
            session = HibernateUtil.getSessionFactory().openSession();
            tr =session.beginTransaction();
            machine= (Machine) session.get(Machine.class,id);
            tr.commit();
        } catch (HibernateException e) {
            if(tr != null){
                tr.rollback();
            }
        } finally{
            if (session != null)
                session.close();
        }
        return machine;
    }
    
    public List<Machine> findBetweenDate(Date d1,Date d2){
        Session session = null;
        Transaction tr = null;
        List<Machine> machines =null;
        try {
            session = HibernateUtil.getSessionFactory().openSession();
            tr = session.beginTransaction();
            machines =session.createQuery("from Machine where dateAchat between :d1 and :d2")
                    .setParameter("d1", d1)
                    .setParameter("d2", d2)
                    .list();
            tr.commit();
        } catch (HibernateException e) {
            if(tr != null) {
                tr.rollback();
            }
        } finally {
            if (session != null)
                session.close();
        } 
        return machines;
        
    }
     
}
    
    
