<%@page import="entities.Especialidad"%>
<%@page import="java.util.LinkedList"%>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
     <!-- Bootstrap CSS -->
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-1BmE4kWBq78iYhFldvKuhfTAU6auU8tT94WrHftjDbrCEXSU1oBoqyl2QvZ6jIW3" crossorigin="anonymous">
    <title>Reserva tu turno</title>
    <link rel="stylesheet" href="styles/estilos.css">
</head>

<body>

		<% 
			LinkedList<Especialidad> esp = (LinkedList<Especialidad>)request.getAttribute("especialidades");
		%>

        <form action="ReservarTurno" method="post">
			<h2>  Seleccione una especialidad:</h2>
			<div class="container">
	 			<table class="table table-striped">
	    			<thead class="table-primary">
	      				<tr>
	        				<th>Especialidades</th>
	        				<th></th>
	     				 </tr>
	    			</thead>
	    
	    			<tbody>
	    
	   					 <% for (Especialidad e:esp) { %> 
						<tr>
	        				<td><%=e.getNombre() %></td> 
	        				<td><button class="btn btn-link" name="opc" type="submit" value="<%=e.getCodigo_esp() %>">Elegir</button></td>
	      				</tr>
	    				 <% } %>
	   				</tbody> 
	  			</table>
	  			<div class="d-grid gap-2 col-6 mx-auto">
            	<button type="submit" class="btn btn-primary" name="opc" value="0">Volver atrás</button>
            	</div>
			</div>
        </form>  
        <!-- Option 1: Bootstrap Bundle with Popper -->
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/js/bootstrap.bundle.min.js" integrity="sha384-ka7Sk0Gln4gmtz2MlQnikT1wXgYsOg+OMhuP+IlRH9sENBO0LRn5q+8nbTov4+1p" crossorigin="anonymous"></script>
         
</body>
</html>