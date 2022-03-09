package logic;

import java.sql.SQLException;
import java.util.LinkedList;

import dataBase.DataEspecialidad_ObraSocial;
import dataBase.DataEspecialidades;
import dataBase.DataProfesionales;
import dataBase.DataValor_Especialidad;
import entities.Especialidad;
import entities.Profesional;
import entities.Valor_especialidad;

public class EspecialidadesController {
	private DataEspecialidades de;
	private DataProfesionales dp;
	private DataEspecialidad_ObraSocial deo;
	private DataValor_Especialidad dve;
	
	public EspecialidadesController() {
		de = new DataEspecialidades();
		dp=new DataProfesionales();
		deo= new DataEspecialidad_ObraSocial();
		dve= new DataValor_Especialidad();
		}

	public LinkedList<Especialidad> getAll() throws SQLException {
		// TODO Auto-generated method stub
		return de.getAll();
	}

	public Especialidad getByNombre(Especialidad e) throws SQLException {
		// TODO Auto-generated method stub
		return  de.getByNombre(e);
	}

	public void add(Especialidad e2,Valor_especialidad ve) {
		e2=de.add(e2);
		Especialidad esp = new Especialidad();
		esp.setCodigo_esp(e2.getCodigo_esp());
		ve.setEsp(esp);;
		dve.insertarValor(ve);
	}

	public Especialidad getByCodigo(Especialidad e) throws SQLException {
		// TODO Auto-generated method stub
		return de.getByCodigo(e);
	}

	public void update(Especialidad esp, Valor_especialidad ve)throws SQLException {
		de.update(esp);
		dve.insertarValor(ve);
	}

	public Integer delete(Especialidad e) throws SQLException {
		LinkedList<Profesional> profesionales= new LinkedList<>();
		profesionales= dp.getByEspecialidad(e);
		if (profesionales== null || profesionales.isEmpty()) {
		deo.delete(e);
		dve.delete(e);
		de.delete(e);
		return 1;
		}else {
			return 0;
		}
		
	}

	public LinkedList<Valor_especialidad> getValores() {
		// TODO Auto-generated method stub
		return dve.getValoresActuales();
	}

	public Valor_especialidad getValorPorCodigo(Valor_especialidad ve) {
		// TODO Auto-generated method stub
		return dve.getValorPorCodigo(ve);
	}

}
