package kk.jokesapp.ui.collections;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

import kk.jokesapp.R;
import kk.jokesapp.model.Joke;

public class CollectionsListAdapter extends RecyclerView.Adapter<CollectionsListAdapter.ViewHolder> {

    private LayoutInflater layoutInflater;
    private List<Joke> jokes;

    private ItemClickListener clickListener;

    private int selectedBackground;
    private int normalBackground;

    int selectedViewHolder = -1;

    public CollectionsListAdapter(Context context, List<Joke> jokeList) {
        layoutInflater = LayoutInflater.from(context);
        jokes = jokeList;
        normalBackground = context.getResources().getColor(R.color.background);
        selectedBackground = context.getResources().getColor(R.color.design_default_color_background);
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = layoutInflater.inflate(R.layout.item_collections_list, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ViewHolder viewHolder, int position) {
        Joke joke = jokes.get(position);
        viewHolder.jokeId = joke.getId();
        viewHolder.tvJokeSetup.setText(joke.getSetup());
        viewHolder.tvJokePunchline.setText(joke.getPunchline());
        if(position == selectedViewHolder) {
            viewHolder.tvJokePunchline.setVisibility(View.VISIBLE);
            viewHolder.bDeleteJoke.setVisibility(View.VISIBLE);
            viewHolder.collectionsItemLayout.setBackgroundColor(selectedBackground);
        }
        else {
            viewHolder.tvJokePunchline.setVisibility(View.GONE);
            viewHolder.bDeleteJoke.setVisibility(View.INVISIBLE);
            viewHolder.collectionsItemLayout.setBackgroundColor(normalBackground);
        }
    }

    @Override
    public int getItemCount() {
        return jokes.size();
    }

    public void updateItems(List<Joke> items) {
        this.jokes = items;
        notifyDataSetChanged();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        TextView tvJokeSetup;
        TextView tvJokePunchline;
        ImageButton bDeleteJoke;
        LinearLayout collectionsItemLayout;

        int jokeId;

        public ViewHolder(View itemView) {
            super(itemView);

            tvJokeSetup = itemView.findViewById(R.id.tvJokeSetup);
            tvJokePunchline = itemView.findViewById(R.id.tvJokePunchline);
            bDeleteJoke = itemView.findViewById(R.id.bDeleteJoke);
            collectionsItemLayout = itemView.findViewById(R.id.collectionsItemLayout);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    int position = getAdapterPosition();
                    if(selectedViewHolder == position)
                        selectedViewHolder = -1;
                    else
                        selectedViewHolder = position;
                    notifyDataSetChanged();
                }
            });

            bDeleteJoke.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    if(clickListener != null) {
                        selectedViewHolder = -1;
                        clickListener.onItemDeleteClick(jokeId);
                    }
                }
            });
        }
    }

    public void setOnClickListener(ItemClickListener listener) {
        this.clickListener = listener;
    }

    public interface ItemClickListener {
        void onItemDeleteClick(int id);
    }
}
