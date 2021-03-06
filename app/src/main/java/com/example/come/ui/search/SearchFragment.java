package com.example.come.ui.search;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.res.Resources;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;
import androidx.core.content.res.ResourcesCompat;
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
import android.widget.Toast;

import com.example.come.CurrentUser;
import com.example.come.R;
import com.example.come.db.RoomDB;
import com.example.come.db.User;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;

public class SearchFragment extends Fragment {
    ListView listView;
    String searchKey;
    RoomDB db;
    List<User> allUsers, filteredUsers;
    View view;

    @RequiresApi(api = Build.VERSION_CODES.N)
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.search_fragment, container, false);
        listView = view.findViewById(R.id.search_list);

        db = RoomDB.getInstance(getContext());
        allUsers = db.UserDao().getAllUsers();

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

            // Need to remove file extension for this to work
            String imgFilepath = users.get(position).getProfilePhoto();
            String uri = "@drawable/" + imgFilepath.substring(0, imgFilepath.indexOf('.'));
            int imageResource =
                    getResources().getIdentifier(uri, null, context.getPackageName());

            photo.setImageResource(imageResource);
            username.setText("@" + users.get(position).getUserName());
            btn.setText(follows((String) username.getText()) ? "Following" : "Follow");

            btn.setOnClickListener(view -> {
                String current = (String) btn.getText();
                if(current.equals("Follow")){
                    btn.setText("Following");
                    followUser((String) username.getText());
                } else{
                    btn.setText("Follow");
                    unfollowUser((String) username.getText());
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
        List<User> usersToSearch = removeUserFromListByUsername(allUsers,
                ((CurrentUser) getActivity().getApplication()).getUsername());
        if(key.equals("@all")) return usersToSearch;
        if(!key.equals("")){
            for(User user: usersToSearch){
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

    /**
     * Removes a user from a list of users by using the username as the key
     * @param lst - list to explore
     * @param username - username to search for
     * @return returns a new list of users without the user with provided username
     */
    private List<User> removeUserFromListByUsername(List<User> lst, String username){
        for(int i = 0; i < lst.size(); ++i){
            if(lst.get(i).getUserName().equals(username)){
                lst.remove(i);
            }
        }
        return lst;
    }

    private void followUser(String userToBeFollowed){
        ((CurrentUser) getActivity().getApplication()).follow(userToBeFollowed);
        Toast.makeText(view.getContext(),  "You followed " + userToBeFollowed, Toast.LENGTH_SHORT).show();
    }

    private void unfollowUser(String userToBeFollowed){
        ((CurrentUser) getActivity().getApplication()).unfollow(userToBeFollowed);
        Toast.makeText(view.getContext(), "You unfollowed " + userToBeFollowed, Toast.LENGTH_SHORT).show();
    }

    // Returns true if user follows user2. For now lets make it random
    private boolean follows(String user){
        return ((CurrentUser) getActivity().getApplication()).follows(user);
    }
}