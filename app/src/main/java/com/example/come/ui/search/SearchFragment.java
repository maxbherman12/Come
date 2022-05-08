package com.example.come.ui.search;

import android.annotation.SuppressLint;
import android.content.Context;
import android.os.Build;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;
import androidx.fragment.app.Fragment;

import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import com.example.come.R;
import com.example.come.db.RoomDB;
import com.example.come.db.User;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;

public class SearchFragment extends Fragment {
    ListView listView;
    String searchKey;
    RoomDB db;
    List<User> allUsers, filteredUsers;

    @RequiresApi(api = Build.VERSION_CODES.N)
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.search_fragment, container, false);
        listView = view.findViewById(R.id.search_list);

//        TODO: FIX DB SO THAT getAllUsers() actually returns all users and then remove this
//        db = RoomDB.getInstance(getContext());
//        allUsers = db.UserDao().getAllUsers();
        allUsers = Arrays.asList(new User("come", "come"),
                new User("gorka", "come"),
                new User("max", "come"),
                new User("sören", "come"),
                new User("michael", "come"),
                new User("theEater", "come"),
                new User("burgerLover", "come"));

        searchKey = ""; // list should contain no users when nothing in search bar
        filteredUsers = filterUsers(searchKey);
        MyAdapter adapter = new MyAdapter(view.getContext(), filteredUsers);
        listView.setAdapter(adapter);

        EditText searchBar = view.findViewById(R.id.search_edit_text);
        searchBar.addTextChangedListener(new TextWatcher() {
            @RequiresApi(api = Build.VERSION_CODES.N)
            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                searchKey = charSequence.toString();
                adapter.clear();
                adapter.addAll(filterUsers(searchKey));
            }

            @Override public void beforeTextChanged(CharSequence charSeq, int i, int i1, int i2) {}
            @Override public void afterTextChanged(Editable editable) {}
        });

        return view;
    }

    class MyAdapter extends ArrayAdapter<User> {
        Context context;
        List<User> users;

        MyAdapter(Context c, List<User> _users){
            super(c, R.layout.search_row, R.id.search_username, _users);
            this.context = c;
            this.users = _users;
        }

        @SuppressLint("SetTextI18n")
        @NonNull
        @Override
        public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent ){
            LayoutInflater layoutInflater =
                    (LayoutInflater) this.context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            @SuppressLint("ViewHolder") View row =
                    layoutInflater.inflate(R.layout.search_row, parent, false);

            ImageView photo = row.findViewById(R.id.search_profile_photo);
            TextView username = row.findViewById(R.id.search_username);
            Button btn = row.findViewById(R.id.search_follow_btn);

            // TODO: replace this with getting the photo value
            photo.setImageResource(R.drawable.profile_photo);
            username.setText(users.get(position).getUserName());
            btn.setText(follows("self", (String) username.getText()) ? "Following" : "Follow");

            btn.setOnClickListener(view -> {
                String current = (String) btn.getText();
                if(current.equals("Follow")){
                    btn.setText("Following");
                    followUser("self", (String) username.getText());
                } else{
                    btn.setText("Follow");
                    unfollowUser("self", (String) username.getText());
                }
            });

            return row;
        }
    }

    /**
     * Filters list of all users to only include those whose username contains the search key
     * After filtering the list, it is then sorted to prefer options that begin with the key
     * @param key - value typed into the search bar
     * @return sorted and filtered list
     */
    @RequiresApi(api = Build.VERSION_CODES.N)
    private List<User> filterUsers(String key){
        List<User> ret = new ArrayList<>();
        if(!key.equals("")){
            for(User user: allUsers){
                if(user.getUserName().toLowerCase().contains(key.toLowerCase())){
                    ret.add(user);
                }
            }

            // Sort the list to prefer usernames that begin with the search key
            ret.sort((user1, user2) -> user1.getUserName().startsWith(key) ?
                    (user2.getUserName().startsWith(key) ? 0 : -1) : 1);
        }

        return ret;
    }

    // Temporary follow method to be later replaced
    private void followUser(String user, String userToBeFollowed){
        Log.v(null, user + " followed " + userToBeFollowed);
    }

    // Temporary unfollow method to be later replaced
    private void unfollowUser(String user, String userToBeFollowed){
        Log.v(null, user + " unfollowed " + userToBeFollowed);
    }

    // Temporary method for checking if one user follows another to be later replaced
    // Returns true if user follows user2. For now lets make it random
    private boolean follows(String user, String user2){
        return new Random().nextBoolean();
    }
}