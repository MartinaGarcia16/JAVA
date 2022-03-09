package entities;

import java.sql.Date;
import java.sql.Time;

public class Turnos {
	Integer numero;
	Date fecha_turno;
	Time hora_turno;
	Profesional prof;
	Paciente pac;
	
	public Profesional getProf() {
		return prof;
	}
	public void setProf(Profesional prof) {
		this.prof = prof;
	}
	public Paciente getPac() {
		return pac;
	}
	public void setPac(Paciente pac) {
		this.pac = pac;
	}
	public Integer getNumero() {
		return numero;
	}
	public void setNumero(Integer numero) {
		this.numero = numero;
	}
	public Date getFecha_turno() {
		return fecha_turno;
	}
	public void setFecha_turno(Date fecha_turno) {
		this.fecha_turno = fecha_turno;
	}
	public Time getHora_turno() {
		return hora_turno;
	}
	public void setHora_turno(Time hora_turno) {
		this.hora_turno = hora_turno;
	}
}
