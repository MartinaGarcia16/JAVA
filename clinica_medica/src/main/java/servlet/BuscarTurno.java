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

@WebServlet({ "/BuscarTurno", "/buscarturno", "/buscarTurno", "/BUSCARTURNO" })
public class BuscarTurno extends HttpServlet {
	private static final long serialVersionUID = 1L;
  
    public BuscarTurno() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		LinkedList<Turnos> turnos = new LinkedList<>();
		ComunicacionDb ctrl = new ComunicacionDb();
		Profesional prof = new Profesional();
		Paciente pac = new Paciente();
		
		String mat = request.getParameter("nro_matricula"); 		
		
		if (mat.equals("0")) {
			request.getRequestDispatcher("menu.html").forward(request, response);
		}
		
		else {
			prof.setMatricula(mat);
		try {
			turnos = ctrl.getTurnos(prof);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		try {
			prof = ctrl.getProfesional(prof);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		HttpSession session = request.getSession();
		pac = (Paciente) session.getAttribute("usuario");
		
		request.setAttribute("paciente", pac);
		request.setAttribute("turnosDisponibles", turnos);
		request.setAttribute("profesional", prof);
		request.getRequestDispatcher("WEB-INF/turnosDisponibles.jsp").forward(request, response); 
		
		} //fin del else
	}

}
