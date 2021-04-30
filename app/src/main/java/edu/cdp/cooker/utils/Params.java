package edu.cdp.cooker.utils;

import java.util.HashMap;
import java.util.Map;

public class Params {
    public static HashMap<String , String> login(String acount, String pass){
        Map<String,String> map = new HashMap<String, String>();
        map.put("user_name",acount);
        map.put("password",pass);
        return (HashMap<String, String>)map;
    }
    public static HashMap<String , String> shoucang(String acount){
        Map<String,String> map = new HashMap<String, String>();
        map.put("user_name",acount);
        return (HashMap<String, String>)map;
    }


}
