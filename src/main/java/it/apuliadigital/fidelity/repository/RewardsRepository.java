package it.apuliadigital.fidelity.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import it.apuliadigital.fidelity.model.Rewards;

@Repository
public interface RewardsRepository extends JpaRepository<Rewards, Long> {

    Optional<Rewards> findByNomeIsContaining(String nome);

    List<Rewards> findByPuntiNecessari(int puntiNecessari);
}
