package com.gwr.nextmovie.activity;

import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.gwr.nextmovie.R;
import com.gwr.nextmovie.domain.Movie;
import com.gwr.nextmovie.network.ConsURL;
import com.squareup.picasso.Picasso;
import com.squareup.picasso.Target;

import java.io.IOException;

public class MovieDetailActivity extends AppCompatActivity {

    RelativeLayout rlBackground;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_movie_detail);

        Movie movie = (Movie) getIntent().getSerializableExtra("Filme");
        rlBackground = (RelativeLayout) findViewById(R.id.rlBackground);
        ImageView poster = (ImageView) findViewById(R.id.ivPoster);
        TextView sinopse = (TextView) findViewById(R.id.tvSinopse);
        TextView titulo = (TextView) findViewById(R.id.tvTitulo);
        TextView dataLancamento = (TextView) findViewById(R.id.tvData);
        TextView mediaVotos = (TextView) findViewById(R.id.tvVoto);


        Picasso.with(this).load(ConsURL.imageURL+movie.getBackdrop_path()).into(new Target() {
            @Override
            public void onBitmapLoaded(Bitmap bitmap, Picasso.LoadedFrom from) {
                rlBackground.setBackground(new BitmapDrawable(getResources(), bitmap));
                rlBackground.getBackground().setAlpha(100);
            }

            @Override
            public void onBitmapFailed(Drawable errorDrawable) {

            }

            @Override
            public void onPrepareLoad(Drawable placeHolderDrawable) {

            }
        });

        String urlImage = ConsURL.imageURL+movie.getPoster_path();
        Picasso.with(this).load(urlImage).fit().into(poster);


        sinopse.setText(movie.getOverview());
        titulo.setText(movie.getOriginal_title());
        dataLancamento.setText(movie.getRelease_date());
        mediaVotos.setText(String.valueOf(movie.getVote_average()));

    }
}
