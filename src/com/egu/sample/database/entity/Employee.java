package com.egu.sample.database.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 社員を表すエンティティです。
 * @author t-eguchi
 *
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Employee {

	/** 社員番号 */
	private String no;

	/** 氏名 */
	private String name;

	/** ふりがな */
	private String kana;

	/** ID */
	private String id;

	/** 所属 */
	private String belong;

	/** メールアドレス */
	private String email;
}
