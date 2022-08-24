package com.godol.hibernate.demo;

import com.godol.hibernate.demo.entity.Student;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import java.util.List;

public class QueryStudentDemo {

    public static void main(String[] args) {
        // creat session factory
        SessionFactory factory = new Configuration()
                .configure("hibernate.cfg.xml")
                .addAnnotatedClass(Student.class)
                .buildSessionFactory();
        // create session
        Session session = factory.getCurrentSession();

        try{

            // start a transaction
            session.beginTransaction();

            // query students
            List<Student> theStudents = session.createQuery("from Student").list();

            // display the students
            extracted(theStudents);
            // query students : lastName = 'doe'
            theStudents = session.createQuery("from Student s where s.lastName='doe'").list();
            // display the students
            System.out.println("\n\nStudents who have last name of doe");
            extracted(theStudents);

            // query students: lastName = 'Doe' Or firstName = 'jongchan'
            theStudents = session.createQuery("from Student s where s.lastName = 'doe' or s.firstName='jongchan'").list();
            extracted(theStudents);

            // query students where email LIKE '%naver.com'
            theStudents = session.createQuery("from Student s where s.email like '%naver.com'").list();
            extracted(theStudents);
            // commit transaction
            session.getTransaction().commit();


        }catch (Exception e){
            e.printStackTrace();
        }finally {
            factory.close();
        }


    }

    private static void extracted(List<Student> theStudents) {
        for (Student tempStudent: theStudents){
            System.out.println(tempStudent);
        }
    }
}
