package com.example.jimv2;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import java.util.ArrayList;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import com.squareup.picasso.Picasso;

public class WorkoutAdapter extends RecyclerView.Adapter<WorkoutAdapter.ExerciseViewHolder> {
    public ArrayList<ExerciseObject> mExerciseList;
    private OnClickListner mListener;
    private View.OnLongClickListener longListener;
    private static final String TAG = "WorkoutAdapter";


    public interface OnClickListner{
        void onItemClick(int position);

    }

    public void setOnItemClickListner(OnClickListner listner){
        mListener = listner;
    }
    public void setOnLongClickListener(View.OnLongClickListener listener){
        longListener = listener;
    }

    public static class ExerciseViewHolder extends RecyclerView.ViewHolder{

        public ImageView exerciseImage;
        public TextView  exerciseName;

        public ExerciseViewHolder(@NonNull View itemView, final OnClickListner  listner) {
            super(itemView);
            exerciseImage = itemView.findViewById(R.id.exercise_pic);
            exerciseName = itemView.findViewById(R.id.exercise_name);
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if(listner != null){
                        int position = getAdapterPosition();
                        if(position != RecyclerView.NO_POSITION){
                            listner.onItemClick(position);
                        }
                    }
                }
            });
        }
    }

    public WorkoutAdapter(ArrayList<ExerciseObject> exerciseList){
        mExerciseList = exerciseList;
    }

    @NonNull
    @Override
    public ExerciseViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        Log.d(TAG, "onCreateViewHolder: called.");

        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.exercisesquare,parent,false); //card 2
        ExerciseViewHolder evh = new ExerciseViewHolder(v,mListener);
        return evh;
    }

    @Override
    public void onBindViewHolder(@NonNull ExerciseViewHolder holder, int position) {
        Log.d(TAG, "onBindViewHolder: called.");

        ExerciseObject currentExercise = mExerciseList.get(position);
        holder.exerciseImage.setImageResource(currentExercise.getmImageResource());
        holder.exerciseName.setText(currentExercise.getmText());
    }


    @Override
    public int getItemCount() {
        return mExerciseList.size();
    }
}
