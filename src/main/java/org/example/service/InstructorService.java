package org.example.service;

import org.apache.commons.collections4.MapUtils;
import org.example.jpa.model.dto.InstructorDto;
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

        instructor.setName(MapUtils.getString(params, "name"));
        instructor.setDesc(MapUtils.getString(params, "desc"));
        instructor.setLeft(MapUtils.getBoolean(params, "left", false));

        instructorRepository.save(instructor);

        return instructor;
    }

    @Autowired
    private InstructorCustomRepository instructorCustomRepository;
    public void addInstrcutor(Map<String, Object> params) {
        String name = MapUtils.getString(params, "name");
        String desc = MapUtils.getString(params, "desc");
        instructorCustomRepository.saveInstructor(name, desc);
    }

    public Instructor getInstructorById(Long id) {
        return instructorCustomRepository.findInstructorWithQuerydsl(id);
    }

    public InstructorDto getInstructorDtoById(Long id) {
        return instructorCustomRepository.findInstructorDtoWithQuerydsl(id);
    }
}
