package com.egu.sample.database;

import java.net.URL;

import com.egu.sample.database.ui.EmployeeFrameController;

import javafx.application.Application;
import javafx.collections.ObservableList;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.SplitPane;
import javafx.stage.Stage;

/**
 * 社員アプリケーションです。
 * @author t-eguchi
 *
 */
public class EmployeeApplication extends Application {

	@Override
	public void start(Stage stage) {
		try {
			// ルートペインの読込
			URL fxmlLocation = EmployeeFrameController.class.getResource("EmployeeFrame.fxml");
			FXMLLoader loader = new FXMLLoader();
			loader.setLocation(fxmlLocation);
			EmployeeFrameController controller = new EmployeeFrameController();
			loader.setController(controller);
			SplitPane rootPane = loader.load();

			// シーンの生成
			Scene scene = new Scene(rootPane, 850, 500);

			// スタイルシートの適用
			String styleSheet = getClass().getResource("application.css").toExternalForm();
			ObservableList<String> styleSheets = scene.getStylesheets();
			styleSheets.add(styleSheet);

			// 描画
			stage.setTitle("社員一覧");
			stage.setScene(scene);
			stage.show();

		} catch(Exception e) {
			e.printStackTrace();
			throw new RuntimeException(e);
		}
	}

	/**
	 * 実行する。
	 * @param args
	 */
	public static void main(String[] args) {
		launch(args);
	}
}
