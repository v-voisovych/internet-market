package com.voisovych.internetmarket.servis;

import com.voisovych.internetmarket.model.Item;
import com.voisovych.internetmarket.repository.ItemCRUDRepository;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.TransactionSystemException;
import javax.persistence.EntityExistsException;
import java.text.ParseException;
import java.text.SimpleDateFormat;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;


@RunWith(SpringRunner.class)
@SpringBootTest
@TestPropertySource("/application-test.properties")
@Sql(value = {"/sql-query/item-query/create-item-before.sql"}, executionPhase = Sql.ExecutionPhase.BEFORE_TEST_METHOD)
@Sql(value = {"/sql-query/item-query/create-items-after.sql"}, executionPhase = Sql.ExecutionPhase.AFTER_TEST_METHOD)
public class ItemCRUDServiceTest {

//    @MockBean
    @Autowired
    private ItemCRUDRepository itemCRUDRepository;

    @Test
    public void findByTypeTest() {

        ItemCRUDService itemCRUDService = new ItemCRUDService(itemCRUDRepository);
        Assert.assertEquals(2, itemCRUDService.findByType("console").size());
    }

    @Test
    public void saveItemSuccessful() {
        ItemCRUDService itemCRUDService = new ItemCRUDService(itemCRUDRepository);

        Item itemOne = new Item();
        itemOne.setId(1);
        itemOne.setName("a");

        Assert.assertEquals(itemOne, itemCRUDService.save(itemOne));
    }

    @Test
    public void findByIdSuccessfulTest() throws ParseException {
        ItemCRUDService itemCRUDService = new ItemCRUDService(itemCRUDRepository);
        Date creationDate = new SimpleDateFormat("yyyy-MM-dd").parse("2020-12-27");
        Item item = new Item();

        item.setId(1);
        item.setName("playstation");
        item.setType("console");
        item.setDescription("console");
        item.setNumber(458);
        item.setPrice(12588.0f);
        item.setCount(45);
        item.setCreationDate(creationDate);

        Assert.assertEquals(item, itemCRUDService.findById(1l));
    }

    @Test
    public void deleteSuccessfulTest() {
        ItemCRUDService itemCRUDService = new ItemCRUDService(itemCRUDRepository);
        Assert.assertEquals(true, itemCRUDService.delete(2L));
        Assert.assertEquals(2, itemCRUDService.findAll().size());
    }

    @Test(expected = EntityExistsException.class)
    public void deleteWithExceptionTest() {
        ItemCRUDService itemCRUDService = new ItemCRUDService(itemCRUDRepository);
        itemCRUDService.delete(123L);
    }

    @Test
    public void saveAllSuccessfulTest() {
        ItemCRUDService itemCRUDService = new ItemCRUDService(itemCRUDRepository);
        List<Item> iterable = new ArrayList<>();
        Item itemOne = new Item();
        itemOne.setName("a");

        Item itemTwo = new Item();
        itemTwo.setName("b");

        iterable.add(itemOne);
        iterable.add(itemTwo);
        itemCRUDService.saveAll(iterable);
        Assert.assertEquals(5, itemCRUDService.findAll().size());
    }

    @Test(expected = TransactionSystemException.class)
    public void saveAllWithException() {
        ItemCRUDService itemCRUDService = new ItemCRUDService(itemCRUDRepository);
        List<Item> iterable = new ArrayList<>();
        iterable.add(new Item());
        itemCRUDService.saveAll(iterable);
    }
}