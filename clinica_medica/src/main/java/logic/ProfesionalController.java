package logic;

import java.util.LinkedList;

import dataBase.DataProfesionales;
import entities.Profesional;

public class ProfesionalController {
	private DataProfesionales dp;
	
	public ProfesionalController() {
		dp= new DataProfesionales();
	}

	public LinkedList<Profesional> getAll() {
		// TODO Auto-generated method stub
		return dp.getAll();
	}

	public Profesional getByEmailMatricula(Profesional p) {
		// TODO Auto-generated method stub
		return dp.getByEmailMatricula(p);
	}

	public void add(Profesional p2) {
		// TODO Auto-generated method stub
		dp.add(p2);
	}

	public Profesional getByMatricula(Profesional p) {
		// TODO Auto-generated method stub
		return dp.getByMatricula(p);
	}

	public void update(Profesional p2, String matricula) {
		// TODO Auto-generated method stub
		dp.update(p2,matricula);
	}

	public void delete(Profesional p) {
		// TODO Auto-generated method stub
		dp.delete(p);
	}

}
