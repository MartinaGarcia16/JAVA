<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
      <%@page import="java.util.LinkedList"%>
<%@page import="entities.Profesional"%>
<%@page import="entities.Paciente"%>
<%@page import="entities.Turnos"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<!-- Bootstrap CSS -->
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-1BmE4kWBq78iYhFldvKuhfTAU6auU8tT94WrHftjDbrCEXSU1oBoqyl2QvZ6jIW3" crossorigin="anonymous">
<title>Listado de todos los turnos</title>
<%
    //Socio s = (Socio)session.getAttribute("usuario");
LinkedList<Paciente> lpa = (LinkedList<Paciente>)request.getAttribute("listaPacientes");        	
LinkedList<Profesional> lp = (LinkedList<Profesional>)request.getAttribute("listaProfesionales");
LinkedList<Turnos> lt = (LinkedList<Turnos>)request.getAttribute("listaTurnos");

    %>
</head>
<body>
<br>
<p class="text-end"><a href="panel.jsp">Volver al panel administrativo</a>.</p>
<br>
<div class="table-responsive">
    <table class="table">
      <thead>
        <tr class="table-info">
        <th scope="col">Numero</th>
          <th scope="col">Fecha</th>
          <th scope="col">Hora</th>
          <th scope="col">Matricula</th>
          <th scope="col">Profesional</th>
          <th scope="col">Paciente</th>
        </tr>
      </thead>
      <tbody>
      <% for (Turnos t : lt) { %>
        <tr>
        	<td> <%=t.getNumero() %></td>
          <td><%=t.getFecha_turno()%></td>
          <td><%=t.getHora_turno()%></td>
          <td><%=t.getMatricula_prof()%></td>
          <% for (Profesional p : lp) { 
          if(t.getMatricula_prof().equalsIgnoreCase(p.getMatricula())){%>
          <td> <%=p.getNombre()+" "+p.getApellido() %></td>
          <% } }%>
          <% 
          String nombre="No asignado";
          for (Paciente pa : lpa) { 
          if(t.getId_paciente()==pa.getId()){
         nombre= pa.getNombre()+" "+pa.getApellido();
           }}%>
            <td><%=nombre%></td>
        </tr>
        <% } %>
      </tbody>
    </table>
    </div>
    <script src="https://cdn.jsdelivr.net/npm/@popperjs/core@2.10.2/dist/umd/popper.min.js" integrity="sha384-7+zCNj/IqJ95wo16oMtfsKbZ9ccEh31eOz1HGyDuCQ6wgnyJNSYdrPa03rtR1zdB" crossorigin="anonymous"></script>
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/js/bootstrap.min.js" integrity="sha384-QJHtvGhmr9XOIpI6YVutG+2QOK9T+ZnN4kzFN1RtK3zEFEIsxhlmWl5/YESvpZ13" crossorigin="anonymous"></script>

</body>
</html>