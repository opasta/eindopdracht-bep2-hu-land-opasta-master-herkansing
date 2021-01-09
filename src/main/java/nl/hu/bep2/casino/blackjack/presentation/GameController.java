package nl.hu.bep2.casino.blackjack.presentation;

import nl.hu.bep2.casino.blackjack.application.GameService;
import nl.hu.bep2.casino.blackjack.domain.Game;
import nl.hu.bep2.casino.blackjack.domain.GameNotFound;
import nl.hu.bep2.casino.blackjack.domain.GameStatus;
import nl.hu.bep2.casino.blackjack.domain.Progress;
import nl.hu.bep2.casino.blackjack.presentation.dto.StartDto;
import nl.hu.bep2.casino.chips.data.InsufficientFundsException;
import nl.hu.bep2.casino.security.data.UserProfile;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import javax.validation.Valid;

@RestController
@RequestMapping("/game")
public class GameController {
    private GameService gameService;

    public GameController(GameService gameService) {
        this.gameService = gameService;
    }

    @PostMapping
    public Progress startGame(Authentication authentication, @Valid @RequestBody StartDto dto) {
        UserProfile profile = (UserProfile) authentication.getPrincipal();
        try {
            Progress progress = this.gameService.findByplayerName(profile.getUsername(), GameStatus.PLAYING);
            return progress;
        } catch (Exception e) {
            try {
                Progress progress = this.gameService.start(profile.getUsername(), dto.bet);
                return progress;
            } catch (InsufficientFundsException ez) {
                throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Insufficient funds");
            }
        }
    }

    @PostMapping("/hit")
    public Progress continueGame(Authentication authentication) {
        UserProfile profile = (UserProfile) authentication.getPrincipal();
        try {
            Game game = this.gameService.findByProgress(profile.getUsername(), GameStatus.PLAYING);
            Progress progress = this.gameService.hit(game);
            return progress;
        } catch (Exception e) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        }
    }

    @PostMapping("/surrender")
    public Progress surrenderGame(Authentication authentication) {
        UserProfile profile = (UserProfile) authentication.getPrincipal();
        try {
            Game game = this.gameService.findByProgress(profile.getUsername(), GameStatus.PLAYING);
            Progress progress = this.gameService.surrender(game);
            return progress;
        } catch (Exception e) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        }
    }

    @PostMapping("/stand")
    public Progress finishGame(Authentication authentication) {
        UserProfile profile = (UserProfile) authentication.getPrincipal();
        try {
            Game game = this.gameService.findByProgress(profile.getUsername(), GameStatus.PLAYING);
            Progress progress = this.gameService.stand(game);
            return progress;
        } catch (Exception e) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        }
    }

    @PostMapping("/double")
    public Progress finishGamebyDouble(Authentication authentication, @Valid @RequestBody StartDto dto) {
        UserProfile profile = (UserProfile) authentication.getPrincipal();
        try {
            Game game = this.gameService.findByProgress(profile.getUsername(), GameStatus.PLAYING);
            Progress progress = this.gameService.doubler(game);
            return progress;
        } catch (Exception e) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping("/{id}")
    public Progress findGame(Authentication authentication, @PathVariable Long id) {
        UserProfile profile = (UserProfile) authentication.getPrincipal();
        try {
            Progress progress = this.gameService.findByIdAndPlayerName(id, profile.getUsername());
            return progress;
        } catch (Exception e) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping("/getFirst")
    public Progress findFirstGame(Authentication authentication) {
        UserProfile profile = (UserProfile) authentication.getPrincipal();
        try {
            Progress progress = this.gameService.findByplayerName(profile.getUsername(), GameStatus.PLAYING);
            return progress;
        } catch (Exception e) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping("/getByProgress")
    public Game getGamebyProgress(Authentication authentication) {
        UserProfile profile = (UserProfile) authentication.getPrincipal();
        try {
            Game game = this.gameService.findByProgress(profile.getUsername(), GameStatus.PLAYING);
            return game;
        } catch (Exception e) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        }
    }
}
