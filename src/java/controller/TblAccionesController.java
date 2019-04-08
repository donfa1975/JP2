package controller;

import modelo.TblAcciones;
import modelo.TblCitas;
import java.util.Collection;
import facade.TblAccionesFacade;
import javax.inject.Named;
import javax.faces.view.ViewScoped;
import javax.faces.context.FacesContext;
import javax.inject.Inject;

@Named(value = "tblAccionesController")
@ViewScoped
public class TblAccionesController extends AbstractController<TblAcciones> {

	// Flags to indicate if child collections are empty
	private boolean isTblCitasCollectionEmpty;

	public TblAccionesController() {
		// Inform the Abstract parent controller of the concrete TblAcciones Entity
		super(TblAcciones.class);
	}

	/**
	 * Set the "is[ChildCollection]Empty" property for OneToMany fields.
	 */
	@Override
	protected void setChildrenEmptyFlags() {
		this.setIsTblCitasCollectionEmpty();
	}

	public boolean getIsTblCitasCollectionEmpty() {
		return this.isTblCitasCollectionEmpty;
	}

	private void setIsTblCitasCollectionEmpty() {
		TblAcciones selected = this.getSelected();
		if (selected != null) {
			TblAccionesFacade ejbFacade = (TblAccionesFacade) this.getFacade();
			this.isTblCitasCollectionEmpty = ejbFacade.isTblCitasCollectionEmpty(selected);
		} else {
			this.isTblCitasCollectionEmpty = true;
		}
	}

	/**
	 * Sets the "items" attribute with a collection of TblCitas entities that are retrieved from TblAcciones and returns the navigation outcome.
	 *
	 * @return navigation outcome for TblCitas page
	 */
	public String navigateTblCitasCollection() {
		TblAcciones selected = this.getSelected();
		if (selected != null) {
			TblAccionesFacade ejbFacade = (TblAccionesFacade) this.getFacade();
			Collection<TblCitas> selectedTblCitasCollection = ejbFacade.findTblCitasCollection(selected);
			FacesContext.getCurrentInstance().getExternalContext().getRequestMap().put("TblCitas_items", selectedTblCitasCollection);
		}
		return "/app/tblCitas/index";
	}

}
