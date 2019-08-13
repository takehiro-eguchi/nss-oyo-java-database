package com.egu.sample.database.integrate;

/**
 * 社員アクセサのファクトリクラスです。
 * @author t-eguchi
 *
 */
public final class EmployeeAccessorFactory {

	/** インスタンス */
	private static final EmployeeAccessor INSTANCE = new EmployeeCsvAccessor();

	/** デフォルトコンストラクタを隠ぺい */
	private EmployeeAccessorFactory() {}

	/**
	 * インスタンスを取得します。
	 * @return
	 */
	public static EmployeeAccessor getInstance() {
		return INSTANCE;
	}
}
