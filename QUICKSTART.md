# クイックスタートガイド

VotingGachaを素早く始めるための簡易ガイドです。

## 🚀 5分でスタート

### 1. ビルド (1分)

```bash
./gradlew build
```

### 2. インストール (1分)

```bash
# JARファイルをサーバーのpluginsフォルダにコピー
cp build/libs/VotingGacha-1.0.0.jar /path/to/server/plugins/
```

### 3. 起動 (1分)

サーバーを起動すると、自動的に設定ファイルが生成されます。

### 4. 設定 (2分)

`plugins/VotingGacha/config.yml` を編集：

```yaml
# 基本設定はそのまま使えます！
# 必要に応じてアイテムや確率を調整
```

### 5. リロード

```
/votinggacha reload
```

## 💡 よく使うコマンド

| コマンド | 用途 |
|---------|------|
| `/votinggacha gacha` | テスト用にガチャを引く |
| `/votinggacha reload` | 設定を再読み込み |
| `/votinggacha help` | ヘルプを表示 |

## ⚙️ 基本的な設定変更

### アイテムを追加する

`config.yml` に追加：

```yaml
gacha-items:
  - material: DIAMOND_SWORD    # アイテムの種類
    amount: 1                  # 個数
    name: "&bダイヤモンドの剣"  # 名前（&で色指定）
    lore:                      # 説明文
      - "&7強力な武器"
    weight: 5                  # 確率の重み（大きいほど出やすい）
```

### 確率を変更する

`weight` の値を変更：

- 超レア（約1%）: `weight: 1`
- レア（約5%）: `weight: 5`
- ノーマル（約20%）: `weight: 20`

### メッセージを変更する

```yaml
messages:
  vote-received: "&a投票ありがとう！"
  gacha-result: "&e【結果】{item} ゲット！"
```

## 🎮 テスト方法

### 1. プラグインが動作しているか確認

```
/plugins
```

VotingGachaが緑色で表示されればOK

### 2. ガチャをテスト

```
/votinggacha gacha
```

アイテムが付与されることを確認

### 3. 設定変更をテスト

1. `config.yml` を編集
2. `/votinggacha reload` を実行
3. `/votinggacha gacha` でテスト

## 📚 詳細なドキュメント

より詳しい情報は以下を参照：

- **README.md** - プロジェクト全体の説明
- **BUILD.md** - ビルド手順の詳細
- **CONFIG_GUIDE.md** - 設定のカスタマイズ方法
- **SUMMARY.md** - 技術仕様と概要

## ❓ トラブルシューティング

### プラグインが赤色で表示される

1. Java 17以降がインストールされているか確認
2. Spigot/Paper 1.19.4以降を使用しているか確認
3. サーバーログでエラーを確認

### ガチャが動作しない

1. Votifier または NuVotifier がインストールされているか確認
2. 投票サイトの設定を確認
3. テストコマンド `/votinggacha gacha` を試す

### アイテムが表示されない

1. `material` の値が正しいか確認
2. Minecraft 1.19.4で有効なアイテムIDか確認
3. サーバーログでエラーを確認

### 設定が反映されない

1. YAMLの文法が正しいか確認（インデントなど）
2. `/votinggacha reload` を実行したか確認
3. サーバーを再起動してみる

## 🎯 次のステップ

1. **カスタマイズ**: CONFIG_GUIDE.mdで詳細な設定方法を学ぶ
2. **拡張**: 独自のアイテムやメッセージを追加
3. **共有**: プレイヤーに投票を促進

## 💬 サポート

問題がある場合は、GitHubのIssuesで質問してください。

---

**楽しいMinecraftライフを！** 🎮✨
