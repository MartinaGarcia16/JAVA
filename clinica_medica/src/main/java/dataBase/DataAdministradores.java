package dataBase;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.LinkedList;

import entities.Administradores;



public class DataAdministradores {

	public Administradores getByUser(Administradores a) throws SQLException {
		PreparedStatement stmt = null;
		ResultSet rs = null;
		Administradores adm = null;
		String consulta = "select id, dni, nombre, apellido, email \r\n"
				+ " from administradores where email=? and password=?";
		try{
			// Crear la conexión
			stmt = DbConnector.getInstancia().getConn().prepareStatement(consulta);
			
			// Ejecutar la query
			stmt.setString(1, a.getEmail());
			stmt.setString(2, a.getPassword());
			rs = stmt.executeQuery();
			
			// Mapeo de ResultSet a objeto
			if(rs!= null && rs.next()) {
				adm = new Administradores(); 
				adm.setId(rs.getInt("id"));
				adm.setDni(rs.getString("dni"));
				adm.setNombre(rs.getString("nombre"));
				adm.setApellido(rs.getString("apellido"));
				adm.setEmail(rs.getString("email"));
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
		return adm;
	}

	public LinkedList<Administradores> getAll() {
		// TODO Auto-generated method stub
		Statement stmt=null;
		ResultSet rs=null;
		LinkedList<Administradores> administradores = new LinkedList<>();
		try {
					
		// Ejecutar la query
		stmt = DbConnector.getInstancia().getConn().createStatement();
		rs = stmt.executeQuery("select * from administradores");
					
		// Mapeao de ResultSet a objeto
		if (rs!=null) {
			while(rs.next()) {
				Administradores adm = new Administradores();
				adm.setId(rs.getInt("id"));
				adm.setDni(rs.getString("dni"));
				adm.setNombre(rs.getString("nombre"));
				adm.setApellido(rs.getString("apellido"));
				adm.setEmail(rs.getString("email"));
				adm.setPassword(rs.getString("password"));
				administradores.add(adm);
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
		
		return administradores;
	}

	public Administradores getByEmailDni(Administradores a) {
		// TODO Auto-generated method stub
		PreparedStatement stmt = null;
		ResultSet rs = null;
		Administradores adm = null;
		String consulta = "select id, dni, nombre, apellido, email \r\n"
				+ " from administradores where email=? or dni=?";
		try{
			// Crear la conexión
			stmt = DbConnector.getInstancia().getConn().prepareStatement(consulta);
			
			// Ejecutar la query
			stmt.setString(1, a.getEmail());
			stmt.setString(2, a.getDni());
			rs = stmt.executeQuery();
			
			// Mapeo de ResultSet a objeto
			if(rs!= null && rs.next()) {
				adm = new Administradores(); 
				adm.setId(rs.getInt("id"));
				adm.setDni(rs.getString("dni"));
				adm.setNombre(rs.getString("nombre"));
				adm.setApellido(rs.getString("apellido"));
				adm.setEmail(rs.getString("email"));
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
		return adm;
	}

	public void add(Administradores a2) {
		// TODO Auto-generated method stub
		PreparedStatement stmt= null;
		ResultSet keyResultSet=null;
		try {
			stmt=DbConnector.getInstancia().getConn().
					prepareStatement(
							"insert into administradores(nombre,apellido,dni,email,password) values(?,?,?,?,?)",
							PreparedStatement.RETURN_GENERATED_KEYS
							);
			stmt.setString(1, a2.getNombre());
			stmt.setString(2, a2.getApellido());
			stmt.setString(3, a2.getDni());
			stmt.setString(4, a2.getEmail());
			stmt.setString(5, a2.getPassword());
			stmt.executeUpdate();
			
			keyResultSet=stmt.getGeneratedKeys();
            if(keyResultSet!=null && keyResultSet.next()){
                a2.setId(keyResultSet.getInt(1));
            }

			
		}  catch (SQLException e) {
            e.printStackTrace();
		} finally {
            try {
                if(keyResultSet!=null)keyResultSet.close();
                if(stmt!=null)stmt.close();
                DbConnector.getInstancia().releaseConn();
            } catch (SQLException e) {
            	e.printStackTrace();
            }
		}
	}

	public Administradores getById(Administradores a) {
		// TODO Auto-generated method stub
		PreparedStatement stmt = null;
		ResultSet rs = null;
		Administradores adm = null;
		String consulta = "select id, dni, nombre, apellido, email,password \r\n"
				+ " from administradores where id=?";
		try{
			// Crear la conexión
			stmt = DbConnector.getInstancia().getConn().prepareStatement(consulta);
			
			// Ejecutar la query
			stmt.setInt(1, a.getId());
			rs = stmt.executeQuery();
			
			// Mapeo de ResultSet a objeto
			if(rs!= null && rs.next()) {
				adm = new Administradores(); 
				adm.setId(rs.getInt("id"));
				adm.setDni(rs.getString("dni"));
				adm.setNombre(rs.getString("nombre"));
				adm.setApellido(rs.getString("apellido"));
				adm.setEmail(rs.getString("email"));
				adm.setPassword(rs.getString("password"));
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
		return adm;
	}

	public void update(Administradores a2) {
		// TODO Auto-generated method stub
		PreparedStatement stmt= null;
		ResultSet rs=null;
		try {
			stmt=DbConnector.getInstancia().getConn().
					prepareStatement(
							"update administradores set nombre=?,apellido=?,dni=?,email=?,password=? where id=? "
							);
			stmt.setString(1, a2.getNombre());
			stmt.setString(2, a2.getApellido());
			stmt.setString(3, a2.getDni());
			stmt.setString(4, a2.getEmail());
			stmt.setString(5, a2.getPassword());
			stmt.setInt(6, a2.getId());
		
			stmt.executeUpdate();
			
		}  catch (SQLException e) {
            e.printStackTrace();
		} finally {
            try {
                if(rs!=null)rs.close();
                if(stmt!=null)stmt.close();
                DbConnector.getInstancia().releaseConn();
            } catch (SQLException e) {
            	e.printStackTrace();
            }
		}
		
	}

	public void delete(Administradores a) {
		// TODO Auto-generated method stub
		PreparedStatement stmt = null;
		ResultSet rs = null;
		try {
			stmt = DbConnector.getInstancia().getConn().prepareStatement("delete from administradores where id=? ");
			stmt.setInt(1, a.getId());
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
