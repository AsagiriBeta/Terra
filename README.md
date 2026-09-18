<img align="left" width="64" height="64" src="https://raw.githubusercontent.com/wiki/PolyhedralDev/Terra/images/terra_logo.png" alt="Terra Logo">

# Terra（Bukkit 插件服）

这是 [PolyhedralDev/Terra](https://github.com/PolyhedralDev/Terra) 的 fork，只维护 **Bukkit 系插件服**：Paper、Purpur、Folia，以及兼容的 Spigot/Bukkit 核心。

Fabric / Forge / Quilt / Sponge / Allay / Minestom 等模组与非插件平台已从此仓库移除。

当前编译目标：**Paper 1.21.11**（Purpur / Folia 等同版本可直接用同一 JAR）。世界生成 NMS 注入依赖 Paper API，**请用 Paper 或其下游（Purpur、Folia），不要用纯 CraftBukkit**。

## 下载

推送到 `master` 后，GitHub Actions 会自动构建并 Release shaded 插件 JAR：

[Releases](https://github.com/AsagiriBeta/Terra/releases)

把 `Terra-bukkit-*-shaded.jar` 放进 `plugins/`。主世界生成器示例：

```yml
# bukkit.yml
worlds:
  world:
    generator: Terra:OVERWORLD
```

`plugin.yml` 已声明 `folia-supported: true`。

## 构建

```bash
./gradlew :platforms:bukkit:shadowJar
```

产物在 `platforms/bukkit/build/libs/`。

本地 Paper 测试服：

```bash
./gradlew :platforms:bukkit:runServer
```

## 许可证

与上游一致：API / 核心 addon 为 MIT，平台实现为 GPLv3。
