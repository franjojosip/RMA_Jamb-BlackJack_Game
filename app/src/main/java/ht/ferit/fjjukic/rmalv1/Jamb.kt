package ht.ferit.fjjukic.rmalv1

class Jamb(number: Int) {
    private var dices: MutableList<Dice> = mutableListOf()
    private var numberOfDices = number

    init {
        for (i: Int in 1..this.numberOfDices) this.dices.add(Dice())
    }

    fun rollDices(){
        for (item: Dice in this.dices) item.rollADice()
    }

    fun changeIsDiceLocked(index: Int): Boolean {
        return this.dices[index].changeIsLockedDice()
    }

    fun checkDuplicates(numberOfDuplicates: Int): String{
        var text = ""
        if (numberOfDuplicates == 4){
            text = when {
                this.dices.groupingBy { it.getDiceNumber() }.eachCount().filter { it.value >= 4 }.count() == 1 -> {
                    "a poker"
                }
                else -> {
                    "not a poker"
                }
            }
            when {
                this.dices.groupingBy { it.getDiceNumber() }.eachCount().filter { it.value >= 5 }.count() == 1 -> {
                    text += " and also a jamb"
                }
            }
        }
        if (numberOfDuplicates == 5) {
            text = when {
                this.dices.groupingBy { it.getDiceNumber() }.eachCount().filter { it.value >= 5 }
                    .count() == 1 -> {
                    "a jamb"
                }
                else -> {
                    "not a jamb"
                }
            }
        }
        return text
    }

    fun checkStraight(): String {
        var straightText: String = "not a"
        if (this.dices.groupingBy { it.getDiceNumber() }.eachCount().filter { it.value > 0 }.keys.contains(1) &&
            this.dices.groupingBy { it.getDiceNumber() }.eachCount().filter { it.value > 0 }.keys.contains(2) &&
            this.dices.groupingBy { it.getDiceNumber() }.eachCount().filter { it.value > 0 }.keys.contains(3) &&
            this.dices.groupingBy { it.getDiceNumber() }.eachCount().filter { it.value > 0 }.keys.contains(4) &&
            this.dices.groupingBy { it.getDiceNumber() }.eachCount().filter { it.value > 0 }.keys.contains(5))
        {
            straightText = "a small"
            when {
                this.dices.groupingBy { it.getDiceNumber() }.eachCount().filter { it.value > 0 }.keys.contains(6) -> {
                    straightText += " and a large"
                }
            }
        }
        else if (this.dices.groupingBy { it.getDiceNumber() }.eachCount().filter { it.value > 0 }.keys.contains(2) &&
                this.dices.groupingBy { it.getDiceNumber() }.eachCount().filter { it.value > 0 }.keys.contains(3) &&
                this.dices.groupingBy { it.getDiceNumber() }.eachCount().filter { it.value > 0 }.keys.contains(4) &&
                this.dices.groupingBy { it.getDiceNumber() }.eachCount().filter { it.value > 0 }.keys.contains(5) &&
                this.dices.groupingBy { it.getDiceNumber() }.eachCount().filter { it.value > 0 }.keys.contains(6))
            {
                straightText = "a large"
            }

        return straightText
    }

    fun getDiceResult(): MutableList<Int> {
        val values: MutableList<Int> = mutableListOf()
        for(item: Dice in this.dices) values.add(item.getDiceNumber())
        return values
    }
}