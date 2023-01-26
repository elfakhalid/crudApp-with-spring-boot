package com.crudApp.dao;

import com.crudApp.Entities.Customer;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CustomerRepository
        extends JpaRepository<Customer,Integer> {
}
