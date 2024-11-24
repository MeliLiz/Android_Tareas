package com.example.tareas;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

public class ProfileFragment extends Fragment {

    private TextView name;
    private TextView email;
    private TextView username;
    private TextView password;
    private Button editar;

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
        } else{
            Log.d("ProfileFragment", user.toString());
        }

        name = view.findViewById(R.id.te_user);
        email = view.findViewById(R.id.te_email);
        username = view.findViewById(R.id.te_name);
        password = view.findViewById(R.id.te_pass);

        name.setText(user.getName());
        email.setText(user.getEmail());
        username.setText(user.getUsername());
        password.setText(user.getPassword());

        editar = view.findViewById(R.id.editar);
        editar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.d("ProfileFragment", "Editar perfil");
            }
        });

        return view;
    }
}
