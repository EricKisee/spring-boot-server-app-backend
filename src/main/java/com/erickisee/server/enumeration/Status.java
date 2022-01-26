package com.erickisee.server.enumeration;
public enum Status {

    SERVER_UP("SERVER_UP"),
    SERVER_DOWN("SERVER_DOWN");

    private final String status;

    Status (String status){
        this.status = status;
    }

    public String gatStatus (){
        return this.status;
    }
    
}
