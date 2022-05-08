package com.example.come.ui.search;

import androidx.lifecycle.ViewModelProvider;

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
import android.view.OnReceiveContentListener;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import com.example.come.R;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Locale;

public class SearchFragment extends Fragment {
    ListView listView;
    List<String> allUsers = new ArrayList<>();
    List<String> filteredUsers = new ArrayList<>();
    boolean tmpFollowed[]= {false, true, false, false};
    int tmpImgs[] = {1,2,3,4};
    String searchKey;

    public static SearchFragment newInstance() {
        return new SearchFragment();
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.search_fragment, container, false);
        listView = view.findViewById(R.id.search_list);

        // TODO: CHANGE TO READING ALL USERS FROM THE DB
        String[] tmpUsers  = {"@maxbherman12", "@carmocarmo", "@charlieThorne", "@comeApp"};
        allUsers.addAll(Arrays.asList(tmpUsers));

        searchKey = ""; // list should contain no users when nothing in search bar
        filteredUsers = filterUsers(searchKey);

        MyAdapter adapter = new MyAdapter(view.getContext(), filteredUsers, tmpImgs, tmpFollowed);
        listView.setAdapter(adapter);

        EditText searchBar = view.findViewById(R.id.search_edit_text);
        searchBar.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                searchKey = charSequence.toString();
                filteredUsers.clear();
                Log.v(null, filteredUsers.toString());
                filteredUsers.addAll(filterUsers(searchKey));
                Log.v(null, filteredUsers.toString());
                Log.v(null, allUsers.toString());
                adapter.notifyDataSetChanged();
            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });


        return view;
    }

    class MyAdapter extends ArrayAdapter<String> {
        Context context;
        List<String> usernames;
        int images[];
        boolean followed[];

        MyAdapter(Context c, List<String> _users, int imgs[], boolean follows[]){
            super(c, R.layout.search_row, R.id.search_username, _users);
            this.context = c;
            this.usernames = _users;
            this.images = imgs;
            this.followed = follows;
        }

        @NonNull
        @Override
        public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent ){
            LayoutInflater layoutInflater = (LayoutInflater) this.context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            @SuppressLint("ViewHolder")
            View row = layoutInflater.inflate(R.layout.search_row, parent, false);
            ImageView photo = row.findViewById(R.id.search_profile_photo);
            TextView username = row.findViewById(R.id.search_username);
            Button btn = row.findViewById(R.id.search_follow_btn);

            // TODO: replace this with list of photos
            photo.setImageResource(R.drawable.profile_photo);
            username.setText(allUsers.get(position));

            btn.setOnClickListener(view -> {
                String current = (String) btn.getText();
                String newStr = current.equals("Follow") ? "Following" : "Follow";
                btn.setText(newStr);

                // TODO: UPDATE THIS SO THAT IT CHANGES THE FOLLOWER TABLE TO FOLLOW THAT PERSON
            });

            return row;
        }
    }

    private List<String> filterUsers(String key){
        List<String> ret = new ArrayList<>();
        if(!key.equals("")){
            for(String user: allUsers){
                if(user.toLowerCase().contains(key.toLowerCase())){
                    ret.add(user);
                }
            }
        }
        return ret;
    }
}