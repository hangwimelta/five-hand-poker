package za.co.melta.five.hand.card.service;

import za.co.melta.five.hand.card.model.PokerHandResponse;

public interface PokerHandService {

    public PokerHandResponse shuffleHand();
    public PokerHandResponse shuffleHand(String hand);
}
