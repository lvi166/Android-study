
class Main(){



  fun letFun(){


  }


  fun withFun(){

      val numbers = mutableListOf("one", "two", "three")
      with(numbers) {
        println("'with' is called with argument $this")
        println("It contains $size elements")
    }
  }




 fun main(args: Array<String>) {
    withFun();
 }

}