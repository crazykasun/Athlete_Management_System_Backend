package com.olympic.athletemanagementsystem.common.constants;

public class Routes {
    //Gender Routes
    public static final String API_GENDER = "/api/gender";
    public static final String API_GENDER_BY_ID = "/{genderId}";

    //Athlete Routes
    public static final String API_ATHLETE = "/api/athletes";
    public static final String API_ATHLETE_UPLOAD_IMAGE = "/uploadImage/{athleteId}";
    public static final String API_ATHLETE_GET_IMAGE = "/getImage/{athleteId}";
    public static final String API_ATHLETE_BY_EVENT_ENABLED = "/event";
    public static final String API_SEARCH_ATHLETE_BY_GENDER = "/search/gender/{genderId}";
    public static final String API_SEARCH_ATHLETE_BY_NAMES = "/search/names";
    public static final String API_ATHLETE_BY_ID = "/{athleteId}";
    public static final String API_ATHLETE_EVENTS = "/events";

    //Event category Routes
    public static final String API_CATEGORY = "/api/categories";
    public static final String API_CATEGORY_BY_ID = "/{categoryId}";

    //Event Routes
    public static final String API_EVENT = "/api/events";
    public static final String API_EVENT_BY_ID = "/{eventId}";

    //Result Routes
    public static final String API_RESULT = "/api/results";
    public static final String API_RESULT_BY_ID = "/{resultId}";
    public static final String API_ATHLETE_RESULTS = "/athlete";
}
