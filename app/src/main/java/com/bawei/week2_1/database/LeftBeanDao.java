package com.bawei.week2_1.database;

import android.database.Cursor;
import android.database.sqlite.SQLiteStatement;

import org.greenrobot.greendao.AbstractDao;
import org.greenrobot.greendao.Property;
import org.greenrobot.greendao.internal.DaoConfig;
import org.greenrobot.greendao.database.Database;
import org.greenrobot.greendao.database.DatabaseStatement;

import com.bawei.week2_1.model.bean.LeftBean;

// THIS CODE IS GENERATED BY greenDAO, DO NOT EDIT.
/** 
 * DAO for table "LEFT_BEAN".
*/
public class LeftBeanDao extends AbstractDao<LeftBean, Void> {

    public static final String TABLENAME = "LEFT_BEAN";

    /**
     * Properties of entity LeftBean.<br/>
     * Can be used for QueryBuilder and for referencing column names.
     */
    public static class Properties {
        public final static Property Leftjson = new Property(0, String.class, "leftjson", false, "LEFTJSON");
    }


    public LeftBeanDao(DaoConfig config) {
        super(config);
    }
    
    public LeftBeanDao(DaoConfig config, DaoSession daoSession) {
        super(config, daoSession);
    }

    /** Creates the underlying database table. */
    public static void createTable(Database db, boolean ifNotExists) {
        String constraint = ifNotExists? "IF NOT EXISTS ": "";
        db.execSQL("CREATE TABLE " + constraint + "\"LEFT_BEAN\" (" + //
                "\"LEFTJSON\" TEXT);"); // 0: leftjson
    }

    /** Drops the underlying database table. */
    public static void dropTable(Database db, boolean ifExists) {
        String sql = "DROP TABLE " + (ifExists ? "IF EXISTS " : "") + "\"LEFT_BEAN\"";
        db.execSQL(sql);
    }

    @Override
    protected final void bindValues(DatabaseStatement stmt, LeftBean entity) {
        stmt.clearBindings();
 
        String leftjson = entity.getLeftjson();
        if (leftjson != null) {
            stmt.bindString(1, leftjson);
        }
    }

    @Override
    protected final void bindValues(SQLiteStatement stmt, LeftBean entity) {
        stmt.clearBindings();
 
        String leftjson = entity.getLeftjson();
        if (leftjson != null) {
            stmt.bindString(1, leftjson);
        }
    }

    @Override
    public Void readKey(Cursor cursor, int offset) {
        return null;
    }    

    @Override
    public LeftBean readEntity(Cursor cursor, int offset) {
        LeftBean entity = new LeftBean( //
            cursor.isNull(offset + 0) ? null : cursor.getString(offset + 0) // leftjson
        );
        return entity;
    }
     
    @Override
    public void readEntity(Cursor cursor, LeftBean entity, int offset) {
        entity.setLeftjson(cursor.isNull(offset + 0) ? null : cursor.getString(offset + 0));
     }
    
    @Override
    protected final Void updateKeyAfterInsert(LeftBean entity, long rowId) {
        // Unsupported or missing PK type
        return null;
    }
    
    @Override
    public Void getKey(LeftBean entity) {
        return null;
    }

    @Override
    public boolean hasKey(LeftBean entity) {
        // TODO
        return false;
    }

    @Override
    protected final boolean isEntityUpdateable() {
        return true;
    }
    
}
