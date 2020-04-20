package ht.ferit.fjjukic.rmalv1

class Card (value: Int, name: String) {
    private var name: String = name
    private var value: Int = value;

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