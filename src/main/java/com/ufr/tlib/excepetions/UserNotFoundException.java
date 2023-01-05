package com.ufr.tlib.excepetions;


public class UserNotFoundException extends Exception{

    public UserNotFoundException(){
        super("User not found");
    }
}

