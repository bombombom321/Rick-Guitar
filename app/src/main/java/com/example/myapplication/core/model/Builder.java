package com.example.myapplication.core.model;

public enum Builder {

    FENDER, MARTIN, GIBSON, COLLINGS, OLSON, RYAN, PRS, ANY;

    public static Builder toEnum(String keyword) {
        keyword = keyword.toLowerCase();
        if (keyword.equals(FENDER.toString().toLowerCase())) return FENDER;
        else if (keyword.equals(MARTIN.toString().toLowerCase())) return MARTIN;
        else if (keyword.equals(GIBSON.toString().toLowerCase())) return GIBSON;
        else if (keyword.equals(COLLINGS.toString().toLowerCase())) return COLLINGS;
        else if (keyword.equals(OLSON.toString().toLowerCase())) return OLSON;
        else if (keyword.equals(RYAN.toString().toLowerCase())) return RYAN;
        else if (keyword.equals(PRS.toString().toLowerCase())) return PRS;
        else if (keyword.equals(ANY.toString().toLowerCase())) return ANY;
        else return null;
    }

    public String toString() {
        switch (this) {
            case FENDER:
                return "Fender";
            case MARTIN:
                return "Martin";
            case GIBSON:
                return "Gibson";
            case COLLINGS:
                return "Collings";
            case OLSON:
                return "Olson";
            case RYAN:
                return "Ryan";
            case PRS:
                return "PRS";
            default:
                return "Unspecified";
        }
    }
}
