package servlet;

import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import entities.Especialidad_ObralSocial;
import entities.ObraSocial;
import entities.Paciente;
import entities.Profesional;
import entities.Turnos;
import entities.Valor_especialidad;
import logic.ComunicacionDb;

/**
 * Servlet implementation class AsignarTurno
 */
@WebServlet("/AsignarTurno")
public class AsignarTurno extends HttpServlet {
	private static final long serialVersionUID = 1L;
 
    public AsignarTurno() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		ComunicacionDb ctrl = new ComunicacionDb();
		Paciente paciente = new Paciente();
		Turnos turno = new Turnos();
		Profesional prof = new Profesional();
		ObraSocial os = new ObraSocial();
		Valor_especialidad valor_esp = new Valor_especialidad();
		Especialidad_ObralSocial esp_os = new Especialidad_ObralSocial();
		Double precio_final;
		HttpSession session = request.getSession();

		int num_turno = Integer.parseInt(request.getParameter("nro_turno"));
		turno.setNumero(num_turno);
		
		try {
			turno = ctrl.getTurno(turno);
		} catch (SQLException e2) {
			e2.printStackTrace();
		}
		prof.setMatricula(turno.getMatricula_prof());
		
		try {
			prof = ctrl.getProfesional(prof);
		} catch (SQLException e1) {
			e1.printStackTrace();
		}
		
		try {
			valor_esp = ctrl.getValorEspecialidad(prof);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		paciente = (Paciente) session.getAttribute("usuario");
		try {
			os = ctrl.getObraSocial(paciente);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		try {
			esp_os = ctrl.getPorcentajeCobertura(prof.getCod_especialidad(), os.getId_obra_social());
		} catch (SQLException e) {
			e.printStackTrace();
		}
		precio_final = (double) (valor_esp.getValor() - (valor_esp.getValor() * esp_os.getProcentaje_cobertura())); 
		
		request.setAttribute("usuario", paciente);
		request.setAttribute("turno", turno);
		request.setAttribute("profesional", prof);
		request.setAttribute("obra_social", os);
		request.setAttribute("valor_especialidad", valor_esp);
		request.setAttribute("precio_final", precio_final);
		request.getRequestDispatcher("WEB-INF/pruebas.jsp").forward(request, response); 
		
		doGet(request, response);
	}

}
