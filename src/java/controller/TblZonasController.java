package controller;

import modelo.TblZonas;
import modelo.TblPacientes;
import java.util.Collection;
import facade.TblZonasFacade;
import javax.inject.Named;
import javax.faces.view.ViewScoped;
import javax.faces.context.FacesContext;
import javax.inject.Inject;

@Named(value = "tblZonasController")
@ViewScoped
public class TblZonasController extends AbstractController<TblZonas> {

	// Flags to indicate if child collections are empty
	private boolean isTblPacientesCollectionEmpty;

	public TblZonasController() {
		// Inform the Abstract parent controller of the concrete TblZonas Entity
		super(TblZonas.class);
	}

	/**
	 * Set the "is[ChildCollection]Empty" property for OneToMany fields.
	 */
	@Override
	protected void setChildrenEmptyFlags() {
		this.setIsTblPacientesCollectionEmpty();
	}

	public boolean getIsTblPacientesCollectionEmpty() {
		return this.isTblPacientesCollectionEmpty;
	}

	private void setIsTblPacientesCollectionEmpty() {
		TblZonas selected = this.getSelected();
		if (selected != null) {
			TblZonasFacade ejbFacade = (TblZonasFacade) this.getFacade();
			this.isTblPacientesCollectionEmpty = ejbFacade.isTblPacientesCollectionEmpty(selected);
		} else {
			this.isTblPacientesCollectionEmpty = true;
		}
	}

	/**
	 * Sets the "items" attribute with a collection of TblPacientes entities that are retrieved from TblZonas and returns the navigation outcome.
	 *
	 * @return navigation outcome for TblPacientes page
	 */
	public String navigateTblPacientesCollection() {
		TblZonas selected = this.getSelected();
		if (selected != null) {
			TblZonasFacade ejbFacade = (TblZonasFacade) this.getFacade();
			Collection<TblPacientes> selectedTblPacientesCollection = ejbFacade.findTblPacientesCollection(selected);
			FacesContext.getCurrentInstance().getExternalContext().getRequestMap().put("TblPacientes_items", selectedTblPacientesCollection);
		}
		return "/app/tblPacientes/index";
	}

}
