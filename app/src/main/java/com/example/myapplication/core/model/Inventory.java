package com.example.myapplication.core.model;

import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

public class Inventory {
    private List<Guitar> guitars;

    public Inventory() {
        guitars = new LinkedList<>();
    }


    public void addGuitar(String serialNumber, double price,
                          GuitarSpec spec) {
        Guitar guitar = new Guitar(serialNumber, price, spec);
        guitars.add(guitar);
    }

    public Guitar getGuitar(String serialNumber) {
        for (Iterator i = guitars.iterator(); i.hasNext(); ) {
            Guitar guitar = (Guitar)i.next();
            if (guitar.getSerialNumber().equals(serialNumber)) {
                return guitar;
            }
        }
        return null;
    }
    //기타 스펙에 위임함으로써 속성이 추가 되어도 기타스펙만 바꾸면 될 수 있게
    public List search(GuitarSpec searchSpec) {//기타 스펙에 위임을 해서 코드가 짧아짐 매개변수로 넘어온 서치스펙이랑 창고에 있는 기타들과 같으면 리스트에 리턴
        List<Guitar> matchingGuitars = new LinkedList<>();
        for (Iterator i = guitars.iterator(); i.hasNext(); ) {
            Guitar guitar = (Guitar)i.next();
            if (guitar.getSpec().matches(searchSpec))
                matchingGuitars.add(guitar);
        }
        return matchingGuitars;
    }

    public List<Guitar> getGuitars() {
        return guitars;
    }
}



