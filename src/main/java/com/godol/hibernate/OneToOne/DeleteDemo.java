package com.godol.hibernate.OneToOne;

import com.godol.hibernate.OneToOne.entity.Instructor;
import com.godol.hibernate.OneToOne.entity.InstructorDetail;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

public class DeleteDemo {

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

            // get instructor by primary key / id
            int theId = 2;
            Instructor tempInstructor = session.get(Instructor.class, theId);

            // Delete the instructor
            System.out.println("Found instructor : " + tempInstructor);

            if (tempInstructor != null){
                System.out.println("Deleting: " + tempInstructor);
                // Note : will ALSO delete associated "details" object
                // because of CascadeType.ALL
                //
                session.delete(tempInstructor);
            }

            // Note: this will ALSO save the details object

            // Because of CascadeType.ALL

            session.getTransaction().commit();
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            factory.close();
        }


    }
}
