package fr.wcs.springtests.data;

import java.time.Instant;
import java.util.ArrayList;
import java.util.List;

import org.hibernate.annotations.ManyToAny;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.OneToMany;
import jakarta.validation.constraints.Min;

@Entity
public class Fireman {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private String name;

    @ManyToMany()
    private List<Fire> fires = new ArrayList<>();

    public Fireman() {
    }

    public Fireman(String name, List<Fire> fires) {
        this.name = name;
        this.fires = fires;
    }

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public List<Fire> getFires() {
        return fires;
    }

}
