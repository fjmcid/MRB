
<%@page language="java" contentType="text/html" pageEncoding="UTF-8"%>

<%

HttpSession sesionLV1 = request.getSession(true);

String activar = "";
int estado = 0;

activar = (String)sesionLV1.getAttribute("activar");

if(1 == 1){
estado = 1; //poner alert
}else{
estado = 0; //no poner alert
}

String load = (String) session.getAttribute("load");

if (load == null)
{

}else{
    if(load.equals("NO.jsp")){
    out.write("<script>alert('El Usuario y/o Contraseña Ingresados son Inválidos')</script>");
    }else{
    out.write("<script>alert('Debe de encontrarse logeado para poder ingresar a la aplicacion')</script>");
    }
}

%>

<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Login MRB</title>
    </head>
    <body id="imagenLogin" class="index" style="background-repeat: repeat">

<form name="menuLogin" id="menuLogin"method="POST">

    <div style="display:none">
           
        </div>

       <ul id="nav">
           <li class="current" ><a><input style="color: white; height:20px; " type="submit"  class="botonPersonalizado" value="Log In" name="Ingresar" id="Ingresar" onclick="return login(1);" /></a></li>
      </ul>

     <input type="hidden" id="campoLogin" name="campoLogin" value="" />

     <br><br><br
     <center>
         
         <h4>Nombre Usr <input style="text-align: center; border-radius:22px; color:black; font-weight: bold; border-bottom-color:silver;" class="" align="middle" type="text" name="usuario" id="usuario" value="" />
             <br><br>Contraseña <input style="text-align: center;  border-radius:22px; color:black; font-weight: bold;" class="" type="password" name="password" id="password" value="" />
         
         </h4></center>

       </div>

 </form>

      
    </body>
</html>