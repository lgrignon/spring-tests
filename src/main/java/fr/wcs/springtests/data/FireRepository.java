package fr.wcs.springtests.data;

import org.springframework.data.jpa.repository.JpaRepository;

public interface FireRepository extends JpaRepository<Fire, Long> {
    
}
