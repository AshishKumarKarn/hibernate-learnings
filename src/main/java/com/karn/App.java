package com.karn;

import com.karn.entities.Standard;
import com.karn.entities.Student;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

import java.util.List;


public class App {
    public static void main(String[] args) {
         //nPlusOneProblem();
       // simpleUseCase_1();
        nPlusOneProblemSolved();
    }
    private static void simpleUseCase_1(){
        SessionFactory factory = HibernateUtil.getSessionFactory();
        Session currentSession = null;
        Transaction transaction = null;
        try {
            currentSession = factory.openSession();
            transaction = currentSession.beginTransaction();

//            Student student = currentSession.find(Student.class, 2L);
            Standard standard = currentSession.find(Standard.class, 2L);
            if (standard == null) {
                standard = new Standard("XI", "A");
            }
            Student student = null;
            if (student == null) {
                student = new Student();
                student.setName("Lina Heardy");
                student.setEmail("lina.heardy@email.com");
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
    private static void nPlusOneProblem() {
        try(Session session = HibernateUtil.getSessionFactory().openSession()) {
            Transaction transaction = session.beginTransaction();
            // Fetching all standards
            List<Standard> standards = session.createQuery("from Standard", Standard.class).list();
            for (Standard std : standards) {
                // Each call triggers a separate query
                List<Student> students = std.getStudents();
                students.forEach(student -> System.out.printf("Standard: %s, Student: %s, Section: %s \n",
                        std.getName(), student.getName(), student.getSection()));
            }
            transaction.commit();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private static void nPlusOneProblemSolved() {
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            Transaction transaction = session.beginTransaction();
            // Using JOIN FETCH to avoid N+1 problem
            List<Standard> standards = session.createQuery("SELECT s FROM Standard s JOIN FETCH s.students", Standard.class).list();
            for (Standard std : standards) {
                std.getStudents().forEach(student -> {
                    System.out.printf("Standard: %s, Student: %s, Section: %s \n",
                            std.getName(), student.getName(), student.getSection());
                });
            }
            transaction.commit();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
