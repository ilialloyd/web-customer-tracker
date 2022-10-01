package com.example.trackerapp.controller;

import com.example.trackerapp.dao.CustomerDAO;
import com.example.trackerapp.entity.Customer;
import com.example.trackerapp.service.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;


import java.util.List;

/*
 *           Leave your comment below if you have
 --------------------------------------------------------
 *https://stackoverflow.com/questions/504947/when-should-i-use-get-or-post-method-whats-the-difference-between-them
 *
 *
 */
@Controller
@RequestMapping("/customer") //classbase map
public class CustomerController {

    //inject the customer DAO - we remove here, because it weas for test.
//    @Autowired
//    private CustomerDAO customerDAO;

    @Autowired
    private CustomerService customerService;


    @GetMapping("/list") // customer/list // also @GetMApping will limit and will allow to only accept get reguest
    public String listCustomers(Model theModel){

        //get the customers from the service
        List<Customer> theCustomers = customerService.getCustomers();

        //add customers to the model
        theModel.addAttribute("customers",theCustomers);

        return "list-customers"; //this directing us to the jsp file
    }

    @GetMapping(name = "/showFormForAdd")
    public String showFormForAdd(Model theModel){
        return "customer-form";
    }


}
