package negocio;

import java.util.ArrayList;

import dao.MetasDAO;
import dto.MetasDTO;
import dto.ProcedimientoDTO;
import excepciones.ComunicacionException;

public class Metas {
	private int id;
	private String descripcion;
	private boolean completa;
	private String varAccion;
	private String varSujeto; 
	private String varNivel;
	private ArrayList<Procedimiento> procedimientos;
	private String user;
	
	public Metas(int id, String descripcion, boolean completa, String varAccion, String varSujeto, String varNivel,
			ArrayList<Procedimiento> procedimientos) {
		this.id = id;
		this.descripcion = descripcion;
		this.completa = completa;
		this.varAccion = varAccion;
		this.varSujeto = varSujeto;
		this.varNivel = varNivel;
		this.procedimientos = procedimientos;
	}
	
	public Metas(int id) {
		this.id = id;
		this.procedimientos = new ArrayList<Procedimiento>();;
	}
	
	public Metas(int id, String descripcion, boolean completa, String varAccion, String varSujeto, String varNivel) {
		this.id = id;
		this.descripcion = descripcion;
		this.completa = completa;
		this.varAccion = varAccion;
		this.varSujeto = varSujeto;
		this.varNivel = varNivel;
	}
	
	public Metas(String descripcion, boolean completa, String varAccion, String varSujeto, String varNivel,
			ArrayList<Procedimiento> procedimientos) {
		this.descripcion = descripcion;
		this.completa = completa;
		this.varAccion = varAccion;
		this.varSujeto = varSujeto;
		this.varNivel = varNivel;
		this.procedimientos = procedimientos;
	}
	
	public Metas(String descripcion, boolean completa, String varAccion, String varSujeto, String varNivel) {
		this.descripcion = descripcion;
		this.completa = completa;
		this.varAccion = varAccion;
		this.varSujeto = varSujeto;
		this.varNivel = varNivel;
	}
	
	
	
	public Metas(String descripcion, boolean completa, String varAccion, String varSujeto, String varNivel,
			ArrayList<Procedimiento> procedimientos, String user) {
		super();
		this.descripcion = descripcion;
		this.completa = completa;
		this.varAccion = varAccion;
		this.varSujeto = varSujeto;
		this.varNivel = varNivel;
		this.procedimientos = procedimientos;
		this.user = user;
	}

	public Metas(int id, String descripcion, boolean completa, String varAccion, String varSujeto, String varNivel,
			ArrayList<Procedimiento> procedimientos, String user) {
		super();
		this.id = id;
		this.descripcion = descripcion;
		this.completa = completa;
		this.varAccion = varAccion;
		this.varSujeto = varSujeto;
		this.varNivel = varNivel;
		this.procedimientos = procedimientos;
		this.user = user;
	}

	public Metas() {}

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

	public ArrayList<Procedimiento> getProcedimientos() {
		return procedimientos;
	}

	public void setProcedimientos(ArrayList<Procedimiento> procedimientos) {
		this.procedimientos = procedimientos;
	}
	
	public String getUser() {
		return user;
	}

	public void setUser(String user) {
		this.user = user;
	}

	public void crear() throws ComunicacionException {
		Integer id = MetasDAO.getInstancia().crear(this);
		if (id != null) this.id = id;
		else throw new ComunicacionException("Hubo un error al generar una nueva meta");
	}
	
	public void guardar() {
		MetasDAO.getInstancia().grabar(this);
	}
	
	public MetasDTO toDTO() {
		ArrayList<ProcedimientoDTO> procedimiento  =new ArrayList<ProcedimientoDTO>();
		for(Procedimiento p : this.getProcedimientos()) {
			procedimiento.add(p.toDTO());
		}
		MetasDTO mdto = new MetasDTO();
		mdto.setId(this.id);
		mdto.setDescripcion(this.descripcion);
		mdto.setCompleta(this.completa);
		mdto.setVarAccion(this.varAccion);
		mdto.setVarSujeto(this.varSujeto);
		mdto.setVarNivel(this.varNivel);
		mdto.setProcedimientos(procedimiento);
		mdto.setUser(this.user);		
		return 	mdto;
	}
}
