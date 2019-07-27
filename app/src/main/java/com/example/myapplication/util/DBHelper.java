package com.example.myapplication.util;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import com.example.myapplication.core.model.Builder;
import com.example.myapplication.core.model.Guitar;
import com.example.myapplication.core.model.GuitarSpec;
import com.example.myapplication.core.model.Inventory;
import com.example.myapplication.core.model.Type;
import com.example.myapplication.core.model.Wood;

public class DBHelper extends SQLiteOpenHelper {

    public DBHelper(Context context, String name, SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, factory, version);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(SQL.CREATE);
    }

    /**
     * 텍스트 문자열이 비어있는지 여부
     * @param str 문자열
     * @return 문자열이 비어있으면 true 비어있지 않으면 false
     */
    private boolean isEmpty(String str){
        return str == null || str.equals("");
    }

    /**
     * 검색키워드에 맞는 기타 목록을 반환한다
     * @param serialNumber 시리얼번호
     * @param price 가격
     * @param builder 빌더
     * @param model 모델
     * @param type 유형
     * @param numStrings 줄수
     * @param backWood 백우드
     * @param topWood 탑우드
     * @return 조건에 맞는 기타 목록을 Inventory로 반환
     */
    public Inventory select(String serialNumber, String price, String builder, String model, String type, String numStrings, String backWood, String topWood) {
        Inventory inventory = new Inventory();

        // 읽고 쓰기가 가능하게 DB 열기
        SQLiteDatabase db = getReadableDatabase();
        StringBuilder sql = new StringBuilder("SELECT serialNumber, price, builder, model, type, numStrings, backWood, topWood FROM Guitar WHERE 1= 1");

        // 조건에 맞는 항목만 검색하도록 SQL문 생성
        if(!isEmpty(serialNumber)) sql.append(" AND serialNumber = '" + serialNumber + "'");
        if(!isEmpty(price))        sql.append(" AND price = " + price);
        if(!isEmpty(builder))      sql.append(" AND builder = '" + builder + "'");
        if(!isEmpty(model))        sql.append(" AND model = '" + model + "'");
        if(!isEmpty(type))         sql.append(" AND type = '" + type + "'");
        if(!isEmpty(numStrings))   sql.append(" AND numStrings = " + numStrings);
        if(!isEmpty(backWood))     sql.append(" AND backWood = '" + backWood + "'");
        if(!isEmpty(topWood))      sql.append(" AND topWood = '" + topWood + "'");

        Log.i("OUTPUT", sql.toString());
        // DB에 있는 데이터를 쉽게 처리하기 위해 Cursor를 사용하여 테이블에 있는 모든 데이터 출력
        Cursor cursor = db.rawQuery(sql.toString(), null);
        while (cursor.moveToNext()) {
            GuitarSpec s = new GuitarSpec(Builder.toEnum(cursor.getString(2)), cursor.getString(3), Type.toEnum(cursor.getString(4)), cursor.getInt(5), Wood.toEnum(cursor.getString(6)), Wood.toEnum(cursor.getString(7)));
            inventory.addGuitar(cursor.getString(0), cursor.getDouble(1), s);
        }

        cursor.close();
        db.close();

        return inventory;
    }

    /**
     * 기타 정보를 등록한다
     * @param guitar 기타정보
     */
    public void insert(Guitar guitar) {
        if(guitar != null && guitar.getSpec() != null){
            GuitarSpec spec = guitar.getSpec();

            // 읽고 쓰기가 가능하게 DB 열기
            SQLiteDatabase db = getWritableDatabase();
            String sql = String.format(SQL.INSERT, guitar.getSerialNumber(), guitar.getPrice(), spec.getBuilder(), spec.getModel(), spec.getType(), spec.getNumStrings(), spec.getBackWood(), spec.getTopWood());
            Log.i("OUTPUT", sql);

            // DB에 입력한 값으로 행 추가
            db.execSQL(sql);
            db.close();
        }

    }

    // DB 업그레이드를 위해 버전이 변경될 때 호출되는 함수
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
    public void update(String item, int price , int serial) {
        SQLiteDatabase db = getWritableDatabase();
        // 입력한 항목과 일치하는 행의 가격 정보 수정
        db.execSQL("UPDATE rickguitar1 SET price=" + price + " WHERE item='" + item + "';");
        db.execSQL("UPDATE rickguitar1 SET serial=" + serial + " WHERE item='" + item + "';");
        db.close();
    }

    public void delete(String serialNumber) {
        SQLiteDatabase db = getWritableDatabase();

        String sql = String.format(SQL.DELETE, serialNumber);
        Log.i("OUTPUT", sql);

        db.execSQL(sql);
        db.close();
    }
}




