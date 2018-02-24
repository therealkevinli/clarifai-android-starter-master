package com.clarifai.android.starter.api.v2.service;

import android.app.IntentService;
import android.content.Intent;
import android.util.Log;

import com.google.android.gms.location.ActivityRecognitionResult;
import com.google.android.gms.location.DetectedActivity;

/**
 * Created by hyungiko on 6/26/17.
 */

public class GooglePlayIntentService extends IntentService {

    public String TAG = "GooglePlayIntentService";
    public long original_time = 0;
    public GooglePlayIntentService() {
        super("ActivityRecognitionService");
    }

    /** Called when a new activity detection update is available.
     *
     */
    @Override
    protected void onHandleIntent(Intent intent) {
        if (ActivityRecognitionResult.hasResult(intent)) {
            ActivityRecognitionResult result = ActivityRecognitionResult.extractResult(intent);
            Log.e(TAG, "result: "+getActivity(result));
        }
    }

    public String getActivity(ActivityRecognitionResult result) {
        DetectedActivity activity = result.getMostProbableActivity();

        String log = "";
        original_time = result.getTime();

        switch(activity.getType()){
            case DetectedActivity.STILL:
                log = "STILL";
                break;
            case DetectedActivity.TILTING:
                log = "TILTING";
                break;
            case DetectedActivity.WALKING:
                log = "WALKING";
                break;
            case DetectedActivity.ON_FOOT:
                log = "ON_FOOT";
                break;
            case DetectedActivity.RUNNING:
                log = "RUNNING";
                break;
            case DetectedActivity.ON_BICYCLE:
                log = "ON_BICYCLE";
                break;
            case DetectedActivity.IN_VEHICLE:
                log = "IN_VEHICLE";
                break;
            case DetectedActivity.UNKNOWN:
                log = "UNKNOWN";
                break;
        }


        Log.d(TAG, "Activity: "+activity.getConfidence() + "%:\t\t\t" + log + " long time - " +original_time);

        return log;
    }
}
