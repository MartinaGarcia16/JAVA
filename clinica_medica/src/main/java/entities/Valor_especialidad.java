package entities;

import java.sql.Date;

public class Valor_especialidad {
	
	Integer cod_especialidad;
	Date fecha_desde;
	Integer valor;
	
	public Integer getCod_especialidad() {
		return cod_especialidad;
	}
	public void setCod_especialidad(Integer cod_especialidad) {
		this.cod_especialidad = cod_especialidad;
	}
	public Date getFecha_desde() {
		return fecha_desde;
	}
	public void setFecha_desde(Date fecha_desde) {
		this.fecha_desde = fecha_desde;
	}
	public Integer getValor() {
		return valor;
	}
	public void setValor(Integer valor) {
		this.valor = valor;
	}
}
