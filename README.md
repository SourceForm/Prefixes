# CustomPrefix Plugin

CustomPrefix is a Minecraft plugin that allows server administrators to assign custom prefixes to players. These prefixes are displayed in chat, the tab list (as long as no other plugins overwrite the tab list), and above the player's head.

## Features

- Set custom prefixes for players.
- Prefixes are stored in a configuration file.
- Prefixes appear in chat, the tab list, and above players' heads.
- Track and display the number of deaths for each player.

## Installation

1. Download the latest release of the CustomPrefix plugin.
2. Place the downloaded JAR file into your server's `plugins` directory.
3. Start or restart your Minecraft server.

## Configuration
Prefixes and player death counts are stored in the 'config.yml' file. You can modify these values directly in the file to customize prefixes or view and edit death counts for each player.
Here is an example of the configuration format:
```plaintext
prefixes:
  ccf3ff6f-61a4-4086-8508-e835cfb745cc: '&c&lGod'
players:
  ccf3ff6f-61a4-4086-8508-e835cfb745cc:
    deaths: 3
```

## Commands

### `/setprefix`

- **Description**: Sets a custom prefix for a specified player.
- **Usage**: `/setprefix <player> <prefix>`
- **Permission**: `customprefix.setprefix`

Examples:
```plaintext
/setprefix Steve &4[Admin]
```
