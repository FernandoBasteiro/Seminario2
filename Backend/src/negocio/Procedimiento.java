package negocio;

import dto.ProcedimientoDTO;

public class Procedimiento {
	private int id;
	private String descripcion;
	private String url; 
	private int duracion;
	private Integer cantCalif;
	private Integer sumaCalif;
	
	public Procedimiento(String descripcion, String url, int duracion) {
		this.descripcion = descripcion;
		this.url = url;
		this.duracion = duracion;
		this.cantCalif = 0;
		this.sumaCalif = 0;
	}

	public Procedimiento(int id, String descripcion, String url, int duracion, int cantCalif, int sumaCalif) {
		this.id = id;
		this.descripcion = descripcion;
		this.url = url;
		this.duracion = duracion;
		this.cantCalif = cantCalif;
		this.sumaCalif = sumaCalif;
	}

	public ProcedimientoDTO toDTO() {
		return new ProcedimientoDTO(this.id, this.descripcion, this.url, this.duracion, this.calcularCalificacion());
	}
	
	public void calificar(int nota) {
		this.cantCalif++;
		this.sumaCalif = this.sumaCalif + nota;
	}
	
	public float calcularCalificacion() {
		return (float)sumaCalif/cantCalif;
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
