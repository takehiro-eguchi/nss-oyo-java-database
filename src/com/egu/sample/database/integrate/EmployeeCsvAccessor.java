package com.egu.sample.database.integrate;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.URL;
import java.util.LinkedList;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.function.Predicate;
import java.util.stream.Collectors;

import com.egu.sample.database.entity.Employee;
import com.orangesignal.csv.CsvConfig;
import com.orangesignal.csv.manager.CsvEntityLoader;
import com.orangesignal.csv.manager.CsvEntityManager;
import com.orangesignal.csv.manager.CsvEntitySaver;

import lombok.NonNull;

/**
 * CSVファイルを用いて、社員にアクセスする実装オブジェクトです。
 * @author t-eguchi
 *
 */
class EmployeeCsvAccessor implements EmployeeAccessor {

	/** デフォルトファイルパス */
	private static final String DEFAULT_FILENAME = "employee-list.csv";

	/** デフォルトエンコーディング */
	private static final String DEFAULT_ENCODING = "Shift_JIS";

	/** 社員一覧 */
	private List<Employee> employees;

	/** ファイル名 */
	private final String filename;

	/** エンコーディング */
	private final String encoding;

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
		this.encoding = DEFAULT_ENCODING;
		load();
	}

	/**
	 * ファイル名を渡すことによりインスタンスを生成します。
	 * @param filename
	 */
	public EmployeeCsvAccessor(@NonNull String filename) {
		this(filename, DEFAULT_ENCODING);
	}

	/**
	 * ファイル名とエンコーディングを渡すことによりインスタンスを生成します。
	 * @param filename
	 * @param encoding
	 */
	public EmployeeCsvAccessor(@NonNull String filename, @NonNull String encoding) {
		this.filename = filename;
		this.encoding = encoding;
		load();
	}

	@Override
	public synchronized void insert(@NonNull Employee target) {
		// 社員番号の検索
		Objects.requireNonNull(this.employees);
		Optional<Employee> empOpt = findByNo(target);
		empOpt.ifPresent(emp -> {
			throw new IllegalArgumentException(target + " already exist.");
		});
		// 追加&保存
		this.employees.add(target);
		save();
	}

	@Override
	public synchronized void delete(@NonNull Employee target) {
		// 社員番号検索
		Objects.requireNonNull(this.employees);
		Optional<Employee> empOpt = findByNo(target);
		// 削除&保存
		Employee emp = empOpt.orElseThrow(() -> new IllegalArgumentException(target + " is not found."));
		this.employees.remove(emp);
		save();
	}


	@Override
	public void update(Employee target) {
		// 社員番号検索
		Objects.requireNonNull(this.employees);
		Optional<Employee> empOpt = findByNo(target);
		// 削除&追加&保存
		Employee emp = empOpt.orElseThrow(() -> new IllegalArgumentException(target + " is not found."));
		this.employees.remove(emp);
		this.employees.add(target);
		save();
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

		// 検索
		Predicate<Employee> predicate = emp -> contains(emp.no(), text);
		predicate = predicate
				.or(emp -> contains(emp.name(), text))
				.or(emp -> contains(emp.kana(), text))
				.or(emp -> contains(emp.id(), text))
				.or(emp -> contains(emp.belong(), text))
				.or(emp -> contains(emp.email(), text));
		List<Employee> result = allEmployees.stream()
				.filter(predicate)
				.collect(Collectors.toList());
		return result;
	}

	/**
	 * 社員番号から検索します。
	 * @param target
	 * @return
	 */
	private Optional<Employee> findByNo(Employee target) {
		Optional<Employee> ret = this.employees.stream()
				.filter(emp -> emp.no().equals(target.no()))
				.findFirst();
		return ret;
	}

	/**
	 * データの読込を行います。
	 */
	private void load() {
		// 読込オブジェクトの生成
		CsvConfig config = new CsvConfig();
		config.setIgnoreEmptyLines(true);
		config.setQuote('"');
		config.setQuoteDisabled(false);
		CsvEntityManager manager = new CsvEntityManager(config);
		CsvEntityLoader<Employee> loader = manager.load(Employee.class);

		// 読込
		try (InputStream inputStream = new FileInputStream(this.filename)) {
			this.employees = loader.from(inputStream, this.encoding);
		} catch (IOException e) {
			throw new RuntimeException(e);
		}
	}

	/**
	 * データの書き込みを行います。
	 */
	private void save() {
		// 書き込みオブジェクトの生成
		CsvConfig config = new CsvConfig();
		config.setQuote('"');
		config.setQuoteDisabled(false);
		CsvEntityManager manager = new CsvEntityManager(config);
		CsvEntitySaver<Employee> saver = manager.save(this.employees, Employee.class);

		// 書き込み
		try (OutputStream outputStream = new FileOutputStream(this.filename)) {
			saver.to(outputStream, this.encoding);
		} catch (IOException e) {
			throw new RuntimeException(e);
		}
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
