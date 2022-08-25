package com.godol.hibernate.OneToOneBiDirectional;

import com.godol.hibernate.OneToOneBiDirectional.entity.Instructor;
import com.godol.hibernate.OneToOneBiDirectional.entity.InstructorDetail;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

public class GetInstructorDetailDemo {

    public static void main(String[] args) {
        // creat session factory
        SessionFactory factory = new Configuration()
                .configure("hibernate.cfg.xml")
                .addAnnotatedClass(Instructor.class)
                .addAnnotatedClass(InstructorDetail.class)
                .buildSessionFactory();
        // create session
        Session session = factory.getCurrentSession();

        try{
            // start a transaction
            session.beginTransaction();

            // get the instructor detail object
            int theId = 2999;
            InstructorDetail tempInstructorDetail = session.get(InstructorDetail.class, theId);

            // print the instructor detail
            System.out.println("tempInstructorDetail :  " + tempInstructorDetail);
            // print the associated instructor
            System.out.println("the associated instructor : " + tempInstructorDetail.getInstructor());
            // Because of CascadeType.ALL

            session.getTransaction().commit();
        }catch (Exception exc){
            exc.printStackTrace();
        }finally {
            // handle connection leak issue
            session.close();

            factory.close();
        }


    }
}
