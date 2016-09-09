package com.gwr.nextmovie.adapter;

import android.content.Context;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.gwr.nextmovie.R;
import com.gwr.nextmovie.domain.Movie;
import com.gwr.nextmovie.network.ConsURL;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by willi on 07/07/2016.
 */
public class GridAdapter extends BaseAdapter {

    private final List<Item> mItems = new ArrayList<Item>();
    private final LayoutInflater mInflater;
    private Context context;
    private List<Movie> listaFilmes;

    public GridAdapter(Context context,List<Movie> lista) {
        mInflater = LayoutInflater.from(context);
        this.context = context;
        this.listaFilmes = lista;

    }

    @Override
    public int getCount() {
        return listaFilmes.size();
    }

    @Override
    public Movie getItem(int i) {
        return listaFilmes.get(i);
    }

    @Override
    public long getItemId(int i) {
        return listaFilmes.get(i).getId();
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        View v = view;
        ImageView picture;
        TextView name;

        if (v == null) {
            v = mInflater.inflate(R.layout.grid_item, viewGroup, false);
            v.setTag(R.id.picture, v.findViewById(R.id.picture));
            v.setTag(R.id.text, v.findViewById(R.id.text));
        }
        picture = (ImageView) v.getTag(R.id.picture);
        name = (TextView) v.getTag(R.id.text);

        Movie item = getItem(i);
        String urlImage = ConsURL.imageURL+item.getBackdrop_path();

        Picasso.with(context).load(urlImage).fit().into(picture);

        //picture.setImageBitmap(decodeSampledBitmapFromResource(context.getResources(),item.drawableId,200,200));

        //picture.setImageResource(item.drawableId);

        name.setText(item.getOriginal_title());

        return v;
    }

    private static class Item {
        public final String name;
        public final int drawableId;

        Item(String name, int drawableId) {
            this.name = name;
            this.drawableId = drawableId;
        }
    }

    public static Bitmap decodeSampledBitmapFromResource(Resources res, int resId,
                                                         int reqWidth, int reqHeight) {

        // First decode with inJustDecodeBounds=true to check dimensions
        final BitmapFactory.Options options = new BitmapFactory.Options();
        options.inJustDecodeBounds = true;
        BitmapFactory.decodeResource(res, resId, options);

        // Calculate inSampleSize
        options.inSampleSize = calculateInSampleSize(options, reqWidth, reqHeight);

        // Decode bitmap with inSampleSize set
        options.inJustDecodeBounds = false;
        return BitmapFactory.decodeResource(res, resId, options);
    }

    public static int calculateInSampleSize(
            BitmapFactory.Options options, int reqWidth, int reqHeight) {
        // Raw height and width of image
        final int height = options.outHeight;
        final int width = options.outWidth;
        int inSampleSize = 1;

        if (height > reqHeight || width > reqWidth) {

            final int halfHeight = height / 2;
            final int halfWidth = width / 2;

            // Calculate the largest inSampleSize value that is a power of 2 and keeps both
            // height and width larger than the requested height and width.
            while ((halfHeight / inSampleSize) > reqHeight
                    && (halfWidth / inSampleSize) > reqWidth) {
                inSampleSize *= 2;
            }
        }

        return inSampleSize;
    }

    public void updateResults(List<Movie> lista){
        this.listaFilmes = lista;

        notifyDataSetChanged();
    }
}



