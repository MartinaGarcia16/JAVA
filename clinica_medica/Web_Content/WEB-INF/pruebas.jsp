<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@page import="entities.Paciente"%>
<%@page import="entities.Turnos"%>
<%@page import="entities.Profesional"%>
<%@page import="entities.Valor_especialidad"%>
<%@page import="entities.ObraSocial"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">

<meta name="viewport" content="width=device-width, initial-scale=1">
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.1/css/bootstrap.min.css">
<link rel="stylesheet" href="styles/estilos.css">
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
<script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.1/js/bootstrap.min.js"></script>

<title>Pruebas</title>
</head>

<body>
		<% 
			Paciente pac = (Paciente)session.getAttribute("usuario");
			Turnos t = (Turnos)request.getAttribute("turno");
			Profesional p = (Profesional)request.getAttribute("profesional");
			Valor_especialidad ve = (Valor_especialidad)request.getAttribute("valor_especialidad");
			ObraSocial os = (ObraSocial)request.getAttribute("obra_social");
			double pago = (double)request.getAttribute("precio_final");
		%>
		
		<form action="ConfirmarTurno" method="post">


		<div class="container">
      
 			<table class="table table-striped">
    			<thead>
      				<tr>
        				<th>Nombre paciente</th>
        				<th>Apellido paciente</th>
        				<th>Fecha turno</th>
        				<th>Hora turno</th>
        				<th>Numero turno</th>
        				<th>Nombre profesional</th>
        				<th>Apellido profesional</th>
        				<th>Obra social</th>
        				<th>Valor consulta</th>
        				<th>Total a pagar</th>
     				 </tr>
    			</thead>
    
    			<tbody>
      				<tr>
        				<td><%=pac.getNombre() %></td>
        				<td><%=pac.getApellido() %></td>
        				<td><%=t.getFecha_turno() %></td>
        				<td><%=t.getHora_turno() %></td>
        				<td><%=t.getNumero() %></td>
        				<td><%=p.getNombre() %></td>
        				<td><%=p.getApellido() %></td>
        				<td><%=os.getNombre() %></td>
        				<td><%=ve.getValor() %></td>
        				<td><%=pago %></td>
      				</tr>
   				</tbody> 
  			</table>
			<button type="submit" class="input-button" name="nro_turno" value="<%=t.getNumero() %>">Aceptar</button>
			<button type="submit" class="input-button" name="nro_turno" value="0">Cancelar</button>
		</div>
	</form>
		
</body>
</html>