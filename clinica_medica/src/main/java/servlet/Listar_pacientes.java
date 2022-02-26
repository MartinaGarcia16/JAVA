package servlet;

import java.io.IOException;
import java.sql.SQLException;
import java.util.LinkedList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import entities.Paciente;
import entities.Profesional;
import entities.Turnos;
import logic.ComunicacionDb;



@WebServlet({ "/Listar_pacientes", "/listar_pacientes", "/Listar_Pacientes", "/LISTAR_PACIENTES" })
public class Listar_pacientes extends HttpServlet {
	private static final long serialVersionUID = 1L;

    public Listar_pacientes() {
        super();

    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	
		String opc;
		opc = request.getParameter("opcion");
		Paciente p = new Paciente();
		Profesional prof = new Profesional();
		LinkedList<Turnos> turnosPacienteActual = new LinkedList<>();
		LinkedList<Turnos> turnosProfesionalActual = new LinkedList<>();
		LinkedList<Paciente> turnosPacientes = new LinkedList<>();
		ComunicacionDb ctrl = new ComunicacionDb();
		HttpSession session = request.getSession();
		p = (Paciente) session.getAttribute("usuario");
		
		if ("misturnos".equals(opc)) {	
			try {
				turnosPacienteActual = ctrl.getTurnosPaciente(p);
			} catch (SQLException e) {
				e.printStackTrace();
			}
			request.setAttribute("turnosPaciente", turnosPacienteActual);
			request.setAttribute("paciente", p);
			request.getRequestDispatcher("WEB-INF/misTurnos.jsp").forward(request, response); 
		}
		
		if ("reservar".equals(opc)) { 
			request.getRequestDispatcher("reservarTurno.html").forward(request, response); 
		}
		 
		if ("misdatos".equals(opc)) {
			request.setAttribute("paciente", p);
			request.getRequestDispatcher("WEB-INF/nuevosDatos.jsp").forward(request, response); 
		}
		
		if ("turnos_profesional".equals(opc)) {
			prof = (Profesional)session.getAttribute("usuario");
			try {
				turnosProfesionalActual = ctrl.getTurnosProfesional(prof);
			} catch (SQLException e) {
				e.printStackTrace();
			}
			
			try {
				turnosPacientes = ctrl.getTurnosPacientesProfActual(prof);
			} catch (SQLException e) {
				e.printStackTrace();
			}
			request.setAttribute("turnosProfesional", turnosProfesionalActual);
			request.setAttribute("pacientes", turnosPacientes);
			request.getRequestDispatcher("WEB-INF/turnosProfesional.jsp").forward(request, response);
		}
		
	}
}
