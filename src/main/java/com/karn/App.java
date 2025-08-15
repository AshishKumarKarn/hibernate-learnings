package com.karn;

import com.karn.entities.Standard;
import com.karn.entities.Student;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;


public class App {
    public static void main(String[] args) {
        SessionFactory factory = HibernateUtil.getSessionFactory();
        Session currentSession = null;
        Transaction transaction = null;
        try {
            currentSession = factory.openSession();
            transaction = currentSession.beginTransaction();

            Student student = currentSession.find(Student.class, 1L);
            Standard standard = currentSession.find(Standard.class, 1L);
            if (standard == null) {
                standard = new Standard("X", "A");
            }
            if (student == null) {
                student = new Student();
                student.setName("Erin Col");
                student.setEmail("erin.col@email.com");
                student.setStandard(standard);
                student.setSection(standard.getSection());
                student.setAbout("Another new diligent student.");
            }

            standard.getStudents().add(student);



            currentSession.persist(standard);
            currentSession.persist(student);

            System.out.printf("Standard Details: %s, %s, %s, %s \n",
                    standard.getId(),
                    standard.getName(),
                    standard.getSection(),
                    standard.getStudents());
            System.out.println("Students in Standard: " + standard.getStudents());
            //throw new RuntimeException();
            transaction.commit();

        } catch (Exception exception) {
            System.out.printf("Exception occurred: %s \n", exception.getMessage());
            if (transaction != null) {
                System.out.printf("Rolling back transaction: %s \n", transaction.getStatus());
                transaction.rollback();
            }
        } finally {
            if (currentSession != null) {
                currentSession.close();
            }
        }

        System.out.println("Hello World!");

    }
}
