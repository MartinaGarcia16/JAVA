package servlet;

import java.io.IOException;
import java.sql.SQLException;
import java.util.LinkedList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import entities.Administradores;
import logic.AdministradorController;




/**
 * Servlet implementation class AdministradoresServlet
 */
@WebServlet("/AdministradoresServlet")
public class AdministradoresServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public AdministradoresServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doPost(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		String accion = request.getParameter("accion");
		if (accion.equalsIgnoreCase("listar")) {
			AdministradorController ac = new AdministradorController();
			LinkedList<Administradores> administradores = null;
			administradores = ac.getAll();
			request.setAttribute("listaAdministradores", administradores);
			request.setAttribute("retro", request.getAttribute("estado"));
			request.getRequestDispatcher("WEB-INF/administrador.jsp").forward(request, response);
		}else if (accion.equalsIgnoreCase("agregar")) {
			request.getRequestDispatcher("WEB-INF/addAdministrador.jsp").forward(request, response);
		} else if (accion.equalsIgnoreCase("Add")) {
			Administradores a = new Administradores();
			AdministradorController ac = new AdministradorController();
			a.setNombre(request.getParameter("nombre"));
			a.setApellido(request.getParameter("apellido"));
			a.setDni(request.getParameter("dni"));
			a.setEmail(request.getParameter("email"));
			a.setPassword(request.getParameter("contrase�a"));
			a = ac.getByEmailDni(a);
			if (a == null) {
				Administradores a2 = new Administradores();
				a2.setNombre(request.getParameter("nombre"));
				a2.setApellido(request.getParameter("apellido"));
				a2.setDni(request.getParameter("dni"));
				a2.setEmail(request.getParameter("email"));
				a2.setPassword(request.getParameter("contrase�a"));
				ac.add(a2);
				request.setAttribute("estado", "Administrador agregado correctamente.");
				request.getRequestDispatcher("AdministradoresServlet?accion=listar").forward(request, response);
			} else {
				request.setAttribute("estado", "Administrador ingresado ya existe con ese Email o Dni.");
				request.getRequestDispatcher("AdministradoresServlet?accion=listar").forward(request, response);
			}

		}else if (accion.equalsIgnoreCase("Editar")) {
			Administradores a=new Administradores();
			AdministradorController ac=new AdministradorController();
			int codigo= Integer.parseInt(request.getParameter("id2"));
			a.setId(codigo);
			a=ac.getById(a);
			request.setAttribute("administrador", a);
			request.getRequestDispatcher("WEB-INF/editAdministrador.jsp").forward(request, response);
		}else if (accion.equalsIgnoreCase("Actualizar")) {
			Administradores a=new Administradores();
			AdministradorController ac=new AdministradorController();
			int id=Integer.parseInt(request.getParameter("id"));
			a.setNombre(request.getParameter("nombre"));
			a.setApellido(request.getParameter("apellido"));
			a.setDni(request.getParameter("dni"));
			a.setEmail(request.getParameter("email"));
			a.setPassword(request.getParameter("contrase�a"));
			a = ac.getByEmailDni(a);
			if (a == null || (a.getEmail().equalsIgnoreCase(request.getParameter("email"))) & a.getDni().equalsIgnoreCase(request.getParameter("dni"))) {
				Administradores a2 = new Administradores();
				a2.setId(id);
				a2.setNombre(request.getParameter("nombre"));
				a2.setApellido(request.getParameter("apellido"));
				a2.setDni(request.getParameter("dni"));
				a2.setEmail(request.getParameter("email"));
				a2.setPassword(request.getParameter("contrase�a"));
				ac.update(a2);
				request.setAttribute("estado", "Administrador editado correctamente.");
				request.getRequestDispatcher("AdministradoresServlet?accion=listar").forward(request, response);
			} else {
				request.setAttribute("estado", "Administrador  ya existe con ese Email o Dni.");
				request.getRequestDispatcher("AdministradoresServlet?accion=listar").forward(request, response);
			}
			
			
		}else if(accion.equalsIgnoreCase("Eliminar")) {
			Administradores a=new Administradores();
			AdministradorController ac=new AdministradorController();
			int codigo= Integer.parseInt(request.getParameter("id2"));
			a.setId(codigo);
			ac.delete(a);
			request.setAttribute("estado", "Administrador eliminado exitosamente.");
			request.getRequestDispatcher("AdministradoresServlet?accion=Listar").forward(request, response);
			
		}
	}

}
