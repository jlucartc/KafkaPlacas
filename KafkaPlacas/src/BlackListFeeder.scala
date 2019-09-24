import com.datastax.driver.core.{Session,Cluster,ResultSet};
import java.util.Random;
import java.util.concurrent.TimeUnit;


object BlackListFeeder {
  
      var letras: String = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
    
      def gerarPlaca() : String = {
        
        var tamanhoLetras : Int = 3;
        var tamanhoNumeros : Int = 4;
        
        var i : Int = 0;
        var palavra : String = "";
        
        var rand : Random = new Random();
        
        for( i <- 0 until tamanhoLetras){
          
          var numero = rand.nextInt(5);
          
          palavra+= letras.substring(numero,numero+1);
          
          
        }
        
        palavra+="-";
        
        for(i <- 0 until tamanhoNumeros){
          
          var numero = rand.nextInt(5);
          
          palavra+= Integer.toString(numero);
          
        }
        
        return palavra;
        
      }
    
    def inserirPlaca() {
      
        var placa : String = gerarPlaca();
        
        var session : Session = Cluster.builder().addContactPoint("localhost").build().connect("placas");
        
        println("PLACA: "+placa);
        
        session.execute("INSERT INTO placas_black_list (codigo) VALUES ('"+placa+"');");
        
        session.close();
      
    }
    
    def main(args : Array[String]) : Unit = {
      
      var i = 100;
      
      while( i >= 0){
        
        inserirPlaca();
        
        i-=1;
        
      }
      
      println("BlackList alimentada");
      
      
    } 
  
}