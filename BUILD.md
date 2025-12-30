# ビルド手順

このドキュメントでは、VotingGachaプラグインをビルドする詳細な手順を説明します。

## 前提条件

- **Java Development Kit (JDK) 17以降**
- **インターネット接続**（依存関係のダウンロードに必要）
- **IntelliJ IDEA 2025.3.1**（推奨）または任意のJava IDE

## 方法1: IntelliJ IDEA 2025.3.1を使用する場合

### 1. プロジェクトを開く

1. IntelliJ IDEAを起動
2. メニューから「File」→「Open」を選択
3. このプロジェクトのルートディレクトリを選択
4. 「OK」をクリック

### 2. Gradleプロジェクトとして認識される

- IntelliJ IDEAがGradleプロジェクトを自動的に検出します
- 右下に「Gradle build scripts found」という通知が表示される場合は「Import Gradle Project」をクリック
- 依存関係のダウンロードが開始されます（初回は時間がかかります）

### 3. ビルドを実行

**方法A: Gradle UIを使用**
1. 右側の「Gradle」タブをクリック
2. 「VotingGacha」→「Tasks」→「build」→「build」をダブルクリック
3. ビルドログが表示され、進行状況を確認できます

**方法B: メニューから**
1. メニューから「Build」→「Build Project」を選択
2. または「Ctrl + F9」（Windows/Linux）/「Cmd + F9」（macOS）を押す

### 4. ビルド結果の確認

ビルドが成功すると、以下の場所にJARファイルが生成されます：
```
build/libs/VotingGacha-1.0.0.jar
```

## 方法2: コマンドラインを使用する場合

### Windows

```cmd
gradlew.bat clean build
```

### macOS / Linux

```bash
./gradlew clean build
```

### ビルド結果

成功すると以下のメッセージが表示されます：
```
BUILD SUCCESSFUL in XXs
```

JARファイルの場所：
```
build/libs/VotingGacha-1.0.0.jar
```

## トラブルシューティング

### ビルドが失敗する場合

#### 1. インターネット接続を確認

依存関係のダウンロードにはインターネット接続が必要です。

#### 2. Gradleキャッシュをクリア

```bash
# Windows
gradlew.bat clean --refresh-dependencies

# macOS / Linux
./gradlew clean --refresh-dependencies
```

#### 3. Java バージョンを確認

```bash
java -version
javac -version
```

Java 17以降が必要です。

#### 4. プロキシ設定（企業ネットワーク等）

プロキシを使用している場合、`gradle.properties` に以下を追加：

```properties
systemProp.http.proxyHost=proxy.example.com
systemProp.http.proxyPort=8080
systemProp.https.proxyHost=proxy.example.com
systemProp.https.proxyPort=8080
```

### IntelliJ IDEAで認識されない場合

1. 「File」→「Invalidate Caches」→「Invalidate and Restart」を実行
2. プロジェクトを再度開く

### 依存関係のダウンロードが遅い場合

Spigot APIのリポジトリが遅い場合があります。しばらく待つか、別のタイミングで再試行してください。

## ビルド成果物

ビルドが成功すると、以下のファイルが生成されます：

```
build/
├── classes/                    # コンパイルされたクラスファイル
├── libs/
│   └── VotingGacha-1.0.0.jar  # 配布用JARファイル（これをサーバーに配置）
├── resources/                  # リソースファイル
└── tmp/                        # 一時ファイル
```

## 次のステップ

ビルドが成功したら：

1. `build/libs/VotingGacha-1.0.0.jar` をMinecraftサーバーの `plugins` フォルダにコピー
2. サーバーを起動または再起動
3. `plugins/VotingGacha/config.yml` で設定をカスタマイズ

詳細は [README.md](README.md) を参照してください。
