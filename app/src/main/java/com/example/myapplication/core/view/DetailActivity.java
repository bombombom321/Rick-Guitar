package com.example.myapplication.core.view;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.example.myapplication.R;
import com.example.myapplication.core.model.Guitar;
import com.example.myapplication.core.model.GuitarSpec;
import com.example.myapplication.core.model.Inventory;
import com.example.myapplication.util.DBHelper;

public class DetailActivity extends AppCompatActivity {
    private DBHelper dbHelper;
    private String serialNumber;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);

        dbHelper = new DBHelper(getApplicationContext(), "rickgitar_.db", null, 1);

        Button delete = findViewById(R.id.delete_btn);

        TextView etSn = findViewById(R.id.search_sn);
        TextView etPrice = findViewById(R.id.search_price);
        TextView etModel = findViewById(R.id.search_model);
        TextView etType = findViewById(R.id.search_type);
        TextView etBuilder = findViewById(R.id.search_builder);
        TextView etNumString = findViewById(R.id.search_numString);
        TextView etBackWood = findViewById(R.id.search_backWood);
        TextView etTopWood = findViewById(R.id.search_topWood);

        // 기타정보를 sqlite에서 불러온다
        serialNumber = this.getIntent().getStringExtra("serialNumber");
        Inventory inventory = dbHelper.select(serialNumber, null, null, null, null, null, null, null);
        for(Guitar guitar:inventory.getGuitars()){
            GuitarSpec spec = guitar.getSpec();
            // 기타의 정보를 TextView에서 보여줌
            etSn.setText(guitar.getSerialNumber());
            etPrice.setText(String.valueOf(guitar.getPrice()));
            etModel.setText(spec.getModel());
            etType.setText(spec.getType().toString());
            etBuilder.setText(spec.getBuilder().toString());
            etNumString.setText(String.valueOf(spec.getNumStrings()));
            etBackWood.setText(spec.getBackWood().toString());
            etTopWood.setText(spec.getTopWood().toString());
        }

        // 기타정보를 sqlite에서 삭제한다. 삭제 후 현재 Activity에서 나가기위해 finish를 호출. 이전 페이지인 ListAcitivity로 이동
        delete.setOnClickListener(v -> {
            dbHelper.delete(serialNumber);

            Toast.makeText(this, "삭제되었습니다.", Toast.LENGTH_SHORT).show();

            this.finish();
        });
    }
}