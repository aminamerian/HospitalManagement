package aa.se.com.hospitalmanagement;

import android.graphics.Typeface;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class Profile extends AppCompatActivity {

    private Typeface yekanFont;
    private PrefManager prefManager;
    private EditText name, lName, nNumber;
    private TextView nameLable, lNameLable, nNumberLable;
    private Button register;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);

        yekanFont = Typeface.createFromAsset(getAssets(), "fonts/b_yekan.ttf");
        prefManager = new PrefManager(this);

        name = (EditText) findViewById(R.id.editText_name);
        lName = (EditText) findViewById(R.id.editText_lname);
        nNumber = (EditText) findViewById(R.id.editText_nationalNumber);
        nameLable = (TextView) findViewById(R.id.textView_name);
        lNameLable = (TextView) findViewById(R.id.textView_lname);
        nNumberLable = (TextView) findViewById(R.id.textView_nationalNumber);
        register = (Button) findViewById(R.id.button_register);

        name.setTypeface(yekanFont);
        lName.setTypeface(yekanFont);
        nNumber.setTypeface(yekanFont);
        nameLable.setTypeface(yekanFont);
        lNameLable.setTypeface(yekanFont);
        nNumberLable.setTypeface(yekanFont);
        register.setTypeface(yekanFont);

        name.setText(prefManager.getUserName());
        lName.setText(prefManager.getUserLastName());
        nNumber.setText(prefManager.getUserNationalNumber());

        register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!name.getText().toString().isEmpty() &&
                        !lName.getText().toString().isEmpty() &&
                        !nNumber.getText().toString().isEmpty()) {

                    prefManager.setUserName(name.getText().toString());
                    prefManager.setUserLastName(lName.getText().toString());
                    prefManager.setUserNationalNumber(nNumber.getText().toString());
                    prefManager.setUserHasRegistered(true);
                    Toast.makeText(Profile.this, "ثبت شد!", Toast.LENGTH_SHORT).show();

                    onBackPressed();
                } else {
                    Toast.makeText(Profile.this, "تمامی فیلدها را پر کنید.", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
}
