package voogasalad_GucciGames.gameAuthoring.gui.gaedialog.mapobjsettings;

import java.util.ArrayList;
import java.util.List;

import javafx.geometry.Insets;
import javafx.scene.control.Button;
import javafx.scene.control.Dialog;
import javafx.scene.layout.GridPane;
import javafx.scene.text.Text;
import voogasalad_GucciGames.gameAuthoring.IDialogGaeController;
import voogasalad_GucciGames.gameAuthoring.gui.gaedialog.dialogcomponents.DialogTableView;
import voogasalad_GucciGames.gameAuthoring.gui.gaedialog.groovySettings.groovyParams.GCharParam;

public class RulesPane extends GridPane {

	private Text title = new Text("Rules");
	private Button saveBtn = new Button("Save and Next");
	private List<GCharParam> charParams = new ArrayList<GCharParam>();

	private List<String> rules = new ArrayList<String>();
	private DialogTableView tableView;
	private IDialogGaeController controller;

	private DialogTableView rulesTableView;

	public RulesPane(IDialogGaeController controller) {
		this.controller = controller;
		loadRules();

		rulesTableView = new DialogTableView(rules, "Add Rules");
		this.add(rulesTableView, 0, 0);
		this.add(saveBtn, 3, 3);

		loadRules();
		addActionToSaveBtn();
		setLayout();

	}

	private void setLayout() {
		this.setHgap(5);
		this.setVgap(5);
		this.setPadding(new Insets(5, 5, 5, 5));
	}

	private void loadRules() {
		controller.getAllRules().forEach(rule -> {
			rules.add(rule.getName());
		});
		;

	}

	private void addActionToSaveBtn() {
		this.saveBtn.setOnAction(e -> {
			List<String> data = tableView.getData();
			// TODO: add Rules to Action

			Dialog d = new Dialog();
			d.showAndWait();

		});
	}

}
