package aa.se.com.hospitalmanagement;

import android.app.Activity;
import android.app.ActivityOptions;
import android.content.Context;
import android.content.Intent;
import android.graphics.Typeface;
import android.support.v4.app.ActivityOptionsCompat;
import android.support.v4.util.Pair;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

/**
 * Created by Amin on 22/06/2017.
 */

public class DocActivity_Recyclerview_Adapter extends RecyclerView.Adapter<DocViewHolder> {
    private String[] doctors;
    private String[] doctorsDes;
    private int[] doctorsImg;
    private Context context;
    private LayoutInflater inflater;
    private Typeface font;


    public DocActivity_Recyclerview_Adapter(Context context, Typeface font, String[] doctors, String[] doctorsDes, int[] doctorsImg) {
        inflater = LayoutInflater.from(context);
        this.context = context;
        this.font = font;
        this.doctors = doctors;
        this.doctorsDes = doctorsDes;
        this.doctorsImg = doctorsImg;
    }

    @Override
    public DocViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new DocViewHolder(inflater.inflate(R.layout.item_cardview_doc, parent, false));
    }

    @Override
    public void onBindViewHolder(final DocViewHolder holder, final int position) {

        holder.docName.setText(doctors[position]);
        holder.docDes.setText(doctorsDes[position]);
        holder.docImage.setImageResource(doctorsImg[position]);

        holder.docName.setTypeface(font);
        holder.docDes.setTypeface(font);

        holder.rootView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context, SingleDocActivity.class);
                intent.putExtra("NAME", doctors[position]);
                intent.putExtra("IMAGE", doctorsImg[position]);

                Pair<View, String> p1 = Pair.create((View) holder.docImage, "sharedImage");
                Pair<View, String> p2 = Pair.create((View) holder.docName, "sharedText");

                ActivityOptionsCompat options = ActivityOptionsCompat.makeSceneTransitionAnimation((Activity) context, p1, p2);
                context.startActivity(intent, options.toBundle());
            }
        });
    }

    @Override
    public int getItemCount() {
        return doctors.length;
    }
}


class DocViewHolder extends RecyclerView.ViewHolder {

    CardView rootView;
    TextView docName;
    TextView docDes;
    ImageView docImage;

    public DocViewHolder(View itemView) {
        super(itemView);

        rootView = (CardView) itemView.findViewById(R.id.cardView_root);
        docName = (TextView) itemView.findViewById(R.id.text_doc);
        docDes = (TextView) itemView.findViewById(R.id.text_doc_des);
        docImage = (ImageView) itemView.findViewById(R.id.image_doc);
    }
}
