package com.egu.sample.database.ui;

import com.egu.sample.database.entity.Employee;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import lombok.ToString;

/**
 * 表示用社員モデルオブジェクトです。
 * @author t-eguchi
 *
 */
@ToString
public class EmployeeModel {

	/** 社員番号 */
	private final StringProperty noProperty;

	/** 氏名 */
	private final StringProperty nameProperty;

	/** ふりがな */
	private final StringProperty kanaProperty;

	/** ID */
	private final StringProperty idProperty;

	/** 所属 */
	private final StringProperty belongProperty;

	/** メールアドレス */
	private final StringProperty emailProperty;

	/** 社員 */
	private final Employee emp;

	/**
	 * 社員オブジェクトを渡すことにより、インスタンスを生成します。
	 * @param emp
	 */
	public EmployeeModel(Employee emp) {
		this.emp = emp;
		this.noProperty = new SimpleStringProperty(emp.no());
		this.nameProperty = new SimpleStringProperty(emp.name());
		this.kanaProperty = new SimpleStringProperty(emp.kana());
		this.idProperty = new SimpleStringProperty(emp.id());
		this.belongProperty = new SimpleStringProperty(emp.belong());
		this.emailProperty = new SimpleStringProperty(emp.email());
	}

	/** 社員番号プロパティ */
	public static final String NO_PROPNAME = "no";
	public StringProperty noProperty() {
		return noProperty;
	}

	/** 氏名プロパティ */
	public static final String NAME_PROPNAME = "name";
	public StringProperty nameProperty() {
		return nameProperty;
	}

	/** ふりがたプロパティ */
	public static final String KANA_PROPNAME = "kana";
	public StringProperty kanaProperty() {
		return kanaProperty;
	}


	/** IDプロパティ */
	public static final String ID_PROPNAME = "id";
	public StringProperty idProperty() {
		return idProperty;
	}

	/** 所属プロパティ */
	public static final String BELONG_PROPNAME = "belong";
	public StringProperty belongProperty() {
		return belongProperty;
	}

	/** メールアドレスプロパティ */
	public static final String EMAIL_PROPNAME = "email";
	public StringProperty emailProperty() {
		return emailProperty;
	}

	/**
	 * 実体を取得します。
	 * @return
	 */
	public Employee getEntity() {
		return this.emp;
	}
}
