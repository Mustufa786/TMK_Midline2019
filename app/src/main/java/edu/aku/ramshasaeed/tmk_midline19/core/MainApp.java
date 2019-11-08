package edu.aku.ramshasaeed.tmk_midline19.core;

import android.Manifest;
import android.app.Activity;
import android.app.AlertDialog;
import android.app.Application;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;
import android.provider.Settings;
import android.support.v4.app.ActivityCompat;
import android.text.format.DateFormat;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import edu.aku.ramshasaeed.tmk_midline19.activities.EndingActivity;
import edu.aku.ramshasaeed.tmk_midline19.contracts.BLRandomContract;
import edu.aku.ramshasaeed.tmk_midline19.contracts.FamilyMembersContract;
import edu.aku.ramshasaeed.tmk_midline19.contracts.FormsContract;
import edu.aku.ramshasaeed.tmk_midline19.contracts.SectionIIMContract;

/**
 * Created by hassan.naqvi on 11/30/2016.
 */

public class MainApp extends Application {

    public static final String _IP = "43.245.131.159"; // Test PHP server
    public static final Integer _PORT = 8080; // Port - with colon (:)
    public static final String _HOST_URL = "http://" + MainApp._IP + ":" + MainApp._PORT + "/tmk_midline/api/";
    public static final String _UPDATE_URL = "http://" + MainApp._IP + ":" + MainApp._PORT + "/tmk_midline/app/app-debug.apk";
    public static final String _UPDATE_URL_NEW = "http://" + MainApp._IP + ":" + MainApp._PORT + "/tmk_midline/app/ml/";

    public static String IMEI;
    public static String DeviceURL = "devices.php";

    /*
        public static final String _IP = "43.245.131.159"; // Test server
    */
    public static final Integer MONTHS_LIMIT = 11;
    public static final Integer DAYS_LIMIT = 29;
    //public static final long MILLISECONDS_IN_5YEAR = (MILLISECONDS_IN_YEAR + MILLISECONDS_IN_YEAR + MILLISECONDS_IN_YEAR + MILLISECONDS_IN_YEAR + MILLISECONDS_IN_YEAR);
    private static final long MINIMUM_DISTANCE_CHANGE_FOR_UPDATES = 1; // in Meters
    private static final long MINIMUM_TIME_BETWEEN_UPDATES = 1000; // in Milliseconds
    private static final int TWENTY_MINUTES = 1000 * 60 * 20;
    private static final int TWO_MINUTES = 1000 * 60 * 2;
    private static final long MILLIS_IN_SECOND = 1000;
    private static final long SECONDS_IN_MINUTE = 60;
    private static final long MINUTES_IN_HOUR = 60;
    private static final long HOURS_IN_DAY = 24;
    public static final long MILLISECONDS_IN_DAY = MILLIS_IN_SECOND * SECONDS_IN_MINUTE * MINUTES_IN_HOUR * HOURS_IN_DAY;
    private static final long DAYS_IN_YEAR = 365;
    public static final long MILLISECONDS_IN_YEAR = MILLIS_IN_SECOND * SECONDS_IN_MINUTE * MINUTES_IN_HOUR * HOURS_IN_DAY * DAYS_IN_YEAR;
    private static final long DAYS_IN_50_YEAR = 365 * 49;
    public static final long MILLISECONDS_IN_50Years = MILLIS_IN_SECOND * SECONDS_IN_MINUTE * MINUTES_IN_HOUR * HOURS_IN_DAY * DAYS_IN_50_YEAR;
    private static final long DAYS_IN_MONTH = 30;
    public static final long MILLISECONDS_IN_MONTH = MILLIS_IN_SECOND * SECONDS_IN_MINUTE * MINUTES_IN_HOUR * HOURS_IN_DAY * DAYS_IN_MONTH;

    private static final long DAYS_IN_2_YEAR = 365 * 2;
    public static final long MILLISECONDS_IN_2Years = MILLIS_IN_SECOND * SECONDS_IN_MINUTE * MINUTES_IN_HOUR * HOURS_IN_DAY * DAYS_IN_2_YEAR;
    //public static final long MILLISECONDS_IN_100_YEAR = MILLISECONDS_IN_YEAR * 100;
    public static String deviceId;

    public static Boolean admin = false;
    public static FormsContract fc;
    public static String userName = "0000";
    //    Total No of members got from Section A

    public static int TotalMembersCount = 0;
    public static int mwraCount = 1;
    public static int TotalMWRACount = 0;
    public static int TotalChildCount = 0;
    public static int TotalDeceasedMotherCount = 0;
    public static int CounterDeceasedMother = 0;
    public static int TotalDeceasedChildCount = 0;
    public static int CounterDeceasedChild = 0;
    public static int counter = 0;
    public static int mm = 1;
    public static int imsCount = 1;
    public static int totalImsCount = 0;
    public static int positionIm = 0;
    public static boolean flag = true;
    public static int versionCode;
    public static String versionName;
    public static String td01, td02, td03, td04, td05, td06, td07, td08;

    public static Map<String, FamilyMembersContract> childsMap = new HashMap<>();
    public static ArrayList<String> lstChild = new ArrayList<>();

    public static int ageRdo = 0;
    public static List<FamilyMembersContract> members_f_m;
    public static List<FamilyMembersContract> mwra;
    public static List<FamilyMembersContract> childUnder2;
    public static List<FamilyMembersContract> childUnder5;
    public static int serial_no;
    public static YoungestChild young_child;


    //    Ali
    public static String regionDss = "";
    public static List<FamilyMembersContract> familyMembersList;
    public static FamilyMembersContract fmc;
    public static SectionIIMContract ims;

    public static int memFlag = 0;

    public static int selectedPos = -1;
    public static int randID = 1;
    public static Boolean isRsvp = false;
    public static Boolean isHead = false;
    public static int ucCode = 0;
    public static int talukaCode = 0;
    public static int areaCode = 0;
    public static String cluster = "";
    public static String hhno = "";
    public static String billno = "";
    public static BLRandomContract selectedHead;
    public static int BLRandomSize;
    protected static LocationManager locationManager;


    public static int monthsBetweenDates(Date startd01te, Date endDate) {

        Calendar start = Calendar.getInstance();
        start.setTime(startd01te);

        Calendar end = Calendar.getInstance();
        end.setTime(endDate);

        int monthsBetween = 0;
        int dateDiff = end.get(Calendar.DAY_OF_MONTH) - start.get(Calendar.DAY_OF_MONTH);

        if (dateDiff < 0) {
            int borrrow = end.getActualMaximum(Calendar.DAY_OF_MONTH);
            dateDiff = (end.get(Calendar.DAY_OF_MONTH) + borrrow) - start.get(Calendar.DAY_OF_MONTH);
            monthsBetween--;

            if (dateDiff > 0) {
                monthsBetween++;
            }
        }

        monthsBetween += end.get(Calendar.MONTH) - start.get(Calendar.MONTH);
        monthsBetween += (end.get(Calendar.YEAR) - start.get(Calendar.YEAR)) * 12;
        return monthsBetween;
    }

    public static Calendar getCalendarDate(String dd, String mm, String yy) {

        String dob = String.format("%02d", Integer.valueOf(dd)) + "-"
                + String.format("%02d", Integer.valueOf(mm))
                + "-" + yy;
        SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy");
        Calendar calendar = Calendar.getInstance();
        try {
            Date date = sdf.parse(dob);
            calendar.setTime(date);
            return calendar;

        } catch (ParseException e) {
            e.printStackTrace();
        }
        return calendar;
    }

    public static long ageInMonthsByDOB(Calendar cal) {
        Date dob = cal.getTime();
        Date today = new Date();
        Long diff = today.getTime() - dob.getTime();
        double ageInMonths = (diff / (24 * 60 * 60 * 1000)) / 30.4375;
        long age = (long) Math.floor(ageInMonths);
        return age;
    }

    public static long ageInMonthsByDOB(String dateStr) {
        SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy");
        Calendar cal = getCalendarDate(dateStr);
        Date dob = cal.getTime();
        Date today = new Date();
        Long diff = today.getTime() - dob.getTime();
        long ageInMonths = (diff / (24 * 60 * 60 * 1000)) / 30;
        return ageInMonths;
    }

    public static Calendar getCalendarDate(String value) {
        SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy");
        Calendar calendar = Calendar.getInstance();
        try {
            Date date = sdf.parse(value);
            calendar.setTime(date);
            return calendar;

        } catch (ParseException e) {
            e.printStackTrace();
        }
        return calendar;
    }

    public static long ageInMonths(String year, String month) {
        long ageInMonths = (Integer.valueOf(year) * 12) + Integer.valueOf(month);
        return ageInMonths;
    }

    public static void errorCheck(final Context context, String error) {
        AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(
                context);
        alertDialogBuilder
                .setMessage(error)
                .setCancelable(false)
                .setPositiveButton("Ok",
                        new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog,
                                                int id) {
                                dialog.cancel();
                            }
                        });
        AlertDialog alert = alertDialogBuilder.create();
        alert.show();
    }

    public static void errorCountDialog(final Context context, final Activity activity, String error) {
        AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(
                context);
        alertDialogBuilder
                .setMessage(error)
                .setCancelable(false)
                .setPositiveButton("Discard",
                        new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog,
                                                int id) {
//                                MainApp.memFlag--;
                                activity.finish();
                            }
                        });
        alertDialogBuilder.setNegativeButton("Cancel",
                new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        dialog.cancel();
                    }
                });
        AlertDialog alert = alertDialogBuilder.create();
        alert.show();
    }

    public static void finishActivity(final Context context, final Activity activity) {
        AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(
                context);
        alertDialogBuilder
                .setMessage("Do you want to Exit??")
                .setCancelable(false)
                .setPositiveButton("Yes",
                        new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog,
                                                int id) {
                                activity.finish();
                            }
                        });
        alertDialogBuilder.setNegativeButton("No",
                new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        dialog.cancel();
                    }
                });
        AlertDialog alert = alertDialogBuilder.create();
        alert.show();
    }

    public static void endActivity(final Context context, final Activity activity) {
        AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(
                context);
        alertDialogBuilder
                .setMessage("Do you want to Exit??")
                .setCancelable(false)
                .setPositiveButton("Yes",
                        new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog,
                                                int id) {
                                activity.finish();
                                Intent end_intent = new Intent(context, EndingActivity.class);
                                end_intent.putExtra("complete", false);
                                context.startActivity(end_intent);
                            }
                        });
        alertDialogBuilder.setNegativeButton("No",
                new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        dialog.cancel();
                    }
                });
        AlertDialog alert = alertDialogBuilder.create();
        alert.show();
    }

    public static String convertd01teFormat(String dateStr) {
        SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy");
        try {
            Date d = sdf.parse(dateStr);
            return new SimpleDateFormat("dd/MM/yyyy").format(d);
        } catch (ParseException ex) {
            ex.printStackTrace();
        }

        return "";
    }

    @Override
    public void onCreate() {
        super.onCreate();
        TypefaceUtil.overrideFont(getApplicationContext(), "SERIF", "fonts/MBLateefi.ttf"); // font from assets: "assets/fonts/Roboto-Regular.ttf

        deviceId = Settings.Secure.getString(getApplicationContext().getContentResolver(),
                Settings.Secure.ANDROID_ID);


        // Requires Permission for GPS -- android.permission.ACCESS_FINE_LOCATION
        // Requires Additional permission for 5.0 -- android.hardware.location.gps
        locationManager = (LocationManager) getSystemService(Context.LOCATION_SERVICE);
        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            // TODO: Consider calling
            //    ActivityCompat#requestPermissions
            // here to request the missing permissions, and then overriding
            //   public void onRequestPermissionsResult(int requestCode, String[] permissions,
            //                                          int[] grantResults)
            // to handle the case where the user grants the permission. See the documentation
            // for ActivityCompat#requestPermissions for more details.
            return;
        }
        locationManager.requestLocationUpdates(
                LocationManager.GPS_PROVIDER,
                MINIMUM_TIME_BETWEEN_UPDATES,
                MINIMUM_DISTANCE_CHANGE_FOR_UPDATES,
                new GPSLocationListener() // Implement this class from code

        );


//        Initialize Dead Member List
//        deadMembers = new ArrayList<String>();

    }

    protected void showCurrentLocation() {

        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            // TODO: Consider calling
            //    ActivityCompat#requestPermissions
            // here to request the missing permissions, and then overriding
            //   public void onRequestPermissionsResult(int requestCode, String[] permissions,
            //                                          int[] grantResults)
            // to handle the case where the user grants the permission. See the documentation
            // for ActivityCompat#requestPermissions for more details.
            return;
        }
        Location location = locationManager.getLastKnownLocation(LocationManager.GPS_PROVIDER);

        if (location != null) {
            String message = String.format(
                    "Current Location \n Longitude: %1$s \n Latitude: %2$s",
                    location.getLongitude(), location.getLatitude()
            );
            //Toast.makeText(getApplicationContext(), message,
            //Toast.LENGTH_SHORT).show();
        }

    }

    protected boolean isBetterLocation(Location location, Location currentBestLocation) {
        if (currentBestLocation == null) {
            // A new location is always better than no location
            return true;
        }

        // Check whether the new location fix is newer or older
        long timeDelta = location.getTime() - currentBestLocation.getTime();
        boolean isSignificantlyNewer = timeDelta > TWO_MINUTES;
        boolean isSignificantlyOlder = timeDelta < -TWO_MINUTES;
        boolean isNewer = timeDelta > 0;

        // If it's been more than two minutes since the current location, use the new location
        // because the user has likely moved
        if (isSignificantlyNewer) {
            return true;

            // If the new location is more than two minutes older, it must be worse
        } else if (isSignificantlyOlder) {
            return false;
        }

        // Check whether the new location fix is more or less accurate
        int accuracyDelta = (int) (location.getAccuracy() - currentBestLocation.getAccuracy());
        boolean isLessAccurate = accuracyDelta > 0;
        boolean isMoreAccurate = accuracyDelta < 0;
        boolean isSignificantlyLessAccurate = accuracyDelta > 200;

        // Check if the old and new location are from the same provider
        boolean isFromSameProvider = isSameProvider(location.getProvider(),
                currentBestLocation.getProvider());

        // Determine location quality using a combination of timeliness and accuracy
        if (isMoreAccurate) {
            return true;
        } else if (isNewer && !isLessAccurate) {
            return true;
        } else return isNewer && !isSignificantlyLessAccurate && isFromSameProvider;
    }

    /**
     * Checks whether two providers are the same
     */
    private boolean isSameProvider(String provider1, String provider2) {
        if (provider1 == null) {
            return provider2 == null;
        }
        return provider1.equals(provider2);
    }

    public static class deadMemberClass {

        int position;
        String DSSId;

        public deadMemberClass(int i, String s) {
            position = i;
            DSSId = s;
        }

        public int getPosition() {
            return position;
        }

        public void setPosition(int i) {
            position = i;
        }

        public void setDSSId(String id) {
            DSSId = id;
        }

    }

    public class GPSLocationListener implements LocationListener {
        public void onLocationChanged(Location location) {

            SharedPreferences sharedPref = getSharedPreferences("GPSCoordinates", Context.MODE_PRIVATE);
            SharedPreferences.Editor editor = sharedPref.edit();

            String dt = DateFormat.format("dd-MM-yyyy HH:mm", Long.parseLong(sharedPref.getString("Time", "0"))).toString();

            Location bestLocation = new Location("storedProvider");
            bestLocation.setAccuracy(Float.parseFloat(sharedPref.getString("Accuracy", "0")));
            bestLocation.setTime(Long.parseLong(sharedPref.getString(dt, "0")));
            bestLocation.setLatitude(Float.parseFloat(sharedPref.getString("Latitude", "0")));
            bestLocation.setLongitude(Float.parseFloat(sharedPref.getString("Longitude", "0")));

            if (isBetterLocation(location, bestLocation)) {
                editor.putString("Longitude", String.valueOf(location.getLongitude()));
                editor.putString("Latitude", String.valueOf(location.getLatitude()));
                editor.putString("Accuracy", String.valueOf(location.getAccuracy()));
                editor.putString("Time", String.valueOf(location.getTime()));
                String date = DateFormat.format("dd-MM-yyyy HH:mm", Long.parseLong(String.valueOf(location.getTime()))).toString();
//                Toast.makeText(getApplicationContext(),
//                        "GPS Commit! LAT: " + String.valueOf(location.getLongitude()) +
//                                " LNG: " + String.valueOf(location.getLatitude()) +
//                                " Accuracy: " + String.valueOf(location.getAccuracy()) +
//                                " Time: " + date,
//                        Toast.LENGTH_SHORT).show();

                editor.apply();
            }
        }


        public void onStatusChanged(String s, int i, Bundle b) {
            showCurrentLocation();
        }

        public void onProviderDisabled(String s) {

        }

        public void onProviderEnabled(String s) {

        }
    }

    public static class YoungestChild {
        int serial;
        double age;

        public YoungestChild(int serial, double age) {
            this.serial = serial;
            this.age = age;
        }

        public int getSerial() {
            return serial;
        }

        public double getAge() {
            return age;
        }
    }

}