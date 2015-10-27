package com.carstenscholz.robospicemoa;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.google.api.client.util.Key;

/**
 * Created by Carsten on 9/10/2015.
 */
public class MoaObject {
    @Key
    private String error;
    @Key
    private String method;

    @Key("param name")
    private String paramName;

    @Key("param value")
    private String paramValue;


    public String getError(){
        return this.error;
    }
    public void setError(String _error){
        this.error = _error;
    }
    public String getMethod(){
        return this.method;
    }
    public void setMethod(String _method){
        this.method = _method;
    }
    public String getParamName(){
        return this.paramName;
    }
    public void setParamName(String _paramName){
        this.paramName = _paramName;
    }
    public String getParamValue(){
        return this.paramValue;
    }
    public void setParamValue(String _paramValue){
        this.paramValue = _paramValue;
    }


}
