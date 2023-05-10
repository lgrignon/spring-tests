package fr.wcs.springtests;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import fr.wcs.springtests.data.FireRepository;
import io.cucumber.java.fr.*;

public class CaserneFeatureTest {

    @Autowired
    FireRepository fireRepository;

    @Etantdonné("les feux suivants:")
    public void givenProducts(List<List<String>> firesData) {
        System.out.println("firesData: " + firesData + " -- " + fireRepository);

        for (List<String> fireData : firesData) {
            
        } 
    }

    @Lorsque("je récupère le plus vieux feu")
    public void selectOldestFire() {
        // for instance, call fireService.getOldestFire()
    }

    @Alors("{int} feu(x) retourné et le nom est {string}")
    public void assertFires(int count, String name) {
        System.out.println("assertFires - count=" + count + " name=" + name + " -- " + fireRepository);
        // asserts like assertEquals(...)
    }
}
