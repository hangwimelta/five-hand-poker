package za.co.melta.five.hand.card.rest.api;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.when;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import za.co.melta.five.hand.card.model.PokerHandResponse;
import za.co.melta.five.hand.card.service.PokerHandService;
import za.co.melta.five.hand.card.util.Constants;

@ContextConfiguration(classes = {PokerHandController.class})
@ExtendWith(SpringExtension.class)
class PokerHandControllerTest {
    @Autowired
    private PokerHandController pokerHandController;

    @MockBean
    private PokerHandService pokerHandService;

    ObjectMapper objectMapper = new ObjectMapper();

    /**
     * Method under test: {@link PokerHandController#randomCardsShuffle()}
     */
    @Test
    void testRandomCardsShuffle() throws Exception {
        when(pokerHandService.shuffleHand()).thenReturn(PokerHandResponse.builder().rank(Constants.STRAIGHT).build());
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.get("/v1/five-hand-shuffle");
        MvcResult mvcResult = MockMvcBuilders.standaloneSetup(pokerHandController)
                .build()
                .perform(requestBuilder)
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType(MediaType.APPLICATION_JSON_VALUE))
                .andReturn();

        String response = mvcResult.getResponse().getContentAsString();
        PokerHandResponse pokerHandResponse = objectMapper.readValue(response, PokerHandResponse.class);
        assertNotNull(pokerHandResponse);
        assertEquals(Constants.STRAIGHT, pokerHandResponse.getRank());
    }

    /**
     * Method under test: {@link PokerHandController#retrieveRankForHand(String)}
     */
    @Test
    void testRetrieveRankForHand() throws Exception {
        when(pokerHandService.shuffleHand(Mockito.anyString())).thenReturn(PokerHandResponse.builder().rank(Constants.STRAIGHT).build());
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.get("/v1/five-hand-shuffle/{hand}", "JH 9S TH 4S 6S");
        MvcResult mvcResult = MockMvcBuilders.standaloneSetup(pokerHandController)
                .build()
                .perform(requestBuilder)
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType(MediaType.APPLICATION_JSON_VALUE))
                .andReturn();

        String response = mvcResult.getResponse().getContentAsString();
        PokerHandResponse pokerHandResponse = objectMapper.readValue(response, PokerHandResponse.class);
        assertNotNull(pokerHandResponse);
        assertEquals(Constants.STRAIGHT, pokerHandResponse.getRank());
    }
}

