package aa.se.com.hospitalmanagement;

import android.content.Intent;
import android.graphics.Typeface;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.mikepenz.iconics.utils.Utils;

public class LoginActivity extends AppCompatActivity {

    private Button button;
    private EditText phoneNumber, authCode;
    private PrefManager prefManager;
    private Typeface yekanFont;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        prefManager = new PrefManager(this);
        yekanFont = Typeface.createFromAsset(getAssets(), "fonts/b_yekan.ttf");
        phoneNumber = (EditText) findViewById(R.id.phoneNumber);
        authCode = (EditText) findViewById(R.id.authCode);
        button = (Button) findViewById(R.id.button);

        phoneNumber.setTypeface(yekanFont);
        authCode.setTypeface(yekanFont);
        button.setTypeface(yekanFont);

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (TextUtils.isEmpty(phoneNumber.getText())) {
                    Toast.makeText(LoginActivity.this, "شماره تلفن را وارد کنید!", Toast.LENGTH_SHORT).show();
                } else if (TextUtils.isEmpty(authCode.getText())) {
                    Toast.makeText(LoginActivity.this, "کد فعالسازی را وارد کنید!", Toast.LENGTH_SHORT).show();
                } else {
                    prefManager.setUserHasBeenAuthenticated(true);
                    prefManager.setUserPhoneNumber(phoneNumber.getText().toString());
                    startActivity(new Intent(LoginActivity.this, MainActivity.class));
                    Toast.makeText(LoginActivity.this, "ثبت شد!", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }


    @Override
    public void onBackPressed() {

    }
}
