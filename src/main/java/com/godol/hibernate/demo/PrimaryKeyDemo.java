package com.godol.hibernate.demo;

import com.godol.hibernate.demo.entity.Student;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

public class PrimaryKeyDemo {
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

            // create 3 student objects
            System.out.println("Creating 3 student objects...");
            Student tempStudent1 = new Student("Jongchan","Ko", "godol811@naver.com");
            Student tempStudent2 = new Student("yoonji","noh", "yoon@naver.com");
            Student tempStudent3 = new Student("John","doe", "doe@naver.com");
            // start a transaction
            session.beginTransaction();
            // save the student object
            System.out.println("Saving the students....");
            session.save(tempStudent1);
            session.save(tempStudent2);
            session.save(tempStudent3);

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
