

object MainObject {
  
  def main(args : Array[String]): Unit = {
    
    var produtoresPlaca : Array[ProdutorPlaca] = new Array[ProdutorPlaca](4);
    
    var consumidoresPlacas : Array[ConsumidorPlacas] = new Array[ConsumidorPlacas](4);
    
    var consumidorAvisos : ConsumidorAvisos = new ConsumidorAvisos();
    
    consumidorAvisos.start();
    
    var i : Int = 0;
    
    for( i <- 0 until produtoresPlaca.size){
      
      produtoresPlaca(i) = new ProdutorPlaca();
      produtoresPlaca(i).start();
      println("PRODUTORES PLACA");
    }
    
    for( i <- 0 until consumidoresPlacas.size){
      
      consumidoresPlacas(i) = new ConsumidorPlacas();
      consumidoresPlacas(i).start();
      println("CONSUMIDORES PLACAS");
    }
    
    
    
  }
  
}