package com.crudApp.controllers;

import com.crudApp.Entities.Customer;
import com.crudApp.dao.CustomerRepository;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/customers")
public class CustomerController {
    private final CustomerRepository customerRepository;

    public CustomerController(CustomerRepository customerRepository) {
        this.customerRepository = customerRepository;
    }

    @GetMapping
    public List<Customer> getCustomers(){
        return customerRepository.findAll();
    }

    @PostMapping
    public Customer createCustomer(@RequestBody Customer customer){
        customer.setId(null);
        return customerRepository.save(customer);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Customer> updateCustomer(@RequestBody Customer updatedCustomer, @PathVariable("id") Integer id){
        Customer customer=customerRepository.findById(id).orElse(null);
        customer.setName(updatedCustomer.getName());
        customer.setEmail(updatedCustomer.getEmail());
        customer.setAge(updatedCustomer.getAge());
        customerRepository.save(customer);
        return ResponseEntity.ok(customer);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteCustomer(@PathVariable("id")Integer id){
        try {
            customerRepository.deleteById(id);
            return new ResponseEntity<>("customer deleted successfully", HttpStatus.OK);
        }catch (EmptyResultDataAccessException e){
            return new ResponseEntity<>("customer not found",HttpStatus.NOT_FOUND);
        }catch (Exception e) {
            return new ResponseEntity<>("Error deleting customer", HttpStatus.BAD_REQUEST);
        }
    }
}
