# Javaデータベース課題

## 課題
社員一覧がアプリケーションがあり、当該アプリケーションの永続化媒体はCSVファイルである。
データベースへの永続化に切り替えなさい。
データベースへの切り替えを行う上で、以下の2段階を行うこととする。

* Java標準のJDBCを用いた方式
    * 検索は、全件取得後にアプリで削るのではなく、1回のSQLで抽出すること。
    * トランザクションも適切に設定すること。
* データベースに関するライブラリを利用した方式
    * 同上
* 便利サイトよりcsvファイルを取得して、データベースへと投入するバッチも作成してください。

## 必要なプラグイン
* Lombok
* e(fx)clipse

## 利用するミドルウェア
* PostgreSQL
    * https://www.enterprisedb.com/downloads/postgres-postgresql-downloads
* Scene Builder(直接の利用はなし)
    * https://gluonhq.com/products/scene-builder/
    * 文字化けするので、** \\seiryu\shinjin-kenshu41\20.Java言語\データベース課題\SceneBuilder.cfg ** をインストールディレクトリの ** app ** に配置すること。

## TODO
* チェックボックスの実装
* 追加画面及び追加処理
* 削除処理
* 社員番号による、更新画面の表示
* 更新処理
