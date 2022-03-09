package servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import entities.Paciente;
import logic.ComunicacionDb;


@WebServlet({ "/CrearCuenta", "/crearcuenta", "/Crearcuenta", "/crearCuenta" })
public class CrearCuenta extends HttpServlet {
	private static final long serialVersionUID = 1L;

    public CrearCuenta() {
        super(); 
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		Paciente pac = new Paciente();
		Boolean encontrado = false;
		ComunicacionDb ctrl = new ComunicacionDb();
		PrintWriter out = response.getWriter();
		
		String nombre = request.getParameter("nombre");
		String apellido = request.getParameter("apellido");
		String dni = request.getParameter("dni");
		String cel = request.getParameter("celular");
		String email = request.getParameter("email");   
		String password = request.getParameter("password");
		
		//Validar que los datos no sean nulos
//		if(nombre != null) {
//			out.print("El usuario ingresado ya existe"); 
//		}
		
		// Validar que el usuario no exista
		pac.setEmail(email);
		pac.setDni(dni);
			
		try {
			pac = ctrl.validateLogin(pac);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		if (pac != null){encontrado = true;}
		
		if (encontrado == false) {
			Paciente p = new Paciente();
			p.setEmail(email);
			p.setPassword(password);
			p.setNombre(nombre);
			p.setApellido(apellido);
			p.setDni(dni);
			p.setNum_tel(cel);
			
			try {
				ctrl.altaPaciente(p);
			} catch (SQLException e) {
				e.printStackTrace();
			}
			request.getRequestDispatcher("bienvenido.html").forward(request, response); 
			}	
			
		else {
			out.print("El usuario ingresado ya existe"); 
			RequestDispatcher rd = request.getRequestDispatcher("ingresaDatos.html");
			rd.include(request, response); 
		}
					
	}
}

