package com.example.project_application

import android.content.ContentValues
import android.content.Context
import android.database.Cursor
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper


class UserInfoDatabaseHelper(context: Context) : SQLiteOpenHelper(context, DATABASE_NAME, null, DATABASE_VERSION) {

    override fun onCreate(db: SQLiteDatabase) {
        val CREATE_USERINFO_TABLE = "CREATE TABLE $TABLE_USERINFO (" +
                "$COLUMN_ID INTEGER PRIMARY KEY AUTOINCREMENT," +
                "$COLUMN_FIRST_NAME TEXT," +
                "$COLUMN_LAST_NAME TEXT)"
        db.execSQL(CREATE_USERINFO_TABLE)

        addTestUserInfo(db)
    }

    override fun onUpgrade(db: SQLiteDatabase, oldVersion: Int, newVersion: Int) {
        db.execSQL("DROP TABLE IF EXISTS $TABLE_USERINFO")
        onCreate(db)
    }

    private fun addTestUserInfo(db: SQLiteDatabase) {
        val usersInfo = listOf(
            Pair("Иван", "Иванов"),
            Pair("Алексей", "Смирнов"),
            Pair("Мария", "Кузнецова"),
            Pair("Екатерина", "Петрова")
        )

        for (user in usersInfo) {
            val values = ContentValues().apply {
                put(COLUMN_FIRST_NAME, user.first)
                put(COLUMN_LAST_NAME, user.second)
            }
            db.insert(TABLE_USERINFO, null, values)
        }
    }

    fun addUserInfo(firstName: String, lastName: String) {
        val db = this.writableDatabase
        val values = ContentValues()
        values.put(COLUMN_FIRST_NAME, firstName)
        values.put(COLUMN_LAST_NAME, lastName)

        db.insert(TABLE_USERINFO, null, values)
        db.close()
    }

    fun getAllUserInfo(): List<UserInfo> {
        val userInfoList = mutableListOf<UserInfo>()
        val db = this.readableDatabase
        val query = "SELECT * FROM $TABLE_USERINFO"
        val cursor: Cursor = db.rawQuery(query, null)

        if (cursor.moveToFirst()) {
            val idIndex = cursor.getColumnIndex(COLUMN_ID) // Получаем индекс столбца
            val firstNameIndex = cursor.getColumnIndex(COLUMN_FIRST_NAME)
            val lastNameIndex = cursor.getColumnIndex(COLUMN_LAST_NAME)

            if (idIndex != -1 && firstNameIndex != -1 && lastNameIndex != -1) {
                do {
                    val id = cursor.getInt(idIndex)
                    val firstName = cursor.getString(firstNameIndex)
                    val lastName = cursor.getString(lastNameIndex)

                    userInfoList.add(UserInfo(id, firstName, lastName)) // Создаем объект UserInfo
                } while (cursor.moveToNext())
            }
        }
        cursor.close()
        db.close()
        return userInfoList
    }


    companion object {
        private const val DATABASE_NAME = "userinfo_db"
        private const val DATABASE_VERSION = 1
        private const val TABLE_USERINFO = "userinfo"

        private const val COLUMN_ID = "id"
        private const val COLUMN_FIRST_NAME = "first_name"
        private const val COLUMN_LAST_NAME = "last_name"
    }
}
