package edu.aku.ramshasaeed.tmk_midline19.contracts;

import android.database.Cursor;
import android.provider.BaseColumns;

import org.json.JSONException;
import org.json.JSONObject;

public class AnthroContract {

    private final String projectName = "UEN-TMK-MIDLINE";
    private String _ID = "";
    private String UID = "";
    private String _UUID = "";
    private String deviceID = "";
    private String formDate = ""; // Date
    private String user = ""; // Interviewer
    private String sI = "";
    private String istatus = "";
    private String devicetagID = "";
    private String synced = "";
    private String synced_date = "";

    public String getProjectName() {
        return projectName;
    }

    public String get_ID() {
        return _ID;
    }

    public void set_ID(String _ID) {
        this._ID = _ID;
    }

    public String getUID() {
        return UID;
    }

    public void setUID(String UID) {
        this.UID = UID;
    }

    public String get_UUID() {
        return _UUID;
    }

    public void set_UUID(String _UUID) {
        this._UUID = _UUID;
    }

    public String getDeviceID() {
        return deviceID;
    }

    public void setDeviceID(String deviceID) {
        this.deviceID = deviceID;
    }

    public String getFormDate() {
        return formDate;
    }

    public void setFormDate(String formDate) {
        this.formDate = formDate;
    }

    public String getUser() {
        return user;
    }

    public void setUser(String user) {
        this.user = user;
    }

    public String getsI() {
        return sI;
    }

    public void setsI(String sI) {
        this.sI = sI;
    }

    public String getIstatus() {
        return istatus;
    }

    public void setIstatus(String istatus) {
        this.istatus = istatus;
    }

    public String getDevicetagID() {
        return devicetagID;
    }

    public void setDevicetagID(String devicetagID) {
        this.devicetagID = devicetagID;
    }

    public String getSynced() {
        return synced;
    }

    public void setSynced(String synced) {
        this.synced = synced;
    }

    public String getSynced_date() {
        return synced_date;
    }

    public void setSynced_date(String synced_date) {
        this.synced_date = synced_date;
    }

    public AnthroContract Sync(JSONObject jsonObject) throws Exception {

        this._ID = jsonObject.getString(SingleAnthros.COLUMN_ID);
        this._UUID = jsonObject.getString(SingleAnthros.COLUMN_UUID);
        this.UID = jsonObject.getString(SingleAnthros.COLUMN_UID);
        this.sI = jsonObject.getString(SingleAnthros.COLUMN_SI);
        this.formDate = jsonObject.getString(SingleAnthros.COLUMN_FORMDATE);
        this.user = jsonObject.getString(SingleAnthros.COLUMN_USER);
        this.deviceID = jsonObject.getString(SingleAnthros.COLUMN_DEVICEID);
        this.synced = jsonObject.getString(SingleAnthros.COLUMN_SYNCED);
        this.synced_date = jsonObject.getString(SingleAnthros.COLUMN_SYNCED_DATE);
        this.istatus = jsonObject.getString(SingleAnthros.COLUMN_ISTATUS);
        this.devicetagID = jsonObject.getString(SingleAnthros.COLUMN_DEVICETAGID);

        return this;

    }

    public AnthroContract Hydrate(Cursor cursor) {
        this._ID = cursor.getString(cursor.getColumnIndex(SingleAnthros.COLUMN_ID));
        this._UUID = cursor.getString(cursor.getColumnIndex(SingleAnthros.COLUMN_UUID));
        this.UID = cursor.getString(cursor.getColumnIndex(SingleAnthros.COLUMN_UID));
        this.sI = cursor.getString(cursor.getColumnIndex(SingleAnthros.COLUMN_SI));
        this.formDate = cursor.getString(cursor.getColumnIndex(SingleAnthros.COLUMN_FORMDATE));
        this.user = cursor.getString(cursor.getColumnIndex(SingleAnthros.COLUMN_USER));
        this.deviceID = cursor.getString(cursor.getColumnIndex(SingleAnthros.COLUMN_DEVICEID));
        this.istatus = cursor.getString(cursor.getColumnIndex(SingleAnthros.COLUMN_ISTATUS));
        this.devicetagID = cursor.getString(cursor.getColumnIndex(SingleAnthros.COLUMN_DEVICETAGID));

        return this;

    }

    public JSONObject toJSONObject() throws JSONException {

        JSONObject json = new JSONObject();

        json.put(SingleAnthros.COLUMN_ID, this._ID == null ? JSONObject.NULL : this._ID);
        json.put(SingleAnthros.COLUMN_UUID, this._UUID == null ? JSONObject.NULL : this._UUID);
        json.put(SingleAnthros.COLUMN_UID, this.UID == null ? JSONObject.NULL : this.UID);
        if (!this.sI.equals("")) {
            json.put(SingleAnthros.COLUMN_SI, this.sI.equals("") ? JSONObject.NULL : new JSONObject(this.sI));
        }
        json.put(SingleAnthros.COLUMN_FORMDATE, this.formDate == null ? JSONObject.NULL : this.formDate);
        json.put(SingleAnthros.COLUMN_USER, this.user == null ? JSONObject.NULL : this.user);
        json.put(SingleAnthros.COLUMN_DEVICEID, this.deviceID == null ? JSONObject.NULL : this.deviceID);
        json.put(SingleAnthros.COLUMN_ISTATUS, this.istatus == null ? JSONObject.NULL : this.istatus);
        json.put(SingleAnthros.COLUMN_DEVICETAGID, this.devicetagID == null ? JSONObject.NULL : this.devicetagID);

        return json;
    }

    public static abstract class SingleAnthros implements BaseColumns {

        public static final String TABLE_NAME = "anthro";
        public static final String COLUMN_NAME_NULLABLE = "NULLHACK";

        public static final String COLUMN_PROJECT_NAME = "project_name";
        public static final String COLUMN_ID = "_id";
        public static final String COLUMN_UUID = "uuid";
        public static final String COLUMN_UID = "uid";
        public static final String COLUMN_SI = "sI";
        public static final String COLUMN_FORMDATE = "formdate";
        public static final String COLUMN_USER = "username";
        public static final String COLUMN_DEVICEID = "deviceid";
        public static final String COLUMN_ISTATUS = "istatus";
        public static final String COLUMN_DEVICETAGID = "tagid";
        public static final String COLUMN_SYNCED = "synced";
        public static final String COLUMN_SYNCED_DATE = "synced_date";

        public static String _URL = "anthro.php";
    }
}