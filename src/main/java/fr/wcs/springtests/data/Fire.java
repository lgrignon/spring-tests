package fr.wcs.springtests.data;

import java.time.Instant;

import org.springframework.validation.annotation.Validated;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;


@Validated
@Entity
public class Fire {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Min(value = 0, message = "severity must be positive")
    private int severity;

    private Instant date;

    public Fire() {
    }
    
    public Fire(@Min(value = 0, message = "severity must be positive") int severity, Instant date) {
        this.severity = severity;
        this.date = date;
    }

    public Long getId() {
        return id;
    }

    public int getSeverity() {
        return severity;
    }

    public Instant getDate() {
        return date;
    }

    
}
