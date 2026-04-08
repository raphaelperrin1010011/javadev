package fr.kingsfall.langflags;

import java.util.Map;

public enum Messages {

    WELCOME(Map.of(
            "fr", "Bienvenue sur le serveur !",
            "en", "Welcome to the server!",
            "es", "Bienvenido al servidor!",
            "de", "Willkommen auf dem Server!",
            "pt", "Bem-vindo ao servidor!",
            "it", "Benvenuto nel server!",
            "ja", "サーバーへようこそ！",
            "ru", "Добро пожаловать на сервер!"
    )),

    WELCOME_SUBTITLE(Map.of(
            "fr", "Votre langue a ete detectee automatiquement",
            "en", "Your language was detected automatically",
            "es", "Tu idioma fue detectado automaticamente",
            "de", "Deine Sprache wurde automatisch erkannt",
            "pt", "Seu idioma foi detectado automaticamente",
            "it", "La tua lingua e stata rilevata automaticamente",
            "ja", "言語は自動的に検出されました",
            "ru", "Ваш язык был определён автоматически"
    )),

    LANG_CHANGED(Map.of(
            "fr", "Langue changee en Francais",
            "en", "Language changed to English",
            "es", "Idioma cambiado a Espanol",
            "de", "Sprache auf Deutsch geaendert",
            "pt", "Idioma alterado para Portugues",
            "it", "Lingua cambiata in Italiano",
            "ja", "言語が日本語に変更されました",
            "ru", "Язык изменён на Русский"
    )),

    LANG_CURRENT(Map.of(
            "fr", "Langue actuelle : Francais",
            "en", "Current language: English",
            "es", "Idioma actual: Espanol",
            "de", "Aktuelle Sprache: Deutsch",
            "pt", "Idioma atual: Portugues",
            "it", "Lingua attuale: Italiano",
            "ja", "現在の言語：日本語",
            "ru", "Текущий язык: Русский"
    )),

    PARTICLES_ON(Map.of(
            "fr", "Particules de drapeau activees !",
            "en", "Flag particles enabled!",
            "es", "Particulas de bandera activadas!",
            "de", "Flaggenpartikel aktiviert!",
            "pt", "Particulas de bandeira ativadas!",
            "it", "Particelle bandiera attivate!",
            "ja", "国旗パーティクルが有効になりました！",
            "ru", "Частицы флага включены!"
    )),

    PARTICLES_OFF(Map.of(
            "fr", "Particules de drapeau desactivees.",
            "en", "Flag particles disabled.",
            "es", "Particulas de bandera desactivadas.",
            "de", "Flaggenpartikel deaktiviert.",
            "pt", "Particulas de bandeira desativadas.",
            "it", "Particelle bandiera disattivate.",
            "ja", "国旗パーティクルが無効になりました。",
            "ru", "Частицы флага выключены."
    )),

    UNKNOWN_LANG(Map.of(
            "fr", "Langue inconnue ! Disponibles : fr, en, es, de, pt, it, ja, ru",
            "en", "Unknown language! Available: fr, en, es, de, pt, it, ja, ru",
            "es", "Idioma desconocido! Disponibles: fr, en, es, de, pt, it, ja, ru",
            "de", "Unbekannte Sprache! Verfuegbar: fr, en, es, de, pt, it, ja, ru",
            "pt", "Idioma desconhecido! Disponiveis: fr, en, es, de, pt, it, ja, ru",
            "it", "Lingua sconosciuta! Disponibili: fr, en, es, de, pt, it, ja, ru",
            "ja", "不明な言語！利用可能: fr, en, es, de, pt, it, ja, ru",
            "ru", "Неизвестный язык! Доступные: fr, en, es, de, pt, it, ja, ru"
    ));

    private final Map<String, String> translations;

    Messages(Map<String, String> translations) {
        this.translations = translations;
    }

    public String get(String langCode) {
        return translations.getOrDefault(langCode, translations.get("en"));
    }
}
