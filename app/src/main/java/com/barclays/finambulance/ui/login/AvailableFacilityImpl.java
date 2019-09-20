package com.barclays.finambulance.ui.login;

import android.annotation.SuppressLint;
import android.annotation.TargetApi;
import android.content.res.Resources;
import android.os.Build;
import android.os.Bundle;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.Button;
import android.widget.EditText;
import android.widget.PopupWindow;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.barclays.finambulance.R;

public class AvailableFacilityImpl extends AppCompatActivity {

    private PopupWindow popupWindow;
    private EditText Name;

    @Override
    public void onCreate(Bundle savedInstanceState) {super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_facility);

    }

    public void callPopup(View view) {

        LayoutInflater layoutInflater = (LayoutInflater) getBaseContext()
                .getSystemService(LAYOUT_INFLATER_SERVICE);

        View popupView = layoutInflater.inflate(R.layout.activity_facility, null);

        Resources resources = getResources();
        @SuppressLint("StringFormatMatches") String response = String.format(resources.getString(R.string.received_message,"NISHIGANDHA"));
        popupWindow = new PopupWindow(popupView,
                ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.MATCH_PARENT,
                true);

        popupWindow.setTouchable(true);
        popupWindow.setFocusable(true);

        popupWindow.showAtLocation(popupView, Gravity.CENTER, 0, 0);
        Name = (EditText) popupView.findViewById(R.id.invisible);

        ((Button) popupView.findViewById(R.id.invisible))
                .setOnClickListener(new View.OnClickListener() {

                    @TargetApi(Build.VERSION_CODES.GINGERBREAD)
                    public void onClick(View arg0) {
                        Toast.makeText(getApplicationContext(),
                                Name.getText().toString(), Toast.LENGTH_LONG).show();

                        popupWindow.dismiss();

                    }

                });

        ((Button) popupView.findViewById(R.id.invisible))
                .setOnClickListener(new View.OnClickListener() {

                    public void onClick(View arg0) {

                        popupWindow.dismiss();
                    }
                });

    }
}
