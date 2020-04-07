package com.riceplant.bakingapp.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.riceplant.bakingapp.R;
import com.riceplant.bakingapp.model.Ingredient;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class IngredientAdapter extends RecyclerView.Adapter<IngredientAdapter.IngredientAdapterViewHolder> {

    private List<Ingredient> mIngredient;
    private Context mContext;
    private IngredientAdapterOnClickHandler mOnClickHandler;

    public IngredientAdapter(Context context, List<Ingredient> ingredientList, IngredientAdapterOnClickHandler onClickHandler) {
        mContext = context;
        mIngredient = ingredientList;
        mOnClickHandler = onClickHandler;
    }

    public interface IngredientAdapterOnClickHandler {
        void onClick(int adapterPosition);
    }

    public class IngredientAdapterViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{
        @BindView(R.id.ingredients)
        TextView ingredientTextView;
        @BindView(R.id.step_description)
        TextView stepsDescriptionTextView;

        public IngredientAdapterViewHolder(@NonNull View itemView) {
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
    public IngredientAdapterViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        View view = layoutInflater.inflate(R.layout.recipe_details_list_item, parent, false);
        return new IngredientAdapterViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull IngredientAdapterViewHolder holder, int position) {
        holder.ingredientTextView.setText(mIngredient.get(position).getIngredient());
        holder.stepsDescriptionTextView.setText(mIngredient.get(position).getQuantity().toString());
    }

    @Override
    public int getItemCount() {
        if (mIngredient == null) {
            return 0;
        }
        return mIngredient.size();
    }
}
