import org.apache.kafka.clients.producer._;
import java.util.Properties;
import java.util.Random;
import java.util.concurrent.TimeUnit;
import java.lang.Thread;
import org.apache.log4j.{Logger,Level};


class ProdutorPlaca extends Thread{
  
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
    
    def publicarPlaca() {
      
      var props : Properties = new Properties();
  
      props.put("bootstrap.servers","localhost:32771");
      props.put("key.serializer","org.apache.kafka.common.serialization.StringSerializer");
      props.put("value.serializer","org.apache.kafka.common.serialization.StringSerializer");
    
      var i = 0;
      
      var rand : Random = new Random();
      
      while(true){
        
        var placa = gerarPlaca();
    
    	  var producer : Producer[String,String] = new KafkaProducer(props);
    
        var record : ProducerRecord[String,String] = new ProducerRecord("placas_lidas","CBD-4144");
        
        producer.send(record);
        producer.close();
        
        println("Placa produzida: "+placa);
        
        TimeUnit.MILLISECONDS.sleep(rand.nextInt(10)*100);
        
      }
      
    }
  
    override def run() = {
      
      publicarPlaca();
      
    }
    
}