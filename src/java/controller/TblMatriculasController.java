package controller;

import modelo.TblMatriculas;
import facade.TblMatriculasFacade;
import javax.inject.Named;
import javax.faces.view.ViewScoped;
import javax.faces.event.ActionEvent;
import javax.inject.Inject;

@Named(value = "tblMatriculasController")
@ViewScoped
public class TblMatriculasController extends AbstractController<TblMatriculas> {

	@Inject
	private TblPacientesController idPacienteController;
	@Inject
	private TblUsuariosController idusuarioController;

	public TblMatriculasController() {
		// Inform the Abstract parent controller of the concrete TblMatriculas Entity
		super(TblMatriculas.class);
	}

	/**
	 * Resets the "selected" attribute of any parent Entity controllers.
	 */
	public void resetParents() {
		idPacienteController.setSelected(null);
		idusuarioController.setSelected(null);
	}

	/**
	 * Sets the "selected" attribute of the TblPacientes controller in order to display its data in its View dialog.
	 *
	 * @param event Event object for the widget that triggered an action
	 */
	public void prepareIdPaciente(ActionEvent event) {
		TblMatriculas selected = this.getSelected();
		if (selected != null && idPacienteController.getSelected() == null) {
			idPacienteController.setSelected(selected.getIdPaciente());
		}
	}

	/**
	 * Sets the "selected" attribute of the TblUsuarios controller in order to display its data in its View dialog.
	 *
	 * @param event Event object for the widget that triggered an action
	 */
	public void prepareIdusuario(ActionEvent event) {
		TblMatriculas selected = this.getSelected();
		if (selected != null && idusuarioController.getSelected() == null) {
			idusuarioController.setSelected(selected.getIdusuario());
		}
	}

}
