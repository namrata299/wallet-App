package com.example.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.model.Transaction;

@Repository
public interface Itransaction extends JpaRepository<Transaction, Integer> {

}
