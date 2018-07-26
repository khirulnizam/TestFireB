package my.edu.kuis.fstm.testfireb;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

public class RecyclerViewAdapter extends RecyclerView.Adapter<RecyclerViewAdapter.ViewHolder> {

    Context context;
    List<TrainingDetails> MainImageUploadInfoList;

    public RecyclerViewAdapter(Context context, List<TrainingDetails> TempList) {

        this.MainImageUploadInfoList = TempList;

        this.context = context;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.recyclerviewitems, parent, false);

        ViewHolder viewHolder = new ViewHolder(view);

        return viewHolder;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {

        TrainingDetails trainingdetails = MainImageUploadInfoList.get(position);

        //holder.trainingid.setText(trainingdetails.getTrainingid());
        holder.trainingname.setText("Name    : "+trainingdetails.getTrainingname());
             holder.website.setText("Phone   : "+trainingdetails.getContact());
             holder.contact.setText("Website: "+trainingdetails.getWebsite());

    }

    @Override
    public int getItemCount() {

        return MainImageUploadInfoList.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder {

        public TextView trainingname, contact, website;

        public ViewHolder(View itemView) {

            super(itemView);

            //trainingid = (TextView) itemView.findViewById(R.id.trainingid);
            trainingname = (TextView) itemView.findViewById(R.id.trainingname);
            contact = (TextView) itemView.findViewById(R.id.contact);
            website = (TextView) itemView.findViewById(R.id.website);
        }
    }
}//end RecyclerViewAdapter
