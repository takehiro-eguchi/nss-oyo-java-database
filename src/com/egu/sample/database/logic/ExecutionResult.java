package com.egu.sample.database.logic;

/**
 * 実行結果を表す例外です。
 * @author t-eguchi
 *
 */
public enum ExecutionResult {
	SUCCESS,
	INVALID_NO,
	INVALID_NAME,
	INVALID_KANA,
	INVALID_ID,
	ALREADY_EXISTS,
	NOT_FOUND,
	;
}
