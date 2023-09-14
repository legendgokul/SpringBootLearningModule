package com.example.FirstApp.student;

import org.springframework.stereotype.Service;

import java.util.ArrayList;

@Service
public class DummyService {

    public ArrayList<Student> GetStudentList(){
       /* Student s1 = new Student("Gokul","iamgokul@gmail.com",24);*/
        ArrayList<Student> StudentList = new ArrayList<Student>();
        /*StudentList.add(s1);*/
        return StudentList;
    }
}
