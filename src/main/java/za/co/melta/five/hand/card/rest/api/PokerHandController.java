package za.co.melta.five.hand.card.rest.api;

import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import za.co.melta.five.hand.card.exception.validator.HandConstraint;
import za.co.melta.five.hand.card.model.PokerHandResponse;
import za.co.melta.five.hand.card.service.PokerHandService;

import javax.validation.Valid;

@RestController
public class PokerHandController {

    public final PokerHandService pokerHandService;

    @Autowired
    public PokerHandController(PokerHandService pokerHandService) {
        this.pokerHandService = pokerHandService;
    }

    @GetMapping(path = "/v1/five-hand-shuffle")
    public ResponseEntity<PokerHandResponse> randomCardsShuffle() {
        return ResponseEntity.ok(pokerHandService.shuffleHand());
    }

    /**
     * Added to verify a rank for a specific hand
     * Needs to follow standard [XY XY XY XY XY] e.g "JH 9S TH AS KS", "2♥ J♥ A♥ J♥ Q♥"
     */
    @GetMapping(path = "/v1/five-hand-shuffle/{hand}")
    public ResponseEntity<PokerHandResponse> retrieveRankForHand(@ApiParam(defaultValue = "2♥ J♥ A♥ J♥ Q♥")
                                                                     @PathVariable @Valid
                                                                     @HandConstraint(message = "Invalid poker hand captured") String hand) {
        return ResponseEntity.ok(pokerHandService.shuffleHand(hand));
    }
}
