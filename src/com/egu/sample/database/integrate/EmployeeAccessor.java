package com.egu.sample.database.integrate;

import java.util.List;

import com.egu.sample.database.entity.Employee;

/**
 * 社員データソースにアクセスするインタフェースです。
 * @author t-eguchi
 *
 */
public interface EmployeeAccessor {

	/**
	 * 社員を追加します。
	 * @param employee
	 */
	void insert(Employee employee);

	/**
	 * 社員を削除します。
	 * @param employee
	 */
	void delete(Employee employee);

	/**
	 * 社員を更新します。
	 * @param employee
	 */
	void update(Employee employee);

	/**
	 * 全社員を取得します。
	 */
	List<Employee> all();

	/**
	 * テキストより合致するレコードの検索を行います。
	 * @param text
	 * @return
	 */
	List<Employee> select(String text);
}
