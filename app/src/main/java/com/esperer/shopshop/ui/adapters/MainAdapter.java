package com.esperer.shopshop.ui.adapters;
import android.content.Context;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import com.esperer.shopshop.R;
import com.esperer.shopshop.models.BrandSingle;
import com.esperer.shopshop.models.CategorySingle;
import com.esperer.shopshop.models.OfferSingle;
import com.esperer.shopshop.models.TendingSingle;
import com.esperer.shopshop.models.VarietiesSingle;
import com.esperer.shopshop.ui.fragments.HomeFragment;
import java.util.ArrayList;
import static com.esperer.shopshop.ui.fragments.HomeFragment.getFContext;



public class MainAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private Context context;
    private ArrayList<Object> items;
    HomeFragment mHomeFragment = new HomeFragment();

    private final int CATEGORY = 1;
    private final int RECOMMEND = 2;
    private final int OFFER = 3;
    private final int BRAND = 4;
    private  final int VARIETIES = 5;

   /// Provide a suitable constructor
    public MainAdapter(Context context, ArrayList<Object> items) {
        this.context = context;
        this.items = items;
    }

    /**
     * This methods creates different RecyclerView.ViewHolder objects based on the item view type.\
     *
     * @param viewGroup ViewGroup container for the item
     * @param viewType type of view to be inflated
     * @return viewHolder to be inflated
     */


    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(context);
        View view;
        RecyclerView.ViewHolder holder;
        switch (viewType) {
            case CATEGORY:
                view = inflater.inflate(R.layout.category, parent, false);
                holder = new CategoryViewHolder(view);
                break;
            case RECOMMEND:
                view = inflater.inflate(R.layout.trending, parent, false);
                holder = new RecommendViewHolder(view);
                break;

            case OFFER:
                view = inflater.inflate(R.layout.offer, parent, false);
                holder = new OfferViewHolder(view);
                break;

            case BRAND:
                view = inflater.inflate(R.layout.brand, parent, false);
                holder = new BrandViewHolder(view);
                break;

            case VARIETIES:
                view = inflater.inflate(R.layout.varieties, parent, false);
                holder = new VarietiesViewHolder(view);
                break;


            default:
                view = inflater.inflate(R.layout.trending, parent, false);
                holder = new RecommendViewHolder(view);
                break;
        }


        return holder;
    }

    /**
     * This method internally calls onBindViewHolder(ViewHolder, int) to update the
     * RecyclerView.ViewHolder contents with the item at the given position
     * and also sets up some private fields to be used by RecyclerView.
     *
     * @param viewHolder The type of RecyclerView.ViewHolder to populate
     * @param position Item position in the viewgroup.
     */

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        if (holder.getItemViewType() == CATEGORY)
            categoryView((CategoryViewHolder) holder);
        else if (holder.getItemViewType() == RECOMMEND)
            recomendView((RecommendViewHolder) holder);

        else if (holder.getItemViewType() == OFFER)
        {
            OfferView((OfferViewHolder)holder);
        }

        else if (holder.getItemViewType() == BRAND)
        {
            BrandView((BrandViewHolder)holder);
        }

        else if (holder.getItemViewType() == VARIETIES)
        {
            VarietiesView((VarietiesViewHolder)holder);
        }

    }


    /**
     * following methods creates and set adapter respect to view
     * @param viewHolder The type of RecyclerView.ViewHolder to populate
    */


    private void categoryView(CategoryViewHolder holder) {

        CategoryAdapter adapter1 = new CategoryAdapter(getFContext(),mHomeFragment.getCategory());
        holder.recyclerView.setLayoutManager(new LinearLayoutManager(context,LinearLayout.HORIZONTAL,false));
        holder.recyclerView.setAdapter(adapter1);
    }


    private void recomendView(RecommendViewHolder holder) {
        TendingAdapter adapter = new TendingAdapter(mHomeFragment.getTrending(),getFContext());
        holder.recyclerView.setLayoutManager(new LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false));
        holder.recyclerView.setAdapter(adapter);
    }


    private void OfferView(OfferViewHolder holder) {
        OfferAdapter adapter = new OfferAdapter(getFContext(),mHomeFragment.getOffer());
        holder.recyclerView.setLayoutManager(new LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false));
        holder.recyclerView.setAdapter(adapter);
    }

    private void BrandView(BrandViewHolder holder) {
        BrandAdapter adapter = new BrandAdapter(getFContext(),mHomeFragment.getBrand());
        holder.recyclerView.setLayoutManager(new LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false));
        holder.recyclerView.setAdapter(adapter);
    }

    private void VarietiesView(VarietiesViewHolder holder) {
        VarietiesAdapter adapter = new VarietiesAdapter(getFContext(),mHomeFragment.getVarieties());
        holder.recyclerView.setLayoutManager(new LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false));
        holder.recyclerView.setAdapter(adapter);
    }


    // Return the size of your dataset (invoked by the layout manager)

    @Override
    public int getItemCount() {
        return items.size();
    }

   /* Returns the view type of the item at position for the purposes of view recycling
     * @param position Item position in the viewgroup.
   */

    @Override
    public int getItemViewType(int position) {
        if (items.get(position) instanceof CategorySingle)
            return CATEGORY;
        if (items.get(position) instanceof TendingSingle)
            return RECOMMEND;

        if (items.get(position) instanceof OfferSingle)
        {
            return OFFER;
        }

        if (items.get(position) instanceof BrandSingle)
        {
            return BRAND;
        }

        if (items.get(position)instanceof VarietiesSingle)
        {
            return VARIETIES;
        }

        return -1;
    }


/// create viewHolder class for different different layout

    public class CategoryViewHolder extends RecyclerView.ViewHolder {

        RecyclerView recyclerView;

        CategoryViewHolder(View itemView) {
            super(itemView);
            recyclerView = itemView.findViewById(R.id.category_recyclerView);
        }
    }

    public class RecommendViewHolder extends RecyclerView.ViewHolder {
        RecyclerView recyclerView;

        RecommendViewHolder(View itemView) {
            super(itemView);
            recyclerView = itemView.findViewById(R.id.recommend_recyclerView);
        }
    }


    public class OfferViewHolder extends RecyclerView.ViewHolder {
        RecyclerView recyclerView;

        OfferViewHolder(View itemView) {
            super(itemView);
            recyclerView = itemView.findViewById(R.id.offer_recyclerView);
        }
    }


    public class BrandViewHolder extends RecyclerView.ViewHolder {
        RecyclerView recyclerView;

        BrandViewHolder(View itemView) {
            super(itemView);
            recyclerView = itemView.findViewById(R.id.brand_recyclerView);
        }
    }

    public class VarietiesViewHolder extends RecyclerView.ViewHolder {
        RecyclerView recyclerView;

        VarietiesViewHolder(View itemView) {
            super(itemView);
            recyclerView = itemView.findViewById(R.id.varieties_recyclerView);
        }
    }

}
