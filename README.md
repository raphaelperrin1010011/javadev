# LangFlags - Paper Plugin 1.21.11

Plugin Minecraft Paper qui detecte automatiquement le pays du joueur via son IP et affiche des particules en forme de drapeau au-dessus de sa tete.

## Features

- **Geolocalisation IP** : Detection automatique du pays via ip-api.com a la connexion
- **Messages multilingues** : Titres de bienvenue dans 8 langues (FR, EN, ES, DE, PT, IT, JA, RU)
- **Particules drapeau** : Drapeau en particules colorees (Dust) qui suit le joueur et tourne avec lui
- **Commande `/olympe`** : Changer de langue, activer/desactiver les particules

## Commandes

| Commande | Description |
|----------|-------------|
| `/olympe` | Affiche le titre de bienvenue + active les particules |
| `/olympe <lang>` | Change la langue (fr, en, es, de, pt, it, ja, ru) |
| `/olympe on` | Active les particules |
| `/olympe off` | Desactive les particules |

## Drapeaux supportes

France, USA, UK, Allemagne, Espagne, Italie, Portugal, Bresil, Japon, Russie

## Build

```bash
mvn clean package
```

Le jar se trouve dans `target/LangFlags-1.0.0.jar`


## Demo

https://github.com/user-attachments/assets/ec6d3ccc-7c88-4d7b-a205-971d1fb2367a


## Tech Stack

- Java 21
- Paper API 1.21.11
- ip-api.com (geolocalisation gratuite)
- Maven
