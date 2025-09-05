package com.studentmgmt.dao;

import com.studentmgmt.model.Student;
import java.util.List;

public interface StudentDAO {
    int insert(Student s);
    List<Student> findAll();
    int update(Student s);
    int delete(int id);
    List<Student> findByName(String part); // bonus: search by name
}
