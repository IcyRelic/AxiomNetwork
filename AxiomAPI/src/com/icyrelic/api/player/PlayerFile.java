package com.icyrelic.api.player;

import com.icyrelic.api.AxiomAPI;
import org.bukkit.configuration.file.YamlConfiguration;

import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.UUID;

/**
 * @author IcyRelic
 */
public class PlayerFile {

    private YamlConfiguration config;
    private File folder;
    private File file;

    public PlayerFile(UUID uid){
        folder = new File(AxiomAPI.Instance.getDataFolder() + File.separator + "Players");
        folder.mkdir();
        file = new File(AxiomAPI.Instance.getDataFolder() + File.separator + "Players" + File.separator + uid + ".yml");
        config = YamlConfiguration.loadConfiguration(file);
    }

    public File getPlayerFile(){
        return file;
    }

    public void createPlayerFile(){
        if(file.exists()) return;

            try {
            file.createNewFile();
        } catch (IOException ex){
            ex.printStackTrace();
        }

        saveConfig();
    }

    public String getString(String path){
        return config.getString(path);
    }

    public int getInt(String path){
        return config.getInt(path);
    }

    public double getDouble(String path){
        return config.getDouble(path);
    }

    public List<String> getStringList(String path){
        return config.getStringList(path);
    }

    public void set(String path, Object value){
        config.set(path, value);
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
