package aa.se.com.hospitalmanagement;

import android.graphics.Typeface;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;

import com.mikepenz.iconics.context.IconicsLayoutInflater;

public class SingleDocActivity extends AppCompatActivity {

    private Typeface yekanFont;
    private String name;
    private int image;
    private int rate;
    private TextView textViewName;
    private TextView textViewDes;
    private ImageView imageView;
    private RatingBar ratingBar;
    private String completeDes;


    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_single_doc);


        yekanFont = Typeface.createFromAsset(getAssets(), "fonts/b_yekan.ttf");
        name = getIntent().getStringExtra("NAME");
        image = getIntent().getIntExtra("IMAGE", R.drawable.ic_doc);
        completeDes = getIntent().getStringExtra("COMDES");
        rate = getIntent().getIntExtra("RATE", 0);

        textViewName = (TextView) findViewById(R.id.text_doc);
        textViewDes = (TextView) findViewById(R.id.text_doc_des);
        imageView = (ImageView) findViewById(R.id.image_doc);
        ratingBar = (RatingBar) findViewById(R.id.ratingBar);

        textViewName.setTypeface(yekanFont);
        textViewDes.setTypeface(yekanFont);
        textViewName.setText(name);
        textViewDes.setText(completeDes);
        imageView.setImageResource(image);
        ratingBar.setRating(rate);

        textViewDes.animate()
                .alpha(1).setDuration(1500);
    }

}
