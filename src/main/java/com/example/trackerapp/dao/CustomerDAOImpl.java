package com.example.trackerapp.dao;
/*
 *           Leave your comment below if you have
 --------------------------------------------------------
 *read more on the HQL "like" clause here:
 * https://docs.jboss.org/hibernate/orm/5.2/userguide/html_single/Hibernate_User_Guide.html#hql-like-predicate
 */

import com.example.trackerapp.entity.Customer;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

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


        //create a query and sort it by last name
        Query<Customer> theQuery =
                currentSession.createQuery("from Customer order by lastName",
                        Customer.class);


        //execute query and get the result
        List<Customer> customers = theQuery.getResultList();
        //return result
        return customers;
    }

    @Override

    public void saveCustomer(Customer theCustomer) {

        //get current hibernate session
        Session currentSession = sessionFactory.getCurrentSession();

        //save or update the customer
        /*
        Behind of saveOrUpdate() method
        if(  given |primary key/id | is empty){
               then INSERT new customer
              }else if( given |primary key/id has a value already in the database){
                        UPDATE existing customer
        }
        save() method add new customer, update is updating existing one
         */
        currentSession.saveOrUpdate(theCustomer);
    }

    //this method brings customer by its id
    @Override
    public Customer getCustomer(int customerId) {

        //get the current hibernate session
        Session session = sessionFactory.getCurrentSession();

        //retrieve | read data from database using the primary key
        Customer goingToUpdateCustomer = session.get(Customer.class, customerId);

        return goingToUpdateCustomer;

    }

    @Override
    public void deleteCustomer(int theId) {
        //get the current hibernate session
        Session theSession = sessionFactory.getCurrentSession();

        //WAY 1 to delete object
        //delete object with its primary key - delete with Query
//        Query theQuery =
//                theSession.createQuery("delete from Customer where id=:customerId");
//
//        //set parameter
//        theQuery.setParameter("customerId",theId);
//
//        //execute query
//        theQuery.executeUpdate();


        // WAY2
        //retrieve | read data from database using the primary key
        Customer deleteCustomer = theSession.get(Customer.class, theId);


        //delete customer
        theSession.delete(deleteCustomer);


    }


    //this method search customers in database for CustomerService
    @Override
    public List<Customer> searchCustomers(String theSearchName) {

        //get the current hb session
        Session currentSession = sessionFactory.getCurrentSession();

        Query theQuery = null;

        //only search by name if theSearchName is not empty

        if (theSearchName != null && theSearchName.trim().length() > 0) {
            //search for first or lastName ...case insensitive

            theQuery = currentSession.createQuery("from Customer  where lower(firstName) like :theName or lower(lastName) like :theName",
                    Customer.class);

//We also make use of the "like" clause and the "%" wildcard characters. This will allow us to search for substrings.
// For example, if we have customers with last name of "Patel", "Patterson" ... then we can search for "Pat" and it will match on those names.
            theQuery.setParameter("theName", "%" +
                    theSearchName.toLowerCase() + "%");
        } else {
            //theSearchName is empty ... so just get all customers
            theQuery = currentSession.createQuery("from Customer", Customer.class);
        }

        //execute query
        List<Customer> customers = theQuery.getResultList();

        //return result
        return customers;
    }
}
