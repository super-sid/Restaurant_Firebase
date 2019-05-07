package com.example.siddhant.bingeandroidassign;

import java.util.List;

public class Restaurant_Model {
    public String name_rest;
    public String location_rest;
    public String timings_rest;
    public String video_url;

    public String getVideo_url() {
        return video_url;
    }

    public void setVideo_url(String video_url) {
        this.video_url = video_url;
    }

    public String getName_rest() {
        return name_rest;
    }

    public void setName_rest(String name_rest) {
        this.name_rest = name_rest;
    }

    public String getLocation_rest() {
        return location_rest;
    }

    public void setLocation_rest(String location_rest) {
        this.location_rest = location_rest;
    }

    public String getTimings_rest() {
        return timings_rest;
    }

    public void setTimings_rest(String timings_rest) {
        this.timings_rest = timings_rest;
    }

}
