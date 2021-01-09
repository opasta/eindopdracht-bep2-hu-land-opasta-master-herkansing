package nl.hu.bep2.casino.blackjack.presentation.dto;

import javax.validation.constraints.Min;

public class StartDto {
    @Min(1)
    public Long bet;
}
