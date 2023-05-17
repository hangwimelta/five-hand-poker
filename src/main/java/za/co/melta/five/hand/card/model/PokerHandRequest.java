package za.co.melta.five.hand.card.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import za.co.melta.five.hand.card.exception.validator.HandConstraint;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class PokerHandRequest {
    
    @HandConstraint
    String hand;
}
