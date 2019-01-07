package adapter;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import com.g4bor.getinshape.R;

import model.TrainingItem;

public class TrainingListAdapter extends RecyclerView.Adapter<TrainingListAdapter.ViewHolder> {
    private List<TrainingItem> listItems;
    public OnItemClickListener itemClickListener;

    private Map<String, Integer> lvlColor =new HashMap<String, Integer>() {
        {   put("BEGINNER", R.drawable.green_panel);
            put("INTERMEDIATE", R.drawable.yellow_panel);
            put("ADVANCED", R.drawable.red_panel); }
    };

    public TrainingListAdapter(List listitem) {
        this.listItems = listitem;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int i) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.training_list_item, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(TrainingListAdapter.ViewHolder viewHolder, int position) {
        TrainingItem item = listItems.get(position);
        viewHolder.title.setText(item.getTitle());
        viewHolder.image.setImageDrawable(item.getImage());
        Integer panelColor = lvlColor.get(item.getLevel().toString());
        viewHolder.lvlText.setBackgroundResource(panelColor);
        viewHolder.lvlText.setText(item.getLevel().toBigCapital());
    }

    @Override
    public int getItemCount() {
        return listItems.size();
    }

    public void setOnClickListener(OnItemClickListener itemClickListener) {
        this.itemClickListener = itemClickListener;
    }

    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        public LinearLayout titlePanel;
        public TextView title;
        public ImageView image;
        public TextView lvlText;

        public ViewHolder(View itemView) {
            super(itemView);
            itemView.setOnClickListener(this);
            titlePanel = itemView.findViewById(R.id.title_panel);
            title = itemView.findViewById(R.id.trainingList_title);
            image = itemView.findViewById(R.id.trainingList_image);
            lvlText = itemView.findViewById(R.id.lvlText);
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
