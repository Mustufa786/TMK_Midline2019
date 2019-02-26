package edu.aku.ramshasaeed.tmk_midline.core;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.MatrixCursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import org.json.JSONArray;
import org.json.JSONObject;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.List;

import edu.aku.ramshasaeed.tmk_midline.contracts.AreasContract;
import edu.aku.ramshasaeed.tmk_midline.contracts.AreasContract.singleAreas;
import edu.aku.ramshasaeed.tmk_midline.contracts.BLRandomContract;
import edu.aku.ramshasaeed.tmk_midline.contracts.BLRandomContract.singleChild;
import edu.aku.ramshasaeed.tmk_midline.contracts.FamilyMembersContract;
import edu.aku.ramshasaeed.tmk_midline.contracts.FamilyMembersContract.familyMembers;
import edu.aku.ramshasaeed.tmk_midline.contracts.FormsContract;
import edu.aku.ramshasaeed.tmk_midline.contracts.FormsContract.FormsTable;
import edu.aku.ramshasaeed.tmk_midline.contracts.SectionIIMContract;
import edu.aku.ramshasaeed.tmk_midline.contracts.SectionIIMContract.singleIm;
import edu.aku.ramshasaeed.tmk_midline.contracts.TalukasContract;
import edu.aku.ramshasaeed.tmk_midline.contracts.TalukasContract.singleTalukas;
import edu.aku.ramshasaeed.tmk_midline.contracts.UCsContract;
import edu.aku.ramshasaeed.tmk_midline.contracts.UCsContract.singleUCs;
import edu.aku.ramshasaeed.tmk_midline.contracts.UsersContract;
import edu.aku.ramshasaeed.tmk_midline.contracts.UsersContract.singleUser;
import edu.aku.ramshasaeed.tmk_midline.contracts.VersionAppContract;
import edu.aku.ramshasaeed.tmk_midline.contracts.VersionAppContract.VersionAppTable;
import edu.aku.ramshasaeed.tmk_midline.contracts.VillagesContract;
import edu.aku.ramshasaeed.tmk_midline.contracts.VillagesContract.singleVillages;


/**
 * Created by hassan.naqvi on 11/30/2016.
 */

public class DatabaseHelper extends SQLiteOpenHelper {

    public static final String DATABASE_NAME = "uentmkmidline.db";
    public static final String DB_NAME = "uentmkmidline_copy.db";
    public static final String PROJECT_NAME = "DMU-UENTMKMIDLINE";
    private static final int DATABASE_VERSION = 2;
    private static final String SQL_CREATE_FORMS = "CREATE TABLE "
            + FormsContract.FormsTable.TABLE_NAME + "("
            + FormsTable.COLUMN_ID + " INTEGER PRIMARY KEY AUTOINCREMENT,"
            + FormsTable.COLUMN_PROJECT_NAME + " TEXT,"
            + FormsTable.COLUMN_UID + " TEXT," +
            FormsTable.COLUMN_FORMDATE + " TEXT,"+
            FormsTable.COLUMN_USER + " TEXT,"+
            FormsTable.COLUMN_ISTATUS + " TEXT,"+
            FormsTable.COLUMN_ISTATUS96X + " TEXT,"+
            FormsTable.COLUMN_SA + " TEXT,"+
            FormsTable.COLUMN_SB + " TEXT,"+
            FormsTable.COLUMN_SE + " TEXT,"+
            FormsTable.COLUMN_SF + " TEXT,"+
            FormsTable.COLUMN_SG + " TEXT,"+
            FormsTable.COLUMN_SH + " TEXT,"+
            FormsTable.COLUMN_SI + " TEXT,"+
            FormsTable.COLUMN_SJ + " TEXT,"+
            FormsTable.COLUMN_SK + " TEXT,"+
            FormsTable.COLUMN_APPVER + " TEXT,"+
            FormsTable.COLUMN_GPSLAT + " TEXT,"+
            FormsTable.COLUMN_GPSLNG + " TEXT,"+
            FormsTable.COLUMN_GPSDT + " TEXT,"+
            FormsTable.COLUMN_GPSACC + " TEXT,"+
            FormsTable.COLUMN_SYNCED + " TEXT,"+
            FormsTable.COLUMN_SYNCED_DATE + " TEXT,"+
            FormsTable.COLUMN_DEVICEID + " TEXT,"+
            FormsTable.COLUMN_CLUSTER + " TEXT,"+
            FormsTable.COLUMN_HHNO + " TEXT,"+
            FormsTable.COLUMN_DEVICETAGID + " TEXT"
            + " );";

    public static final String SQL_CREATE_BL_RANDOM = "CREATE TABLE " + BLRandomContract.singleChild.TABLE_NAME + "("
            + singleChild.COLUMN_ID + " TEXT,"
            + singleChild.COLUMN_SUB_VILLAGE_CODE + " TEXT,"
            + singleChild.COLUMN_LUID + " TEXT,"
            + singleChild.COLUMN_HH + " TEXT,"
            + singleChild.COLUMN_STRUCTURE_NO + " TEXT,"
            + singleChild.COLUMN_FAMILY_EXT_CODE + " TEXT,"
            + singleChild.COLUMN_HH_HEAD + " TEXT,"
            + singleChild.COLUMN_RANDOMDT + " TEXT );";

    final String SQL_CREATE_VERSIONAPP = "CREATE TABLE " + VersionAppContract.VersionAppTable.TABLE_NAME + " (" +
            VersionAppTable._ID + " INTEGER PRIMARY KEY AUTOINCREMENT," +
            VersionAppTable.COLUMN_VERSION_CODE + " TEXT, " +
            VersionAppTable.COLUMN_VERSION_NAME + " TEXT, " +
            VersionAppTable.COLUMN_PATH_NAME + " TEXT " +
            ");";

    public static final String SQL_CREATE_USERS = "CREATE TABLE " + UsersContract.singleUser.TABLE_NAME + "("
            + singleUser._ID + " INTEGER PRIMARY KEY AUTOINCREMENT,"
            + singleUser.ROW_USERNAME + " TEXT,"
            + singleUser.ROW_PASSWORD + " TEXT,"
            + singleUser.FULL_NAME + " TEXT"
//            + singleUser.REGION_DSS + " TEXT"
            + " );";
    private static final String SQL_CREATE_FAMILY_MEMBERS = "CREATE TABLE "
            + familyMembers.TABLE_NAME + "("
            + familyMembers.COLUMN_ID + " INTEGER PRIMARY KEY AUTOINCREMENT,"
            + FamilyMembersContract.familyMembers.COLUMN_PROJECT_NAME + " TEXT," +
            familyMembers.COLUMN_UID + " TEXT,"+
            familyMembers.COLUMN_UUID + " TEXT,"+
            familyMembers.COLUMN_FORMDATE + " TEXT,"+
            familyMembers.COLUMN_DEVICEID + " TEXT,"+
            familyMembers.COLUMN_USER + " TEXT,"+
            familyMembers.COLUMN_DEVICETAGID + " TEXT,"+
            familyMembers.COLUMN_NAME + " TEXT,"+
            familyMembers.COLUMN_DOB + " TEXT,"+
            familyMembers.COLUMN_AGE + " TEXT,"+
            familyMembers.COLUMN_SB + " TEXT,"+
            familyMembers.COLUMN_SYNCED + " TEXT,"+
            familyMembers.COLUMN_SYNCED_DATE + " TEXT,"+
            familyMembers.COLUMN_ISTATUS + " TEXT,"+
            familyMembers.COLUMN_SERIALNO + " TEXT,"+
            familyMembers.COLUMN_MOTHERID + " TEXT,"+
            familyMembers.COLUMN_TYPE + " TEXT,"+
            familyMembers.COLUMN_APP_VER + " TEXT,"+
            familyMembers.COLUMN_CLUSTERNO + " TEXT,"+
            familyMembers.COLUMN_HHNO + " TEXT"
            + " );";


    private static final String SQL_CREATE_SEC_I_IM = "CREATE TABLE "
            + singleIm.TABLE_NAME + "("
            + singleIm.COLUMN_ID + " INTEGER PRIMARY KEY AUTOINCREMENT," +
            SectionIIMContract.singleIm.COLUMN_PROJECT_NAME + " TEXT," +
            singleIm.COLUMN_DEVICETAGID + " TEXT," +
            singleIm.COLUMN_UUID + " TEXT," +
            singleIm.COLUMN_UID + " TEXT," +
            singleIm.COLUMN_SI + " TEXT," +
            singleIm.COLUMN_FORMDATE + " TEXT," +
            singleIm.COLUMN_USER + " TEXT," +
            singleIm.COLUMN_DEVICEID + " TEXT," +
            singleIm.COLUMN_ISTATUS + " TEXT," +
            singleIm.COLUMN_SYNCED + " TEXT," +
            singleIm.COLUMN_SYNCED_DATE + " TEXT" +
            " );";
    private static final String SQL_DELETE_BL_RANDOM =
            "DROP TABLE IF EXISTS " + singleChild.TABLE_NAME;
    private static final String SQL_DELETE_USERS =
            "DROP TABLE IF EXISTS " + singleUser.TABLE_NAME;
    private static final String SQL_DELETE_FORMS =
            "DROP TABLE IF EXISTS " + FormsTable.TABLE_NAME;
    private static final String SQL_DELETE_CENSUS =
            "DROP TABLE IF EXISTS " + familyMembers.TABLE_NAME;
    private static final String SQL_DELETE_SEC_I_IM =
            "DROP TABLE IF EXISTS " + singleIm.TABLE_NAME;
    private static final String SQL_SELECT_MOTHER_BY_CHILD =
            "SELECT c.agem cm, c.agey cy, c.aged cd, c.gender, c.serial serial, m.serial serialm, c.name child_name, c.dss_id_member child_id, m.name mother_name, c.dss_id_member mother_id, c.dob date_of_birth FROM census C join census m on c.dss_id_m = m.dss_id_member where c.member_type =? and c.uuid = m.uuid and c.current_status IN ('1', '2') and c.uuid = ? group by mother_id order by substr(c.dob, 7) desc, substr(c.dob, 4,2) desc, substr(c.dob, 1,2) desc;";
    private static final String SQL_SELECT_CHILD =
            "SELECT * from census where member_type =? and dss_id_hh =? and uuid =? and current_status IN ('1', '2')";
    private static final String SQL_SELECT_MWRA =
            "SELECT * from census where member_type =? and dss_id_hh =? and uuid =? and current_status IN ('1', '2')";
    private static final String SQL_DELETE_VILLAGES = "DROP TABLE IF EXISTS " + VillagesContract.singleVillages.TABLE_NAME;
    private static final String SQL_DELETE_TALUKAS = "DROP TABLE IF EXISTS " + singleTalukas.TABLE_NAME;
    private static final String SQL_DELETE_UCS = "DROP TABLE IF EXISTS " + singleUCs.TABLE_NAME;
    private static final String SQL_DELETE_AREAS = "DROP TABLE IF EXISTS " + AreasContract.singleAreas.TABLE_NAME;
    final String SQL_CREATE_VILLAGES = "CREATE TABLE " + singleVillages.TABLE_NAME + "("
//                + singleVillages.COLUMN_ID + " TEXT,"
            + singleVillages.COLUMN_VILLAGE_NAME + " TEXT,"
            + singleVillages.COLUMN_AREA_CODE + " TEXT,"
//                + singleVillages.COLUMN_TALUKA + " TEXT,"
            + singleVillages.COLUMN_VILLAGE_CODE + " TEXT );";
    final String SQL_CREATE_TALUKAS = "CREATE TABLE " + TalukasContract.singleTalukas.TABLE_NAME + "("
            + singleTalukas.COLUMN_TALUKA_CODE + " TEXT,"
            + singleTalukas.COLUMN_TALUKA + " TEXT );";
    final String SQL_CREATE_UCS = "CREATE TABLE " + UCsContract.singleUCs.TABLE_NAME + "("
            + singleUCs.COLUMN_UCCODE + " TEXT,"
            + singleUCs.COLUMN_TALUKA_CODE + " TEXT,"
            + singleUCs.COLUMN_UCS + " TEXT );";
    final String SQL_CREATE_AREAS = "CREATE TABLE " + AreasContract.singleAreas.TABLE_NAME + "("
            + singleAreas.COLUMN_AREACODE + " TEXT,"
            + singleAreas.COLUMN_UC_CODE + " TEXT,"
            + singleAreas.COLUMN_AREA + " TEXT );";
    private static final String SQL_ALTER_FORMS_ADD_CLUSTER = "ALTER TABLE " +
            FormsTable.TABLE_NAME + " ADD COLUMN " +
            FormsTable.COLUMN_CLUSTER + " TEXT ";
    private static final String SQL_ALTER_FORMS_ADD_HHNO = "ALTER TABLE " +
            FormsTable.TABLE_NAME + " ADD COLUMN " +
            FormsTable.COLUMN_HHNO + " TEXT ";


    private final String TAG = "DatabaseHelper";


    public String spDateT = new SimpleDateFormat("dd-MM-yy").format(new Date().getTime());


    public DatabaseHelper(Context context) {

        super(context, DATABASE_NAME, null, DATABASE_VERSION);

    }

    @Override
    public void onCreate(SQLiteDatabase db) {

        db.execSQL(SQL_CREATE_USERS);
        db.execSQL(SQL_CREATE_FORMS);
        db.execSQL(SQL_CREATE_FAMILY_MEMBERS);
//        db.execSQL(SQL_CREATE_MWRA);
        db.execSQL(SQL_CREATE_SEC_I_IM);
        db.execSQL(SQL_CREATE_VILLAGES);
        db.execSQL(SQL_CREATE_TALUKAS);
        db.execSQL(SQL_CREATE_UCS);
        db.execSQL(SQL_CREATE_AREAS);
        db.execSQL(SQL_CREATE_BL_RANDOM);
        db.execSQL(SQL_CREATE_VERSIONAPP);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int i, int i1) {
//        db.execSQL(SQL_DELETE_USERS);
//        db.execSQL(SQL_DELETE_FORMS);
//        db.execSQL(SQL_DELETE_DECEASED_MOTHER);
//        db.execSQL(SQL_DELETE_DECEASED_CHILD);
//        db.execSQL(SQL_DELETE_MWRA);
//        db.execSQL(SQL_DELETE_SEC_I_IM);
      /*  db.execSQL(SQL_DELETE_VILLAGES);
        db.execSQL(SQL_DELETE_TALUKAS);
        db.execSQL(SQL_DELETE_UCS);
        db.execSQL(SQL_DELETE_AREAS);
        db.execSQL(SQL_DELETE_BL_RANDOM);*/
        switch (i) {
            case 1:
                db.execSQL(SQL_CREATE_VERSIONAPP);
                db.execSQL(SQL_ALTER_FORMS_ADD_CLUSTER);
                db.execSQL(SQL_ALTER_FORMS_ADD_HHNO);
        }
    }

    public void syncVillages(JSONArray Villageslist) {
        SQLiteDatabase db = this.getWritableDatabase();
        db.delete(singleVillages.TABLE_NAME, null, null);
        try {
            JSONArray jsonArray = Villageslist;
            for (int i = 0; i < jsonArray.length(); i++) {
                JSONObject jsonObjectCC = jsonArray.getJSONObject(i);

                VillagesContract Vc = new VillagesContract();
                Vc.Sync(jsonObjectCC);

                ContentValues values = new ContentValues();

//                values.put(singleVillages.COLUMN_ID, Vc.getID());
                values.put(singleVillages.COLUMN_VILLAGE_NAME, Vc.getVillagename());
                values.put(singleVillages.COLUMN_AREA_CODE, Vc.getArea_code());
//                values.put(singleVillages.COLUMN_TALUKA, Vc.getTaluka());
                values.put(singleVillages.COLUMN_VILLAGE_CODE, Vc.getVillagecode());

                db.insert(singleVillages.TABLE_NAME, null, values);
            }
        } catch (Exception e) {
        } finally {
            db.close();
        }
    }
    public void syncVersionApp(JSONArray Versionlist) {
        SQLiteDatabase db = this.getWritableDatabase();
        db.delete(VersionAppTable.TABLE_NAME, null, null);
        try {
            JSONArray jsonArray = Versionlist;
            JSONObject jsonObjectCC = jsonArray.getJSONObject(0);

            VersionAppContract Vc = new VersionAppContract();
            Vc.Sync(jsonObjectCC);

            ContentValues values = new ContentValues();

            values.put(VersionAppTable.COLUMN_PATH_NAME, Vc.getPathname());
            values.put(VersionAppTable.COLUMN_VERSION_CODE, Vc.getVersioncode());
            values.put(VersionAppTable.COLUMN_VERSION_NAME, Vc.getVersionname());

            db.insert(VersionAppTable.TABLE_NAME, null, values);
        } catch (Exception e) {
        } finally {
            db.close();
        }
    }
    public VersionAppContract getVersionApp() {

        SQLiteDatabase db = this.getReadableDatabase();
        Cursor c = null;
        String[] columns = {
                VersionAppTable._ID,
                VersionAppTable.COLUMN_VERSION_CODE,
                VersionAppTable.COLUMN_VERSION_NAME,
                VersionAppTable.COLUMN_PATH_NAME
        };

        String whereClause = null;
        String[] whereArgs = null;
        String groupBy = null;
        String having = null;

        String orderBy = null;

        VersionAppContract allVC = new VersionAppContract();
        try {
            c = db.query(
                    VersionAppTable.TABLE_NAME,  // The table to query
                    columns,                   // The columns to return
                    whereClause,               // The columns for the WHERE clause
                    whereArgs,                 // The values for the WHERE clause
                    groupBy,                   // don't group the rows
                    having,                    // don't filter by row groups
                    orderBy                    // The sort order
            );
            while (c.moveToNext()) {
                allVC.hydrate(c);
            }
        } finally {
            if (c != null) {
                c.close();
            }
            if (db != null) {
                db.close();
            }
        }
        return allVC;
    }

    public void syncTalukas(JSONArray Talukaslist) {
        SQLiteDatabase db = this.getWritableDatabase();
        db.delete(singleTalukas.TABLE_NAME, null, null);
        try {
            JSONArray jsonArray = Talukaslist;
            for (int i = 0; i < jsonArray.length(); i++) {
                JSONObject jsonObjectCC = jsonArray.getJSONObject(i);

                TalukasContract Vc = new TalukasContract();
                Vc.Sync(jsonObjectCC);

                ContentValues values = new ContentValues();

                values.put(singleTalukas.COLUMN_TALUKA_CODE, Vc.getTalukacode());
                values.put(singleTalukas.COLUMN_TALUKA, Vc.getTaluka());

                db.insert(singleTalukas.TABLE_NAME, null, values);
            }
        } catch (Exception e) {
        } finally {
            db.close();
        }
    }

    public void syncUCs(JSONArray UCslist) {
        SQLiteDatabase db = this.getWritableDatabase();
        db.delete(singleUCs.TABLE_NAME, null, null);
        try {
            JSONArray jsonArray = UCslist;
            for (int i = 0; i < jsonArray.length(); i++) {
                JSONObject jsonObjectCC = jsonArray.getJSONObject(i);

                UCsContract Vc = new UCsContract();
                Vc.Sync(jsonObjectCC);

                ContentValues values = new ContentValues();

                values.put(singleUCs.COLUMN_UCCODE, Vc.getUccode());
                values.put(singleUCs.COLUMN_UCS, Vc.getUcs());
                values.put(singleUCs.COLUMN_TALUKA_CODE, Vc.getTaluka_code());

                db.insert(singleUCs.TABLE_NAME, null, values);
            }
        } catch (Exception e) {
        } finally {
            db.close();
        }
    }

    public void syncAreas(JSONArray Areaslist) {
        SQLiteDatabase db = this.getWritableDatabase();
        db.delete(singleAreas.TABLE_NAME, null, null);
        try {
            JSONArray jsonArray = Areaslist;
            for (int i = 0; i < jsonArray.length(); i++) {
                JSONObject jsonObjectCC = jsonArray.getJSONObject(i);

                AreasContract Vc = new AreasContract();
                Vc.Sync(jsonObjectCC);

                ContentValues values = new ContentValues();

                values.put(singleAreas.COLUMN_AREACODE, Vc.getAreacode());
                values.put(singleAreas.COLUMN_AREA, Vc.getArea());
                values.put(singleAreas.COLUMN_UC_CODE, Vc.getUc_code());

                db.insert(singleAreas.TABLE_NAME, null, values);
            }
        } catch (Exception e) {
        } finally {
            db.close();
        }
    }

    public Collection<VillagesContract> getVillage(String areacode) {
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor c = null;
        String[] columns = {
//                singleVillages.COLUMN_ID,
                singleVillages.COLUMN_VILLAGE_NAME,
                singleVillages.COLUMN_AREA_CODE,
//                singleVillages.COLUMN_TALUKA,
                singleVillages.COLUMN_VILLAGE_CODE,
        };

        String whereClause = singleVillages.COLUMN_AREA_CODE + " =?";
        String[] whereArgs = {areacode};
        String groupBy = null;
        String having = null;

        String orderBy =
                singleVillages.COLUMN_VILLAGE_NAME + " ASC";

        Collection<VillagesContract> allDC = new ArrayList<>();
        try {
            c = db.query(
                    singleVillages.TABLE_NAME,  // The table to query
                    columns,                   // The columns to return
                    whereClause,               // The columns for the WHERE clause
                    whereArgs,                 // The values for the WHERE clause
                    groupBy,                   // don't group the rows
                    having,                    // don't filter by row groups
                    orderBy                    // The sort order
            );
            while (c.moveToNext()) {
                VillagesContract dc = new VillagesContract();
                allDC.add(dc.HydrateVillages(c));
            }
        } finally {
            if (c != null) {
                c.close();
            }
            if (db != null) {
                db.close();
            }
        }
        return allDC;
    }

    public Collection<TalukasContract> getAllTalukas() {
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor c = null;
        String[] columns = {
                singleTalukas.COLUMN_TALUKA_CODE,
                singleTalukas.COLUMN_TALUKA
        };

        String whereClause = null;
        String[] whereArgs = null;
        String groupBy = null;
        String having = null;

        String orderBy =
                singleTalukas.COLUMN_TALUKA + " ASC";

        Collection<TalukasContract> allDC = new ArrayList<>();
        try {
            c = db.query(
                    singleTalukas.TABLE_NAME,  // The table to query
                    columns,                   // The columns to return
                    whereClause,               // The columns for the WHERE clause
                    whereArgs,                 // The values for the WHERE clause
                    groupBy,                   // don't group the rows
                    having,                    // don't filter by row groups
                    orderBy                    // The sort order
            );
            while (c.moveToNext()) {
                TalukasContract dc = new TalukasContract();
                allDC.add(dc.HydrateTalukas(c));
            }
        } finally {
            if (c != null) {
                c.close();
            }
            if (db != null) {
                db.close();
            }
        }
        return allDC;
    }

    public Collection<UCsContract> getAllUCs(String talukaCode) {
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor c = null;
        String[] columns = {
                singleUCs.COLUMN_UCCODE,
                singleUCs.COLUMN_UCS,
                singleUCs.COLUMN_TALUKA_CODE
        };

        String whereClause = singleUCs.COLUMN_TALUKA_CODE + "=?";
        String[] whereArgs = new String[]{talukaCode};
        String groupBy = null;
        String having = null;

        String orderBy =
                singleUCs.COLUMN_UCS + " ASC";

        Collection<UCsContract> allDC = new ArrayList<>();
        try {
            c = db.query(
                    singleUCs.TABLE_NAME,  // The table to query
                    columns,                   // The columns to return
                    whereClause,               // The columns for the WHERE clause
                    whereArgs,                 // The values for the WHERE clause
                    groupBy,                   // don't group the rows
                    having,                    // don't filter by row groups
                    orderBy                    // The sort order
            );
            while (c.moveToNext()) {
                UCsContract dc = new UCsContract();
                allDC.add(dc.HydrateUCs(c));
            }
        } finally {
            if (c != null) {
                c.close();
            }
            if (db != null) {
                db.close();
            }
        }
        return allDC;
    }

    public Collection<AreasContract> getAllAreas(String UCCode) {
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor c = null;
        String[] columns = {
                singleAreas.COLUMN_AREACODE,
                singleAreas.COLUMN_AREA,
                singleAreas.COLUMN_UC_CODE
        };

        String whereClause = singleAreas.COLUMN_UC_CODE + "=?";
        String[] whereArgs = new String[]{UCCode};
        String groupBy = null;
        String having = null;

        String orderBy = singleAreas.COLUMN_AREA + " ASC";

        Collection<AreasContract> allAC = new ArrayList<>();
        try {
            c = db.query(
                    singleAreas.TABLE_NAME,  // The table to query
                    columns,                   // The columns to return
                    whereClause,               // The columns for the WHERE clause
                    whereArgs,                 // The values for the WHERE clause
                    groupBy,                   // don't group the rows
                    having,                    // don't filter by row groups
                    orderBy                    // The sort order
            );
            while (c.moveToNext()) {
                AreasContract dc = new AreasContract();
                allAC.add(dc.HydrateUCs(c));
            }
        } finally {
            if (c != null) {
                c.close();
            }
            if (db != null) {
                db.close();
            }
        }
        return allAC;
    }

    public Collection<BLRandomContract> getAllBLRandom(String subAreaCode, String hh) {
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor c = null;
        String[] columns = {
                singleChild.COLUMN_ID,
                singleChild.COLUMN_LUID,
                singleChild.COLUMN_STRUCTURE_NO,
                singleChild.COLUMN_FAMILY_EXT_CODE,
                singleChild.COLUMN_HH,
                singleChild.COLUMN_SUB_VILLAGE_CODE,
                singleChild.COLUMN_RANDOMDT,
                singleChild.COLUMN_HH_HEAD
        };

        String whereClause = singleChild.COLUMN_SUB_VILLAGE_CODE + "=? AND " +
                singleChild.COLUMN_HH + "=?";
        String[] whereArgs = new String[]{subAreaCode, hh};
        String groupBy = null;
        String having = null;

        String orderBy =
                singleChild.COLUMN_ID + " ASC";

        Collection<BLRandomContract> allBL = new ArrayList<>();
        try {
            c = db.query(
                    singleChild.TABLE_NAME,  // The table to query
                    columns,                   // The columns to return
                    whereClause,               // The columns for the WHERE clause
                    whereArgs,                 // The values for the WHERE clause
                    groupBy,                   // don't group the rows
                    having,                    // don't filter by row groups
                    orderBy                    // The sort order
            );
            while (c.moveToNext()) {
                BLRandomContract dc = new BLRandomContract();
                allBL.add(dc.Hydrate(c));
            }
        } finally {
            if (c != null) {
                c.close();
            }
            if (db != null) {
                db.close();
            }
        }
        return allBL;
    }

    public void syncBLRandom(JSONArray Areaslist) {
        SQLiteDatabase db = this.getWritableDatabase();
        db.delete(singleChild.TABLE_NAME, null, null);
        try {
            JSONArray jsonArray = Areaslist;
            for (int i = 0; i < jsonArray.length(); i++) {
                JSONObject jsonObjectCC = jsonArray.getJSONObject(i);

                BLRandomContract Vc = new BLRandomContract();
                Vc.Sync(jsonObjectCC);

                ContentValues values = new ContentValues();

                values.put(singleChild.COLUMN_ID, Vc.get_ID());
                values.put(singleChild.COLUMN_LUID, Vc.getLUID());
                values.put(singleChild.COLUMN_STRUCTURE_NO, Vc.getStructure());
                values.put(singleChild.COLUMN_FAMILY_EXT_CODE, Vc.getExtension());
                values.put(singleChild.COLUMN_HH, Vc.getHh());
                values.put(singleChild.COLUMN_SUB_VILLAGE_CODE, Vc.getSubVillageCode());
                values.put(singleChild.COLUMN_RANDOMDT, Vc.getRandomDT());
                values.put(singleChild.COLUMN_HH_HEAD, Vc.getHhhead());

                db.insert(singleChild.TABLE_NAME, null, values);
            }
        } catch (Exception e) {
        } finally {
            db.close();
        }
    }

    public void syncUser(JSONArray userlist) {
        SQLiteDatabase db = this.getWritableDatabase();
        db.delete(UsersContract.singleUser.TABLE_NAME, null, null);
        try {
            JSONArray jsonArray = userlist;
            for (int i = 0; i < jsonArray.length(); i++) {

                JSONObject jsonObjectUser = jsonArray.getJSONObject(i);

                UsersContract user = new UsersContract();
                user.Sync(jsonObjectUser);
                ContentValues values = new ContentValues();

                values.put(singleUser.ROW_USERNAME, user.getUserName());
                values.put(singleUser.ROW_PASSWORD, user.getPassword());
                values.put(singleUser.FULL_NAME, user.getFULL_NAME());
//                values.put(singleUser.REGION_DSS, user.getREGION_DSS());
                db.insert(singleUser.TABLE_NAME, null, values);
            }


        } catch (Exception e) {
            Log.d(TAG, "syncUser(e): " + e);
        } finally {
            db.close();
        }
    }


    public boolean Login(String username, String password) throws SQLException {
        SQLiteDatabase db = this.getReadableDatabase();

        Cursor mCursor = db.rawQuery("SELECT * FROM " + singleUser.TABLE_NAME + " WHERE " + singleUser.ROW_USERNAME + "=? AND " + singleUser.ROW_PASSWORD + "=?", new String[]{username, password});
        if (mCursor != null) {
            //                if (mCursor.moveToFirst()) {
            //                    MainApp.regionDss = mCursor.getString(mCursor.getColumnIndex("region_dss"));
            //                }
            return mCursor.getCount() > 0;
        }
        return false;
    }


    public List<FormsContract> getFormsByDSS(String dssID) {
        List<FormsContract> formList = new ArrayList<FormsContract>();
        // Select All Query
        String selectQuery = "SELECT  * FROM " + FormsTable.TABLE_NAME;

        SQLiteDatabase db = this.getWritableDatabase();
        Cursor c = db.rawQuery(selectQuery, null);

        // looping through all rows and adding to list
        if (c.moveToFirst()) {
            do {
                FormsContract fc = new FormsContract();
                formList.add(fc.Hydrate(c));
            } while (c.moveToNext());
        }

        // return contact list
        return formList;
    }

   /* public Collection<MotherLst> getMotherByUUID(String uuid) {

        SQLiteDatabase db = this.getReadableDatabase();
        Cursor c = null;
        // COLUMNS RETURNED: child_name, child_id, mother_name, mother_id, date_of_birth, serial
        Collection<MotherLst> memList = new ArrayList<>();
        try {

//            c = db.rawQuery(SQL_SELECT_MOTHER_BY_CHILD, new String[]{"c", "('1', '2')", uuid});
            c = db.rawQuery(SQL_SELECT_MOTHER_BY_CHILD, new String[]{"c", uuid});

            while (c.moveToNext()) {

                MotherLst mc = new MotherLst();
                memList.add(mc.Hydrate(c));
            }
        } finally {
            if (c != null) {
                c.close();
            }
            if (db != null) {
                db.close();
            }
        }
        return memList;
    }*/

    public Collection<FamilyMembersContract> getChildFromMember(String dssID, String uuid) {

        SQLiteDatabase db = this.getReadableDatabase();
        Cursor c = null;
        // COLUMNS RETURNED: child_name, child_id, mother_name, mother_id, date_of_birth, serial
        Collection<FamilyMembersContract> memList = new ArrayList<>();
        try {

//            c = db.rawQuery(SQL_SELECT_CHILD, new String[]{"c", dssID, uuid, "('1', '2')"});
            c = db.rawQuery(SQL_SELECT_CHILD, new String[]{"c", dssID, uuid});

            while (c.moveToNext()) {
                FamilyMembersContract mc = new FamilyMembersContract();
                memList.add(mc.Hydrate(c));
            }
        } finally {
            if (c != null) {
                c.close();
            }
            if (db != null) {
                db.close();
            }
        }
        return memList;
    }


    public Collection<FamilyMembersContract> getMWRA(String dssID, String uuid) {

        SQLiteDatabase db = this.getReadableDatabase();
        Cursor c = null;
        // COLUMNS RETURNED: child_name, child_id, mother_name, mother_id, date_of_birth, serial
        Collection<FamilyMembersContract> memList = new ArrayList<>();
        try {

//            c = db.rawQuery(SQL_SELECT_CHILD, new String[]{"c", dssID, uuid, "('1', '2')"});
            c = db.rawQuery(SQL_SELECT_CHILD, new String[]{"c", dssID, uuid});

            while (c.moveToNext()) {
                FamilyMembersContract mc = new FamilyMembersContract();
                memList.add(mc.Hydrate(c));
            }
        } finally {
            if (c != null) {
                c.close();
            }
            if (db != null) {
                db.close();
            }
        }
        return memList;
    }


    public Long addForm(FormsContract fc) {

        // Gets the data repository in write mode
        SQLiteDatabase db = this.getWritableDatabase();

// Create a new map of values, where column names are the keys
        ContentValues values = new ContentValues();
        values.put(FormsTable.COLUMN_PROJECT_NAME, fc.getprojectName());
        values.put(FormsTable.COLUMN_UID, fc.get_UID());
        values.put(FormsTable.COLUMN_FORMDATE, fc.getformDate());
        values.put(FormsTable.COLUMN_USER, fc.getuser());
        values.put(FormsTable.COLUMN_ISTATUS, fc.getistatus());
        values.put(FormsTable.COLUMN_ISTATUS96X, fc.getistatus96x());
        values.put(FormsTable.COLUMN_SA, fc.getsA());
        values.put(FormsTable.COLUMN_SB, fc.getsB());
        values.put(FormsTable.COLUMN_SE, fc.getsE());
        values.put(FormsTable.COLUMN_SF, fc.getsF());
        values.put(FormsTable.COLUMN_SG, fc.getsG());
        values.put(FormsTable.COLUMN_SH, fc.getsH());
        values.put(FormsTable.COLUMN_SI, fc.getsI());
        values.put(FormsTable.COLUMN_SJ, fc.getsJ());
        values.put(FormsTable.COLUMN_SK, fc.getsK());
        values.put(FormsTable.COLUMN_APPVER, fc.getappVer());
        values.put(FormsTable.COLUMN_GPSLAT, fc.getgpsLat());
        values.put(FormsTable.COLUMN_GPSLNG, fc.getgpsLng());
        values.put(FormsTable.COLUMN_GPSDT, fc.getgpsDT());
        values.put(FormsTable.COLUMN_GPSACC, fc.getgpsAcc());
        values.put(FormsTable.COLUMN_SYNCED, fc.getsynced());
        values.put(FormsTable.COLUMN_SYNCED_DATE, fc.getsynced_date());
        values.put(FormsTable.COLUMN_DEVICEID, fc.getdeviceid());
        values.put(FormsTable.COLUMN_DEVICETAGID, fc.getdevicetagID());
        values.put(FormsTable.COLUMN_CLUSTER, fc.getcluster_no());
        values.put(FormsTable.COLUMN_HHNO, fc.gethhno());


        // Insert the new row, returning the primary key value of the new row
        long newRowId;
        newRowId = db.insert(
                FormsTable.TABLE_NAME,
                FormsTable.COLUMN_NAME_NULLABLE,
                values);
        return newRowId;
    }

    public Long addFamilyMembers(FamilyMembersContract fmc) {

        // Gets the data repository in write mode
        SQLiteDatabase db = this.getWritableDatabase();

// Create a new map of values, where column names are the keys
        ContentValues values = new ContentValues();

        values.put(familyMembers.COLUMN_PROJECT_NAME, fmc.getprojectName());
        values.put(familyMembers.COLUMN_UID, fmc.get_UID());
        values.put(familyMembers.COLUMN_UUID, fmc.get_UUID());
        values.put(familyMembers.COLUMN_FORMDATE, fmc.getformDate());
        values.put(familyMembers.COLUMN_DEVICEID, fmc.getdeviceId());
        values.put(familyMembers.COLUMN_USER, fmc.getuser());
        values.put(familyMembers.COLUMN_DEVICETAGID, fmc.getdevicetagID());
        values.put(familyMembers.COLUMN_NAME, fmc.getname());
        values.put(familyMembers.COLUMN_DOB, fmc.getdob());
        values.put(familyMembers.COLUMN_AGE, fmc.getage());
        values.put(familyMembers.COLUMN_SB, fmc.getsB());
        values.put(familyMembers.COLUMN_SYNCED, fmc.getsynced());
        values.put(familyMembers.COLUMN_SYNCED_DATE, fmc.getsyncedDate());
        values.put(familyMembers.COLUMN_ISTATUS, fmc.getistatus());
        values.put(familyMembers.COLUMN_SERIALNO, fmc.getserialNo());
        values.put(familyMembers.COLUMN_MOTHERID, fmc.getmotherId());
        values.put(familyMembers.COLUMN_TYPE, fmc.gettype());
        values.put(familyMembers.COLUMN_APP_VER, fmc.getapp_ver());
        values.put(familyMembers.COLUMN_CLUSTERNO, fmc.getclusterNo());
        values.put(familyMembers.COLUMN_HHNO, fmc.gethhNo());


        // Insert the new row, returning the primary key value of the new row
        long newRowId;
        newRowId = db.insert(
                familyMembers.TABLE_NAME,
                familyMembers.COLUMN_NAME_NULLABLE,
                values);
        return newRowId;
    }


    public Long addChild(SectionIIMContract ims) {

        // Gets the data repository in write mode
        SQLiteDatabase db = this.getWritableDatabase();

// Create a new map of values, where column names are the keys
        ContentValues values = new ContentValues();

        values.put(singleIm.COLUMN_PROJECT_NAME, ims.getProjectName());
        values.put(singleIm.COLUMN_UUID, ims.get_UUID());
        values.put(singleIm.COLUMN_UID, ims.getUID());
        values.put(singleIm.COLUMN_SI, ims.getsI());
        values.put(singleIm.COLUMN_FORMDATE, ims.getFormDate());
        values.put(singleIm.COLUMN_USER, ims.getUser());
//        values.put(singleIm.COLUMN_MM, ims.getMm());
        //values.put(singleIm.COLUMN_CHILDID, ims.getChildID());
//        values.put(singleIm.COLUMN_DSSID, ims.getDssID());
        values.put(singleIm.COLUMN_DEVICEID, ims.getDeviceId());
        values.put(singleIm.COLUMN_DEVICETAGID, ims.getDevicetagID());


        // Insert the new row, returning the primary key value of the new row
        long newRowId;
        newRowId = db.insert(
                singleIm.TABLE_NAME,
                singleIm.COLUMN_NAME_NULLABLE,
                values);
        return newRowId;
    }


    public void updateSyncedForms(String id) {
        SQLiteDatabase db = this.getReadableDatabase();

// New value for one column
        ContentValues values = new ContentValues();
        values.put(FormsTable.COLUMN_SYNCED, true);
        values.put(FormsTable.COLUMN_SYNCED_DATE, new Date().toString());

// Which row to update, based on the title
        String where = FormsTable.COLUMN_ID + " = ?";
        String[] whereArgs = {id};

        int count = db.update(
                FormsTable.TABLE_NAME,
                values,
                where,
                whereArgs);
    }


    public void updateFamilyMember(String id) {
        SQLiteDatabase db = this.getReadableDatabase();

// New value for one column
        ContentValues values = new ContentValues();
        values.put(familyMembers.COLUMN_SYNCED, true);
        values.put(familyMembers.COLUMN_SYNCED_DATE, new Date().toString());

// Which row to update, based on the title
        String where = familyMembers.COLUMN_ID + " = ?";
        String[] whereArgs = {id};

        int count = db.update(
                familyMembers.TABLE_NAME,
                values,
                where,
                whereArgs);
    }


    public void updateIM(String id) {
        SQLiteDatabase db = this.getReadableDatabase();

// New value for one column
        ContentValues values = new ContentValues();
        values.put(singleIm.COLUMN_SYNCED, true);
        values.put(singleIm.COLUMN_SYNCED_DATE, new Date().toString());

// Which row to update, based on the title
        String where = singleIm.COLUMN_ID + " = ?";
        String[] whereArgs = {id};

        int count = db.update(
                singleIm.TABLE_NAME,
                values,
                where,
                whereArgs);
    }


    public int updateFormID() {
        SQLiteDatabase db = this.getReadableDatabase();

// New value for one column
        ContentValues values = new ContentValues();
        values.put(FormsTable.COLUMN_UID, MainApp.fc.get_UID());

// Which row to update, based on the ID
        String selection = FormsTable._ID + " = ?";
        String[] selectionArgs = {String.valueOf(MainApp.fc.get_ID())};

        int count = db.update(FormsTable.TABLE_NAME,
                values,
                selection,
                selectionArgs);
        return count;
    }


    public int updateFamilyMemberID() {
        SQLiteDatabase db = this.getReadableDatabase();

// New value for one column
        ContentValues values = new ContentValues();
        values.put(familyMembers.COLUMN_UID, MainApp.fmc.get_UID());

// Which row to update, based on the ID
        String selection = familyMembers.COLUMN_ID + " = ?";
        String[] selectionArgs = {String.valueOf(MainApp.fmc.get_ID())};

        int count = db.update(familyMembers.TABLE_NAME,
                values,
                selection,
                selectionArgs);
        return count;
    }

    public int updateChildID() {
        SQLiteDatabase db = this.getReadableDatabase();

// New value for one column
        ContentValues values = new ContentValues();
        values.put(singleIm.COLUMN_UID, MainApp.ims.getUID());

// Which row to update, based on the ID
        String selection = singleIm.COLUMN_ID + " = ?";
        String[] selectionArgs = {String.valueOf(MainApp.ims.get_ID())};

        int count = db.update(singleIm.TABLE_NAME,
                values,
                selection,
                selectionArgs);
        return count;
    }

    public Collection<FormsContract> getAllForms() {
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor c = null;
        String[] columns = {
                FormsTable.COLUMN_PROJECT_NAME,
                FormsTable.COLUMN_ID,
                FormsTable.COLUMN_UID,
                FormsTable.COLUMN_FORMDATE,
                FormsTable.COLUMN_USER,
                FormsTable.COLUMN_ISTATUS,
                FormsTable.COLUMN_ISTATUS96X,
                FormsTable.COLUMN_SA,
                FormsTable.COLUMN_SB,
                FormsTable.COLUMN_SE,
                FormsTable.COLUMN_SF,
                FormsTable.COLUMN_SG,
                FormsTable.COLUMN_SH,
                FormsTable.COLUMN_SI,
                FormsTable.COLUMN_SJ,
                FormsTable.COLUMN_SK,
                FormsTable.COLUMN_APPVER,
                FormsTable.COLUMN_GPSLAT,
                FormsTable.COLUMN_GPSLNG,
                FormsTable.COLUMN_GPSDT,
                FormsTable.COLUMN_GPSACC,
                FormsTable.COLUMN_SYNCED,
                FormsTable.COLUMN_SYNCED_DATE,
                FormsTable.COLUMN_DEVICEID,
                FormsTable.COLUMN_DEVICETAGID,
                FormsTable.COLUMN_CLUSTER,
                FormsTable.COLUMN_HHNO,



        };
        String whereClause = null;
        String[] whereArgs = null;
        String groupBy = null;
        String having = null;

        String orderBy =
                FormsTable.COLUMN_ID + " ASC";

        Collection<FormsContract> allFC = new ArrayList<FormsContract>();
        try {
            c = db.query(
                    FormsTable.TABLE_NAME,  // The table to query
                    columns,                   // The columns to return
                    whereClause,               // The columns for the WHERE clause
                    whereArgs,                 // The values for the WHERE clause
                    groupBy,                   // don't group the rows
                    having,                    // don't filter by row groups
                    orderBy                    // The sort order
            );
            while (c.moveToNext()) {
                FormsContract fc = new FormsContract();
                allFC.add(fc.Hydrate(c));
            }
        } finally {
            if (c != null) {
                c.close();
            }
            if (db != null) {
                db.close();
            }
        }
        return allFC;
    }



    public Collection<SectionIIMContract> getUnsyncedIM() {
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor c = null;
        String[] columns = {
                singleIm.COLUMN_ID,
                singleIm.COLUMN_UUID,
                singleIm.COLUMN_UID,
                singleIm.COLUMN_SI,
                singleIm.COLUMN_FORMDATE,
                singleIm.COLUMN_USER,
//                singleIm.COLUMN_CHILDID,
                /*singleIm.COLUMN_MM,
                singleIm.COLUMN_DSSID,*/
                singleIm.COLUMN_DEVICETAGID,
                singleIm.COLUMN_DEVICEID,
                singleIm.COLUMN_ISTATUS

        };
        String whereClause = singleIm.COLUMN_SYNCED + " is null";
        String[] whereArgs = null;
        String groupBy = null;
        String having = null;

        String orderBy =
                singleIm.COLUMN_ID + " ASC";

        Collection<SectionIIMContract> allIM = new ArrayList<SectionIIMContract>();
        try {
            c = db.query(
                    singleIm.TABLE_NAME,  // The table to query
                    columns,                   // The columns to return
                    whereClause,               // The columns for the WHERE clause
                    whereArgs,                 // The values for the WHERE clause
                    groupBy,                   // don't group the rows
                    having,                    // don't filter by row groups
                    orderBy                    // The sort order
            );
            while (c.moveToNext()) {
                SectionIIMContract kIm = new SectionIIMContract();
                allIM.add(kIm.Hydrate(c));
            }
        } finally {
            if (c != null) {
                c.close();
            }
            if (db != null) {
                db.close();
            }
        }
        return allIM;
    }

    public Collection<FamilyMembersContract> getUnsyncedFamilyMembers() {
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor c = null;
        String[] columns = {
                familyMembers.COLUMN_ID,
                familyMembers.COLUMN_UID,
                familyMembers.COLUMN_UUID,
                familyMembers.COLUMN_FORMDATE,
                familyMembers.COLUMN_DEVICEID,
                familyMembers.COLUMN_USER,
                familyMembers.COLUMN_DEVICETAGID,
                familyMembers.COLUMN_NAME,
                familyMembers.COLUMN_DOB,
                familyMembers.COLUMN_AGE,
                familyMembers.COLUMN_SB,
                familyMembers.COLUMN_SYNCED,
                familyMembers.COLUMN_SYNCED_DATE,
                familyMembers.COLUMN_ISTATUS,
                familyMembers.COLUMN_SERIALNO,
                familyMembers.COLUMN_MOTHERID,
                familyMembers.COLUMN_TYPE,
                familyMembers.COLUMN_APP_VER,
                familyMembers.COLUMN_CLUSTERNO,
                familyMembers.COLUMN_HHNO,

        };
        String whereClause =familyMembers.COLUMN_SYNCED + " is null OR " + familyMembers.COLUMN_SYNCED + " = '' ";
        String[] whereArgs = null;
        String groupBy = null;
        String having = null;

        String orderBy =
                familyMembers.COLUMN_ID + " ASC";

        Collection<FamilyMembersContract> allCC = new ArrayList<FamilyMembersContract>();
        try {
            c = db.query(
                    familyMembers.TABLE_NAME,  // The table to query
                    columns,                   // The columns to return
                    whereClause,               // The columns for the WHERE clause
                    whereArgs,                 // The values for the WHERE clause
                    groupBy,                   // don't group the rows
                    having,                    // don't filter by row groups
                    orderBy                    // The sort order
            );
            while (c.moveToNext()) {
                FamilyMembersContract cc = new FamilyMembersContract();
                allCC.add(cc.Hydrate(c));
            }
        } finally {
            if (c != null) {
                c.close();
            }
            if (db != null) {
                db.close();
            }
        }
        return allCC;
    }




    public Collection<FormsContract> getUnsyncedForms() {
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor c = null;
        String[] columns = {
                FormsTable.COLUMN_PROJECT_NAME,
                FormsTable.COLUMN_ID,
                FormsTable.COLUMN_UID,
                FormsTable.COLUMN_FORMDATE,
                FormsTable.COLUMN_USER,
                FormsTable.COLUMN_ISTATUS,
                FormsTable.COLUMN_ISTATUS96X,
                FormsTable.COLUMN_SA,
                FormsTable.COLUMN_SB,
                FormsTable.COLUMN_SE,
                FormsTable.COLUMN_SF,
                FormsTable.COLUMN_SG,
                FormsTable.COLUMN_SH,
                FormsTable.COLUMN_SI,
                FormsTable.COLUMN_SJ,
                FormsTable.COLUMN_SK,
                FormsTable.COLUMN_APPVER,
                FormsTable.COLUMN_GPSLAT,
                FormsTable.COLUMN_GPSLNG,
                FormsTable.COLUMN_GPSDT,
                FormsTable.COLUMN_GPSACC,
                FormsTable.COLUMN_SYNCED,
                FormsTable.COLUMN_SYNCED_DATE,
                FormsTable.COLUMN_DEVICEID,
                FormsTable.COLUMN_DEVICETAGID,
                FormsTable.COLUMN_CLUSTER,
                FormsTable.COLUMN_HHNO,

        };
        String whereClause = FormsTable.COLUMN_SYNCED + " is null OR "+FormsTable.COLUMN_SYNCED+" = ''";
        String[] whereArgs = null;
        String groupBy = null;
        String having = null;

        String orderBy =
                FormsTable.COLUMN_ID + " ASC";

        Collection<FormsContract> allFC = new ArrayList<FormsContract>();
        try {
            c = db.query(
                    FormsTable.TABLE_NAME,  // The table to query
                    columns,                   // The columns to return
                    whereClause,               // The columns for the WHERE clause
                    whereArgs,                 // The values for the WHERE clause
                    groupBy,                   // don't group the rows
                    having,                    // don't filter by row groups
                    orderBy                    // The sort order
            );
            while (c.moveToNext()) {
                FormsContract fc = new FormsContract();
                allFC.add(fc.Hydrate(c));
            }
        } finally {
            if (c != null) {
                c.close();
            }
            if (db != null) {
                db.close();
            }
        }
        return allFC;
    }

    public Collection<FormsContract> getFormsSg() {
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor c = null;
        String[] columns = {
                FormsTable.COLUMN_PROJECT_NAME,
                FormsTable.COLUMN_ID,
                FormsTable.COLUMN_UID,
                FormsTable.COLUMN_FORMDATE,
                FormsTable.COLUMN_USER,
                FormsTable.COLUMN_ISTATUS,
                FormsTable.COLUMN_ISTATUS96X,
                FormsTable.COLUMN_SA,
                FormsTable.COLUMN_SB,
                FormsTable.COLUMN_SE,
                FormsTable.COLUMN_SF,
                FormsTable.COLUMN_SG,
                FormsTable.COLUMN_SH,
                FormsTable.COLUMN_SI,
                FormsTable.COLUMN_SJ,
                FormsTable.COLUMN_SK,
                FormsTable.COLUMN_APPVER,
                FormsTable.COLUMN_GPSLAT,
                FormsTable.COLUMN_GPSLNG,
                FormsTable.COLUMN_GPSDT,
                FormsTable.COLUMN_GPSACC,
                FormsTable.COLUMN_SYNCED,
                FormsTable.COLUMN_SYNCED_DATE,
                FormsTable.COLUMN_DEVICEID,
                FormsTable.COLUMN_DEVICETAGID,

        };
        String whereClause = FormsTable.COLUMN_SYNCED + " = ? AND " + FormsTable.COLUMN_SG + " != ?";
        String[] whereArgs = new String[]{"1", ""};
        String groupBy = null;
        String having = null;

        String orderBy =
                FormsTable.COLUMN_ID + " ASC";

        Collection<FormsContract> allFC = new ArrayList<FormsContract>();
        try {
            c = db.query(
                    FormsTable.TABLE_NAME,  // The table to query
                    columns,                   // The columns to return
                    whereClause,               // The columns for the WHERE clause
                    whereArgs,                 // The values for the WHERE clause
                    groupBy,                   // don't group the rows
                    having,                    // don't filter by row groups
                    orderBy                    // The sort order
            );
            while (c.moveToNext()) {
                FormsContract fc = new FormsContract();
                allFC.add(fc.Hydrate(c));
            }
        } finally {
            if (c != null) {
                c.close();
            }
            if (db != null) {
                db.close();
            }
        }
        return allFC;
    }

    public Collection<FormsContract> getTodayForms() {
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor c = null;
        String[] columns = {
                FormsTable._ID,
                //FormsTable.COLUMN_DSSID,
                FormsTable.COLUMN_FORMDATE,
                FormsTable.COLUMN_HHNO,
                FormsTable.COLUMN_ISTATUS,
                FormsTable.COLUMN_SYNCED,

        };
        String whereClause = FormsTable.COLUMN_FORMDATE + " Like ? ";
        String[] whereArgs = new String[]{"%" + spDateT.substring(0, 8).trim() + "%"};
        String groupBy = null;
        String having = null;

        String orderBy =
                FormsTable.COLUMN_ID + " ASC";

        Collection<FormsContract> allFC = new ArrayList<>();
        try {
            c = db.query(
                    FormsTable.TABLE_NAME,  // The table to query
                    columns,                   // The columns to return
                    whereClause,               // The columns for the WHERE clause
                    whereArgs,                 // The values for the WHERE clause
                    groupBy,                   // don't group the rows
                    having,                    // don't filter by row groups
                    orderBy                    // The sort order
            );
            while (c.moveToNext()) {
                FormsContract fc = new FormsContract();
                fc.set_ID(c.getString(c.getColumnIndex(FormsTable.COLUMN_ID)));
                //fc.setDSSID(c.getString(c.getColumnIndex(FormsTable.COLUMN_DSSID)));
                fc.setformDate(c.getString(c.getColumnIndex(FormsTable.COLUMN_FORMDATE)));
                fc.sethhno(c.getString(c.getColumnIndex(FormsTable.COLUMN_HHNO)));
                fc.setistatus(c.getString(c.getColumnIndex(FormsTable.COLUMN_ISTATUS)));
                fc.setsynced(c.getString(c.getColumnIndex(FormsTable.COLUMN_SYNCED)));
                allFC.add(fc);
            }
        } finally {
            if (c != null) {
                c.close();
            }
            if (db != null) {
                db.close();
            }
        }
        return allFC;
    }

    // ANDROID DATABASE MANAGER
    public ArrayList<Cursor> getd01ta(String Query) {
        //get writable database
        SQLiteDatabase sqlDB = this.getWritableDatabase();
        String[] columns = new String[]{"message"};
        //an array list of cursor to save two cursors one has results from the query
        //other cursor stores error message if any errors are triggered
        ArrayList<Cursor> alc = new ArrayList<Cursor>(2);
        MatrixCursor Cursor2 = new MatrixCursor(columns);
        alc.add(null);
        alc.add(null);

        try {
            String maxQuery = Query;
            //execute the query results will be save in Cursor c
            Cursor c = sqlDB.rawQuery(maxQuery, null);

            //add value to cursor2
            Cursor2.addRow(new Object[]{"Success"});

            alc.set(1, Cursor2);
            if (null != c && c.getCount() > 0) {

                alc.set(0, c);
                c.moveToFirst();

                return alc;
            }
            return alc;
        } catch (SQLException sqlEx) {
            Log.d("printing exception", sqlEx.getMessage());
            //if any exceptions are triggered save the error message to cursor an return the arraylist
            Cursor2.addRow(new Object[]{"" + sqlEx.getMessage()});
            alc.set(1, Cursor2);
            return alc;
        } catch (Exception ex) {

            Log.d("printing exception", ex.getMessage());

            //if any exceptions are triggered save the error message to cursor an return the arraylist
            Cursor2.addRow(new Object[]{"" + ex.getMessage()});
            alc.set(1, Cursor2);
            return alc;
        }
    }


    public int updateSA() {
        SQLiteDatabase db = this.getReadableDatabase();

// New value for one column
        ContentValues values = new ContentValues();
        values.put(FormsTable.COLUMN_SG, MainApp.fc.getsG());

// Which row to update, based on the ID
        String selection = FormsTable._ID + " = ?";
        String[] selectionArgs = {String.valueOf(MainApp.fc.get_ID())};

        int count = db.update(FormsTable.TABLE_NAME,
                values,
                selection,
                selectionArgs);
        return count;
    }

    public int updateSD() {
        SQLiteDatabase db = this.getReadableDatabase();

// New value for one column
        ContentValues values = new ContentValues();
        values.put(FormsTable.COLUMN_SB, MainApp.fc.getsB());

// Which row to update, based on the ID
        String selection = FormsTable._ID + " = ?";
        String[] selectionArgs = {String.valueOf(MainApp.fc.get_ID())};

        int count = db.update(FormsTable.TABLE_NAME,
                values,
                selection,
                selectionArgs);
        return count;
    }

    public int updateSE() {
        SQLiteDatabase db = this.getReadableDatabase();

// New value for one column
        ContentValues values = new ContentValues();
        values.put(FormsTable.COLUMN_SE, MainApp.fc.getsE());

// Which row to update, based on the ID
        String selection = FormsTable._ID + " = ?";
        String[] selectionArgs = {String.valueOf(MainApp.fc.get_ID())};

        int count = db.update(FormsTable.TABLE_NAME,
                values,
                selection,
                selectionArgs);
        return count;
    }

    public int updateSF() {
        SQLiteDatabase db = this.getReadableDatabase();

// New value for one column
        ContentValues values = new ContentValues();
        values.put(FormsTable.COLUMN_SF, MainApp.fc.getsF());

// Which row to update, based on the ID
        String selection = FormsTable._ID + " = ?";
        String[] selectionArgs = {String.valueOf(MainApp.fc.get_ID())};

        int count = db.update(FormsTable.TABLE_NAME,
                values,
                selection,
                selectionArgs);
        return count;
    }

    public int updateSG() {
        SQLiteDatabase db = this.getReadableDatabase();

// New value for one column
        ContentValues values = new ContentValues();
        values.put(FormsTable.COLUMN_SG, MainApp.fc.getsG());

// Which row to update, based on the ID
        String selection = FormsTable._ID + " = ?";
        String[] selectionArgs = {String.valueOf(MainApp.fc.get_ID())};

        int count = db.update(FormsTable.TABLE_NAME,
                values,
                selection,
                selectionArgs);
        return count;
    }

    public int updateSH() {
        SQLiteDatabase db = this.getReadableDatabase();

// New value for one column
        ContentValues values = new ContentValues();
        values.put(FormsTable.COLUMN_SH, MainApp.fc.getsH());

// Which row to update, based on the ID
        String selection = FormsTable._ID + " = ?";
        String[] selectionArgs = {String.valueOf(MainApp.fc.get_ID())};

        int count = db.update(FormsTable.TABLE_NAME,
                values,
                selection,
                selectionArgs);
        return count;
    }

    public int updateSI() {
        SQLiteDatabase db = this.getReadableDatabase();

// New value for one column
        ContentValues values = new ContentValues();
        values.put(FormsTable.COLUMN_SI, MainApp.fc.getsI());

// Which row to update, based on the ID
        String selection = FormsTable._ID + " = ?";
        String[] selectionArgs = {String.valueOf(MainApp.fc.get_ID())};

        int count = db.update(FormsTable.TABLE_NAME,
                values,
                selection,
                selectionArgs);
        return count;
    }

    public int updateSJ() {
        SQLiteDatabase db = this.getReadableDatabase();

// New value for one column
        ContentValues values = new ContentValues();
        values.put(FormsTable.COLUMN_SJ, MainApp.fc.getsJ());

// Which row to update, based on the ID
        String selection = FormsTable._ID + " = ?";
        String[] selectionArgs = {String.valueOf(MainApp.fc.get_ID())};

        int count = db.update(FormsTable.TABLE_NAME,
                values,
                selection,
                selectionArgs);
        return count;
    }

    public int updateSK() {
        SQLiteDatabase db = this.getReadableDatabase();

// New value for one column
        ContentValues values = new ContentValues();
        values.put(FormsTable.COLUMN_SK, MainApp.fc.getsK());

// Which row to update, based on the ID
        String selection = FormsTable._ID + " = ?";
        String[] selectionArgs = {String.valueOf(MainApp.fc.get_ID())};

        int count = db.update(FormsTable.TABLE_NAME,
                values,
                selection,
                selectionArgs);
        return count;
    }

    public int updateCount() {
        SQLiteDatabase db = this.getReadableDatabase();

// New value for one column
        ContentValues values = new ContentValues();
        values.put(FormsTable.COLUMN_SB, MainApp.fc.getsB());

// Which row to update, based on the ID
        String selection = FormsTable.COLUMN_ID + " = ?";
        String[] selectionArgs = {String.valueOf(MainApp.fc.get_ID())};

        int count = db.update(FormsTable.TABLE_NAME,
                values,
                selection,
                selectionArgs);
        return count;
    }

    public int updateEnding() {
        SQLiteDatabase db = this.getReadableDatabase();

// New value for one column
        ContentValues values = new ContentValues();
        values.put(FormsTable.COLUMN_ISTATUS, MainApp.fc.getistatus());
        values.put(FormsTable.COLUMN_ISTATUS96X, MainApp.fc.getistatus96x());

// Which row to update, based on the ID
        String selection = FormsTable.COLUMN_ID + " =? ";
        String[] selectionArgs = {String.valueOf(MainApp.fc.get_ID())};

        int count = db.update(FormsTable.TABLE_NAME,
                values,
                selection,
                selectionArgs);
        return count;
    }

  /*  public int updateFamilyMember() {
        SQLiteDatabase db = this.getReadableDatabase();

// New value for one column
        ContentValues values = new ContentValues();
        values.put(familyMembers.COLUMN_ISTATUS, MainApp.fc.getistatus());


// Which row to update, based on the ID
        String selection = " uuid=?";
        String[] selectionArgs = {String.valueOf(MainApp.fc.get_UID())};

        int count = db.update(familyMembers.TABLE_NAME,
                values,
                selection,
                selectionArgs);
        return count;
    }



    public int updateIM() {
        SQLiteDatabase db = this.getReadableDatabase();

// New value for one column
        ContentValues values = new ContentValues();
        values.put(singleIm.COLUMN_ISTATUS, MainApp.fc.getistatus());

// Which row to update, based on the ID
        String selection = " uuid=?";
        String[] selectionArgs = {String.valueOf(MainApp.fc.get_UID())};

        int count = db.update(singleIm.TABLE_NAME,
                values,
                selection,
                selectionArgs);
        return count;
    }
    */
    public boolean checkFormAlreadyFilled(String cluster, String hhno) {
        SQLiteDatabase db = this.getReadableDatabase();
        String query = "SELECT * FROM " + FormsTable.TABLE_NAME + " WHERE " + FormsTable.COLUMN_CLUSTER + "='" + cluster + "' AND " + FormsTable.COLUMN_HHNO + " = '" + hhno+ "' AND " + FormsTable.COLUMN_ISTATUS + " = '1'" ;
        Cursor mCursor = db.rawQuery(query, null);
        if (mCursor != null) {
            if (mCursor.getCount() > 0) {
                return true;
            }
        }
        db.close();
        return false;
    }


}