package com.copyalipaypassword.cc;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.Toast;
import butterknife.BindView;
import butterknife.ButterKnife;

public class MainActivity extends AppCompatActivity {

    @BindView(R.id.psdEditText)
    PasswordEditText psdEditText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        psdEditText.SetOnTextEndListener(new PasswordEditText.OnTextEndListener() {
            @Override
            public void onTextEndListener(String string) {
                Toast.makeText(MainActivity.this, "输入完毕输出是" + string, Toast.LENGTH_SHORT).show();
            }
        });
    }

}
