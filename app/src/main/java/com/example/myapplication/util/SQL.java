package com.example.myapplication.util;

class SQL {
    // 테이블 정보 생성SQL
    static String CREATE =
            "CREATE TABLE Guitar (" +
                    "serialNumber VARCHAR(30) PRIMARY KEY, " +
                    "price INTEGER, " +
                    "builder VARCHAR(30), " +
                    "model VARCHAR(30), " +
                    "type VARCHAR(30), " +
                    "numStrings INTEGER, " +
                    "backWood VARCHAR(30), " +
                    "topWood VARCHAR(30));";

    // 테이블 정보 등록SQL
    static String INSERT =
            "INSERT INTO Guitar (serialNumber, price, builder, model, type, numStrings, backWood, topWood)" +
                    " VALUES ('%s', '%s', '%s', '%s', '%s', '%s', '%s', '%s')";

    // 테이블 정보 삭제SQL
    static String DELETE =
            "DELETE FROM Guitar WHERE serialNumber = '%s'";
}
