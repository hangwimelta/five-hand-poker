package za.co.melta.five.hand.card.service.impl;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import za.co.melta.five.hand.card.exception.PokerHandException;
import za.co.melta.five.hand.card.model.PokerHandResponse;
import za.co.melta.five.hand.card.service.PokerHandService;
import za.co.melta.five.hand.card.service.algorithm.PokerHandUtil;
import za.co.melta.five.hand.card.service.algorithm.model.PokerHandModel;

import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

import static za.co.melta.five.hand.card.util.PokerHandUtil.replaceWithSymbols;

@Slf4j
@Service
public class PokerHandServiceImpl implements PokerHandService {

    @Override
    public PokerHandResponse shuffleHand() {
        ScheduledExecutorService executor = Executors.newScheduledThreadPool(1);
        try {
            //Adding a thread that will run and print 'Shuffling...' while we "shuffle" the cards
            Runnable helloRunnable = () -> System.out.print("Shuffling...");
            //Every millisecond it will print 'Shuffling...' while it's randomly generating the hand
            executor.scheduleAtFixedRate(helloRunnable, 0, 10, TimeUnit.MILLISECONDS);

            //Calling a util to generate a random hand
            String generatedHand = za.co.melta.five.hand.card.util.PokerHandUtil.generateHand(5);
            executor.shutdown();
            System.out.println("\n");

            return shuffleHand(generatedHand);
        } catch (Exception e) {
            log.error(e.getMessage(), e);
            executor.shutdown();
            throw new PokerHandException(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @Override
    public PokerHandResponse shuffleHand(String hand) {
        try {
            //Adding a thread that will run and print 'Shuffling...' while we shuffle the cards
            String handWithSymbols = replaceWithSymbols(hand);
            String yourHandWithSymbol = String.format("Your hand: %s", handWithSymbols);
            String yourHand = String.format("Your hand: %s", hand);
            System.out.println(yourHandWithSymbol);
            System.out.println(yourHand); //Printing this in case the browser or terminal is not showing the actual symbols

            PokerHandModel pokerHandModel = PokerHandUtil.buildPokerHand(hand);

            String rank = PokerHandUtil.rank(pokerHandModel);
            String yourRank = String.format("You have: %s", rank);
            System.out.println(yourRank);
            System.out.println("\n");

            return PokerHandResponse.builder().hand(hand).handMessage(yourHandWithSymbol)
                    .rank(rank).rankMessage(yourRank).build();
        } catch (Exception e) {
            log.error(e.getMessage(), e);
            throw new PokerHandException(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
