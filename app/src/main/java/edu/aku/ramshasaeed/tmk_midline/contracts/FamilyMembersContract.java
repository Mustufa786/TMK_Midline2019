package edu.aku.ramshasaeed.tmk_midline.contracts;

import android.database.Cursor;
import android.provider.BaseColumns;

import org.json.JSONException;
import org.json.JSONObject;

/**
 * Created by hassan.naqvi on 11/30/2016.
 */

public class FamilyMembersContract {

    private final String projectName = "UEN TMK";
    private String _ID = "";
    private String REF_ID = "";
    private String _UID = "";
    private String _UUID = "";
    private String _DATE = "";
    private String formDate = "";
    private String deviceId = "";
    private String user = "";
    private String name = "";
    private String ageLess5 = "";
    private String ageLess2 = "";
    private String dob = "";

    /*    private String dss_id_hh = "";
        private String dss_id_f = "";
        private String dss_id_m = "";
        private String dss_id_h = "";
        private String dss_id_member = "";
        private String prevs_dss_id_member = "";
        private String site_code = "";
        private String ageY = "";
        private String ageM = "";
        private String ageD = "";
        private String gender = "";
        private String is_head = "";
        private String relation_hh = "";
        private String current_status = "";
        private String current_statusX = "";
        private String current_date = "";
        private String dod = "";
        private String m_status = "";
        private String education = "";
        private String educationX = "";
        private String occupation = "";
        private String occupationX = "";
        private String member_type = "";
        private String rsvp = "";
        private String update_flag = "";*/
    private String sB = "";
    private String synced = "";
    private String syncedDate = "";
    private String remarks = "";
    private String istatus = "";
    private String serialNo = "";

    private String devicetagID = "";


    public FamilyMembersContract() {
    }

    public FamilyMembersContract(String name, String age, String serialNo, String dob) {
        this.name = name;
        this.ageLess5 = age;
//        this.ageLess2 = ageLess2;
        this.serialNo = serialNo;
        this.dob = dob;
    }

    public FamilyMembersContract(FamilyMembersContract fm) {
        this.name = fm.name;
        this.ageLess5 = fm.ageLess5;
        this.ageLess2 = fm.ageLess2;
        this.serialNo = fm.serialNo;
        this.dob = fm.dob;
    }

    public String getDeviceId() {
        return deviceId;
    }

    public void setDeviceId(String deviceId) {
        this.deviceId = deviceId;
    }

    public String getDevicetagID() {
        return devicetagID;
    }

    public void setDevicetagID(String devicetagID) {
        this.devicetagID = devicetagID;
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

    public String getREF_ID() {
        return REF_ID;
    }

    public void setREF_ID(String REF_ID) {
        this.REF_ID = REF_ID;
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

    public String get_DATE() {
        return _DATE;
    }

    public void set_DATE(String _DATE) {
        this._DATE = _DATE;
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

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAgeLess5() {
        return ageLess5;
    }

    public void setAgeLess5(String ageLess5) {
        this.ageLess5 = ageLess5;
    }

    public String getAgeLess2() {
        return ageLess2;
    }

    public void setAgeLess2(String ageLess2) {
        this.ageLess2 = ageLess2;
    }

    public String getDob() {
        return dob;
    }

    public void setDob(String dob) {
        this.dob = dob;
    }
/*
    public String getDss_id_hh() {
        return dss_id_hh;
    }

    public void setDss_id_hh(String dss_id_hh) {
        this.dss_id_hh = dss_id_hh;
    }

    public String getDss_id_f() {
        return dss_id_f;
    }

    public void setDss_id_f(String dss_id_f) {
        this.dss_id_f = dss_id_f;
    }

    public String getDss_id_m() {
        return dss_id_m;
    }

    public void setDss_id_m(String dss_id_m) {
        this.dss_id_m = dss_id_m;
    }

    public String getDss_id_h() {
        return dss_id_h;
    }

    public void setDss_id_h(String dss_id_h) {
        this.dss_id_h = dss_id_h;
    }

    public String getDss_id_member() {
        return dss_id_member;
    }

    public void setDss_id_member(String dss_id_member) {
        this.dss_id_member = dss_id_member;
    }

    public String getPrevs_dss_id_member() {
        return prevs_dss_id_member;
    }

    public void setPrevs_dss_id_member(String prevs_dss_id_member) {
        this.prevs_dss_id_member = prevs_dss_id_member;
    }

    public String getSite_code() {
        return site_code;
    }

    public void setSite_code(String site_code) {
        this.site_code = site_code;
    }

    public String getAgeY() {
        return ageY;
    }

    public void setAgeY(String ageY) {
        this.ageY = ageY;
    }

    public String getAgeM() {
        return ageM;
    }

    public void setAgeM(String ageM) {
        this.ageM = ageM;
    }

    public String getAgeD() {
        return ageD;
    }

    public void setAgeD(String ageD) {
        this.ageD = ageD;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getIs_head() {
        return is_head;
    }

    public void setIs_head(String is_head) {
        this.is_head = is_head;
    }

    public String getRelation_hh() {
        return relation_hh;
    }

    public void setRelation_hh(String relation_hh) {
        this.relation_hh = relation_hh;
    }

    public String getCurrent_status() {
        return current_status;
    }

    public void setCurrent_status(String current_status) {
        this.current_status = current_status;
    }

    public String getCurrent_date() {
        return current_date;
    }

    public void setCurrent_date(String current_date) {
        this.current_date = current_date;
    }

    public String getDod() {
        return dod;
    }

    public void setDod(String dod) {
        this.dod = dod;
    }

    public String getM_status() {
        return m_status;
    }

    public void setM_status(String m_status) {
        this.m_status = m_status;
    }

    public String getEducation() {
        return education;
    }

    public void setEducation(String education) {
        this.education = education;
    }

    public String getOccupation() {
        return occupation;
    }

    public void setOccupation(String occupation) {
        this.occupation = occupation;
    }

    public String getMember_type() {
        return member_type;
    }

    public void setMember_type(String member_type) {
        this.member_type = member_type;
    }

    public String getUpdate_flag() {
        return update_flag;
    }

    public void setUpdate_flag(String update_flag) {
        this.update_flag = update_flag;
    }

    public String getCurrent_statusX() {
        return current_statusX;
    }

    public void setCurrent_statusX(String current_statusX) {
        this.current_statusX = current_statusX;
    }

    public String getEducationX() {
        return educationX;
    }

    public void setEducationX(String educationX) {
        this.educationX = educationX;
    }

    public String getOccupationX() {
        return occupationX;
    }

    public void setOccupationX(String occupationX) {
        this.occupationX = occupationX;
    }

    public String getRsvp() {
        return rsvp;
    }

    public void setRsvp(String rsvp) {
        this.rsvp = rsvp;
    }*/

    public String getsB() {
        return sB;
    }

    public void setsB(String sB) {
        this.sB = sB;
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

    public String getRemarks() {
        return remarks;
    }

    public void setRemarks(String remarks) {
        this.remarks = remarks;
    }

    public String getSerialNo() {
        return serialNo;
    }

    public void setSerialNo(String serialNo) {
        this.serialNo = serialNo;
    }

    public String getIstatus() {
        return istatus;
    }

    public void setIstatus(String istatus) {
        this.istatus = istatus;
    }

    public String getd05vicetagID() {
        return devicetagID;
    }

    public void setd05vicetagID(String devicetagID) {
        this.devicetagID = devicetagID;
    }

    public FamilyMembersContract Sync(JSONObject jsonObject) throws JSONException {

        this._ID = jsonObject.getString(familyMembers.COLUMN_ID);
//        this.REF_ID = jsonObject.getString(familyMembers.COLUMN_REF_ID);
        this._UID = jsonObject.getString(familyMembers.COLUMN_UID);
        this._UUID = jsonObject.getString(familyMembers.COLUMN_UUID);
        //this._DATE = jsonObject.getString(familyMembers.COLUMN_DATE);
        this.formDate = jsonObject.getString(familyMembers.COLUMN_FORMDATE);
        this.deviceId = jsonObject.getString(familyMembers.COLUMN_DEVICEID);
        this.user = jsonObject.getString(familyMembers.COLUMN_USER);
/*        this.dss_id_hh = jsonObject.getString(familyMembers.COLUMN_DSS_ID_HH);
        this.dss_id_f = jsonObject.getString(familyMembers.COLUMN_DSS_ID_F);
        this.dss_id_m = jsonObject.getString(familyMembers.COLUMN_DSS_ID_M);
        this.dss_id_h = jsonObject.getString(familyMembers.COLUMN_DSS_ID_H);
        this.dss_id_member = jsonObject.getString(familyMembers.COLUMN_DSS_ID_MEMBER);
        this.prevs_dss_id_member = jsonObject.getString(familyMembers.COLUMN_PREVS_DSS_ID_MEMBER);
        this.site_code = jsonObject.getString(familyMembers.COLUMN_SITE_CODE);
        this.name = jsonObject.getString(familyMembers.COLUMN_NAME);
        this.ageLess5 = jsonObject.getString(familyMembers.COLUMN_DOB);
        this.ageY = jsonObject.getString(familyMembers.COLUMN_AGEY);
        this.ageM = jsonObject.getString(familyMembers.COLUMN_AGEM);
        this.ageD = jsonObject.getString(familyMembers.COLUMN_AGED);
        this.gender = jsonObject.getString(familyMembers.COLUMN_GENDER);
        this.is_head = jsonObject.getString(familyMembers.COLUMN_IS_HEAD);
        this.relation_hh = jsonObject.getString(familyMembers.COLUMN_RELATION_HH);
        this.current_status = jsonObject.getString(familyMembers.COLUMN_CURRENT_STATUS);
        this.current_statusX = jsonObject.getString(familyMembers.COLUMN_CURRENT_STATUSX);
        this.current_date = jsonObject.getString(familyMembers.COLUMN_CURRENT_DATE);
        this.dod = jsonObject.getString(familyMembers.COLUMN_DOD);
        this.m_status = jsonObject.getString(familyMembers.COLUMN_M_STATUS);
        this.education = jsonObject.getString(familyMembers.COLUMN_EDUCATION);
        this.educationX = jsonObject.getString(familyMembers.COLUMN_EDUCATIONX);
        this.occupation = jsonObject.getString(familyMembers.COLUMN_OCCUPATION);
        this.occupationX = jsonObject.getString(familyMembers.COLUMN_OCCUPATIONX);
        this.member_type = jsonObject.getString(familyMembers.COLUMN_MEMBER_TYPE);
        this.rsvp = jsonObject.getString(familyMembers.COLUMN_RSVP);
        this.update_flag = jsonObject.getString(familyMembers.COLUMN_UPDATE_FLAG);*/
        this.sB = jsonObject.getString(familyMembers.COLUMN_SB);
//        this.serialNo = jsonObject.getString(familyMembers.COLUMN_SERIAL_NO);
        this.synced = jsonObject.getString(familyMembers.COLUMN_SYNCED);
        this.syncedDate = jsonObject.getString(familyMembers.COLUMN_SYNCED_DATE);
//        this.remarks = jsonObject.getString(familyMembers.COLUMN_REMARKS);
        this.istatus = jsonObject.getString(familyMembers.COLUMN_ISTATUS);
        this.devicetagID = jsonObject.getString(familyMembers.COLUMN_DEVICETAGID);

        return this;

    }

    public FamilyMembersContract Hydrate(Cursor cursor) {

        this._ID = cursor.getString(cursor.getColumnIndex(familyMembers.COLUMN_ID));
//        this.REF_ID = cursor.getString(cursor.getColumnIndex(familyMembers.COLUMN_REF_ID));
        this._UID = cursor.getString(cursor.getColumnIndex(familyMembers.COLUMN_UID));
        this._UUID = cursor.getString(cursor.getColumnIndex(familyMembers.COLUMN_UUID));
        //this._DATE = cursor.getString(cursor.getColumnIndex(familyMembers.COLUMN_DATE));
        this.formDate = cursor.getString(cursor.getColumnIndex(familyMembers.COLUMN_FORMDATE));
        this.deviceId = cursor.getString(cursor.getColumnIndex(familyMembers.COLUMN_DEVICEID));
        this.user = cursor.getString(cursor.getColumnIndex(familyMembers.COLUMN_USER));
/*        this.dss_id_hh = cursor.getString(cursor.getColumnIndex(familyMembers.COLUMN_DSS_ID_HH));
        this.dss_id_f = cursor.getString(cursor.getColumnIndex(familyMembers.COLUMN_DSS_ID_F));
        this.dss_id_m = cursor.getString(cursor.getColumnIndex(familyMembers.COLUMN_DSS_ID_M));
        this.dss_id_h = cursor.getString(cursor.getColumnIndex(familyMembers.COLUMN_DSS_ID_H));
        this.dss_id_member = cursor.getString(cursor.getColumnIndex(familyMembers.COLUMN_DSS_ID_MEMBER));
        this.prevs_dss_id_member = cursor.getString(cursor.getColumnIndex(familyMembers.COLUMN_PREVS_DSS_ID_MEMBER));
        this.site_code = cursor.getString(cursor.getColumnIndex(familyMembers.COLUMN_SITE_CODE));
        this.name = cursor.getString(cursor.getColumnIndex(familyMembers.COLUMN_NAME));
        this.ageLess5 = cursor.getString(cursor.getColumnIndex(familyMembers.COLUMN_DOB));
        this.ageY = cursor.getString(cursor.getColumnIndex(familyMembers.COLUMN_AGEY));
        this.ageM = cursor.getString(cursor.getColumnIndex(familyMembers.COLUMN_AGEM));
        this.ageD = cursor.getString(cursor.getColumnIndex(familyMembers.COLUMN_AGED));
        this.gender = cursor.getString(cursor.getColumnIndex(familyMembers.COLUMN_GENDER));
        this.is_head = cursor.getString(cursor.getColumnIndex(familyMembers.COLUMN_IS_HEAD));
        this.relation_hh = cursor.getString(cursor.getColumnIndex(familyMembers.COLUMN_RELATION_HH));
        this.current_status = cursor.getString(cursor.getColumnIndex(familyMembers.COLUMN_CURRENT_STATUS));
        this.current_statusX = cursor.getString(cursor.getColumnIndex(familyMembers.COLUMN_CURRENT_STATUSX));
        this.current_date = cursor.getString(cursor.getColumnIndex(familyMembers.COLUMN_CURRENT_DATE));
        this.dod = cursor.getString(cursor.getColumnIndex(familyMembers.COLUMN_DOD));
        this.m_status = cursor.getString(cursor.getColumnIndex(familyMembers.COLUMN_M_STATUS));
        this.education = cursor.getString(cursor.getColumnIndex(familyMembers.COLUMN_EDUCATION));
        this.educationX = cursor.getString(cursor.getColumnIndex(familyMembers.COLUMN_EDUCATIONX));
        this.occupation = cursor.getString(cursor.getColumnIndex(familyMembers.COLUMN_OCCUPATION));
        this.occupationX = cursor.getString(cursor.getColumnIndex(familyMembers.COLUMN_OCCUPATIONX));
        this.member_type = cursor.getString(cursor.getColumnIndex(familyMembers.COLUMN_MEMBER_TYPE));
        this.rsvp = cursor.getString(cursor.getColumnIndex(familyMembers.COLUMN_RSVP));
        this.update_flag = cursor.getString(cursor.getColumnIndex(familyMembers.COLUMN_UPDATE_FLAG));*/
        this.sB = cursor.getString(cursor.getColumnIndex(familyMembers.COLUMN_SB));
//        this.serialNo = cursor.getString(cursor.getColumnIndex(familyMembers.COLUMN_SERIAL_NO));
//        this.remarks = cursor.getString(cursor.getColumnIndex(familyMembers.COLUMN_REMARKS));
        this.istatus = cursor.getString(cursor.getColumnIndex(familyMembers.COLUMN_ISTATUS));
        this.devicetagID = cursor.getString(cursor.getColumnIndex(familyMembers.COLUMN_DEVICETAGID));

        return this;

    }


    public JSONObject toJSONObject() throws JSONException {

        JSONObject json = new JSONObject();

        json.put(familyMembers.COLUMN_ID, this._ID == null ? JSONObject.NULL : this._ID);
//        json.put(familyMembers.COLUMN_REF_ID, this.REF_ID == null ? JSONObject.NULL : this.REF_ID);
        json.put(familyMembers.COLUMN_UID, this._UID == null ? JSONObject.NULL : this._UID);
        json.put(familyMembers.COLUMN_UUID, this._UUID == null ? JSONObject.NULL : this._UUID);
        //json.put(familyMembers.COLUMN_DATE, this._DATE == null ? JSONObject.NULL : this._DATE);
        json.put(familyMembers.COLUMN_FORMDATE, this.formDate == null ? JSONObject.NULL : this.formDate);
        json.put(familyMembers.COLUMN_DEVICEID, this.deviceId == null ? JSONObject.NULL : this.deviceId);
        json.put(familyMembers.COLUMN_USER, this.user == null ? JSONObject.NULL : this.user);
/*        json.put(familyMembers.COLUMN_DSS_ID_HH, this.dss_id_hh == null ? JSONObject.NULL : this.dss_id_hh);
        json.put(familyMembers.COLUMN_DSS_ID_F, this.dss_id_f == null ? JSONObject.NULL : this.dss_id_f);
        json.put(familyMembers.COLUMN_DSS_ID_M, this.dss_id_m == null ? JSONObject.NULL : this.dss_id_m);
        json.put(familyMembers.COLUMN_DSS_ID_H, this.dss_id_h == null ? JSONObject.NULL : this.dss_id_h);
        json.put(familyMembers.COLUMN_DSS_ID_MEMBER, this.dss_id_member == null ? JSONObject.NULL : this.dss_id_member);
        json.put(familyMembers.COLUMN_PREVS_DSS_ID_MEMBER, this.prevs_dss_id_member == null ? JSONObject.NULL : this.prevs_dss_id_member);
        json.put(familyMembers.COLUMN_SITE_CODE, this.site_code == null ? JSONObject.NULL : this.site_code);
        json.put(familyMembers.COLUMN_NAME, this.name == null ? JSONObject.NULL : this.name);
        json.put(familyMembers.COLUMN_DOB, this.ageLess5 == null ? JSONObject.NULL : this.ageLess5);
        json.put(familyMembers.COLUMN_AGEY, this.ageY == null ? JSONObject.NULL : this.ageY);
        json.put(familyMembers.COLUMN_AGEM, this.ageM == null ? JSONObject.NULL : this.ageM);
        json.put(familyMembers.COLUMN_AGED, this.ageD == null ? JSONObject.NULL : this.ageD);
        json.put(familyMembers.COLUMN_GENDER, this.gender == null ? JSONObject.NULL : this.gender);
        json.put(familyMembers.COLUMN_IS_HEAD, this.is_head == null ? JSONObject.NULL : this.is_head);
        json.put(familyMembers.COLUMN_RELATION_HH, this.relation_hh == null ? JSONObject.NULL : this.relation_hh);
        json.put(familyMembers.COLUMN_CURRENT_STATUS, this.current_status == null ? JSONObject.NULL : this.current_status);
        json.put(familyMembers.COLUMN_CURRENT_STATUSX, this.current_statusX == null ? JSONObject.NULL : this.current_statusX);
        json.put(familyMembers.COLUMN_CURRENT_DATE, this.current_date == null ? JSONObject.NULL : this.current_date);
        json.put(familyMembers.COLUMN_DOD, this.dod == null ? JSONObject.NULL : this.dod);
        json.put(familyMembers.COLUMN_M_STATUS, this.m_status == null ? JSONObject.NULL : this.m_status);
        json.put(familyMembers.COLUMN_EDUCATION, this.education == null ? JSONObject.NULL : this.education);
        json.put(familyMembers.COLUMN_EDUCATIONX, this.educationX == null ? JSONObject.NULL : this.educationX);
        json.put(familyMembers.COLUMN_OCCUPATION, this.occupation == null ? JSONObject.NULL : this.occupation);
        json.put(familyMembers.COLUMN_OCCUPATIONX, this.occupationX == null ? JSONObject.NULL : this.occupationX);
        json.put(familyMembers.COLUMN_MEMBER_TYPE, this.member_type == null ? JSONObject.NULL : this.member_type);
        json.put(familyMembers.COLUMN_RSVP, this.rsvp == null ? JSONObject.NULL : this.rsvp);
        json.put(familyMembers.COLUMN_UPDATE_FLAG, this.update_flag == null ? JSONObject.NULL : this.update_flag);*/
        if (!this.sB.equals("")) {

            json.put(familyMembers.COLUMN_SB, this.sB.equals("") ? JSONObject.NULL : new JSONObject(this.sB));
        }
//        json.put(familyMembers.COLUMN_SERIAL_NO, this.serialNo == null ? JSONObject.NULL : this.serialNo);
//        json.put(familyMembers.COLUMN_REMARKS, this.remarks == null ? JSONObject.NULL : this.remarks);
        json.put(familyMembers.COLUMN_PROJECT_NAME, this.projectName == null ? JSONObject.NULL : this.projectName);
        json.put(familyMembers.COLUMN_ISTATUS, this.istatus == null ? JSONObject.NULL : this.istatus);
        json.put(familyMembers.COLUMN_DEVICETAGID, this.devicetagID == null ? JSONObject.NULL : this.devicetagID);


        return json;
    }

    public static abstract class familyMembers implements BaseColumns {

        public static final String TABLE_NAME = "familymembers";
        public static final String COLUMN_NAME_NULLABLE = "NULLHACK";

        public static final String COLUMN_PROJECT_NAME = "project_name";
        public static final String COLUMN_ID = "_id";
        public static final String COLUMN_UID = "uid";
        public static final String COLUMN_UUID = "uuid";
        //public static final String COLUMN_DATE = "_date";
        public static final String COLUMN_FORMDATE = "formdate";
        public static final String COLUMN_DEVICEID = "deviceid";
        public static final String COLUMN_USER = "user";
/*        public static final String COLUMN_DSS_ID_HH = "dss_id_hh";
        public static final String COLUMN_DSS_ID_F = "dss_id_f";
        public static final String COLUMN_DSS_ID_M = "dss_id_m";
        public static final String COLUMN_DSS_ID_H = "dss_id_h";
        public static final String COLUMN_DSS_ID_MEMBER = "dss_id_member";
        public static final String COLUMN_PREVS_DSS_ID_MEMBER = "prevs_dss_id_member";
        public static final String COLUMN_SITE_CODE = "site_code";
        public static final String COLUMN_NAME = "name";
        public static final String COLUMN_DOB = "ageLess5";
        public static final String COLUMN_AGEY = "agey";
        public static final String COLUMN_AGEM = "agem";
        public static final String COLUMN_AGED = "aged";
        public static final String COLUMN_GENDER = "gender";
        public static final String COLUMN_IS_HEAD = "is_head";
        public static final String COLUMN_RELATION_HH = "relation_hh";
        public static final String COLUMN_CURRENT_STATUS = "current_status";
        public static final String COLUMN_CURRENT_STATUSX = "current_statusx";
        public static final String COLUMN_CURRENT_DATE = "status_date";
        public static final String COLUMN_DOD = "dod";
        public static final String COLUMN_M_STATUS = "m_status";
        public static final String COLUMN_EDUCATION = "education";
        public static final String COLUMN_EDUCATIONX = "educationx";
        public static final String COLUMN_OCCUPATION = "occupation";
        public static final String COLUMN_OCCUPATIONX = "occupationx";
        public static final String COLUMN_REMARKS = "remarks";
        public static final String COLUMN_MEMBER_TYPE = "member_type";
        public static final String COLUMN_UPDATE_FLAG = "updated_flag";
        public static final String COLUMN_RSVP = "isresp";
        public static final String COLUMN_REF_ID = "refid";*/

        public static final String COLUMN_SB = "sB";
        public static final String COLUMN_SYNCED = "synced";
        public static final String COLUMN_SYNCED_DATE = "sync_date";
        public static final String COLUMN_ISTATUS = "istatus";

        public static final String COLUMN_DEVICETAGID = "tagid";
//        public static final String COLUMN_SERIAL_NO = "serial";

        public static String _URL = "familymembers.php";
    }
}
