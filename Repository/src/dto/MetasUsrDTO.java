package dto;

import java.io.Serializable;
import java.util.ArrayList;

public class MetasUsrDTO  implements Serializable {
	private MetasDTO meta;
	private UsuarioDTO usuario;
	public MetasDTO getMeta() {
		return meta;
	}
	public void setMeta(MetasDTO meta) {
		this.meta = meta;
	}
	public UsuarioDTO getUsuario() {
		return usuario;
	}
	public void setUsuario(UsuarioDTO usuario) {
		this.usuario = usuario;
	}
	public MetasUsrDTO(MetasDTO meta, UsuarioDTO usuario) {
		this.meta = meta;
		this.usuario = usuario;
	}
	public MetasUsrDTO() {
	}
	
	
}
