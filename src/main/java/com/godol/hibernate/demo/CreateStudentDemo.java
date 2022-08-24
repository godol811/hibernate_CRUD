package com.godol.hibernate.demo;

import com.godol.hibernate.demo.entity.Student;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

public class CreateStudentDemo {

    public static void main(String[] args) {
        // creat session factory
        SessionFactory factory = new Configuration()
                .configure("hibernate.cfg.xml")
                .addAnnotatedClass(Student.class)
                .buildSessionFactory();
        // create session
        Session session = factory.getCurrentSession();

        try{
            // use the session object to save Java object

            // create a student object
            System.out.println("Creating new student object...");
            Student tempStudent = new Student("Jongchan","Ko", "godol811@naver.com");
            // start a transaction
            session.beginTransaction();
            // save the student object
            System.out.println("Saving the student....");
            session.save(tempStudent);
            // commit transaction
            session.getTransaction().commit();

            System.out.println("Done!");
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            factory.close();
        }


    }
}
