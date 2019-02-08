package edu.aku.ramshasaeed.tmk_midline.contracts;

import android.database.Cursor;
import android.provider.BaseColumns;

import org.json.JSONException;
import org.json.JSONObject;

public class FamilyMembersContract {

    private final String projectName = "UEN-TMK-MIDLINE";
    private String _ID = "";
    private String _UID = "";
    private String _UUID = "";
    private String formDate = "";
    private String deviceId = "";
    private String user = "";
    private String devicetagID = "";
    private String name = "";
    private String dob = "";
    private String age = "";
    private String sB = "";
    private String synced = "";
    private String syncedDate = "";
    private String istatus = "";
    private String serialNo = "";
    private String motherId = "";
    private String type = "";
    private String app_ver = "";
    private String clusterNo = "";
    private String hhNo = "";



    public FamilyMembersContract() {
    }

    public FamilyMembersContract(String name, String age, String serialNo, String dob) {
        this.name = name;

        this.age = age;
//        this.ageLess2 = ageLess2;
        this.serialNo = serialNo;
        this.dob = dob;
    }

    public FamilyMembersContract(FamilyMembersContract fm) {
        this.name = fm.name;
        this.age = fm.age;
        this.serialNo = fm.serialNo;
        this.dob = fm.dob;
    }

    public String getprojectName() {
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

    public String getformDate() {
        return formDate;
    }

    public void setformDate(String formDate) {
        this.formDate = formDate;
    }

    public String getdeviceId() {
        return deviceId;
    }

    public void setdeviceId(String deviceId) {
        this.deviceId = deviceId;
    }

    public String getuser() {
        return user;
    }

    public void setuser(String user) {
        this.user = user;
    }

    public String getdevicetagID() {
        return devicetagID;
    }

    public void setdevicetagID(String devicetagID) {
        this.devicetagID = devicetagID;
    }

    public String getname() {
        return name;
    }

    public void setname(String name) {
        this.name = name;
    }

    public String getdob() {
        return dob;
    }

    public void setdob(String dob) {
        this.dob = dob;
    }

    public String getage() {
        return age;
    }

    public void setage(String age) {
        this.age = age;
    }

    public String getsB() {
        return sB;
    }

    public void setsB(String sB) {
        this.sB = sB;
    }

    public String getsynced() {
        return synced;
    }

    public void setsynced(String synced) {
        this.synced = synced;
    }

    public String getsyncedDate() {
        return syncedDate;
    }

    public void setsyncedDate(String syncedDate) {
        this.syncedDate = syncedDate;
    }

    public String getistatus() {
        return istatus;
    }

    public void setistatus(String istatus) {
        this.istatus = istatus;
    }

    public String getserialNo() {
        return serialNo;
    }

    public void setserialNo(String serialNo) {
        this.serialNo = serialNo;
    }

    public String getmotherId() {
        return motherId;
    }

    public void setmotherId(String motherId) {
        this.motherId = motherId;
    }

    public String gettype() {
        return type;
    }

    public void settype(String type) {
        this.type = type;
    }

    public String getapp_ver() {
        return app_ver;
    }

    public void setapp_ver(String app_ver) {
        this.app_ver = app_ver;
    }

    public String getclusterNo() {
        return clusterNo;
    }

    public void setclusterNo(String clusterNo) {
        this.clusterNo = clusterNo;
    }

    public String gethhNo() {
        return hhNo;
    }

    public void sethhNo(String hhNo) {
        this.hhNo = hhNo;
    }

    public FamilyMembersContract Sync(JSONObject jsonObject) throws JSONException {

        this._ID= jsonObject.getString(familyMembers.COLUMN_ID);
        this._UID= jsonObject.getString(familyMembers.COLUMN_UID);
        this._UUID= jsonObject.getString(familyMembers.COLUMN_UUID);
        this.formDate= jsonObject.getString(familyMembers.COLUMN_FORMDATE);
        this.deviceId= jsonObject.getString(familyMembers.COLUMN_DEVICEID);
        this.user= jsonObject.getString(familyMembers.COLUMN_USER);
        this.devicetagID= jsonObject.getString(familyMembers.COLUMN_DEVICETAGID);
        this.name= jsonObject.getString(familyMembers.COLUMN_NAME);
        this.dob= jsonObject.getString(familyMembers.COLUMN_DOB);
        this.age= jsonObject.getString(familyMembers.COLUMN_AGE);
        this.sB= jsonObject.getString(familyMembers.COLUMN_SB);
        this.synced= jsonObject.getString(familyMembers.COLUMN_SYNCED);
        this.syncedDate= jsonObject.getString(familyMembers.COLUMN_SYNCED_DATE);
        this.istatus= jsonObject.getString(familyMembers.COLUMN_ISTATUS);
        this.serialNo= jsonObject.getString(familyMembers.COLUMN_SERIALNO);
        this.motherId= jsonObject.getString(familyMembers.COLUMN_MOTHERID);
        this.type= jsonObject.getString(familyMembers.COLUMN_TYPE);
        this.app_ver= jsonObject.getString(familyMembers.COLUMN_APP_VER);
        this.clusterNo= jsonObject.getString(familyMembers.COLUMN_CLUSTERNO);
        this.hhNo= jsonObject.getString(familyMembers.COLUMN_HHNO);


        return this;

    }

    public FamilyMembersContract Hydrate(Cursor cursor) {
        this._ID = cursor.getString(cursor.getColumnIndex(familyMembers.COLUMN_ID));
        this._UID = cursor.getString(cursor.getColumnIndex(familyMembers.COLUMN_UID));
        this._UUID = cursor.getString(cursor.getColumnIndex(familyMembers.COLUMN_UUID));
        this.formDate = cursor.getString(cursor.getColumnIndex(familyMembers.COLUMN_FORMDATE));
        this.deviceId = cursor.getString(cursor.getColumnIndex(familyMembers.COLUMN_DEVICEID));
        this.user = cursor.getString(cursor.getColumnIndex(familyMembers.COLUMN_USER));
        this.devicetagID = cursor.getString(cursor.getColumnIndex(familyMembers.COLUMN_DEVICETAGID));
        this.name = cursor.getString(cursor.getColumnIndex(familyMembers.COLUMN_NAME));
        this.dob = cursor.getString(cursor.getColumnIndex(familyMembers.COLUMN_DOB));
        this.age = cursor.getString(cursor.getColumnIndex(familyMembers.COLUMN_AGE));
        this.sB = cursor.getString(cursor.getColumnIndex(familyMembers.COLUMN_SB));
        this.synced = cursor.getString(cursor.getColumnIndex(familyMembers.COLUMN_SYNCED));
        this.syncedDate = cursor.getString(cursor.getColumnIndex(familyMembers.COLUMN_SYNCED_DATE));
        this.istatus = cursor.getString(cursor.getColumnIndex(familyMembers.COLUMN_ISTATUS));
        this.serialNo = cursor.getString(cursor.getColumnIndex(familyMembers.COLUMN_SERIALNO));
        this.motherId = cursor.getString(cursor.getColumnIndex(familyMembers.COLUMN_MOTHERID));
        this.type = cursor.getString(cursor.getColumnIndex(familyMembers.COLUMN_TYPE));
        this.app_ver = cursor.getString(cursor.getColumnIndex(familyMembers.COLUMN_APP_VER));
        this.clusterNo = cursor.getString(cursor.getColumnIndex(familyMembers.COLUMN_CLUSTERNO));
        this.hhNo = cursor.getString(cursor.getColumnIndex(familyMembers.COLUMN_HHNO));

        return this;

    }


    public JSONObject toJSONObject() throws JSONException {

        JSONObject json = new JSONObject();
        json.put(familyMembers.COLUMN_PROJECT_NAME, this.projectName == null ? JSONObject.NULL : this.projectName);
        json.put(familyMembers.COLUMN_ID, this._ID == null ? JSONObject.NULL : this._ID);
        json.put(familyMembers.COLUMN_UID, this._UID == null ? JSONObject.NULL : this._UID);
        json.put(familyMembers.COLUMN_UUID, this._UUID == null ? JSONObject.NULL : this._UUID);
        json.put(familyMembers.COLUMN_FORMDATE, this.formDate == null ? JSONObject.NULL : this.formDate);
        json.put(familyMembers.COLUMN_DEVICEID, this.deviceId == null ? JSONObject.NULL : this.deviceId);
        json.put(familyMembers.COLUMN_USER, this.user == null ? JSONObject.NULL : this.user);
        json.put(familyMembers.COLUMN_DEVICETAGID, this.devicetagID == null ? JSONObject.NULL : this.devicetagID);
        json.put(familyMembers.COLUMN_NAME, this.name == null ? JSONObject.NULL : this.name);
        json.put(familyMembers.COLUMN_DOB, this.dob == null ? JSONObject.NULL : this.dob);
        json.put(familyMembers.COLUMN_AGE, this.age == null ? JSONObject.NULL : this.age);
        if (!this.sB.equals("")) {

            json.put(familyMembers.COLUMN_SB, this.sB.equals("") ? JSONObject.NULL : new JSONObject(this.sB));
        }
        json.put(familyMembers.COLUMN_SYNCED, this.synced == null ? JSONObject.NULL : this.synced);
        json.put(familyMembers.COLUMN_SYNCED_DATE, this.syncedDate == null ? JSONObject.NULL : this.syncedDate);
        json.put(familyMembers.COLUMN_ISTATUS, this.istatus == null ? JSONObject.NULL : this.istatus);
        json.put(familyMembers.COLUMN_SERIALNO, this.serialNo == null ? JSONObject.NULL : this.serialNo);
        json.put(familyMembers.COLUMN_MOTHERID, this.motherId == null ? JSONObject.NULL : this.motherId);
        json.put(familyMembers.COLUMN_TYPE, this.type == null ? JSONObject.NULL : this.type);
        json.put(familyMembers.COLUMN_APP_VER, this.app_ver == null ? JSONObject.NULL : this.app_ver);
        json.put(familyMembers.COLUMN_CLUSTERNO, this.clusterNo == null ? JSONObject.NULL : this.clusterNo);
        json.put(familyMembers.COLUMN_HHNO, this.hhNo == null ? JSONObject.NULL : this.hhNo);



        return json;
    }

    public static abstract class familyMembers implements BaseColumns {

        public static final String TABLE_NAME = "familymembers";
        public static final String COLUMN_NAME_NULLABLE = "NULLHACK";

        public static final String COLUMN_PROJECT_NAME = "project_name";


        public static final String COLUMN_ID = "_id";
        public static final String COLUMN_UID = "_uid";
        public static final String COLUMN_UUID = "_uuid";
        public static final String COLUMN_FORMDATE = "formdate";
        public static final String COLUMN_DEVICEID = "deviceid";
        public static final String COLUMN_USER = "user";
        public static final String COLUMN_DEVICETAGID = "devicetagid";
        public static final String COLUMN_NAME = "name";
        public static final String COLUMN_DOB = "dob";
        public static final String COLUMN_AGE = "age";
        public static final String COLUMN_SB = "sb";
        public static final String COLUMN_SYNCED = "synced";
        public static final String COLUMN_SYNCED_DATE = "synceddate";
        public static final String COLUMN_ISTATUS = "istatus";
        public static final String COLUMN_SERIALNO = "serialno";
        public static final String COLUMN_MOTHERID = "motherid";
        public static final String COLUMN_TYPE = "type";
        public static final String COLUMN_APP_VER = "app_ver";
        public static final String COLUMN_CLUSTERNO = "clusterno";
        public static final String COLUMN_HHNO = "hhno";

        public static String _URL = "familymembers.php";
    }
}
