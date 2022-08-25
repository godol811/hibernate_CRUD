package com.godol.hibernate.OneToOneBiDirectional;

import com.godol.hibernate.OneToOneBiDirectional.entity.Instructor;
import com.godol.hibernate.OneToOneBiDirectional.entity.InstructorDetail;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

public class DeleteInstructorDetailDemo {

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
            int theId = 4;
            InstructorDetail tempInstructorDetail = session.get(InstructorDetail.class, theId);

            // print the instructor detail
            System.out.println("tempInstructorDetail :  " + tempInstructorDetail);
            // print the associated instructor
            System.out.println("the associated instructor : " + tempInstructorDetail.getInstructor());

            // now let's delete the instructor detail
            System.out.println("Deleting tempInstructorDetail : " + tempInstructorDetail);

            // remove the associated object reference
            // break bi-directional link
            // null 값으로 set을 함으로서 연결된 값중 하나의 테이블만 삭제를 할 수 있다.
            tempInstructorDetail.getInstructor().setInstructorDetail(null);

            session.delete(tempInstructorDetail);

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
