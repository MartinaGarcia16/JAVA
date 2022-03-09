package servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.util.LinkedList;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import entities.Administradores;
import entities.Paciente;
import entities.Profesional;
import logic.AdministradorController;
import logic.ComunicacionDb;

@WebServlet({ "/Signin", "/signin", "/SignIn", "/SIGNIN", "/signIn" })
public class Signin extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public Signin() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		Paciente pac = new Paciente();
		Profesional prof = new Profesional();
		Administradores adm=new Administradores();
		AdministradorController AdmCtrl= new AdministradorController();
		ComunicacionDb ctrl = new ComunicacionDb();
		PrintWriter out = response.getWriter();
		
		String email = request.getParameter("email");   // Recuperar información de un formulario html
		String password = request.getParameter("password");
		
		adm.setEmail(email);
		adm.setPassword(password);
		
		try {
			adm = AdmCtrl.validate(adm);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		if(adm != null) {
			request.getSession().setAttribute("administrador", adm);
			request.getRequestDispatcher("panel.jsp").forward(request, response);	
		}
		
		// Validar email y password
		pac.setEmail(email);
		pac.setPassword(password);
		
		try {
			pac = ctrl.validateLogin(pac);
		} catch (SQLException e) {
			e.printStackTrace();
		}

		if (pac != null){
		request.getSession().setAttribute("usuario", pac);
		request.getRequestDispatcher("menu.html").forward(request, response); 
				}
		
		prof.setEmail(email);
		prof.setPassword(password);
		try {
			prof = ctrl.getProfesionalByUser(prof);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		if (prof != null){
			request.getSession().setAttribute("profesional", prof);
			request.getRequestDispatcher("menuProfesionales.html").forward(request, response); 
					}
		
		
		
		else {
			out.print("Sorry Email or Password Error! Try again"); 
			RequestDispatcher rd = request.getRequestDispatcher("index.html");
			rd.include(request, response); 
				} 
		}	
}


