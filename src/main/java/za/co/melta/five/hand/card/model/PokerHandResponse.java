package za.co.melta.five.hand.card.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class PokerHandResponse {
    String hand;
    String handMessage;
    String rank;
    String rankMessage;
}
