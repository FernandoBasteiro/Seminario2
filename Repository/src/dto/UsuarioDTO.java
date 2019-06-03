package dto;

import java.io.Serializable;
import java.time.LocalDate;

public class UsuarioDTO implements Serializable {
	private static final long serialVersionUID = 2897638576412881813L;
	private Integer id;
	private String login;
	private String pwd;
	private String nombre;
	private String token;
	private String varUbicacion;
	private Integer varDispHoraria;
	private String varNivel;
	private boolean activo;
	private LocalDate varFechaNac;
	
	public LocalDate getVarFechaNac() {
		return varFechaNac;
	}

	public void setVarFechaNac(LocalDate varFechaNac) {
		this.varFechaNac = varFechaNac;
	}

	public UsuarioDTO(int id, String login, String pwd, String nombre, String token, String varUbicacion,
			int varDispHoraria, String varNivel, boolean activo) {
		this.id = id;
		this.login = login;
		this.pwd = pwd;
		this.nombre = nombre;
		this.token = token;
		this.varUbicacion = varUbicacion;
		this.varDispHoraria = varDispHoraria;
		this.varNivel = varNivel;
		this.activo = activo;
	}
	
	public UsuarioDTO(String login, String pwd, String token) {
		this.login = login;
		this.pwd = pwd;
		this.token = token;
	}
	
	public UsuarioDTO(String login, String pwd, String nombre, String token, boolean activo) {
		this.login = login;
		this.pwd = pwd;
		this.nombre = nombre;
		this.token = token;
		this.activo = activo;
	}
	
	public UsuarioDTO(String login, String pwd, String nombre, String token, String varUbicacion,
			int varDispHoraria, String varNivel, boolean activo) {
		this.login = login;
		this.pwd = pwd;
		this.nombre = nombre;
		this.token = token;
		this.varUbicacion = varUbicacion;
		this.varDispHoraria = varDispHoraria;
		this.varNivel = varNivel;
		this.activo = activo;
	}
	
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getLogin() {
		return login;
	}

	public void setLogin(String login) {
		this.login = login;
	}

	public String getPwd() {
		return pwd;
	}

	public void setPwd(String pwd) {
		this.pwd = pwd;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getToken() {
		return token;
	}

	public void setToken(String token) {
		this.token = token;
	}

	public String getVarUbicacion() {
		return varUbicacion;
	}

	public void setVarUbicacion(String varUbicacion) {
		this.varUbicacion = varUbicacion;
	}

	public Integer getVarDispHoraria() {
		return varDispHoraria;
	}

	public void setVarDispHoraria(int varDispHoraria) {
		this.varDispHoraria = varDispHoraria;
	}

	public String getVarNivel() {
		return varNivel;
	}

	public void setVarNivel(String varNivel) {
		this.varNivel = varNivel;
	}

	public boolean isActivo() {
		return activo;
	}

	public void setActivo(boolean activo) {
		this.activo = activo;
	}

}
