package com.egu.sample.database.logic;

/**
 * 社員ストアのファクトリクラスです。
 * @author t-eguchi
 *
 */
public final class EmployeeStoreFactory {

	/** デフォルトコンストラクタを隠ぺい */
	private EmployeeStoreFactory() {}

	/**
	 * インスタンスを取得します
	 * @return
	 */
	public static EmployeeStore getInstance() {
		return new EmployeeStoreImpl();
	}
}
