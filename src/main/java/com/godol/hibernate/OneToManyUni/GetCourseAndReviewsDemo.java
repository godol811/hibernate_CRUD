package com.godol.hibernate.OneToManyUni;

import com.godol.hibernate.OneToManyUni.entity.Course;
import com.godol.hibernate.OneToManyUni.entity.Instructor;
import com.godol.hibernate.OneToManyUni.entity.InstructorDetail;
import com.godol.hibernate.OneToManyUni.entity.Review;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

public class GetCourseAndReviewsDemo {

    public static void main(String[] args) {
        // creat session factory
        SessionFactory factory = new Configuration()
                .configure("hibernate.cfg.xml")
                .addAnnotatedClass(Instructor.class)
                .addAnnotatedClass(InstructorDetail.class)
                .addAnnotatedClass(Course.class)
                .addAnnotatedClass(Review.class)
                .buildSessionFactory();
        // create session
        Session session = factory.getCurrentSession();

        try{
            session.beginTransaction();

            int theId = 11;

            Course tempCourse = session.get(Course.class, theId);

            System.out.println("course : " + tempCourse);

            System.out.println("reviews : " + tempCourse.getReviews());

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
