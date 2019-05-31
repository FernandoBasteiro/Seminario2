package dto;

import java.io.Serializable;
import java.util.ArrayList;

public class MetasDTO implements Serializable{
	private static final long serialVersionUID = -3494073997821404572L;
	private int id;
	private String descripcion;
	private boolean completa;
	private String varAccion;
	private String varSujeto; 
	private String varNivel;
	private ArrayList<ProcedimientoDTO> procedimientos;
	private String user;
	
	public MetasDTO(String descripcion, boolean completa, String varAccion, String varSujeto, String varNivel,
			ArrayList<ProcedimientoDTO> procedimientos, String user) {
		this.descripcion = descripcion;
		this.completa = completa;
		this.varAccion = varAccion;
		this.varSujeto = varSujeto;
		this.varNivel = varNivel;
		this.procedimientos = procedimientos;
		this.user = user;
	}
	
	public MetasDTO() {
	
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

	public ArrayList<ProcedimientoDTO> getProcedimientos() {
		return procedimientos;
	}

	public void setProcedimientos(ArrayList<ProcedimientoDTO> procedimientos) {
		this.procedimientos = procedimientos;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	public String getUser() {
		return user;
	}

	public void setUser(String user) {
		this.user = user;
	}
	
	
}
