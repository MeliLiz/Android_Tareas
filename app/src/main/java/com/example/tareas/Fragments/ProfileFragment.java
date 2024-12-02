package com.example.tareas.Fragments;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.example.tareas.R;
import com.example.tareas.Model.User;
import com.example.tareas.DB.UserBDManager;
import com.example.tareas.Session.UserSession;

public class ProfileFragment extends Fragment {

    private TextView name;
    private TextView email;
    private TextView username;
    private TextView password;
    private Button guardar;
    private ImageView editUser;
    private ImageView editEmail;
    private ImageView editName;
    private ImageView editPass;
    private int updating = 0;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.info_user_fragment, container, false);

        if(!UserSession.getInstance().isLoggedIn()){
            return null;
        }

        int userId = UserSession.getInstance().getUserId();

        UserBDManager userBdd = new UserBDManager(requireContext());
        userBdd.openForRead();
        User user = userBdd.getUser(userId);
        userBdd.close();

        if(user == null){
            return null;
        }


        guardar = view.findViewById(R.id.editar);
        updateButtonState();
        name = view.findViewById(R.id.te_user);
        email = view.findViewById(R.id.te_email);
        username = view.findViewById(R.id.te_name);
        password = view.findViewById(R.id.te_pass);

        name.setText(user.getName());
        email.setText(user.getEmail());
        username.setText(user.getUsername());
        password.setText(user.getPassword());

        editUser = view.findViewById(R.id.editIconUser);
        editEmail = view.findViewById(R.id.editIconEmail);
        editName = view.findViewById(R.id.editIconName);
        editPass = view.findViewById(R.id.editIconPass);

        editUser.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(name.isEnabled()){
                    name.setText(user.getName());
                    name.setEnabled(false);
                    updating -= 1;
                }else{
                    name.setEnabled(true);
                    updating += 1;
                }
                updateButtonState();
            }
        });

        editEmail.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(email.isEnabled()){
                    email.setText(user.getEmail());
                    email.setEnabled(false);
                    updating -= 1;
                }else{
                    email.setEnabled(true);
                    updating += 1;
                }
                updateButtonState();
            }
        });

        editName.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(username.isEnabled()){
                    username.setText(user.getUsername());
                    username.setEnabled(false);
                    updating -= 1;
                }else{
                    username.setEnabled(true);
                    updating += 1;
                }
                updateButtonState();
            }
        });

        editPass.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(password.isEnabled()){
                    password.setText(user.getPassword());
                    password.setEnabled(false);
                    updating -= 1;
                }else{
                    password.setEnabled(true);
                    updating += 1;
                }
                updateButtonState();
            }
        });

        guardar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (updating != 0){
                    User modified = new User(
                            name.getText().toString(),
                            email.getText().toString(),
                            username.getText().toString(),
                            password.getText().toString()
                    );
                    int id = UserSession.getInstance().getUserId();

                    userBdd.openForWrite();
                    Boolean res = userBdd.updateUser(id, modified);
                    if(res){
                        Log.d("ProfileFragment", "User updated");
                    }else{
                        Log.d("ProfileFragment", "User not updated");
                    }
                    userBdd.close();

                    name.setEnabled(false);
                    email.setEnabled(false);
                    username.setEnabled(false);
                    password.setEnabled(false);
                }
            }
        });
        return view;
    }

    private void updateButtonState() {
        if (updating == 0) {
            guardar.setEnabled(false);
        } else {
            guardar.setEnabled(true);
        }
    }
}
