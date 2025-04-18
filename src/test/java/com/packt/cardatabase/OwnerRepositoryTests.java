package com.packt.cardatabase;

import com.packt.cardatabase.domain.Owner;
import com.packt.cardatabase.domain.OwnerRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import static org.assertj.core.api.Assertions.assertThat;

@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
public class OwnerRepositoryTests {

    @Autowired
    private OwnerRepository repository;

    @Test
    void saveOwner() {
        repository.save(new Owner("Lucy", "Smith"));
        assertThat(
                repository.findByFirstname("Lucy").isPresent()
        ).isTrue();
    }
}
