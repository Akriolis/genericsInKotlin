class PetOwner <T: Pet> (t: T){
    val pets = mutableListOf(t)

    fun add(t: T){
        pets.add(t)
    }

    fun remove (t: T){
        pets.remove(t)
    }
}

fun main(){
    val catFuzz = Cat("Fuzz Light-year")
    val catKatsu = Cat ("Katsu")
    val fishFinny = Fish ("Finny McGraw")
    val catOwner = PetOwner (catFuzz)
    catOwner.add (catKatsu)
    // println("${catOwner.pets[0].name} and ${catOwner.pets[1].name}")
    for (x in catOwner.pets) println ("${x.name}")

}