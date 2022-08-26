package com.godol.hibernate.OneToMany;

import com.godol.hibernate.OneToMany.entity.Course;
import com.godol.hibernate.OneToMany.entity.Instructor;
import com.godol.hibernate.OneToMany.entity.InstructorDetail;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

public class CreateCoursesDemo {

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
            // create some courses
            Course tempCourse1 = new Course("Air Guitar - The Ultimate Guide");
            Course tempCourse2 = new Course("Drum Master class");
            // add courses to instructor
            tempInstructor.add(tempCourse1);
            tempInstructor.add(tempCourse2);
            // save the courses
            session.save(tempCourse1);
            session.save(tempCourse2);


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
