# Hunllef Helper Plugin

This RuneLite plugin enhances the Gauntlet experience by visually indicating Hunllef’s attack style and alerting the player when the correct protection prayer is not active.

## 🎯 Features

- 🟦 Blue outline when Hunllef uses MAGIC
- 🟩 Green outline when Hunllef uses RANGED
- 🟥 Red outline when Hunllef uses MELEE
- 🔴 Red screen flash if the player is not protected correctly
- 🧠 Automatically detects both **Regular Hunllef** and **Corrupted Hunllef**

## ⚙️ Installation

This plugin is designed to be integrated into a local RuneLite build.

1. Clone this repository into `runelite-client/src/main/java/net/runelite/client/plugins/`
2. Add the `plugin.properties` file to `src/main/resources/net/runelite/client/plugins/hunllefhelper/`
3. Run `mvn clean install -DskipTests`
4. Launch RuneLite from IntelliJ and enable the plugin

## 📌 Status

✅ Fully functional  
🧪 Pending review for possible official inclusion

## 👤 Author

Carlos – Valentín Valiente, Estado Sucre, Venezuela 🇻🇪

---

This plugin complies with RuneLite and Jagex guidelines: it does not automate gameplay, does not provide unfair advantages, and simply improves visibility and reaction time during the Gauntlet boss fight.