package edu.aku.ramshasaeed.tmk_midline.contracts;

import android.database.Cursor;
import android.provider.BaseColumns;

import org.json.JSONException;
import org.json.JSONObject;

/**
 * Created by gul.sanober on 4/20/2017.
 */

public class DeceasedChildContract {

    private final String projectName = "UEN TMK";
    private String _ID = "";
    private String _UID = "";
    private String _UUID = "";
    private String formDate = "";
    private String deviceId = "";
    private String user = "";
    private String sF = "";
    private String synced = "";
    private String syncedDate = "";
    private String devicetagID = "";

    public DeceasedChildContract() {
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

    public String getDeviceId() {
        return deviceId;
    }

    public void setDeviceId(String deviceId) {
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

    public String getsF() {

        return sF;
    }

    public void setsF(String sF) {
        this.sF = sF;
    }

    public String getDevicetagID() {
        return devicetagID;
    }

    public void setDevicetagID(String devicetagID) {
        this.devicetagID = devicetagID;
    }

    public DeceasedChildContract Sync(JSONObject jsonObject) throws JSONException {

        this._ID = jsonObject.getString(DeceasedChildContract.DeceasedChild.COLUMN_ID);
        this._UID = jsonObject.getString(DeceasedChildContract.DeceasedChild.COLUMN_UID);
        this._UUID = jsonObject.getString(DeceasedChildContract.DeceasedChild.COLUMN_UUID);
        this.formDate = jsonObject.getString(DeceasedChildContract.DeceasedChild.COLUMN_FORMDATE);
        this.deviceId = jsonObject.getString(DeceasedChildContract.DeceasedChild.COLUMN_DEVICEID);
        this.user = jsonObject.getString(DeceasedChildContract.DeceasedChild.COLUMN_USER);
        this.sF = jsonObject.getString(DeceasedChild.COLUMN_SF);
        this.synced = jsonObject.getString(DeceasedChildContract.DeceasedChild.COLUMN_SYNCED);
        this.syncedDate = jsonObject.getString(DeceasedChildContract.DeceasedChild.COLUMN_SYNCED_DATE);
        this.devicetagID = jsonObject.getString(DeceasedChildContract.DeceasedChild.COLUMN_DEVICETAGID);

        return this;

    }

    public DeceasedChildContract Hydrate(Cursor cursor) {

        this._ID = cursor.getString(cursor.getColumnIndex(DeceasedChildContract.DeceasedChild.COLUMN_ID));
        this._UID = cursor.getString(cursor.getColumnIndex(DeceasedChildContract.DeceasedChild.COLUMN_UID));
        this._UUID = cursor.getString(cursor.getColumnIndex(DeceasedChildContract.DeceasedChild.COLUMN_UUID));
        this.formDate = cursor.getString(cursor.getColumnIndex(DeceasedChildContract.DeceasedChild.COLUMN_FORMDATE));
        this.deviceId = cursor.getString(cursor.getColumnIndex(DeceasedChildContract.DeceasedChild.COLUMN_DEVICEID));
        this.user = cursor.getString(cursor.getColumnIndex(DeceasedChildContract.DeceasedChild.COLUMN_USER));
        this.sF = cursor.getString(cursor.getColumnIndex(DeceasedChild.COLUMN_SF));
        this.devicetagID = cursor.getString(cursor.getColumnIndex(DeceasedChildContract.DeceasedChild.COLUMN_DEVICETAGID));

        return this;

    }


    public JSONObject toJSONObject() throws JSONException {

        JSONObject json = new JSONObject();

        json.put(DeceasedChild.COLUMN_ID, this._ID == null ? JSONObject.NULL : this._ID);
        json.put(DeceasedChild.COLUMN_UID, this._UID == null ? JSONObject.NULL : this._UID);
        json.put(DeceasedChild.COLUMN_UUID, this._UUID == null ? JSONObject.NULL : this._UUID);
        json.put(DeceasedChild.COLUMN_FORMDATE, this.formDate == null ? JSONObject.NULL : this.formDate);
        json.put(DeceasedChild.COLUMN_DEVICEID, this.deviceId == null ? JSONObject.NULL : this.deviceId);
        json.put(DeceasedChild.COLUMN_USER, this.user == null ? JSONObject.NULL : this.user);
        if (!this.sF.equals("")) {

            json.put(DeceasedChild.COLUMN_SF, this.sF.equals("") ? JSONObject.NULL : new JSONObject(this.sF));
        }
        json.put(DeceasedChild.COLUMN_DEVICETAGID, this.devicetagID == null ? JSONObject.NULL : this.devicetagID);

        return json;
    }

    public static abstract class DeceasedChild implements BaseColumns {

        public static final String TABLE_NAME = "DeceasedChilds";
        public static final String COLUMN_NAME_NULLABLE = "NULLHACK";

        public static final String COLUMN_PROJECT_NAME = "project_name";
        public static final String COLUMN_ID = "_id";
        public static final String COLUMN_UID = "uid";
        public static final String COLUMN_UUID = "uuid";
        public static final String COLUMN_FORMDATE = "formdate";
        public static final String COLUMN_DEVICEID = "deviceid";
        public static final String COLUMN_USER = "user";
        public static final String COLUMN_SF = "sf";
        public static final String COLUMN_SYNCED = "synced";
        public static final String COLUMN_SYNCED_DATE = "sync_date";
        public static final String COLUMN_DEVICETAGID = "tagid";

        public static String _URL = "deceasedchild.php";
    }

}
