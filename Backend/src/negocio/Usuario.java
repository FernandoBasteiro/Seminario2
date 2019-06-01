package negocio;

import org.joda.time.LocalDate;

import dao.UsuarioDAO;
import dto.UsuarioDTO;
import excepciones.ComunicacionException;

public class Usuario {
	private int id;
	private String login;
	private String pwd;
	private String nombre;
	private String token;
	private String varUbicacion;
	private int varDispHoraria;
	private String varNivel;
	private boolean activo;
	private LocalDate varFechaNac;
	
	public LocalDate getVarFechaNac() {
		return varFechaNac;
	}

	public void setVarFechaNac(LocalDate localDate) {
		this.varFechaNac = localDate;
	}

	public Usuario(int id, String login, String pwd, String nombre, String token, String varUbicacion,
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
	
	public Usuario(int id, String login, String pwd, String nombre, String token, boolean activo) {
		this.id = id;
		this.login = login;
		this.pwd = pwd;
		this.nombre = nombre;
		this.token = token;
		this.activo = activo;
	}
	
	public Usuario(String login, String pwd, String nombre, String token, boolean activo) {
		this.login = login;
		this.pwd = pwd;
		this.nombre = nombre;
		this.token = token;
		this.activo = activo;
	}
	
	public void crear() throws ComunicacionException {
		int id = UsuarioDAO.getInstancia().crear(this);
		if (id != 0) this.id = id;
		else throw new ComunicacionException("Hubo un error al generar el nuevo Usuario");
	}
	
	public int getId() {
		return id;
	}

	public boolean passwordCorrecta(String password) {
		return (this.pwd.equals(password));
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

	public int getVarDispHoraria() {
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
	
	public void grabar() {
		UsuarioDAO.getInstancia().grabar(this);
	}
	
	public void actualizar() throws ComunicacionException {
		UsuarioDAO.getInstancia().actualizar(this);
	}

	public UsuarioDTO toDTO() {
		return new UsuarioDTO(this.id, this.login, null, this.nombre, this.token, this.varUbicacion, this.varDispHoraria, this.varNivel, this.activo);
	}
	
}
