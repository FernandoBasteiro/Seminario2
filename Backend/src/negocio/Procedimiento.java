package negocio;

import dao.MetasDAO;
import dao.ProcedimientoDAO;
import dto.ProcedimientoDTO;
import excepciones.ComunicacionException;

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

	public Boolean getEsPromo() {
		return esPromo;
	}

	public void setEsPromo(Boolean esPromo) {
		this.esPromo = esPromo;
	}

	public Integer getCantCalif() {
		return cantCalif;
	}

	public void setCantCalif(Integer cantCalif) {
		this.cantCalif = cantCalif;
	}

	public Integer getSumaCalif() {
		return sumaCalif;
	}

	public void setSumaCalif(Integer sumaCalif) {
		this.sumaCalif = sumaCalif;
	}

	public void crear() throws ComunicacionException {
		Integer id = ProcedimientoDAO.getInstancia().crear(this);
		if (id != null) this.id = id;
		else throw new ComunicacionException("Hubo un error al generar el procedimiento");
	}

	public void guardar() {
		ProcedimientoDAO.getInstancia().guardar(this);
	}
	
}
