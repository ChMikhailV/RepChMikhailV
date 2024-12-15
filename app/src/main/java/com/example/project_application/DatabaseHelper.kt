package com.example.project_application

import android.content.ContentValues
import android.content.Context
import android.database.Cursor
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper

class DatabaseHelper(context: Context) : SQLiteOpenHelper(context, DATABASE_NAME, null, DATABASE_VERSION) {

    override fun onCreate(db: SQLiteDatabase) {
        val CREATE_USERS_TABLE = "CREATE TABLE $TABLE_USERS (" +
                "$COLUMN_ID INTEGER PRIMARY KEY AUTOINCREMENT," +
                "$COLUMN_USERNAME TEXT," +
                "$COLUMN_PASSWORD TEXT)"
        db.execSQL(CREATE_USERS_TABLE)

        addTestUsers(db)
    }

    override fun onUpgrade(db: SQLiteDatabase, oldVersion: Int, newVersion: Int) {
        db.execSQL("DROP TABLE IF EXISTS $TABLE_USERS")
        onCreate(db)
    }

    private fun addTestUsers(db: SQLiteDatabase) {
        val users = listOf(
            Pair("admin", "admin123"),
            Pair("user1", "password1"),
            Pair("chmikhailv", "password2023")
        )

        for (user in users) {
            val values = ContentValues().apply {
                put(COLUMN_USERNAME, user.first)
                put(COLUMN_PASSWORD, user.second)
            }
            db.insert(TABLE_USERS, null, values)
        }
    }

    fun addUser(username: String, password: String) {
        val db = this.writableDatabase
        val values = ContentValues()
        values.put(COLUMN_USERNAME, username)
        values.put(COLUMN_PASSWORD, password)

        db.insert(TABLE_USERS, null, values)
        db.close()
    }

    fun checkUser(username: String, password: String): Boolean {
        val db = this.readableDatabase
        val query = "SELECT * FROM $TABLE_USERS WHERE $COLUMN_USERNAME = ? AND $COLUMN_PASSWORD = ?"
        val cursor: Cursor = db.rawQuery(query, arrayOf(username, password))

        val exists = cursor.count > 0
        cursor.close()
        db.close()
        return exists
    }

    companion object {
        private const val DATABASE_NAME = "users_db"
        private const val DATABASE_VERSION = 1
        private const val TABLE_USERS = "users"

        private const val COLUMN_ID = "id"
        private const val COLUMN_USERNAME = "username"
        private const val COLUMN_PASSWORD = "password"
    }
}
