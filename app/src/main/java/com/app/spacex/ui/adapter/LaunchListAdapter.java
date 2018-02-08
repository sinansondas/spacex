package com.app.spacex.ui.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.text.method.LinkMovementMethod;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.app.spacex.R;
import com.app.spacex.model.Launch;
import com.app.spacex.model.Links;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by sinansondas on 08/02/18.
 */

public class LaunchListAdapter extends RecyclerView.Adapter<LaunchListAdapter.ViewHolder> {
    private List<Launch> launches;
    private LaunchListListener launchListListener;

    private Context context;

    protected static class ViewHolder extends RecyclerView.ViewHolder {
        private TextView textViewLaunchDetails;
        private TextView textViewLaunchArticleLink;
        private ImageView imageVieLaunchBanner;

        private ViewHolder(View itemView) {
            super(itemView);
            textViewLaunchDetails = itemView.findViewById(R.id.tv_launch_details);
            textViewLaunchArticleLink = itemView.findViewById(R.id.tv_launch_article_link);
            imageVieLaunchBanner = itemView.findViewById(R.id.iv_launch_banner);
        }
    }

    public LaunchListAdapter(LaunchListListener launchListListener) {
        this(new ArrayList<Launch>(), launchListListener);
    }

    private LaunchListAdapter(List<Launch> launches, LaunchListListener launchListListener) {
        this.launches = launches;
        this.launchListListener = launchListListener;
    }

    @Override
    public LaunchListAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        context = parent.getContext();
        View itemView = LayoutInflater.from(context).inflate(R.layout.item_launch, parent, false);
        return new ViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, final int position) {
        final Launch launch = launches.get(position);

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (launchListListener != null)
                    launchListListener.onLaunchClicked(launch);
            }
        });

        String launchDetails = launch.getDetails();
        holder.textViewLaunchDetails.setText(launchDetails);

        if (launch.getLinks() != null) {
            Links links = launch.getLinks();

            if (links.getMissionPatch() != null) {
                String imageBannerPath = launch.getLinks().getMissionPatch();
                Picasso.with(context).load(imageBannerPath).into(holder.imageVieLaunchBanner);
            }

            if (links.getArticleLink() != null) {
                holder.textViewLaunchArticleLink.setText(launch.getLinks().getArticleLink());
               // holder.textViewLaunchArticleLink.setMovementMethod(LinkMovementMethod.getInstance());
            }
        }

    }

    @Override
    public int getItemCount() {
        return launches.size();
    }

    public void updateItems(List<Launch> launches) {
        this.launches = launches;
        notifyDataSetChanged();
    }
}
