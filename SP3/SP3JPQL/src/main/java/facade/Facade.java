package facade;

import javax.persistence.*;
import entity.*;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Esben
 */
public class Facade {

    EntityManagerFactory emf;

    public Facade(EntityManagerFactory emf) {
        this.emf = emf;
    }

    EntityManager getEntityManager() {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("sp3jpql");
        return emf.createEntityManager();
    }

    public Facade() {
    }

    public List<Student> getAllStudents() {
        EntityManager em = getEntityManager();
        List<Student> students = new ArrayList();
        try {
            students = em.createNamedQuery("Student.findAll").getResultList();
        } finally {
            em.close();
        }
        return students;
    }

    public List<Student> getAllStudentsByFirstName(String name) {
        EntityManager em = getEntityManager();
        List<Student> students = new ArrayList();
        try {
            Query query = em.createQuery("SELECT s FROM Student s WHERE s.firstname = :name");
            query.setParameter("name", name);
            students = query.getResultList();
        } finally {
            em.close();
        }
        return students;
    }

    public void createStudent(Student student) {
        EntityManager em = getEntityManager();
        try {
            em.getTransaction().begin();
            em.persist(student);
            em.getTransaction().commit();
        } finally {
            em.close();
        }
    }

    public void assignStudent(Student student, int semID) {
        EntityManager em = getEntityManager();
        try { 
            Semester sem = em.createNamedQuery("Semester.findById", Semester.class).setParameter("id", semID).getSingleResult();
            student.setSemester(sem);
            em.getTransaction().begin();
            em.merge(student);
            em.getTransaction().commit();
        } finally {
            em.close();
        }
    }

    public List<Student> getAllStudentsByLastName(String name) {
        EntityManager em = getEntityManager();
        List<Student> students = new ArrayList();
        try {
            Query query = em.createQuery("SELECT s FROM Student s WHERE s.lastname = :name");
            query.setParameter("name", name);
            students = query.getResultList();
        } finally {
            em.close();
        }
        return students;
    }

    public List<Student> getStudentsInSemester(String semestername) {
        EntityManager em = getEntityManager();
        List<Student> students = new ArrayList();
        try {
            Semester s =em.createNamedQuery("Semester.findByName", Semester.class).setParameter("name", semestername).getSingleResult();
            System.out.println(s);
            Query query = em.createQuery("SELECT s FROM Student s WHERE s.semester = :semesterID");
            query.setParameter("semesterID", s);

            students = query.getResultList();
        } finally {
            em.close();
        }
        return students;

    }

    public int StudentCount() {
        EntityManager em = getEntityManager();
        List<Student> students = new ArrayList();
        try {
            students = em.createNamedQuery("Student.findAll").getResultList();
        } finally {
            em.close();
        }
        return students.size();
    }
    
    public int StudentCountInSem(String semestername) {
        return getStudentsInSemester(semestername).size();
    }
}
