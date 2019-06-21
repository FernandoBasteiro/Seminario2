package entities;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.Table;

@Entity
@Table(name="metas")
public class MetasEntity {
	@Id
	@GeneratedValue
	private Integer id;
	private String descripcion;
	private Boolean completa;
	private String varAccion;
	private String varSujeto; 
	private String varNivel;
	@ManyToMany (cascade = CascadeType.ALL)
	private List<ProcedimientoEntity> procedimientos;
	private String user;

	public List<ProcedimientoEntity> getProcedimientos() {
		return procedimientos;
	}
	public void setProcedimientos(ArrayList<ProcedimientoEntity> procedimientos) {
		this.procedimientos = procedimientos;
	}
	
	public MetasEntity() {
	}
	
	public MetasEntity(String descripcion, boolean completa, String varAccion, String varSujeto, String varNivel, String user) {
		this.descripcion = descripcion;
		this.completa = completa;
		this.varAccion = varAccion;
		this.varSujeto = varSujeto;
		this.varNivel = varNivel;
		this.user = user;
	}
	
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
	public boolean isCompleta() {
		return completa;
	}
	public void setCompleta(boolean completa) {
		this.completa = completa;
	}
	public String getVarAccion() {
		return varAccion;
	}
	public void setVarAccion(String varAccion) {
		this.varAccion = varAccion;
	}
	public String getVarSujeto() {
		return varSujeto;
	}
	public void setVarSujeto(String varSujeto) {
		this.varSujeto = varSujeto;
	}
	public String getVarNivel() {
		return varNivel;
	}
	public void setVarNivel(String varNivel) {
		this.varNivel = varNivel;
	}
	public void setProcedimientos(List<ProcedimientoEntity> procedimientos) {
		this.procedimientos = procedimientos;
	}
	public String getUser() {
		return user;
	}
	public void setUser(String user) {
		this.user = user;
	}
	
}
