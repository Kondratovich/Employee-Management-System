package com.company.springboot.dto;

import java.io.Serializable;

public class APOD implements Serializable {
    private String hdurl;

    public String getHdurl() {
        return hdurl;
    }

    public void setHdurl(String hdurl) {
        this.hdurl = hdurl;
    }
}
