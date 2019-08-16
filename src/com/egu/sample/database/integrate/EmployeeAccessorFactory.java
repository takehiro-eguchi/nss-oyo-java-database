package com.egu.sample.database.integrate;

import java.net.URL;

/**
 * 社員アクセサのファクトリクラスです。
 * @author t-eguchi
 *
 */
public final class EmployeeAccessorFactory {

	/** デフォルトファイルパス */
	private static final String DEFAULT_FILENAME = "employee-list.csv";

	/** キー(URL) */
	private static final String URL_KEY = "datasource.url";

	/** キー(ユーザー) */
	private static final String USER_KEY = "datasource.user";

	/** キー(パスワード) */
	private static final String PASS_KEY = "datasource.pass";

	/** インスタンス */
	private static EmployeeAccessor INSTANCE;

	/** デフォルトコンストラクタを隠ぺい */
	private EmployeeAccessorFactory() {}

	/**
	 * インスタンスを取得します。
	 * @return
	 */
	public static synchronized EmployeeAccessor getInstance() {
		// インスタンスが初期化されている場合は再利用
		if (INSTANCE != null) {
			return INSTANCE;
		}

		try {
			// ファイルパスの取得
			// VMオプションが設定されているURLを優先的に利用
			String datasourceUrl = System.getProperty(URL_KEY);
			if (datasourceUrl == null) {
				// VMオプションが設定されていない場合はクラスパスよりデフォルトファイルを利用
				ClassLoader classLoader = ClassLoader.getSystemClassLoader();
				URL resourceUrl = classLoader.getResource(DEFAULT_FILENAME);
				if (resourceUrl == null) {
					throw new IllegalStateException(DEFAULT_FILENAME + " is not found.");
				}
				datasourceUrl = resourceUrl.getPath();
			}

			// アクセサの生成
			if (datasourceUrl.startsWith("jdbc:")) {	// URLの最初が jdbc: で始まる場合はJDBCアクセサ
				String user = System.getProperty(USER_KEY);
				String password = System.getProperty(PASS_KEY);
				INSTANCE = new EmployeeJDBCAccessor(datasourceUrl, user, password);
			} else {	// それ以外はCSVアクセサ
				INSTANCE = new EmployeeCsvAccessor(datasourceUrl);
			}
			return INSTANCE;

		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}
}
