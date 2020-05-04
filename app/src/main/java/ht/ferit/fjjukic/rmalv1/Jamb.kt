package ht.ferit.fjjukic.rmalv1

class Jamb(number: Int) {
    private var dices: MutableList<Dice> = mutableListOf()
    private var numberOfDices = number

    init {
        for (i: Int in 1..this.numberOfDices) {
            this.dices.add(Dice())
        }
    }

    fun roll(){
        for (item: Dice in this.dices) item.roll()
    }

    fun lock(index: Int): Boolean {
        return this.dices[index].lock()
    }

    fun checkDuplicates(quantity: Int, name: String): String{
        return when{
            this.dices.groupingBy { it.getValue() }.eachCount().filter { it.value >= quantity}.count() > 0 -> {
                "a $name"
            }
            else -> "not a $name"
        }
    }

    fun checkStraight(): String {
        val sortedDices:List<Dice> = this.dices.sortedBy { it.getValue() }
        var isStraight = true
        for(i:Int in 1 until sortedDices.count()) {
            if(sortedDices[i].getValue() != (sortedDices[i - 1].getValue() + 1)) {
                isStraight = false
                break
            }
        }
        return when{
            isStraight -> "a straight"
            else -> "not a straight"
        }
    }

    fun getResult(): MutableList<Int> {
        val values: MutableList<Int> = mutableListOf()
        for(item: Dice in this.dices) values.add(item.getValue())
        return values
    }
}