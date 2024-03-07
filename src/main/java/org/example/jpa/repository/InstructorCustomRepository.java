package org.example.jpa.repository;

import com.querydsl.core.types.Projections;
import com.querydsl.core.types.dsl.CaseBuilder;
import com.querydsl.jpa.impl.JPAQueryFactory;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import org.example.jpa.model.dto.InstructorDto;
import org.example.jpa.model.entity.Instructor;
import org.example.jpa.model.entity.QInstructor;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository
public class InstructorCustomRepository {
    @PersistenceContext
    private EntityManager entityManager;

    @Transactional
    public void saveInstructor(String name, String desc) {
        Instructor instructor = new Instructor();
        instructor.setName(name);
        instructor.setDesc(desc);

        entityManager.persist(instructor);
    }

    public Instructor findInstructorWithQuerydsl(Long id) {
        JPAQueryFactory queryFactory = new JPAQueryFactory(entityManager);
        QInstructor ins = QInstructor.instructor;
        return queryFactory.selectFrom(ins)
                .where(ins.id.eq(id))
                .fetchOne();
    }

    public InstructorDto findInstructorDtoWithQuerydsl(Long id) {
        JPAQueryFactory queryFactory = new JPAQueryFactory(entityManager);
        QInstructor ins = QInstructor.instructor;
        return queryFactory.select(
                        Projections.constructor(InstructorDto.class
                                , ins.id
                                , ins.name
                                , new CaseBuilder().when(ins.left.isTrue()).then("재직중").otherwise("퇴사")
                                , ins.desc
                        )
                )
                .where(ins.id.eq(id))
                .from(ins)
                .fetchOne();
    }
}