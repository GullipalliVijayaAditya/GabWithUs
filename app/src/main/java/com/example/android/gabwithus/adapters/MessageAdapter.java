package com.example.android.gabwithus.adapters;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.android.gabwithus.R;
import com.example.android.gabwithus.holders.MessageHolder;
import com.example.android.gabwithus.models.Message;
import com.google.firebase.auth.FirebaseAuth;

import java.util.List;


public class MessageAdapter extends RecyclerView.Adapter<MessageHolder> {
    private List<Message> messagesList;

    public MessageAdapter(List<Message> messagesList) {
        this.messagesList = messagesList;
    }

    @Override
    public MessageHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.message, parent, false);

        return new MessageHolder(view, view.getContext());
    }

    @Override
    public void onBindViewHolder(final MessageHolder holder, int position) {
        final String currentUserId = FirebaseAuth.getInstance().getCurrentUser().getUid();

        Message c = messagesList.get(position);

        if (messagesList.size() - 1 == position) {
            holder.setLastMessage(currentUserId, c.getFrom(), c.getTo());
        } else {
            holder.hideBottom();
        }

        if (c.getFrom().equals(currentUserId)) {
            holder.setRightMessage(c.getFrom(), c.getMessage(), c.getTimestamp(), c.getType());
        } else {
            holder.setLeftMessage(c.getFrom(), c.getMessage(), c.getTimestamp(), c.getType());
        }
    }

    @Override
    public int getItemCount() {
        return messagesList.size();
    }
}
