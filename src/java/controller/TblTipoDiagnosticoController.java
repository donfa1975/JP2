package controller;

import modelo.TblTipoDiagnostico;
import modelo.TblDiagnotiscos;
import java.util.Collection;
import facade.TblTipoDiagnosticoFacade;
import javax.inject.Named;
import javax.faces.view.ViewScoped;
import javax.faces.context.FacesContext;
import javax.inject.Inject;

@Named(value = "tblTipoDiagnosticoController")
@ViewScoped
public class TblTipoDiagnosticoController extends AbstractController<TblTipoDiagnostico> {

	// Flags to indicate if child collections are empty
	private boolean isTblDiagnotiscosCollectionEmpty;

	public TblTipoDiagnosticoController() {
		// Inform the Abstract parent controller of the concrete TblTipoDiagnostico Entity
		super(TblTipoDiagnostico.class);
	}

	/**
	 * Set the "is[ChildCollection]Empty" property for OneToMany fields.
	 */
	@Override
	protected void setChildrenEmptyFlags() {
		this.setIsTblDiagnotiscosCollectionEmpty();
	}

	public boolean getIsTblDiagnotiscosCollectionEmpty() {
		return this.isTblDiagnotiscosCollectionEmpty;
	}

	private void setIsTblDiagnotiscosCollectionEmpty() {
		TblTipoDiagnostico selected = this.getSelected();
		if (selected != null) {
			TblTipoDiagnosticoFacade ejbFacade = (TblTipoDiagnosticoFacade) this.getFacade();
			this.isTblDiagnotiscosCollectionEmpty = ejbFacade.isTblDiagnotiscosCollectionEmpty(selected);
		} else {
			this.isTblDiagnotiscosCollectionEmpty = true;
		}
	}

	/**
	 * Sets the "items" attribute with a collection of TblDiagnotiscos entities that are retrieved from TblTipoDiagnostico and returns the navigation outcome.
	 *
	 * @return navigation outcome for TblDiagnotiscos page
	 */
	public String navigateTblDiagnotiscosCollection() {
		TblTipoDiagnostico selected = this.getSelected();
		if (selected != null) {
			TblTipoDiagnosticoFacade ejbFacade = (TblTipoDiagnosticoFacade) this.getFacade();
			Collection<TblDiagnotiscos> selectedTblDiagnotiscosCollection = ejbFacade.findTblDiagnotiscosCollection(selected);
			FacesContext.getCurrentInstance().getExternalContext().getRequestMap().put("TblDiagnotiscos_items", selectedTblDiagnotiscosCollection);
		}
		return "/app/tblDiagnotiscos/index";
	}

}
