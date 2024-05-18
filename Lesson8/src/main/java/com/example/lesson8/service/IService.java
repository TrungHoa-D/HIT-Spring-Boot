package com.example.lesson8.service;

import org.springframework.stereotype.Service;

import java.util.List;
@Service
public interface IService {
    Object createObject(Object object);
    List<Object> readObjects();
    Object readObject(int id);
    Object updateObject(Object object);
    void deleteObject(int id);
}
