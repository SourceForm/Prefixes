# CustomPrefix Plugin

CustomPrefix is a Minecraft plugin that allows server administrators to assign custom prefixes to players. These prefixes are displayed in chat, the tab list (as long as no other plugins overwrite the tab list), and above the player's head.

## Features

- Set custom prefixes for players.
- Prefixes are stored in a configuration file.
- Prefixes appear in chat, the tab list, and above players' heads.

## Installation

1. Download the latest release of the CustomPrefix plugin.
2. Place the downloaded JAR file into your server's `plugins` directory.
3. Start or restart your Minecraft server.

## Commands

### `/setprefix`

- **Description**: Sets a custom prefix for a specified player.
- **Usage**: `/setprefix <player> <prefix>`
- **Permission**: `customprefix.setprefix`

Example:
```plaintext
/setprefix Steve &4[Admin]
