package controller;

import modelo.TblCantones;
import modelo.TblPacientes;
import java.util.Collection;
import facade.TblCantonesFacade;
import javax.inject.Named;
import javax.faces.view.ViewScoped;
import javax.faces.context.FacesContext;
import javax.inject.Inject;

@Named(value = "tblCantonesController")
@ViewScoped
public class TblCantonesController extends AbstractController<TblCantones> {

	// Flags to indicate if child collections are empty
	private boolean isTblPacientesCollectionEmpty;

	public TblCantonesController() {
		// Inform the Abstract parent controller of the concrete TblCantones Entity
		super(TblCantones.class);
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
		TblCantones selected = this.getSelected();
		if (selected != null) {
			TblCantonesFacade ejbFacade = (TblCantonesFacade) this.getFacade();
			this.isTblPacientesCollectionEmpty = ejbFacade.isTblPacientesCollectionEmpty(selected);
		} else {
			this.isTblPacientesCollectionEmpty = true;
		}
	}

	/**
	 * Sets the "items" attribute with a collection of TblPacientes entities that are retrieved from TblCantones and returns the navigation outcome.
	 *
	 * @return navigation outcome for TblPacientes page
	 */
	public String navigateTblPacientesCollection() {
		TblCantones selected = this.getSelected();
		if (selected != null) {
			TblCantonesFacade ejbFacade = (TblCantonesFacade) this.getFacade();
			Collection<TblPacientes> selectedTblPacientesCollection = ejbFacade.findTblPacientesCollection(selected);
			FacesContext.getCurrentInstance().getExternalContext().getRequestMap().put("TblPacientes_items", selectedTblPacientesCollection);
		}
		return "/app/tblPacientes/index";
	}

}
