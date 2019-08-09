package com.egu.sample.database.integrate;

import java.net.URL;
import java.util.LinkedList;
import java.util.List;
import java.util.Objects;
import java.util.function.Predicate;
import java.util.stream.Collectors;

import com.egu.sample.database.entity.Employee;

import lombok.NonNull;

/**
 * CSVファイルを用いて、社員にアクセスする実装オブジェクトです。
 * @author t-eguchi
 *
 */
class EmployeeCsvAccessor implements EmployeeAccessor {

	/** デフォルトファイルパス */
	private static final String DEFAULT_FILENAME = "employee-list.csv";

	/** 社員一覧 */
	private List<Employee> employees;

	/** ファイル名 */
	private final String filename;

	/**
	 * デフォルトコンストラクタにより、インスタンスを生成します。
	 */
	public EmployeeCsvAccessor() {
		ClassLoader classLoader = ClassLoader.getSystemClassLoader();
		URL url = classLoader.getResource(DEFAULT_FILENAME);
		if (url == null) {
			throw new IllegalStateException(DEFAULT_FILENAME + " is not found.");
		}
		this.filename = url.getPath();
	}

	/**
	 * ファイル名を渡すことによりインスタンスを生成します。
	 * @param filename
	 */
	public EmployeeCsvAccessor(@NonNull String filename) {
		this.filename = filename;
	}

	@Override
	public synchronized void insert(@NonNull Employee employee) {
		Objects.requireNonNull(this.employees);
	}

	@Override
	public synchronized void delete(@NonNull Employee employee) {
		Objects.requireNonNull(this.employees);
		// TODO 自動生成されたメソッド・スタブ

	}

	@Override
	public synchronized List<Employee> all() {
		Objects.requireNonNull(this.employees);
		List<Employee> copies = new LinkedList<>();
		this.employees.forEach(employee -> {
			Employee copy = new Employee(employee);
			copies.add(copy);
		});
		return copies;
	}

	@Override
	public synchronized List<Employee> select(String text) {
		// 全件取得
		List<Employee> allEmployees = all();
		if (text == null || text.isEmpty()) {
			return allEmployees;
		}

		// 検索条件
		Predicate<Employee> predicate = emp -> contains(emp.getNo(), text);
		predicate = predicate
				.or(emp -> contains(emp.getName(), text))
				.or(emp -> contains(emp.getKana(), text))
				.or(emp -> contains(emp.getId(), text))
				.or(emp -> contains(emp.getBelong(), text))
				.or(emp -> contains(emp.getEmail(), text));

		// 検索
		List<Employee> result = allEmployees.stream()
				.filter(predicate)
				.collect(Collectors.toList());
		return result;
	}

	/**
	 * データの読込を行います。
	 */
	private void load() {
		// TODO 自動生成されたメソッド・スタブ
	}

	/**
	 * データの書き込みを行います。
	 */
	private void save() {
		// TODO 自動生成されたメソッド・スタブ
	}

	/**
	 * 対象の文字列に指定されたパターンが含まれるかどうかを検証します。
	 * @param target
	 * @param pattern
	 * @return
	 */
	private static boolean contains(String target, String pattern) {
		return target != null && target.contains(pattern);
	}
}
