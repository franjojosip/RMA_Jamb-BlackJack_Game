package ht.ferit.fjjukic.rmalv1

class BlackJack {
    private var userCards: MutableList<Card> = mutableListOf()
    private var computerCards: MutableList<Card> = mutableListOf()

    fun addCard(isUserTurn: Boolean, card: Card){
        when (isUserTurn){
            true -> {
                if(card.getName().contains("a") && (userCards.toList().sumBy { it.getValue() } + 11) <= 21){
                    card.setValue(11)
                }
                userCards.add(card)
                }
            false -> {
                if(card.getName().contains("a") && (computerCards.toList().sumBy { it.getValue() } + 11) <= 21){
                    card.setValue(11)
                }
                computerCards.add(card)
            }
        }
    }

    fun countComputerCards(): Int{
        return computerCards.count()
    }

    fun countUserCards(): Int{
        return userCards.count()
    }

    fun getCardResult(isUser: Boolean): Int{
        return when (isUser){
            true -> userCards.toList().sumBy { it.getValue() }
            false -> computerCards.toList().sumBy { it.getValue() }
        }
    }
}