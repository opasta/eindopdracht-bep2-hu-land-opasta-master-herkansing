package nl.hu.bep2.casino.blackjack.data;

import nl.hu.bep2.casino.blackjack.domain.Game;
import nl.hu.bep2.casino.blackjack.domain.GameStatus;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface SpringGameRepository extends JpaRepository<Game, Long> {
    Optional<Game> findByIdAndPlayerName(Long id, String name);

    List<Game> findByPlayerNameAndStatus(String name, GameStatus status);
}
