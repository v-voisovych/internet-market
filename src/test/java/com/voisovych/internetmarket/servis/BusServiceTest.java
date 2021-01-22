package com.voisovych.internetmarket.servis;

import com.voisovych.internetmarket.model.Bus;
import com.voisovych.internetmarket.repository.BusRepository;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
@TestPropertySource("/application-test.properties")
@Sql(value = {"/sql-query/busses-query/create-buses-before.sql"}, executionPhase = Sql.ExecutionPhase.BEFORE_TEST_METHOD)
@Sql(value = {"/sql-query/busses-query/create-busses-after.sql"}, executionPhase = Sql.ExecutionPhase.AFTER_TEST_METHOD)
public class BusServiceTest {

    @Autowired
    BusRepository busRepository;

    @Test
    public void addAndFindAllBusSuccessful() {
        BusService busService = new BusService(busRepository);
        Bus bus = new Bus();
        int i = 0;
        busService.addBus(bus);

        for (Bus b: busService.allBuses()) {
            i++;
        }

        Assert.assertEquals(4,i);
    }

    @Test
    public void deleteSuccessful() {
        BusService busService = new BusService(busRepository);
        Assert.assertNotNull(busRepository.findByRouteNumber("545"));
        busService.delete("545");
        Assert.assertEquals(null, busRepository.findByRouteNumber("545"));
    }

}