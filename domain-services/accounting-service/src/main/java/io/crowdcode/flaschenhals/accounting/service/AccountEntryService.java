package io.crowdcode.flaschenhals.accounting.service;

import io.crowdcode.flaschenhals.accounting.model.AccountEntry;
import io.crowdcode.flaschenhals.accounting.repository.AccountEntryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;

@Service
public class AccountEntryService {

    @Autowired
    private AccountEntryRepository repository;

    public void book(Long customerId, Long userId, Long productId, BigDecimal price, Long quantity) {
        String description = buildDescription(productId);
        AccountEntry entry = new AccountEntry()
                .setCustomerId(customerId)
                .setUserId(userId)
                .setProductId(productId)
                .setDescription(description)
                .setPrice(price)
                .setQuantity(quantity);

        repository.save(entry);
    }

    private String buildDescription(Long productId) {
        return "DESCRIPTION SHOULD BE FETCHED FROM PRODUCT-SERVICE FOR PRODUCTID "+productId;
    }

    public List<AccountEntry> findCustomerEntries(Long customerId) {
        return repository.findByCustomerId(customerId);
    }

    public List<AccountEntry> findEntries(Long customerId, Long userId) {
        return repository.findByCustomerIdAndUserId(customerId, userId);
    }

    public List<AccountEntry> findUserEntries(Long userId) {
        return repository.findByUserId(userId);
    }
}
