package com.example.cache.CacheData.controller;

import com.example.cache.CacheData.model.Student;
import com.example.cache.CacheData.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class StudentController
{

    @Autowired
    StudentService studentService;

    @RequestMapping("cache/{id}/{isCacheable}")
    public Student getMyStudent(@PathVariable("id") String studentId, @PathVariable("isCacheable") Boolean isCacheable)
    {
       return studentService.getMyStudentById(studentId,isCacheable);
    }

}
