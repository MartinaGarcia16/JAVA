<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
	<%@page import="entities.Especialidad" %>
	<%@page import="java.util.LinkedList"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<link
	href="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/css/bootstrap.min.css"
	rel="stylesheet"
	integrity="sha384-1BmE4kWBq78iYhFldvKuhfTAU6auU8tT94WrHftjDbrCEXSU1oBoqyl2QvZ6jIW3"
	crossorigin="anonymous">
<title>Agregar profesional</title>
<%
    //Socio s = (Socio)session.getAttribute("usuario");
LinkedList<Especialidad> le = (LinkedList<Especialidad>)request.getAttribute("listaEspecialidades");%>
</head>
<body>
<br>
<p class="text-end"><a href= "ProfesionalesServlet?accion=listar" >Volver al Listado</a></p>
<div class="container">
		<div class="row">
			<div class="col-2"></div>
			<div class="col-7">
				<div class="card text-center">
					<div class="card-header">

						<h3 class="display-6">Agregar Profesional</h3>
					</div>
					<div class="card-body">
	<form action="ProfesionalesServlet" method="Post">
		<div>
			<label for="matricula">Ingrese matricula del profesional</label> <br>
			<input type="text" class="form-control" name="matricula"> <br>
		</div>
		<div>
			<label for="nombre">Ingrese el nombre del profesional</label> <br>
			<input type="text" class="form-control" name="nombre"> <br>
		</div>
		<div>
			<label for="apellido">Ingrese el apellido del profesional</label> <br>
			<input type="text" class="form-control" name="apellido"> <br>
		</div>
		<div>
			<label for="estado">Ingrese el estado del profesional</label>
			<div class="form-check">
				<input class="form-check-input" type="radio" name="estado"
					id="exampleRadios1" value="1" checked> <label
					class="form-check-label" for="exampleRadios1"> Activo </label>
			</div>
			<div class="form-check">
				<input class="form-check-input" type="radio" name="estado"
					id="exampleRadios2" value="0"> <label
					class="form-check-label" for="exampleRadios2"> Inactivo </label>
			</div>
			<br>
		</div>
		<div>
			<label for="email">Ingrese el email del administrador</label> <br>
			<input type="text" class="form-control" name="email"> <br>
		</div>
		<div>
			<label for="contraseña">Ingrese el contraseña del
				administrador</label> <br> <input type="text" class="form-control"
				name="contraseña"> <br>
		</div>
		<div>
		<label for="estado">Ingrese especialidad del profesional</label>
		 <% for (Especialidad p : le) { %>
			<div class="form-check">
				<input class="form-check-input" type="radio" name="especialidad"
					id="exampleRadios1" value="<%=p.getCodigo_esp() %>" required > <label
					class="form-check-label" for="exampleRadios1"> <%=p.getNombre() %> </label>
			</div>
		<%} %>
		</div>
		<input class="btn btn-primary btn-lg" type="submit" name="accion"
			value="Add">
	</form>
	</div>
				</div>
			</div>
			<div class="col-3"></div>
		</div>
	</div>
	<script src="https://cdn.jsdelivr.net/npm/@popperjs/core@2.10.2/dist/umd/popper.min.js" integrity="sha384-7+zCNj/IqJ95wo16oMtfsKbZ9ccEh31eOz1HGyDuCQ6wgnyJNSYdrPa03rtR1zdB" crossorigin="anonymous"></script>
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/js/bootstrap.min.js" integrity="sha384-QJHtvGhmr9XOIpI6YVutG+2QOK9T+ZnN4kzFN1RtK3zEFEIsxhlmWl5/YESvpZ13" crossorigin="anonymous"></script>

	
</body>
</html>