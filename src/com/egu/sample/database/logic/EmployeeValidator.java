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
	public static void validate(Employee employee) {
		// 社員番号
		if (isEmpty(employee.getNo())) {
			raiseError("no");
		}

		// 氏名
		if (isEmpty(employee.getName())) {
			raiseError("name");
		}
		//
//		@CsvColumn(position = 0)
//		private String no;
//
//		@CsvColumn(position = 1)
//		private String name;
//
//		/** ふりがな */
//		@CsvColumn(position = 2)
//		private String kana;
//
//		/** ID */
//		@CsvColumn(position = 3)
//		private String id;
//
//		/** 所属 */
//		@CsvColumn(position = 4)
//		private String belong;
//
//		/** メールアドレス */
//		@CsvColumn(position = 5)
//		private String email;
	}

	/**
	 * 文字列が空かどうかを設定する。
	 * @param value
	 * @return
	 */
	private static boolean isEmpty(String value) {
		return value == null || value.isEmpty();
	}

	/**
	 * エラー例外をスローします。
	 * @param name
	 */
	private static void raiseError(String name) {
		throw new IllegalArgumentException(name);
	}
}
