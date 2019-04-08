package controller;

import modelo.TblDiagnotiscos;
import facade.TblDiagnotiscosFacade;
import javax.inject.Named;
import javax.faces.view.ViewScoped;
import javax.faces.event.ActionEvent;
import javax.inject.Inject;

@Named(value = "tblDiagnotiscosController")
@ViewScoped
public class TblDiagnotiscosController extends AbstractController<TblDiagnotiscos> {

	@Inject
	private TblPacientesController idPacienteController;
	@Inject
	private TblTipoDiagnosticoController idTipoDiagnosticoController;
	@Inject
	private TblUsuariosController idusuarioController;

	public TblDiagnotiscosController() {
		// Inform the Abstract parent controller of the concrete TblDiagnotiscos Entity
		super(TblDiagnotiscos.class);
	}

	/**
	 * Resets the "selected" attribute of any parent Entity controllers.
	 */
	public void resetParents() {
		idPacienteController.setSelected(null);
		idTipoDiagnosticoController.setSelected(null);
		idusuarioController.setSelected(null);
	}

	/**
	 * Sets the "selected" attribute of the TblPacientes controller in order to display its data in its View dialog.
	 *
	 * @param event Event object for the widget that triggered an action
	 */
	public void prepareIdPaciente(ActionEvent event) {
		TblDiagnotiscos selected = this.getSelected();
		if (selected != null && idPacienteController.getSelected() == null) {
			idPacienteController.setSelected(selected.getIdPaciente());
		}
	}

	/**
	 * Sets the "selected" attribute of the TblTipoDiagnostico controller in order to display its data in its View dialog.
	 *
	 * @param event Event object for the widget that triggered an action
	 */
	public void prepareIdTipoDiagnostico(ActionEvent event) {
		TblDiagnotiscos selected = this.getSelected();
		if (selected != null && idTipoDiagnosticoController.getSelected() == null) {
			idTipoDiagnosticoController.setSelected(selected.getIdTipoDiagnostico());
		}
	}

	/**
	 * Sets the "selected" attribute of the TblUsuarios controller in order to display its data in its View dialog.
	 *
	 * @param event Event object for the widget that triggered an action
	 */
	public void prepareIdusuario(ActionEvent event) {
		TblDiagnotiscos selected = this.getSelected();
		if (selected != null && idusuarioController.getSelected() == null) {
			idusuarioController.setSelected(selected.getIdusuario());
		}
	}

}
