package com.dblibproject.dblibproject;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

import com.dblibproject.dblibproject.Student;

@Service
public class StudentService {

    @Autowired
    private StudentRepository studentRepository;

    public Student createStudent(Student student) {
        return studentRepository.save(student);
    }

    public List<Student> getAllStudents() {
        return studentRepository.findAll();
    }

    public Optional<Student> getStudentById(Long id) {
        return studentRepository.findById(id);
    }

    public Student updateStudent(Long id, Student studentDetails) {
        Optional<Student> studentOpt = studentRepository.findById(id);
        if (studentOpt.isPresent()) {
            Student student = studentOpt.get();
            student.setName(studentDetails.getName());
            student.setSurname(studentDetails.getSurname());
            return studentRepository.save(student);
        } else {
            throw new RuntimeException("Student not found");
        }
    }

    public Student updateWorkingStatus(Long id, boolean isWorking) {
        Optional<Student> studentOpt = studentRepository.findById(id);
        if (studentOpt.isPresent()) {
            Student student = studentOpt.get();
            student.setWorking(isWorking);
            return studentRepository.save(student);
        } else {
            throw new RuntimeException("Student not found");
        }
    }

    public void deleteStudent(Long id) {
        Optional<Student> studentOpt = studentRepository.findById(id);
        if (studentOpt.isPresent()) {
            studentRepository.deleteById(id);
        } else {
            throw new RuntimeException("Student not found");
        }
    }
}