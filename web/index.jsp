
<%@page language="java" contentType="text/html" pageEncoding="UTF-8"%>

<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Login MRB</title>
    </head>
    
    <script type="text/javascript" src="JavaScript/jquery-1.8.2.js" ></script>
    <script type="text/javascript" src="JavaScript/jquery-ui-1.9.1.custom.js" ></script> 
    <link rel="stylesheet" type="text/css" href="Css/jquery-ui-1.9.1.custom.css" />
    
    <script type="text/javascript" >
        
    $(document).ready(function(){
        
        $("#myDiv").show('slow');
        $("#myDiv").show(500);    
        $("#myDiv").css("color","Red"); 
        
        $("#myButton").click(function(){         
           // alert("yes");   
            
        });
        
        $("#myDiv2").dialog();    
        
        
    }); //end
      
        
    </script>
   
    
    <body id="imagenLogin" class="index" style="background-repeat: repeat">

<form name="menuLogin" id="menuLogin"method="POST">

    <div id="myDiv" style="display:none">test</div>
    
    <button id="myButton">Push</button>
    
    <div id="myDiv2" style="display:none">test</div>
    

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