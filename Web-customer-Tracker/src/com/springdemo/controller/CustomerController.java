package com.springdemo.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.springdemo.dao.CustomerDAO;
import com.springdemo.entity.Customer;
import com.springdemo.service.CustomerService;


@Controller
@RequestMapping("/customer")
public class CustomerController {
	         
	      // need to inject the customer dao
	     //Spring will scan for a component that implements 
	    //CustomeDAO in our case it is implemented in CustomerDAOImpl
	    
	    @Autowired
	    private CustomerService customerService;
	
	    @GetMapping("/list")
	    public String listCustomer(Model theModel){
	    	
	    	//get customer from the dao
	    	List<Customer> theCustomers = customerService.getCustomers();
	    	
	    	//add the customer to the model
	    	theModel.addAttribute("customers",theCustomers);
	    	
	    	
	    	return "list-customers";
	    }
         
	     @GetMapping("/showFormForAdd")
	     public String showFormForAdd(Model theModel)
	     {
	    	 //create model attribute to bind form data
	    	 Customer theCustomer = new Customer();
	    	 
	    	 theModel.addAttribute("customer",theCustomer);
	    	 
	    	 return "customer-form";
	     }
	
	      @PostMapping("/saveCustomer")
	      public String saveCustomer(@ModelAttribute("customer") Customer theCustomer)
	      {
	    	  //save the customer using our service
	    	  customerService.saveCustomer(theCustomer);
	    	  
	    	  
			return "redirect:/customer/list";
			}
	      
	      @GetMapping("/showFormForUpdate")
	      public String showFormForUpdate(@RequestParam("customerId") int theId,
	    		                                         Model theModel)
	      {
	    	  //get the customer from the databae
	    	  
	    	    Customer theCustomer = customerService.getCustomers(theId);
	    	    
	    	  
	    	  //set customer as a model attribute to pre-populate the form
	    	  
	    	    theModel.addAttribute("customer", theCustomer);
	    	  
	    	  
	    	  //send over to our form
	    	   return "customer-form";
	    	  
	      }
	         @GetMapping("/delete")
	         public String deleteCustomer(@RequestParam("customer")  int theId)
	         {
	        	 customerService.deleteCustomer(theId);
	        	 
	        	  
	        	 return "redirect:/customer/list";
	         }
}
