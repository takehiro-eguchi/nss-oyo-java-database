package com.egu.sample.database.ui.edit;

import com.egu.sample.database.entity.Employee;
import com.egu.sample.database.ui.util.FXMLUtil;

import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.stage.Window;

/**
 * 社員編集ステージです。
 * @author t-eguchi
 *
 */
public class EmployeeEditStage extends Stage {

	/** コントローラ */
	private final EmployeeEditController controller;

	/** シーングラフ */
	private final Scene scene;

	/**
	 * デフォルトコンストラクタにより、インスタンスを生成します。
	 */
	public EmployeeEditStage() {
		this(null);
	}

	/**
	 * 社員を渡すことにより、インスタンスを生成します。
	 * @param emp
	 */
	public EmployeeEditStage(Employee emp) {
		// コントローラ
		controller = new EmployeeEditController(emp);

		// ルートペインの取得
		Parent rootPane = FXMLUtil.loadPane("EmployeeEdit.fxml", controller);

		// シーングラフの設定
		scene = new Scene(rootPane, 400, 300);
		setScene(scene);

		// 登録処理成功時には、画面を閉じる
		controller.setOnRegisterSuccess(() -> {
			Window window = scene.getWindow();
			window.hide();
		});
	}
}
