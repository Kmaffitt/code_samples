package com.flysystems.catchit_stl.model;

import java.io.Serializable;

public class Route implements Serializable {
    /**
     * 
     */
    private static final long serialVersionUID = -4792062039266131701L;
    private String name;
    
    public Route( String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
