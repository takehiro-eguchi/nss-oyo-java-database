package com.egu.sample.database.entity;

import com.orangesignal.csv.annotation.CsvColumn;
import com.orangesignal.csv.annotation.CsvEntity;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

/**
 * 社員を表すエンティティです。
 * @author t-eguchi
 *
 */
@Data
@NoArgsConstructor
@Accessors(fluent=true)
@CsvEntity(header = true)
public class Employee {


	/** 社員番号 */
	@CsvColumn(position = 0)
	private String no;

	/** 氏名 */
	@CsvColumn(position = 1)
	private String name;

	/** ふりがな */
	@CsvColumn(position = 2)
	private String kana;

	/** ID */
	@CsvColumn(position = 3)
	private String id;

	/** 所属 */
	@CsvColumn(position = 4)
	private String belong;

	/** メールアドレス */
	@CsvColumn(position = 5)
	private String email;

	/**
	 * コピーコンストラクタです。
	 * @param src
	 */
	public Employee(Employee src) {
		this.no = src.no;
		this.name = src.name;
		this.kana = src.kana;
		this.id = src.id;
		this.belong = src.belong;
		this.email = src.email;
	}
}
