package com.godol.hibernate.OneToOne;

import com.godol.hibernate.OneToOne.entity.Instructor;
import com.godol.hibernate.OneToOne.entity.InstructorDetail;
import com.godol.hibernate.demo.entity.Student;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

public class CreateDemo {

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
            // create the objects
//            Instructor tempInstructor = new Instructor("chad","darby", "godol811@naver.com");
//            InstructorDetail tempInstructorDetail = new InstructorDetail("https://youtube.com/godol811", "CrossFit");

            Instructor tempInstructor = new Instructor("Madhu","Pata", "godolrefwfqf1@dfjdklfjl.com");
            InstructorDetail tempInstructorDetail = new InstructorDetail("https://youtube.com/hihijiji", "bybybybyby");
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
            factory.close();
        }


    }
}
