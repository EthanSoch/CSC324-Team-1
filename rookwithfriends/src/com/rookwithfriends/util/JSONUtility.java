package com.rookwithfriends.util;

import java.util.Map;

import com.google.gson.Gson;

public class JSONUtility {
	public static String convertToJson(Map response){
    	Gson gson = new Gson();
    	return gson.toJson(response);
    }
	
	//TODO add more to JSON types?
	public static Map<String,Object> parseJson(String message){
		Gson gson = new Gson();
        @SuppressWarnings("unchecked")
		Map<String,Object> response = gson.fromJson(message, Map.class);
        return response;
	}
}
