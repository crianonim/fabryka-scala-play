import scala.collection.mutable

class Entity(){
  val id=Entity.nextId()
 }
trait Doer {
  def doThing():Unit
}
case class Item(name:String,amount:Int)
trait Producer {
  val output:Item
}
case class Inventory(var slots:mutable.ArraySeq[Item]=mutable.ArraySeq.empty[Item]);
case class Decoration() extends  Entity
case class Miner() extends Entity with Producer with Doer {
  val outputSlots=Inventory()
  val output=Item("ore",1)
  def doThing()={
    outputSlots.slots=outputSlots.slots :+ output
    println("Mining: "+output+" with "+this.id+" slots: "+outputSlots)
  }
}
object Entity {
  var id=0
  def nextId():Int = {
    id=id+1;
    id
  }

}
object Nauka {
  def main(args:Array[String]):Unit={
    val entities=List(Miner(),Decoration())
    for (e<-entities){
      e match  {
        case doer:Doer => doer.doThing()
        case _ => println(e.id)
      }
    }
    println("Dziala")
  }
}
