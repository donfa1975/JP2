package controller;

import modelo.TblUsuarios;
import modelo.TblDiagnotiscos;
import modelo.TblMatriculas;
import java.util.Collection;
import facade.TblUsuariosFacade;
import controller.util.MobilePageController;
import javax.inject.Named;
import javax.faces.view.ViewScoped;
import javax.faces.context.FacesContext;
import javax.inject.Inject;

@Named(value = "tblUsuariosController")
@ViewScoped
public class TblUsuariosController extends AbstractController<TblUsuarios> {

	@Inject
	private MobilePageController mobilePageController;

	// Flags to indicate if child collections are empty
	private boolean isTblDiagnotiscosCollectionEmpty;
	private boolean isTblMatriculasCollectionEmpty;

	public TblUsuariosController() {
		// Inform the Abstract parent controller of the concrete TblUsuarios Entity
		super(TblUsuarios.class);
	}

	/**
	 * Set the "is[ChildCollection]Empty" property for OneToMany fields.
	 */
	@Override
	protected void setChildrenEmptyFlags() {
		this.setIsTblDiagnotiscosCollectionEmpty();
		this.setIsTblMatriculasCollectionEmpty();
	}

	public boolean getIsTblDiagnotiscosCollectionEmpty() {
		return this.isTblDiagnotiscosCollectionEmpty;
	}

	private void setIsTblDiagnotiscosCollectionEmpty() {
		TblUsuarios selected = this.getSelected();
		if (selected != null) {
			TblUsuariosFacade ejbFacade = (TblUsuariosFacade) this.getFacade();
			this.isTblDiagnotiscosCollectionEmpty = ejbFacade.isTblDiagnotiscosCollectionEmpty(selected);
		} else {
			this.isTblDiagnotiscosCollectionEmpty = true;
		}
	}

	/**
	 * Sets the "items" attribute with a collection of TblDiagnotiscos entities that are retrieved from TblUsuarios and returns the navigation outcome.
	 *
	 * @return navigation outcome for TblDiagnotiscos page
	 */
	public String navigateTblDiagnotiscosCollection() {
		TblUsuarios selected = this.getSelected();
		if (selected != null) {
			TblUsuariosFacade ejbFacade = (TblUsuariosFacade) this.getFacade();
			Collection<TblDiagnotiscos> selectedTblDiagnotiscosCollection = ejbFacade.findTblDiagnotiscosCollection(selected);
			FacesContext.getCurrentInstance().getExternalContext().getRequestMap().put("TblDiagnotiscos_items", selectedTblDiagnotiscosCollection);
		}
		return this.mobilePageController.getMobilePagesPrefix() + "/app/tblDiagnotiscos/index";
	}

	public boolean getIsTblMatriculasCollectionEmpty() {
		return this.isTblMatriculasCollectionEmpty;
	}

	private void setIsTblMatriculasCollectionEmpty() {
		TblUsuarios selected = this.getSelected();
		if (selected != null) {
			TblUsuariosFacade ejbFacade = (TblUsuariosFacade) this.getFacade();
			this.isTblMatriculasCollectionEmpty = ejbFacade.isTblMatriculasCollectionEmpty(selected);
		} else {
			this.isTblMatriculasCollectionEmpty = true;
		}
	}

	/**
	 * Sets the "items" attribute with a collection of TblMatriculas entities that are retrieved from TblUsuarios and returns the navigation outcome.
	 *
	 * @return navigation outcome for TblMatriculas page
	 */
	public String navigateTblMatriculasCollection() {
		TblUsuarios selected = this.getSelected();
		if (selected != null) {
			TblUsuariosFacade ejbFacade = (TblUsuariosFacade) this.getFacade();
			Collection<TblMatriculas> selectedTblMatriculasCollection = ejbFacade.findTblMatriculasCollection(selected);
			FacesContext.getCurrentInstance().getExternalContext().getRequestMap().put("TblMatriculas_items", selectedTblMatriculasCollection);
		}
		return this.mobilePageController.getMobilePagesPrefix() + "/app/tblMatriculas/index";
	}

}
