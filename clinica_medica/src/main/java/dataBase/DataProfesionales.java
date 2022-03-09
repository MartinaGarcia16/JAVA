package dataBase;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.LinkedList;

import entities.Especialidad;
import entities.Profesional;

public class DataProfesionales {

	public LinkedList<Profesional> getByEspecialidad(Especialidad e) {
		// TODO Auto-generated method stub
		PreparedStatement stmt = null;
		ResultSet rs = null;
		LinkedList<Profesional> profesionales = new LinkedList<>();
		String consulta = "select * from profesionales where cod_especialidad=?";
		try {
			// Crear la conexión
			stmt = DbConnector.getInstancia().getConn().prepareStatement(consulta);

			// Ejecutar la query
			stmt.setInt(1, e.getCodigo_esp());
			rs = stmt.executeQuery();

			// Mapeo de ResultSet a objeto
			if (rs != null) {
				while (rs.next()) {
					Profesional p = new Profesional();
					p.setMatricula(rs.getString("matricula"));
					p.setNombre(rs.getString("nombre_prof"));
					p.setApellido(rs.getString("apellido_prof"));
					profesionales.add(p);
				} // Fin del while
			} // Fin del if

			// Cerrar recursos
			if (stmt != null) {
				stmt.close();
			}
			if (rs != null) {
				rs.close();
			}
			DbConnector.getInstancia().releaseConn();

		} catch (SQLException ex) {
			// Errores
			System.out.println("SQLException: " + ex.getMessage());
			System.out.println("SQLState: " + ex.getSQLState());
			System.out.println("VendorError: " + ex.getErrorCode());
		}
		return profesionales;
	}

	public LinkedList<Profesional> getAll() {
		// TODO Auto-generated method stub
		Statement stmt = null;
		ResultSet rs = null;
		LinkedList<Profesional> profesionales = new LinkedList<>();
		try {

			// Ejecutar la query
			stmt = DbConnector.getInstancia().getConn().createStatement();
			rs = stmt.executeQuery("SELECT p.matricula, p.email, p.password, p.nombre_prof, p.apellido_prof, p.estado, e.codigo_esp, e.nombre_esp\r\n"
					+ "FROM profesionales p\r\n"
					+ "inner join especialidades e\r\n"
					+ "	on p.cod_especialidad = e.codigo_esp;");

			// Mapeao de ResultSet a objeto
			if (rs != null) {
				while (rs.next()) {
					Profesional p = new Profesional();
					Especialidad esp = new Especialidad();
					p.setMatricula(rs.getString("matricula"));
					p.setNombre(rs.getString("nombre_prof"));
					p.setApellido(rs.getString("apellido_prof"));
					p.setEmail(rs.getString("email"));
					p.setPassword(rs.getString("password"));
					esp.setCodigo_esp(rs.getInt("codigo_esp"));
					esp.setNombre(rs.getString("nombre_esp"));
					p.setEsp(esp);
					p.setEstado(rs.getInt("estado"));
					profesionales.add(p);
				} // Fin del while
			} // Fin del if

		} catch (SQLException ex) {
			ex.printStackTrace();

		} finally {
			try {
				if (rs != null) {
					rs.close();
				}
				if (stmt != null) {
					stmt.close();
				}
				DbConnector.getInstancia().releaseConn();
			} catch (SQLException ex) {
				ex.printStackTrace();
			}
		}

		return profesionales;
	}

	public Profesional getByEmailMatricula(Profesional p) {
		// TODO Auto-generated method stub
		PreparedStatement stmt = null;
		ResultSet rs = null;
		Profesional pro = null;
		String consulta = "select * from profesionales where matricula=?";
		try {
			// Crear la conexión
			stmt = DbConnector.getInstancia().getConn().prepareStatement(consulta);

			// Ejecutar la query
			stmt.setString(1, p.getMatricula());
			rs = stmt.executeQuery();

			// Mapeo de ResultSet a objeto
			if (rs != null && rs.next()) {
				pro = new Profesional();
				pro.setMatricula(rs.getString("matricula"));
				pro.setNombre(rs.getString("nombre_prof"));
				pro.setApellido(rs.getString("apellido_prof"));
				pro.setEmail(rs.getString("email"));
				pro.setPassword(rs.getString("password"));
				pro.setEstado(rs.getInt("estado"));
			} // Fin del if

			// Cerrar recursos
			if (stmt != null) {
				stmt.close();
			}
			if (rs != null) {
				rs.close();
			}
			DbConnector.getInstancia().releaseConn();

		} catch (SQLException ex) {
			// Errores
			System.out.println("SQLException: " + ex.getMessage());
			System.out.println("SQLState: " + ex.getSQLState());
			System.out.println("VendorError: " + ex.getErrorCode());
		}
		return pro;
	}

	public void add(Profesional p2) {
		// TODO Auto-generated method stub
		PreparedStatement stmt = null;
		Especialidad esp = p2.getEsp();
		try {
			stmt = DbConnector.getInstancia().getConn().prepareStatement(
					"insert into profesionales(matricula,nombre_prof,apellido_prof,email,password,cod_especialidad,estado) values(?,?,?,?,?,?,?)");
			stmt.setString(1, p2.getMatricula());
			stmt.setString(2, p2.getNombre());
			stmt.setString(3, p2.getApellido());
			stmt.setString(4, p2.getEmail());
			stmt.setString(5, p2.getPassword());
			stmt.setInt(6, esp.getCodigo_esp());
			stmt.setInt(7, p2.getEstado());
			stmt.executeUpdate();

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				if (stmt != null)
					stmt.close();
				DbConnector.getInstancia().releaseConn();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}

	}

	public Profesional getByMatricula(Profesional p) {
		// TODO Auto-generated method stub
		PreparedStatement stmt = null;
		ResultSet rs = null;
		Profesional pro = null;
		String consulta = "SELECT p.matricula, p.email, p.password, p.nombre_prof, p.apellido_prof, e.codigo_esp, e.nombre_esp\r\n"
				+ "FROM profesionales p\r\n"
				+ "inner join especialidades e\r\n"
				+ "	on p.cod_especialidad = e.codigo_esp\r\n"
				+ "where p.matricula = ?;";
		try {
			// Crear la conexión
			stmt = DbConnector.getInstancia().getConn().prepareStatement(consulta);

			// Ejecutar la query
			stmt.setString(1, p.getMatricula());
			rs = stmt.executeQuery();

			// Mapeo de ResultSet a objeto
			if (rs != null && rs.next()) {
				pro = new Profesional();
				Especialidad esp = new Especialidad();
				pro.setMatricula(rs.getString("matricula"));
				pro.setNombre(rs.getString("nombre_prof"));
				pro.setApellido(rs.getString("apellido_prof"));
				pro.setEmail(rs.getString("email"));
				pro.setPassword(rs.getString("password"));
				esp.setCodigo_esp(rs.getInt("codigo_esp"));
				esp.setNombre(rs.getString("nombre_esp"));
				pro.setEsp(esp);
				pro.setEstado(rs.getInt("estado"));
			} // Fin del if

			// Cerrar recursos
			if (stmt != null) {
				stmt.close();
			}
			if (rs != null) {
				rs.close();
			}
			DbConnector.getInstancia().releaseConn();

		} catch (SQLException ex) {
			// Errores
			System.out.println("SQLException: " + ex.getMessage());
			System.out.println("SQLState: " + ex.getSQLState());
			System.out.println("VendorError: " + ex.getErrorCode());
		}
		return pro;
	}

	public void update(Profesional p2, String matricula) {
		// TODO Auto-generated method stub
		PreparedStatement stmt = null;
		ResultSet rs = null;
		Especialidad esp = p2.getEsp();
		try {
			stmt = DbConnector.getInstancia().getConn().prepareStatement(
					"update profesionales set matricula=?,nombre_prof=?,apellido_prof=?,email=?,password=?,cod_especialidad=?,estado=? where matricula=? ");
			stmt.setString(1, p2.getMatricula());
			stmt.setString(2, p2.getNombre());
			stmt.setString(3, p2.getApellido());
			stmt.setString(4, p2.getEmail());
			stmt.setString(5, p2.getPassword());
			stmt.setInt(6, esp.getCodigo_esp());
			stmt.setInt(7, p2.getEstado());
			stmt.setString(8, matricula);

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

	public void delete(Profesional p) {
		// TODO Auto-generated method stub
		PreparedStatement stmt = null;
		ResultSet rs = null;
		try {
			stmt = DbConnector.getInstancia().getConn()
					.prepareStatement("delete from profesionales where matricula=? ");
			stmt.setString(1, p.getMatricula());
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

}
