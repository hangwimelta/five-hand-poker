package za.co.melta.five.hand.card;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;
import springfox.documentation.spring.web.plugins.Docket;

class FiveHandCardApplicationTest {
    /**
     * Method under test: {@link FiveHandCardApplication#productApi()}
     */
    @Test
    void testProductApi() {
        Docket actualProductApiResult = (new FiveHandCardApplication()).productApi();
        assertTrue(actualProductApiResult.isEnabled());
        assertEquals("default", actualProductApiResult.getGroupName());
    }
}

