package com.example.trackerapp.dao;
/*
 *           Leave your comment below if you have
 --------------------------------------------------------
 *
 */

import com.example.trackerapp.entity.Customer;

import java.util.List;

public interface CustomerDAO {
    public List<Customer> getCustomers();

    void saveCustomer(Customer theCustomer);

    Customer getCustomer(int theId);

    void deleteCustomer(int theId);

    List<Customer> searchCustomers(String theSearchName);
}
