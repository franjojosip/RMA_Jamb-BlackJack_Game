package ht.ferit.fjjukic.rmalv1

class Deck(cards: MutableList<Card>) {
    private val cards: MutableList<Card> = cards
    private var remainingCards: MutableList<Card> = cards

    fun getRandomCard(): Card{
        val index = (0 until remainingCards.size).random()
        val card: Card = remainingCards[index]
        remainingCards.remove(card)
        return card
    }

    fun countCards(): String{
        return remainingCards.count().toString()
    }
    fun resetDeck(){
        this.remainingCards = mutableListOf()
        this.remainingCards.addAll(cards)
    }
}