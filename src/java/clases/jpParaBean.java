/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package clases;

import javafx.scene.control.TreeTableColumn.CellEditEvent;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.inject.Named;
import javax.faces.view.ViewScoped;
import modelo.TblAreas;
import modelo.TblAsiste;
import modelo.TblCantones;
import modelo.TblOrigenes;
import modelo.TblTipoDiagnostico;
import modelo.TblZonas;

/**
 *
 * @author fabricio.diaz
 */
@Named(value = "jpParaBean")
@ViewScoped
public class jpParaBean {

	/**
	 * Creates a new instance of jpParaBean
	 */
	private clsParametros parametros;
	private String mensaje;
	private String parametro;
	private TblAreas _areaSel;
	private TblCantones _cantonSel;
	private TblOrigenes _origenSel;
	private TblZonas _zonaSel;
	private TblTipoDiagnostico _tipoDiagSel;
	private TblAsiste _asisteSel;
	//////////////////////////////
	private Boolean _verArea=false;
	private Boolean _verCanton=false;
	private Boolean _verOrigen=false;
	private Boolean _verZona=false;
	private Boolean _verAsiste=false;
	//////////////////////////////
	
	
	public jpParaBean() {
	  parametros=new clsParametros();	
	  mensaje="";
	  this._verArea=true;
	  parametro="";
	}
	public void setMostrar(int op)
	{
		_verArea=false;
		_verCanton=false;
		_verOrigen=false;
		_verZona=false;
		_verAsiste=false;
		switch(op)
		{
			case 1:_verArea=true;break;
			case 2:_verCanton=true;break;
			case 3:_verOrigen=true;break;
			case 4:_verZona=true;break;
			case 5:_verAsiste=true;break;
		}
	}
	public clsParametros getParametros() {
		return parametros;
	}

	public void setParametros(clsParametros parametros) {
		this.parametros = parametros;
	}
	
	public void editaArea(TblAreas a)
	{
		this.setParametro(a.getArea());
		this._areaSel=a;
		//return "index?faces-redirect=true";
	}
	public void updateDato()
	{
		this.parametros.updateArea(_areaSel,parametro, 1);
		this.parametros.loadData();
		//return "index?faces-redirect=true";
	}
	
	
	/*public void onCellEdit(CellEditEvent event) {
        Object oldValue = event.getOldValue();
        Object newValue = event.getNewValue();
	         
        if(newValue != null && !newValue.equals(oldValue)) {
           // FacesMessage msg = new FacesMessage(FacesMessage.SEVERITY_INFO, "Cell Changed", "Old: " + oldValue + ", New:" + newValue);
            //FacesContext.getCurrentInstance().addMessage(null, msg);
			mensaje=oldValue + "-"+ newValue;
		}
	}*/

	public String getMensaje() {
		return mensaje;
	}

	public void setMensaje(String mensaje) {
		this.mensaje = mensaje;
	}

	public TblAreas getAreaSel() {
		return _areaSel;
	}

	public void setAreaSel(TblAreas _areaSel) {
		this._areaSel = _areaSel;
	}

	public TblCantones getCantonSel() {
		return _cantonSel;
	}

	public void setCantonSel(TblCantones _cantonSel) {
		this._cantonSel = _cantonSel;
	}

	public TblOrigenes getOrigenSel() {
		return _origenSel;
	}

	public void setOrigenSel(TblOrigenes _origenSel) {
		this._origenSel = _origenSel;
	}

	public TblZonas getZonaSel() {
		return _zonaSel;
	}

	public void setZonaSel(TblZonas _zonaSel) {
		this._zonaSel = _zonaSel;
	}

	public TblTipoDiagnostico getTipoDiagSel() {
		return _tipoDiagSel;
	}

	public void setTipoDiagSel(TblTipoDiagnostico _tipoDiagSel) {
		this._tipoDiagSel = _tipoDiagSel;
	}

	public TblAsiste getAsisteSel() {
		return _asisteSel;
	}

	public void setAsisteSel(TblAsiste _asisteSel) {
		this._asisteSel = _asisteSel;
	}

	public String getParametro() {
		return parametro;
	}

	public void setParametro(String parametro) {
		this.parametro = parametro;
	}

	public Boolean getVerArea() {
		return _verArea;
	}

	public void setVerArea(Boolean _verArea) {
		this._verArea = _verArea;
	}

	public Boolean getVerCanton() {
		return _verCanton;
	}

	public void setVerCanton(Boolean _verCanton) {
		this._verCanton = _verCanton;
	}

	public Boolean getVerOrigen() {
		return _verOrigen;
	}

	public void setVerOrigen(Boolean _verOrigen) {
		this._verOrigen = _verOrigen;
	}

	public Boolean getVerZona() {
		return _verZona;
	}

	public void setVerZona(Boolean _verZona) {
		this._verZona = _verZona;
	}

	public Boolean getVerAsiste() {
		return _verAsiste;
	}

	public void setVerAsiste(Boolean _verAsiste) {
		this._verAsiste = _verAsiste;
	}
}
