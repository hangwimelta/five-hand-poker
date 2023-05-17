package za.co.melta.five.hand.card.service.algorithm.model;

import lombok.Data;
import za.co.melta.five.hand.card.service.algorithm.PokerHandUtil;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Data
public class PokerHandModel {

    private List<PokerHandUtil.Card> cards = new ArrayList<>();
    private Map<Integer, List<PokerHandUtil.Card>> cardsMap = new HashMap<>();
}
