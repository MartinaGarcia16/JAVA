package dataBase;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.LinkedList;
import entities.Especialidad;
import entities.Especialidad_ObralSocial;
import entities.ObraSocial;
import entities.Paciente;
import entities.Profesional;
import entities.Turnos;
import entities.Valor_especialidad;

public class FuncionesDb {
	public Paciente getByUser(Paciente p) throws SQLException {

		PreparedStatement stmt = null;
		ResultSet rs = null;
		Paciente pac = null;
		String consulta = "select p.id, p.dni, p.nombre, p.apellido, p.num_tel, p.email, os.id_obra_social, os.nombre_os\r\n"
				+ "from pacientes p\r\n"
				+ "left join obras_sociales os\r\n"
				+ "	on p.id_obra_social = os.id_obra_social\r\n"
				+ "where email=? or dni=?";
		try{
			// Crear la conexión
			stmt = DbConnector.getInstancia().getConn().prepareStatement(consulta);
			
			// Ejecutar la query
			stmt.setString(1, p.getEmail());
			stmt.setString(2, p.getDni());
			rs = stmt.executeQuery();
			
			// Mapeo de ResultSet a objeto
			if(rs!= null && rs.next()) {
				pac = new Paciente();
				ObraSocial os = new ObraSocial();
				pac.setId(rs.getInt("id"));
				pac.setDni(rs.getString("dni"));
				pac.setNombre(rs.getString("nombre"));
				pac.setApellido(rs.getString("apellido"));
				pac.setNum_tel(rs.getString("num_tel"));
				pac.setEmail(rs.getString("email"));
				os.setId_obra_social(rs.getInt("id_obra_social"));;
				os.setNombre(rs.getString("nombre_os"));
				pac.setOs(os);
						} // Fin del if
			
			// Cerrar recursos
			if(stmt!=null) {stmt.close();}
			if(rs!=null) {rs.close();}
			DbConnector.getInstancia().releaseConn(); 
											
		} catch(SQLException  ex) {
			// Errores
			System.out.println("SQLException: "+ ex.getMessage());
			System.out.println("SQLState: "+ ex.getSQLState());
			System.out.println("VendorError: "+ ex.getErrorCode());
		}
		return pac;
} // Fin del getById
	
	public LinkedList<Paciente> getAll() throws SQLException {
		
		Statement stmt=null;
		ResultSet rs=null;
		LinkedList<Paciente> pacientes = new LinkedList<>();
		try {
					
		// Ejecutar la query
		stmt = DbConnector.getInstancia().getConn().createStatement();
		rs = stmt.executeQuery("select * from pacientes");
					
		// Mapeao de ResultSet a objeto
		if (rs!=null) {
			while(rs.next()) {
				Paciente p = new Paciente();
				p.setId(rs.getInt("id"));
				p.setDni(rs.getString("dni"));
				p.setNombre(rs.getString("nombre"));
				p.setApellido(rs.getString("apellido"));
				p.setNum_tel(rs.getString("num_tel"));
				p.setEmail(rs.getString("email"));
				pacientes.add(p);
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
		
		return pacientes;
	} // fin getAll
	
	public LinkedList<Profesional> getByEspecialidad(Especialidad e) throws SQLException {
		PreparedStatement stmt=null;
		ResultSet rs=null;
		LinkedList<Profesional> profesionales = new LinkedList<>();
		String consulta = "select prof.nombre_prof, prof.apellido_prof, prof.matricula \r\n"
				+ "from profesionales prof \r\n"
				+ "where prof.cod_especialidad =? and prof.estado=1";
		
		try {		
		// Ejecutar la query
		stmt = DbConnector.getInstancia().getConn().prepareStatement(consulta);
		stmt.setInt(1, e.getCodigo_esp());
		rs = stmt.executeQuery();
		
		// Mapeo de ResultSet a objeto
		if (rs!=null) {
			while(rs.next()) {
				Profesional prof = new Profesional();
				prof.setNombre(rs.getString("nombre_prof"));
				prof.setApellido(rs.getString("apellido_prof"));
				prof.setMatricula(rs.getString("matricula"));
				profesionales.add(prof);
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
		
		return profesionales;
	} // Fin getByEspecialidad
	
	
	public LinkedList<Especialidad> getEspecialidad() {
		PreparedStatement stmt=null;
		ResultSet rs=null;
		LinkedList<Especialidad> especialidades = new LinkedList<>();
		String consulta = "select e.nombre_esp, e.codigo_esp from especialidades e";

		try {		
		// Ejecutar la query
		stmt = DbConnector.getInstancia().getConn().prepareStatement(consulta);
		rs = stmt.executeQuery();
		
		// Mapeo de ResultSet a objeto
		if (rs!=null) {
			while(rs.next()) {	
				Especialidad e = new Especialidad();
				e.setNombre(rs.getString("nombre_esp"));
				e.setCodigo_esp(rs.getInt("codigo_esp"));
				especialidades.add(e);
						} // Fin del if
		}
		
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

		return especialidades;
	} // Fin getEspecialidad
	
	public Especialidad getEspecialidadByCodigo(Especialidad e) {
		PreparedStatement stmt=null;
		ResultSet rs=null;
		String consulta = "select e.nombre_esp from especialidades e where e.codigo_esp =?";

		try {		
		// Ejecutar la query
		stmt = DbConnector.getInstancia().getConn().prepareStatement(consulta);
		stmt.setInt(1, e.getCodigo_esp());
		rs = stmt.executeQuery();
		
		// Mapeo de ResultSet a objeto
		while(rs.next()) {
			if (rs!=null) {		
				e.setNombre(rs.getString("nombre_esp"));
						} // Fin del if
		}
		
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

		return e;
	} // Fin getEspecialidadByCodigo
	
	public void altaPaciente(Paciente c) {
		PreparedStatement stmt=null;
		//ResultSet rs=null;
		String consulta = "insert into pacientes (nombre, apellido, dni, email, password, num_tel, id_obra_social)"
							+ "values(?,?,?,?,?,?,null)";
		
		try {		
		// Ejecutar la query
		stmt = DbConnector.getInstancia().getConn().prepareStatement(consulta, PreparedStatement.RETURN_GENERATED_KEYS);
		stmt.setString(1, c.getNombre());
		stmt.setString(2, c.getApellido());
		stmt.setString(3, c.getDni());
		stmt.setString(4, c.getEmail());
		stmt.setString(5, c.getPassword());
		stmt.setString(6, c.getNum_tel());
		
		stmt.executeUpdate();
		
		ResultSet Keyrs = stmt.getGeneratedKeys(); 
		
		if (Keyrs != null && Keyrs.next()) {
			int id = Keyrs.getInt(1);
			c.setId(id);		
		}		
		
		} catch(SQLException  ex) {
			ex.printStackTrace();
			
		} finally {
			DbConnector.getInstancia().releaseConn();
		}
	}  // Fin altaPaciente
	
	public LinkedList<Turnos> getTurnos(Profesional p) throws SQLException {
		LinkedList<Turnos> turnos = new LinkedList<>();
		PreparedStatement stmt=null;
		ResultSet rs=null;
		String consulta = "select t.fecha_turno, t.hora_turno, t.numero \r\n"
				+ "from turnos t \r\n"
				+ "inner join profesionales p \r\n"
				+ "	on p.matricula = t.matricula_prof \r\n"
				+ "where p.matricula = ? and t.id_paciente is null \r\n"
				+ "order by t.fecha_turno, t.hora_turno";
			
		stmt = DbConnector.getInstancia().getConn().prepareStatement(consulta);
		stmt.setString(1, p.getMatricula());
		rs = stmt.executeQuery();
		
		if (rs!=null) {
			while(rs.next()) {
				Turnos t = new Turnos();
				t.setFecha_turno(rs.getDate("fecha_turno"));
				t.setHora_turno(rs.getTime("hora_turno"));
				t.setNumero(rs.getInt("numero"));
				turnos.add(t);
					} // Fin del while
		} // Fin del if
		
		if(rs!=null) {rs.close();}
		if(stmt!=null) {stmt.close();}
		DbConnector.getInstancia().releaseConn();
		
		return turnos;
	} // Fin getTurnos
	
	public Profesional getProfesional(Profesional p) throws SQLException {
		PreparedStatement stmt=null;
		ResultSet rs=null;
		String consulta = "select p.nombre_prof, p.apellido_prof, e.codigo_esp, e.nombre_esp\r\n"
				+ "from profesionales p\r\n"
				+ "inner join especialidades e\r\n"
				+ "	on p.cod_especialidad = e.codigo_esp\r\n"
				+ "where p.matricula = ?;";
		
		stmt = DbConnector.getInstancia().getConn().prepareStatement(consulta);
		stmt.setString(1, p.getMatricula());
		rs = stmt.executeQuery();
		
		if(rs!= null && rs.next()) {
				Especialidad esp = new Especialidad();
				p.setNombre(rs.getString("nombre_prof"));
				p.setApellido(rs.getString("apellido_prof"));
				esp.setCodigo_esp(rs.getInt("codigo_esp"));
				esp.setNombre(rs.getString("nombre_esp"));
				p.setEsp(esp);
					} // Fin if
		
		if(rs!=null) {rs.close();}
		if(stmt!=null) {stmt.close();}
		DbConnector.getInstancia().releaseConn();
		
		return p;
	} //fin getProfesional
	
	public void asignarTurno(Turnos t, Paciente p) throws SQLException {
		PreparedStatement stmt=null;
		String consulta = "update turnos set id_paciente = ? \r\n"
				+ "where numero = ?";
		
		stmt = DbConnector.getInstancia().getConn().prepareStatement(consulta);
		stmt.setInt(1, p.getId());
		stmt.setInt(2, t.getNumero());
		stmt.executeUpdate();
		
		if(stmt!=null) {stmt.close();}
		DbConnector.getInstancia().releaseConn();
		
	} //fin asignarTurno
		
	public Valor_especialidad getValorEspecialidad(Profesional p) throws SQLException {
		PreparedStatement stmt=null;
		ResultSet rs=null;
		Especialidad esp = p.getEsp();
		Valor_especialidad valor_esp = new Valor_especialidad(); 
		String consulta = "select max(ve.valor) valor \r\n"
				+ "from valor_especialidad ve \r\n"
				+ "inner join especialidades e \r\n"
				+ "	on e.codigo_esp = ve.cod_especialidad \r\n"
				+ "where e.codigo_esp = ?";	
		
		stmt = DbConnector.getInstancia().getConn().prepareStatement(consulta);
		stmt.setInt(1, esp.getCodigo_esp());
		rs = stmt.executeQuery();
		
		if(rs!= null && rs.next()) {
				valor_esp.setValor(rs.getInt("valor"));
					} // Fin del if
		
		if(rs!=null) {rs.close();}
		if(stmt!=null) {stmt.close();}
		DbConnector.getInstancia().releaseConn();
		
		return valor_esp;
		
	} // fin getValorEspecialidad
	
	
	public Turnos getTurno(Turnos t) throws SQLException {
		PreparedStatement stmt=null;
		ResultSet rs=null;
		Turnos turno = new Turnos();
		String consulta = "select t.numero, t.fecha_turno, t.hora_turno, prof.matricula, prof.nombre_prof, prof.apellido_prof, esp.codigo_esp, esp.nombre_esp\r\n"
				+ "from turnos t\r\n"
				+ "inner join profesionales prof\r\n"
				+ "	on t.matricula_prof = prof.matricula\r\n"
				+ "inner join especialidades esp\r\n"
				+ "	on prof.cod_especialidad = esp.codigo_esp\r\n"
				+ "where t.numero = ?;";
		
		stmt = DbConnector.getInstancia().getConn().prepareStatement(consulta);
		stmt.setInt(1, t.getNumero());
		rs = stmt.executeQuery();
		
		if(rs!= null && rs.next()) {
			Especialidad esp = new Especialidad();
			Profesional prof = new Profesional();
			turno.setNumero(rs.getInt("numero"));
			turno.setFecha_turno(rs.getDate("fecha_turno"));
			turno.setHora_turno(rs.getTime("hora_turno"));
			esp.setCodigo_esp(rs.getInt("codigo_esp"));
			esp.setNombre(rs.getString("nombre_esp"));
			prof.setEsp(esp);
			prof.setMatricula(rs.getString("matricula"));
			prof.setNombre(rs.getString("nombre_prof"));
			prof.setApellido(rs.getString("apellido_prof"));
			turno.setProf(prof);;
			
		} //fin del if
		
		if(rs!=null) {rs.close();}
		if(stmt!=null) {stmt.close();}
		DbConnector.getInstancia().releaseConn();
		
		return turno;
	}
	
	public Especialidad_ObralSocial getPorcentajeCobertura(Integer e, Integer os) throws SQLException {
		PreparedStatement stmt=null;
		ResultSet rs=null;
		Especialidad_ObralSocial esp_os = new Especialidad_ObralSocial();
		String consulta = "select e_os.porcentaje_cobertura from especialidad_obrasocial e_os \r\n"
				+ "where e_os.cod_especialidad = ? and e_os.id_os = ?";
		
		stmt = DbConnector.getInstancia().getConn().prepareStatement(consulta);
		stmt.setInt(1, e);
		stmt.setInt(2, os);
		rs = stmt.executeQuery();
		
		if(rs!= null && rs.next()) {
			esp_os.setProcentaje_cobertura(rs.getFloat("porcentaje_cobertura"));
		} //fin del if
		
		if(rs!=null) {rs.close();}
		if(stmt!=null) {stmt.close();}
		DbConnector.getInstancia().releaseConn();
		
		return esp_os;
	}
	
	public LinkedList<Turnos> getTurnosPaciente(Paciente p) throws SQLException{
		LinkedList<Turnos> turnosPaciente = new LinkedList<>();
		PreparedStatement stmt=null;
		ResultSet rs=null;
		String consulta = "select t.numero, t.fecha_turno, t.hora_turno, prof.nombre_prof, prof.apellido_prof, prof.matricula,\r\n"
				+ "	esp.codigo_esp, esp.nombre_esp\r\n"
				+ "from turnos t\r\n"
				+ "inner join pacientes p\r\n"
				+ "	on t.id_paciente = p.id\r\n"
				+ "inner join profesionales prof\r\n"
				+ "	on t.matricula_prof = prof.matricula\r\n"
				+ "inner join especialidades esp\r\n"
				+ "	on prof.cod_especialidad = esp.codigo_esp\r\n"
				+ "where t.id_paciente = ?";
		
		stmt = DbConnector.getInstancia().getConn().prepareStatement(consulta);
		stmt.setInt(1, p.getId());
		rs = stmt.executeQuery();
		
		if (rs!=null) {
			while(rs.next()) {
				Turnos t = new Turnos();
				Especialidad esp = new Especialidad();
				Profesional prof = new Profesional();
				t.setNumero(rs.getInt("numero"));
				t.setFecha_turno(rs.getDate("fecha_turno"));
				t.setHora_turno(rs.getTime("hora_turno"));
				esp.setCodigo_esp(rs.getInt("codigo_esp"));
				esp.setNombre(rs.getString("nombre_esp"));
				prof.setEsp(esp);
				prof.setMatricula(rs.getString("matricula"));
				prof.setNombre(rs.getString("nombre_prof"));
				prof.setApellido(rs.getString("apellido_prof"));
				t.setProf(prof);;
				turnosPaciente.add(t);
					} // Fin del while
		} // Fin del if
		
		if(rs!=null) {rs.close();}
		if(stmt!=null) {stmt.close();}
		DbConnector.getInstancia().releaseConn();
		
		return turnosPaciente;
	}
	
	public void cancelarTurno(Integer nro_turno) throws SQLException {
		PreparedStatement stmt=null;
		String consulta = "update turnos set id_paciente = null \r\n"
				+ "where numero = ?";
		
		stmt = DbConnector.getInstancia().getConn().prepareStatement(consulta);
		stmt.setInt(1, nro_turno);
		stmt.executeUpdate();
		
		if(stmt!=null) {stmt.close();}
		DbConnector.getInstancia().releaseConn();
		
	} //fin cancelarTurno
	
	public Profesional getProfesionalByUser(Profesional p) throws SQLException {

		PreparedStatement stmt = null;
		ResultSet rs = null;
		Profesional prof = null;
		String consulta = "select p.matricula, p.nombre_prof, p.apellido_prof, p.email, e.codigo_esp, e.nombre_esp\r\n"
				+ "from profesionales p\r\n"
				+ "inner join especialidades e\r\n"
				+ "	on p.cod_especialidad = e.codigo_esp\r\n"
				+ "where email=? and password=?";
		try{
			// Crear la conexión
			stmt = DbConnector.getInstancia().getConn().prepareStatement(consulta);
			
			// Ejecutar la query
			stmt.setString(1, p.getEmail());
			stmt.setString(2, p.getPassword());
			rs = stmt.executeQuery();
			
			// Mapeo de ResultSet a objeto
			if(rs!= null && rs.next()) { 
				prof = new Profesional();
				Especialidad esp = new Especialidad();
				prof.setMatricula(rs.getString("matricula"));
				prof.setNombre(rs.getString("nombre_prof"));
				prof.setApellido(rs.getString("apellido_prof"));
				prof.setEmail(rs.getString("email"));
				esp.setCodigo_esp(rs.getInt("codigo_esp"));
				esp.setNombre(rs.getString("nombre_esp"));
				prof.setEsp(esp);
						} // Fin del if
			
			// Cerrar recursos
			if(stmt!=null) {stmt.close();}
			if(rs!=null) {rs.close();}
			DbConnector.getInstancia().releaseConn(); 
											
		} catch(SQLException  ex) {
			// Errores
			System.out.println("SQLException: "+ ex.getMessage());
			System.out.println("SQLState: "+ ex.getSQLState());
			System.out.println("VendorError: "+ ex.getErrorCode());
		}
		return prof;
} // Fin del getProfesionalById
	
	public LinkedList<Turnos> getTurnosProfesional(Profesional p) throws SQLException{
		LinkedList<Turnos> turnosProfesional = new LinkedList<>();
		PreparedStatement stmt=null;
		ResultSet rs=null;
		String consulta = "select  t.numero, t.hora_turno, t.fecha_turno, pac.nombre, pac.apellido, pac.id,\r\n"
				+ "	os.id_obra_social, os.nombre_os\r\n"
				+ "from turnos t \r\n"
				+ "left join pacientes pac\r\n"
				+ "	on t.id_paciente = pac.id\r\n"
				+ "left join obras_sociales os\r\n"
				+ "	on pac.id_obra_social = os.id_obra_social\r\n"
				+ "where t.matricula_prof = ?;";
		
		stmt = DbConnector.getInstancia().getConn().prepareStatement(consulta);
		stmt.setString(1, p.getMatricula());
		rs = stmt.executeQuery();
		
		if (rs!=null) {
			while(rs.next()) {
				Turnos t = new Turnos();
				Paciente pac = new Paciente();
				ObraSocial os = new ObraSocial();
				t.setNumero(rs.getInt("numero"));
				t.setFecha_turno(rs.getDate("fecha_turno"));
				t.setHora_turno(rs.getTime("hora_turno"));
				os.setId_obra_social(rs.getInt("id_obra_social"));
				os.setNombre(rs.getString("nombre_os"));
				pac.setOs(os);
				pac.setNombre(rs.getString("nombre"));
				pac.setApellido(rs.getString("apellido"));
				pac.setId(rs.getInt("id"));
				t.setPac(pac);;
				turnosProfesional.add(t);
					} // Fin del while
		} // Fin del if
		
		if(rs!=null) {rs.close();}
		if(stmt!=null) {stmt.close();}
		DbConnector.getInstancia().releaseConn();
		
		return turnosProfesional;
	}
	
	public LinkedList<Paciente> getTurnosPacientesProfActual(Profesional p) throws SQLException{
		LinkedList<Paciente> pacientes = new LinkedList<>();
		PreparedStatement stmt=null;
		ResultSet rs=null;
		String consulta = "select p.nombre, p.apellido, p.id, os.id_obra_social, os.nombre_os \r\n"
				+ "from pacientes p\r\n"
				+ "inner join obras_sociales os\r\n"
				+ "	on p.id_obra_social = os.id_obra_social\r\n"
				+ "inner join turnos t\r\n"
				+ "	on p.id = t.id_paciente\r\n"
				+ "where t.matricula_prof = ?";
		
		stmt = DbConnector.getInstancia().getConn().prepareStatement(consulta);
		stmt.setString(1, p.getMatricula());
		rs = stmt.executeQuery();
		
		if (rs!=null) {
			while(rs.next()) {
				Paciente pac = new Paciente();
				ObraSocial os = new ObraSocial();
				pac.setNombre(rs.getString("nombre"));
				pac.setApellido(rs.getString("apellido"));
				pac.setId(rs.getInt("id"));
				os.setId_obra_social(rs.getInt("id_obra_social"));
				os.setNombre(rs.getString("nombre_os"));
				pac.setOs(os);
				pacientes.add(pac);
					} // Fin del while
		} // Fin del if
		
		if(rs!=null) {rs.close();}
		if(stmt!=null) {stmt.close();}
		DbConnector.getInstancia().releaseConn();
		
		return pacientes;
	}
	
	public LinkedList<ObraSocial> getObraSocialPaciente() throws SQLException {
		PreparedStatement stmt=null;
		ResultSet rs=null;
		LinkedList<ObraSocial> obrasSociales = new LinkedList<>();
		String consulta = "select os.nombre_os, os.id_obra_social from obras_sociales os \r\n";
		
		stmt = DbConnector.getInstancia().getConn().prepareStatement(consulta);
		rs = stmt.executeQuery();
		
		if (rs!=null) {
			while(rs.next()) {
			ObraSocial os = new ObraSocial();
			os.setNombre(rs.getString("nombre_os"));
			os.setId_obra_social(rs.getInt("id_obra_social"));
			obrasSociales.add(os);
			}
		} //fin del if
		
		if(rs!=null) {rs.close();}
		if(stmt!=null) {stmt.close();}
		DbConnector.getInstancia().releaseConn();
		
		return obrasSociales;
	}
	
	public void actualizarDatosPaciente(Paciente p) throws SQLException {
		PreparedStatement stmt=null;
		String consulta = "update pacientes set email=?, password=?, num_tel=? \r\n"
				+ "where id=?";
		
		stmt = DbConnector.getInstancia().getConn().prepareStatement(consulta);
		stmt.setString(1, p.getEmail());
		stmt.setString(2, p.getPassword());
		stmt.setString(3, p.getNum_tel());
		stmt.setInt(4, p.getId());
		stmt.executeUpdate();
		
		if(stmt!=null) {stmt.close();}
		DbConnector.getInstancia().releaseConn();
		
	} //fin cancelarTurno
}
