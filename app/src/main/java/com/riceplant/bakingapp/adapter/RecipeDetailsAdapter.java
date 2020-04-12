package com.riceplant.bakingapp.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.riceplant.bakingapp.R;
import com.riceplant.bakingapp.model.Step;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class RecipeDetailsAdapter extends RecyclerView.Adapter<RecipeDetailsAdapter.RecipeDetailsAdapterViewHolder> {

    private List<Step> mSteps;
    private Context mContext;
    private RecipeDetailsAdapterOnClickHandler mOnClickHandler;

    public RecipeDetailsAdapter(Context context, List<Step> steps, RecipeDetailsAdapterOnClickHandler onClickHandler) {
        mContext = context;
        mSteps = steps;
        mOnClickHandler = onClickHandler;
    }

    public interface RecipeDetailsAdapterOnClickHandler {
        void onClick(int adapterPosition);
    }

    public class RecipeDetailsAdapterViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{
        @BindView(R.id.steps_id)
        TextView stepIdTextView;
        @BindView(R.id.steps_short_description)
        TextView stepShortDescriptionTextView;

        public RecipeDetailsAdapterViewHolder(@NonNull View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }

        @Override
        public void onClick(View v) {
            int adapterPosition = getAdapterPosition();
            mOnClickHandler.onClick(adapterPosition);
        }
    }

    @NonNull
    @Override
    public RecipeDetailsAdapterViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        View view = layoutInflater.inflate(R.layout.recipe_details_list_item, parent, false);
        return new RecipeDetailsAdapterViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull RecipeDetailsAdapterViewHolder holder, int position) {
        holder.stepIdTextView.setText(mSteps.get(position).getId().toString());
        holder.stepShortDescriptionTextView.setText(mSteps.get(position).getShortDescription());
    }

    @Override
    public int getItemCount() {
        if (mSteps == null) {
            return 0;
        }
        return mSteps.size();
    }
}
