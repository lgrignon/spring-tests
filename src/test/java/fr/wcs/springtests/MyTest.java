package fr.wcs.springtests;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import fr.wcs.springtests.data.FireRepository;

//@EnableAutoConfiguration
@DataJpaTest
public class MyTest {
    
    @Autowired
    FireRepository fireRepository;

    @Test
    public void test() {
        var fires = fireRepository.findAll();
        System.out.println("FIRES=" + fires);
    }
}
