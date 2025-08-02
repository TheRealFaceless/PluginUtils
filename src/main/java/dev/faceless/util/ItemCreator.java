package dev.faceless.util;

import com.destroystokyo.paper.profile.PlayerProfile;
import com.google.common.collect.Multimap;
import dev.faceless.PluginUtils;
import net.kyori.adventure.text.Component;
import net.minecraft.core.component.DataComponentMap;
import net.minecraft.core.component.DataComponentType;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.NamespacedKey;
import org.bukkit.OfflinePlayer;
import org.bukkit.attribute.Attribute;
import org.bukkit.attribute.AttributeModifier;
import org.bukkit.craftbukkit.inventory.CraftItemStack;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.Player;
import org.bukkit.inventory.EquipmentSlotGroup;
import org.bukkit.inventory.ItemFlag;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.inventory.meta.SkullMeta;
import org.bukkit.persistence.PersistentDataType;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.profile.PlayerTextures;

import javax.annotation.Nullable;
import java.net.MalformedURLException;
import java.net.URI;
import java.net.URL;
import java.util.*;

@SuppressWarnings({"deprecation", "unused"})
public class ItemCreator {
    private ItemStack bukkitItem;
    private ItemMeta bukkitMeta;

    private ItemCreator(ItemStack bukkitItem){
        this.bukkitItem = bukkitItem.clone();
        ItemMeta meta = this.bukkitItem.getItemMeta();
        if (meta != null) {
            this.bukkitMeta = meta.clone();
            this.bukkitItem.setItemMeta(this.bukkitMeta);
        } else {
            this.bukkitMeta = null;
            PluginUtils.getPlugin().getSLF4JLogger().error("Meta is null for item: {}", bukkitItem);
        }
    }

    public static ItemStack create(Component name, Material material) {
        return get(material).setName(name).build();
    }
    public static ItemStack create(Material material) {
        return get(material).build();
    }
    public static ItemStack createNameless(Material material) {
        return get(material).setName(Component.empty()).build();
    }

    public static ItemCreator get(Material material) {
        return new ItemCreator(new ItemStack(material));
    }

    public static ItemCreator get(ItemStack item) {
        return new ItemCreator(item);
    }

    public static ItemStack getHead(Player player) {
        ItemStack playerHead = new ItemStack(Material.PLAYER_HEAD, 1);
        SkullMeta playerHeadMeta = (SkullMeta) playerHead.getItemMeta();
        playerHeadMeta.setOwner(player.getName());
        playerHead.setItemMeta(playerHeadMeta);
        return playerHead;
    }

    public static ItemStack getHead(OfflinePlayer player) {
        ItemStack playerHead = new ItemStack(Material.PLAYER_HEAD, 1);
        SkullMeta playerHeadMeta = (SkullMeta) playerHead.getItemMeta();
        playerHeadMeta.setOwner(player.getName());
        playerHead.setItemMeta(playerHeadMeta);
        return playerHead;
    }

    public static ItemStack getSkullFromUrl(URL url){
        UUID uuid = UUID.randomUUID();
        PlayerProfile profile = Bukkit.createProfile(uuid, "");
        PlayerTextures textures = profile.getTextures();

        textures.setSkin(url);
        profile.setTextures(textures);

        ItemStack head = new ItemStack(Material.PLAYER_HEAD);
        SkullMeta skullMeta = (SkullMeta) head.getItemMeta();

        skullMeta.setPlayerProfile(profile);
        head.setItemMeta(skullMeta);
        return head;
    }

    public static URL getURL(String textureUrl) {
        textureUrl = "http://textures.minecraft.net/texture/" + textureUrl;
        URL url;
        try {
            url = URI.create(textureUrl).toURL();
        } catch (MalformedURLException e) {
            throw new RuntimeException(e);
        }
        return url;
    }

    public ItemCreator setName(Component name) {
        bukkitMeta.displayName(name);
        return this;
    }
    public ItemCreator setName(String name, dev.faceless.util.Component.TextFormat format) {
        Component component = dev.faceless.util.Component.format(name, format);
        bukkitMeta.displayName(component);
        return this;
    }
    public ItemCreator setLore(List<Component> lore) {
        bukkitMeta.lore(lore);
        return this;
    }
    public ItemCreator setLore(int line, String text) {
        List<Component> lore = bukkitMeta.lore();
        if(lore == null) lore = new ArrayList<>();
        lore.set(line, dev.faceless.util.Component.formatMini(text));
        return this;
    }
    public ItemCreator setLore(int line, Component text) {
        List<Component> lore = bukkitMeta.lore();
        if (lore == null) lore = new ArrayList<>();
        while (lore.size() <= line) {
            lore.add(Component.empty());
        }
        lore.set(line, text);
        dev.faceless.util.Component.trimTrailingEmptyLore(lore);
        bukkitMeta.lore(lore);
        return this;
    }
    public ItemCreator addEnchantment(Enchantment enchantment, int level, boolean ignoreLevelRestriction) {
        bukkitMeta.addEnchant(enchantment, level, ignoreLevelRestriction);
        return this;
    }
    public ItemCreator removeEnchantment(Enchantment enchantment) {
        bukkitMeta.removeEnchant(enchantment);
        return this;
    }
    public ItemCreator addItemFlags(ItemFlag... flags) {
        bukkitMeta.addItemFlags(flags);
        return this;
    }
    public ItemCreator setUnbreakable(boolean unbreakable) {
        bukkitMeta.setUnbreakable(unbreakable);
        return this;
    }
    public ItemCreator setCustomModelData(int data) {
        bukkitMeta.setCustomModelData(data);
        return this;
    }
    public <T> ItemCreator setPDC(JavaPlugin plugin, String key, PersistentDataType<T, T> type, T value) {
        NamespacedKey namespacedKey = new NamespacedKey(plugin, key);
        bukkitMeta.getPersistentDataContainer().set(namespacedKey, type, value);
        return this;
    }
    public <T> ItemCreator setPDC(NamespacedKey key, PersistentDataType<T, T> type, T value) {
        bukkitMeta.getPersistentDataContainer().set(key, type, value);
        return this;
    }
    public <T> ItemCreator setPDC(NamespacedKey key) {
        bukkitMeta.getPersistentDataContainer().set(key, PersistentDataType.STRING, "");
        return this;
    }
    public <T> T getPDC(JavaPlugin plugin, String key, PersistentDataType<T, T> type) {
        NamespacedKey namespacedKey = new NamespacedKey(plugin, key);
        return bukkitMeta.getPersistentDataContainer().get(namespacedKey, type);
    }
    public <T> ItemCreator setDataComponent(DataComponentType<T> component, @Nullable T value) {
        CraftItemStack craftItemStack = (CraftItemStack) bukkitItem;
        net.minecraft.world.item.ItemStack nmsItem = CraftItemStack.asNMSCopy(craftItemStack);
        nmsItem.applyComponents(DataComponentMap
                .builder()
                .set(component, value)
                .build());
        bukkitItem = nmsItem.asBukkitCopy();
        bukkitMeta = bukkitItem.getItemMeta();
        return this;
    }
    public ItemCreator setItem(ItemStack newItem, boolean keepMeta) {
        ItemStack copy = newItem.clone(); // clone stack
        ItemMeta meta = copy.getItemMeta();

        if (keepMeta) {
            copy.setItemMeta(bukkitMeta);
        } else if (meta != null) {
            meta = meta.clone(); // clone meta defensively
            copy.setItemMeta(meta);
            bukkitMeta = meta;
        }

        bukkitItem = copy;
        return this;
    }
    public ItemCreator setItemModel(NamespacedKey key) {
        bukkitMeta.setItemModel(key);
        return this;
    }
    public ItemCreator setAttribute(Attribute attribute, NamespacedKey key, double amount, AttributeModifier.Operation operation) {
        AttributeModifier modifier = new AttributeModifier(key, amount, operation);
        bukkitMeta.addAttributeModifier(attribute, modifier);
        return this;
    }

    public ItemCreator setAttribute(Attribute attribute, NamespacedKey key, double amount,
                                    AttributeModifier.Operation operation, EquipmentSlotGroup slotGroup) {
        AttributeModifier modifier = new AttributeModifier(key, amount, operation, slotGroup);
        bukkitMeta.addAttributeModifier(attribute, modifier);
        return this;
    }
    public ItemCreator setAttribute(Attribute attribute, AttributeModifier modifier) {
        bukkitMeta.addAttributeModifier(attribute, modifier);
        return this;
    }

    public ItemCreator setAttributes(Map<Attribute, AttributeModifier> attributes) {
        attributes.forEach(bukkitMeta::addAttributeModifier);
        return this;
    }

    public ItemCreator clearAttributes() {
        Multimap<Attribute, AttributeModifier> modifiers = bukkitMeta.getAttributeModifiers();
        if (modifiers != null) {
            for (Attribute attribute : modifiers.keySet()) {
                bukkitMeta.removeAttributeModifier(attribute);
            }
        }
        return this;
    }

    public ItemCreator removeAttributeModifier(Attribute attribute, NamespacedKey key) {
        Multimap<Attribute, AttributeModifier> modifiers = bukkitMeta.getAttributeModifiers();
        if (modifiers != null && modifiers.containsKey(attribute)) {
            Collection<AttributeModifier> toRemove = modifiers.get(attribute)
                    .stream()
                    .filter(mod -> mod.getKey().equals(key))
                    .toList();

            for (AttributeModifier mod : toRemove) {
                bukkitMeta.removeAttributeModifier(attribute, mod);
            }
        }
        return this;
    }

    public ItemCreator removeAttributeModifier(Attribute attribute, AttributeModifier attributeModifier) {
        bukkitMeta.removeAttributeModifier(attribute, attributeModifier);
        return this;
    }
    public ItemStack build() {
        bukkitItem.setItemMeta(bukkitMeta);
        return bukkitItem;
    }
}