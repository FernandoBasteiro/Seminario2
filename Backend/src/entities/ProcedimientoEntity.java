package entities;

import javax.persistence.GeneratedValue;
import javax.persistence.Id;

public class ProcedimientoEntity {
	@Id
	@GeneratedValue
	private Integer id;
	private String descripcion;
	private String url; 
	private int duracion;
	
	public ProcedimientoEntity(String descripcion, String url, int duracion) {
		this.descripcion = descripcion;
		this.url = url;
		this.duracion = duracion;
	}

	public ProcedimientoEntity() {}
	
	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getDescripcion() {
		return descripcion;
	}

	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public int getDuracion() {
		return duracion;
	}

	public void setDuracion(int duracion) {
		this.duracion = duracion;
	}
	
}
