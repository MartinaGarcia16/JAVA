package logic;

import java.util.LinkedList;

import dataBase.DataTurnos;
import entities.Turnos;

public class TurnosController {
	private DataTurnos dt;
	
	public TurnosController() {
		dt= new DataTurnos();
	}

	public void deletePorMatricula(Turnos t) {
		// TODO Auto-generated method stub
		dt.deletePorMatricula(t);
	}

	public LinkedList<Turnos> getAll() {
		// TODO Auto-generated method stub
		return dt.getAll();
	}

}
