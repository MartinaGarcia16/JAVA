<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
   <%@page import="java.util.LinkedList"%>
<%@page import="entities.Especialidad"%>
<%@page import="entities.Valor_especialidad"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<!-- Bootstrap CSS -->
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-1BmE4kWBq78iYhFldvKuhfTAU6auU8tT94WrHftjDbrCEXSU1oBoqyl2QvZ6jIW3" crossorigin="anonymous">
<title>Listado especialidades</title>
<%
    //Socio s = (Socio)session.getAttribute("usuario");
        	LinkedList<Especialidad> le = (LinkedList<Especialidad>)request.getAttribute("listaEspecialidades");
        	LinkedList<Valor_especialidad> lv=(LinkedList<Valor_especialidad>)request.getAttribute("listaValores");
    %>
</head>
<body>
<br>
<p class="text-end"><a href="panel.jsp">Volver al panel administrativo</a></p>
<div>
<br>
<%if ((request.getAttribute("retro"))!=null) { %>
		<p class="text-center"> <%=request.getAttribute("retro")%> </p>		
	<% } %>
	<br>
      <form action="EspecialidadesServlet" method="POST">
        <input class="btn btn-success" type="submit" name="accion" value="agregar">
      </form>
  </div>
    <div class="table-responsive">
    <table class="table">
      <thead>
        <tr class="table-info">
        <th scope="col">Codigo</th>
          <th scope="col">Nombre</th>
          <th scope="col">Valor</th>
           <th scope="col">Acciones</th>
        </tr>
      </thead>
      <tbody>
      <% for (Especialidad esp : le) { %>
        <tr>
        	<td> <%=esp.getCodigo_esp() %></td>
          <td><%=esp.getNombre()%></td>
          <% for (Valor_especialidad val : lv) { 
          if(esp.getCodigo_esp()==val.getEsp().getCodigo_esp()){
          %>
          <td><%=val.getValor()%></td>
          <% } %>
          <% } %>
          <td>
            <form action="EspecialidadesServlet" method="POST">
              <input type="hidden" name="id2" value="<%=esp.getCodigo_esp()%>">
              <input class="btn btn-warning" type="submit" name="accion" value="Editar">
              <input class="btn btn-danger" type="submit" name="accion" value="Eliminar">
          </form>
          </td>
        </tr>
        <% } %>
      </tbody>
    </table>
    </div>
    <script src="https://cdn.jsdelivr.net/npm/@popperjs/core@2.10.2/dist/umd/popper.min.js" integrity="sha384-7+zCNj/IqJ95wo16oMtfsKbZ9ccEh31eOz1HGyDuCQ6wgnyJNSYdrPa03rtR1zdB" crossorigin="anonymous"></script>
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/js/bootstrap.min.js" integrity="sha384-QJHtvGhmr9XOIpI6YVutG+2QOK9T+ZnN4kzFN1RtK3zEFEIsxhlmWl5/YESvpZ13" crossorigin="anonymous"></script>
</body>
</html>