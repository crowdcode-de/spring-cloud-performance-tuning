package io.crowdcode.flaschenhals.customer.controller;

import io.crowdcode.flaschenhals.customer.model.Customer;
import io.crowdcode.flaschenhals.customer.repository.CustomerRepository;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.util.List;

@Slf4j
@RestController
@RequestMapping(value = "/customer", produces = MediaType.APPLICATION_JSON_VALUE)
public class CustomerController {

    @Autowired
    private CustomerRepository customerRepository;

    @ApiOperation(value = "Create a new customer.")
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public void createCustomer(@Valid @RequestBody Customer customer) {
        log.debug("Creating customer {}", customer);

        customerRepository.save(customer);
    }

    @ApiOperation(value = "Find customer by Id.")
    @GetMapping("/{customerId}")
    public Customer findCustomer(@PathVariable("customerId") long customerId) {
        return customerRepository.findOne(customerId);
    }

    @ApiOperation("Find all customers.")
    @GetMapping
    public List<Customer> findAll() {
        return customerRepository.findAll();
    }

    @ApiOperation("Update customer")
    @PutMapping("/{customerId}")
    public Customer updateCustomer(@PathVariable("customerId") long customerId, @Valid @RequestBody Customer customer) {
        final Customer customerModel = customerRepository.findOne(customerId);

        customerModel.setName(customer.getName());
        customerModel.setAddress(customer.getAddress());
        customerModel.setEmail(customer.getEmail());

        log.debug("Saving customer {}", customerModel);

        return customerRepository.save(customerModel);
    }

}
