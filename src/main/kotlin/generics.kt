//<> angle brackets means that it uses generics
//<E> is just a placeholder for the type of element you want the collection to hold and return (type parameter)
// mutableList isn't a class, but an interface

/*
Using generic types its possible to:
- create an instance of a generified class (you can tell the type of objects you'll allow it to hold or let the compiler infer it)
- create a function that takes a generic type.
- create a function that returns a generic type.
 */

abstract class Pet(var name: String)

class Cat(name: String): Pet(name)

class Dog (name: String): Pet(name)

class Fish (name: String): Pet(name)

class Contest<T: Pet> (var vet: Vet<in T>){ // :Pet this means that T must be a type of Pet
    val scores: MutableMap<T, Int> = mutableMapOf()

    fun addScore (t: T, score: Int = 0){
        if (score >= 0) scores.put(t, score)
    }

    fun getWinners(): MutableSet<T>{
        val highScore = scores.values.max()
        val winners: MutableSet<T> = mutableSetOf()
        for ((t, score) in scores){
            if (score == highScore) winners.add(t)

        }
        return winners
    }
}

interface Retailer <out T>{
    fun sell(): T
}

class CatRetailer: Retailer<Cat>{
    override fun sell(): Cat {
        println("Sell Cat")
        return Cat("")
    }
}

class DogRetailer: Retailer<Dog>{
    override fun sell(): Dog {
        println("Sell Dog")
        return Dog("")
    }
}

class FishRetailer: Retailer<Fish>{
    override fun sell(): Fish {
        println("Sell Fish")
        return Fish("")
    }
}

class Vet <T: Pet>{
    fun treat (t: T){
        println("Treat Pet ${t.name}")
    }
}

//"T" is common name for the generic type name
// also, you can use "E" (for "Element"), or "K" and "V" (for "Key" and "Value") if it's a map.

fun main(){

    val catFuzz = Cat ("Fuzz Lightyear")
    val catKatsu = Cat("Katsu")
    val fishFinny = Fish ("Finny McGraw")
    val catCap = Cat ("Captain Meow")

    val catVet = Vet<Cat>()
    val fishVet = Vet<Fish>()
    val petVet = Vet<Pet>()

    catVet.treat(catFuzz)
    petVet.treat(catCap)
    petVet.treat(fishFinny)

    val catContest = Contest<Cat>(catVet)
    catContest.addScore(catFuzz, 50)
    catContest.addScore(catKatsu, 45)

    val topCat = catContest.getWinners().first()

    // if you use just getWinners fun you will end up with mutableSet
    // but by adding .first() fun you get the reference to a particular object
    println("Cat contest winner is ${topCat.name}")


    val petContest = Contest<Pet>(petVet)
    petContest.addScore(fishFinny, 59)
    petContest.addScore(catCap, 56)
    val topPet = petContest.getWinners().first()
    println("Pet contest winner is ${topPet.name}")

    val fishContest = Contest<Fish>(petVet)

    // a generic type can be nullable
    // class MyClass<T> {
    // fun myFun(): T?
    // }

    // a class may have more than one generic type - for example class MyMap <K, V>

    val catRetailer1 = CatRetailer()
    val catRetailer2: CatRetailer = CatRetailer()

    // use keyword "out" to make a generic type covariant
    // covariant means to use a generic subtype in a place of generic supertype
    // if a generic type is covariant, you can use a subtype in place of a supertype

    // prohibited to use out if the class has function parameters or var properties of generic type
    // val properties are allowed

    val dogRetailer: Retailer<Dog> = DogRetailer()
    val catRetailer: Retailer<Cat> = CatRetailer()
    val petRetailer: Retailer<Pet> = CatRetailer()
    petRetailer.sell()

    val catList: List<Cat> = listOf(Cat(""), Cat(""))
    val petList: List <Pet> = catList

    // use keyword "in" to make a generic type contravariant
    // contravariant means to use a generic supertype object in a place of generic subtype
    // if a generic type is contravariant, you can use a supertype in place of a subtype
    // contravariant type can't be used in "in" positions

    // generic type can be locally contravariant

    // when a generic type has no in or out prefix, we say that the type is
    // invariant. An invariant type can only accept references of that specific type




}