package com.elegion.test.behancer.data.model.user;

import com.google.gson.annotations.SerializedName;

public class UserResponse {

    @SerializedName("user")
    private User mUser;

    public User getUser() {
        return mUser;
    }

    public void setUser(User user) {
        mUser = user;
    }
}
