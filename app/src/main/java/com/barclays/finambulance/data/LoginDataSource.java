package com.barclays.finambulance.data;

import com.barclays.finambulance.data.model.LoggedInUser;
import com.barclays.finambulance.services.GetUserDetailsImpl;

import java.io.IOException;

/**
 * Class that handles authentication w/ login credentials and retrieves user information.
 */
public class LoginDataSource {

    public Result<LoggedInUser> login(String username, String password) {

        try {
            // TODO: handle loggedInUser authentication

            GetUserDetailsImpl getUserDetails = new GetUserDetailsImpl();
            LoggedInUser loginUser = new LoggedInUser(java.util.UUID.randomUUID().toString(),getUserDetails.fetchUser(username,password));
            /* LoggedInUser fakeUser =
                    new LoggedInUser(
                            java.util.UUID.randomUUID().toString(),
                            "Jane Doe");*/
            return new Result.Success<>(loginUser);
        } catch (Exception e) {
            return new Result.Error(new IOException("Error logging in", e));
        }
    }

    public void logout() {
        // TODO: revoke authentication
    }
}
