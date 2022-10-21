package com.example.demo.student;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/v1/student")
public class StudentController {

    private final StudentService studentService;

    @Autowired
    public StudentController(StudentService studentService) {
        this.studentService = studentService;
    }

    @GetMapping
    public List<Student> getStudent() {
        return studentService.getStudents();
    }

    @PostMapping
    public void addStudent(@RequestBody Student student) { // request body takes the body of the payload and will map with the student
        studentService.addNewStudent(student);
    }

    @DeleteMapping(path = "{studentId}")
    public void deleteStudent(@PathVariable("studentId") Long id) {
        studentService.deleteStudent(id);
    }

    @PutMapping(path = "{studentId}")
    public void updateAStudent(@PathVariable("studentId")Long id, @RequestParam(required = false)String name, @RequestParam(required = false) String email) {
        // request param will catch the parameters in url which is defined like ie. "url/url?name=fahim
        studentService.updateStudent(id, name, email);
    }
}
