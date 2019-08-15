package com.egu.sample.database.ui.edit;

import static com.egu.sample.database.ui.util.FXMLUtil.*;

import com.egu.sample.database.entity.Employee;
import com.egu.sample.database.logic.EmployeeStore;
import com.egu.sample.database.logic.EmployeeStoreFactory;
import com.egu.sample.database.logic.ExecutionResult;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import lombok.extern.slf4j.Slf4j;

/**
 * 社員編集コントローラです。
 * @author t-eguchi
 *
 */
@Slf4j
public class EmployeeEditController {

	/** 社員番号 */
	@FXML
	private TextField noText;

	/** 氏名 */
	@FXML
	private TextField nameText;

	/** ふりがな */
	@FXML
	private TextField kanaText;

	/** ID */
	@FXML
	private TextField idText;

	/** 所属 */
	@FXML
	private TextField belongText;

	/** メールアドレス */
	@FXML
	private TextField emailText;

	/** 登録ボタン */
	@FXML
	private Button registerButton;

	/** 新規作成フラグ */
	private final boolean isNew;

	/** 社員 */
	private final Employee emp;

	/** 社員ストア */
	private final EmployeeStore store = EmployeeStoreFactory.getInstance();

	/** 登録成功処理 */
	private Runnable onRegisterSuccessRunner;

	/**
	 * デフォルトコンストラクタによりインスタンスを生成します。
	 */
	public EmployeeEditController() {
		this(null);
	}

	/**
	 * 社員を渡すことにより、インスタンスを生成します。
	 * @param emp
	 */
	public EmployeeEditController(Employee emp) {
		if (emp != null) {	// 更新
			this.emp = new Employee(emp);
			isNew = false;
		} else {	// 新規作成
			this.emp = new Employee();
			isNew = true;
		}
	}

	/** 初期化処理を行います */
	@FXML
	private void initialize() {
		initializeTexts();
		initializeRegisterButton();
	}

	/** テキスト項目の初期化を行います */
	private void initializeTexts() {
		// 各テキストに社員の情報を設定
		noText.setText(emp.no());
		nameText.setText(emp.name());
		kanaText.setText(emp.kana());
		idText.setText(emp.id());
		belongText.setText(emp.belong());
		emailText.setText(emp.email());

		// 更新の場合、社員番号は変更不可
		if (!isNew) {
			noText.setEditable(false);
		}
	}

	/** 登録ボタンの初期化を行います */
	private void initializeRegisterButton() {
		registerButton.setOnAction(e -> {
			registerEmployee();
		});
	}

	/** 登録を行います */
	private void registerEmployee() {
		// 各テキストの内容を反映
		emp.no(noText.getText());
		emp.name(nameText.getText());
		emp.kana(kanaText.getText());
		emp.id(idText.getText());
		emp.belong(belongText.getText());
		emp.email(emailText.getText());

		// 登録処理
		try {
			ExecutionResult result;
			if (isNew) {	// 新規
				result = store.create(emp);
			} else {	// 更新
				result = store.update(emp);
			}

			// 処理成功
			if (result.isSuccess()) {
				showInfo("社員番号 " + emp.no() + " の登録に成功しました");
				if (onRegisterSuccessRunner != null) {
					onRegisterSuccessRunner.run();
				}
				return;
			}

			// 処理失敗
			showError("社員の登録に失敗しました cause = " + result.name());

		} catch (Exception e) {
			log.error("Failed to register.", e);
			showError("社員番号 " + emp.no() + " の登録に失敗しました");
		}
	}

	/**
	 * 登録成功時のイベントを登録します。
	 * @param runner
	 */
	public void setOnRegisterSuccess(Runnable runner) {
		onRegisterSuccessRunner = runner;
	}
}
