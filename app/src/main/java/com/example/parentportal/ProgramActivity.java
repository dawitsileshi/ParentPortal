package com.example.parentportal;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.widget.Toast;

import com.example.parentportal.Utils.RequestController;
import com.example.parentportal.adapters.ProgramRecyclerViewAdapter;
import com.example.parentportal.model.Program;
import com.example.parentportal.model.Teacher;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

public class ProgramActivity extends AppCompatActivity implements ProgramRecyclerViewAdapter.ClickLongClick {

    private RecyclerView recyclerView;
    private ProgramRecyclerViewAdapter programRecyclerViewAdapter;

    private Retrofit retrofit;
    private RetrofitCalls retrofitCalls;

    private ArrayList<Program> programs;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_program);

        recyclerView = findViewById(R.id.rv_activity_program);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        retrofit = RequestController.getRetrofit();

        retrofitCalls = retrofit.create(RetrofitCalls.class);
        Intent intent = getIntent();
        if(intent.getExtras() == null) {
            Toast.makeText(this, "Nothing sent", Toast.LENGTH_SHORT).show();
            return;
        } else {
            programs = intent.getParcelableArrayListExtra("programs");
            Toast.makeText(this, programs.get(0).getTeacherName(), Toast.LENGTH_SHORT).show();
        }

//        for (int i = 0; i < programs.size(); i++) {
//            Call<Teacher> teacherCall = retrofitCalls.getTeacher(programs.get(i).getTeacherId());
//
//            final int finalI = i;
//            teacherCall.enqueue(new Callback<Teacher>() {
//                @Override
//                public void onResponse(@NonNull Call<Teacher> call, @NonNull Response<Teacher> response) {
//                    programs.get(finalI).setTeacher(response.body());
//                }
//
//                @Override
//                public void onFailure(@NonNull Call<Teacher> call, @NonNull Throwable t) {
//                    Toast.makeText(ProgramActivity.this, t.getMessage(), Toast.LENGTH_SHORT).show();
//                }
//            });
//        }
//        for (int i = 0; i < programs.size(); i++) {
//            Log.i("The teacher", programs.get(i).getTeacherName());
//        }
        Toast.makeText(this, String.valueOf(programs.size()), Toast.LENGTH_SHORT).show();
        programRecyclerViewAdapter = new ProgramRecyclerViewAdapter(this, programs, this);
        recyclerView.setAdapter(programRecyclerViewAdapter);
    }

    @Override
    public void click(Program program, int position) {
        Toast.makeText(this, "Touched", Toast.LENGTH_SHORT).show();
    }
}
