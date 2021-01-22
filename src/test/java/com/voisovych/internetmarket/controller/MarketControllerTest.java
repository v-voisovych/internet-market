package com.voisovych.internetmarket.controller;

import com.voisovych.internetmarket.model.Item;
import com.voisovych.internetmarket.repository.ItemCRUDRepository;
import com.voisovych.internetmarket.servis.ItemCRUDService;
import org.junit.Assert;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import org.junit.runner.RunWith;
import org.mockito.Answers;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithUserDetails;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.util.NestedServletException;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.security.Principal;
import java.util.ArrayList;
import java.util.List;
import static org.mockito.Mockito.*;
import static org.springframework.security.test.web.servlet.response.SecurityMockMvcResultMatchers.authenticated;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
@WithUserDetails("admin")
public class MarketControllerTest {

//    @Rule
//    public ExpectedException thrown = ExpectedException.none();

    @Autowired
    private MockMvc mockMvc;

    @Mock
    ItemCRUDRepository itemCRUDRepository;

    @Mock
    ItemCRUDService itemCRUDService;

    @Mock(answer = Answers.RETURNS_DEEP_STUBS)
    Model model;

    @Mock(answer = Answers.RETURNS_DEEP_STUBS)
    Principal principal;

    @Mock(answer = Answers.RETURNS_DEEP_STUBS)
    BindingResult bindingResult;

    @InjectMocks
    MarketController marketController;

    @Test
    public void viewItemTest() throws Exception {

        ItemCRUDService itemCRUDServiceTwo = new ItemCRUDService(itemCRUDRepository);
        Item item = new Item();
        item.setName("dfdfdf");

        List<Item> listOne = new ArrayList<>();
        listOne.add(item);

        List<Item> listTwo = new ArrayList<>();
        listTwo.add(item);

        this.mockMvc.perform(get("/"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(authenticated());

        when(itemCRUDRepository.findAll()).thenReturn(listOne);
        Assert.assertEquals(listTwo, itemCRUDServiceTwo.findAll());

        when(principal.getName()).thenReturn("admin");
        marketController.viewItem(model, principal);
        verify(itemCRUDService, times(1)).findAll();
        verify(model, times(1)).addAttribute("list", itemCRUDService.findAll());
        verify(model, times(1)).addAttribute("username", principal.getName());
    }

    @Test
    public void findByTypeTest () throws Exception {
        this.mockMvc.perform(get("/type?type=console"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(authenticated());

        when(principal.getName()).thenReturn("admin");
        marketController.viewItemByType("console", model, principal);
        verify(itemCRUDService,times(1)).findByType("console");
        verify(model, times(1)).addAttribute("list", itemCRUDService.findByType("console"));
        verify(model, times(1)).addAttribute("username", principal.getName());
    }

    @Test
    public void editTest() throws Exception {
        this.mockMvc.perform(post("/edit?id=1"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(authenticated());
        Item item = new Item();
        item.setId(1);

        when(itemCRUDService.findById(1l)).thenReturn(item);
        when(principal.getName()).thenReturn("admin");
        marketController.edit(1l, model, principal);
        verify(itemCRUDService, times(1)).findById(1l);
        verify(model, times(1)).addAttribute("edit", itemCRUDService.findById(1l));
        verify(model, times(1)).addAttribute("username", principal.getName());
    }
    @Test
    public void saveTest() throws Throwable {
        Date creationDate = new SimpleDateFormat("yyyy-MM-dd").parse("2020-12-27");

        Item itemOne = new Item();

        Item itemTwo = new Item();
        itemTwo.setId(26);
        itemTwo.setName("PS5");
        itemTwo.setType("save");
        itemTwo.setDescription("description");
        itemTwo.setNumber(123);
        itemTwo.setPrice(123.0f);
        itemTwo.setCount(12345);
        itemTwo.setCreationDate(creationDate);

        when(itemCRUDService.save(itemOne)).thenReturn(itemOne);

        this.mockMvc.perform(post("/editsave")
                .param("id", "26")
                .param( "name", "PS5")
                .param("type","save")
                .param("description", "description")
                .param("number", "123")
                .param("price", "123.0")
                .param("count", "12345")
                .param("creationDate", "2020-12-27")
                .flashAttr("item", itemOne))
                .andDo(print())
                .andExpect(status().isFound())
                .andExpect(authenticated())
                .andExpect(redirectedUrl("/"));

        marketController.editSave(itemOne, bindingResult, "2020-12-27", model);
        verify(itemCRUDService, times(1)).save(itemOne);
        Assert.assertEquals(itemTwo, itemCRUDService.save(itemOne));
        verify(model, times(1)).addAttribute("id", itemOne.getId());
    }

//    @Test(expected = ParseException.class)
//    public void saveWithExceptionTest() throws Exception {
//        this.mockMvc.perform(post("/editsave")
//                .param("id", "26")
//                .param( "name", "PS5")
//                .param("type","save")
//                .param("description", "description")
//                .param("number", "123")
//                .param("price", "123.0")
//                .param("count", "12345")
//                .param("creationDate", "2020--27")
//                .flashAttr("item", new Item()));
//    }

}