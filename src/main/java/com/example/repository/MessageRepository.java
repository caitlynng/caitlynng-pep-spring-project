package com.example.repository;

import com.example.entity.Account;
import com.example.entity.Message;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface MessageRepository  extends JpaRepository<Message, Integer> {
   //8R
    @Query("SELECT m FROM Message m WHERE m.posted_by = :accountId")
    List<Message> findAllByPostedBy(@Param("accountId") int accountId);
}
