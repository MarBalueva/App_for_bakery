package com.example.app_for_bakery.repository;

import com.example.app_for_bakery.model.MUserProfile;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class UserRepository {
    private List<MUserProfile> all_users;

    public UserRepository() {
        if (all_users == null)
            all_users = new ArrayList<>();
        addUser(new MUserProfile("mashabalueva2002@gmail.com", "123456", "Мария"));
    }

    public void addUser(MUserProfile mUserProfile){
        all_users.add(mUserProfile);
    }

    public void deleteUser(String email){
        Iterator <MUserProfile> iterator = all_users.iterator();
        while (iterator.hasNext()){
            MUserProfile next_user = iterator.next();
            if (next_user.getEmail() != null && next_user.getEmail().equals(email))
                iterator.remove();
        }
    }

    public MUserProfile getUserByLoginAndPassword(String email, String password){
        for (MUserProfile next_user : all_users)
            if (next_user.getPassword() != null && next_user.getEmail() != null
                    && next_user.getEmail().equals(email)
                    && next_user.getPassword().equals(password))
                return next_user;
        return null;
    }

    public boolean checkUser(String email, String password){
        for (MUserProfile next_user : all_users)
            if (next_user.getPassword() != null && next_user.getEmail() != null
                    && next_user.getEmail().equals(email)
                    && next_user.getPassword().equals(password))
                return true;
        return false;
    }
}
