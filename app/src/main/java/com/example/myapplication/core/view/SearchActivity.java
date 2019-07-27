package com.example.myapplication.core.view;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.myapplication.R;
import com.example.myapplication.core.model.Inventory;
import com.example.myapplication.util.DBHelper;

public class SearchActivity extends AppCompatActivity {
    private DBHelper dbHelper;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search);

        // 데이터베이스에 접근할 헬퍼를 등록한다
        // 데이터베이스가 저장되는 sqlite파일명은 rickgitar_.db
        // 현재 버전은 1
        dbHelper = new DBHelper(getApplicationContext(), "rickgitar_.db", null, 1);

        Button search = findViewById(R.id.search_btn);

        EditText etSn = findViewById(R.id.search_sn);
        EditText etPrice = findViewById(R.id.search_price);
        EditText etModel = findViewById(R.id.search_model);
        EditText etType = findViewById(R.id.search_type);
        EditText etBuilder = findViewById(R.id.search_builder);
        EditText etNumString = findViewById(R.id.search_numString);
        EditText etBackWood = findViewById(R.id.search_backWood);
        EditText etTopWood = findViewById(R.id.search_topWood);

        search.setOnClickListener(v -> {

            String sn = etSn.getText().toString();
            String model = etModel.getText().toString();
            String price = etPrice.getText().toString();
            String numStrings = etNumString.getText().toString();
            String builder = etBuilder.getText().toString();
            String type = etType.getText().toString();
            String backWood = etBackWood.getText().toString();
            String topWood = etTopWood.getText().toString();

            Toast.makeText(SearchActivity.this, "조건에 맞는 기타를 검색합니다.", Toast.LENGTH_SHORT).show();

            // 조건 정보를 ListActivity로 전달한다. (실제 검색은 ListActivity에서 검색 후 보여짐. 여기에서는 검색 결과가 있는지 여부만 체크)
            Inventory inventory = dbHelper.select(sn, price, builder, model, type, numStrings, backWood, topWood);
            if(inventory.getGuitars() != null && inventory.getGuitars().size() > 0){
                Intent intent = new Intent(SearchActivity.this, ListActivity.class);
                intent.putExtra("serialNumber", sn);
                intent.putExtra("price", price);
                intent.putExtra("builder", builder);
                intent.putExtra("model", model);
                intent.putExtra("type", type);
                intent.putExtra("numStrings", numStrings);
                intent.putExtra("backWood", backWood);
                intent.putExtra("topWood", topWood);

                startActivity(intent);
            }else{
                Toast.makeText(this, "조건에 맞는 기타가 존재하지 않습니다.", Toast.LENGTH_SHORT).show();
            }
        });
    }
}