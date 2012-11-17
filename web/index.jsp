
<%@page language="java" contentType="text/html" pageEncoding="UTF-8"%>

<%

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
           <li class="current" ><a><input style="color: white; height:20px; " type="submit"  class="botonPersonalizado" value="Log In" name="Ingresar" id="Ingresar" /></a></li>
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