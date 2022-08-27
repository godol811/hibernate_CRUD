package com.godol.hibernate.EagerVSLazyDemo;

import com.godol.hibernate.EagerVSLazyDemo.entity.Course;
import com.godol.hibernate.EagerVSLazyDemo.entity.Instructor;
import com.godol.hibernate.EagerVSLazyDemo.entity.InstructorDetail;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.hibernate.query.Query;

public class FetchJoinDemo {

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

            // option 2: Hibernate query with HQL


            // get the instructor from DB
            int theId = 1;
            //course는 Instructor의 있는 변수명을 기준으로 한다. 그러므로 빨간줄이 걸린다고 하면 Instructor의 필드값을 고려해보도록하자
            Query<Instructor> query = session.createQuery("select i from Instructor i JOIN FETCH i.courses where i.id=:theInstructorId", Instructor.class);

            query.setParameter("theInstructorId", theId);

            Instructor tempInstructor = query.getSingleResult();

            System.out.println("godol811 : Instructor : " + tempInstructor);


            // get course for the instructor
            System.out.println("godol811 : Courses : " + tempInstructor.getCourses());

            session.getTransaction().commit();

            System.out.println("godol811 : Done!");
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            // add clean up code
            session.close();
            factory.close();

        }


    }
}
