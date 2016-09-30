package com.nyx_itech.k13;

import android.app.Fragment;
import android.app.FragmentManager;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v4.content.LocalBroadcastManager;
import android.util.Log;
import android.view.View;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.GooglePlayServicesUtil;
import com.google.firebase.iid.FirebaseInstanceId;
import com.nyx_itech.k13.adapters.HomeChat;
import com.nyx_itech.k13.adapters.HomeChatListAdapter;
import com.nyx_itech.k13.database.Chat;
import com.nyx_itech.k13.database.DatabaseHandler;
import com.nyx_itech.k13.database.Guru;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {

    /* Type: View Element
    * Description: Home Page ListView
    * ---- Layout: res/layout/fragment_home.xml
    * */
    public ListView homeChatsList;
    /* Class: HomeChatListAdapter
     * Package: com.atouchsimo.whatsup.adapters
     * */
    public HomeChatListAdapter adapter;
    /* Home chats array
     * Class: HomeChat
     * Package: com.atouchsimo.whatsup.adapters
     * */
    public List<HomeChat> chats = new ArrayList<HomeChat>();
    /* ListView Items
     * */
    public String[] names, dates, images, hints;
    public int[] newCounts;

    private BroadcastReceiver mRegistrationBroadcastReceiver;
    private Fragment mFragment = null;
    private FragmentManager mFragmentManager;
    private ArrayList<ChatRoom> chatRoomArrayList;
    DatabaseHandler db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);

        //******************************* NYX START CODE!!!11! **********************************
        //DATABASE SQLITE
        db = new DatabaseHandler(this);
//        db.addGuru(new Guru(1, "munir", "matematika", "1.jpg"));
//        db.addGuru(new Guru(2, "Dion", "Sejarah", "2.jpg"));
//        db.addChat(new Chat("1", "testing pesan", "2", "2016", "13:00", "1"));
//        db.addChat(new Chat("1", "ini pesan kedua", "2", "2016", "13:00", "0"));
//        db.addChat(new Chat("2", "aduh pean ini kekirim ga ya", "2", "2016", "13:00", "1"));
        homeChatsList = (ListView) findViewById(R.id.homeChatsList);
        /* Class: HomeChatListAdapter
         * */

        adapter = new HomeChatListAdapter(this, chats);
        homeChatsList.setAdapter(adapter);
        homeChatsList.setOnItemClickListener(new AdapterView.OnItemClickListener() {

            @Override
            public void onItemClick(AdapterView<?> arg0, View view, int position,
                                    long arg3) {
                /* when an item Clicked we run ChatActivity
                 * And we put an Extra "position"
				 * */
                Intent i = new Intent(MainActivity.this, ChatActivity.class);
                String guruID = ((TextView) view.findViewById(R.id.chatListItemGuru_id)).getText().toString();
                i.putExtra("guruID", guruID);
                startActivity(i);
            }
        });


        mRegistrationBroadcastReceiver = new BroadcastReceiver() {
            @Override
            public void onReceive(Context context, Intent intent) {
                //Check type of intent filter
                Log.d("mytoken", intent.getAction());
                if (intent.getAction().equals("pushnotification")) {
                    updateData();
                } else if (intent.getAction().equals("tokenReceiver")) {
                    Toast.makeText(MainActivity.this, intent.getStringExtra("token"), Toast.LENGTH_SHORT).show();
                    Log.d("token", "ini tokmen : " + intent.getStringExtra("token"));
                }
            }
        };


        //Check status of Google play service in device
        int resultCode = GooglePlayServicesUtil.isGooglePlayServicesAvailable(getApplicationContext());
        if (ConnectionResult.SUCCESS != resultCode) {
            //Check type of error
            if (GooglePlayServicesUtil.isUserRecoverableError(resultCode)) {
                Toast.makeText(getApplicationContext(), "Google Play Service is not install/enabled in this device!", Toast.LENGTH_LONG).show();
                //So notification
                GooglePlayServicesUtil.showErrorNotification(resultCode, getApplicationContext());
            } else {
                Toast.makeText(getApplicationContext(), "This device does not support for Google Play Service!", Toast.LENGTH_LONG).show();
            }
        }

        updateData();


//        mFragment = new HomeFragment();
//        mFragmentManager = getFragmentManager();
//        mFragmentManager.beginTransaction()
//                .replace(R.id.frameContainer, mFragment).commit();


        FirebaseInstanceId.getInstance().getToken();
    }

    private void updateData() {
        List<Guru> gurus = db.getGuru();
        chats.clear();
        for (Guru guru : gurus) {
            Chat lastChat = db.getLast(String.valueOf(guru.get_id()));
            HomeChat chat = new HomeChat();
            chat.setName(guru.get_nama());
            chat.setImage("user_image_6");
            chat.setCount(0);
            chat.setGuruID(lastChat.get_guru_id());
            chat.setDate(lastChat.get_date());
            chat.setHint(lastChat.get_pesan());
            chats.add(chat);

        }
        adapter.notifyDataSetChanged();
    }

    @Override
    protected void onResume() {
        super.onResume();
        LocalBroadcastManager.getInstance(this).registerReceiver(mRegistrationBroadcastReceiver,
                new IntentFilter("pushnotification"));
        LocalBroadcastManager.getInstance(this).registerReceiver(mRegistrationBroadcastReceiver,
                new IntentFilter("tokenReceiver"));
    }


    /**
     * Updates the chat list unread count and the last message
     */
    private void updateRow(int chatRoomId, String message) {
        View v = homeChatsList.getChildAt(chatRoomId -
                homeChatsList.getFirstVisiblePosition());

        if (v == null)
            return;

        TextView someText = (TextView) v.findViewById(R.id.chatListItemHints);
        someText.setText(message);

    }

    @Override
    protected void onPause() {
        super.onPause();
        Log.w("MainActivity", "onPause");
        LocalBroadcastManager.getInstance(this).unregisterReceiver(mRegistrationBroadcastReceiver);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();

    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.nav_camera) {
            // Handle the camera action
        } else if (id == R.id.nav_gallery) {

        } else if (id == R.id.nav_slideshow) {

        } else if (id == R.id.nav_manage) {

        } else if (id == R.id.nav_share) {

        } else if (id == R.id.nav_send) {

        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }
}
