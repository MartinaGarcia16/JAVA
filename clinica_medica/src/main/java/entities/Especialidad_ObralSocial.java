package entities;

public class Especialidad_ObralSocial {
	
	ObraSocial os;
	Especialidad esp;
	Float procentaje_cobertura;
	
	public Especialidad getEsp() {
		return esp;
	}
	public void setEsp(Especialidad esp) {
		this.esp = esp;
	}
	public ObraSocial getOs() {
		return os;
	}
	public void setOs(ObraSocial os) {
		this.os = os;
	}
	public Float getProcentaje_cobertura() {
		return procentaje_cobertura;
	}
	public void setProcentaje_cobertura(Float procentaje_cobertura) {
		this.procentaje_cobertura = procentaje_cobertura;
	}
}
