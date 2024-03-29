package com.example.myapplication.core.model;

public class GuitarSpec {

    private Builder builder;
    private String model;
    private Type type;
    private int numStrings;
    private Wood backWood;
    private Wood topWood;

    public GuitarSpec(Builder builder, String model, Type type,
                      int numStrings, Wood backWood, Wood topWood) {
        this.builder = builder;
        this.model = model;
        this.type = type;
        this.numStrings = numStrings;
        this.backWood = backWood;
        this.topWood = topWood;
    }

    public Builder getBuilder() {
        return builder;
    }

    public String getModel() {
        return model;
    }

    public Type getType() {
        return type;
    }

    public int getNumStrings() {
        return numStrings;
    }

    public Wood getBackWood() {
        return backWood;
    }

    public Wood getTopWood() {
        return topWood;
    }
    //자기자신의 속성과 매개변수로 넘어온 기타스펙의 속성을 비교해서 같은지 비교하는 역할
    public boolean matches(GuitarSpec otherSpec) {
        if (builder != otherSpec.builder)
            return false;
        if ((model != null) && (!model.equals("")) &&
                (!model.toLowerCase().equals(otherSpec.model.toLowerCase())))
            return false;
        if (type != otherSpec.type)
            return false;
        if (numStrings != otherSpec.numStrings)
            return false;
        if (topWood != otherSpec.topWood) {
            if (backWood != otherSpec.backWood)
                return false;
            return false;
        }
        return true;
    }
}
