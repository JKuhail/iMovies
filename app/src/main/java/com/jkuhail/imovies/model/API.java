package com.jkuhail.imovies.model;

public enum API{
    TOP_RATED("{media_type}/top_rated"),
    TRENDING("trending/{media_type}/{time_window}");

    String value;

    API(String value) {
        this.value = value;
    }

    public String topRatedUrl(MediaType mediaType){
        return Constants.API_URL.concat(Constants.API_KEY).concat(this.value.replace("{media_type}" , mediaType.getValue()));
    }
    public String trendingUrl(MediaType mediaType, Time time){
        return Constants.API_URL.concat(Constants.API_KEY).concat(this.value.replace("{media_type}" ,
                mediaType.getValue()).replace("{time_window}", time.getValue()));
    }
}



