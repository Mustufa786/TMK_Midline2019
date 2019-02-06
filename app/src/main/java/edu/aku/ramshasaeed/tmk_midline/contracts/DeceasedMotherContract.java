package edu.aku.ramshasaeed.tmk_midline.contracts;

import android.database.Cursor;
import android.provider.BaseColumns;

import org.json.JSONException;
import org.json.JSONObject;

/**
 * Created by gul.sanober on 4/20/2017.
 */

public class DeceasedMotherContract {

    private final String projectName = "UEN TMK";
    private String _ID = "";
    private String _UID = "";
    private String _UUID = "";
    private String formDate = "";
    private String deviceId = "";
    private String user = "";
    private String sE = "";
    private String synced = "";
    private String syncedDate = "";
    private String devicetagID = "";

    public DeceasedMotherContract() {
    }

    public String getProjectName() {
        return projectName;
    }

    public String get_ID() {
        return _ID;
    }

    public void set_ID(String _ID) {
        this._ID = _ID;
    }

    public String get_UID() {
        return _UID;
    }

    public void set_UID(String _UID) {
        this._UID = _UID;
    }

    public String get_UUID() {
        return _UUID;
    }

    public void set_UUID(String _UUID) {
        this._UUID = _UUID;
    }

    public String getFormDate() {
        return formDate;
    }

    public void setFormDate(String formDate) {
        this.formDate = formDate;
    }

    public String getd05viceId() {
        return deviceId;
    }

    public void setd05viceId(String deviceId) {
        this.deviceId = deviceId;
    }

    public String getUser() {
        return user;
    }


    public void setUser(String user) {
        this.user = user;
    }


    public String getSynced() {
        return synced;
    }

    public void setSynced(String synced) {
        this.synced = synced;
    }

    public String getSyncedDate() {
        return syncedDate;
    }

    public void setSyncedDate(String syncedDate) {
        this.syncedDate = syncedDate;
    }

    public String getsE() {

        return sE;
    }

    public void setsE(String sE) {
        this.sE = sE;
    }

    public String getd05vicetagID() {
        return devicetagID;
    }

    public void setd05vicetagID(String devicetagID) {
        this.devicetagID = devicetagID;
    }

    public DeceasedMotherContract Sync(JSONObject jsonObject) throws JSONException {

        this._ID = jsonObject.getString(DeceasedMotherContract.DeceasedMother.COLUMN_ID);
        this._UID = jsonObject.getString(DeceasedMotherContract.DeceasedMother.COLUMN_UID);
        this._UUID = jsonObject.getString(DeceasedMotherContract.DeceasedMother.COLUMN_UUID);
        this.formDate = jsonObject.getString(DeceasedMotherContract.DeceasedMother.COLUMN_FORMDATE);
        this.deviceId = jsonObject.getString(DeceasedMotherContract.DeceasedMother.COLUMN_DEVICEID);
        this.user = jsonObject.getString(DeceasedMotherContract.DeceasedMother.COLUMN_USER);
        this.sE = jsonObject.getString(DeceasedMother.COLUMN_SE);
        this.synced = jsonObject.getString(DeceasedMotherContract.DeceasedMother.COLUMN_SYNCED);
        this.syncedDate = jsonObject.getString(DeceasedMotherContract.DeceasedMother.COLUMN_SYNCED_DATE);
        this.devicetagID = jsonObject.getString(DeceasedMotherContract.DeceasedMother.COLUMN_DEVICETAGID);

        return this;

    }

    public DeceasedMotherContract Hydrate(Cursor cursor) {

        this._ID = cursor.getString(cursor.getColumnIndex(DeceasedMotherContract.DeceasedMother.COLUMN_ID));
        this._UID = cursor.getString(cursor.getColumnIndex(DeceasedMotherContract.DeceasedMother.COLUMN_UID));
        this._UUID = cursor.getString(cursor.getColumnIndex(DeceasedMotherContract.DeceasedMother.COLUMN_UUID));
        this.formDate = cursor.getString(cursor.getColumnIndex(DeceasedMotherContract.DeceasedMother.COLUMN_FORMDATE));
        this.deviceId = cursor.getString(cursor.getColumnIndex(DeceasedMotherContract.DeceasedMother.COLUMN_DEVICEID));
        this.user = cursor.getString(cursor.getColumnIndex(DeceasedMotherContract.DeceasedMother.COLUMN_USER));
        this.sE = cursor.getString(cursor.getColumnIndex(DeceasedMother.COLUMN_SE));
        this.devicetagID = cursor.getString(cursor.getColumnIndex(DeceasedMotherContract.DeceasedMother.COLUMN_DEVICETAGID));

        return this;

    }


    public JSONObject toJSONObject() throws JSONException {

        JSONObject json = new JSONObject();

        json.put(DeceasedMother.COLUMN_ID, this._ID == null ? JSONObject.NULL : this._ID);
        json.put(DeceasedMother.COLUMN_UID, this._UID == null ? JSONObject.NULL : this._UID);
        json.put(DeceasedMother.COLUMN_UUID, this._UUID == null ? JSONObject.NULL : this._UUID);
        json.put(DeceasedMother.COLUMN_FORMDATE, this.formDate == null ? JSONObject.NULL : this.formDate);
        json.put(DeceasedMother.COLUMN_DEVICEID, this.deviceId == null ? JSONObject.NULL : this.deviceId);
        json.put(DeceasedMother.COLUMN_USER, this.user == null ? JSONObject.NULL : this.user);
        if (!this.sE.equals("")) {

            json.put(DeceasedMother.COLUMN_SE, this.sE.equals("") ? JSONObject.NULL : new JSONObject(this.sE));
        }
        json.put(DeceasedMother.COLUMN_DEVICETAGID, this.devicetagID == null ? JSONObject.NULL : this.devicetagID);

        return json;
    }

    public static abstract class DeceasedMother implements BaseColumns {

        public static final String TABLE_NAME = "deceasedmothers";
        public static final String COLUMN_NAME_NULLABLE = "NULLHACK";

        public static final String COLUMN_PROJECT_NAME = "project_name";
        public static final String COLUMN_ID = "_id";
        public static final String COLUMN_UID = "uid";
        public static final String COLUMN_UUID = "uuid";
        public static final String COLUMN_FORMDATE = "formdate";
        public static final String COLUMN_DEVICEID = "deviceid";
        public static final String COLUMN_USER = "user";
        public static final String COLUMN_SE = "se";
        public static final String COLUMN_SYNCED = "synced";
        public static final String COLUMN_SYNCED_DATE = "sync_date";
        public static final String COLUMN_DEVICETAGID = "tagid";

        public static String _URL = "deceasedmother.php";
    }

}
