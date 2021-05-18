package com.hrishi.videoproject;

public class VideoModel {
    String videoTitle;
    String videoDescription;
    String videoUrl;

    public VideoModel(String title, String description, String videoUrl) {
        this.videoTitle = title;
        this.videoDescription = description;
        this.videoUrl = videoUrl;
    }

    public String getVideoTitle() {
        return videoTitle;
    }

    public String getVideoDescription() {
        return videoDescription;
    }

    public String getVideoUrl() {
        return videoUrl;
    }
}
