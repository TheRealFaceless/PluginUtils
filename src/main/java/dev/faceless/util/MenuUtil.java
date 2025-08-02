package dev.faceless.util;

import org.bukkit.Material;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;

public class MenuUtil {
    public static void addBorders(Inventory inventory, Material mat) {
        ItemStack item = ItemCreator.createNameless(mat);
        int size = inventory.getSize();
        for (int i = 0; i < 9; i++) {
            inventory.setItem(i, item);
        }
        for (int i = size - 9; i < size; i++) {
            inventory.setItem(i, item);
        }
        for (int i = 1; i < size / 9 - 1; i++) {
            int leftIndex = i * 9;
            int rightIndex = (i + 1) * 9 - 1;
            inventory.setItem(leftIndex, item);
            inventory.setItem(rightIndex, item);
        }
    }

    public static void addBorders(Inventory inventory, ItemStack item) {
        int size = inventory.getSize();
        for (int i = 0; i < 9; i++) {
            inventory.setItem(i, item);
        }
        for (int i = size - 9; i < size; i++) {
            inventory.setItem(i, item);
        }
        for (int i = 1; i < size / 9 - 1; i++) {
            int leftIndex = i * 9;
            int rightIndex = (i + 1) * 9 - 1;
            inventory.setItem(leftIndex, item);
            inventory.setItem(rightIndex, item);
        }
    }

    public static void fill(Inventory inventory, Material material) {
        for (int i = 0; i < inventory.getSize(); i++) {
            if (inventory.getItem(i) == null) {
                inventory.setItem(i, ItemCreator.createNameless(material));
            }
        }
    }

    public static void fill(Inventory inventory, ItemStack item) {
        for (int i = 0; i < inventory.getSize(); i++) {
            if (inventory.getItem(i) == null) {
                inventory.setItem(i, item);
            }
        }
    }

    public static void addTopLayer(Inventory inventory, Material mat) {
        ItemStack item = ItemCreator.createNameless(mat);
        for (int i = 0; i < 9; i++) {
            inventory.setItem(i, item);
        }
    }

    public static void addTopLayer(Inventory inventory, ItemStack item) {
        for (int i = 0; i < 9; i++) {
            inventory.setItem(i, item);
        }
    }

    public static void addBottomLayer(Inventory inventory, Material mat) {
        ItemStack item = ItemCreator.createNameless(mat);
        int size = inventory.getSize();
        for (int i = size - 9; i < size; i++) {
            inventory.setItem(i, item);
        }
    }

    public static void addBottomLayer(Inventory inventory, ItemStack item) {
        int size = inventory.getSize();
        for (int i = size - 9; i < size; i++) {
            inventory.setItem(i, item);
        }
    }

    public static void addLeftLayer(Inventory inventory, Material mat) {
        ItemStack item = ItemCreator.createNameless(mat);
        int size = inventory.getSize();
        for (int i = 1; i < size / 9 - 1; i++) {
            int leftIndex = i * 9;
            inventory.setItem(leftIndex, item);
        }
    }

    public static void addLeftLayer(Inventory inventory, ItemStack item) {
        int size = inventory.getSize();
        for (int i = 1; i < size / 9 - 1; i++) {
            int leftIndex = i * 9;
            inventory.setItem(leftIndex, item);
        }
    }

    public static void addRightLayer(Inventory inventory, Material mat) {
        ItemStack item = ItemCreator.createNameless(mat);
        int size = inventory.getSize();
        for (int i = 1; i < size / 9 - 1; i++) {
            int rightIndex = (i + 1) * 9 - 1;
            inventory.setItem(rightIndex, item);
        }
    }

    public static void addRightLayer(Inventory inventory, ItemStack item) {
        int size = inventory.getSize();
        for (int i = 1; i < size / 9 - 1; i++) {
            int rightIndex = (i + 1) * 9 - 1;
            inventory.setItem(rightIndex, item);
        }
    }

    public static void addBorders(Inventory inventory, Material mat, int rowOffset) {
        ItemStack item = ItemCreator.createNameless(mat);
        addBorders(inventory, item, rowOffset);
    }

    public static void addBorders(Inventory inventory, ItemStack item, int rowOffset) {
        int size = inventory.getSize();
        int rows = size / 9;
        int bottomRow = rows - 1 - rowOffset;

        // Top border
        for (int i = rowOffset * 9; i < rowOffset * 9 + 9; i++) {
            inventory.setItem(i, item);
        }
        // Bottom border
        for (int i = bottomRow * 9; i < bottomRow * 9 + 9; i++) {
            inventory.setItem(i, item);
        }
        // Left & right borders
        for (int r = rowOffset + 1; r < bottomRow; r++) {
            inventory.setItem(r * 9, item);
            inventory.setItem(r * 9 + 8, item);
        }
    }

    public static void addTopLayer(Inventory inventory, Material mat, int rowOffset) {
        ItemStack item = ItemCreator.createNameless(mat);
        addTopLayer(inventory, item, rowOffset);
    }

    public static void addTopLayer(Inventory inventory, ItemStack item, int rowOffset) {
        int start = rowOffset * 9;
        for (int i = start; i < start + 9; i++) {
            inventory.setItem(i, item);
        }
    }

    public static void addBottomLayer(Inventory inventory, Material mat, int rowOffset) {
        ItemStack item = ItemCreator.createNameless(mat);
        addBottomLayer(inventory, item, rowOffset);
    }

    public static void addBottomLayer(Inventory inventory, ItemStack item, int rowOffset) {
        int rows = inventory.getSize() / 9;
        int row = rows - 1 - rowOffset;
        int start = row * 9;
        for (int i = start; i < start + 9; i++) {
            inventory.setItem(i, item);
        }
    }

    public static void addLeftLayer(Inventory inventory, Material mat, int rowOffset) {
        ItemStack item = ItemCreator.createNameless(mat);
        addLeftLayer(inventory, item, rowOffset);
    }

    public static void addLeftLayer(Inventory inventory, ItemStack item, int rowOffset) {
        int rows = inventory.getSize() / 9;
        for (int r = rowOffset; r < rows - rowOffset; r++) {
            inventory.setItem(r * 9, item);
        }
    }

    public static void addRightLayer(Inventory inventory, Material mat, int rowOffset) {
        ItemStack item = ItemCreator.createNameless(mat);
        addRightLayer(inventory, item, rowOffset);
    }

    public static void addRightLayer(Inventory inventory, ItemStack item, int rowOffset) {
        int rows = inventory.getSize() / 9;
        for (int r = rowOffset; r < rows - rowOffset; r++) {
            inventory.setItem(r * 9 + 8, item);
        }
    }
}