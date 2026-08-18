package com.akslabs.cloudgallery.data.localdb.migration

import android.util.Log
import androidx.room.migration.Migration
import androidx.sqlite.db.SupportSQLiteDatabase

class Migration10to11_PhotoForumTopics : Migration(10, 11) {
    override fun migrate(db: SupportSQLiteDatabase) {
        Log.i("Migration10to11", "Starting migration 10 → 11 (Photo Forum Topics)")

        db.execSQL("ALTER TABLE photos ADD COLUMN topicId INTEGER DEFAULT NULL")
        db.execSQL("ALTER TABLE photos ADD COLUMN topicName TEXT DEFAULT NULL")

        Log.i("Migration10to11", "Migration 10 → 11 completed successfully")
    }
}
