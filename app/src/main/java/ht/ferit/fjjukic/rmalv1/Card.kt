package ht.ferit.fjjukic.rmalv1

class Card (private var name: String, private var value: Int) {

    fun getValue(): Int{
        return this.value
    }

    fun setValue(newValue: Int){
        this.value = newValue
    }

    fun getName(): String{
        return this.name
    }
}