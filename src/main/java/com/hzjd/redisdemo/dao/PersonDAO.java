package com.hzjd.redisdemo.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.hzjd.redisdemo.domain.Person;

public interface PersonDAO extends JpaRepository<Person, Integer> {
}
