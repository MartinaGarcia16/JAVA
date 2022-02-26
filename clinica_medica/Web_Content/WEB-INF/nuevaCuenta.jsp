<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Insert title here</title>
</head>
<body>

	 <div class="form-createaccount">
        <form action="CrearCuenta" method="post">

                <h3>CREAR CUENTA</h3>
  
                <label for="inputNombre">Nombre</label>
                <input type="text" id="inputNombre" name="nombre" class="input" placeholder="Ingrese su nombre">

                <label for="inputApellido">Apellido</label>
                <input type="text" id="inputApellido" name="apellido" class="input" placeholder="Ingrese su apellido">

                <label for="inputDni">Numero de dni</label>
                <input type="text" id="inputDni" name="dni" class="input" placeholder="Ingrese su numero de documento">
                
                <label for="inputEmail">Email</label>
                <input type="email" id="inputEmail" name="email" class="input" placeholder="Input your email">

                <label for="inputPass">Clave</label>
                <input type="password" id="inputPass" name="clave" class="input" placeholder="Ingrese su clave">
                
                <label for="inputCel">Numero de celular</label>
                <input type="text" id="inputCel" name="celular" class="input" placeholder="Ingrese su numero de celular">
                
                <div><button type="submit" class="ingresar-button">Aceptar</button></div>

                <div><a href="index.html"><b>Iniciar sesión</b></a></div>
        </form>
    </div>
	
</body>
</html>