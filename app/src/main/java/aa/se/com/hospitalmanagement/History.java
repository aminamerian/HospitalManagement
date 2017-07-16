package aa.se.com.hospitalmanagement;

import android.graphics.Typeface;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

public class History extends AppCompatActivity {

    private TextView textView;
    private PrefManager prefManager;
    private int reservationNumber;
    private String reservation = "";
    private Typeface yekanFont;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_history);

        yekanFont = Typeface.createFromAsset(getAssets(), "fonts/b_yekan.ttf");
        textView = (TextView) findViewById(R.id.textView_history);
        prefManager = new PrefManager(this);

        textView.setTypeface(yekanFont);

        reservationNumber = prefManager.getReservationNumber();
        for (int i = reservationNumber; i > 0; i--) {
            reservation +=
                    prefManager.getDoctorName(i) + " \n" +
                            prefManager.getReservationTime(i) + " " +
                            prefManager.getReservationDay(i) + " " +
                            prefManager.getReservationDayOfMonth(i) + " " +
                            prefManager.getReservationMonth(i) + "\n\n\n";
        }
        if (reservationNumber > 0) {
            textView.setText( reservation);
        }
    }
}
