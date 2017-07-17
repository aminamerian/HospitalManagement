package aa.se.com.hospitalmanagement;

import android.content.Context;
import android.graphics.Color;
import android.graphics.PorterDuff;
import android.graphics.Typeface;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.Toast;

/**
 * Created by Amin on 15/07/2017.
 */

public class TimeOptions_Recyclerview_Adapter extends RecyclerView.Adapter<TimeOptionsViewHolder> {

    private final Context context;
    private LayoutInflater inflater;
    private Typeface font;
    private String[] options;
    private Boolean[] enabled;
    int colorSelectedTime, mainColor;
    private Button previousSelection = null;
    private String selectedTime = "";

    public TimeOptions_Recyclerview_Adapter(Context context, Typeface font, Boolean[] enabled) {
        inflater = LayoutInflater.from(context);
        this.context = context;
        this.font = font;
        options = context.getResources().getStringArray(R.array.time_options);
        this.enabled = enabled;
        colorSelectedTime = context.getResources().getColor(R.color.A1300);
        mainColor = context.getResources().getColor(R.color.A700);
    }

    @Override
    public TimeOptionsViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new TimeOptionsViewHolder(inflater.inflate(R.layout.item_timeoptions, parent, false));
    }

    @Override
    public void onBindViewHolder(final TimeOptionsViewHolder holder, final int position) {
        holder.optionButton.setText(options[position]);
        holder.optionButton.setTypeface(font);
        if (!enabled[position]) {
            holder.optionButton.setEnabled(false);
            holder.buttonContainer.getBackground().setColorFilter(Color.LTGRAY, PorterDuff.Mode.SRC);
        }
        holder.optionButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (previousSelection != null) {
                    previousSelection.setBackgroundColor(mainColor);
                }
                selectedTime = options[position];
                holder.buttonContainer.setBackgroundColor(colorSelectedTime);
                previousSelection = holder.optionButton;
            }
        });
    }

    @Override
    public int getItemCount() {
        return options.length;
    }

    public String getSelectedTime() {
        return selectedTime;
    }
}

class TimeOptionsViewHolder extends RecyclerView.ViewHolder {

    Button optionButton;
    LinearLayout buttonContainer;

    public TimeOptionsViewHolder(View itemView) {
        super(itemView);

        buttonContainer = (LinearLayout) itemView.findViewById(R.id.linearLayout_buttonContainer);
        optionButton = (Button) itemView.findViewById(R.id.button_options);

    }
}
