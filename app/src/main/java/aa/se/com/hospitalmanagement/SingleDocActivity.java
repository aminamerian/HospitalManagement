package aa.se.com.hospitalmanagement;

import android.graphics.Typeface;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.widget.TextView;

public class SingleDocActivity extends AppCompatActivity {

    private Typeface yekanFont;
    private String name;
    private TextView Tname;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_single_doc);


        yekanFont = Typeface.createFromAsset(getAssets(), "fonts/b_yekan.ttf");

        name= getIntent().getStringExtra("NAME");
        Tname= (TextView) findViewById(R.id.text_doc);

        Tname.setText(name);
        Tname.setTypeface(yekanFont);

    }
}
