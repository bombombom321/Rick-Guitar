package com.example.myapplication.core.view;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.myapplication.R;
import com.example.myapplication.core.model.Builder;
import com.example.myapplication.core.model.Guitar;
import com.example.myapplication.core.model.GuitarSpec;
import com.example.myapplication.core.model.Type;
import com.example.myapplication.core.model.Wood;
import com.example.myapplication.util.DBHelper;

public class InsertActivity extends AppCompatActivity {
    private DBHelper dbHelper;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_insert);

        dbHelper = new DBHelper(getApplicationContext(), "rickgitar_.db", null, 1);

        Button insert = findViewById(R.id.insert_btn);

        EditText etSn = findViewById(R.id.insert_sn);
        EditText etPrice = findViewById(R.id.insert_price);
        EditText etModel = findViewById(R.id.insert_model);
        EditText etType = findViewById(R.id.insert_type);
        EditText etBuilder = findViewById(R.id.insert_builder);
        EditText etNumString = findViewById(R.id.insert_numString);
        EditText etBackWood = findViewById(R.id.insert_backWood);
        EditText etTopWood = findViewById(R.id.insert_topWood);

        insert.setOnClickListener(v -> {

            String sn = etSn.getText() != null ? etSn.getText().toString() : "";
            String model = etModel.getText() != null ? etModel.getText().toString() : "";
            if (sn.trim().equals("") || model.trim().equals("")) {
                showMsg();
                return;
            }

            String priceString = etPrice.getText() != null ? etPrice.getText().toString() : "";
            String numStringString = etNumString.getText() != null ? etNumString.getText().toString() : "";
            double price = 0;
            int numStrings = 0;

            if (priceString.trim().equals("") || numStringString.trim().equals("")) {
                showMsg();
                return;
            }

            try{
                price = Double.valueOf(priceString);
                numStrings = Integer.parseInt(numStringString);
            }catch (NumberFormatException e){
                Toast.makeText(InsertActivity.this, "가격과 줄 수는 숫자로만 입력해주세요", Toast.LENGTH_SHORT).show();
                return;
            }
            Builder builder = Builder.toEnum(etBuilder.getText().toString());
            Type type = Type.toEnum((etType.getText().toString()));
            Wood backWood = Wood.toEnum(etBackWood.getText().toString());
            Wood topWood = Wood.toEnum(etTopWood.getText().toString());

            if (builder != null && type != null && backWood != null && topWood != null) {
                GuitarSpec spec = new GuitarSpec(builder, model, type, numStrings, backWood, topWood);
                Guitar guitar = new Guitar(sn, price, spec);

                // 작성한 기타 정보를 sqlite에 등록한다
                this.dbHelper.insert(guitar);

                Toast.makeText(InsertActivity.this, "삽입에 성공하였습니다.", Toast.LENGTH_SHORT).show();
            } else showMsg();
        });
    }

    /**
     * 잘못입력된 메시지를 보여준다
     */
    private void showMsg() {
        Toast.makeText(InsertActivity.this, "잘못 입력되었습니다.", Toast.LENGTH_SHORT).show();
    }
}
