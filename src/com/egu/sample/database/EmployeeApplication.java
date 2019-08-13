package com.egu.sample.database;

import com.egu.sample.database.ui.EmployeeListScene;

import javafx.application.Application;
import javafx.collections.ObservableList;
import javafx.stage.Stage;

/**
 * 社員アプリケーションです。
 * @author t-eguchi
 *
 */
public class EmployeeApplication extends Application {

	@Override
	public void start(Stage stage) {
		// シーンの生成
		EmployeeListScene scene = new EmployeeListScene();

		// スタイルシートの適用
		String styleSheet = getClass().getResource("application.css").toExternalForm();
		ObservableList<String> styleSheets = scene.getStylesheets();
		styleSheets.add(styleSheet);

		// 描画
		stage.setTitle("社員一覧");
		stage.setScene(scene);
		stage.show();
	}

	/**
	 * 実行する。
	 * @param args
	 */
	public static void main(String[] args) {
		launch(args);
	}
}
