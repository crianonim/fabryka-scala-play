import scala.collection.mutable

class Entity(){
  val id=Entity.nextId()
 }
trait Doer {
  def doThing():Unit
}
case class Item(name:String,var amount:Int)
trait Producer {
  val output:Item
}
case class Inventory( slots:mutable.ArrayBuffer[Item]=mutable.ArrayBuffer.empty[Item]){
  def addItem(item: Item)={
      slots.find(_.name==item.name) match {
        case Some(i)=> i.amount+=item.amount
        case None =>  slots.append(item.copy())
      }

  }
};
case class Decoration() extends  Entity
case class Miner(output:Item) extends Entity with Producer with Doer {
  val outputSlots=Inventory()
  def doThing()={
    outputSlots.addItem(output.copy())

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
    val entities=List(Miner(Item("IronOre",1)),Decoration(),Miner(Item("CopperOre",2)))

    for {
      turn <-0 until 10
      e<-entities
    }
    {
      e match  {
        case doer:Doer => doer.doThing()
        case _ => println(e.getClass())
      }
    }
  }
}
