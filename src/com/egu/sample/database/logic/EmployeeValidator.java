package com.egu.sample.database.logic;

import com.egu.sample.database.entity.Employee;

/**
 * 社員検証を行うオブジェクトです。
 * @author t-eguchi
 *
 */
final class EmployeeValidator {

	/** デフォルトコンストラクタを隠ぺい */
	private EmployeeValidator() {}

	/**
	 * 検証します。
	 * @param employee
	 */
	public static ExecutionResult validate(Employee employee) {
		// 社員番号
		if (isEmpty(employee.no())) {
			return ExecutionResult.INVALID_NO;
		}

		// 氏名
		if (isEmpty(employee.name())) {
			return ExecutionResult.INVALID_NAME;
		}

		// ふりがな
		if (isEmpty(employee.kana())) {
			return ExecutionResult.INVALID_KANA;
		}

		// ID
		if (isEmpty(employee.id())) {
			return ExecutionResult.INVALID_ID;
		}

		return ExecutionResult.SUCCESS;
	}

	/**
	 * 文字列が空かどうかを設定する。
	 * @param value
	 * @return
	 */
	private static boolean isEmpty(String value) {
		return value == null || value.isEmpty();
	}
}
