package dto;

import java.io.Serializable;
import java.util.ArrayList;

public class RecomendacionesDTO implements Serializable{
	private ArrayList<ProcedimientoDTO> promoProcs;
	private Integer sumaPromoProcs;
	private ArrayList<ProcedimientoDTO> normalProcs;
	private ArrayList<MetasUsrDTO> metas;
	public ArrayList<ProcedimientoDTO> getPromoProcs() {
		return promoProcs;
	}
	public void setPromoProcs(ArrayList<ProcedimientoDTO> promoProcs) {
		this.promoProcs = promoProcs;
	}
	public Integer getSumaPromoProcs() {
		return sumaPromoProcs;
	}
	public void setSumaPromoProcs(Integer sumaPromoProcs) {
		this.sumaPromoProcs = sumaPromoProcs;
	}
	public ArrayList<ProcedimientoDTO> getNormalProcs() {
		return normalProcs;
	}
	public void setNormalProcs(ArrayList<ProcedimientoDTO> normalProcs) {
		this.normalProcs = normalProcs;
	}
	public ArrayList<MetasUsrDTO> getMetas() {
		return metas;
	}
	public void setMetas(ArrayList<MetasUsrDTO> metas) {
		this.metas = metas;
	}
	public RecomendacionesDTO(ArrayList<ProcedimientoDTO> promoProcs, Integer sumaPromoProcs,
			ArrayList<ProcedimientoDTO> normalProcs, ArrayList<MetasUsrDTO> metas) {
		this.promoProcs = promoProcs;
		this.sumaPromoProcs = sumaPromoProcs;
		this.normalProcs = normalProcs;
		this.metas = metas;
	}
	
	
}
