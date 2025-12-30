# 設定例とカスタマイズガイド

このファイルでは、config.ymlとmessages.ymlをカスタマイズする方法を詳しく説明します。

## ファイル構成

VotingGachaは2つの設定ファイルを使用します：

- **config.yml** - ガチャアイテムの設定
- **messages.yml** - メッセージの設定

この分離により、設定がより見やすく管理しやすくなっています。

## config.yml - ガチャアイテム設定

### 基本的な構造

```yaml
gacha-items:
  # 各アイテムの設定
  - material: アイテムID
    amount: 個数
    name: 表示名
    lore:
      - 説明文1
      - 説明文2
    weight: 排出確率の重み
```

### レアリティ別の整理

config.ymlではアイテムがレアリティ別に整理されています：

- **超レア** (weight: 1-2) - 約0.88% - 1.75%
- **レア** (weight: 2-10) - 約1.75% - 8.77%
- **アンコモン** (weight: 15-20) - 約13.16% - 17.54%
- **コモン** (weight: 25+) - 約21.93%以上

## messages.yml - メッセージ設定

### 基本的な構造

```yaml
vote:
  # 投票関連メッセージ
  
command:
  # コマンド関連メッセージ
  
help:
  # ヘルプメッセージ
```

## メッセージのカスタマイズ

### 色コード

`&` の後に色コードを付けて色を変更できます：

| コード | 色 | 例 |
|--------|-----|-----|
| `&0` | 黒 | `&0黒い文字` |
| `&1` | ダークブルー | `&1濃い青` |
| `&2` | ダークグリーン | `&2濃い緑` |
| `&3` | ダークアクア | `&3濃い水色` |
| `&4` | ダークレッド | `&4濃い赤` |
| `&5` | ダークパープル | `&5濃い紫` |
| `&6` | ゴールド | `&6金色` |
| `&7` | グレー | `&7灰色` |
| `&8` | ダークグレー | `&8濃い灰色` |
| `&9` | ブルー | `&9青` |
| `&a` | グリーン | `&a緑` |
| `&b` | アクア | `&b水色` |
| `&c` | レッド | `&c赤` |
| `&d` | ライトパープル | `&dピンク` |
| `&e` | イエロー | `&e黄色` |
| `&f` | ホワイト | `&f白` |

### 装飾コード

| コード | 効果 |
|--------|------|
| `&l` | 太字 |
| `&m` | 取り消し線 |
| `&n` | 下線 |
| `&o` | 斜体 |
| `&r` | リセット |

### 例（messages.yml）

```yaml
vote:
  received: "&a&l投票ありがとうございます！&r&eガチャを引いています..."
  result: "&6&l【ガチャ結果】&r&f{item} &7を獲得しました！"

command:
  reload-success: "&a&l設定リロード完了！"
```

## アイテム設定の詳細（config.yml）

### 基本的なアイテム設定

```yaml
gacha-items:
  - material: DIAMOND          # 必須：アイテムの種類
    amount: 1                  # 必須：個数（1-64）
    name: "&bダイヤモンド"     # オプション：表示名
    lore:                      # オプション：説明文（複数行可能）
      - "&7レア報酬！"
      - "&7大切に使ってね"
    weight: 10                 # 必須：排出確率の重み
```

### レアリティ別の設定例

config.ymlではレアリティ別に整理されています。新しいアイテムを追加する際は、適切なセクションに追加してください。

#### 超レア（SSR）- weight: 1-2（約0.88% - 1.75%）

```yaml
  - material: NETHERITE_INGOT
    amount: 1
    name: "&4&lネザライトインゴット"
    lore:
      - "&d&l★★★ 超レア ★★★"
      - "&7投票ありがとうございます！"
    weight: 1
```

#### レア（SR）- weight: 2-10（約1.75% - 8.77%）

```yaml
  - material: DIAMOND
    amount: 5
    name: "&b&lダイヤモンド"
    lore:
      - "&e&l★★ レア ★★"
      - "&7投票ありがとうございます！"
    weight: 5
```

#### アンコモン（R）- weight: 15-20（約13.16% - 17.54%）

```yaml
  - material: EMERALD
    amount: 10
    name: "&a&lエメラルド"
    lore:
      - "&a&l★ アンコモン ★"
      - "&7投票ありがとうございます！"
    weight: 15
```

#### コモン（C）- weight: 25+（約21.93%以上）

```yaml
  - material: IRON_INGOT
    amount: 32
    name: "&f鉄インゴット"
    lore:
      - "&7コモン"
      - "&7投票ありがとうございます！"
    weight: 25
```

## 新しいファイル構成のメリット

### 1. 見やすさの向上
- config.ymlはガチャアイテムのみに集中
- messages.ymlはメッセージのみに集中
- 各ファイルが短く、編集しやすい

### 2. 整理された構造
- アイテムはレアリティ別に分類
- メッセージは用途別にセクション分け
- コメントで確率が明記されている

### 3. 管理の容易さ
- メッセージだけ変更したい時はmessages.ymlのみ編集
- アイテムだけ追加したい時はconfig.ymlのみ編集
- 設定の目的が一目で分かる

## カスタマイズ例集

### 例1: 食料系ガチャ

```yaml
gacha-items:
  - material: GOLDEN_APPLE
    amount: 3
    name: "&6金のリンゴ"
    weight: 10
    
  - material: ENCHANTED_GOLDEN_APPLE
    amount: 1
    name: "&6エンチャントされた金のリンゴ"
    weight: 2
    
  - material: COOKED_BEEF
    amount: 32
    name: "&cステーキ"
    weight: 30
    
  - material: GOLDEN_CARROT
    amount: 16
    name: "&6金のニンジン"
    weight: 20
```

### 例2: 装備品ガチャ

```yaml
gacha-items:
  - material: DIAMOND_SWORD
    amount: 1
    name: "&bダイヤモンドの剣"
    lore:
      - "&7強力な武器"
    weight: 5
    
  - material: DIAMOND_PICKAXE
    amount: 1
    name: "&bダイヤモンドのツルハシ"
    lore:
      - "&7採掘用ツール"
    weight: 5
    
  - material: IRON_CHESTPLATE
    amount: 1
    name: "&f鉄の胸当て"
    weight: 15
    
  - material: IRON_BOOTS
    amount: 1
    name: "&f鉄のブーツ"
    weight: 20
```

### 例3: ポーション・エンチャント本ガチャ

```yaml
gacha-items:
  - material: EXPERIENCE_BOTTLE
    amount: 64
    name: "&5経験値ボトル"
    lore:
      - "&7大量の経験値"
    weight: 15
    
  - material: ENCHANTED_BOOK
    amount: 1
    name: "&dエンチャント本"
    lore:
      - "&7ランダムなエンチャント"
    weight: 10
    
  - material: POTION
    amount: 3
    name: "&dポーション"
    lore:
      - "&7便利なポーション"
    weight: 20
```

### 例4: 建築材料ガチャ

```yaml
gacha-items:
  - material: OAK_PLANKS
    amount: 64
    name: "&e樫の木材"
    weight: 40
    
  - material: STONE_BRICKS
    amount: 64
    name: "&7石レンガ"
    weight: 35
    
  - material: GLASS
    amount: 32
    name: "&fガラス"
    weight: 25
    
  - material: GLOWSTONE
    amount: 16
    name: "&eグロウストーン"
    weight: 15
    
  - material: SEA_LANTERN
    amount: 8
    name: "&bシーランタン"
    weight: 5
```

## 排出確率の計算

### 計算式

```
アイテムの排出確率 (%) = (アイテムのweight / 全アイテムのweight合計) × 100
```

### 例

以下の設定の場合：

```yaml
gacha-items:
  - material: DIAMOND
    weight: 5
  - material: EMERALD
    weight: 10
  - material: IRON_INGOT
    weight: 35
```

合計weight = 5 + 10 + 35 = 50

- ダイヤモンド: (5 / 50) × 100 = 10%
- エメラルド: (10 / 50) × 100 = 20%
- 鉄インゴット: (35 / 50) × 100 = 70%

## よくある質問

### Q1: エンチャント付きアイテムを設定できますか？

A1: 現在のバージョンではエンチャントの設定には対応していません。将来のバージョンで追加予定です。

### Q2: アイテムの個数を64以上にできますか？

A2: Minecraftの制限により、1スタック（通常64個）が最大です。

### Q3: プレイヤー固有の確率を設定できますか？

A3: 現在は全プレイヤー共通の確率です。将来のバージョンで権限による確率変更機能を検討中です。

### Q4: 複数のアイテムを同時に付与できますか？

A4: 現在は1回の投票で1つのアイテムのみです。ただし、amountで個数を増やすことは可能です。

### Q5: 設定を変更したらサーバーを再起動する必要がありますか？

A5: いいえ、`/votinggacha reload` コマンドで設定を再読み込みできます。

## トラブルシューティング

### アイテムが正しく表示されない

- `material` の値が正しいか確認してください
- Minecraft 1.19.4で有効なアイテムIDを使用してください

### 確率がおかしい

- すべてのアイテムの `weight` を確認してください
- weightは正の整数である必要があります

### 色が表示されない

- 色コードは `&` で始まる必要があります（`§` ではありません）
- シングルクォート `'` またはダブルクォート `"` で囲んでください

## 参考リンク

- [Minecraft Wiki - アイテムID一覧](https://minecraft.fandom.com/wiki/Java_Edition_data_values)
- [Minecraft Wiki - 色コード](https://minecraft.fandom.com/wiki/Formatting_codes)
