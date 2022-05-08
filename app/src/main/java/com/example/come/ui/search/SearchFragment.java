package com.example.come.ui.search;

import android.annotation.SuppressLint;
import android.content.Context;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
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
import java.util.Comparator;
import java.util.List;

public class SearchFragment extends Fragment {
    ListView listView;
    String searchKey;
    RoomDB db;
    List<User> allUsers;
    List<User> filteredUsers = new ArrayList<>();

    public static SearchFragment newInstance() {
        return new SearchFragment();
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.search_fragment, container, false);
        listView = view.findViewById(R.id.search_list);

        // TODO: FIX DB SO THAT getAllUsers() actually returns all users and then remove this
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

            // TODO: replace this with list of photos
            username.setText(users.get(position).getUserName());
            photo.setImageResource(R.drawable.profile_photo);

            btn.setOnClickListener(view -> {
                String current = (String) btn.getText();
                String newStr = current.equals("Follow") ? "Following" : "Follow";
                btn.setText(newStr);

                // TODO: UPDATE THIS SO THAT IT CHANGES THE FOLLOWER TABLE TO FOLLOW THAT PERSON
            });

            return row;
        }
    }

    private List<User> filterUsers(String key){
        List<User> ret = new ArrayList<>();
        if(!key.equals("")){
            for(User user: allUsers){
                if(user.getUserName().toLowerCase().contains(key.toLowerCase())){
                    ret.add(user);
                }
            }
        }

        // TODO: Add sort that prioritizes key being at the beginning
        return ret;
    }
}