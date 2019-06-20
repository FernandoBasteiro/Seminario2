package entities;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="procedimientos")
public class ProcedimientoEntity {
	@Id
	@GeneratedValue
	private Integer id;
	private String descripcion;
	private String url; 
	private Integer duracion;
	private Integer cantCalif;
	private Integer sumaCalif;
	private Boolean esPromo;
	
	public Boolean getEsPromo() {
		return esPromo;
	}

	public void setEsPromo(Boolean esPromo) {
		this.esPromo = esPromo;
	}

	public ProcedimientoEntity(String descripcion, String url, Integer duracion, Integer cantCalif, Integer sumaCalif) {
		this.descripcion = descripcion;
		this.url = url;
		this.duracion = duracion;
		this.cantCalif = cantCalif;
		this.sumaCalif = sumaCalif;
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
	
}
