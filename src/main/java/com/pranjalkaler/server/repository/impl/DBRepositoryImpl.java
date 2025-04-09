package com.pranjalkaler.server.repository.impl;

import com.pranjalkaler.server.models.Message;
import com.pranjalkaler.server.models.User;
import com.pranjalkaler.server.repository.DBRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.lang.annotation.Annotation;
import java.util.List;

@Repository
public class DBRepositoryImpl implements Repository, DBRepository {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    public DBRepositoryImpl() {

    }

    @Override
    public Integer testDB() {
        String sql = "SELECT COUNT(*) FROM TEST_TABLE";
        return jdbcTemplate.queryForObject(sql, Integer.class);
    }

    @Override
    public void storeMessage(Message message) {

    }

    @Override
    public void addUser() {

    }

    @Override
    public User getUser(String username) {
        return null;
    }

    @Override
    public List<Message> getAllMessageS() {
        return List.of();
    }

    @Override
    public List<Message> getMessagesForUser() {
        return List.of();
    }

    @Override
    public String value() {
        return "";
    }

    @Override
    public Class<? extends Annotation> annotationType() {
        return null;
    }
}
