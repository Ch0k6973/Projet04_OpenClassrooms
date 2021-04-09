package com.example.mareu.ui.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.recyclerview.widget.RecyclerView;

import com.example.mareu.databinding.ActivityMeetingListItemBinding;
import com.example.mareu.events.DeleteMeetingEvent;
import com.example.mareu.model.Meeting;

import org.greenrobot.eventbus.EventBus;

import java.text.SimpleDateFormat;
import java.util.List;


public class MyMeetingRecyclerViewAdapter extends RecyclerView.Adapter<MyMeetingRecyclerViewAdapter.ViewHolder> {

    private final List<Meeting> mMeeting;
    SimpleDateFormat sdf = new SimpleDateFormat("hh:mm");

    public MyMeetingRecyclerViewAdapter(List<Meeting> items){
        mMeeting = items;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new ViewHolder(ActivityMeetingListItemBinding.inflate(LayoutInflater.from(parent.getContext()), parent, false));
    }

    @Override
    public void onBindViewHolder(final ViewHolder holder, int position) {
        Meeting meeting = mMeeting.get(position);
        holder.binding.itemListAvatar.setBackgroundColor(meeting.getColor());
        holder.binding.itemListTitle.setText(meeting.getRoom() + " - " + sdf.format(meeting.getDate()) + " - " + meeting.getSubject());
        holder.binding.itemListMember.setText(meeting.getMember());

        holder.binding.itemListDeleteButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                EventBus.getDefault().post(new DeleteMeetingEvent(meeting));
            }
        });

    }

    @Override
    public int getItemCount() {
        return mMeeting.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        private ActivityMeetingListItemBinding binding;

        public ViewHolder(ActivityMeetingListItemBinding binding) {
            super(binding.getRoot());
            this.binding = binding;
        }
    }
}
