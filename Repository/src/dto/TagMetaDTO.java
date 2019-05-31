package dto;

import java.io.Serializable;

import enumeraciones.TipoTagsMetas;

public class TagMetaDTO implements Serializable {
	private static int proxId = 1;
	private int id;
	private String nombre;
	private TipoTagsMetas tipo;
	
	private static int getProxId() {
		return proxId++;
	}
	
	public TagMetaDTO(String nombre, TipoTagsMetas tipo) {
		this.id = TagMetaDTO.getProxId();
		this.nombre = nombre;
		this.tipo = tipo;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public TipoTagsMetas getTipo() {
		return tipo;
	}

	public void setTipo(TipoTagsMetas tipo) {
		this.tipo = tipo;
	}
	
	
}
