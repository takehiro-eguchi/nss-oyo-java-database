# Javaデータベース課題

## 課題
NSS社員を管理するアプリケーションがあり、当該アプリケーションの永続化媒体はCSVファイルである。
データベースへの永続化に切り替えなさい。
データベースへの切り替えを行う上で、以下の段階を踏んで行うこととする。

* Java標準のJDBCを用いた方式で実装
    * 検索は、全件取得後にアプリで削るのではなく、1回のSQLで抽出すること。
    * トランザクションも適切に設定すること。
* データベースに関するライブラリを利用した方式で実装
    * 同上
* テーブルの保持については正規化されていないため、正規化すること。
* 移行作業として、便利サイトよりcsvファイルを取得して、データベースへと投入するバッチを作成する。

**\\seiryu.nssys.co.jp\shinjin-kenshu41\20.Java言語\データベース課題\employee-list.csv** を**resources** 直下に配置すれば、アプリが動作します。

## パッケージ

~~~
com.egu.sample.database         := ルートパッケージ。当該アプリケーションのエントリポイントを提供
                    + entity    := アプリケーションにて利用するエンティティを提供
                    + ui        := アプリケーションにおけるプレゼンテーション層を提供
                    + logic     := アプリケーションにおけるビジネスロジック層を提供
                    + integrate := アプリケーションにおけるインテグレーション層を提供|
~~~

## 必要なJava
JavaFXを利用しているため、JDK8を利用します。
https://www.oracle.com/java/technologies/downloads/#java8-windows
より取得し、`JREシステムライブラリ`に登録してください。

## 必要なプラグイン
* Lombok
    * **lib** 以下の **lombok.jar** をダブルクリック後、**eclipse.exe** を選択して、インストールする。
* e(fx)clipse
    * 「ヘルプ」→「Eclipseマーケットプレース」より、**e(fx)clipse** を検索して、インストールする。

## 利用するミドルウェア及びツール
* PostgreSQL
    * https://www.enterprisedb.com/downloads/postgres-postgresql-downloads
    * 本番運用ではないので、スーパーユーザのパスワードは **password** でよい。
* Scene Builder(直接の利用はなし)
    * https://gluonhq.com/products/scene-builder/
    * 文字化けするので、**\\seiryu\shinjin-kenshu41\20.Java言語\データベース課題\SceneBuilder.cfg** をインストールディレクトリの **app** に配置すること。
