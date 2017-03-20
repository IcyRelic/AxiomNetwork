package com.icyrelic.api.manager.types;

import com.icyrelic.api.language.Language;
import com.icyrelic.api.language.LanguageFile;
import com.icyrelic.api.manager.Manager;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

/**
 * @author IcyRelic
 */
public class LanguageManager extends Manager {

    public LanguageManager(){
        createLanguageFiles();
    }

    public void createLanguageFiles(){
        List<Language> values = Arrays.asList(Language.values());
        HashMap<String, List<Language>> fileLists = new HashMap<>();

        values.forEach(value -> {
            if(!fileLists.containsKey(value.getPluginName()))
                fileLists.put(value.getPluginName(), new ArrayList<>());
            fileLists.get(value.getPluginName()).add(value);
        });

        fileLists.entrySet().forEach(entry -> new LanguageFile(entry.getKey(), entry.getValue()));
    }
}