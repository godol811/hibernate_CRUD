package com.godol.hibernate.OneToMany;

import com.godol.hibernate.OneToMany.entity.Course;
import com.godol.hibernate.OneToMany.entity.Instructor;
import com.godol.hibernate.OneToMany.entity.InstructorDetail;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

public class GetInstructorCoursesDemo {

    public static void main(String[] args) {
        // creat session factory
        SessionFactory factory = new Configuration()
                .configure("hibernate.cfg.xml")
                .addAnnotatedClass(Instructor.class)
                .addAnnotatedClass(InstructorDetail.class)
                .addAnnotatedClass(Course.class)
                .buildSessionFactory();
        // create session
        Session session = factory.getCurrentSession();

        try{

            session.beginTransaction();

            // get the instructor from DB
            int theId = 1;
            Instructor tempInstructor = session.get(Instructor.class, theId);

            System.out.println("Instructor : " + tempInstructor);


            // get course for the instructor
            System.out.println("Courses : " + tempInstructor.getCourses());

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
