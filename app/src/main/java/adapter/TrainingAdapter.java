package adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.g4bor.getinshape.R;

import java.util.List;

import model.TrainingItem;

public class TrainingAdapter extends RecyclerView.Adapter<TrainingAdapter.ViewHolder> {
    private List<TrainingItem> listItems;
    public OnItemClickListener itemClickListener;

    public TrainingAdapter(Context context, List listitem) {
        this.listItems = listitem;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int i) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.training_list_item, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(TrainingAdapter.ViewHolder viewHolder, int position) {
        TrainingItem item = listItems.get(position);
        viewHolder.title.setText(item.getTitle());
        viewHolder.image.setImageDrawable(item.getImage());
    }

    @Override
    public int getItemCount() {
        return listItems.size();
    }

    public void setOnClickListener(OnItemClickListener itemClickListener) {
        this.itemClickListener = itemClickListener;
    }

    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        public TextView title;
        public ImageView image;

        public ViewHolder(View itemView) {
            super(itemView);
            itemView.setOnClickListener(this);
            title = itemView.findViewById(R.id.trainingList_title);
            image = itemView.findViewById(R.id.trainingList_image);
        }

        @Override
        public void onClick(View view) {
            itemClickListener.onItemClick(view, getAdapterPosition());
        }
    }

    public interface OnItemClickListener {
        void onItemClick(View view, int position);
    }
}
