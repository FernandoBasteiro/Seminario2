package negocio;

import dto.ProcedimientoDTO;

public class Procedimiento {
	private int id;
	private String descripcion;
	private String url; 
	private Integer duracion;
	private Integer cantCalif;
	private Integer sumaCalif;
	private Boolean esPromo;
	
	public Procedimiento(String descripcion, String url, Integer duracion) {
		this.descripcion = descripcion;
		this.url = url;
		this.duracion = duracion;
		this.cantCalif = 0;
		this.sumaCalif = 0;
		this.esPromo = false;
	}

	public Procedimiento(int id, String descripcion, String url, Integer duracion, Integer cantCalif, Integer sumaCalif, Boolean esPromo) {
		this.id = id;
		this.descripcion = descripcion;
		this.url = url;
		this.duracion = duracion;
		this.cantCalif = cantCalif;
		this.sumaCalif = sumaCalif;
		this.esPromo = esPromo;
	}

	public ProcedimientoDTO toDTO() {
		return new ProcedimientoDTO(this.id, this.descripcion, this.url, this.duracion, this.calcularCalificacion(), this.esPromo);
	}
	
	public void calificar(int nota) {
		if (cantCalif != null) {
			this.cantCalif++;
			this.sumaCalif = this.sumaCalif + nota;			
		}
		else {
			this.cantCalif = 1;
			this.sumaCalif = nota;			
		}
	}
	
	public float calcularCalificacion() {
		if (cantCalif != null && cantCalif > 0) return (float)sumaCalif/cantCalif;
		else return 0;
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
		
}
