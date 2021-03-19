package com.example.mareu.ui.activity;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.fragment.app.FragmentActivity;
import androidx.recyclerview.widget.RecyclerView;

import android.app.Activity;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;

import com.example.mareu.R;
import com.example.mareu.databinding.ActivityCreateMeetingBinding;
import com.example.mareu.databinding.ActivityMeetingListItemBinding;
import com.example.mareu.di.DI;
import com.example.mareu.model.Hour;
import com.example.mareu.model.Meeting;
import com.example.mareu.model.MeetingRoom;
import com.example.mareu.service.MeetingApiService;
import com.example.mareu.ui.adapter.MyMeetingRecyclerViewAdapter;

import petrov.kristiyan.colorpicker.ColorPicker;

import static com.example.mareu.model.MeetingRoom.values;

public class CreateMeetingActivity extends AppCompatActivity {

    private ActivityCreateMeetingBinding binding;
    private MeetingApiService mApiService;
    private ColorPicker colorPicker;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mApiService = DI.getMeetingApiService();
        binding = ActivityCreateMeetingBinding.inflate(getLayoutInflater());
        View view = binding.getRoot();
        binding.createLocationText.setAdapter(new ArrayAdapter<MeetingRoom>(this,
                android.R.layout.simple_list_item_1, MeetingRoom.values()));
        binding.createHourText.setIs24HourView(true);
        initColorPicker();
        setContentView(view);

        binding.createButton.setOnClickListener(new View.OnClickListener() {
            @RequiresApi(api = Build.VERSION_CODES.M)
            @Override
            public void onClick(View v) {
                mApiService.createMeeting(new Meeting(mApiService.getMeeting().size() + 1,
                        MeetingRoom.valueOf(binding.createLocationText.getSelectedItem().toString().toUpperCase()),
                                new Hour(binding.createHourText.getHour(), binding.createHourText.getMinute()),
                        binding.createSubjectText.getText().toString(),
                        binding.createMemberText.getText().toString()
                    )
                );
                for(Meeting m : mApiService.getMeeting()){
                    System.out.println("\n\tRéunion n°" + m.getId() + "\n\tLieu : " + m.getRoom() + "\n\tHeure : " + m.getHour().getHour() + "\n\tSujet : " + m.getSubject() + "\n\tMembres : " + m.getMember() + "\n\n");
                }
                finish();
            }
        });

        binding.createColorPicker.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                colorPicker.show();
            }
        });

    }

    private void initColorPicker() {
        colorPicker = new ColorPicker(this);
        colorPicker.setOnChooseColorListener(new ColorPicker.OnChooseColorListener() {
            @Override
            public void onChooseColor(int position, int color) {

            }

            @Override
            public void onCancel(){
                // put code
            }
        });
    }
}