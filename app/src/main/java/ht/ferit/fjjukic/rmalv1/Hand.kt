import ht.ferit.fjjukic.rmalv1.Card

class Hand {
    private var cards: MutableList<Card> = mutableListOf()

    fun add(card: Card){
        if(card.getName().contains("ACE") && (cards.toList().sumBy { it.getValue() } + 11) <= 21){
            card.setValue(11)
        }
        cards.add(card)
    }

    fun getCards(): MutableList<Card>{
        return cards
    }

    fun result(): Int{
        return cards.toList().sumBy { it.getValue() }
    }

    fun reset(){
        cards.clear()
    }
}