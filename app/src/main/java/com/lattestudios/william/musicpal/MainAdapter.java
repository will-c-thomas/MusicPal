package com.lattestudios.william.musicpal;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.PopupMenu;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;

import java.util.List;

public class MainAdapter extends RecyclerView.Adapter<MainAdapter.MainViewHolder> {

    private Context context;
    private List<Song> songList;

    public static class MainViewHolder extends RecyclerView.ViewHolder {

        public TextView title, artist;
        public ImageView thumbnail, overflow;

        public MainViewHolder(View v) {
            super(v);

            title = (TextView) v.findViewById(R.id.song_title);
            artist = (TextView) v.findViewById(R.id.artist);
            thumbnail = (ImageView) v.findViewById(R.id.thumbnail);
            overflow = (ImageView) v.findViewById(R.id.overflow);

        }
    }

        public MainAdapter(Context context, List<Song> songList) {
            this.context = context;
            this.songList = songList;
        }

        @Override
    public MainViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.song_card, parent, false);
        return new MainViewHolder(itemView);
    }

    public void onBindViewHolder(final MainViewHolder holder, int position) {

        Song song = songList.get(position);
        holder.title.setText(song.getName());
        holder.artist.setText("by " + song.getArtist());

        Glide.with(context).load(song.getThumbnail()).into(holder.thumbnail);

        holder.overflow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showPopup(holder.overflow);
            }
        });

    }

    private void showPopup(View view) {
        PopupMenu popupMenu = new PopupMenu(context, view);
        MenuInflater menuInflater = popupMenu.getMenuInflater();
        menuInflater.inflate(R.menu.song_menu, popupMenu.getMenu());
        popupMenu.setOnMenuItemClickListener(new MenuItemClickListener());
        popupMenu.show();
    }

    class MenuItemClickListener implements PopupMenu.OnMenuItemClickListener {

        public MenuItemClickListener(){}

        @Override
        public boolean onMenuItemClick(MenuItem menuItem) {
            switch(menuItem.getItemId()) {
                case R.id.menu_remove_song:
                    Toast.makeText(context, "Removed", Toast.LENGTH_SHORT).show();
            }
            return false;

        }

    }

    @Override
    public int getItemCount() {
        return songList.size();
    }


    }
