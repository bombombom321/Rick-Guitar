package com.example.myapplication.core.model;

public enum Type {

    ACOUSTIC, ELECTRIC;


    public static Type toEnum(String keyword) {
        keyword = keyword.toLowerCase();
        if (keyword.equals(ACOUSTIC.toString().toLowerCase())) return ACOUSTIC;
        else if (keyword.equals(ELECTRIC.toString().toLowerCase())) return ELECTRIC;
        else return null;
    }
    public String toString() {
        switch(this) {
            case ACOUSTIC: return "acoustic";
            case ELECTRIC: return "electric";

            default:       return "unspecified";
        }
    }
}
