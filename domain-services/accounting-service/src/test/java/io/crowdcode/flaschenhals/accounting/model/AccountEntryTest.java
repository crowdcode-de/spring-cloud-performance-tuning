package io.crowdcode.flaschenhals.accounting.model;

import org.junit.Test;

import java.math.BigDecimal;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.is;

/**
 * Created by idueppe on 26.03.17.
 */
public class AccountEntryTest {

    @Test
    public void testTotalAmount() throws Exception {
        AccountEntry entry = new AccountEntry().setPrice(BigDecimal.ONE).setQuantity(12l);
        assertThat(entry.getTotalAmount(), is(equalTo(new BigDecimal("12"))));
    }


}