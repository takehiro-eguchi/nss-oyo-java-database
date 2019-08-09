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
	 * 登録します。
	 * @param employee
	 */
	void register(Employee employee);

	/**
	 * 削除します。
	 * @param employee
	 */
	void delete(Employee employee);

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
