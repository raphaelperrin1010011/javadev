package fr.kingsfall.langflags;

import org.bukkit.Color;

import java.util.HashMap;
import java.util.Map;

public class FlagDesign {

    // Each flag is a 7x5 grid (7 columns, 5 rows) of RGB colors
    private static final Map<String, Color[][]> FLAGS = new HashMap<>();

    static {
        Color B = Color.fromRGB(0, 35, 149);    // Bleu France
        Color W = Color.WHITE;
        Color R = Color.fromRGB(237, 41, 57);    // Rouge France

        // France - tricolore vertical
        FLAGS.put("FR", new Color[][]{
                {B, B, B, W, W, R, R},
                {B, B, B, W, W, R, R},
                {B, B, B, W, W, R, R},
                {B, B, B, W, W, R, R},
                {B, B, B, W, W, R, R}
        });

        // USA
        Color NB = Color.fromRGB(60, 59, 110);   // Navy blue
        Color RU = Color.fromRGB(178, 34, 52);   // US Red
        FLAGS.put("US", new Color[][]{
                {NB, NB, NB, W, W, RU, RU},
                {NB, NB, NB, RU, RU, RU, RU},
                {W, W, W, W, W, W, W},
                {RU, RU, RU, RU, RU, RU, RU},
                {W, W, W, W, W, W, W}
        });

        // UK
        Color UKR = Color.fromRGB(200, 16, 46);
        Color UKB = Color.fromRGB(1, 33, 105);
        FLAGS.put("GB", new Color[][]{
                {UKB, UKB, UKB, UKR, UKB, UKB, UKB},
                {UKB, UKB, UKR, UKR, UKR, UKB, UKB},
                {UKR, UKR, UKR, W, UKR, UKR, UKR},
                {UKB, UKB, UKR, UKR, UKR, UKB, UKB},
                {UKB, UKB, UKB, UKR, UKB, UKB, UKB}
        });

        // Germany - horizontal tricolor
        Color BK = Color.BLACK;
        Color GR = Color.fromRGB(255, 206, 0);   // Gold
        FLAGS.put("DE", new Color[][]{
                {BK, BK, BK, BK, BK, BK, BK},
                {BK, BK, BK, BK, BK, BK, BK},
                {R, R, R, R, R, R, R},
                {GR, GR, GR, GR, GR, GR, GR},
                {GR, GR, GR, GR, GR, GR, GR}
        });

        // Spain
        Color SY = Color.fromRGB(255, 196, 0);
        Color SR = Color.fromRGB(198, 11, 30);
        FLAGS.put("ES", new Color[][]{
                {SR, SR, SR, SR, SR, SR, SR},
                {SY, SY, SY, SY, SY, SY, SY},
                {SY, SY, SY, SY, SY, SY, SY},
                {SY, SY, SY, SY, SY, SY, SY},
                {SR, SR, SR, SR, SR, SR, SR}
        });

        // Italy - vertical tricolor
        Color IG = Color.fromRGB(0, 146, 70);
        Color IR = Color.fromRGB(206, 43, 55);
        FLAGS.put("IT", new Color[][]{
                {IG, IG, IG, W, W, IR, IR},
                {IG, IG, IG, W, W, IR, IR},
                {IG, IG, IG, W, W, IR, IR},
                {IG, IG, IG, W, W, IR, IR},
                {IG, IG, IG, W, W, IR, IR}
        });

        // Portugal
        Color PG = Color.fromRGB(0, 102, 0);
        Color PR = Color.fromRGB(255, 0, 0);
        Color PY = Color.fromRGB(255, 255, 0);
        FLAGS.put("PT", new Color[][]{
                {PG, PG, PG, PR, PR, PR, PR},
                {PG, PG, PG, PR, PR, PR, PR},
                {PG, PG, PY, PY, PR, PR, PR},
                {PG, PG, PG, PR, PR, PR, PR},
                {PG, PG, PG, PR, PR, PR, PR}
        });

        // Brazil
        Color BG = Color.fromRGB(0, 155, 58);
        Color BY = Color.fromRGB(255, 223, 0);
        Color BB = Color.fromRGB(0, 39, 118);
        FLAGS.put("BR", new Color[][]{
                {BG, BG, BG, BG, BG, BG, BG},
                {BG, BG, BG, BY, BG, BG, BG},
                {BG, BG, BY, BB, BY, BG, BG},
                {BG, BG, BG, BY, BG, BG, BG},
                {BG, BG, BG, BG, BG, BG, BG}
        });

        // Japan
        Color JR = Color.fromRGB(188, 0, 45);
        FLAGS.put("JP", new Color[][]{
                {W, W, W, W, W, W, W},
                {W, W, W, JR, W, W, W},
                {W, W, JR, JR, JR, W, W},
                {W, W, W, JR, W, W, W},
                {W, W, W, W, W, W, W}
        });

        // Russia
        Color RUB = Color.fromRGB(0, 57, 166);
        Color RUR = Color.fromRGB(213, 43, 30);
        FLAGS.put("RU", new Color[][]{
                {W, W, W, W, W, W, W},
                {W, W, W, W, W, W, W},
                {RUB, RUB, RUB, RUB, RUB, RUB, RUB},
                {RUR, RUR, RUR, RUR, RUR, RUR, RUR},
                {RUR, RUR, RUR, RUR, RUR, RUR, RUR}
        });
    }

    // Country code -> lang code mapping
    private static final Map<String, String> COUNTRY_TO_LANG = Map.ofEntries(
            Map.entry("FR", "fr"),
            Map.entry("BE", "fr"),
            Map.entry("CH", "fr"),
            Map.entry("CA", "fr"),
            Map.entry("US", "en"),
            Map.entry("GB", "en"),
            Map.entry("AU", "en"),
            Map.entry("NZ", "en"),
            Map.entry("IE", "en"),
            Map.entry("ES", "es"),
            Map.entry("MX", "es"),
            Map.entry("AR", "es"),
            Map.entry("CO", "es"),
            Map.entry("CL", "es"),
            Map.entry("DE", "de"),
            Map.entry("AT", "de"),
            Map.entry("PT", "pt"),
            Map.entry("BR", "pt"),
            Map.entry("IT", "it"),
            Map.entry("JP", "ja"),
            Map.entry("RU", "ru")
    );

    public static Color[][] getDesign(String countryCode) {
        return FLAGS.getOrDefault(countryCode, getDefaultFlag());
    }

    public static String getLangForCountry(String countryCode) {
        return COUNTRY_TO_LANG.getOrDefault(countryCode, "en");
    }

    // Rainbow flag as default
    private static Color[][] getDefaultFlag() {
        Color R = Color.fromRGB(228, 3, 3);
        Color O = Color.fromRGB(255, 140, 0);
        Color Y = Color.fromRGB(255, 237, 0);
        Color G = Color.fromRGB(0, 128, 38);
        Color B = Color.fromRGB(0, 77, 255);
        return new Color[][]{
                {R, R, R, R, R, R, R},
                {O, O, O, O, O, O, O},
                {Y, Y, Y, Y, Y, Y, Y},
                {G, G, G, G, G, G, G},
                {B, B, B, B, B, B, B}
        };
    }
}
