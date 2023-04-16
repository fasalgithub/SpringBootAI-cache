package com.example.cache.CacheData.service;

import com.example.cache.CacheData.model.Student;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class StudentService
{

    public static List<Student> studentList = new ArrayList<>();


    static
    {
        studentList.add(new Student("101","Sathya","ECE"));
        studentList.add(new Student("102","Abi","ECE"));
        studentList.add(new Student("103","Arun","ECE"));
        studentList.add(new Student("104","Riya","ECE"));
    }


    @CacheEvict(value = "Least-Time-Cache",key = "'studentCache'+ #id",condition = " #isCacheable==null || !#isCacheable ",beforeInvocation = true)
    @Cacheable(value = "Least-Time-Cache",key = "'studentCache'+ #id",condition = " #isCacheable!=null && #isCacheable")
    public Student getMyStudentById(String id, boolean isCacheable)
    {
        try {
            Thread.sleep(5000L);
        } catch (InterruptedException e)
        {
            throw new RuntimeException(e);
        }
        return studentList.stream()
                .filter(student -> id.equals(student.getId()))
                .findFirst().orElse(new Student());
    }

}
