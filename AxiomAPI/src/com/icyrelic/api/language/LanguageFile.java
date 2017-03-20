package com.icyrelic.api.language;

import com.icyrelic.api.AxiomAPI;
import org.bukkit.configuration.file.YamlConfiguration;

import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.Set;

/**
 * @author IcyRelic
 */
public class LanguageFile {

    private File configFile;
    private File languageFolder;
    private YamlConfiguration config;

    public LanguageFile(String pluginName, List<Language> values){
        loadLanguage(pluginName, values);
    }

    public void loadLanguage(String pluginName, List<Language> values){
        languageFolder = new File(AxiomAPI.Instance.getDataFolder() + File.separator + "Language");
        languageFolder.mkdir();
        configFile = new File(AxiomAPI.Instance.getDataFolder() + File.separator + "Language" + File.separator + pluginName + ".yml");
        if(!configFile.exists()){
            try{
                configFile.createNewFile();
            } catch (IOException e){
                e.printStackTrace();
            }
        }
        config = YamlConfiguration.loadConfiguration(configFile);

        if(!values.isEmpty()){
            values.forEach(value -> {
                if(config.get(value.getName()) == null){
                    config.set(value.getName(), value.getMessage());
                }
            });
        }

        saveConfig();

        Set<String> keys = config.getConfigurationSection("").getKeys(false);
        keys.forEach(key -> {
            Language.valueOf(key).setMessage(config.getString(key));
        });
    }

    public void saveConfig(){
        try {
            config.save(configFile);
        } catch (IOException e){
            e.printStackTrace();
        }
    }
}
