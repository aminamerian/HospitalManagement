package aa.se.com.hospitalmanagement;

import android.graphics.Typeface;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import com.mikepenz.iconics.context.IconicsLayoutInflater;

public class SingleDocActivity extends AppCompatActivity {

    private Typeface yekanFont;
    private String name;
    private TextView textViewName;
    private ImageView imageView;


    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_single_doc);


        yekanFont = Typeface.createFromAsset(getAssets(), "fonts/b_yekan.ttf");
        name = getIntent().getStringExtra("NAME");
        int image = getIntent().getIntExtra("IMAGE", R.drawable.ic_doc);

        textViewName = (TextView) findViewById(R.id.text_doc);
        imageView = (ImageView) findViewById(R.id.image_doc);

        textViewName.setTypeface(yekanFont);
        textViewName.setText(name);
        imageView.setImageResource(image);

    }


}
