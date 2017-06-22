package com.app.socialmedialogin;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;

import com.facebook.drawee.view.SimpleDraweeView;

import java.text.SimpleDateFormat;
import java.util.Locale;

public class LoggedinActivity extends AppCompatActivity {

    TextView tvName, tvEmail, tvNickname, tvDate;
    SimpleDraweeView ivPicture;
    private String name, email, id, nickname, picture, date, token;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_logggedin);
        initviews();
        Bundle bundle = getIntent().getExtras();
        if (bundle.containsKey("name"))
            name = bundle.getString("name");
        if (bundle.containsKey("email"))
            email = bundle.getString("email");
        if (bundle.containsKey("id"))
            id = bundle.getString("id");
        if (bundle.containsKey("nickname"))
            nickname = bundle.getString("nickname");
        if (bundle.containsKey("picture"))
            picture = bundle.getString("picture");
        if (bundle.containsKey("date"))
            date = bundle.getString("date");
        if (bundle.containsKey("token"))
            token = bundle.getString("token");

        setValues(name, email, nickname, picture, date);
    }

    private void initviews() {
        tvName = (TextView) findViewById(R.id.tv_name);
        tvEmail = (TextView) findViewById(R.id.tv_email);
        tvNickname = (TextView) findViewById(R.id.tv_nickname);
        tvDate = (TextView) findViewById(R.id.tv_date);
        ivPicture = (SimpleDraweeView) findViewById(R.id.iv_picture);
    }

    private void setValues(String name, String email, String nickname, String picture, String date) {
        if (name != null) tvName.setText(name);
        if (email != null) tvEmail.setText(email);
        if (nickname != null) tvNickname.setText(nickname);
        if (date != null) tvDate.setText(formatDate(date));
        if (picture != null) ivPicture.setImageURI(picture);
    }

    private String formatDate(String date) {
        SimpleDateFormat dateFormat = new SimpleDateFormat("MMMM yy", Locale.getDefault());
        return dateFormat.format(date);
    }
}