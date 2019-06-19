package com.example.parentportal.adapters;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.parentportal.R;
import com.example.parentportal.model.Program;

import java.util.ArrayList;

public class ProgramRecyclerViewAdapter extends RecyclerView.Adapter<ProgramRecyclerViewAdapter.ProgramViewHolder> {

    private Context context;
    private ArrayList<Program> programs;
    private ClickLongClick clickLongClick;

    public interface ClickLongClick{
        void click(Program program, int position);
    }

    public ProgramRecyclerViewAdapter(Context context, ArrayList<Program> programs, ClickLongClick clickLongClick) {
        this.context = context;
        this.programs = programs;
        this.clickLongClick = clickLongClick;
    }

    @NonNull
    @Override
    public ProgramViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        return new ProgramViewHolder(LayoutInflater.from(context).inflate(R.layout.item_program, viewGroup, false));
    }

    @Override
    public void onBindViewHolder(@NonNull ProgramViewHolder programViewHolder, int i) {

        Program program = programs.get(i);

        programViewHolder.tv_item_program_period.setText(String.valueOf(program.getPeriod()));
        programViewHolder.tv_item_program_courseName.setText(String.valueOf(program.getCourseName()));
        programViewHolder.tv_item_program_teacherName.setText(String.valueOf(program.getTeacherName()));
//        Log.i("The teacher", program.getTeacherName());

    }

    @Override
    public int getItemCount() {
        return programs.size();
    }

    public class ProgramViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        private TextView tv_item_program_period,
                tv_item_program_courseName,
                tv_item_program_teacherName;

        public ProgramViewHolder(@NonNull View itemView) {
            super(itemView);

            tv_item_program_period = itemView.findViewById(R.id.tv_item_program_period);
            tv_item_program_courseName = itemView.findViewById(R.id.tv_item_program_courseName);
            tv_item_program_teacherName = itemView.findViewById(R.id.tv_item_program_teacherName);

            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {
            clickLongClick.click(programs.get(getAdapterPosition()), getAdapterPosition());
        }
    }
}
