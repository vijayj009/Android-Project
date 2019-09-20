package com.barclays.finambulance.services;

public class GetUserDetailsImpl implements GetUserDetails {


    @Override
    public String fetchUser(String username, String password){

        String fullName = "Nishigandha Mhaisne";
        if(null != fullName){
            return fullName;
        }else {
            return "Invalid_User";
        }

    }
}
