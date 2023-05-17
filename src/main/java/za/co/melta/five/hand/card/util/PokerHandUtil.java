package za.co.melta.five.hand.card.util;

import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.util.Collections;
import java.util.List;
import java.util.Random;

public class PokerHandUtil {

    private PokerHandUtil() {
    }

    public static String generateHand(int size) throws NoSuchAlgorithmException {
        //Deck numbers to select from
        //T represent #10
        List<String> deckRank = new java.util.ArrayList<>(List.of("A", "2", "3", "4", "5", "6", "7", "8", "9", "T", "J", "Q", "K"));
        Collections.shuffle(deckRank);
        //Suits to select from
        List<String> deckSuits = new java.util.ArrayList<>(List.of("S", "H", "C", "D"));
        Collections.shuffle(deckSuits);

        Random random = SecureRandom.getInstanceStrong();

        StringBuilder stringBuilder = new StringBuilder();

        for (int i = 0; i < size; i++) {
            int randomDeckRankIndex = random.nextInt(deckRank.size() - 1); //Getting random index based on
            int randomDeckSuitsIndex = random.nextInt(deckSuits.size() - 1);
            stringBuilder.append(deckRank.get(randomDeckRankIndex))
                    .append(deckSuits.get(randomDeckSuitsIndex));
            if (i != (size - 1)) { //Only appending empty string when it's not the last index
                stringBuilder.append(" ");
            }
        }
        return stringBuilder.toString().trim();
    }

    public static String replaceWithSymbols(String hand) {
        if (hand.toUpperCase().contains("H"))
            hand = hand.replace("H", "♥");

        if (hand.toUpperCase().contains("C"))
            hand = hand.replace("C", "♣");

        if (hand.toUpperCase().contains("D"))
            hand = hand.replace("D", "♦");

        if (hand.toUpperCase().contains("S"))
            hand = hand.replace("S", "♠");

        if (hand.toUpperCase().contains("T"))
            hand = hand.replace("T", "10");

        return hand;
    }
}
