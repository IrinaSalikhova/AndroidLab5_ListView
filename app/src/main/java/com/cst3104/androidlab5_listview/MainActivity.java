package com.cst3104.androidlab5_listview;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private EditText newMessage;
    private MyAdapter myAdapter;
    private ArrayList<ChatMessage> messages;

    public static Context mycontext;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mycontext = this;

        messages = new ArrayList<ChatMessage>();
        newMessage = findViewById(R.id.messageField);

        ListView messageView = findViewById(R.id.messageView);
        myAdapter = new MyAdapter(this, messages);
        messageView.setAdapter(myAdapter);

        ImageButton receiveButton = findViewById(R.id.receiveButton);

        receiveButton.setOnClickListener(click ->
        {
            String readMessage = newMessage.getText().toString();
            if (!readMessage.isEmpty()) {
                ChatMessage thisMessage = new ChatMessage(readMessage, 0);
                messages.add(thisMessage);
                myAdapter.notifyDataSetChanged();
                newMessage.setText("");
            }
        });

        ImageButton sendButton = findViewById(R.id.sendButton);

        sendButton.setOnClickListener(click ->
        {
            String readMessage = newMessage.getText().toString();
            if (!readMessage.isEmpty()) {
                ChatMessage thisMessage = new ChatMessage(readMessage, 1);
            messages.add(thisMessage);
            myAdapter.notifyDataSetChanged();
            newMessage.setText("");
        }
        });

        messageView.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {

                AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(mycontext);
                alertDialogBuilder.setTitle(getString(R.string.alert_question))

                        //What is the message:
                        .setMessage(getString(R.string.alert_inf1) + (position+1) + "\n" + getString(R.string.alert_inf2) + id)

                        //what the Yes button does:
                        .setPositiveButton(R.string.positive_alert, (click, arg) -> {
                            messages.remove(position);
                            myAdapter.notifyDataSetChanged();
                        })
                        //What the No button does:
                        .setNegativeButton(R.string.negative_alert, (click, arg) -> { })

                        //Show the dialog
                        .create().show();
                return true;
            }
        });

    }

    public class MyAdapter extends ArrayAdapter<ChatMessage> {


        public MyAdapter(@NonNull Context context, @NonNull List<ChatMessage> objects) {
            super(context, 0, objects);
        }

        @Override
        public int getCount() {
            return messages.size();
        }

        @Nullable
        @Override
        public ChatMessage getItem(int position) {
            return messages.get(position);
        }

        @Override
        public long getItemId(int position) {
            return position;
        }

        @NonNull
        @Override
        public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
            // Get the data item for this position
            ChatMessage actualMessage = getItem(position);
            // Check if an existing view is being reused, otherwise inflate the view
            if (convertView == null) {
                if (actualMessage.getStatus() == 0) {
                    convertView = LayoutInflater.from(getContext()).inflate(R.layout.receivemessage_layout, parent, false);
                }
                else {
                    convertView = LayoutInflater.from(getContext()).inflate(R.layout.sentmessage_layout, parent, false);
                }
                TextView testMessage = convertView.findViewById(R.id.textMessage);
                testMessage.setText(actualMessage.getMessageText());
            }

            return convertView;
        }
    }
}