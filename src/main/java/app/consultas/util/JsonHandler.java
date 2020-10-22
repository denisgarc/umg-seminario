/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package app.consultas.util;

import com.google.gson.GsonBuilder;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;

/**
 *
 * @author DOxlaj
 */
public class JsonHandler {
    public <T> JsonArray ToJsonArray(T listado){
        JsonArray jarray = new GsonBuilder()
                                .excludeFieldsWithoutExposeAnnotation()
                                .serializeNulls()
                                .create()
                                .toJsonTree(listado)
                                .getAsJsonArray();
        return jarray;
    }
    
    public <T> JsonObject ToJson(T item){
        JsonObject result = new GsonBuilder().excludeFieldsWithoutExposeAnnotation().create().toJsonTree(item).getAsJsonObject();
        return result;
    }
}
