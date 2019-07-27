package com.example.myapplication.core.model;

public enum Wood {

    INDIAN_ROSEWOOD, BRAZILIAN_ROSEWOOD, MAHOGANY, MAPLE,
    COCOBOLO, CEDAR, ADIRONDACK, ALDER, SITKA;


    public static Wood toEnum(String keyword) {
        keyword = keyword.toLowerCase();
        if (keyword.equals(INDIAN_ROSEWOOD.toString().toLowerCase())) return INDIAN_ROSEWOOD;
        else if (keyword.equals(BRAZILIAN_ROSEWOOD.toString().toLowerCase())) return BRAZILIAN_ROSEWOOD;
        else if (keyword.equals(MAHOGANY.toString().toLowerCase())) return MAHOGANY;
        else if (keyword.equals(MAPLE.toString().toLowerCase())) return MAPLE;
        else if (keyword.equals(COCOBOLO.toString().toLowerCase())) return COCOBOLO;
        else if (keyword.equals(CEDAR.toString().toLowerCase())) return CEDAR;
        else if (keyword.equals(ADIRONDACK.toString().toLowerCase())) return ADIRONDACK;
        else if (keyword.equals(ALDER.toString().toLowerCase())) return ALDER;
        else if (keyword.equals(SITKA.toString().toLowerCase())) return SITKA;
        else return null;
    }


    public String toString() {
        switch(this) {
            case INDIAN_ROSEWOOD:    return "Indian Rosewood";
            case BRAZILIAN_ROSEWOOD: return "Brazilian Rosewood";
            case MAHOGANY:           return "Mahogany";
            case MAPLE:              return "Maple";
            case COCOBOLO:           return "Cocobolo";
            case CEDAR:              return "Cedar";
            case ADIRONDACK:         return "Adirondack";
            case ALDER:              return "Alder";
            case SITKA:              return "Sitka";
            default:  return "unspecified";
        }
    }
}
