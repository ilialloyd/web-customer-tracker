package com.example.trackerapp.service;
/*
 *           Leave your comment below if you have
 --------------------------------------------------------
 *
 */

import com.example.trackerapp.dao.CustomerDAO;
import com.example.trackerapp.entity.Customer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class CustomerServiceImpl implements CustomerService{

    //inject customer dao
    @Autowired
    private CustomerDAO customerDAO;

    //this method basically calls to CustomerDAO
    @Override
    @Transactional
    public List<Customer> getCustomers() {
        return customerDAO.getCustomers();
    }
}
