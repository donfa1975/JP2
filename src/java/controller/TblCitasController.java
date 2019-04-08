package controller;

import modelo.TblCitas;
import facade.TblCitasFacade;
import javax.inject.Named;
import javax.faces.view.ViewScoped;
import javax.faces.event.ActionEvent;
import javax.inject.Inject;

@Named(value = "tblCitasController")
@ViewScoped
public class TblCitasController extends AbstractController<TblCitas> {

	@Inject
	private TblAccionesController idAccionController;
	@Inject
	private TblPacientesController idPacienteController;

	public TblCitasController() {
		// Inform the Abstract parent controller of the concrete TblCitas Entity
		super(TblCitas.class);
	}

	/**
	 * Resets the "selected" attribute of any parent Entity controllers.
	 */
	public void resetParents() {
		idAccionController.setSelected(null);
		idPacienteController.setSelected(null);
	}

	/**
	 * Sets the "selected" attribute of the TblAcciones controller in order to display its data in its View dialog.
	 *
	 * @param event Event object for the widget that triggered an action
	 */
	public void prepareIdAccion(ActionEvent event) {
		TblCitas selected = this.getSelected();
		if (selected != null && idAccionController.getSelected() == null) {
			idAccionController.setSelected(selected.getIdAccion());
		}
	}

	/**
	 * Sets the "selected" attribute of the TblPacientes controller in order to display its data in its View dialog.
	 *
	 * @param event Event object for the widget that triggered an action
	 */
	public void prepareIdPaciente(ActionEvent event) {
		TblCitas selected = this.getSelected();
		if (selected != null && idPacienteController.getSelected() == null) {
			idPacienteController.setSelected(selected.getIdPaciente());
		}
	}

}
