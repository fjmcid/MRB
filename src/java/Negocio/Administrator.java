package Negocio;

import java.util.Properties;

public class Administrator {

    //MRB gestor = new MRB();  // esta es la instancia que me permitira accesar a los metodos de la clase MRB
    public Administrator() {
    }

    public String conectar() {
        String message = new String();
        try {
            administradorBD.getInstance().Connect();
        } catch (Exception exc) {
            message = exc.getMessage();
        }
        return message;
    } //posee el string de conexion con la base de datos

    public String desconectar() {
        String message = new String();
        try {
            administradorBD.getInstance().Disconnect();
        } catch (Exception exc) {
            message = exc.getMessage();
        }
        return message;
    } //cierra la conexion con la base de datos

    public void insertarLinea(String[][] linea) {
        //gestor.insertarLinea(linea);        
    } // el string contiene toda la informacion e.g fecha, departamento, acciones, informacion para insertar la linea de informacion en la base de datos

    public String[][] desplegarMatriz() {
        String[][] matriz = null;
        try {
            matriz = administradorBD.getInstance().getResultSetData(administradorBD.getInstance().selectQuery(administradorBD.SELECT_STRING_MRB, null));
        } catch (Exception exc) {
            matriz = new String[1][1];
            matriz[0][0] = exc.getMessage();
        }
        return matriz;
    }// carga las lineas anteriormente almacenadas en la base de datos en base a un determinado departamento

    public void editarLinea(String[][] lineaEditada, int idLinea) {
        //gestor.UpdateLinea(lineaEditada, idLinea); //este metodo va a hacer un update de la linea en la base de datos mediante el id que le corresponde
    }
    
    public String[][] desplegarDefectos()
    {
        String[][] matriz = null;
        try {
            //obtiene el id y titulo (en ese orden) de la tabla defectos los cuales se encuentren entre el codigo 1 y 18.
            matriz = administradorBD.getInstance().getResultSetData(administradorBD.getInstance().selectQuery(administradorBD.SELECT_STRING_DEFECT, null));
        } catch (Exception exc) {
            matriz = new String[1][1];
            matriz[0][0] = exc.getMessage();
        }
        return matriz;
    }

    public void eliminarLinea(int idLinea) {
        //gestor.DeleteLinea(idLinea); //este metodo va a eliminar el registro en la base de datos mediante el id que le corresponde
    }

    public boolean insertarLinea() {
        try {
            String p_table = "MRB";
            Properties p_parameters = new Properties();
            
            //campos requeridos//
            
            p_parameters.put("DEPARTMENT_ID",""); //Departamento
            p_parameters.put("TAG_NUMBER",""); //digitado
            p_parameters.put("REVIEW_DATE",""); //datepicker
            p_parameters.put("SHIFT",""); //values 1.2.3
            p_parameters.put("OPERATION_CODE",""); //defect code
            p_parameters.put("LOT_NUMBER",""); // digitado
            p_parameters.put("QUANTITY",""); //digitado
            p_parameters.put("COST_PER_PIECE",""); //digitado
            p_parameters.put("CURRENCY",""); //Dolares
            p_parameters.put("WHO_TAGGED",""); //personas, lo escogen los usuarios
            p_parameters.put("DISCREPANCY_CODE",""); // codigo de los defectos, eso se sacaria de la tabla defects.
            p_parameters.put("DISPOSITION_DATE",""); //date picker
            p_parameters.put("WHO_DISPOSITION","");  //Digitado
            p_parameters.put("DISPOSITION_CODE",""); // A B C D E F
            p_parameters.put("NON_CONFORMING_PARTS",""); //digitado
            p_parameters.put("SCRAPPED_QUANTITY",""); //digitado
            p_parameters.put("TOTAL_SCRAPPED_COST",""); //dolares scrapped
            p_parameters.put("SCRAPPED_PERCENT",""); //
            p_parameters.put("REGULATOY_COMPLIENCE",""); //si no estan, pueden no incluirse
            p_parameters.put("DISCREPANCY_REPORT_NUMBER",""); //si no estan, pueden no incluirse
            
            
            return administradorBD.getInstance().insertQuery(p_table, p_parameters);
        } catch (Exception exc) {
            //manejar exception.
        } finally {
            return false;
        }
    }
}
