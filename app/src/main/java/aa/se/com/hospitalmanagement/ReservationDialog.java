package aa.se.com.hospitalmanagement;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.graphics.Typeface;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.DialogFragment;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.Toast;

/**
 * Created by Amin on 10/03/2017.
 */

public class ReservationDialog extends DialogFragment {

    private RecyclerView recyclerView;
    private DatePicker datePicker;
    private Button cancelButton, acceptButton;
    private Typeface yekanFont;
    private boolean dateSelected = false;
    private TextView textViewHeader;
    private LinearLayout layoutHeader;

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
        layoutHeader = (LinearLayout) view.findViewById(R.id.layout_header);
        textViewHeader = (TextView) view.findViewById(R.id.textView_header);
        cancelButton = (Button) view.findViewById(R.id.button_cancel);
        acceptButton = (Button) view.findViewById(R.id.button_accept);

        cancelButton.setTypeface(yekanFont);
        acceptButton.setTypeface(yekanFont);
        textViewHeader.setTypeface(yekanFont);

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            datePicker.setFirstDayOfWeek(7);
        }

        acceptButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (dateSelected) {

                    dismiss();
                    Toast.makeText(getContext(), "ثبت شد!", Toast.LENGTH_SHORT).show();
                } else {
                    dateSelected = true;
                    datePicker.setVisibility(View.GONE);
                    layoutHeader.setVisibility(View.VISIBLE);
                    acceptButton.setText("تایید");
                    textViewHeader.setText(datePicker.getDayOfMonth()+"");

                    TimeOptions_Recyclerview_Adapter adapter = new TimeOptions_Recyclerview_Adapter(recyclerView.getContext(), yekanFont);
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
}
