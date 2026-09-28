package com.example.OpenAPIAssignment.controller;

import java.util.List;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.example.OpenAPIAssignment.dto.CustomerDTO;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;

import org.springframework.web.bind.annotation.PathVariable;
import io.swagger.v3.oas.annotations.Parameter;

import org.springframework.web.bind.annotation.RequestParam;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@Tag(name = "Customer APIs",
description = "APIs for managing customers")
@RestController
public class CustomerController {
	@Operation(summary = "Get all Customers",
			description = "Retrieves a list of all customers from the system")
//	@ApiResponses({ @ApiResponse(responseCode = "200",
//	description = "Customers retrieved successfully"),
//			@ApiResponse(responseCode = "500",
//			description = "Internal server error") })
	@ApiResponses({
	    @ApiResponse(
	        responseCode = "20",
	        description = "Customers retrieved successfully",
	        content = @Content(
	            mediaType = "application/json",
	            schema = @Schema(
	                type = "array",
	                implementation = CustomerDTO.class
	            )
	        )
	    ),
	    @ApiResponse(
	        responseCode = "500",
	        description = "Internal server error"
	    )
	})
	@SecurityRequirement(name = "bearerAuth")
	@GetMapping("/api/customers")
	//public  CustomerDTO getCustomers() {
		public List<CustomerDTO> getCustomers(){
	    CustomerDTO customer1 = new CustomerDTO();

	    customer1.setCustomerId(101L);
	    customer1.setName("Gopi");
	    customer1.setEmail("gopi@gmail.com");

	    //return customer;
	    CustomerDTO customer2 = new CustomerDTO();
	    customer2.setCustomerId(102L);
	    customer2.setName("Ravi");
	    customer2.setEmail("ravi@gmail.com");

	    CustomerDTO customer3 = new CustomerDTO();
	    customer3.setCustomerId(103L);
	    customer3.setName("Priya");
	    customer3.setEmail("priya@gmail.com");

	    return List.of(customer1, customer2, customer3);
		
	}
	
	@Operation(
		    summary = "Create a new customer",
		    description = "Creates a new customer using the provided customer information.",
		    requestBody = @io.swagger.v3.oas.annotations.parameters.RequestBody(
		        description = "Customer information",
		        required = true,
		        content = @Content(
		            mediaType = "application/json",
		            schema = @Schema(implementation = CustomerDTO.class)
		        )
		    )
		)
	@ApiResponses({
		@ApiResponse(
			    responseCode = "201",
			    description = "Customer created successfully",
			    content = @Content(
			        mediaType = "application/json",
			        schema = @Schema(
			            implementation = CustomerDTO.class
			        )
			    )
			),
	    @ApiResponse(
	        responseCode = "400",
	        description = "Invalid customer data"
	    ),
	    @ApiResponse(
	        responseCode = "500",
	        description = "Internal server error"
	    )
	})
	@ResponseStatus(HttpStatus.CREATED)
	@PostMapping("/api/customers")
	public CustomerDTO createCustomer(
	        @Valid @RequestBody CustomerDTO customer) {

	    return customer;
	}
	
	
	@Operation(
		    summary = "Get customer by ID",
		    description = "Retrieves a customer using the customer ID."
		)
		@ApiResponses({
		    @ApiResponse(
		        responseCode = "200",
		        description = "Customer found successfully",
		        content = @Content(
		            mediaType = "application/json",
		            schema = @Schema(implementation = CustomerDTO.class)
		        )
		    ),
		    @ApiResponse(
		        responseCode = "404",
		        description = "Customer not found"
		    ),
		    @ApiResponse(
		        responseCode = "500",
		        description = "Internal server error"
		    )
		})
	    
		@GetMapping("/api/customers/{id}")
		public CustomerDTO getCustomerById(

		        @Parameter(
		            description = "Unique ID of the customer",
		            example = "101",
		            required = true
		        )
		        @PathVariable Long id) {

		    CustomerDTO customer = new CustomerDTO();

		    customer.setCustomerId(id);
		    customer.setName("Gopi");
		    customer.setEmail("gopi@gmail.com");

		    return customer;
		}
	
	@Operation(
		    summary = "Search customers by name",
		    description = "Searches for customers using the customer name."
		)
		@ApiResponses({
		    @ApiResponse(
		        responseCode = "200",
		        description = "Customers found successfully",
		        content = @Content(
		            mediaType = "application/json",
		            schema = @Schema(
		                type = "array",
		                implementation = CustomerDTO.class
		            )
		        )
		    ),
		    @ApiResponse(
		        responseCode = "400",
		        description = "Invalid search parameter"
		    ),
		    @ApiResponse(
		        responseCode = "500",
		        description = "Internal server error"
		    )
		})
		@GetMapping("/api/customers/search")
		public List<CustomerDTO> searchCustomers(

		        @Parameter(
		            description = "Name of the customer to search",
		            example = "Gopi",
		            required = true
		        )
		        @RequestParam String name) {

		    CustomerDTO customer = new CustomerDTO();

		    customer.setCustomerId(101L);
		    customer.setName(name);
		    customer.setEmail("gopi@gmail.com");

		    return List.of(customer);
		}
	
	
	@Operation(
		    summary = "Update an existing customer",
		    description = "Updates customer information using the customer ID."
		)
		@ApiResponses({
		    @ApiResponse(
		        responseCode = "200",
		        description = "Customer updated successfully",
		        content = @Content(
		            mediaType = "application/json",
		            schema = @Schema(
		                implementation = CustomerDTO.class
		            )
		        )
		    ),
		    @ApiResponse(
		        responseCode = "400",
		        description = "Invalid customer data"
		    ),
		    @ApiResponse(
		        responseCode = "404",
		        description = "Customer not found"
		    ),
		    @ApiResponse(
		        responseCode = "500",
		        description = "Internal server error"
		    )
		})
		@PutMapping("/api/customers/{id}")
		public CustomerDTO updateCustomer(

		        @Parameter(
		            description = "Unique ID of the customer",
		            example = "101",
		            required = true
		        )
		        @PathVariable Long id,

		        @Valid @RequestBody CustomerDTO customer) {

		    customer.setCustomerId(id);

		    return customer;
		}
	
	@Operation(
		    summary = "Delete a customer",
		    description = "Deletes an existing customer using the customer ID."
		)
		@ApiResponses({
		    @ApiResponse(
		        responseCode = "204",
		        description = "Customer deleted successfully"
		    ),
		    @ApiResponse(
		        responseCode = "404",
		        description = "Customer not found"
		    ),
		    @ApiResponse(
		        responseCode = "500",
		        description = "Internal server error"
		    )
		})
		@DeleteMapping("/api/customers/{id}")
		@ResponseStatus(HttpStatus.NO_CONTENT)
		public void deleteCustomer(

		        @Parameter(
		            description = "Unique ID of the customer",
		            example = "101",
		            required = true
		        )
		        @PathVariable Long id) {

		    System.out.println("Customer " + id + " deleted successfully");
		}

}