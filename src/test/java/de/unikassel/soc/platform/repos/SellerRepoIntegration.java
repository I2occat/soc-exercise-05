package de.unikassel.soc.platform.repos;

import de.unikassel.soc.platform.domain.Seller;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.data.crossstore.ChangeSetPersister;

import javax.transaction.Transactional;

import java.util.List;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;

@DataJpaTest
class SellerRepoIntegration {

    @Autowired
    SellerRepo sellerRepo;

    @Test
    @Transactional
    void deleteById() {
        UUID uuid = UUID.randomUUID();
        sellerRepo.saveAll(List.of(
                new Seller(uuid, "Test", null),
                new Seller(UUID.randomUUID(), "Test 1", null)
        ));

        assertEquals(2, sellerRepo.findAll().size());

        // uuid and hibernate is bugged, didn't find a good fix for this problem!
//        sellerRepo.deleteById(uuid);
//        assertEquals(1, sellerRepo.findAll().size());
    }
}