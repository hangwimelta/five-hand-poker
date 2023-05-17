package za.co.melta.five.hand.card.service.algorithm;

import lombok.Data;
import za.co.melta.five.hand.card.service.algorithm.model.PokerHandModel;

import java.util.Comparator;
import java.util.List;
import java.util.Map;

import static java.util.stream.Collectors.groupingBy;
import static za.co.melta.five.hand.card.util.Constants.FLUSH;
import static za.co.melta.five.hand.card.util.Constants.FOUR_OF_A_KIND;
import static za.co.melta.five.hand.card.util.Constants.FULL_HOUSE;
import static za.co.melta.five.hand.card.util.Constants.HIGH_CARD;
import static za.co.melta.five.hand.card.util.Constants.ONE_PAIR;
import static za.co.melta.five.hand.card.util.Constants.STRAIGHT;
import static za.co.melta.five.hand.card.util.Constants.STRAIGHT_FLUSH;
import static za.co.melta.five.hand.card.util.Constants.THREE_OF_A_KIND;
import static za.co.melta.five.hand.card.util.Constants.TWO_PAIR;

/**
 * This is a repurposed poker hand algorithm. Original project on GitHub
 * https://github.com/ashwanikumar04/poker-texas-hold-em-java
 */

public class PokerHandUtil {

    private PokerHandUtil() {
    }

    public static PokerHandModel buildPokerHand(String handString) {
        PokerHandModel pokerHandModel = new PokerHandModel();
        String[] cardsString = handString.split(" ");
        for (String card : cardsString) {
            pokerHandModel.getCards().add(new Card(card));
        }
        pokerHandModel.getCards().sort(Comparator.comparingInt(o -> o.value));
        pokerHandModel.setCardsMap(Helpers.getValueMap(pokerHandModel.getCards()));

        return pokerHandModel;
    }

    public static String rank(PokerHandModel pokerHandModel) {
        if (isStraightFlush(pokerHandModel.getCards())) {
            return STRAIGHT_FLUSH;
        } else if (isFourOfAKind(pokerHandModel.getCardsMap())) {
            return FOUR_OF_A_KIND;
        } else if (isFullHouse(pokerHandModel.getCardsMap())) {
            return FULL_HOUSE;
        } else if (isFlush(pokerHandModel.getCards())) {
            return FLUSH;
        } else if (isStraight(pokerHandModel.getCards())) {
            return STRAIGHT;
        } else if (isThreeOfAKind(pokerHandModel.getCardsMap())) {
            return THREE_OF_A_KIND;
        } else if (isTwoPair(pokerHandModel.getCardsMap())) {
            return TWO_PAIR;
        } else if (isOnePair(pokerHandModel.getCardsMap())) {
            return ONE_PAIR;
        } else {
            return HIGH_CARD;
        }
    }

    private static boolean isOnePair(Map<Integer, List<Card>> cardsMap) {
        return Helpers.getCountOfGroupOfASize(cardsMap, 2) == 1;
    }

    private static boolean isTwoPair(Map<Integer, List<Card>> cardsMap) {
        return Helpers.getCountOfGroupOfASize(cardsMap, 2) == 2;
    }

    private static boolean isThreeOfAKind(Map<Integer, List<Card>> cardsMap) {
        return Helpers.getCountOfGroupOfASize(cardsMap, 3) == 1;
    }

    private static boolean isFullHouse(Map<Integer, List<Card>> cardsMap) {
        return isThreeOfAKind(cardsMap) && isOnePair(cardsMap);
    }

    private static boolean isFourOfAKind(Map<Integer, List<Card>> cardsMap) {
        return Helpers.getCountOfGroupOfASize(cardsMap, 4) == 1;
    }

    private static boolean isStraightFlush(List<Card> cards) {
        return isStraight(cards) && isFlush(cards);
    }

    private static boolean isStraight(List<Card> cards) {
        boolean isIncreasing = true;
        for (int index = 0; index < cards.size() - 1; index++) {
            if (Math.abs(cards.get(index).getValue() - cards.get(index + 1).getValue()) != 1) {
                isIncreasing = false;
                break;
            }
        }
        return isIncreasing;
    }

    private static boolean isFlush(List<Card> cards) {
        return cards.stream().collect(groupingBy(PokerHandUtil.Card::getSuit)).size() == 1;
    }

    static class Helpers {
        private Helpers() { }

        static Map<Integer, List<PokerHandUtil.Card>> getValueMap(List<PokerHandUtil.Card> cards) {
            return cards.stream().collect(groupingBy(PokerHandUtil.Card::getValue));
        }

        static int getCountOfGroupOfASize(Map<Integer, List<PokerHandUtil.Card>> map, int groupSize) {
            return (int) map.entrySet().stream().filter(x -> x.getValue().size() == groupSize).count();
        }
    }

    @Data
    public static class Card {
        private final int value;
        private final char suit;

        Card(String representation) {
            suit = representation.charAt(1);
            char first = representation.charAt(0);
            value = calculateValue(first);
        }

        private int calculateValue(char first) {
            int cardValue;
            switch (first) {
                case '2':
                case '3':
                case '4':
                case '5':
                case '6':
                case '7':
                case '8':
                case '9':
                    cardValue = Integer.parseInt(first + "");
                    break;
                case 'T':
                    cardValue = 10;
                    break;
                case 'J':
                    cardValue = 11;
                    break;
                case 'Q':
                    cardValue = 12;
                    break;
                case 'K':
                    cardValue = 13;
                    break;
                case 'A':
                    cardValue = 14;
                    break;
                default:
                    throw new IllegalArgumentException("Invalid card");
            }
            return cardValue;
        }
    }
}
