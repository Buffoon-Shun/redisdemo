package com.hzjd.redisdemo.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import com.hzjd.redisdemo.dao.PersonDAO;
import com.hzjd.redisdemo.domain.Person;

@Service
@CacheConfig(cacheNames = "person")
public class PersonService {
    @Autowired
    PersonDAO personDAO;

    @Cacheable(keyGenerator = "myKeyGenerator")
    public List<Person> getPersonList() {
        List<Person> list = personDAO.findAll();
        return list;
    }

    @Cacheable(key = "#p0")
    public Optional<Person> findById(Integer id) {
        Optional<Person> person = personDAO.findById(id);
        return person;
    }

    @CacheEvict(allEntries = true)
    public void addPerson(Person person) {
        personDAO.save(person);
    }

    @CacheEvict(allEntries = true)
    public void delete(Integer id) {
        personDAO.deleteById(id);
    }

    @CacheEvict(allEntries = true)
    public void update(Person person) {
        personDAO.save(person);
    }
}
