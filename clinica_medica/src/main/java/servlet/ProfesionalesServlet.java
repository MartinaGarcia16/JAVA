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
import entities.Profesional;
import entities.Turnos;
import logic.EspecialidadesController;
import logic.ProfesionalController;
import logic.TurnosController;

/**
 * Servlet implementation class ProfesionalesServlet
 */
@WebServlet("/ProfesionalesServlet")
public class ProfesionalesServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public ProfesionalesServlet() {
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
			ProfesionalController pc = new ProfesionalController();
			LinkedList<Profesional> profesionales = null;

			profesionales = pc.getAll();
			request.setAttribute("listaProfesionales", profesionales);
			request.setAttribute("retro", request.getAttribute("estado"));
			request.getRequestDispatcher("WEB-INF/profesional.jsp").forward(request, response);
		} else if (accion.equalsIgnoreCase("agregar")) {
			EspecialidadesController ec = new EspecialidadesController();
			LinkedList<Especialidad> especialidades = null;
			try {
				especialidades = ec.getAll();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			request.setAttribute("listaEspecialidades", especialidades);
			request.getRequestDispatcher("WEB-INF/addProfesional.jsp").forward(request, response);
		} else if (accion.equalsIgnoreCase("Add")) {
			Profesional p = new Profesional();
			Especialidad esp = new Especialidad();
			ProfesionalController pc = new ProfesionalController();
			p.setEmail(request.getParameter("email"));
			p.setMatricula(request.getParameter("matricula"));
			p = pc.getByEmailMatricula(p);
			if (p == null) {
				Profesional p2 = new Profesional();
				p2.setMatricula(request.getParameter("matricula"));
				p2.setNombre(request.getParameter("nombre"));
				p2.setApellido(request.getParameter("apellido"));
				p2.setEmail(request.getParameter("email"));
				p2.setPassword(request.getParameter("contraseña"));
				Integer estado = Integer.parseInt(request.getParameter("estado"));
				p2.setEstado(estado);
				Integer especialidad = Integer.parseInt(request.getParameter("especialidad"));
				esp.setCodigo_esp(especialidad);
				p2.setEsp(esp);
				pc.add(p2);
				request.setAttribute("estado", "Profesional agregado correctamente.");
				request.getRequestDispatcher("ProfesionalesServlet?accion=listar").forward(request, response);
			} else {
				request.setAttribute("estado", "Profesional ingresado ya existe con ese Email o Matricula.");
				request.getRequestDispatcher("ProfesionalesServlet?accion=listar").forward(request, response);
			}

		} else if (accion.equalsIgnoreCase("Editar")) {
			Profesional p = new Profesional();
			ProfesionalController pc = new ProfesionalController();
			EspecialidadesController ec = new EspecialidadesController();
			LinkedList<Especialidad> especialidades = null;
			try {
				especialidades = ec.getAll();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			p.setMatricula(request.getParameter("id2"));
			p = pc.getByMatricula(p);
			request.setAttribute("profesional", p);
			request.setAttribute("listaEspecialidades", especialidades);
			request.getRequestDispatcher("WEB-INF/editProfesional.jsp").forward(request, response);
		} else if (accion.equalsIgnoreCase("Actualizar")) {
			Profesional p= new Profesional();
			Especialidad esp = new Especialidad();
			ProfesionalController pc= new ProfesionalController();
			String matricula=request.getParameter("id");
			p.setEmail(request.getParameter("email"));
			p.setMatricula(request.getParameter("matricula"));
			p = pc.getByEmailMatricula(p);
			if (p == null || p.getMatricula().equalsIgnoreCase(request.getParameter("id"))) {
				Profesional p2 = new Profesional();
				p2.setMatricula(request.getParameter("matricula"));
				p2.setNombre(request.getParameter("nombre"));
				p2.setApellido(request.getParameter("apellido"));
				p2.setEmail(request.getParameter("email"));
				p2.setPassword(request.getParameter("contraseña"));
				Integer estado = Integer.parseInt(request.getParameter("estado"));
				p2.setEstado(estado);
				Integer especialidad = Integer.parseInt(request.getParameter("especialidad"));
				esp.setCodigo_esp(especialidad);
				p2.setEsp(esp);
				pc.update(p2,matricula);
				request.setAttribute("estado", "Profesional editado correctamente.");
				request.getRequestDispatcher("ProfesionalesServlet?accion=listar").forward(request, response);
			} else {
				request.setAttribute("estado", "Profesional ingresado ya existe con ese Email o Matricula.");
				request.getRequestDispatcher("ProfesionalesServlet?accion=listar").forward(request, response);
			}
			
		}else if(accion.equalsIgnoreCase("Eliminar")) {
			Profesional p=new Profesional();
			Turnos t=new Turnos();
			ProfesionalController pc=new ProfesionalController();
			TurnosController tc= new TurnosController();
			p.setMatricula(request.getParameter("id2"));
			t.setProf(p);
			tc.deletePorMatricula(t);
			pc.delete(p);
			request.setAttribute("estado", "Profesional eliminado exitosamente.");
			request.getRequestDispatcher("ProfesionalesServlet?accion=Listar").forward(request, response);
			
		}

	}
}
