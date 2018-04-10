# これはチャットルームサイトです
<br>
レイアウトは無料テンプレートを使用。
<br><br>
＜画面＞<br><br>
・ログイン画面-<br>
  [機能]<br>
    １．登録済のメールとパスワードを入力し「Login」ボタンをクリックするとチャット画面へ遷移する。<br>
    ２．「Register」ボタンをクリックすると登録画面へ遷移する。<br>
    ３．登録済のメールとパスワードを入力し「Update」ボタンをクリックすると登録済のメールとパスワードを変更する。<br>
    <br>
・登録画面-<br>
  [機能]<br>
    １．メール、パスワードと名前を入力し「Regist」ボタンをクリックするとデータベースへ各情報を登録する。名前は空欄でもよい。<br><br>
・チャット画面-<br>
<t>[機能]<br>
<t> <t>・メニュー<br>
<t> <t> <t>１．AllChat-データベースに登録された全てのチャットを表示する。<br>
<t> <t> <t>２．MyChat-チャット済の自分の発言のみ表示する。<br>
<t> <t> <t>３．LogOut-ログイン画面へ遷移する。<br>
<t> <t> <t>４．テキスト入力欄に文章を入力し「書き込む」ボタンをクリックすると、入力した文章をデータベースへ登録し<br>
         チャット画面に反映させる。<br><br>
        
<開発環境><br>
Eclipse(4.6.2)<br>
Sqlite3<br>

<ライブラリ><br>
・jackson<br>
・sqlite-jdbc<br>
・taglibs<br>
<br>
・パスワード暗号化のためのpasswordUtil クラスは下記サイトのものをそのまま使用。<br>
http://www.casleyconsulting.co.jp/blog-engineer/java/%E3%80%90java-se-8%E9%99%90%E5%AE%9A%E3%80%91%E5%AE%89%E5%85%A8%E3%81%AA%E3%83%91%E3%82%B9%E3%83%AF%E3%83%BC%E3%83%89%E3%82%92%E7%94%9F%E6%88%90%E3%81%99%E3%82%8B%E6%96%B9%E6%B3%95/<br>
<br>
