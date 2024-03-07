package org.example.controller;

import org.example.jpa.model.dto.InstructorDto;
import org.example.jpa.model.entity.Instructor;
import org.example.service.InstructorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;
import java.util.Optional;

@RestController
public class InstructorController {
    @Autowired
    private InstructorService instructorService;

    @GetMapping("/instructor/list")
    public List<Instructor> getInstructor() {
        return instructorService.getInstructorList();
    }

    @GetMapping("/instructor/{id}")
    public Optional<Instructor> getInstructor(@PathVariable("id") Long id) {
        return instructorService.getInstructor(id);
    }

    @GetMapping("/instructor/name")
    public Instructor getInstructorByName(@RequestParam String name) {
        return instructorService.getInstructorByName(name);
    }

    /**
     * curl -X PUT "http://localhost:8080/student/regist?name=이지아&age=29"
     *
     * curl -X PUT -G --data-urlencode "name=김영수" --data-urlencode "age=28" http://localhost:8080/student/regist
     *
     * requestBody
     * curl -H 'Content-type: application/json' -X PUT "http://localhost:8080/instructor/regist" -d '{ "name":"이강사", "desc":"testtest"}'
     * @param params
     */
    @PutMapping("/instructor/regist")
    public void registInstructor(@RequestBody Map<String, Object> params) {
        instructorService.saveInstructor(params);
    }

    /**
     * curl -X PUT -G --data-urlencode "name=JPA Teacher" --data-urlencode "desc=lets do it" http://localhost:8080/instructor/regist2
     *
     * curl -H 'Content-type: application/json' -X PUT "http://localhost:8080/instructor/regist2" -d '{ "name":"박강사", "desc":"testtest"}'
     * curl -H 'Content-type: application/json' -X PUT "http://localhost:8080/instructor/regist2" -d '{ "name":"고강사", "desc":"testtest"}'
     * @param params
     */
    @PutMapping("/instructor/regist2")
    public void registInstructor2(@RequestBody Map<String, Object> params) {
        instructorService.addInstrcutor(params);
    }

    /**
     * curl -X GET "http://localhost:8080/instructor/find/querydsl/1"
     * @param id
     * @return
     */
    @GetMapping("/instructor/find/querydsl/{id}")
    public Instructor findInstructor(@PathVariable("id") Long id) {
        return instructorService.getInstructorById(id);
    }

    /**
     * curl -X GET "http://localhost:8080/instructor/find/querydsl/projection/1"
     * @param id
     * @return
     */
    @GetMapping("/instructor/find/querydsl/projection/{id}")
    public InstructorDto findInstructorDto(@PathVariable("id") Long id) {
        return instructorService.getInstructorDtoById(id);
    }

}
