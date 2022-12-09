package com.example.project2.student;

import jakarta.persistence.Transient;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.Month;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Service
public class StudentService {
    private final StudentRespository studentRespository;

    @Autowired
    public StudentService(StudentRespository studentRespository) {
        this.studentRespository = studentRespository;
    }


    public List<Student> getStudent(){
        return studentRespository.findAll();
    }

    public void addNewStudent(Student student) {
        Optional<Student> studentByEmail = studentRespository
            .findStudentByEmail(student.getEmail());
        if(studentByEmail.isPresent()){
            throw new IllegalStateException("email taken");
        }
        studentRespository.save(student);
    }

    public void deleteStudent(Long studentId) {

        boolean exists = studentRespository.existsById(studentId);
        if(!exists){
            throw new IllegalStateException("student with id " + studentId + " does not exists");
        }
        studentRespository.deleteById(studentId);

    }

    @Transactional
    public void updateStudent(Long studentId,
                              String name,
                              String email) {
        Student student = studentRespository.findById(studentId).orElseThrow(() -> new IllegalStateException(
            "student with id " + studentId + "does not exists"));

        if(name != null &&
        name.length()>0 &&
        !Objects.equals(student.getName(), name)){
            student.setName(name);
        }

        if(email !=null &&
        email.length()>0 &&
        !Objects.equals(student.getEmail(), email)){
            Optional<Student> studentByEmail = studentRespository.findStudentByEmail(email);
            if(studentByEmail.isPresent()){
                throw new IllegalStateException("Email taken");
            }
            student.setEmail(email);
        }
    }
}
