package com.example.trackerapp.dao;
/*
 *           Leave your comment below if you have
 --------------------------------------------------------
 *
 */

import com.example.trackerapp.entity.Customer;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;


import java.util.List;

//Spring @Repository annotation is used to indicate that the class
// provides the mechanism for storage, retrieval, search, update and delete operation on objects.

@Repository
public class CustomerDAOImpl implements CustomerDAO {


    // inject the session factory
    @Autowired
    private SessionFactory sessionFactory;


    @Override
    //@Transactional  --we remove transactional from here, because we this in service IMPL
    public List<Customer> getCustomers() {

        //get the current hibernate session
        Session currentSession = sessionFactory.openSession();


        //create a query
        Query<Customer> theQuery = currentSession.createQuery("from Customer", Customer.class);



        //execute query and get the result
        List<Customer> customers = theQuery.getResultList();
        //return result
        return customers;
    }
}
