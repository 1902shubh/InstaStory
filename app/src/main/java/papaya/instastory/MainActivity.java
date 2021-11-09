package papaya.instastory;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

import papaya.instastory.adapter.ProfileAdapter;
import papaya.instastory.adapter.StoryAdapter;
import papaya.instastory.adapter.UserListInterface;
import papayacoders.instastory.Stories;
import papayacoders.instastory.models.ItemModel;
import papayacoders.instastory.models.NodeModel;
import papayacoders.instastory.models.TrayModel;

public class MainActivity extends AppCompatActivity implements UserListInterface {

    RecyclerView recyclerViewUser;
    ProfileAdapter profileAdapter;
    RecyclerView recyclerViewStories;
    StoryAdapter storyAdapter;
    private TextView loginBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        recyclerViewUser = findViewById(R.id.recyclerViewUser);
        recyclerViewStories = findViewById(R.id.recyclerViewStories);
        loginBtn = findViewById(R.id.textViewLogin);


        if (Stories.isLogin(this)) {
            loginBtn.setText("Logout");
        } else loginBtn.setText("Login");

        loginBtn.setOnClickListener(view -> {
            if (loginBtn.getText().equals("Logout")) {
                loginBtn.setText("Login");
                Stories.logout(MainActivity.this);
            } else {
                loginBtn.setText("Logout");
                Stories.login(MainActivity.this);
            }
        });

        Stories.users(this);

        Stories.storyList.observe(this, itemModels -> {

            storyAdapter = new StoryAdapter(MainActivity.this, itemModels);
            recyclerViewStories.setAdapter(storyAdapter);

            for (ItemModel item : itemModels) {
                Log.d("StatusInsta", "url : " + item.getImageversions2().getCandidates().get(0).getUrl());


            }
        });

        Stories.list.observe(this, trayModels -> {

            if (trayModels == null) {
                recyclerViewUser.setVisibility(View.GONE);
                recyclerViewStories.setVisibility(View.GONE);
                return;
            }
            profileAdapter = new ProfileAdapter(MainActivity.this, trayModels, MainActivity.this);
            recyclerViewUser.setAdapter(profileAdapter);

//            for (TrayModel item : trayModels) {
//                Log.d("StatusInsta", "name: " + item.getUser().getFullname() +
//                        "\nimage : " + item.getUser().getProfilepicurl());
//
//            }
        });

    }


    @Override
    public void FacebookUserListClick(int i, NodeModel nodeModel) {

    }

    @Override
    public void FacebookUserListClick(int i, TrayModel trayModel) {
        Stories.getStories(MainActivity.this, String.valueOf(trayModel.getUser().getPk()));

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (Stories.isLogin(this))
            Stories.users(this);

    }
}