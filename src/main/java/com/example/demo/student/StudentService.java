package com.example.demo.student;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.persistence.Transient;
import javax.swing.text.html.Option;
import javax.transaction.Transactional;
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Service
public class StudentService {

    private final StudentRepository studentRepository;

    @Autowired
    public StudentService(StudentRepository studentRepository) {
        this.studentRepository = studentRepository;
    }

    public List<Student> getStudents() {
        return studentRepository.findAll();
    }

    public void addNewStudent(Student student) {

        Optional<Student> studentByEmail =  studentRepository.findStudentByEmail(student.getEmail());

        if (studentByEmail.isPresent()) {
            throw new IllegalStateException("Email is already taken");
        }
        studentRepository.save(student);
    }

    public void deleteStudent(Long id) {
        boolean exists = studentRepository.existsById(id);
        if(!exists) {
            throw new IllegalStateException("No user exist with the given id " + id);
        }
        studentRepository.deleteById(id);
    }

    @Transactional
    public void updateStudent(Long id, String name, String email) {
        Student student = studentRepository.findById(id).orElseThrow(
                () -> new IllegalStateException("student doesn't exist"));

        if (name != null && name.length() > 0 && !name.equals(student.getName())) {
            student.setName(name);
        }

        if (email != null && email.length() > 0 && !email.equals(student.getEmail())) {
            if (studentRepository.findStudentByEmail(email).isPresent()) {
                throw new IllegalStateException("Email already registered to another user");
            }
            student.setEmail(email);
        }
    }
}
