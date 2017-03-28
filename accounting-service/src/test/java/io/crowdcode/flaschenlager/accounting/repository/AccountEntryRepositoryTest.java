package io.crowdcode.flaschenlager.accounting.repository;

import io.crowdcode.flaschenlager.accounting.model.AccountEntry;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

import static io.crowdcode.flaschenlager.accounting.fixture.AccountEntryFixture.buildAccountEntry;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;

@RunWith(SpringRunner.class)
@DataJpaTest
public class AccountEntryRepositoryTest {

    @Autowired
    private AccountEntryRepository accountEntryRepository;

    @Autowired
    private TestEntityManager testEntityManager;

    @Test
    public void testSaveAccountEntry() throws Exception {
        AccountEntry entry = accountEntryRepository.save(
                buildAccountEntry(1l, 2l));

        assertThat(entry.getId(), is(notNullValue()));
    }

    @Test
    public void testFindByCustomerIdAndUserId() throws Exception {
        testEntityManager.persist(buildAccountEntry(1l, 1l));
        testEntityManager.persist(buildAccountEntry(1l, 2l));
        testEntityManager.persist(buildAccountEntry(1l, 3l));
        testEntityManager.persist(buildAccountEntry(2l, 4l));

        List<AccountEntry> entries = accountEntryRepository.findByCustomerIdAndUserId(1l, 3l);

        assertThat(entries, hasSize(1));
    }

    @Test
    public void testFindByCustomerId() throws Exception {
        testEntityManager.persist(buildAccountEntry(1l, 1l));
        testEntityManager.persist(buildAccountEntry(1l, 2l));
        testEntityManager.persist(buildAccountEntry(1l, 3l));
        testEntityManager.persist(buildAccountEntry(2l, 4l));

        List<AccountEntry> entries = accountEntryRepository.findByCustomerId(1l);

        assertThat(entries, hasSize(3));
    }

    @Test
    public void testFindByUserId() throws Exception {
        testEntityManager.persist(buildAccountEntry(1l, 1l));
        testEntityManager.persist(buildAccountEntry(1l, 2l));
        testEntityManager.persist(buildAccountEntry(1l, 3l));
        testEntityManager.persist(buildAccountEntry(2l, 4l));

        List<AccountEntry> entries = accountEntryRepository.findByUserId(4l);

        assertThat(entries, hasSize(1));
    }


}