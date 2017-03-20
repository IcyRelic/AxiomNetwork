package com.icyrelic.messages.events;

import com.icyrelic.api.AxiomAPI;
import com.icyrelic.api.event.AxiomEvent;
import com.icyrelic.api.language.Language;
import com.icyrelic.api.util.Format;
import com.icyrelic.messages.AxiomMessages;
import org.apache.commons.lang.WordUtils;
import org.bukkit.Material;
import org.bukkit.entity.*;
import org.bukkit.event.EventHandler;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.event.entity.EntityDamageEvent;
import org.bukkit.event.entity.PlayerDeathEvent;
import org.bukkit.inventory.ItemStack;

public class PlayerDeath extends AxiomEvent {
	
	public PlayerDeath() {
		super(AxiomMessages.Instance);
	}

	@EventHandler
	public void death(PlayerDeathEvent event){
		event.setDeathMessage("");
		Player p = event.getEntity();
		boolean doDeathMessage = AxiomMessages.Instance.getConfig().getBoolean("DeathMessage");
		EntityDamageEvent damageEvent = p.getLastDamageCause();

		if(doDeathMessage)
			AxiomAPI.Instance.getServer().broadcastMessage(getDeathMessage(p, damageEvent));
	}
/*


=== Entities ===
ELDER_GUARDIAN
WITHER_SKELETON
STRAY
SPLASH_POTION
WITHER_SKULL
PRIMED_TNT
HUSK
ZOMBIE_VILLAGER
SKELETON_HORSE
ZOMBIE_HORSE
DONKEY
MULE
EVOKER_FANGS
EVOKER
VEX
VINDICATOR
CREEPER
SKELETON
SPIDER
GIANT
ZOMBIE
SLIME
GHAST
PIG_ZOMBIE
ENDERMAN
CAVE_SPIDER
SILVERFISH
BLAZE
MAGMA_CUBE
ENDER_DRAGON
WITHER
BAT
WITCH
ENDERMITE
GUARDIAN
SHULKER
PIG
SHEEP
COW
CHICKEN
SQUID
WOLF
MUSHROOM_COW
SNOWMAN
OCELOT
IRON_GOLEM
HORSE
RABBIT
POLAR_BEAR
LLAMA
VILLAGER
ENDER_CRYSTAL
LIGHTNING
PLAYER

==== Projectiles ====
ARROW
SNOWBALL
FIREBALL
SMALL_FIREBALL
SPECTRAL_ARROW
SHULKER_BULLET
DRAGON_FIREBALL
LLAMA_SPIT
TIPPED_ARROW

 */
	private String getDeathMessage(Player player, EntityDamageEvent damageEvent) {
		Language lang = Language.DEATHMSG_UNKNOWN;
		String killer = "An Unknown Killer";
		String weapon = "An Unknown Weapon";

		if(damageEvent instanceof EntityDamageByEntityEvent) {
			//Mob or player
			Entity damager = ((EntityDamageByEntityEvent) damageEvent).getDamager();

			killer = damager.getName();

			if(damager instanceof Projectile) {
				Projectile arrow = (Projectile) damager;

				weapon = damager.getType().name();

				if (arrow.getShooter() instanceof Entity) {
					Entity entity = (Entity) arrow.getShooter();
					killer = entity.getName();

					if(entity instanceof Player) lang = Language.DEATHMSG_PLAYER;
					if(entity instanceof Skeleton) lang = Language.DEATHMSG_SKELETON;
					if(entity instanceof Ghast) lang = Language.DEATHMSG_GHAST;
					if(entity instanceof Wither) lang = Language.DEATHMSG_WITHER;
					if(entity instanceof EnderDragon) lang = Language.DEATHMSG_ENDER_DRAGON;
					if(entity instanceof Shulker) lang = Language.DEATHMSG_SHULKER;
					if(entity instanceof Llama) lang = Language.DEATHMSG_LLAMA;
					if(entity instanceof Blaze) lang = Language.DEATHMSG_BLAZE;
					if(entity instanceof Witch) lang = Language.DEATHMSG_WITCH;
				}

				if (damager instanceof Arrow) weapon = "Arrow";
				if (damager instanceof SpectralArrow) weapon = "SpectralArrow";
				if (damager instanceof TippedArrow) weapon = "TippedArrow";
				if (damager instanceof Fireball) weapon = "Fireball";
				if (damager instanceof Snowball) weapon = "Snowball";
				if (damager instanceof ShulkerBullet) weapon = "ShulkerBullet";
				if (damager instanceof LlamaSpit) weapon = "LlamaSpit";
				if (damager instanceof SplashPotion) weapon = "SplashPotion";
			}
			else if(damager instanceof Player) {
				lang = Language.DEATHMSG_PLAYER;
				ItemStack inHand = ((Player) damager).getInventory().getItemInMainHand();

				if(inHand.getType() == Material.AIR) weapon = "Fist";
				else {
					weapon = WordUtils.capitalize(inHand.getType().name().replace("_", " ").toLowerCase());
					if(inHand.getItemMeta().hasDisplayName()) weapon = inHand.getItemMeta().getDisplayName();
				}


			}

			//Hostile Mobs
			else if(damager instanceof Blaze) lang = Language.DEATHMSG_BLAZE;
			else if(damager instanceof Creeper) lang = Language.DEATHMSG_CREEPER;
			else if(damager instanceof ElderGuardian) lang = Language.DEATHMSG_ELDER_GUARDIAN;
			else if(damager instanceof Endermite) lang = Language.DEATHMSG_ENDERMITE;
			else if(damager instanceof Evoker) lang = Language.DEATHMSG_EVOKER;
			else if(damager instanceof Guardian) lang = Language.DEATHMSG_GUARDIAN;
			else if(damager instanceof Husk) lang = Language.DEATHMSG_HUSK;
			else if(damager instanceof MagmaCube) lang = Language.DEATHMSG_MAGMA_CUBE;
			else if(damager instanceof Silverfish) lang = Language.DEATHMSG_SILVERFISH;
			else if(damager instanceof Slime) lang = Language.DEATHMSG_SLIME;
			else if(damager instanceof Stray) lang = Language.DEATHMSG_STRAY;
			else if(damager instanceof Vex) lang = Language.DEATHMSG_VEX;
			else if(damager instanceof Vindicator) lang = Language.DEATHMSG_VINDICATOR;
			else if(damager instanceof Witch) lang = Language.DEATHMSG_WITCH;
			else if(damager instanceof WitherSkeleton) lang = Language.DEATHMSG_WITHER_SKELETON;
			else if(damager instanceof ZombieVillager) lang = Language.DEATHMSG_ZOMBIE_VILLAGER;

			//Passive Mobs
			else if(damager instanceof Bat) lang = Language.DEATHMSG_BAT;
			else if(damager instanceof Chicken) lang = Language.DEATHMSG_CHICKEN;
			else if(damager instanceof MushroomCow) lang = Language.DEATHMSG_MOOSHROOM;
			else if(damager instanceof Cow) lang = Language.DEATHMSG_COW;
			else if(damager instanceof Pig) lang = Language.DEATHMSG_PIG;
			else if(damager instanceof Rabbit) lang = Language.DEATHMSG_RABBIT;
			else if(damager instanceof Sheep) lang = Language.DEATHMSG_SHEEP;
			else if(damager instanceof SkeletonHorse) lang = Language.DEATHMSG_SKELETON_HORSE;
			else if(damager instanceof Squid) lang = Language.DEATHMSG_SQUID;
			else if(damager instanceof Villager) lang = Language.DEATHMSG_VILLAGER;

			//Neutral Mobs
			else if(damager instanceof CaveSpider) lang = Language.DEATHMSG_CAVE_SPIDER;
			else if(damager instanceof Enderman) lang = Language.DEATHMSG_ENDERMAN;
			else if(damager instanceof PolarBear) lang = Language.DEATHMSG_POLAR_BEAR;
			else if(damager instanceof Spider) lang = Language.DEATHMSG_SPIDER;
			else if(damager instanceof PigZombie) lang = Language.DEATHMSG_PIGMAN;

			//Tamable Mobs
			else if(damager instanceof Donkey) lang = Language.DEATHMSG_DONKEY;
			else if(damager instanceof Horse) lang = Language.DEATHMSG_HORSE;
			else if(damager instanceof Llama) lang = Language.DEATHMSG_LLAMA;
			else if(damager instanceof Mule) lang = Language.DEATHMSG_MULE;
			else if(damager instanceof Ocelot) lang = Language.DEATHMSG_OCELOT;
			else if(damager instanceof Wolf) lang = Language.DEATHMSG_WOLF;

			//Utility Mobs
			else if(damager instanceof IronGolem) lang = Language.DEATHMSG_IRON_GOLEM;
			else if(damager instanceof Snowman) lang = Language.DEATHMSG_SNOW_GOLEM;

			//Boss Mobs
			else if(damager instanceof EnderDragon) lang = Language.DEATHMSG_ENDER_DRAGON;
			else if(damager instanceof Wither) lang = Language.DEATHMSG_WITHER;

			//Unused Mobs
			else if(damager instanceof Giant) lang = Language.DEATHMSG_GIANT;
			else if(damager instanceof ZombieHorse) lang = Language.DEATHMSG_ZOMBIE_HORSE;

			else if(damager instanceof Zombie) lang = Language.DEATHMSG_ZOMBIE;

			else if(damager instanceof Firework) {
				lang = Language.DEATHMSG_FIREWORK;
				killer = "Firework";
			}
		} else {
			//drowning ect
			if(damageEvent.getCause() == EntityDamageEvent.DamageCause.DROWNING) lang = Language.DEATHMSG_DROWNING;
			else if(damageEvent.getCause() == EntityDamageEvent.DamageCause.FALL) lang = Language.DEATHMSG_FALLING;
			else if(damageEvent.getCause() == EntityDamageEvent.DamageCause.FIRE) lang = Language.DEATHMSG_FIRE;
			else if(damageEvent.getCause() == EntityDamageEvent.DamageCause.SUFFOCATION) lang = Language.DEATHMSG_SUFFOCATION;
			else if(damageEvent.getCause() == EntityDamageEvent.DamageCause.VOID) lang = Language.DEATHMSG_VOID;
			else if(damageEvent.getCause() == EntityDamageEvent.DamageCause.STARVATION) lang = Language.DEATHMSG_STARVATION;
			else if(damageEvent.getCause() == EntityDamageEvent.DamageCause.SUICIDE) lang = Language.DEATHMSG_SUICIDE;
			else if(damageEvent.getCause() == EntityDamageEvent.DamageCause.MAGIC) lang = Language.DEATHMSG_MAGIC;
			else if(damageEvent.getCause() == EntityDamageEvent.DamageCause.POISON) lang = Language.DEATHMSG_POISON;
			else if(damageEvent.getCause() == EntityDamageEvent.DamageCause.LIGHTNING) lang = Language.DEATHMSG_LIGHTENING;
			else if(damageEvent.getCause() == EntityDamageEvent.DamageCause.FIRE_TICK) lang = Language.DEATHMSG_FIRE_TICK;
			else if(damageEvent.getCause() == EntityDamageEvent.DamageCause.CONTACT) lang = Language.DEATHMSG_CONTACT;
			else if(damageEvent.getCause() == EntityDamageEvent.DamageCause.BLOCK_EXPLOSION) lang = Language.DEATHMSG_BLOCK_EXPLOSION;
			else if(damageEvent.getCause() == EntityDamageEvent.DamageCause.LAVA) lang = Language.DEATHMSG_LAVA;

		}

		//return message;
		return Format.replace(lang.getMessage(), new String[] {"%player%", "%killer%", "%weapon%"}, new String[] {player.getName(), killer, weapon});

	}
}