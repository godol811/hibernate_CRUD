package com.godol.hibernate.ManyToMany;

import com.godol.hibernate.ManyToMany.entity.*;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

public class DeleteMaryStudentDemo {

    public static void main(String[] args) {
        // creat session factory
        SessionFactory factory = new Configuration()
                .configure("hibernate.cfg.xml")
                .addAnnotatedClass(Instructor.class)
                .addAnnotatedClass(InstructorDetail.class)
                .addAnnotatedClass(Course.class)
                .addAnnotatedClass(Review.class)
                .addAnnotatedClass(Student.class)
                .buildSessionFactory();
        // create session
        Session session = factory.getCurrentSession();

        try{
            session.beginTransaction();

            // get the student mary from database
            int studentId = 1;
            Student tempStudent = session.get(Student.class, studentId);
            System.out.println("\n불러와진 학생:  " + tempStudent);
            System.out.println("과정 : " + tempStudent.getCourses());

            // delete student
            System.out.println("\n Deleting Student" + tempStudent);

            session.delete(tempStudent);


            // commit transaction
            session.getTransaction().commit();
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            // add clean up code
            session.close();
            factory.close();

        }


    }
}
