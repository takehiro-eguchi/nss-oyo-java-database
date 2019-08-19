package com.egu.sample.database.integrate;

import static org.junit.jupiter.api.Assertions.*;

import java.io.IOException;
import java.net.URISyntaxException;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import com.egu.sample.database.entity.Employee;

/**
 * EmployeeAccessorの検証を行うオブジェクトです。
 * @author t-eguchi
 *
 */
public class EmployeeAccessorTest {

	/** テスト対象 */
	private EmployeeAccessor target;

	/** テストファイル名 */
	private Path testPath;

	@BeforeEach
	public void before() throws IOException, URISyntaxException {
		// ソースファイルをコピー
		URL srcURL = getClass().getResource("dummy-list.csv");
		Path srcPath = Paths.get(srcURL.toURI());
		String testDir = srcPath.getParent().toString();
		this.testPath = Paths.get(testDir + "/EmployeeAccessorTest.csv");
		Files.copy(srcPath, this.testPath);

		// テスト対象の生成
		String testFile = this.testPath.toFile().getPath();
		this.target = new EmployeeCsvAccessor(testFile);
	}

	@AfterEach
	public void after() throws IOException {
		// コピーファイルを削除
		Files.delete(this.testPath);
	}

	@Test
	@DisplayName("insert(正常系)")
	void testInsertLegal() {
		Employee employee = new Employee().no("4300").name("New Comer");
		this.target.insert(employee);
		List<Employee> actual = this.target.all();
		assertEquals(14, actual.size());
	}

	@Test
	@DisplayName("insert(異常系)")
	void testInsertIllegal() {
		Employee employee = new Employee().no("4284").name("New Comer");
		assertThrows(
				IllegalArgumentException.class, () -> this.target.insert(employee));
	}

	@Test
	@DisplayName("delete(正常系)")
	void testDeleteLegal() {
		Employee employee = new Employee().no("4284").name("so.okayasu");
		this.target.delete(employee);
		List<Employee> actual = this.target.all();
		assertEquals(12, actual.size());
	}

	@Test
	@DisplayName("delete(異常系)")
	void testDeleteIllegal() {
		Employee employee = new Employee().no("5000").name("unknown");
		assertThrows(
				IllegalArgumentException.class, () -> this.target.delete(employee));
	}

	@Test
	@DisplayName("all")
	void testAll() {
		List<Employee> actual = this.target.all();
		assertEquals(13, actual.size());
	}

	@Test
	@DisplayName("select")
	void testSelect() {
		List<Employee> actual = this.target.select("川");
		assertEquals(3, actual.size());
		actual = this.target.select(null);
		assertEquals(13, actual.size());
		actual = this.target.select("");
		assertEquals(13, actual.size());
	}
}
