package controller;

import modelo.TblPacientes;
import modelo.TblDiagnotiscos;
import modelo.TblMatriculas;
import modelo.TblCitas;
import java.util.Collection;
import facade.TblPacientesFacade;
import java.time.LocalDate;
import javax.inject.Named;
import javax.faces.view.ViewScoped;
import javax.faces.context.FacesContext;
import javax.faces.event.ActionEvent;
import javax.inject.Inject;
import java.time.Period;
import java.time.ZoneId;
import java.util.Date;
@Named(value = "tblPacientesController")
@ViewScoped
public class TblPacientesController extends AbstractController<TblPacientes> {

	@Inject
	private TblAreasController idAreaController;
	@Inject
	private TblCantonesController idCantonController;
	@Inject
	private TblOrigenesController idOrigenController;
	@Inject
	private TblZonasController idzonaController;

	// Flags to indicate if child collections are empty
	private boolean isTblDiagnotiscosCollectionEmpty;
	private boolean isTblMatriculasCollectionEmpty;
	private boolean isTblCitasCollectionEmpty;

	private TblCitas nCita;

	private Collection<TblDiagnotiscos> diagnosticos;
	/**
	 * Get the value of nCita
	 *
	 * @return the value of nCita
	 */
	public TblCitas getnCita() {
		return nCita;
	}

	/**
	 * Set the value of nCita
	 *
	 * @param nCita new value of nCita
	 */
	public void setnCita(TblCitas nCita) {
		this.nCita = nCita;
	}

	
	public TblPacientesController() {
		// Inform the Abstract parent controller of the concrete TblPacientes Entity
		super(TblPacientes.class);
		nCita=null;
	}

	/**
	 * Resets the "selected" attribute of any parent Entity controllers.
	 */
	public void resetParents() {
		idAreaController.setSelected(null);
		idCantonController.setSelected(null);
		idOrigenController.setSelected(null);
		idzonaController.setSelected(null);
	}

	/**
	 * Set the "is[ChildCollection]Empty" property for OneToMany fields.
	 */
	@Override
	protected void setChildrenEmptyFlags() {
		this.setIsTblDiagnotiscosCollectionEmpty();
		this.setIsTblMatriculasCollectionEmpty();
		this.setIsTblCitasCollectionEmpty();
	}

	public boolean getIsTblDiagnotiscosCollectionEmpty() {
		return this.isTblDiagnotiscosCollectionEmpty;
	}

	private void setIsTblDiagnotiscosCollectionEmpty() {
		TblPacientes selected = this.getSelected();
		if (selected != null) {
			TblPacientesFacade ejbFacade = (TblPacientesFacade) this.getFacade();
			this.isTblDiagnotiscosCollectionEmpty = ejbFacade.isTblDiagnotiscosCollectionEmpty(selected);
		} else {
			this.isTblDiagnotiscosCollectionEmpty = true;
		}
	}

	/**
	 * Sets the "items" attribute with a collection of TblDiagnotiscos entities that are retrieved from TblPacientes and returns the navigation outcome.
	 *
	 * @return navigation outcome for TblDiagnotiscos page
	 */
	public String navigateTblDiagnotiscosCollection() {
		TblPacientes selected = this.getSelected();
		if (selected != null) {
			TblPacientesFacade ejbFacade = (TblPacientesFacade) this.getFacade();
			Collection<TblDiagnotiscos> selectedTblDiagnotiscosCollection = ejbFacade.findTblDiagnotiscosCollection(selected);
			FacesContext.getCurrentInstance().getExternalContext().getRequestMap().put("TblDiagnotiscos_items", selectedTblDiagnotiscosCollection);
		}
		return "/app/tblDiagnotiscos/index";
	}

	/**
	 * Sets the "selected" attribute of the TblAreas controller in order to display its data in its View dialog.
	 *
	 * @param event Event object for the widget that triggered an action
	 */
	public void prepareIdArea(ActionEvent event) {
		TblPacientes selected = this.getSelected();
		if (selected != null && idAreaController.getSelected() == null) {
			idAreaController.setSelected(selected.getIdArea());
		}
	}

	/**
	 * Sets the "selected" attribute of the TblCantones controller in order to display its data in its View dialog.
	 *
	 * @param event Event object for the widget that triggered an action
	 */
	public void prepareIdCanton(ActionEvent event) {
		TblPacientes selected = this.getSelected();
		if (selected != null && idCantonController.getSelected() == null) {
			idCantonController.setSelected(selected.getIdCanton());
		}
	}

	/**
	 * Sets the "selected" attribute of the TblOrigenes controller in order to display its data in its View dialog.
	 *
	 * @param event Event object for the widget that triggered an action
	 */
	public void prepareIdOrigen(ActionEvent event) {
		TblPacientes selected = this.getSelected();
		if (selected != null && idOrigenController.getSelected() == null) {
			idOrigenController.setSelected(selected.getIdOrigen());
		}
	}

	/**
	 * Sets the "selected" attribute of the TblZonas controller in order to display its data in its View dialog.
	 *
	 * @param event Event object for the widget that triggered an action
	 */
	public void prepareIdzona(ActionEvent event) {
		TblPacientes selected = this.getSelected();
		if (selected != null && idzonaController.getSelected() == null) {
			idzonaController.setSelected(selected.getIdzona());
		}
	}

	public boolean getIsTblMatriculasCollectionEmpty() {
		return this.isTblMatriculasCollectionEmpty;
	}

	private void setIsTblMatriculasCollectionEmpty() {
		TblPacientes selected = this.getSelected();
		if (selected != null) {
			TblPacientesFacade ejbFacade = (TblPacientesFacade) this.getFacade();
			this.isTblMatriculasCollectionEmpty = ejbFacade.isTblMatriculasCollectionEmpty(selected);
		} else {
			this.isTblMatriculasCollectionEmpty = true;
		}
	}

	/**
	 * Sets the "items" attribute with a collection of TblMatriculas entities that are retrieved from TblPacientes and returns the navigation outcome.
	 *
	 * @return navigation outcome for TblMatriculas page
	 */
	public String navigateTblMatriculasCollection() {
		TblPacientes selected = this.getSelected();
		if (selected != null) {
			TblPacientesFacade ejbFacade = (TblPacientesFacade) this.getFacade();
			Collection<TblMatriculas> selectedTblMatriculasCollection = ejbFacade.findTblMatriculasCollection(selected);
			FacesContext.getCurrentInstance().getExternalContext().getRequestMap().put("TblMatriculas_items", selectedTblMatriculasCollection);
		}
		return "/app/tblMatriculas/index";
	}

	public boolean getIsTblCitasCollectionEmpty() {
		return this.isTblCitasCollectionEmpty;
	}

	private void setIsTblCitasCollectionEmpty() {
		TblPacientes selected = this.getSelected();
		if (selected != null) {
			TblPacientesFacade ejbFacade = (TblPacientesFacade) this.getFacade();
			this.isTblCitasCollectionEmpty = ejbFacade.isTblCitasCollectionEmpty(selected);
		} else {
			this.isTblCitasCollectionEmpty = true;
		}
	}

	/**
	 * Sets the "items" attribute with a collection of TblCitas entities that are retrieved from TblPacientes and returns the navigation outcome.
	 *
	 * @return navigation outcome for TblCitas page
	 */
	public String navigateTblCitasCollection() {
		TblPacientes selected = this.getSelected();
		if (selected != null) {
			TblPacientesFacade ejbFacade = (TblPacientesFacade) this.getFacade();
			Collection<TblCitas> selectedTblCitasCollection = ejbFacade.findTblCitasCollection(selected);
			FacesContext.getCurrentInstance().getExternalContext().getRequestMap().put("TblCitas_items", selectedTblCitasCollection);
		}
		return "/app/tblCitas/index";
	}
	
	public int getEdad(Date fechanac)
	{
		LocalDate ahora=LocalDate.now();
		LocalDate fe1=fechanac.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
		Period periodo=Period.between(fe1, ahora);
		
		return periodo.getYears();
		
	}
	public void prepareNuevaCita(ActionEvent event) {
		TblPacientes selected = this.getSelected();
		if(selected!=null)
		{
			nCita=new TblCitas();
			nCita.setIdCita(0);
			nCita.setIdPaciente(selected);
			
		}
		
	}

	public void guardar(ActionEvent event)
	{
		this.getSelected().setIdPaciente(0);
		this.saveNew();
	}
	
	public void preparaDiags(ActionEvent event)
	{
		if(this.getSelected()!=null)
		{
			this.diagnosticos=this.getSelected().getTblDiagnotiscosCollection();
		}
		else
		{
			this.diagnosticos=null;
		}
	}

	public Collection<TblDiagnotiscos> getDiagnosticos() {
		return diagnosticos;
	}

	public void setDiagnosticos(Collection<TblDiagnotiscos> diagnosticos) {
		this.diagnosticos = diagnosticos;
	}

}
