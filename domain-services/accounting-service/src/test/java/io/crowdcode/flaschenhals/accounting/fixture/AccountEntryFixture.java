package io.crowdcode.flaschenhals.accounting.fixture;

import io.crowdcode.flaschenhals.accounting.model.AccountEntry;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.time.ZoneOffset;

public class AccountEntryFixture {

    private static long now = System.currentTimeMillis();
    private static int nanoOfSecond = 0;


    public static AccountEntry buildAccountEntry(Long customerId, Long userId) {
        return new AccountEntry()
                .setCustomerId(customerId)
                .setUserId(userId)
                .setProductId(1l)
                .setQuantity(10l)
                .setPrice(BigDecimal.valueOf(1.2d))
                .setDescription("Description")
                .setBookedAt(now());
    }

    private static LocalDateTime now() {
        return LocalDateTime.ofEpochSecond(now, nanoOfSecond++, ZoneOffset.UTC);
    }
}
