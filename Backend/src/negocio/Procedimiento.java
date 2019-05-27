package negocio;

import dto.ProcedimientoDTO;

public class Procedimiento {
	private int id;
	private String descripcion;
	private String url; 
	private int duracion;
	
	public Procedimiento() {
	}
	
	public Procedimiento(String descripcion, String url, int duracion) {
		this.descripcion = descripcion;
		this.url = url;
		this.duracion = duracion;
	}

	public Procedimiento(int id, String descripcion, String url, int duracion) {
		this.id = id;
		this.descripcion = descripcion;
		this.url = url;
		this.duracion = duracion;
	}

	public ProcedimientoDTO toDTO() {
		return new ProcedimientoDTO(this.id, this.descripcion, this.url, this.duracion);
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

	public int getDuracion() {
		return duracion;
	}

	public void setDuracion(int duracion) {
		this.duracion = duracion;
	}
		
}
