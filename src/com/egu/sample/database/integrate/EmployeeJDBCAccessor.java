package com.egu.sample.database.integrate;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.LinkedList;
import java.util.List;

import com.egu.sample.database.entity.Employee;

import lombok.extern.slf4j.Slf4j;

/**
 * JDBCを用いて社員情報にアクセスするオブジェクトです。
 * @author t-eguchi
 */
@Slf4j
class EmployeeJDBCAccessor implements EmployeeAccessor {

	/** テーブル名 */
	private static final String TABLE_NAME = "employee";

	/** 社員番号カラム */
	private static final String NO_COLUMN = "no";

	/** 氏名カラム */
	private static final String NAME_COLUMN = "name";

	/** ふりがなカラム */
	private static final String KANA_COLUMN = "kana";

	/** IDカラム */
	private static final String ID_COLUMN = "id";

	/** 所属カラム */
	private static final String BELONG_COLUMN = "belong";

	/** メールアドレスカラム */
	private static final String EMAIL_COLUMN = "email";

	/** 実行用SQL */
	private static final String SQL_SELECT_ALL = "select * from " + TABLE_NAME;
	private static final String SQL_SELECT_BYTEXT;
	private static final String SQL_INSERT;
	private static final String SQL_UPDATE;
	private static final String SQL_DELETE =
			"delete from " + TABLE_NAME + " where " + NO_COLUMN + " = ?";
	static {
		// select by text
		SQL_SELECT_BYTEXT = new StringBuilder()
				.append("select * from ").append(TABLE_NAME)
				.append(" where ")
				.append(NO_COLUMN).append(" LIKE ?")
				.append(" or ").append(NAME_COLUMN).append(" LIKE ?")
				.append(" or ").append(KANA_COLUMN).append(" LIKE ?")
				.append(" or ").append(ID_COLUMN).append(" LIKE ?")
				.append(" or ").append(BELONG_COLUMN).append(" LIKE ?")
				.append(" or ").append(EMAIL_COLUMN).append(" LIKE ?")
				.toString();

		// insert
		SQL_INSERT = new StringBuilder()
				.append("insert into ").append(TABLE_NAME).append("(")
				.append(NO_COLUMN).append(',')
				.append(NAME_COLUMN).append(',')
				.append(KANA_COLUMN).append(',')
				.append(ID_COLUMN).append(',')
				.append(BELONG_COLUMN).append(',')
				.append(EMAIL_COLUMN).append(')')
				.append(" values (?, ?, ?, ?, ?, ?)")
				.toString();

		// update
		SQL_UPDATE = new StringBuilder()
				.append("update ").append(TABLE_NAME)
				.append(" set ")
				.append(NO_COLUMN).append(" = ?,")
				.append(NAME_COLUMN).append(" = ?,")
				.append(KANA_COLUMN).append(" = ?,")
				.append(ID_COLUMN).append(" = ?,")
				.append(BELONG_COLUMN).append(" = ?,")
				.append(EMAIL_COLUMN).append(" = ?")
				.append(" where ").append(NO_COLUMN).append(" = ?")
				.toString();
	}

	/** 接続 */
	private final Connection connection;

	/**
	 * URL、ユーザ、パスワードを渡すことにより、インスタンスを生成します。
	 * @param url
	 * @param user
	 * @param password
	 * @throws SQLException
	 */
	public EmployeeJDBCAccessor(String url, String user, String password) throws SQLException {
		connection = DriverManager.getConnection(url, user, password);
//		connection.setAutoCommit(false);
	}

	@Override
	public void insert(Employee emp) {
		try (PreparedStatement statement = connection.prepareStatement(SQL_INSERT)) {
			// 値を埋め込み
			int index = 1;
			statement.setString(index++, emp.no());
			statement.setString(index++, emp.name());
			statement.setString(index++, emp.kana());
			statement.setString(index++, emp.id());
			statement.setString(index++, emp.belong());
			statement.setString(index++, emp.email());

			// 実行
			log.info("Inserting employee. statement = {}", statement);
			int updateCount = statement.executeUpdate();
			if (updateCount <= 0) {
				throw new IllegalArgumentException("update count = " + updateCount);
			}

		} catch (SQLException e) {
			log.error("Failed to insert.", e);
			throw new IllegalArgumentException(e);
		}
	}

	@Override
	public void delete(Employee emp) {
		try (PreparedStatement statement = connection.prepareStatement(SQL_DELETE)) {
			// 値を埋め込み
			statement.setString(1, emp.no());

			// 実行
			log.info("Deleting employee. statement = {}", statement);
			int updateCount = statement.executeUpdate();
			if (updateCount <= 0) {
				throw new IllegalArgumentException("update count = " + updateCount);
			}

		} catch (SQLException e) {
			log.error("Failed to delete.", e);
			throw new IllegalArgumentException(e);
		}
	}

	@Override
	public void update(Employee emp) {
		try (PreparedStatement statement = connection.prepareStatement(SQL_UPDATE)) {
			// 値を埋め込み
			int index = 1;
			statement.setString(index++, emp.no());
			statement.setString(index++, emp.name());
			statement.setString(index++, emp.kana());
			statement.setString(index++, emp.id());
			statement.setString(index++, emp.belong());
			statement.setString(index++, emp.email());
			statement.setString(index++, emp.no());

			// 実行
			log.info("Updating employee. statement = {}", statement);
			int updateCount = statement.executeUpdate();
			if (updateCount <= 0) {
				throw new IllegalArgumentException("update count = " + updateCount);
			}

		} catch (SQLException e) {
			log.error("Failed to update.", e);
			throw new IllegalArgumentException(e);
		}
	}

	@Override
	public List<Employee> all() {
		try (
				Statement statement = connection.createStatement();
				ResultSet resultSet = statement.executeQuery(SQL_SELECT_ALL)) {

			// 結果の変換
			List<Employee> emps = toEmployees(resultSet);
			return emps;

		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}

	@Override
	public List<Employee> select(String text) {
		// 全件取得
		List<Employee> allEmployees = all();
		if (text == null || text.isEmpty()) {
			return allEmployees;
		}

		try (PreparedStatement statement = connection.prepareStatement(SQL_SELECT_BYTEXT)) {
			// 埋め込み
			for (int index = 1; index <= 6; index++) {
				statement.setString(index, "%" + text + "%");
			}

			// 実行
			log.info("Selecting statement = {}", statement);
			try (ResultSet resultSet = statement.executeQuery()) {
				// 結果の変換
				List<Employee> emps = toEmployees(resultSet);
				return emps;
			}
		} catch (SQLException e) {
			log.error("Failed to select.", e);
			throw new IllegalArgumentException(e);
		}
	}

	/**
	 * 結果セットを社員リストに変換します。
	 * @param resultSet
	 * @return
	 * @throws SQLException
	 */
	private List<Employee> toEmployees(ResultSet resultSet) throws SQLException {
		List<Employee> emps = new LinkedList<>();
		while (resultSet.next()) {
			Employee emp = new Employee();
			String no = resultSet.getString(NO_COLUMN);
			emp.no(no);

			String name = resultSet.getString(NAME_COLUMN);
			emp.name(name);

			String kana = resultSet.getString(KANA_COLUMN);
			emp.kana(kana);

			String id = resultSet.getString(ID_COLUMN);
			emp.id(id);

			String belong = resultSet.getString(BELONG_COLUMN);
			emp.belong(belong);

			String email = resultSet.getString(EMAIL_COLUMN);
			emp.email(email);
			emps.add(emp);
		}
		return emps;
	}

	public static void main(String[] args) throws SQLException {
		EmployeeAccessor accessor = new EmployeeJDBCAccessor(
				"jdbc:postgresql://localhost:5432/postgres",
				"postgres", "password");
		System.out.println(SQL_INSERT);
		System.out.println(SQL_UPDATE);

//		// all
//		accessor.all().forEach(System.out::println);
//		// insert
//		Employee emp = new Employee();
//		emp.no("xxxx").name("鈴木太郎").kana("すずきたろう");
//		accessor.insert(emp);
//		// all
//		accessor.all().forEach(System.out::println);
//		// update
//		emp.belong("FEG");
//		accessor.update(emp);
//		// all
//		accessor.all().forEach(System.out::println);
//		// delete
//		emp.no("10379");
//		accessor.delete(emp);
		// all
		accessor.select("eg").forEach(System.out::println);
	}

}
