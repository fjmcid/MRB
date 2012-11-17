
public class Administrator {

    //MRB gestor = new MRB();  // esta es la instancia que me permitira accesar a los metodos de la clase MRB
    
    public Administrator() {
    }
    
    public void conectar(){
        
    //gestor.Conectar();    
        
    } //posee el string de conexion con la base de datos
    
    public void desconectar(){
        
    //gestor.Desconectar();    
        
    } //cierra la conexion con la base de datos
    
    public void insertarLinea(String[][] linea){
        
    //gestor.insertarLinea(linea);        
        
    } // el string contiene toda la informacion e.g fecha, departamento, acciones, informacion para insertar la linea de informacion en la base de datos
    
    public String[][] desplegarLinea(String[][] matriz){
        
    return matriz;
    
    }// carga las lineas anteriormente almacenadas en la base de datos en base a un determinado departamento
    
    public void editarLinea(String[][] lineaEditada, int idLinea){
        
   //gestor.UpdateLinea(lineaEditada, idLinea); //este metodo va a hacer un update de la linea en la base de datos mediante el id que le corresponde
    }
    
    public void eliminarLinea(int idLinea){
        
   //gestor.DeleteLinea(idLinea); //este metodo va a eliminar el registro en la base de datos mediante el id que le corresponde
    }
    
}
