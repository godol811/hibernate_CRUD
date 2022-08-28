package com.godol.hibernate.ManyToMany;

import com.godol.hibernate.ManyToMany.entity.*;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

public class CreateCourseAndStudentsDemo {

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

            // create a course
            Course tempCourse = new Course("Pacman - How to Score One Million Points");


            System.out.println("\nSaving the course ...");
            session.save(tempCourse);
            System.out.println("saved thd course: " + tempCourse);
            Student tempStudent1 = new Student("John", "Doe", "johndoe@naver.com");
            Student tempStudent2 = new Student("jongchan", "ko", "godol811@naver.com");

            tempCourse.addStudent(tempStudent1);
            tempCourse.addStudent(tempStudent2);
            System.out.println("\n Saving students ...");
            session.save(tempStudent1);
            session.save(tempStudent2);
            System.out.println("\n 저장된 학생들 ..." + tempCourse.getStudents());
            session.save(tempCourse);

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
