package sk.ygor.stackoverflow.q53207105;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.AutoConfigureTestEntityManager;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.test.context.junit4.SpringRunner;

import static org.assertj.core.api.Assertions.assertThat;

import javax.transaction.Transactional;

@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureTestEntityManager
@Transactional
public class AudiServiceTest {

    @Autowired
    private TestEntityManager entityManager;

    @Autowired
    private AudiService audiService;

    @Before
    public void setUp() throws Exception {
        entityManager.persist(new Audi("Alpha Giant"));
        entityManager.persist(new Audi("gamma Giant"));
        entityManager.persist(new Audi("Delta Dwarf"));
        entityManager.persist(new Audi("Beta Giant"));
        entityManager.persist(new Audi("beta Giant"));
        entityManager.persist(new Audi("Gamma Giant"));
        entityManager.persist(new Audi("alpha Dwarf"));
        entityManager.persist(new Audi("beta Dwarf"));
        entityManager.persist(new Audi("Delta Giant"));
        entityManager.persist(new Audi("Gamma Dwarf"));
        entityManager.persist(new Audi("Beta Dwarf"));
        entityManager.persist(new Audi("alpha Giant"));
        entityManager.persist(new Audi("gamma Dwarf"));
        entityManager.persist(new Audi("Alpha Dwarf"));
        entityManager.persist(new Audi("delta Giant"));
        entityManager.persist(new Audi("delta Dwarf"));
        entityManager.flush();
    }

    @Test
    public void testPaging() {
        Page<Audi> page = audiService.getPage(1, 3, "giant", AudiService.Sorting.descriptionCaseInsensitive);

        assertThat(page.getTotalElements()).isEqualTo(8);
        assertThat(page.getTotalPages()).isEqualTo(3);
        assertThat(page.getContent().size()).isEqualTo(3);
        System.out.println("page.getContent() = " + page.getContent());
        assertThat(page.getContent().get(0).getDescription().toLowerCase()).isEqualTo("beta giant");
        assertThat(page.getContent().get(1).getDescription().toLowerCase()).isEqualTo("delta giant");
        assertThat(page.getContent().get(2).getDescription().toLowerCase()).isEqualTo("delta giant");
    }

}
