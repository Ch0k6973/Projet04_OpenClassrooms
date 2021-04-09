package com.example.mareu.ui.activity;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Toast;

import com.example.mareu.databinding.ActivityCreateMeetingBinding;
import com.example.mareu.di.DI;
import com.example.mareu.model.Meeting;
import com.example.mareu.model.MeetingRoom;
import com.example.mareu.service.MeetingApiService;
import com.google.android.material.snackbar.Snackbar;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import petrov.kristiyan.colorpicker.ColorPicker;

public class CreateMeetingActivity extends AppCompatActivity {

    private ActivityCreateMeetingBinding binding;
    private MeetingApiService mApiService;
    private ColorPicker colorPicker;
    private int colorMeeting = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mApiService = DI.getMeetingApiService();
        binding = ActivityCreateMeetingBinding.inflate(getLayoutInflater());
        View view = binding.getRoot();
        binding.createLocationText.setAdapter(new ArrayAdapter<MeetingRoom>(this,
                android.R.layout.simple_list_item_1, MeetingRoom.values()));
        binding.createHourPicker.setIs24HourView(true);
        setContentView(view);

        binding.createButton.setOnClickListener(new View.OnClickListener() {
            @RequiresApi(api = Build.VERSION_CODES.M)
            @Override
            public void onClick(View v) {
                if (isFormValid()){
                    Calendar mCalendar = Calendar.getInstance();
                    mCalendar.set(Calendar.MILLISECOND, 0);
                    mCalendar.set(binding.createDatePicker.getYear(),
                            binding.createDatePicker.getMonth(),
                            binding.createDatePicker.getDayOfMonth(),
                            binding.createHourPicker.getHour(),
                            binding.createHourPicker.getMinute(),
                            0
                    );
                    Meeting meeting = new Meeting(mApiService.getMeeting().size() + 1,
                            colorMeeting,
                            MeetingRoom.valueOf(binding.createLocationText.getSelectedItem().toString().toUpperCase()),
                            mCalendar.getTime() ,
                            binding.createSubjectText.getText().toString(),
                            binding.createMemberText.getText().toString()
                    );
                    if (isRegister(meeting)) {
                        Snackbar.make(v, "This room is not available", Snackbar.LENGTH_SHORT).show();
                    }
                    else {
                        mApiService.createMeeting(meeting);
                        finish();
                    }
                }
            }
        });

        binding.createColorPicker.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                initColorPicker();
                colorPicker.show();
            }
        });
    }

    private void initColorPicker() {
        colorPicker = new ColorPicker(this);
        colorPicker.setOnChooseColorListener(new ColorPicker.OnChooseColorListener() {
            @Override
            public void onChooseColor(int position, int color) {
                colorMeeting = color;
            }

            @Override
            public void onCancel(){
                // put code
            }
        });
    }

    public boolean isRegister(Meeting meeting) {
        for(Meeting m : mApiService.getMeeting()){
            if (meeting.getDate().getTime() == m.getDate().getTime()) {
                if (meeting.getRoom() == m.getRoom()){
                    return true;
                }
            }
        }
        return false;
    }

    public boolean isFormValid() {
        if (colorMeeting == 0) {
            Toast.makeText(this, "Color not selected", Toast.LENGTH_SHORT).show();
            return false;
        }
        else if (binding.createSubjectText.getText().toString().isEmpty()) {
            Toast.makeText(this, "Subject empty", Toast.LENGTH_SHORT).show();
            return false;
        }
        else if (binding.createMemberText.getText().toString().isEmpty()) {
            Toast.makeText(this, "Members empty", Toast.LENGTH_SHORT).show();
            return false;
        }
        return true;
    }
}