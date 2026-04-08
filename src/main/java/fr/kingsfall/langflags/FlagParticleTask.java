package fr.kingsfall.langflags;

import org.bukkit.Bukkit;
import org.bukkit.Color;
import org.bukkit.Location;
import org.bukkit.Particle;
import org.bukkit.entity.Player;
import org.bukkit.scheduler.BukkitRunnable;

import java.util.UUID;

public class FlagParticleTask extends BukkitRunnable {

    private final PlayerData playerData;

    // Flag grid dimensions
    private static final int COLS = 7;
    private static final int ROWS = 5;

    // Spacing between particles (in blocks)
    private static final double SPACING = 0.15;

    // Height offset above the player's head
    private static final double HEIGHT_OFFSET = 2.5;

    // Particle size
    private static final float PARTICLE_SIZE = 0.6f;

    public FlagParticleTask(PlayerData playerData) {
        this.playerData = playerData;
    }

    @Override
    public void run() {
        for (var entry : playerData.getAll().entrySet()) {
            UUID uuid = entry.getKey();
            PlayerData.PlayerInfo info = entry.getValue();

            if (!info.isParticlesEnabled()) continue;

            Player player = Bukkit.getPlayer(uuid);
            if (player == null || !player.isOnline()) continue;

            renderFlag(player, info.getCountryCode());
        }
    }

    private void renderFlag(Player player, String countryCode) {
        Color[][] design = FlagDesign.getDesign(countryCode);
        Location base = player.getLocation();

        // Center the flag above the player's head
        double startX = -(COLS - 1) * SPACING / 2.0;
        double startY = HEIGHT_OFFSET;

        // Get the player's yaw to rotate the flag so it always faces the viewer
        float yaw = base.getYaw();
        double radians = Math.toRadians(yaw);
        double cos = Math.cos(radians);
        double sin = Math.sin(radians);

        for (int row = 0; row < ROWS && row < design.length; row++) {
            for (int col = 0; col < COLS && col < design[row].length; col++) {
                Color color = design[row][col];

                // Local offset (flag is flat facing the player's look direction)
                double localX = startX + col * SPACING;
                double localY = startY - row * SPACING;

                // Rotate around Y axis based on player yaw (perpendicular to look direction)
                double worldX = base.getX() + localX * cos;
                double worldY = base.getY() + localY;
                double worldZ = base.getZ() + localX * sin;

                Location particleLoc = new Location(player.getWorld(), worldX, worldY, worldZ);

                Particle.DustOptions dust = new Particle.DustOptions(color, PARTICLE_SIZE);
                player.getWorld().spawnParticle(
                        Particle.DUST,
                        particleLoc,
                        1,        // count
                        0, 0, 0,  // offset
                        0,        // extra (speed)
                        dust
                );
            }
        }
    }
}
