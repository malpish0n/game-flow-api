package com.malpishon.gameflow.game;


import jakarta.persistence.*;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Entity
@Table(name = "games")
public class Game {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Long igdbId;
    private String title;

    private BigDecimal purchasePrice;

    @Enumerated(EnumType.STRING)
    private GameStatus status;

    private LocalDateTime createdAt;

    public Game () {

    }

}
