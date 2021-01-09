package nl.hu.bep2.casino.blackjack.application;

import nl.hu.bep2.casino.blackjack.data.SpringGameRepository;
import nl.hu.bep2.casino.blackjack.domain.Game;
import nl.hu.bep2.casino.blackjack.domain.GameNotFound;
import nl.hu.bep2.casino.blackjack.domain.GameStatus;
import nl.hu.bep2.casino.blackjack.domain.Progress;
import nl.hu.bep2.casino.chips.application.ChipsService;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
@Transactional
public class GameService {
    private ChipsService chipsService;
    private SpringGameRepository gameRepository;

    public GameService(ChipsService chipsService, SpringGameRepository gameRepository) {
        this.chipsService = chipsService;
        this.gameRepository = gameRepository;
    }

    public Progress start(String username, Long bet) {
        this.chipsService.withdrawChips(username, bet);

        Game game = new Game();
        game.start(username, bet);

        this.gameRepository.save(game);

        return game.getProgress();
    }

    public Progress hit(Game game) {
        game.hit(game.getPlayerName(), game.getDeck(), game.getPlayerHand());
        this.gameRepository.save(game);

        return game.getProgress();
    }

    public Progress surrender(Game game) {
        game.surrender(game.getPlayerName(), game.getBet(), game.getPlayerHand());
        this.gameRepository.save(game);

        this.chipsService.depositChips(game.getPlayerName(), game.getBet());
        return game.getProgress();
    }

    public Progress stand(Game game) {
        game.calculateDealer(game.getPlayerName(), game.getDeck(), game.getPlayerHand(), game.getDealerHand(), game.getBet());
        this.gameRepository.save(game);

        this.chipsService.depositChips(game.getPlayerName(), game.getBet());
        return game.getProgress();
    }

    public Progress doubler(Game game) {
        this.chipsService.withdrawChips(game.getPlayerName(), game.getBet());
        game.hit(game.getPlayerName(), game.getDeck(), game.getPlayerHand());
        this.gameRepository.save(game);

        Long doubleBet = (game.getBet() * 2);

        game.calculateDealer(game.getPlayerName(), game.getDeck(), game.getPlayerHand(), game.getDealerHand(), doubleBet);
        this.gameRepository.save(game);

        this.chipsService.depositChips(game.getPlayerName(), game.getBet());
        return game.getProgress();
    }


    public Progress findByIdAndPlayerName(Long id, String username) {
        Game game = this.gameRepository.findByIdAndPlayerName(id, username)
                .orElseThrow(GameNotFound::new);

        return game.getProgress();
    }

    public Progress findByplayerName(String username, GameStatus status) {
        List<Game> game = this.gameRepository.findByPlayerNameAndStatus(username, status);
        return game.get(0).getProgress();
    }

    public Game findByProgress(String username, GameStatus status) {
        List<Game> game = this.gameRepository.findByPlayerNameAndStatus(username, status);
        return game.get(0);
    }
}
