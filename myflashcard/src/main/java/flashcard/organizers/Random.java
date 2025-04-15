package flashcard.organizers;
import java.util.List;
import flashcard.Card;
import flashcard.CardOrganizer;

public class Random implements CardOrganizer {
    @override
    public List<Card> reorganize(List<Card> cards) {
        Collectinos.shuffle(cards);
        return cards;
    }
}
