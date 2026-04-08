package fr.kingsfall.langflags;

import java.util.Map;
import java.util.UUID;
import java.util.concurrent.ConcurrentHashMap;

public class PlayerData {

    private final Map<UUID, PlayerInfo> data = new ConcurrentHashMap<>();

    public PlayerInfo get(UUID uuid) {
        return data.computeIfAbsent(uuid, k -> new PlayerInfo());
    }

    public void remove(UUID uuid) {
        data.remove(uuid);
    }

    public Map<UUID, PlayerInfo> getAll() {
        return data;
    }

    public static class PlayerInfo {
        private String countryCode = "FR";
        private String langCode = "fr";
        private boolean particlesEnabled = true;

        public String getCountryCode() {
            return countryCode;
        }

        public void setCountryCode(String countryCode) {
            this.countryCode = countryCode;
        }

        public String getLangCode() {
            return langCode;
        }

        public void setLangCode(String langCode) {
            this.langCode = langCode;
        }

        public boolean isParticlesEnabled() {
            return particlesEnabled;
        }

        public void setParticlesEnabled(boolean particlesEnabled) {
            this.particlesEnabled = particlesEnabled;
        }
    }
}
