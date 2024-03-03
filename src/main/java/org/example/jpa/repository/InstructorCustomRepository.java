package org.example.jpa.repository;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import org.example.jpa.model.entity.Instructor;
import org.springframework.stereotype.Repository;

@Repository
public class InstructorCustomRepository {
//    @PersistenceContext
    private EntityManager entityManager;

    public void saveInstructor(String name, String desc) {
        entityManager.getTransaction().begin();

        Instructor instructor = new Instructor();
        instructor.setName(name);
        instructor.setDesc(desc);

        entityManager.persist(instructor);
        entityManager.getTransaction().commit();
    }
}