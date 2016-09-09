package com.gwr.nextmovie.network;

import org.json.JSONObject;

/**
 * Created by willi on 09/07/2016.
 */
public interface Transaction {

    public String doBefore();
    public void doAfter(JSONObject jsonObject);
}
