package dataBase;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.LinkedList;

import entities.Especialidad;
import entities.ObraSocial;
import entities.Paciente;
import entities.Profesional;
import entities.Turnos;

public class DataTurnos {

	public void deletePorMatricula(Turnos t) {
		// TODO Auto-generated method stub
		PreparedStatement stmt = null;
		ResultSet rs = null;
		Profesional prof = t.getProf();
		try {
			stmt = DbConnector.getInstancia().getConn().prepareStatement("delete from turnos where matricula_prof=? ");
			stmt.setString(1, prof.getMatricula());
			stmt.executeUpdate();

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				if (rs != null)
					rs.close();
				if (stmt != null)
					stmt.close();
				DbConnector.getInstancia().releaseConn();
			} catch (SQLException e) {
				e.printStackTrace();
			}

		}
	}

	public LinkedList<Turnos> getAll() {
		// TODO Auto-generated method stub
		Statement stmt=null;
		ResultSet rs=null;
		LinkedList<Turnos> turnos = new LinkedList<>();
		try {
					
		// Ejecutar la query
		stmt = DbConnector.getInstancia().getConn().createStatement();
		rs = stmt.executeQuery("select t.numero, t.fecha_turno, t.hora_turno, pac.id, pac.nombre, pac.apellido, os.nombre_os, os.id_obra_social,\r\n"
				+ "	prof.matricula, prof.nombre_prof, prof.apellido_prof, esp.codigo_esp, esp.nombre_esp\r\n"
				+ "from turnos t\r\n"
				+ "inner join profesionales prof\r\n"
				+ "	on t.matricula_prof = prof.matricula\r\n"
				+ "inner join especialidades esp\r\n"
				+ "	on prof.cod_especialidad = esp.codigo_esp\r\n"
				+ "left join pacientes pac\r\n"
				+ "	on t.id_paciente = pac.id\r\n"
				+ "left join obras_sociales os\r\n"
				+ "	on pac.id_obra_social = os.id_obra_social\r\n"
				+ "order by matricula_prof asc, hora_turno asc;");
					
		// Mapeao de ResultSet a objeto
		if (rs!=null) {
			while(rs.next()) {
				Turnos t = new Turnos();
				Profesional prof = new Profesional();
				Especialidad esp = new Especialidad();
				Paciente pac = new Paciente();
				ObraSocial os = new ObraSocial();
				t.setNumero(rs.getInt("numero"));
				t.setFecha_turno(rs.getDate("fecha_turno"));
				t.setHora_turno(rs.getTime("hora_turno"));
				prof.setMatricula(rs.getString("matricula"));
				prof.setNombre(rs.getString("nombre_prof"));
				prof.setApellido(rs.getString("apellido_prof"));
				esp.setCodigo_esp(rs.getInt("codigo_esp"));
				esp.setNombre(rs.getString("nombre_esp"));
				prof.setEsp(esp);
				pac.setNombre(rs.getString("nombre"));
				pac.setApellido(rs.getString("apellido"));
				pac.setId(rs.getInt("id"));
				os.setId_obra_social(rs.getInt("id_obra_social"));
				os.setNombre(rs.getString("nombre_os"));
				pac.setOs(os);
				t.setPac(pac);;
				t.setProf(prof);;
				turnos.add(t);
					} // Fin del while
		} // Fin del if
		
		
		} catch(SQLException  ex) {
			ex.printStackTrace();
			
		} finally {
			try {
				if(rs!=null) {rs.close();}
				if(stmt!=null) {stmt.close();}
				DbConnector.getInstancia().releaseConn();
			} catch (SQLException ex) {
				ex.printStackTrace();
			}
		}
		
		return turnos;	
	}

}
