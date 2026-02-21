package com.malpishon.gameflow.game;

import com.malpishon.gameflow.external.igdb.IgdbClient;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/games")
public class GameController {

    private final GameService gameService;
    private final IgdbClient igdbClient;

    public GameController(GameService gameService, IgdbClient igdbClient) {
        this.gameService = gameService;
        this.igdbClient = igdbClient;
    }

    @PostMapping
    public Game addGame(@RequestBody Game game) {
        return gameService.saveGame(game);
    }

    @GetMapping("/search")
    public String searchGames(@RequestParam String title) {
        return igdbClient.searchGames(title);
    }
}
