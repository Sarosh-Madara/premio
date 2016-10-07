package com.saroshmadara.root.premiotravelsandtours.rest;

import com.cloudinary.Cloudinary;
import com.saroshmadara.root.premiotravelsandtours.utils.ConfigConstants;

import java.util.HashMap;
import java.util.Map;
import java.util.logging.Handler;

/**
 * Created by root on 8/18/16.
 */
public class Handlers {
    private static Handlers mInstance;
    private String mPackagesHandler;
    private Cloudinary mCloudinary;

    private Handlers(){
        mPackagesHandler = ConfigConstants.BASE_URL.concat("phpjson.php");
//        Map config = new HashMap();
//        config.put("cloud_name", "wear-n-care");
//        config.put("api_key", "938383425659143");
//        config.put("api_secret", "DErCuLLYu352hSJ3ve14XWtIWnQ");
//        mCloudinary = new Cloudinary(config);
    }

    public static Handlers getInstance(){
        if(mInstance == null){
            mInstance = new Handlers();
        }
        return mInstance;
    }

    public String getPackagesHandler() {
        return mPackagesHandler;
    }

    public Cloudinary getCloudinaryHandler(){
        return mCloudinary;
    }
}
