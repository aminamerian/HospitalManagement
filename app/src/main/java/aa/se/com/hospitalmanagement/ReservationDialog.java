package aa.se.com.hospitalmanagement;

import android.app.AlertDialog;
import android.app.Dialog;
import android.graphics.Typeface;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.DialogFragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Date;

/**
 * Created by Amin on 10/03/2017.
 */

public class ReservationDialog extends DialogFragment {

    private RecyclerView recyclerView;
    private TimeOptions_Recyclerview_Adapter adapter;
    private DatePicker datePicker;
    private Button cancelButton, acceptButton;
    private Typeface yekanFont;
    private boolean dateSelected = false;
    private TextView textViewHeader, textViewHeader2, textViewHeader3;
    private RelativeLayout layoutHeader;
    long aDay = 86400000;  //milliseconds in a day
    private String[] dateToDisplay;
    private PrefManager prefManager;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        getDialog().requestWindowFeature(STYLE_NO_TITLE);
        setCancelable(false);

        return super.onCreateView(inflater, container, savedInstanceState);
    }

    @NonNull
    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        final AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        View view = getActivity().getLayoutInflater().inflate(R.layout.dialog_reservation, null);
        yekanFont = Typeface.createFromAsset(getActivity().getAssets(), "fonts/b_yekan.ttf");

        recyclerView = (RecyclerView) view.findViewById(R.id.recycler_time_options);
        datePicker = (DatePicker) view.findViewById(R.id.datePicker);
        layoutHeader = (RelativeLayout) view.findViewById(R.id.layout_header);
        textViewHeader = (TextView) view.findViewById(R.id.textView_header);
        textViewHeader2 = (TextView) view.findViewById(R.id.textView_header2);
        textViewHeader3 = (TextView) view.findViewById(R.id.textView_header3);
        cancelButton = (Button) view.findViewById(R.id.button_cancel);
        acceptButton = (Button) view.findViewById(R.id.button_accept);

        prefManager = new PrefManager(getContext());

        cancelButton.setTypeface(yekanFont);
        acceptButton.setTypeface(yekanFont);
        textViewHeader.setTypeface(yekanFont);
        textViewHeader2.setTypeface(yekanFont);
        textViewHeader3.setTypeface(yekanFont);

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            datePicker.setFirstDayOfWeek(7);
        }
        datePicker.setMinDate(System.currentTimeMillis() - 1000);
        datePicker.setMaxDate(System.currentTimeMillis() + 100 * aDay);

        acceptButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (dateSelected) {

                    dismiss();
                    prefManager.setReservationNumber(prefManager.getReservationNumber() + 1);
                    int i = prefManager.getReservationNumber();
                    prefManager.setReservationTime(adapter.getSelectedTime(), i);
                    prefManager.setReservationDay(dateToDisplay[0], i);
                    prefManager.setReservationDayOfMonth(dateToDisplay[1], i);
                    prefManager.setReservationMonth(dateToDisplay[2], i);
                    SingleDocActivity.writeDocNameToSP(i);
                    Toast.makeText(getContext(), "ثبت شد!", Toast.LENGTH_SHORT).show();
                } else {
                    dateSelected = true;
                    datePicker.setVisibility(View.GONE);
                    layoutHeader.setVisibility(View.VISIBLE);
                    acceptButton.setText("تایید");
                    dateToDisplay = getDateToDisplay(datePicker);
                    textViewHeader.setText(dateToDisplay[0]);
                    textViewHeader2.setText(dateToDisplay[1]);
                    textViewHeader3.setText(dateToDisplay[2]);
                    adapter = new TimeOptions_Recyclerview_Adapter(recyclerView.getContext(), yekanFont, getEnableTimes(datePicker));
                    recyclerView.setAdapter(adapter);
                    recyclerView.setHasFixedSize(true);
                    recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
                }
            }
        });

        cancelButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getDialog().dismiss();
            }
        });
        builder.setView(view);
        return builder.create();
    }

    public String[] getDateToDisplay(DatePicker dp) {
        Date date = new Date(dp.getYear(), dp.getMonth(), dp.getDayOfMonth() - 1);
        String[] daysOfWeek = getResources().getStringArray(R.array.days_of_week);
        String[] months = getResources().getStringArray(R.array.months);

        return new String[]{daysOfWeek[date.getDay()], String.valueOf(dp.getDayOfMonth()), months[dp.getMonth()]};
    }

    public Boolean[] getEnableTimes(DatePicker dp) {
        Date today = new Date(System.currentTimeMillis());
        int dayInterval = dp.getDayOfMonth() - today.getDate();
        if (dayInterval == 0) {
            return new Boolean[]{true, false, false, true, false, false};
        } else if (0 < dayInterval && dayInterval <= 3) {
            return new Boolean[]{true, false, false, true, false, true};
        } else if (3 < dayInterval && dayInterval <= 9) {
            return new Boolean[]{true, false, true, true, false, true};
        } else if (9 < dayInterval && dayInterval < 15) {
            return new Boolean[]{false, true, true, true, true, true};
        } else {
            return new Boolean[]{true, true, true, true, true, true};
        }
    }
}
