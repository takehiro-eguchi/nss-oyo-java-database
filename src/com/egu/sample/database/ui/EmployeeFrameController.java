package com.egu.sample.database.ui;

import java.util.List;

import org.apache.commons.lang3.StringUtils;

import com.egu.sample.database.entity.Employee;
import com.egu.sample.database.logic.EmployeeStore;

import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;

/**
 * 社員フレームのコントローラオブジェクトです。
 * @author t-eguchi
 *
 */
public class EmployeeFrameController {

	/** 追加ボタン */
	@FXML
	private Button createButton;

	/** 削除ボタン */
	@FXML
	private Button deleteButton;

	/** 検索テキスト */
	@FXML
	private TextField searchText;

	/** リフレッシュボタン */
	@FXML
	private Button refreshButton;

	/** テーブル */
	@FXML
	private TableView<EmployeeModel> employeeView;

	/** 社員番号カラム */
	@FXML
	private TableColumn<EmployeeModel, String> noColumn;

	/** 氏名カラム */
	@FXML
	private TableColumn<EmployeeModel, String> nameColumn;

	/** ふりがなカラム */
	@FXML
	private TableColumn<EmployeeModel, String> kanaColumn;

	/** IDカラム */
	@FXML
	private TableColumn<EmployeeModel, String> idColumn;

	/** 所属カラム */
	@FXML
	private TableColumn<EmployeeModel, String> belongColumn;

	/** メールアドレスカラム */
	@FXML
	private TableColumn<EmployeeModel, String> emailColumn;

	/** 社員ストア */
	private final EmployeeStore store;

	/**
	 * デフォルトコンストラクタにより、インスタンスを生成します。
	 */
	public EmployeeFrameController() {
		this.store = EmployeeStore.getInstance();
	}

	/** 初期化処理を行います */
	@FXML
	public void initialize() {
		initializeCreateButton();
		initializeDeleteButton();
		initializeSearchText();
		initializeRefreshButton();
		initializeEmployeeView();
	}

	/** 追加ボタンの初期化を行います */
	private void initializeCreateButton() {
		// TODO 自動生成されたメソッド・スタブ
		this.createButton.setOnAction(e -> {
			System.err.println("追加ボタンが押下されました。");
		});
	}

	/** 削除ボタンの初期化を行います */
	private void initializeDeleteButton() {
		// TODO 自動生成されたメソッド・スタブ
		this.deleteButton.setOnAction(e -> {
			System.err.println("削除ボタンが押下されました。");
		});
	}

	/** 検索テキストの初期化を行います */
	private void initializeSearchText() {
		this.searchText.setOnAction(e -> {
			// 検索および再描画
			String text = this.searchText.getText();
			List<Employee> employees = this.store.findByText(text);
			redrawView(employees);
		});
	}

	/** リフレッシュボタンの初期化を行います */
	private void initializeRefreshButton() {
		this.refreshButton.setOnAction(e -> {
			String text = this.searchText.getText();
			List<Employee> employees;
			if (StringUtils.isEmpty(text)) {	// 検索テキストが空なら、全検索
				employees = this.store.list();
			} else {	// 検索テキストに入力があれば、検索
				employees = this.store.findByText(text);
			}
			redrawView(employees);
		});
	}

	/** 社員ビューの初期化を行います */
	@SuppressWarnings("unchecked")
	private void initializeEmployeeView() {
		// TODO 自動生成されたメソッド・スタブ
		// カラムの設定
		setCellValueFactory(this.noColumn, EmployeeModel.NO_PROPNAME);
		setCellValueFactory(this.nameColumn, EmployeeModel.NAME_PROPNAME);
		setCellValueFactory(this.kanaColumn, EmployeeModel.KANA_PROPNAME);
		setCellValueFactory(this.idColumn, EmployeeModel.ID_PROPNAME);
		setCellValueFactory(this.belongColumn, EmployeeModel.BELONG_PROPNAME);
		setCellValueFactory(this.emailColumn, EmployeeModel.EMAIL_PROPNAME);
		this.employeeView.getColumns().setAll(
				this.noColumn, this.nameColumn, this.kanaColumn,
				this.idColumn, this.belongColumn, this.emailColumn);

		// 社員の取得および描画
		List<Employee> employees = this.store.list();
		redrawView(employees);
	}

	/**
	 * 社員ビューの再描画を行います。
	 * @param employees
	 */
	private void redrawView(List<Employee> employees) {
		ObservableList<EmployeeModel> models = this.employeeView.getItems();
		models.clear();
		employees.forEach(emp -> {
			models.add(new EmployeeModel(emp));
		});
	}

	/** 社員ビューの再描画を行います */
	private <M, V> void setCellValueFactory(TableColumn<M, V> column, String name) {
		PropertyValueFactory<M, V> factory = new PropertyValueFactory<>(name);
		column.setCellValueFactory(factory);
	}
}
