package com.jkuhail.imovies.model;

public enum API{
    TOP_RATED("{media_type}/top_rated"),
    TRENDING("trending/{media_type}/{time_window}"),
    GET_ITEM("{media_type}/{item_id}"),
    GET_ITEM_CREDITS("{media_type}/{item_id}/credits"),
    GET_ITEM_VIDEO("{media_type}/{item_id}/videos");

    String value;

    API(final String value) {
        this.value = value;
    }

    public String topRatedUrl(String mediaType){
        return Constants.API_URL.concat(this.value.replace("{media_type}" , mediaType)).concat(Constants.API_KEY);
    }
    public String trendingUrl(String mediaType, Time time){
        return Constants.API_URL.concat(this.value.replace("{media_type}" ,
                mediaType).replace("{time_window}", time.getValue())).concat(Constants.API_KEY);
    }
    public String itemUrl(String mediaType, int item_id){
        return Constants.API_URL.concat(this.value.replace("{item_id}" , String.valueOf(item_id)).replace("{media_type}" ,
                mediaType).concat(Constants.API_KEY));
    }
}



