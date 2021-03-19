package com.example.mareu.ui.activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.AlertDialog;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.mareu.R;
import com.example.mareu.databinding.ActivityCreateMeetingBinding;
import com.example.mareu.databinding.ActivityMeetingListBinding;
import com.example.mareu.di.DI;
import com.example.mareu.events.DeleteMeetingEvent;
import com.example.mareu.model.Meeting;
import com.example.mareu.service.MeetingApiService;
import com.example.mareu.ui.adapter.MyMeetingRecyclerViewAdapter;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;


public class MeetingListActivity extends AppCompatActivity {

    private ActivityMeetingListBinding binding;
    private MeetingApiService mApiService;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mApiService = DI.getMeetingApiService();
        binding = ActivityMeetingListBinding.inflate(getLayoutInflater());
        View view = binding.getRoot();
        setContentView(view);

        binding.createButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(v.getContext(), CreateMeetingActivity.class);
                v.getContext().startActivity(i);
            }
        });

        for(Meeting m : mApiService.getMeeting()){
            System.out.println("\n\tRéunion n°" + m.getId() + "\n\tLieu : " + m.getRoom() + "\n\tHeure : " + m.getHour().getHour() + "\n\tSujet : " + m.getSubject() + "\n\tMembres : " + m.getMember() + "\n\n");
        }
    }

    @Override
    protected void onResume() {
        super.onResume();
        initRecycler();
    }

    public void initRecycler() {
        MyMeetingRecyclerViewAdapter mAdapter = new MyMeetingRecyclerViewAdapter(mApiService.getMeeting());
        binding.recyclerView.setAdapter(mAdapter);
        binding.recyclerView.setLayoutManager(new LinearLayoutManager(this));
    }

    @Override
    public void onStart() {
        super.onStart();
        EventBus.getDefault().register(this);
    }

    @Override
    public void onStop() {
        super.onStop();
        EventBus.getDefault().unregister(this);
    }

    @Subscribe
    public void onDeleteMeeting(DeleteMeetingEvent event) {
        mApiService.deleteMeeting(event.mMeeting);
        initRecycler();
    }
}