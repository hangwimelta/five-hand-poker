package za.co.melta.five.hand.card.service.impl;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.stream.Stream;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import org.mockito.InjectMocks;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;
import za.co.melta.five.hand.card.exception.PokerHandException;
import za.co.melta.five.hand.card.model.PokerHandResponse;
import za.co.melta.five.hand.card.service.algorithm.PokerHandUtil;
import za.co.melta.five.hand.card.service.algorithm.model.PokerHandModel;
import za.co.melta.five.hand.card.util.Constants;

@ExtendWith(MockitoExtension.class)
class PokerHandServiceImplTest {

    @InjectMocks
    private PokerHandServiceImpl pokerHandServiceImpl;

    @BeforeEach
    public void initMock() {
        pokerHandServiceImpl = new PokerHandServiceImpl();
    }

    /**
     * Method under test: {@link PokerHandServiceImpl#shuffleHand()}
     */
    @Test
    @DisplayName("Method to test random generated hand.")
    void testShuffleHand() {
        PokerHandModel pokerHandModel = new PokerHandModel();
        pokerHandModel.setCards(new ArrayList<>());
        pokerHandModel.setCardsMap(new HashMap<>());
        PokerHandResponse pokerHandResponse = pokerHandServiceImpl.shuffleHand();
        assertNotNull(pokerHandResponse.getRank());
        assertNotNull(pokerHandResponse.getHand());
    }

    /**
     * Method under test: {@link PokerHandServiceImpl#shuffleHand(String)}
     * ParameterizedTest method to test all expected 9 ranks
     * 1. Straight Flush
     * 2. Four of a Kind
     * 3. Full House
     * 4. Flush
     * 5. Straight
     * 6. Three of a Kind
     * 7. Two Pair
     * 8. One Pair
     * 9. High Cards
     */
    @ParameterizedTest
    @MethodSource("provideRankForHand")
    void testRanksByProvideHand(String hand, String expectedRank) {
        PokerHandResponse handResponse = pokerHandServiceImpl.shuffleHand(hand);
        assertEquals(expectedRank, handResponse.getRank());
    }

    private static Stream<Arguments> provideRankForHand() {
        return Stream.of(
                Arguments.of("JC TC 9C 8C 7C", Constants.STRAIGHT_FLUSH),
                Arguments.of("5C 5D 5H 5S 2D", Constants.FOUR_OF_A_KIND),
                Arguments.of("6S 6H 6D KC KH", Constants.FULL_HOUSE),
                Arguments.of("JD 9D 8D 4D 3D", Constants.FLUSH),
                Arguments.of("TD 9S 8H 7D 6C", Constants.STRAIGHT),
                Arguments.of("QC QS QH 9H 2S", Constants.THREE_OF_A_KIND),
                Arguments.of("JH JS 3C 3S 2H", Constants.TWO_PAIR),
                Arguments.of("TS TH 8S 7H 4C", Constants.ONE_PAIR),
                Arguments.of("KD QD 7S 4S 3H", Constants.HIGH_CARD)
        );
    }

    /**
     * Method under test: {@link PokerHandServiceImpl#shuffleHand(String)}
     */
    @Test
    void testShuffleHand2() {
        PokerHandException pokerHandException = assertThrows(PokerHandException.class,
                () -> pokerHandServiceImpl.shuffleHand("Invalid hand"));
        assertEquals("Invalid card", pokerHandException.getMessage());
    }
}

