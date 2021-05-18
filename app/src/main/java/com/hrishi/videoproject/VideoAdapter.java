 package com.hrishi.videoproject;

import android.media.MediaPlayer;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.VideoView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

 public class VideoAdapter extends RecyclerView.Adapter<VideoAdapter.videoViewHolder> {
    private List<VideoModel> videoItems;

     public VideoAdapter(List<VideoModel> videoItems) {
         this.videoItems = videoItems;
     }

     @NonNull
     @Override
     public videoViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
         return new videoViewHolder(
                 LayoutInflater.from(parent.getContext()).inflate(R.layout.single_video_row,parent,false)
         );
     }

     @Override
     public void onBindViewHolder(@NonNull videoViewHolder holder, int position) {
         holder.setVideoData(videoItems.get(position));

     }

     @Override
     public int getItemCount() {
         return videoItems.size();
     }


     static class videoViewHolder extends RecyclerView.ViewHolder{
        VideoView mVideoView;
        TextView mTextViewTitle;
        TextView mTextViewDescription;
        ProgressBar mProgressBar;


        public videoViewHolder(@NonNull View itemView) {
            super(itemView);
            mVideoView=itemView.findViewById(R.id.videoView);
            mTextViewTitle=itemView.findViewById(R.id.textVideoTitle);
            mTextViewDescription=itemView.findViewById(R.id.textVideoDescription);
            mProgressBar=itemView.findViewById(R.id.videoProgressBar);


        }
        void setVideoData(VideoModel videoModel){
            mTextViewTitle.setText(videoModel.getVideoTitle());
            mTextViewDescription.setText(videoModel.getVideoDescription());
            mVideoView.setVideoPath(videoModel.getVideoUrl());
            mVideoView.setOnPreparedListener(new MediaPlayer.OnPreparedListener() {
                @Override
                public void onPrepared(MediaPlayer mp) {
                    mProgressBar.setVisibility(View.GONE);
                    mp.start();

                    float videoRatio=mp.getVideoWidth() / (float)mp.getVideoHeight();
                    float screenRatio=mVideoView.getWidth() / (float)mVideoView.getHeight();
                    float scale=videoRatio/screenRatio;
                    if(scale >= 1f){
                        mVideoView.setScaleX(scale);
                    }else{
                        mVideoView.setScaleY(1f/scale);
                    }
                }
            });
            mVideoView.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
                @Override
                public void onCompletion(MediaPlayer mp) {
                    mp.start();
                }
            });
        }
    }

}
