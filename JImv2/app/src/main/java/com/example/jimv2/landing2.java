package com.example.jimv2;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.ViewGroup;
import android.widget.TextView;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.Toast;
import java.text.*;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

import com.firebase.ui.database.FirebaseListAdapter;
import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;



public class landing2 extends AppCompatActivity {

    RecyclerView recyclerView;
    DatabaseReference databaseref;
    FirebaseListAdapter<ExerciseObject> firebaseListAdapter;
    FirebaseRecyclerOptions <ExerciseObject> options;
    FirebaseRecyclerAdapter <ExerciseObject, DatabaseHolder> adapter;
    Date currentDate = Calendar.getInstance().getTime();
    SimpleDateFormat df = new SimpleDateFormat("ddMMMyyyy");
    String formattedDate = df.format(currentDate);
    private static final String TAG = "Landing";

    private Button calendarButton;
    private Button statisticsButton;
    private ImageButton userIconButton;
    private Button workoutButton;
    private Button Exercise1;
    private ImageButton leftArrow;
    private ImageButton rightArrow;
    private Button friendsButton;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.landing2);
        //getCurrentDate();
        recyclerView = (RecyclerView) findViewById(R.id.landingRecyclerView);
        recyclerView.setHasFixedSize(true);
        getYesterdayDateString();
        //****
        //Recycler View from Database Query
        //***
        databaseref = FirebaseDatabase.getInstance().getReference().child(setYesterdayDateString());
        options = new FirebaseRecyclerOptions.Builder<ExerciseObject>().setQuery(databaseref,ExerciseObject.class).build();
        adapter = new FirebaseRecyclerAdapter<ExerciseObject, DatabaseHolder>(options) {
            @Override
            protected void onBindViewHolder(@NonNull DatabaseHolder holder, final int position, @NonNull ExerciseObject model) {
//                findViewById(R.id.loadingBarDB).setVisibility(View.GONE);
                holder.exerciseName.setText(model.getExerciseName());

                holder.itemView.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Toast.makeText(landing2.this, "This item is removed from workout: " + position, Toast.LENGTH_SHORT).show();
                        DatabaseReference myRef = adapter.getRef(position);
                        myRef.removeValue();
                        adapter.notifyDataSetChanged();
                    }
                });
            }


            @NonNull
            @Override
            public DatabaseHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
                Log.d(TAG, "onCreateViewHolder: called.");

                View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.landingexercise,parent,false);
                return new DatabaseHolder(view);
            }
        };

        LinearLayoutManager linearlayoutManager = new LinearLayoutManager(getApplicationContext());
        recyclerView.setLayoutManager(linearlayoutManager);
        adapter.startListening();
        recyclerView.setAdapter(adapter);



        //
        //
        //***

        calendarButton = (Button) findViewById(R.id.calendar_button);
        calendarButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openCalendarScreen();
            }
        });

        statisticsButton = (Button) findViewById(R.id.statistics_button);
        statisticsButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openStatisticsScreen();
            }
        });

        workoutButton = (Button) findViewById(R.id.workoutButtonLanding);
        workoutButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openWorkoutScreen();
            }
        });

        leftArrow = (ImageButton) findViewById(R.id.leftArrow);

        rightArrow = (ImageButton) findViewById(R.id.rightArrow);
        rightArrow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                forwardDay();
            }
        });



        friendsButton = (Button) findViewById(R.id.friends_button);
        friendsButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openFriends();
            }
        });


        userIconButton = (ImageButton) findViewById(R.id.userIcon);
        userIconButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openProfileScreen();
            }
        });


    }

    public void getCurrentDate(){
        String date_n = new SimpleDateFormat("MMM dd, yyyy", Locale.getDefault()).format(new Date());
        TextView date  = (TextView) findViewById(R.id.currentDate);
        Calendar calendar = Calendar.getInstance();
        calendar.add(Calendar.DATE, -1);
        Date yesterday = calendar.getTime();

        date.setText(yesterday.toString());
    }

    private Date yesterday() {
        final Calendar cal = Calendar.getInstance();
        cal.add(Calendar.DATE, -1);
        return cal.getTime();
    }

    private String getYesterdayDateString() {
        String ydate;
        DateFormat dateFormat = new SimpleDateFormat("MMM dd, yyyy");
        TextView date  = (TextView) findViewById(R.id.currentDate);
        ydate = dateFormat.format(yesterday());
        date.setText(ydate);
        return ydate;
    }
    private String setYesterdayDateString() {
        String ydate;
        DateFormat dateFormat = new SimpleDateFormat("ddMMMyyyy");
        TextView date  = (TextView) findViewById(R.id.currentDate);
        ydate = dateFormat.format(yesterday());
        return ydate;
    }

    public void openCalendarScreen(){
        Intent intent = new Intent(this,CalendarActivity.class);
        startActivity(intent);
    }

    public void openStatisticsScreen(){
        Intent intent = new Intent(this,ReportActivity.class);
        startActivity(intent);
    }

    public void openProfileScreen(){
        Intent intent = new Intent(this,ProfileActivity.class);
        startActivity(intent);
    }

    public void openCalculatorScreen(){
        Intent intent = new Intent(this,CalculatorActivity.class);
        startActivity(intent);
    }
    public void openWorkoutScreen(){
        Intent intent = new Intent(this,WorkoutActivityV2.class);
        startActivity(intent);
    }
    public void openAddExercise(){
        Intent intent = new Intent(this,AddExercise.class);
        startActivity(intent);
    }
    public void openExercise(){
        Intent intent = new Intent(this,ExerciseActivity.class);
        startActivity(intent);
    }

    public void forwardDay(){
        Intent intent = new Intent(this,landing.class);
        startActivity(intent);
    }
    public void openFriends(){
        Intent intent = new Intent(this,DatabaseWorkout.class);
        startActivity(intent);
    }
}
