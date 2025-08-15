package com.karn;

import com.karn.entities.Standard;
import com.karn.entities.Student;
import org.hibernate.Session;
import org.hibernate.SessionFactory;


public class App {
    public static void main(String[] args) {
        SessionFactory factory = HibernateUtil.getSessionFactory();
        try(Session currentSession = factory.getCurrentSession()){
            currentSession.beginTransaction();
            Student student = new Student();
            student.setName("Erin Col");
            student.setEmail("erin.col@email.com");
            Standard standard = currentSession.find(Standard.class, 1L);
            standard.getStudents().add(student);

            student.setStandard(standard);
            student.setSection(standard.getSection());

            student.setAbout("Another new diligent student.");
            currentSession.persist(standard);
            currentSession.persist(student);
//            currentSession.

            System.out.printf("Standard Details: %s, %s, %s, %s \n",
                    standard.getId(),
                    standard.getName(),
                    standard.getSection(),
                    standard.getStudents());
            System.out.println("Students in Standard: " + standard.getStudents());
        currentSession.getTransaction().commit();

        }

        System.out.println("Hello World!");

    }
}
