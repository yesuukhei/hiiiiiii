package flashcard.organizers;

import java.util.ArrayList;
import java.util.List;

import flashcard.Card;
import flashcard.CardOrganizer;

public class RecentMistakesFirstSorter implements CardOrganizer {
     private final List<Card> mistakeCards = new ArrayList<>();
    
    public void addMistake(Card card) {
        mistakeCards.remove(card); 
        mistakeCards.add(0, card);
    }
    
    @Override
    public List<Card> reorganize(List<Card> cards) {
        List<Card> reorganized = new ArrayList<>(mistakeCards);
        
        for (Card card : cards) {
            if (!mistakeCards.contains(card)) {
                reorganized.add(card);
            }
        }
        
        return reorganized;
    }
}
