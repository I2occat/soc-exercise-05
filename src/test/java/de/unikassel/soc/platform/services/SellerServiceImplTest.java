package de.unikassel.soc.platform.services;

import de.unikassel.soc.platform.domain.Product;
import de.unikassel.soc.platform.domain.Seller;
import de.unikassel.soc.platform.repos.SellerRepo;
import de.unikassel.soc.platform.web.mappers.SellerMapper;
import de.unikassel.soc.platform.web.mappers.SellerMapperImpl;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;
import static org.mockito.Mockito.times;

class SellerServiceImplTest {

    static SellerMapper mapper;
    SellerServiceImpl sellerService;

    @Mock
    SellerRepo repo;

    @BeforeAll
    static void beforeAll() { mapper = new SellerMapperImpl(); }

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        sellerService = new SellerServiceImpl(repo, mapper);
    }

    @Test
    void deleteById() {
        List<Seller> sellerList = new ArrayList<>();
        UUID uuid1 = UUID.randomUUID(),
            uuid2 = UUID.randomUUID();
        sellerList.add(new Seller(uuid1, "Test", new ArrayList<Product>()));
        sellerList.add(new Seller(uuid2, "Test", new ArrayList<Product>()));
        when(repo.findByName("Test")).thenReturn(sellerList);
        assertEquals(2, sellerService.getSellersByName("Test").size());
        sellerService.deleteById(uuid2);
        when(repo.findByName("Test")).thenReturn(sellerList.subList(0,1));
        assertEquals(1, sellerService.getSellersByName("Test").size());
        verify(repo, times(1)).deleteById(uuid2);
    }
}