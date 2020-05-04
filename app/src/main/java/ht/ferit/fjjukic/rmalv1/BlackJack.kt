package ht.ferit.fjjukic.rmalv1

import Hand

class BlackJack {
    private var userHand: Hand = Hand()
    private var computerHand: Hand = Hand()
    private var deck: Deck = Deck()

    fun pullCard(isUser: Boolean){
        when{
            isUser -> userHand.add(deck.pullCard())
            else -> computerHand.add(deck.pullCard())
        }
    }

    fun getResult(isUser: Boolean): Int{
        return when{
            isUser -> userHand.result()
            else -> computerHand.result()
        }
    }

    fun getCards(isUser: Boolean): MutableList<Card> {
        return when{
            isUser -> userHand.getCards()
            else -> computerHand.getCards()
        }
    }

    fun reset(){
        userHand.reset()
        computerHand.reset()
        deck.reset()
    }
}