package edu.aku.ramshasaeed.tmk_midline_monitor19.contracts;

import android.database.Cursor;
import android.provider.BaseColumns;

import org.json.JSONException;
import org.json.JSONObject;

public class FormsContract {

    private final String projectName = "UEN-TMK-MIDLINE";
    //private final String surveyType = "SN";
    private String _ID = "";
    private String _UID = "";
    private String formDate = ""; // Date
    private String user = ""; // Interviewer
    private String istatus = ""; // Interview Status
    private String istatus96x = ""; // Interview Status
    private String sA = "";
    private String sB = "";
    private String sE = "";
    private String sF = "";
    private String sG = "";
    private String sH = "";
    private String sI = "";
    private String sJ = "";
    private String sK = "";
    private String sL = "";
    private String appVer = "";
    private String gpsLat = "";
    private String gpsLng = "";
    private String gpsDT = "";
    private String gpsAcc = "";
    private String deviceid = "";
    private String devicetagID = "";
    private String cluster_no = "";
    private String hhno = "";
    private String synced = "";
    private String synced_date = "";


    public FormsContract() {
    }

    public FormsContract Sync(JSONObject jsonObject) throws JSONException {

        this._ID = jsonObject.getString(FormsTable.COLUMN_ID);
        this._UID = jsonObject.getString(FormsTable.COLUMN_UID);
        this.formDate = jsonObject.getString(FormsTable.COLUMN_FORMDATE);
        this.user = jsonObject.getString(FormsTable.COLUMN_USER);
        this.istatus = jsonObject.getString(FormsTable.COLUMN_ISTATUS);
        this.istatus96x = jsonObject.getString(FormsTable.COLUMN_ISTATUS96X);
        this.sA = jsonObject.getString(FormsTable.COLUMN_SA);
        this.sB = jsonObject.getString(FormsTable.COLUMN_SB);
        this.sE = jsonObject.getString(FormsTable.COLUMN_SE);
        this.sF = jsonObject.getString(FormsTable.COLUMN_SF);
        this.sG = jsonObject.getString(FormsTable.COLUMN_SG);
        this.sH = jsonObject.getString(FormsTable.COLUMN_SH);
        this.sI = jsonObject.getString(FormsTable.COLUMN_SI);
        this.sJ = jsonObject.getString(FormsTable.COLUMN_SJ);
        this.sK = jsonObject.getString(FormsTable.COLUMN_SK);
        this.sL = jsonObject.getString(FormsTable.COLUMN_SL);
        this.appVer = jsonObject.getString(FormsTable.COLUMN_APPVER);
        this.gpsLat = jsonObject.getString(FormsTable.COLUMN_GPSLAT);
        this.gpsLng = jsonObject.getString(FormsTable.COLUMN_GPSLNG);
        this.gpsDT = jsonObject.getString(FormsTable.COLUMN_GPSDT);
        this.gpsAcc = jsonObject.getString(FormsTable.COLUMN_GPSACC);
        this.synced = jsonObject.getString(FormsTable.COLUMN_SYNCED);
        this.synced_date = jsonObject.getString(FormsTable.COLUMN_SYNCED_DATE);
        this.deviceid = jsonObject.getString(FormsTable.COLUMN_DEVICEID);
        this.devicetagID = jsonObject.getString(FormsTable.COLUMN_DEVICETAGID);
        this.cluster_no = jsonObject.getString(FormsTable.COLUMN_CLUSTER);
        this.hhno = jsonObject.getString(FormsTable.COLUMN_HHNO);

        return this;

    }

    public FormsContract Hydrate(Cursor cursor) {
        this._ID = cursor.getString(cursor.getColumnIndex(FormsTable.COLUMN_ID));
        this._UID = cursor.getString(cursor.getColumnIndex(FormsTable.COLUMN_UID));
        this.formDate = cursor.getString(cursor.getColumnIndex(FormsTable.COLUMN_FORMDATE));
        this.user = cursor.getString(cursor.getColumnIndex(FormsTable.COLUMN_USER));
        this.istatus = cursor.getString(cursor.getColumnIndex(FormsTable.COLUMN_ISTATUS));
        this.istatus96x = cursor.getString(cursor.getColumnIndex(FormsTable.COLUMN_ISTATUS96X));
        this.sA = cursor.getString(cursor.getColumnIndex(FormsTable.COLUMN_SA));
        this.sB = cursor.getString(cursor.getColumnIndex(FormsTable.COLUMN_SB));
        this.sE = cursor.getString(cursor.getColumnIndex(FormsTable.COLUMN_SE));
        this.sF = cursor.getString(cursor.getColumnIndex(FormsTable.COLUMN_SF));
        this.sG = cursor.getString(cursor.getColumnIndex(FormsTable.COLUMN_SG));
        this.sH = cursor.getString(cursor.getColumnIndex(FormsTable.COLUMN_SH));
        this.sI = cursor.getString(cursor.getColumnIndex(FormsTable.COLUMN_SI));
        this.sJ = cursor.getString(cursor.getColumnIndex(FormsTable.COLUMN_SJ));
        this.sK = cursor.getString(cursor.getColumnIndex(FormsTable.COLUMN_SK));
        this.sL = cursor.getString(cursor.getColumnIndex(FormsTable.COLUMN_SL));
        this.appVer = cursor.getString(cursor.getColumnIndex(FormsTable.COLUMN_APPVER));
        this.gpsLat = cursor.getString(cursor.getColumnIndex(FormsTable.COLUMN_GPSLAT));
        this.gpsLng = cursor.getString(cursor.getColumnIndex(FormsTable.COLUMN_GPSLNG));
        this.gpsDT = cursor.getString(cursor.getColumnIndex(FormsTable.COLUMN_GPSDT));
        this.gpsAcc = cursor.getString(cursor.getColumnIndex(FormsTable.COLUMN_GPSACC));
        this.synced = cursor.getString(cursor.getColumnIndex(FormsTable.COLUMN_SYNCED));
        this.synced_date = cursor.getString(cursor.getColumnIndex(FormsTable.COLUMN_SYNCED_DATE));
        this.deviceid = cursor.getString(cursor.getColumnIndex(FormsTable.COLUMN_DEVICEID));
        this.devicetagID = cursor.getString(cursor.getColumnIndex(FormsTable.COLUMN_DEVICETAGID));
        this.cluster_no = cursor.getString(cursor.getColumnIndex(FormsTable.COLUMN_CLUSTER));
        this.hhno = cursor.getString(cursor.getColumnIndex(FormsTable.COLUMN_HHNO));

        // TODO:

        return this;

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

    public String getformDate() {
        return formDate;
    }

    public void setformDate(String formDate) {
        this.formDate = formDate;
    }

    public String getuser() {
        return user;
    }

    public void setuser(String user) {
        this.user = user;
    }

    public String getistatus() {
        return istatus;
    }

    public void setistatus(String istatus) {
        this.istatus = istatus;
    }

    public String getistatus96x() {
        return istatus96x;
    }

    public void setistatus96x(String istatus96x) {
        this.istatus96x = istatus96x;
    }

    public String getsA() {
        return sA;
    }

    public void setsA(String sA) {
        this.sA = sA;
    }

    public String getsB() {
        return sB;
    }

    public void setsB(String sB) {
        this.sB = sB;
    }

    public String getsE() {
        return sE;
    }

    public void setsE(String sE) {
        this.sE = sE;
    }

    public String getsF() {
        return sF;
    }

    public void setsF(String sF) {
        this.sF = sF;
    }

    public String getsG() {
        return sG;
    }

    public void setsG(String sG) {
        this.sG = sG;
    }

    public String getsH() {
        return sH;
    }

    public void setsH(String sH) {
        this.sH = sH;
    }

    public String getsI() {
        return sI;
    }

    public void setsI(String sI) {
        this.sI = sI;
    }

    public String getsJ() {
        return sJ;
    }

    public void setsJ(String sJ) {
        this.sJ = sJ;
    }

    public String getsK() {
        return sK;
    }

    public void setsK(String sK) {
        this.sK = sK;
    }

    public String getappVer() {
        return appVer;
    }

    public void setappVer(String appVer) {
        this.appVer = appVer;
    }

    public String getgpsLat() {
        return gpsLat;
    }

    public void setgpsLat(String gpsLat) {
        this.gpsLat = gpsLat;
    }

    public String getgpsLng() {
        return gpsLng;
    }

    public void setgpsLng(String gpsLng) {
        this.gpsLng = gpsLng;
    }

    public String getgpsDT() {
        return gpsDT;
    }

    public void setgpsDT(String gpsDT) {
        this.gpsDT = gpsDT;
    }

    public String getgpsAcc() {
        return gpsAcc;
    }

    public void setgpsAcc(String gpsAcc) {
        this.gpsAcc = gpsAcc;
    }

    public String getdeviceid() {
        return deviceid;
    }

    public void setdeviceid(String deviceid) {
        this.deviceid = deviceid;
    }

    public String getdevicetagID() {
        return devicetagID;
    }

    public void setdevicetagID(String devicetagID) {
        this.devicetagID = devicetagID;
    }

    public String getsynced() {
        return synced;
    }

    public void setsynced(String synced) {
        this.synced = synced;
    }

    public String getsynced_date() {
        return synced_date;
    }

    public void setsynced_date(String synced_date) {
        this.synced_date = synced_date;
    }

    public String getcluster_no() {
        return cluster_no;
    }

    public void setcluster_no(String cluster_no) {
        this.cluster_no = cluster_no;
    }

    public String gethhno() {
        return hhno;
    }

    public void sethhno(String hhno) {
        this.hhno = hhno;
    }

    public String getsL() {
        return sL;
    }

    public void setsL(String sL) {
        this.sL = sL;
    }

    public JSONObject toJSONObject() throws JSONException {

        JSONObject json = new JSONObject();

        json.put(FormsTable.COLUMN_PROJECT_NAME, this.projectName == null ? JSONObject.NULL : this.projectName);
        json.put(FormsTable.COLUMN_ID, this._ID == null ? JSONObject.NULL : this._ID);
        json.put(FormsTable.COLUMN_UID, this._UID == null ? JSONObject.NULL : this._UID);
        json.put(FormsTable.COLUMN_FORMDATE, this.formDate == null ? JSONObject.NULL : this.formDate);
        json.put(FormsTable.COLUMN_USER, this.user == null ? JSONObject.NULL : this.user);
        json.put(FormsTable.COLUMN_ISTATUS, this.istatus == null ? JSONObject.NULL : this.istatus);
        json.put(FormsTable.COLUMN_ISTATUS96X, this.istatus96x == null ? JSONObject.NULL : this.istatus96x);

        if (!this.sA.equals("")) {

            json.put(FormsTable.COLUMN_SA, this.sA.equals("") ? JSONObject.NULL : new JSONObject(this.sA));
        }
        if (!this.sB.equals("")) {

            json.put(FormsTable.COLUMN_SB, this.sB.equals("") ? JSONObject.NULL : new JSONObject(this.sB));
        }
        if (!this.sE.equals("")) {
            json.put(FormsTable.COLUMN_SE, this.sE.equals("") ? JSONObject.NULL : new JSONObject(this.sE));
        }
        if (!this.sF.equals("")) {

            json.put(FormsTable.COLUMN_SF, this.sF.equals("") ? JSONObject.NULL : new JSONObject(this.sF));
        }
        if (!this.sG.equals("")) {

            json.put(FormsTable.COLUMN_SG, this.sG.equals("") ? JSONObject.NULL : new JSONObject(this.sG));

        }
        if (!this.sH.equals("")) {
            json.put(FormsTable.COLUMN_SH, this.sH.equals("") ? JSONObject.NULL : new JSONObject(this.sH));
        }

        if (!this.sI.equals("")) {

            json.put(FormsTable.COLUMN_SI, this.sI.equals("") ? JSONObject.NULL : new JSONObject(this.sI));
        }
        if (!this.sJ.equals("")) {

            json.put(FormsTable.COLUMN_SJ, this.sJ.equals("") ? JSONObject.NULL : new JSONObject(this.sJ));
        }
        if (!this.sK.equals("")) {

            json.put(FormsTable.COLUMN_SK, this.sK.equals("") ? JSONObject.NULL : new JSONObject(this.sK));
        }
        if (!this.sL.equals("")) {

            json.put(FormsTable.COLUMN_SL, this.sL.equals("") ? JSONObject.NULL : new JSONObject(this.sL));
        }

        json.put(FormsTable.COLUMN_APPVER, this.appVer == null ? JSONObject.NULL : this.appVer);
        json.put(FormsTable.COLUMN_GPSLAT, this.gpsLat == null ? JSONObject.NULL : this.gpsLat);
        json.put(FormsTable.COLUMN_GPSLNG, this.gpsLng == null ? JSONObject.NULL : this.gpsLng);
        json.put(FormsTable.COLUMN_GPSDT, this.gpsDT == null ? JSONObject.NULL : this.gpsDT);
        json.put(FormsTable.COLUMN_GPSACC, this.gpsAcc == null ? JSONObject.NULL : this.gpsAcc);
        json.put(FormsTable.COLUMN_DEVICEID, this.deviceid == null ? JSONObject.NULL : this.deviceid);
        json.put(FormsTable.COLUMN_DEVICETAGID, this.devicetagID == null ? JSONObject.NULL : this.devicetagID);
        return json;
    }


    public static abstract class FormsTable implements BaseColumns {

        public static final String TABLE_NAME = "forms";
        public static final String COLUMN_NAME_NULLABLE = "NULLHACK";
        public static final String COLUMN_PROJECT_NAME = "projectname";

        public static final String COLUMN_ID = "_id";
        public static final String COLUMN_UID = "_uid";
        public static final String COLUMN_FORMDATE = "formdate";
        public static final String COLUMN_CLUSTER = "cluster_no";
        public static final String COLUMN_HHNO = "hhno";
        public static final String COLUMN_USER = "username";
        public static final String COLUMN_ISTATUS = "istatus";
        public static final String COLUMN_ISTATUS96X = "istatus96x";
        public static final String COLUMN_SA = "sa"; // Info
        public static final String COLUMN_SB = "sb"; // Count
        public static final String COLUMN_SC = "sc"; // Not work
        public static final String COLUMN_SG = "sg"; // Not work
        public static final String COLUMN_SE = "sha"; // Direha
        public static final String COLUMN_SF = "shb"; // Ari
        public static final String COLUMN_SH = "sj";  // Breast Feed
        public static final String COLUMN_SI = "sk";  // Water
        public static final String COLUMN_SJ = "sl";  // Handwash
        public static final String COLUMN_SK = "sm";  // Spot check
        public static final String COLUMN_SL = "sn";  // Spot check
        public static final String COLUMN_APPVER = "app_version";
        public static final String COLUMN_GPSLAT = "gpslat";
        public static final String COLUMN_GPSLNG = "gpslng";
        public static final String COLUMN_GPSDT = "gpsdt";
        public static final String COLUMN_GPSACC = "gpsacc";
        public static final String COLUMN_SYNCED = "synced";
        public static final String COLUMN_SYNCED_DATE = "synced_date";
        public static final String COLUMN_DEVICEID = "deviceid";
        public static final String COLUMN_DEVICETAGID = "devicetagid";


        public static String _URL = "formsmonitor.php";
    }
}
