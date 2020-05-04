package ht.ferit.fjjukic.rmalv1

class Deck {
    private val cards: MutableList<Card> = mutableListOf()
    private val remainingCards: MutableList<Card> = mutableListOf()

    init {
        var value = 1
        for (s:Suit in Suit.values()) {
            value = 1
            for (r: Rank in Rank.values()) {
                cards.add(Card("${r}_$s", value))
                if(value != 10){
                    value++
                }
            }
        }
        this.remainingCards.addAll(this.cards)
    }

    fun pullCard(): Card {
        val card: Card = remainingCards[(0 until remainingCards.size).random()]
        remainingCards.remove(card)
        return card
    }

    fun reset() {
        this.remainingCards.clear()
        this.remainingCards.addAll(cards)
    }
}