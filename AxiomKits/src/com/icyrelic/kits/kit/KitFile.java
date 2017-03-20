package com.icyrelic.kits.kit;

import com.icyrelic.api.util.Format;
import com.icyrelic.kits.AxiomKits;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.inventory.ItemStack;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * @author IcyRelic
 */
public class KitFile {

    private YamlConfiguration config;
    private File folder;
    private File file;
    private AxiomKit kit;

    public KitFile(AxiomKit kit){
        this.kit = kit;
        folder = new File(AxiomKits.Instance.getDataFolder() + File.separator + "Kits");
        folder.mkdir();
        file = new File(AxiomKits.Instance.getDataFolder() + File.separator + "Kits" + File.separator + kit.getName() + ".yml");
        config = YamlConfiguration.loadConfiguration(file);
    }

    public void createKitFile(){
        if(file.exists()) return;

        try {
            file.createNewFile();
        } catch (IOException ex){
            ex.printStackTrace();
        }

        saveKitFile();
    }

    public void saveKitFile(){
        config.set("Name", kit.getName());
        config.set("Cooldown", kit.getCooldown());

        List<String> items = new ArrayList<>();

        for(ItemStack item : kit.getItems()){
            items.add(Format.itemToString(item));
        }

        saveConfig();
    }

    private void saveConfig(){
        try {
            config.save(file);
        } catch (IOException e){
            e.printStackTrace();
        }
    }

}
