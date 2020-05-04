package ht.ferit.fjjukic.rmalv1

class Dice {
    private var number: Int = 1
    private var isLocked: Boolean = false;

    fun roll(){
        if(!this.isLocked){
            this.number = (1..6).random()
        }
    }

    fun getValue(): Int {
        return this.number;
    }

    fun lock(): Boolean {
        this.isLocked = !this.isLocked
        return this.isLocked
    }
}