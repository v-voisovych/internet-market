package com.voisovych.internetmarket.controller;

import com.voisovych.internetmarket.model.Item;
import com.voisovych.internetmarket.repository.ItemCRUDRepository;;
import com.voisovych.internetmarket.servis.ItemCRUDService;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithUserDetails;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.web.util.NestedServletException;
import sun.security.acl.PrincipalImpl;

import java.util.ArrayList;
import java.util.List;
import static org.hamcrest.Matchers.*;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.*;
import static org.springframework.security.test.web.servlet.response.SecurityMockMvcResultMatchers.authenticated;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;

@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
@WithUserDetails("admin")
@TestPropertySource("/application-test.properties")
@Sql(value = {"/sql-query/item-query/create-item-before.sql", "/sql-query/user-query/create-user-before.sql"}, executionPhase = Sql.ExecutionPhase.BEFORE_TEST_METHOD)
@Sql(value = {"/sql-query/item-query/create-items-after.sql", "/sql-query/user-query/create-user-after.sql"}, executionPhase = Sql.ExecutionPhase.AFTER_TEST_METHOD)
public class MyRestApiTest {

    @Autowired
    private MockMvc mockMvc;

//    @MockBean
//    ItemCRUDRepository itemCRUDRepository;

    @Autowired
    private MyRestApi myRestApi;

    @Autowired
    private ItemCRUDRepository itemCRUDRepository;

    @Test
    public void allItemsRestFoundTest() throws Exception {

        this.mockMvc.perform(get("/allItemRest"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(authenticated())
                .andExpect(jsonPath("$", hasSize(3)))
                .andExpect(jsonPath("$[*].id", containsInAnyOrder(1, 2, 3)))
                .andExpect(jsonPath("$[*].name", containsInAnyOrder("playstation", "sony", "playstation")))
                .andExpect(jsonPath("$[*].count", containsInAnyOrder(45, 46, 47)));
    }

    @Test(expected = Exception.class)
    public void allItemsRestWithExceptionTest() throws Exception {
        ItemCRUDRepository itemCRUDRepository = Mockito.mock(ItemCRUDRepository.class);
        MockMvc mockMvcWithException =  Mockito.mock(MockMvc.class);

        List<Item> list = new ArrayList<>();
        when(itemCRUDRepository.findAll()).thenReturn(list);
        mockMvcWithException.perform(get("/allItemRest"));

    }

    @Test
    public void findItemsRestSuccessfulTest() throws Exception {

        this.mockMvc.perform(get("/findItemRest?id=1"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(authenticated())
                .andExpect(jsonPath("$.id", comparesEqualTo(1)))
                .andExpect(jsonPath("$.name", comparesEqualTo("playstation")))
                .andExpect(jsonPath("$.count", comparesEqualTo(45)));
    }

    @Test
    public void addItemRestSuccessfulTest() throws Exception {
        ItemCRUDService itemCRUDService = new ItemCRUDService(itemCRUDRepository);
        MockHttpServletRequestBuilder multipart = MockMvcRequestBuilders.multipart("/addItemRest")
                .contentType(MediaType.APPLICATION_JSON)
                .content("  {\n" +
                        "        \"id\": 37,\n" +
                        "        \"name\": \"playstation\",\n" +
                        "        \"type\": \"console\",\n" +
                        "        \"description\": \"ps3\",\n" +
                        "        \"number\": 5,\n" +
                        "        \"price\": 12000.0,\n" +
                        "        \"count\": 45,\n" +
                        "        \"creationDate\": \"2020-12-15\"\n" +
                        "    }");

        this.mockMvc.perform(multipart)
                .andDo(print())
                .andExpect(authenticated())
                .andExpect(status().isOk());

        Assert.assertEquals(4, itemCRUDService.findAll().size());
    }

    @Test
    public void editItemRestSuccessfulTest() throws Exception {
        ItemCRUDService itemCRUDService = new ItemCRUDService(itemCRUDRepository);
        MockHttpServletRequestBuilder multipart = MockMvcRequestBuilders.multipart("/addItemRest")
                .contentType(MediaType.APPLICATION_JSON)
                .content("  {\n" +
                        "        \"id\": 1,\n" +
                        "        \"name\": \"playstation\",\n" +
                        "        \"type\": \"console\",\n" +
                        "        \"description\": \"ps3\",\n" +
                        "        \"number\": 5,\n" +
                        "        \"price\": 12000.0,\n" +
                        "        \"count\": 45,\n" +
                        "        \"creationDate\": \"2020-12-15\"\n" +
                        "    }");

        this.mockMvc.perform(multipart)
                .andDo(print())
                .andExpect(authenticated())
                .andExpect(status().isOk());

        Assert.assertEquals(3, itemCRUDService.findAll().size());
    }

    @Test
    public void addAndEditItemRestWithExceptionTest() throws Exception {
        MockHttpServletRequestBuilder multipart = MockMvcRequestBuilders.multipart("/addItemRest")
                .contentType(MediaType.APPLICATION_JSON)
                .content("  {\n" +
                        "        \"id\": 1,\n" +
                        "        \"type\": \"console\",\n" +
                        "        \"description\": \"ps3\",\n" +
                        "        \"number\": 5,\n" +
                        "        \"price\": 12000.0,\n" +
                        "        \"count\": 45,\n" +
                        "        \"creationDate\": \"2020-12-15\"\n" +
                        "    }");

        this.mockMvc.perform(multipart)
                .andDo(print())
                .andExpect(authenticated())
                .andExpect(status().is4xxClientError());
    }

    @Test
    public void deleteSuccessfulTest() throws Exception {
        ItemCRUDService itemCRUDService = new ItemCRUDService(itemCRUDRepository);

        this.mockMvc.perform(post("/deleteItemRest?id=1"))
                .andDo(print())
                .andExpect(authenticated())
                .andExpect(status().isOk());

        Assert.assertEquals(2, itemCRUDService.findAll().size());

    }

//    EntityExistsException
    @Test(expected = NestedServletException.class)
    public void deleteNotSuccessfulTest() throws Exception {
        this.mockMvc.perform(post("/deleteItemRest?id=145"))
                .andDo(print())
                .andExpect(authenticated())
                .andExpect(status().isBadRequest());
    }

    @Test
    public void addListRestWithErrorTest() throws Exception {
        MockHttpServletRequestBuilder multipart = MockMvcRequestBuilders.multipart("/addListItemsRest")
                .contentType(MediaType.APPLICATION_JSON)
                .content("[\n" +
                        "    {\n" +
                        "        \"id\": 21,\n" +
                        "        \"name\": \"playstation5\",\n" +
                        "        \"type\": \"console\",\n" +
                        "        \"description\": \"ps5pro\",\n" +
                        "        \"number\": 5,\n" +
                        "        \"price\": 12000.0,\n" +
                        "        \"count\": 12,\n" +
                        "        \"creationDate\": \"2020-12-15\"\n" +
                        "    },\n" +
                        "    {\n" +
                        "        \"id\": 22,\n" +
                        "        \"name\": \"playstation5\",\n" +
                        "        \"type\": \"console\",\n" +
                        "        \"description\": \"ps3\",\n" +
                        "        \"number\": 5,\n" +
                        "        \"price\": 12000.0,\n" +
                        "        \"count\": \"1d23\",\n" +
                        "        \"creationDate\": \"2020-12-15\"\n" +
                        "    },\n" +
                        "    {\n" +
                        "        \"id\": 37,\n" +
                        "        \"name\": \"null\",\n" +
                        "        \"type\": \"console\",\n" +
                        "        \"description\": \"ps3\",\n" +
                        "        \"number\": 5,\n" +
                        "        \"price\": 12000.0,\n" +
                        "        \"count\": 123,\n" +
                        "        \"creationDate\": \"2020-12-15\"\n" +
                        "    }\n" +
                        "]");

        this.mockMvc.perform(multipart)
                .andDo(print())
                .andExpect(authenticated())
                .andExpect(status().is4xxClientError());
    }

    @Test
    public void addListRestSuccessfulTest() throws Exception {
        ItemCRUDService itemCRUDService = new ItemCRUDService(itemCRUDRepository);
        MockHttpServletRequestBuilder multipart = MockMvcRequestBuilders.multipart("/addListItemsRest")
                .contentType(MediaType.APPLICATION_JSON)
                .content("[\n" +
                        "    {\n" +
                        "        \"id\": 21,\n" +
                        "        \"name\": \"playstation5\",\n" +
                        "        \"type\": \"console\",\n" +
                        "        \"description\": \"ps5pro\",\n" +
                        "        \"number\": 5,\n" +
                        "        \"price\": 12000.0,\n" +
                        "        \"count\": 12,\n" +
                        "        \"creationDate\": \"2020-12-15\"\n" +
                        "    },\n" +
                        "    {\n" +
                        "        \"id\": 22,\n" +
                        "        \"name\": \"playstation5\",\n" +
                        "        \"type\": \"console\",\n" +
                        "        \"description\": \"ps3\",\n" +
                        "        \"number\": 5,\n" +
                        "        \"price\": 12000.0,\n" +
                        "        \"count\": 123,\n" +
                        "        \"creationDate\": \"2020-12-15\"\n" +
                        "    },\n" +
                        "    {\n" +
                        "        \"id\": 37,\n" +
                        "        \"name\": \"null\",\n" +
                        "        \"type\": \"console\",\n" +
                        "        \"description\": \"ps3\",\n" +
                        "        \"number\": 5,\n" +
                        "        \"price\": 12000.0,\n" +
                        "        \"count\": 123,\n" +
                        "        \"creationDate\": \"2020-12-15\"\n" +
                        "    }\n" +
                        "]");

        this.mockMvc.perform(multipart)
                .andDo(print())
                .andExpect(authenticated())
                .andExpect(status().isOk());

        Assert.assertEquals(6, itemCRUDService.findAll().size());
    }

}