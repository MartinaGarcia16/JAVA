package entities;

import java.sql.Date;
import java.sql.Time;

public class Turnos {
	Integer numero;
	Date fecha_turno;
	Time hora_turno;
	String matricula_prof;
	Integer id_paciente;
	Integer estado;
	
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
	public String getMatricula_prof() {
		return matricula_prof;
	}
	public void setMatricula_prof(String matricula_prof) {
		this.matricula_prof = matricula_prof;
	}
	public Integer getId_paciente() {
		return id_paciente;
	}
	public void setId_paciente(Integer id_paciente) {
		this.id_paciente = id_paciente;
	}
	public Integer getEstado() {
		return estado;
	}
	public void setEstado(Integer estado) {
		this.estado = estado;
	}
}
