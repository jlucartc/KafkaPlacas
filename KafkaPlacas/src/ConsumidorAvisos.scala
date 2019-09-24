import org.apache.kafka.clients.consumer.{ KafkaConsumer, ConsumerRecords, ConsumerRecord };
import scala.collection.{ JavaConversions }
import java.util.{ Properties, Collections };
import java.util.concurrent._
import java.time._
import java.lang.Thread;
import org.apache.log4j.{Logger,Level};

class ConsumidorAvisos extends Thread{
  
  def consumirAvisos(){
   
    
    var props: Properties = new Properties();
  
  	props.put("bootstrap.servers", "localhost:32771");
  	props.put("group.id", "consumidor_placas_avisos");
  	props.put("key.deserializer", "org.apache.kafka.common.serialization.StringDeserializer");
  	props.put("value.deserializer", "org.apache.kafka.common.serialization.StringDeserializer");
  
  	var consumer: KafkaConsumer[String, String] = new KafkaConsumer[String, String](props);
  	consumer.subscribe(Collections.singletonList("placas_aviso"));
  	
  	while (true) {
  		println("Esperando avisos...")
  	  var records: ConsumerRecords[String, String] = consumer.poll(Duration.ofMillis(1000));
    	var it = records.iterator();
    
    	
    	while (it.hasNext()) {
    	  
    		var record = it.next();
    		println("Aviso da placa: "+record.value());
    		
    		
    	}
  
  	}
    
  }  
  
  override def run(){
    
    consumirAvisos();
    
  }
  
}