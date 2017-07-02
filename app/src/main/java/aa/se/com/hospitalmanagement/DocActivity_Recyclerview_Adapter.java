package aa.se.com.hospitalmanagement;

import android.content.Context;
import android.graphics.Typeface;
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
    private Context context;
    private LayoutInflater inflater;
    private Typeface font;


    public DocActivity_Recyclerview_Adapter(Context context, Typeface font, String[] doctors) {
        inflater = LayoutInflater.from(context);
        this.context = context;
        this.font = font;
        this.doctors=doctors;
    }

    @Override
    public DocViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new DocViewHolder(inflater.inflate(R.layout.item_cardview_doc, parent, false));
    }

    @Override
    public void onBindViewHolder(final DocViewHolder holder, final int position) {

        holder.docName.setText(doctors[position]);
        holder.docName.setTypeface(font);
    }

    @Override
    public int getItemCount() {
        return doctors.length;
    }
}


class DocViewHolder extends RecyclerView.ViewHolder {

    public TextView docName;
    public TextView docDes;
    public ImageView docImage;

    public DocViewHolder(View itemView) {
        super(itemView);

        docName= (TextView) itemView.findViewById(R.id.text_doc);
        docDes= (TextView) itemView.findViewById(R.id.text_doc_des);
        docImage= (ImageView) itemView.findViewById(R.id.image_doc);
    }
}
