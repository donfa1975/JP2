package controller;

import modelo.TblOrigenes;
import modelo.TblPacientes;
import java.util.Collection;
import facade.TblOrigenesFacade;
import javax.inject.Named;
import javax.faces.view.ViewScoped;
import javax.faces.context.FacesContext;
import javax.inject.Inject;

@Named(value = "tblOrigenesController")
@ViewScoped
public class TblOrigenesController extends AbstractController<TblOrigenes> {

	// Flags to indicate if child collections are empty
	private boolean isTblPacientesCollectionEmpty;

	public TblOrigenesController() {
		// Inform the Abstract parent controller of the concrete TblOrigenes Entity
		super(TblOrigenes.class);
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
		TblOrigenes selected = this.getSelected();
		if (selected != null) {
			TblOrigenesFacade ejbFacade = (TblOrigenesFacade) this.getFacade();
			this.isTblPacientesCollectionEmpty = ejbFacade.isTblPacientesCollectionEmpty(selected);
		} else {
			this.isTblPacientesCollectionEmpty = true;
		}
	}

	/**
	 * Sets the "items" attribute with a collection of TblPacientes entities that are retrieved from TblOrigenes and returns the navigation outcome.
	 *
	 * @return navigation outcome for TblPacientes page
	 */
	public String navigateTblPacientesCollection() {
		TblOrigenes selected = this.getSelected();
		if (selected != null) {
			TblOrigenesFacade ejbFacade = (TblOrigenesFacade) this.getFacade();
			Collection<TblPacientes> selectedTblPacientesCollection = ejbFacade.findTblPacientesCollection(selected);
			FacesContext.getCurrentInstance().getExternalContext().getRequestMap().put("TblPacientes_items", selectedTblPacientesCollection);
		}
		return "/app/tblPacientes/index";
	}

}
