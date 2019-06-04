package dto;

import java.io.Serializable;

public class ProcedimientoDTO implements Serializable{
	private static final long serialVersionUID = -3494073997821404572L;
	private int id;
	private String descripcion;
	private String url; 
	private Integer duracion;
	private Float calificacion;
	
	public ProcedimientoDTO(int id, String descripcion, String url, int duracion, float calificacion) {
		this.id = id;
		this.descripcion = descripcion;
		this.url = url;
		this.duracion = duracion;
		this.calificacion = calificacion;
	}
	
	public ProcedimientoDTO(String descripcion, String url, int duracion) {
		this.descripcion = descripcion;
		this.url = url;
		this.duracion = duracion;
	}
	
	public ProcedimientoDTO(int id) {
		this.id = id;
	}
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
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
	public Integer getDuracion() {
		return duracion;
	}
	public void setDuracion(Integer duracion) {
		this.duracion = duracion;
	}
	public Float getCalificacion() {
		return calificacion;
	}
	public void setCalificacion(Float calificacion) {
		this.calificacion = calificacion;
	}
	
	

}
