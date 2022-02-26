package servlet;

import java.io.IOException;
import java.sql.SQLException;
import java.util.LinkedList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import entities.Especialidad;
import entities.Valor_especialidad;
import logic.EspecialidadesController;




/**
 * Servlet implementation class EspecialidadesServlet
 */
@WebServlet("/EspecialidadesServlet")
public class EspecialidadesServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public EspecialidadesServlet() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		doPost(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		String accion = request.getParameter("accion");
		if (accion.equalsIgnoreCase("listar")) {
			EspecialidadesController ec = new EspecialidadesController();
			LinkedList<Especialidad> especialidades = null;
			LinkedList<Valor_especialidad> valores=null;
			try {
				especialidades = ec.getAll();
				valores=ec.getValores();
			} catch (SQLException e) {
				e.printStackTrace();
			}
			request.setAttribute("listaEspecialidades", especialidades);
			request.setAttribute("listaValores", valores);
			request.setAttribute("retro", request.getAttribute("estado"));
			request.getRequestDispatcher("WEB-INF/especialidad.jsp").forward(request, response);
		} else if (accion.equalsIgnoreCase("agregar")) {
			request.getRequestDispatcher("WEB-INF/addEspecialidad.jsp").forward(request, response);
		} else if (accion.equalsIgnoreCase("Add")) {
			Especialidad e = new Especialidad();
			EspecialidadesController ec = new EspecialidadesController();
			String nombre = request.getParameter("nombre");
			e.setNombre(nombre);
			try {
				e = ec.getByNombre(e);
			} catch (SQLException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			if (e == null) {
				Valor_especialidad ve= new Valor_especialidad();
				Especialidad e2 = new Especialidad();
				ve.setValor(Integer.parseInt(request.getParameter("valor")));
				e2.setNombre(nombre);
				ec.add(e2,ve);
				request.setAttribute("estado", "Especialidad agregada correctamente.");
				request.getRequestDispatcher("EspecialidadesServlet?accion=listar").forward(request, response);
			} else {
				request.setAttribute("estado", "Especialidad ingresada ya existe.");
				request.getRequestDispatcher("EspecialidadesServlet?accion=listar").forward(request, response);
			}

		}else if (accion.equalsIgnoreCase("Editar")) {
			Especialidad e=new Especialidad();
			Valor_especialidad ve= new Valor_especialidad();
			EspecialidadesController ec=new EspecialidadesController();
			int codigo= Integer.parseInt(request.getParameter("id2"));
			e.setCodigo_esp(codigo);
			ve.setCod_especialidad(codigo);
			try {
				e=ec.getByCodigo(e);
				ve=ec.getValorPorCodigo(ve);
			} catch (SQLException e1) {
				e1.printStackTrace();
			}
			request.setAttribute("especialidad", e);
			request.setAttribute("valor", ve);
			request.getRequestDispatcher("WEB-INF/editEspecialidad.jsp").forward(request, response);
		}else if (accion.equalsIgnoreCase("Actualizar")) {
			Especialidad e=new Especialidad();
			EspecialidadesController ec=new EspecialidadesController();
			int id=Integer.parseInt(request.getParameter("id"));
			String nombre=request.getParameter("nombre");
			e.setNombre(nombre);
			try {
				e=ec.getByNombre(e);
			} catch (SQLException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			if (e == null || e.getCodigo_esp()==id) {
				Valor_especialidad va= new Valor_especialidad();
				Especialidad esp= new Especialidad();
				esp.setCodigo_esp(id);
				esp.setNombre(nombre);
				va.setCod_especialidad(id);
				va.setValor(Integer.parseInt(request.getParameter("valor1")));
				try {
					ec.update(esp,va);
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				request.setAttribute("estado", "especialidad actualizada correctamente.");
				request.getRequestDispatcher("EspecialidadesServlet?accion=listar").forward(request, response);
			}else {
				request.setAttribute("estado", "No se puedo actualizar.Ya existe especialidad con ese nombre");
				request.getRequestDispatcher("EspecialidadesServlet?accion=Listar").forward(request, response);
			}
		}else if(accion.equalsIgnoreCase("Eliminar")) {
			Especialidad e=new Especialidad();
			EspecialidadesController ec=new EspecialidadesController();
			int codigo= Integer.parseInt(request.getParameter("id2"));
			e.setCodigo_esp(codigo);
			
				
				try {
					Integer numero;
					numero = ec.delete(e);
					if (numero==1) {
			request.setAttribute("estado", "Especialidad eliminada exitosamente.");
			request.getRequestDispatcher("EspecialidadesServlet?accion=Listar").forward(request, response);
			}else {
				request.setAttribute("estado", "No se pudo eliminar porque hay profesionales con esa especialidad.");
				request.getRequestDispatcher("EspecialidadesServlet?accion=Listar").forward(request, response);
				
			}
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			
			
			
		}

	}
}
