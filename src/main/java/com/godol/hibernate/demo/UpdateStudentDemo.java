package com.godol.hibernate.demo;

import com.godol.hibernate.demo.entity.Student;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

public class UpdateStudentDemo {

    public static void main(String[] args) {
        // creat session factory
        SessionFactory factory = new Configuration()
                .configure("hibernate.cfg.xml")
                .addAnnotatedClass(Student.class)
                .buildSessionFactory();
        // create session
        Session session = factory.getCurrentSession();

        try{
            int studentId = 1;



            // now get a new session and start transaction
            session = factory.getCurrentSession();
            session.beginTransaction();
            // retrieve student based on the id: primary key
            System.out.println("\nGettting student with id  " + studentId);
            Student myStudent = session.get(Student.class, studentId);

            System.out.println("Updating student...");
            myStudent.setFirstName("Scooby");
            // commit the transaction
            session.getTransaction().commit();


            // NEW CODE

            session = factory.getCurrentSession();
            session.beginTransaction();
            // update email for all students
            System.out.println("Update email for all students");

            session.createQuery("update Student set email = 'hapgodol@naver.com'").executeUpdate();

            // commit the transaction
            session.getTransaction().commit();

            System.out.println("Done!");
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            factory.close();
        }


    }
}
