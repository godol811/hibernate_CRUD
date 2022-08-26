package com.godol.hibernate.OneToMany;

import com.godol.hibernate.OneToMany.entity.Course;
import com.godol.hibernate.OneToMany.entity.Instructor;
import com.godol.hibernate.OneToMany.entity.InstructorDetail;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

public class CreateInstructorDemo {

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

            Instructor tempInstructor = new Instructor("Susan","Public", "susan@gmail.com");
            InstructorDetail tempInstructorDetail = new InstructorDetail("https://youtube.com/hihijiji", "Games");
//            Course tempCourse = new Course("");
            // associate the objects
            tempInstructor.setInstructorDetail(tempInstructorDetail);
            session.beginTransaction();

            // save the instructor
            // Note: this will ALSO save the details object
            // Because of CascadeType.ALL
            System.out.println("Saving instructor  " + tempInstructor);
            session.save(tempInstructor);

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
