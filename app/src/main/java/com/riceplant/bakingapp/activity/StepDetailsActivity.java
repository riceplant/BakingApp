package com.riceplant.bakingapp.activity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.exoplayer2.ExoPlayerFactory;
import com.google.android.exoplayer2.SimpleExoPlayer;
import com.google.android.exoplayer2.source.MediaSource;
import com.google.android.exoplayer2.source.ProgressiveMediaSource;
import com.google.android.exoplayer2.ui.PlayerView;
import com.google.android.exoplayer2.upstream.DataSource;
import com.google.android.exoplayer2.upstream.DefaultDataSourceFactory;
import com.google.android.exoplayer2.util.Log;
import com.google.android.exoplayer2.util.Util;
import com.riceplant.bakingapp.R;
import com.riceplant.bakingapp.model.Recipe;
import com.riceplant.bakingapp.model.Step;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

import static com.riceplant.bakingapp.activity.RecipeActivity.MY_RECIPE;

public class StepDetailsActivity extends AppCompatActivity {
    public static final String TAG = StepDetailsActivity.class.getSimpleName();

    public static Step steps;
    private String shortDescription;
    private String videoURL;
    private String mDescription;
    private Uri uri;

    private SimpleExoPlayer player;
    private boolean playWhenReady = true;
    private int currentWindow = 0;
    private long playbackPosition = 0;

    @BindView(R.id.exo_player_view)
    PlayerView playerView;
    @BindView(R.id.step_detail_description)
    TextView descriptionView;
    @BindView(R.id.placeholder_image_view)
    ImageView placeholderView;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_steps_detail);

        videoURL = steps.getVideoURL();
        mDescription = steps.getDescription();
        shortDescription = steps.getShortDescription();

        ButterKnife.bind(this);
        descriptionView.setText(mDescription);
        setTitle(shortDescription);
    }

    @Override
    protected void onStart() {
        super.onStart();
        if (Util.SDK_INT >= 24) {
            initializePlayer();
        }
    }

    @Override
    protected void onResume() {
        super.onResume();
        if (Util.SDK_INT < 24 || player == null) {
            initializePlayer();
        }
    }

    @Override
    protected void onStop() {
        super.onStop();
        if (Util.SDK_INT >= 24) {
            releasePlayer();
        }
    }

    @Override
    protected void onPause() {
        super.onPause();
        if (Util.SDK_INT < 24) {
            releasePlayer();
        }
    }

    private void releasePlayer() {
        if (player != null) {
            playWhenReady = player.getPlayWhenReady();
            playbackPosition = player.getCurrentPosition();
            currentWindow = player.getCurrentWindowIndex();
            player.release();
            player = null;
        }
    }

    private void initializePlayer() {
        player = ExoPlayerFactory.newSimpleInstance(this);
        playerView.setPlayer(player);
        if (videoURL != null) {
            placeholderView.setVisibility(View.GONE);
            uri = Uri.parse(videoURL);
        }
        if (videoURL.equals("")) {
            placeholderView.setVisibility(View.VISIBLE);
            playerView.setVisibility(View.GONE);
        }
        MediaSource mediaSource = buildMediaSource(uri);
        player.setPlayWhenReady(playWhenReady);
        player.seekTo(currentWindow, playbackPosition);
        player.prepare(mediaSource, false, false);
    }

    private MediaSource buildMediaSource(Uri uri) {
        DataSource.Factory dataSourceFactory = new DefaultDataSourceFactory(this, "BakingApp");
        return new ProgressiveMediaSource.Factory(dataSourceFactory)
                .createMediaSource(uri);
    }
}
