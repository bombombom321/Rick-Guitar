package com.example.myapplication.core.view;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListAdapter;
import android.widget.ListView;

import com.example.myapplication.R;
import com.example.myapplication.adapter.GuitarListAdapter;
import com.example.myapplication.core.model.Builder;
import com.example.myapplication.core.model.GuitarSpec;
import com.example.myapplication.core.model.Inventory;
import com.example.myapplication.core.model.Type;
import com.example.myapplication.core.model.Wood;
import com.example.myapplication.util.DBHelper;

public class ListActivity extends AppCompatActivity {
    private DBHelper dbHelper;
    private Inventory inventory;

    private ListView guitarList;
    private String serialNumber, price, builder, model, type, numStrings, backWood, topWood;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list);

        dbHelper = new DBHelper(getApplicationContext(), "rickgitar_.db", null, 1);

        serialNumber = this.getIntent().getStringExtra("serialNumber");
        price = this.getIntent().getStringExtra("price");
        builder = this.getIntent().getStringExtra("builder");
        model = this.getIntent().getStringExtra("model");
        type = this.getIntent().getStringExtra("type");
        numStrings = this.getIntent().getStringExtra("numStrings");
        backWood = this.getIntent().getStringExtra("backWood");
        topWood = this.getIntent().getStringExtra("topWood");

        inventory = dbHelper.select(serialNumber, price, builder, model, type, numStrings, backWood, topWood);

        guitarList = findViewById(R.id.guitar_list);
        ListAdapter adapter = new GuitarListAdapter(this, inventory);

        // 기타 목록 중에서 선택한 기타의 상세정보로 이동하는 이벤트 등록
        guitarList.setOnItemClickListener(new AdapterView.OnItemClickListener(){
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int position, long id) {
                Log.i("OUTPUT", position + "");
                Intent intent = new Intent(ListActivity.this, DetailActivity.class);

                String serialNumber = inventory.getGuitars().get(position).getSerialNumber();
                intent.putExtra("serialNumber", serialNumber);
                startActivity(intent);
            }
        });
        guitarList.setAdapter(adapter);
    }

    @Override
    public void onResume(){
        super.onResume();

        // 상세에서 기타가 삭제된 경우 해당 목록을 새로고치기 위해 resume 프로세스에서 다시한번 검색
        inventory = dbHelper.select(serialNumber, price, builder, model, type, numStrings, backWood, topWood);
        ListAdapter adapter = new GuitarListAdapter(this, inventory);
        guitarList.setAdapter(adapter);
    }
}
