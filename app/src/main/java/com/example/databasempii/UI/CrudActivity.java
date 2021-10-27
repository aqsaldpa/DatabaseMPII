package com.example.databasempii.UI;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import com.example.databasempii.R;
import com.example.databasempii.Adapter.RecyclerViewAdapter;
import com.example.databasempii.Data.Database.MyApp;
import com.example.databasempii.Data.Model.Mahasiswa;
import com.example.databasempii.Data.common.DataListListener;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.List;

public class CrudActivity extends AppCompatActivity {
    private RecyclerView rvListMahasiswa;
    private FloatingActionButton fabTambah;
    private RecyclerViewAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_crud);

        rvListMahasiswa = findViewById(R.id.rv_list_mahasiswa);
        fabTambah = findViewById(R.id.fab_tambah_data);

        adapter = new RecyclerViewAdapter();
        rvListMahasiswa.setAdapter(adapter);

        adapter.setRemoveListener(new DataListListener() {
            @Override
            public void onRemoveClick(Mahasiswa mahasiswa) {
                adapter.removeData(mahasiswa);
            }
        });

        fabTambah.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(CrudActivity.this, AddRoomDataActivity.class));
            }
        });

    }

    @Override
    protected void onResume() {
        super.onResume();
        List<Mahasiswa> datas = MyApp.getInstance().getDatabase().userDao().getAll();
        adapter.setData(datas);
    }

}