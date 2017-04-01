package io.crowdcode.flaschenhals.accounting.controller;

import io.crowdcode.flaschenhals.accounting.dto.EntryRequest;
import io.crowdcode.flaschenhals.accounting.model.AccountEntry;
import io.crowdcode.flaschenhals.accounting.service.AccountEntryService;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/account")
public class AccountEntryController {

    @Autowired
    private AccountEntryService accountEntryService;

    @ApiOperation("Booking list by customer")
    @GetMapping("/customer/{customerId}")
    public List<AccountEntry> getEntriesByCustomer(@PathVariable("customerId") Long customerId) {
        return accountEntryService.findCustomerEntries(customerId);
    }

    @ApiOperation("Booking list by customer and user")
    @GetMapping("/customer/{customerId}/user/{userId}")
    public List<AccountEntry> getEntriesByCustomerAndUser(@PathVariable("customerId") Long customerId,
                                                          @PathVariable("userId") Long userId) {
        return accountEntryService.findEntries(customerId, userId);
    }

    @ApiOperation("Booking list by user")
    @GetMapping("/user/{userId}")
    public List<AccountEntry> getEntriesByUser(@PathVariable("userId") Long userId) {
        return accountEntryService.findUserEntries(userId);
    }

    @ApiOperation("Create account entry")
    @PostMapping("/customer/{customerId}/user/{userId}")
    @ResponseStatus(HttpStatus.CREATED)
    public void createEntry(@PathVariable("customerId") Long customerId,
                            @PathVariable("userId") Long userId,
                            @RequestBody() EntryRequest entryRequest) {
        accountEntryService.book(customerId, userId, entryRequest.getProductId(),
                entryRequest.getPrice(), entryRequest.getQuantity());
    }



}
