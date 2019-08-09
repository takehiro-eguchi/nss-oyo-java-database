package com.egu.sample.database.logic;

import java.util.List;

import com.egu.sample.database.entity.Employee;
import com.egu.sample.database.integrate.EmployeeAccessor;

import lombok.NonNull;

/**
 * 社員ストアの実装オブジェクトです。
 * @author t-eguchi
 *
 */
class EmployeeStoreImpl implements EmployeeStore {

	/** 社員アクセッサ */
	private final EmployeeAccessor accessor;

	/**
	 * デフォルトコンストラクタにより、インスタンスを生成します。
	 */
	public EmployeeStoreImpl() {
		this(EmployeeAccessor.getInstance());
	}

	/**
	 * アクセッサを渡すことにより、インスタンスを生成します。
	 * @param accessor
	 */
	public EmployeeStoreImpl(@NonNull EmployeeAccessor accessor) {
		this.accessor = accessor;
	}

	@Override
	public void register(Employee employee) {
		// TODO 自動生成されたメソッド・スタブ

	}

	@Override
	public void delete(Employee employee) {
		// TODO 自動生成されたメソッド・スタブ

	}

	@Override
	public List<Employee> list() {
		return this.accessor.all();
	}

	@Override
	public List<Employee> findByText(String text) {
		return this.accessor.select(text);
	}
}
