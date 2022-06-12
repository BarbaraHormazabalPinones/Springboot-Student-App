package com.example.demo.student;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Service
public class StudentService {

    private final StudentRepository studentRepository;

    @Autowired
    public StudentService(StudentRepository studentRepository){
        this.studentRepository = studentRepository;
    }
    public List<Student> getStudents(){
        return studentRepository.findAll();
    }

    public void addNewStudent(Student student) {
        /* Se checkea a través de Optional si el email existe ya en el repositorio. En caso contrario se agrega
        al nuevo estudiante.
         */
        Optional<Student> StudentOptional = studentRepository.findStudentByEmail(student.getEmail());
        if(StudentOptional.isPresent()){
            throw new IllegalStateException("email taken");
        }
        studentRepository.save(student);
        System.out.println(student);
    }

    public void deleteStudent(Long studentID){
        boolean exists = studentRepository.existsById(studentID);
        if(!exists){
            throw new IllegalStateException("student with id" + studentID + "does not exist.");
        }

        studentRepository.deleteById(studentID);
    }
    @Transactional
    public void updateStudent(Long studentID, String name, String email){
        /*Primero se debe buscar al estudiante a modificar a través de su id */
        Student student = studentRepository.findById(studentID)
                .orElseThrow(() -> new IllegalStateException("student with id" + studentID + "does not exist."));

        if(name != null && name.length() > 0 && !Objects.equals(student.getName(),name)){
                student.setName(name);
        }
        if(email != null && email.length() > 0 && !Objects.equals(student.getEmail(),email)){
            Optional<Student> studentOptional = studentRepository.findStudentByEmail(email);
            if(studentOptional.isPresent()){
                throw new IllegalStateException("email taken");
            }
            student.setEmail(email);
        }
    }
}
