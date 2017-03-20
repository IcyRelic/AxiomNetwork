package com.icyrelic.api.util;

import com.google.gson.Gson;
import com.icyrelic.api.exceptions.PlayerNotFoundException;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.UUID;

/**
 * @author IcyRelic
 */
public class MojangAPI {

    String id;
    String name;

    public MojangAPI(String id, String name){
        this.id = id;
        this.name = name;
    }

    public UUID getId() {
        return UUID.fromString(id.replaceAll("(\\w{8})(\\w{4})(\\w{4})(\\w{4})(\\w{12})", "$1-$2-$3-$4-$5"));
    }

    public String getName() {
        return name;
    }

    public static MojangAPI getInformation(String name) throws Exception {

        String url = "https://api.mojang.com/users/profiles/minecraft/" + name;

        URL obj = new URL(url);
        HttpURLConnection con = (HttpURLConnection) obj.openConnection();
        BufferedReader in = new BufferedReader(new InputStreamReader(con.getInputStream()));
        String inputLine;
        StringBuffer response = new StringBuffer();

        while ((inputLine = in.readLine()) != null) {
            response.append(inputLine);
        }
        in.close();

        if(response.toString().isEmpty()) throw new PlayerNotFoundException("Player doesnt exist");

        return new Gson().fromJson(response.toString(), MojangAPI.class);
    }
}
