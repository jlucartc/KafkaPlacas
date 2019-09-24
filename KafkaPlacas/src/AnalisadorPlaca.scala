import com.datastax.driver.core.{ResultSet,Session,Cluster};


class AnalisadorPlaca(plc : String) extends Thread{
  
  var placa : String = plc;
  
  
  override def run(){
    
    
    var session : Session = Cluster.builder().addContactPoint("localhost").build().connect("placas");
    
    var results : ResultSet = session.execute("SELECT * FROM placas_black_list WHERE codigo='"+placa+"';");
    
    if(results.all().size() >= 1){
      
      session.close();
      
      var produtorAviso : ProdutorAviso = new ProdutorAviso(placa);
      
      produtorAviso.start();
      
    }else{
      
      session.execute("INSERT INTO placas_lidas (codigo) VALUES ('"+placa+"');");
      
      session.close();
      
    }
    
    
  }
  
  
}