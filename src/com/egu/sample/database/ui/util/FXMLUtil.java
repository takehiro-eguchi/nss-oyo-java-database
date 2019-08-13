package com.egu.sample.database.ui.util;

import java.io.IOException;
import java.net.URL;

import javafx.fxml.FXMLLoader;
import lombok.NonNull;

/**
 * FXMLを扱う際のユーティリティクラスです。
 * @author t-eguchi
 *
 */
public final class FXMLUtil {

	/** デフォルトコンストラクタを隠ぺい */
	private FXMLUtil() {}

	/**
	 * リソースロケーションを取得します。
	 * @param targetClass
	 * @param resourceName
	 * @return
	 */
	public static URL getLocation(
			@NonNull Class<?> targetClass, @NonNull String resourceName) {
		URL location = targetClass.getResource(resourceName);
		return location;
	}

	/**
	 * FXMLおよびコントローラーより、Paneをロードします。
	 * @param <T>
	 * @param location
	 * @param controller
	 * @return
	 */
	public static <T> T loadPane(
			@NonNull URL location, @NonNull Object controller) {
		FXMLLoader loader = new FXMLLoader();
		loader.setLocation(location);
		loader.setController(controller);
		try {
			return loader.load();
		} catch (IOException e) {
			throw new RuntimeException(e);
		}
	}

	/**
	 * リソースロケーションとコントローラより、Paneをロードします。
	 * @param <T>
	 * @param targetClass
	 * @param resourceName
	 * @param controller
	 * @return
	 */
	public static <T> T loadPane(
			@NonNull Class<?> targetClass, @NonNull String resourceName, @NonNull Object controller) {
		URL location = getLocation(targetClass, resourceName);
		return loadPane(location, controller);
	}

	/**
	 * コントローラと同パッケージのリソースロケーションより、Paneをロードします。
	 * @param <T>
	 * @param resourceName
	 * @param controller
	 * @return
	 */
	public static <T> T loadPane(
			@NonNull String resourceName, @NonNull Object controller) {
		URL location = getLocation(
				controller.getClass(), resourceName);
		return loadPane(location, controller);
	}
}
