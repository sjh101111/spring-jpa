package org.example.service;

import org.example.jpa.model.entity.Instructor;
import org.example.jpa.repository.InstructorCustomRepository;
import org.example.jpa.repository.InstructorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import java.util.Optional;

@Service
public class InstructorService {
    @Autowired
    private InstructorRepository instructorRepository;

    public List<Instructor> getInstructorList() {
        return instructorRepository.findAll();
    }

    public Optional<Instructor> getInstructor(Long id) {
        return instructorRepository.findById(id);
    }

    public Instructor getInstructorByName(String name) {
        return instructorRepository.findByName(name);
    }

    public Instructor saveInstructor(Map<String, Object> params) {
        Instructor instructor = new Instructor();

        instructor.setName("Sam Altman");
        instructor.setDesc("hi I'm Sam Altman from open ai ");
//        instructor.setLeft(false);

        instructorRepository.save(instructor);

        return instructor;
    }

    @Autowired
    private InstructorCustomRepository instructorCustomRepository;
    public void addInstrcutor(String name, String desc) {
        instructorCustomRepository.saveInstructor(name, desc);
    }

}
