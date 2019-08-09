package com.egu.sample.database.logic;

import java.util.List;

import com.egu.sample.database.entity.Employee;

/**
 * 社員を扱うインタフェースです。
 * @author t-eguchi
 *
 */
public interface EmployeeStore {

	/**
	 * 新規作成を行います。
	 * @param employee
	 */
	ExecutionResult create(Employee employee);

	/**
	 * 更新を行います。
	 * @param employee
	 */
	ExecutionResult update(Employee employee);

	/**
	 * 削除します。
	 * @param employee
	 */
	ExecutionResult delete(Employee employee);

	/**
	 * 一覧を取得します。
	 * @return
	 */
	List<Employee> list();

	/**
	 * テキストにより検索を行う。
	 * @param text
	 * @return
	 */
	List<Employee> findByText(String text);

	/**
	 * 社員ストアの実装を取得します。
	 * @return
	 */
	public static EmployeeStore getInstance() {
		return new EmployeeStoreImpl();
	}
}
