package edu.aku.ramshasaeed.tmk_midline_monitor.contracts;


import android.database.Cursor;
import android.provider.BaseColumns;

import org.json.JSONException;
import org.json.JSONObject;

public class VillagesContract {

    private static final String TAG = "Villages_CONTRACT";
    //    String ID;
    String villagename;
    String area_code;
    //    String taluka;
    String villagecode;

    public VillagesContract() {
        // Default Constructor
    }

    public VillagesContract(VillagesContract l) {
//        this.ID = l.getID();
        this.villagename = l.getVillagename();
        this.area_code = l.getArea_code();
//        this.taluka = l.getTaluka();
        this.villagecode = l.getVillagecode();
    }

    public VillagesContract Sync(JSONObject jsonObject) throws JSONException {
//        this.ID = jsonObject.getString(singleVillages.COLUMN_ID);
        this.villagename = jsonObject.getString(singleVillages.COLUMN_VILLAGE_NAME);
        this.area_code = jsonObject.getString(singleVillages.COLUMN_AREA_CODE);
//        this.taluka = jsonObject.getString(singleVillages.COLUMN_TALUKA);
        this.villagecode = jsonObject.getString(singleVillages.COLUMN_VILLAGE_CODE);

        return this;

    }

    public VillagesContract HydrateVillages(Cursor cursor) {
//        this.ID = cursor.getString(cursor.getColumnIndex(singleVillages.COLUMN_ID));
        this.area_code = cursor.getString(cursor.getColumnIndex(singleVillages.COLUMN_AREA_CODE));
//        this.taluka = cursor.getString(cursor.getColumnIndex(singleVillages.COLUMN_TALUKA));
        this.villagename = cursor.getString(cursor.getColumnIndex(singleVillages.COLUMN_VILLAGE_NAME));
        this.villagecode = cursor.getString(cursor.getColumnIndex(singleVillages.COLUMN_VILLAGE_CODE));
        return this;
    }
/*
    public String getID() {
        return ID;
    }

    public void setID(String ID) {
        this.ID = ID;
    }*/

    public String getVillagename() {
        return villagename;
    }

    public void setVillagename(String villagename) {
        this.villagename = villagename;
    }

    public String getArea_code() {
        return area_code;
    }

    public void setArea_code(String area_code) {
        this.area_code = area_code;
    }

/*    public String getTaluka() {
        return taluka;
    }

    public void setTaluka(String taluka) {
        this.taluka = taluka;
    }*/

    public String getVillagecode() {
        return villagecode;
    }

    public void setVillagecode(String villagecode) {
        this.villagecode = villagecode;
    }

    public JSONObject toJSONObject() throws JSONException {

        JSONObject json = new JSONObject();
//        json.put(singleVillages.COLUMN_ID, this.ID == null ? JSONObject.NULL : this.ID);
        json.put(singleVillages.COLUMN_VILLAGE_NAME, this.villagename == null ? JSONObject.NULL : this.villagename);
        json.put(singleVillages.COLUMN_AREA_CODE, this.area_code == null ? JSONObject.NULL : this.area_code);
//        json.put(singleVillages.COLUMN_TALUKA, this.taluka == null ? JSONObject.NULL : this.taluka);
        json.put(singleVillages.COLUMN_VILLAGE_CODE, this.villagecode == null ? JSONObject.NULL : this.villagecode);

        return json;
    }


    public static abstract class singleVillages implements BaseColumns {

        public static final String TABLE_NAME = "villages";
        //        public static final String COLUMN_ID = "_id";
        public static final String COLUMN_VILLAGE_NAME = "village_name";
        public static final String COLUMN_AREA_CODE = "area_code";
        //        public static final String COLUMN_TALUKA = "taluka";
        public static final String COLUMN_VILLAGE_CODE = "village_code";

        public static final String _URI = "villages.php";
    }
}