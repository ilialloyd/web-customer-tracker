package com.example.trackerapp.controller;

import com.example.trackerapp.entity.Customer;
import com.example.trackerapp.service.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

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
    public String listCustomers(Model theModel) {

        //get the customers from the service
        List<Customer> theCustomers = customerService.getCustomers();

        //add customers to the model
        theModel.addAttribute("customers", theCustomers);

        return "list-customers"; //this directing us to the jsp file
    }

    @GetMapping("/showFormForAdd")
    public String showFormForAdd(Model theModel) {
          /*
        We are adding the customer object to the model. this will support Spring MVC
        form data binding. Basically, when the form data is submitted,
         the customer object is automatically populated with the form data.
         We basically give empty object to the Model to go view page and if there will be new data
         we will put the data to the empty object and will bring it back to Controller
        */
        Customer customer = new Customer();
        theModel.addAttribute("customer", customer);
        return "customer-form";
    }

    @PostMapping("/saveCustomer")
    public String saveCustomer(@ModelAttribute("customer") Customer theCustomer) {

        //save the customer
        customerService.saveCustomer(theCustomer);
        return "redirect:/customer/list";
    }


    //When we click update, this method will show us form with user's old data.
    @GetMapping("/showFormForUpdate")
    public String showFormForUpdate(@RequestParam("customerId") int theId,
                                    Model theModel) {

        //get the customer from the customer service
        Customer theCustomer = customerService.getCustomer(theId);


        //set customer as a model attribute the pre-populate the form
        // (daxil olduqda update edilecek userin infolari avtomatik yazilsin)
        theModel.addAttribute("customer", theCustomer);

        //send over to our form


        return "customer-form";

    }


    @GetMapping("/delete")
    public String deleteCustomer(@RequestParam("customerId") int theId) {


        //delete the Customer
        customerService.deleteCustomer(theId);

        return "redirect:/customer/list";
    }

    @GetMapping("/search")
    public String searchCustomers(@RequestParam("theSearchName") String theSearchName,
                                  Model theModel) {

        //search customers from the list
        List<Customer> customers =
                customerService.searchCustomers(theSearchName);


        //add the customers to the model
        theModel.addAttribute("customers", customers);
        return "search-result";
    }

}
