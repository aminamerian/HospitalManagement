package aa.se.com.hospitalmanagement;

import android.content.Context;
import android.graphics.Typeface;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

/**
 * Created by Amin on 15/07/2017.
 */

public class TimeOptions_Recyclerview_Adapter extends RecyclerView.Adapter<TimeOptionsViewHolder> {

    private Context context;
    private LayoutInflater inflater;
    private Typeface font;
    private String[] options;

    public TimeOptions_Recyclerview_Adapter(Context context, Typeface font) {
        inflater = LayoutInflater.from(context);
        this.context = context;
        this.font = font;
        options = context.getResources().getStringArray(R.array.time_options);
    }

    @Override
    public TimeOptionsViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new TimeOptionsViewHolder(inflater.inflate(R.layout.item_timeoptions, parent, false));
    }

    @Override
    public void onBindViewHolder(TimeOptionsViewHolder holder, int position) {
        holder.optionButton.setText(options[position]);
        holder.optionButton.setTypeface(font);

    }

    @Override
    public int getItemCount() {
        return options.length;
    }
}

class TimeOptionsViewHolder extends RecyclerView.ViewHolder {

    Button optionButton;

    public TimeOptionsViewHolder(View itemView) {
        super(itemView);

        optionButton = (Button) itemView.findViewById(R.id.button_options);

    }
}
