package controller;

import modelo.TblAreas;
import modelo.TblPacientes;
import java.util.Collection;
import facade.TblAreasFacade;
import javax.inject.Named;
import javax.faces.view.ViewScoped;
import javax.faces.context.FacesContext;
import javax.inject.Inject;

@Named(value = "tblAreasController")
@ViewScoped
public class TblAreasController extends AbstractController<TblAreas> {

	// Flags to indicate if child collections are empty
	private boolean isTblPacientesCollectionEmpty;

	public TblAreasController() {
		// Inform the Abstract parent controller of the concrete TblAreas Entity
		super(TblAreas.class);
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
		TblAreas selected = this.getSelected();
		if (selected != null) {
			TblAreasFacade ejbFacade = (TblAreasFacade) this.getFacade();
			this.isTblPacientesCollectionEmpty = ejbFacade.isTblPacientesCollectionEmpty(selected);
		} else {
			this.isTblPacientesCollectionEmpty = true;
		}
	}

	/**
	 * Sets the "items" attribute with a collection of TblPacientes entities that are retrieved from TblAreas and returns the navigation outcome.
	 *
	 * @return navigation outcome for TblPacientes page
	 */
	public String navigateTblPacientesCollection() {
		TblAreas selected = this.getSelected();
		if (selected != null) {
			TblAreasFacade ejbFacade = (TblAreasFacade) this.getFacade();
			Collection<TblPacientes> selectedTblPacientesCollection = ejbFacade.findTblPacientesCollection(selected);
			FacesContext.getCurrentInstance().getExternalContext().getRequestMap().put("TblPacientes_items", selectedTblPacientesCollection);
		}
		return "/app/tblPacientes/index";
	}

}
