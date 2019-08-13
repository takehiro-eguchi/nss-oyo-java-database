package com.egu.sample.database.ui.edit;

import com.egu.sample.database.entity.Employee;
import com.egu.sample.database.ui.util.FXMLUtil;

import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.SplitPane;

/**
 * 社員編集シーングラフです。
 * @author t-eguchi
 *
 */
public class EmployeeEditScene extends Scene {

	public EmployeeEditScene() {
		this(null);
	}

	public EmployeeEditScene(Employee emp) {
		this(emp, 400, 300);
	}

	public EmployeeEditScene(Employee emp, double width, double height) {
		super(loadRoot(emp), width, height);
	}

	private static Parent loadRoot(Employee emp) {
		// コントローラの生成
		EmployeeEditController controller = new EmployeeEditController(emp);

		// ロード
		SplitPane rootPane = FXMLUtil.loadPane("EmployeeEdit.fxml", controller);
		return rootPane;
	}

}
