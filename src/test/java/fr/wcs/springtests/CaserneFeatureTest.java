package fr.wcs.springtests;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.time.Instant;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import fr.wcs.springtests.data.Fire;
import fr.wcs.springtests.data.FireRepository;
import io.cucumber.java.ca.Quan;
import io.cucumber.java.fr.*;

public class CaserneFeatureTest {

    @Autowired
    FireRepository fireRepository;

    Optional<Fire> selected = Optional.empty();

    @Etantdonné("les feux suivants:")
    public void givenProducts(List<List<String>> firesData) {
        System.out.println("firesData: " + firesData + " -- " + fireRepository);

        for (List<String> fireData : firesData) {
            var fire = new Fire(1, fireData.get(0), Instant.parse(fireData.get(1) + "-01-01T00:00:00.000Z"));
            fireRepository.saveAndFlush(fire);
        }
    }

    @Quand("je récupère le plus vieux feu")
    public void selectOldestFire() {
        selected = fireRepository.getOldestFire();
    }

    @Alors("{int} feu(x) retourné et le nom est {string}")
    public void assertFires(int count, String name) {
        System.out.println("assertFires - count=" + count + " name=" + name + " -- " + fireRepository);
        assertTrue(selected.isPresent());
        System.out.println("assertFires - selected name=" + selected.get().getName());
        assertEquals(name, selected.get().getName());
    }
}
