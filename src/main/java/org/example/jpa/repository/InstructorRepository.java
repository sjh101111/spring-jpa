package org.example.jpa.repository;

import org.example.jpa.model.entity.Instructor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface InstructorRepository extends JpaRepository<Instructor, Long> {

    Instructor findByName(String name);

    @Query(value = "select Instructor from Instructor where name like %:name% ")
    List<Instructor> findInstructorList(@Param("name") String name);

    @Modifying(flushAutomatically = true, clearAutomatically = true)
    @Query(value = "update instructor set name = :name where id = :id", nativeQuery = true)
    int updateNameById(Long id, String name);
}
