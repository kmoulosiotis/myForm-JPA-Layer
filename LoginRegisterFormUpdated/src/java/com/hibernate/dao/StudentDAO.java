/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.hibernate.dao;

import com.hibernate.bean.Student;
import org.hibernate.Session;
import org.hibernate.Transaction;

import com.hibernate.util.HibernateUtil;
import org.hibernate.Query;

/**
 *
 * @author Kostas
 */
public class StudentDAO {
    
    public void addStudent(Student s){
      Transaction transaction = null;
      
      try  {
           Session session = HibernateUtil.getSessionFactory().openSession();
           // start a transaction
           transaction = session.beginTransaction();
           // save the student object
           session.save(s);
           // commit transaction
           transaction.commit();
       } catch (Exception e) {
           System.out.println(e);
       }
    }
    
    public Student getStudent(String username){
        
        Session session = HibernateUtil.getSessionFactory().openSession();
        
        Query query = session.createQuery("from Student s where s.username = :username");
        query.setParameter("username", username);
        
        Student s = (Student) query.uniqueResult();
      
        return s;
        
    }
    
    public boolean deleteStudent(Student s){
        Transaction transaction = null;
        boolean status = false;
        try  {
            Session session = HibernateUtil.getSessionFactory().openSession();
            // start a transaction
            transaction = session.beginTransaction();
            // save the student object
            Student s2 = getStudent(s.getUsername());
            if(s2 != null){
                session.delete(s);
                status =true;
            }
            // commit transaction
            transaction.commit();
        } catch (Exception e) {
            System.out.println(e);
        }
        
        return status;
    }
}
