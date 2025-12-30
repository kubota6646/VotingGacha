# VotingGacha

Minecraft Java版 1.19.4 対応の投票ガチャプラグイン

## 概要

VotingGachaは、プレイヤーが投票サイトで投票すると、ランダムでアイテムが貰えるMinecraftプラグインです。
Votifierプラグインと連携して動作します。

## 特徴

- 🎲 **ランダムアイテム配布**: 投票時にランダムでアイテムを獲得
- ⚖️ **重み付け設定**: アイテムごとに排出確率を調整可能
- 🎨 **カスタマイズ可能**: アイテム名、説明文、個数などを自由に設定
- 💬 **日本語対応**: すべてのメッセージが日本語
- 🔧 **簡単設定**: config.ymlで簡単にカスタマイズ

## 必要環境

- **Minecraft**: Java版 1.19.4
- **サーバー**: Spigot / Paper 1.19.4以降
- **Java**: 17以降
- **依存プラグイン**: Votifier 2.x（オプション）

## インストール方法

1. このリポジトリをクローンまたはダウンロード
2. Gradleでビルド:
   ```bash
   ./gradlew build
   ```
3. `build/libs/VotingGacha-0.0.1.jar` をサーバーの `plugins` フォルダにコピー
4. Votifierプラグインをインストール（まだの場合）
5. サーバーを起動
6. `plugins/VotingGacha/config.yml` を編集して設定をカスタマイズ
7. サーバーをリロードまたは再起動

## IntelliJ IDEA 2025.3.1でのビルド方法

**注意：ビルドにはインターネット接続が必要です**（Spigot API等の依存関係をダウンロードするため）

### IDEから：

1. IntelliJ IDEAで「File」→「Open」からこのプロジェクトを開く
2. Gradleプロジェクトとして認識されるまで待つ
3. 右側の「Gradle」タブを開く
4. 「VotingGacha」→「Tasks」→「build」→「build」をダブルクリック
5. ビルドが完了すると `build/libs/VotingGacha-0.0.1.jar` が生成されます

### コマンドラインから：
```bash
./gradlew build
```

## 設定ファイル

初回起動時に `plugins/VotingGacha/` フォルダに以下のファイルが自動生成されます：

- **config.yml** - ガチャアイテムの設定
- **messages.yml** - メッセージの設定

### config.yml - ガチャアイテム設定

```yaml
gacha-items:
  # 超レアアイテム（約0.88%）
  - material: NETHERITE_INGOT
    amount: 1
    name: "&4&lネザライトインゴット"
    lore:
      - "&d&l★★★ 超レア ★★★"
      - "&7投票ありがとうございます！"
    weight: 1
  
  # レアアイテム（約1.75% - 8.77%）
  - material: DIAMOND
    amount: 3
    name: "&b&lダイヤモンド"
    lore:
      - "&e&l★★ レア ★★"
      - "&7投票ありがとうございます！"
    weight: 5
  
  # コモンアイテム（約21.93%）
  - material: EXPERIENCE_BOTTLE
    amount: 32
    name: "&5経験値ボトル"
    lore:
      - "&7コモン"
      - "&7投票ありがとうございます！"
    weight: 25
```

アイテムはレアリティ別に整理されており、確率も明記されています。

### messages.yml - メッセージ設定

```yaml
vote:
  received: "&a投票ありがとうございます！ガチャを引いています..."
  result: "&e【ガチャ結果】&f{item} &7を獲得しました！"
  no-space: "&c所持アイテムに空きがありません！"

command:
  no-permission: "&cこのコマンドを実行する権限がありません。"
  reload-success: "&a設定ファイルをリロードしました。"

help:
  header: "&6===== VotingGacha ヘルプ ====="
  gacha: "&e/votinggacha gacha &7- ガチャを引く（テスト用）"
  reload: "&e/votinggacha reload &7- 設定をリロード"
```

メッセージは用途別にセクション分けされています。

#### 排出確率の計算方法

各アイテムの排出確率は以下の式で計算されます：

```
アイテムの排出確率 = (アイテムのweight / 全アイテムのweight合計) × 100%
```

例：デフォルト設定の場合
- ネザライトインゴット: 1/114 ≈ 0.88%（超レア）
- エメラルド: 10/114 ≈ 8.77%（コモン）
- 経験値ボトル: 25/114 ≈ 21.93%（よく出る）

## コマンド

| コマンド | 説明 | 権限 |
|---------|------|------|
| `/votinggacha help` | ヘルプを表示 | `votinggacha.use` |
| `/votinggacha gacha` | ガチャを引く（テスト用） | `votinggacha.gacha` |
| `/votinggacha reload` | 設定をリロード | `votinggacha.admin` |

## 権限

| 権限 | 説明 | デフォルト |
|------|------|-----------|
| `votinggacha.use` | 基本機能を使用 | true（全員） |
| `votinggacha.gacha` | ガチャを引く | true（全員） |
| `votinggacha.admin` | 管理者コマンドを使用 | op |

## 使い方

### プレイヤー向け

1. 投票サイトでサーバーに投票
2. 自動的にガチャが引かれ、アイテムが付与されます
3. インベントリに空きがない場合は警告メッセージが表示されます

### 管理者向け

#### ガチャアイテムの追加

`config.yml` の `gacha-items` に新しいアイテムを追加：

```yaml
gacha-items:
  - material: ELYTRA
    amount: 1
    name: "&dエリトラ"
    lore:
      - "&7超レア報酬！"
      - "&7大切に使ってね"
    weight: 1
```

#### 設定の反映

変更後は以下のコマンドで設定をリロード：
```
/votinggacha reload
```

## アイテムID一覧

Minecraft 1.19.4 で使用可能なアイテムIDは[こちら](https://minecraft.fandom.com/wiki/Java_Edition_data_values)を参照してください。

例：
- `DIAMOND` - ダイヤモンド
- `EMERALD` - エメラルド
- `NETHERITE_INGOT` - ネザライトインゴット
- `ENCHANTED_GOLDEN_APPLE` - エンチャントされた金のリンゴ
- `ELYTRA` - エリトラ
- `EXPERIENCE_BOTTLE` - 経験値ボトル

## トラブルシューティング

### ガチャが動作しない

1. Votifierプラグインがインストールされているか確認
2. サーバーログで `Votifierが検出されました` というメッセージを確認
3. 投票サイトの設定が正しいか確認

### アイテムが付与されない

1. プレイヤーのインベントリに空きがあるか確認
2. config.ymlの設定が正しいか確認
3. サーバーログでエラーメッセージを確認

### ビルドエラー

1. Java 17以降がインストールされているか確認
2. Gradle 8.5以降が使用されているか確認
3. `./gradlew clean build` で再ビルドを試す

## 開発

### プロジェクト構成

```
VotingGacha/
├── src/
│   └── main/
│       ├── java/
│       │   └── com/kubota6646/votinggacha/
│       │       ├── VotingGacha.java          # メインクラス
│       │       ├── GachaManager.java         # ガチャ管理
│       │       ├── GachaItem.java            # ガチャアイテム
│       │       ├── ConfigManager.java        # 設定管理
│       │       ├── VoteListener.java         # 投票リスナー
│       │       └── VotingGachaCommand.java   # コマンド処理
│       └── resources/
│           ├── plugin.yml                    # プラグイン設定
│           └── config.yml                    # ガチャ設定
├── build.gradle                              # Gradleビルド設定
└── README.md                                 # このファイル
```

### ビルド

```bash
./gradlew build
```

生成されたJARファイル：`build/libs/VotingGacha-0.0.1.jar`

### クリーン

```bash
./gradlew clean
```

## ライセンス

このプロジェクトは MIT License の下で公開されています。詳細は [LICENSE](LICENSE) ファイルを参照してください。

## サポート

問題が発生した場合は、GitHubのIssuesで報告してください。

## バージョン履歴

### v0.0.1 (初回リリース)
- 基本的な投票ガチャ機能
- 重み付けランダムシステム
- カスタマイズ可能な設定ファイル
- 日本語メッセージ対応
- Minecraft 1.19.4 対応