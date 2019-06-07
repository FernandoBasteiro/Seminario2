package entities;

import java.util.Calendar;
import java.util.Locale;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.joda.time.LocalDate;
import org.joda.time.LocalDateTime;
import org.joda.time.LocalTime;

@Entity
@Table(name="usuarios")
public class UsuarioEntity {

	@Id
	@GeneratedValue
	private Integer id;
	private String login;
	private String pwd;
	private String nombre;
	private String token;
	private String varUbicacion;
	private Integer varDispHoraria;
	private String varNivel;
	private Boolean activo;
	@Temporal(TemporalType.TIMESTAMP)
	private Calendar varFechaNac;

	public UsuarioEntity() {}

	public UsuarioEntity(String login, String pwd, String nombre, String token, String varUbicacion,
			Integer varDispHoraria, String varNivel, boolean activo, LocalDate varFechaNac) {
		this.login = login;
		this.pwd = pwd;
		this.nombre = nombre;
		this.token = token;
		this.varUbicacion = varUbicacion;
		this.varDispHoraria = varDispHoraria;
		this.varNivel = varNivel;
		this.activo = activo;
		this.varFechaNac = varFechaNac.toDateTime(LocalTime.MIDNIGHT).toCalendar(Locale.getDefault());
	}
	
	public LocalDate getVarFechaNac() {
		return (varFechaNac == null ? null : LocalDate.fromCalendarFields(varFechaNac));
	}

	public void setVarFechaNac(LocalDate varFechaNac) {
		if (varFechaNac != null) this.varFechaNac = varFechaNac.toDateTime(LocalTime.MIDNIGHT).toCalendar(Locale.getDefault());
	}

	public UsuarioEntity(String login, String pwd, String nombre, String token, boolean activo) {
		this.login = login;
		this.pwd = pwd;
		this.nombre = nombre;
		this.token = token;
		this.activo = activo;
	}
	
	public UsuarioEntity(Integer id, String login, String pwd, String nombre, String token, String varUbicacion,
			Integer varDispHoraria, String varNivel, boolean activo) {
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

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
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

	public void setVarDispHoraria(Integer varDispHoraria) {
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
