package za.co.melta.five.hand.card.event;

import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;
import za.co.melta.five.hand.card.service.PokerHandService;

@Component
public class StartUpEventListener {
    public final PokerHandService pokerHandService;

    public StartUpEventListener(PokerHandService pokerHandService) {
        this.pokerHandService = pokerHandService;
    }

    @EventListener
    public void onApplicationEvent(ContextRefreshedEvent event) {
        pokerHandService.shuffleHand();
    }
}
