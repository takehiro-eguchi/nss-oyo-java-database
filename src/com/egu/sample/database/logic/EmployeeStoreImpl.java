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
	public ExecutionResult create(Employee employee) {
		// 検証
		ExecutionResult result = EmployeeValidator.validate(employee);
		if (!result.isSuccess()) {
			return result;
		}

		// 追加
		try {
			this.accessor.insert(employee);
			return ExecutionResult.SUCCESS;
		} catch (IllegalArgumentException e) {
			return ExecutionResult.ALREADY_EXISTS;
		}
	}

	@Override
	public ExecutionResult update(Employee employee) {
		// 検証
		ExecutionResult result = EmployeeValidator.validate(employee);
		if (!result.isSuccess()) {
			return result;
		}

		// 更新
		try {
			this.accessor.update(employee);
			return ExecutionResult.SUCCESS;
		} catch (IllegalArgumentException e) {
			return ExecutionResult.NOT_FOUND;
		}
	}

	@Override
	public ExecutionResult delete(Employee employee) {
		// 削除
		try {
			this.accessor.delete(employee);
			return ExecutionResult.SUCCESS;
		} catch (IllegalArgumentException e) {
			return ExecutionResult.NOT_FOUND;
		}
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
