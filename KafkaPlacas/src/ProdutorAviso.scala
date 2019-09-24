import org.apache.kafka.clients.producer._;
import java.util.Properties;
import java.util.Random;
import java.util.concurrent.TimeUnit;
import java.lang.Thread;
import org.apache.log4j.{Logger,Level};


class ProdutorAviso(plc : String) extends Thread{
  
  var placa : String = plc;
  
  def emitirAviso(){
    
    var props : Properties = new Properties();

    props.put("bootstrap.servers","localhost:32771");
    props.put("key.serializer","org.apache.kafka.common.serialization.StringSerializer");
    props.put("value.serializer","org.apache.kafka.common.serialization.StringSerializer");
  
	  var producer : Producer[String,String] = new KafkaProducer(props);

    var record : ProducerRecord[String,String] = new ProducerRecord("placas_aviso",placa);
    
    producer.send(record);
    producer.close();
    
    println("Aviso gerado para a placa: "+placa);
      
  }
  
  override def run() {
    
    emitirAviso();
    
  }
  
}