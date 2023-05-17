package za.co.melta.five.hand.card.util;

import java.security.NoSuchAlgorithmException;

import org.apache.logging.log4j.util.Strings;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;

class PokerHandUtilTest {
    /**
     * Method under test: {@link PokerHandUtil#generateHand(int)}
     */
    @Test
    void testGenerateHand() throws NoSuchAlgorithmException {
        String generateHand = PokerHandUtil.generateHand(5);
        assertNotNull(generateHand);
        assertFalse(Strings.isBlank(generateHand));
    }
}

