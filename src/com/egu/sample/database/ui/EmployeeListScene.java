package com.egu.sample.database.ui;

import com.egu.sample.database.ui.util.FXMLUtil;

import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.SplitPane;

/**
 * 社員一覧シーングラフです。
 * @author t-eguchi
 *
 */
public class EmployeeListScene extends Scene {

	/**
	 * デフォルトコンストラクタによりインスタンスを生成します
	 */
	public EmployeeListScene() {
		// 委譲
		this(850, 500);
	}

	/**
	 * 幅と高さを渡すことにより、インスタンスを生成します。
	 * @param width
	 * @param height
	 */
	public EmployeeListScene(double width, double height) {
		// 委譲
		super(loadRoot(), width, height);
	}

	/**
	 * ルートペインをロードします。
	 * @return
	 */
	private static Parent loadRoot() {
		// コントローラの生成
		EmployeeListController controller = new EmployeeListController();

		// ロード
		SplitPane rootPane = FXMLUtil.loadPane("EmployeeList.fxml", controller);
		return rootPane;
	}
}
