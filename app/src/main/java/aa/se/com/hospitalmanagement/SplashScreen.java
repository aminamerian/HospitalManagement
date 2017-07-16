package aa.se.com.hospitalmanagement;

import android.content.Intent;
import android.graphics.Typeface;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

public class SplashScreen extends AppCompatActivity {

    private Thread splashTread;
    private Typeface yekanFont;
    private TextView textView1, textView2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash_screen);

        yekanFont = Typeface.createFromAsset(getAssets(), "fonts/b_yekan.ttf");
        textView1 = (TextView) findViewById(R.id.textView_1);
        textView2 = (TextView) findViewById(R.id.textView_2);

        textView1.setTypeface(yekanFont);
        textView2.setTypeface(yekanFont);

        splashTread = new Thread() {
            @Override
            public void run() {
                try {
                    sleep(1000);
                    Intent intent = new Intent(SplashScreen.this, MainActivity.class);
                    intent.setFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION);
                    startActivity(intent);
                } catch (InterruptedException e) {
                } finally {
                    SplashScreen.this.finish();
                }

            }
        };
        splashTread.start();
    }
}
