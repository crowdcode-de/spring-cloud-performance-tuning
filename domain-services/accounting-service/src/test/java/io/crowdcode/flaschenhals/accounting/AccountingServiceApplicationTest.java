package io.crowdcode.flaschenhals.accounting;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.ApplicationContext;
import org.springframework.test.context.junit4.SpringRunner;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.notNullValue;

@RunWith(SpringRunner.class)
@SpringBootTest
public class AccountingServiceApplicationTest {


    @Autowired
    private ApplicationContext context;

    @Test
    public void testApplicationStartUp() throws Exception {
        assertThat(context, is(notNullValue()));
    }
}