package org.example.controller;

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
    public Optional<Instructor> getInstructor(@PathVariable Long id) {
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
     * curl -X PUT "http://localhost:8080/student/regist" -d '{ "name":"김영수", "age":"28"}'
     * @param params
     */
    @PutMapping("/instructor/regist")
    public void registInstructor(@RequestParam Map<String, Object> params) {
        instructorService.saveInstructor(params);
    }

    /**
     * curl -X PUT -G --data-urlencode "name=JPA Teacher" --data-urlencode "desc=lets do it" http://localhost:8080/instructor/regist2
     * @param name
     * @param desc
     */
    @PutMapping("/instructor/regist2")
    public void registInstructor(@RequestParam String name, @RequestParam String desc) {
        instructorService.addInstrcutor(name, desc);
    }

}
