package com.example.chatnew;

import android.os.Bundle;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    private static int MAX_MSG_LENGH = 150;

    Button but_send;
    EditText send_text;
    ArrayList<String> messenges = new ArrayList<>();
    RecyclerView msg_recycle;

    FirebaseDatabase database = FirebaseDatabase.getInstance();
    DatabaseReference myRef = database.getReference("messages");

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        send_text = findViewById(R.id.text_input);
        msg_recycle = findViewById(R.id.msg_recycle);
        but_send = findViewById(R.id.send_messege_b);
        final DataAdapter dataAdapter = new DataAdapter(this, messenges);
        msg_recycle.setLayoutManager(new LinearLayoutManager(this));

        msg_recycle.setAdapter(dataAdapter);
        but_send.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String msg = send_text.getText().toString();
                if(msg.equals("")){
                    Toast.makeText(getApplicationContext(),"Введите сообщение!",Toast.LENGTH_SHORT).show();

                    return;
                }
                if(msg.length()>MAX_MSG_LENGH){
                    Toast.makeText(getApplicationContext(),"Превышена длина сообщений!",Toast.LENGTH_SHORT).show();
                    return;
                }
                myRef.push().setValue(msg);
                send_text.setText("");

            }
        });
        myRef.addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {
                String msg = dataSnapshot.getValue(String.class);
                messenges.add(msg);
                dataAdapter.notifyDataSetChanged();
                msg_recycle.smoothScrollToPosition(messenges.size());
            }

            @Override
            public void onChildChanged(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {

            }

            @Override
            public void onChildRemoved(@NonNull DataSnapshot dataSnapshot) {

            }

            @Override
            public void onChildMoved(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {

            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });

    }


}
