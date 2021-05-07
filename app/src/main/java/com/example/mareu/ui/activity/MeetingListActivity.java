package com.example.mareu.ui.activity;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;

import android.app.AlertDialog;
import android.app.DatePickerDialog;
import android.app.Dialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.DatePicker;

import com.example.mareu.R;
import com.example.mareu.databinding.ActivityMeetingListBinding;
import com.example.mareu.di.DI;
import com.example.mareu.events.DeleteMeetingEvent;
import com.example.mareu.model.Meeting;
import com.example.mareu.model.MeetingRoom;
import com.example.mareu.service.MeetingApiService;
import com.example.mareu.ui.adapter.MyMeetingRecyclerViewAdapter;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;

import java.util.Calendar;
import java.util.Date;
import java.util.List;

import static com.example.mareu.model.MeetingRoom.ODIN;
import static com.example.mareu.model.MeetingRoom.getRooms;


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
            System.out.println("\n\tRéunion n°" + m.getId() + "\n\tLieu : " + m.getRoom() + "\n\tDate : " + m.getDate() + "/" + m.getDate().getMonth() + "\n\tHeure : " + m.getDate().getTime() + "\n\tSujet : " + m.getSubject() + "\n\tMembres : " + m.getMember() + "\n\n");        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu, menu);
        return true;
    }



    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        Calendar calendar = Calendar.getInstance();
        switch(item.getItemId()){
            case R.id.menu_all:
                initRecycler(mApiService.getMeeting());
                return true;
            case R.id.menu_date:

                DatePickerDialog.OnDateSetListener dateSetListener = new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                        calendar.set(year, month, dayOfMonth);
                        initRecycler(mApiService.getMeetingDate(calendar.getTime()));
                    }
                };
                DatePickerDialog datePickerDialog = new DatePickerDialog(this,
                        android.R.style.Theme_Holo_Light_Dialog_NoActionBar,
                        dateSetListener,
                        2021,
                        calendar.get(Calendar.MONTH),
                        calendar.get(Calendar.DAY_OF_MONTH)
                );
                datePickerDialog.show();
                return true;
            case R.id.menu_room:
                AlertDialog.Builder b = new AlertDialog.Builder(this);
                b.setTitle("Select a room : ");
                b.setItems(getRooms(), (dialog, which) -> {
                    dialog.dismiss();
                    initRecycler(mApiService.getMeetingRoom(MeetingRoom.values()[which]));
                });
                b.show();

                return true;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    protected void onResume() {
        super.onResume();
        initRecycler(mApiService.getMeeting());
    }

    public void initRecycler(List<Meeting> meetingList) {
        MyMeetingRecyclerViewAdapter mAdapter = new MyMeetingRecyclerViewAdapter(meetingList);
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
        initRecycler(mApiService.getMeeting());
    }
}
