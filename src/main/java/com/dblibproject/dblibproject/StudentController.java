package com.dblibproject.dblibproject;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/students")
public class StudentController {

    @Autowired
    private StudentService studentService;

    @PostMapping
    public Student createStudent(@RequestBody Student student) {
        return studentService.createStudent(student);
    }

    @GetMapping
    public List<Student> getAllStudents() {
        return studentService.getAllStudents();
    }

    @GetMapping("/{id}")
    public Student getStudentById(@PathVariable Long id) {
        Optional<Student> studentOpt = studentService.getStudentById(id);
        if (studentOpt.isPresent()) {
            return studentOpt.get();
        } else {
            throw new RuntimeException("Student not found");
        }
    }

    @PutMapping("/{id}")
    public Student updateStudent(@PathVariable Long id, @RequestBody Student studentDetails) {
        Optional<Student> studentOpt = studentService.getStudentById(id);
        if (studentOpt.isPresent()) {
            return studentService.updateStudent(id, studentDetails);
        } else {
            throw new RuntimeException("Student not found");
        }
    }

    @PatchMapping("/{id}/working")
    public Student updateWorkingStatus(@PathVariable Long id, @RequestParam boolean working) {
        Optional<Student> studentOpt = studentService.getStudentById(id);
        if (studentOpt.isPresent()) {
            return studentService.updateWorkingStatus(id, working);
        } else {
            throw new RuntimeException("Student not found");
        }
    }

    @DeleteMapping("/{id}")
    public void deleteStudent(@PathVariable Long id) {
        Optional<Student> studentOpt = studentService.getStudentById(id);
        if (studentOpt.isPresent()) {
            studentService.deleteStudent(id);
        } else {
            throw new RuntimeException("Student not found");
        }
    }
}
