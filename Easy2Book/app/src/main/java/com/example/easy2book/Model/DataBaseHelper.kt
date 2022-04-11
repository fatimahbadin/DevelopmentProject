package com.example.easy2book.Model

import android.content.ContentValues
import android.content.Context
import android.database.Cursor
import android.database.SQLException
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteException
import android.database.sqlite.SQLiteOpenHelper

/* Data config */
private val DataBaseName = "Easy2BookDatabase.db"
private val ver : Int = 1

class DataBaseHelper(context: Context) : SQLiteOpenHelper(context,DataBaseName,null ,ver) {

    /* User Table */
    public val UserTableName = "User"
    public val UserColumn_ID = "ID"
    public val UserColumn_Username = "Username"
    public val UserColumn_Password = "Password"
    public val UserColumn_Email = "Email"

   /****************************************/
   /* Confirmation Details Table */
    public val BookingDetailsTableName = "BookingDetails"
    public val BookingDetailsColumn_ID = "ID"
    public val BookingDetailsColumn_Activity = "Activity"
    public val BookingDetailsColumn_TimeBooked = "TimeBooked"
    public val BookingDetailsColumn_MovieName = "MovieName"
    public val BookingDetailsColumn_Exhibition = "Exhibition"
    public val BookingDetailsColumn_Transport = "Transport"
    public val BookingDetailsColumn_LocationFrom = "LocationFrom"
    public val BookingDetailsColumn_LocationTo = "LocationTo"
    public val BookingDetailsColumn_DepartTime = "DepartTime"
    public val BookingDetailsColumn_NoOfPeople = "NoOfPeople"

    /****************************************/


    // This is called the first time a database is accessed
    // Create a new database
    override fun onCreate(db: SQLiteDatabase?) {
        try {
            var sqlCreateStatement: String =
                "CREATE TABLE " + UserTableName + " ( " + UserColumn_ID +
                        " INTEGER PRIMARY KEY AUTOINCREMENT, " + UserColumn_Username + " TEXT NOT NULL, " +
                        UserColumn_Username + " TEXT NOT NULL, " + UserColumn_Password + " TEXT NOT NULL, " +
                        UserColumn_Email + " TEXT NOT NULL )"

            db?.execSQL(sqlCreateStatement)


            sqlCreateStatement = "CREATE TABLE " + BookingDetailsTableName + " ( " + BookingDetailsColumn_ID +
                    " INTEGER PRIMARY KEY AUTOINCREMENT, " + BookingDetailsColumn_Activity +
                    " TEXT, " + BookingDetailsColumn_TimeBooked + " TEXT, " +
                    BookingDetailsColumn_MovieName + " TEXT, " + BookingDetailsColumn_Exhibition +
                    " TEXT, " + BookingDetailsColumn_Transport + " TEXT, " +
                    BookingDetailsColumn_LocationFrom + " TEXT, " + BookingDetailsColumn_LocationTo +
                    " TEXT, " + BookingDetailsColumn_DepartTime + " TEXT, " +
                    BookingDetailsColumn_NoOfPeople + " TEXT NOT NULL )"

            db?.execSQL(sqlCreateStatement)
//
//            sqlCreateStatement = "CREATE TABLE " + AnswerTableName + " ( " + AnswerColumn_ID +
//                    " INTEGER PRIMARY KEY AUTOINCREMENT, " + AnswerColumn_QuestionID +
//                    " INTEGER NOT NULL, " + Column_Answers + " TEXT NOT NULL, " +
//                    AnswersColumn_IsCorrect + " INTEGER NOT NULL )"
//
//            db?.execSQL(sqlCreateStatement)
//
//            sqlCreateStatement = "CREATE TABLE " + StudentTableName + " ( " + StudentColumn_ID +
//                    " INTEGER PRIMARY KEY AUTOINCREMENT, " + StudentTableName + " TEXT NOT NULL, " +
//                    StudentColumn_Grade + " INTEGER NOT NULL, " + StudentColumn_DateTaken +
//                    " TEXT NOT NULL )"
//
//            db?.execSQL(sqlCreateStatement)
//
//            sqlCreateStatement = "CREATE TABLE " + AdminTableName + " ( " + AdminColumn_ID +
//                    " INTEGER PRIMARY KEY AUTOINCREMENT, " + AdminColumn_AdminNumber + " INTEGER NOT NULL, " +
//                    AdminColumn_Password + " INTEGER NOT NULL )"
//
//            db?.execSQL(sqlCreateStatement)

        } catch (e: SQLException) {
        }

    }

    // This is called if the database ver. is changed
    override fun onUpgrade(db: SQLiteDatabase?, oldVersion: Int, newVersion: Int) {
        TODO("Not yet implemented")
    }

    fun getAllUsers(): ArrayList<User> {
        val userList = ArrayList<User>()
        val db: SQLiteDatabase = this.readableDatabase
        val sqlStatement = "SELECT * FROM $UserTableName"

        val cursor: Cursor = db.rawQuery(sqlStatement, null)

        if (cursor.moveToFirst())
            do {
                val id: Int = cursor.getInt(0)
                val username: String = cursor.getString(1)
                val password: String = cursor.getString(2)
                val email: String = cursor.getString(3)
                val b = User(id, username, password, email)
                userList.add(b)
            } while (cursor.moveToNext())

        cursor.close()
        db.close()

        return userList
    }

    fun addUser(user : User) : Int {
        val nameExists = checkUserName(user)
        if (nameExists < 0)
            return nameExists

        val db: SQLiteDatabase = this.writableDatabase
        val cv: ContentValues = ContentValues()

        cv.put(UserColumn_Username, user.Username)
        cv.put(UserColumn_Password, user.Password)
        cv.put(UserColumn_Email, user.Email)

        val success  =  db.insert(UserTableName, null, cv)

        db.close()

        if (success.toInt() == -1) {
            return success.toInt()
        } else {
            return 1
        }
    }

    private fun checkUserName(user: User) : Int {
        val db: SQLiteDatabase
        try {
            db = this.readableDatabase
        } catch (e: SQLException) {
            return -2
        }

        val username = user.Username.lowercase()

        val sqlStatement = "SELECT * FROM $UserTableName WHERE $UserColumn_Username ==?"

        val param = arrayOf(username)
        val cursor: Cursor = db.rawQuery(sqlStatement, param)

        if (cursor.moveToFirst()){
            val a = cursor.getInt(0)
            cursor.close()
            db.close()
            return -3
        }

        cursor.close()
        db.close()
        return 0

    }

    fun loginValid(un: String, pw: String): Boolean {
        val db: SQLiteDatabase = this.readableDatabase
        val cursor: Cursor = db.rawQuery("SELECT * FROM " + UserTableName +
                " WHERE $UserColumn_Username=? AND $UserColumn_Password=?", arrayOf(un, pw))

        if (cursor != null) {
            if (cursor.count > 0) {
                return true
            }
        }
        return false
    }


    fun getAllConfirmDetails(): ArrayList<ConfirmDetails> {
        val detailsList = ArrayList<ConfirmDetails>()
        val db: SQLiteDatabase = this.readableDatabase
        val sqlStatement = "SELECT * FROM $BookingDetailsTableName"

        val cursor: Cursor = db.rawQuery(sqlStatement, null)

        if (cursor.moveToFirst())
            do {
                val id: Int = cursor.getInt(0)
                val activity: String = cursor.getString(1)
                val timeBooked: String = cursor.getString(2)
                val movieName: String = cursor.getString(3)
                val exhibition: String = cursor.getString(4)
                val transport: String = cursor.getString(5)
                val locationFrom: String = cursor.getString(6)
                val locationTo: String = cursor.getString(7)
                val departTime: String = cursor.getString(8)
                val noOfPeople: String = cursor.getString(9)

                val b = ConfirmDetails(id, activity, timeBooked, movieName, exhibition,
                    transport, locationFrom, locationTo, departTime, noOfPeople)
                detailsList.add(b)
            } while (cursor.moveToNext())

        cursor.close()
        db.close()

        return detailsList
    }

    fun addConfirmDetails(confirmDetails : ConfirmDetails) : Boolean {

        val db: SQLiteDatabase = this.writableDatabase
        val cv: ContentValues = ContentValues()

        cv.put(BookingDetailsColumn_Activity, confirmDetails.Activity)
        cv.put(BookingDetailsColumn_TimeBooked, confirmDetails.TimeBooked)
        cv.put(BookingDetailsColumn_MovieName, confirmDetails.MovieName)
        cv.put(BookingDetailsColumn_Exhibition, confirmDetails.Exhibition)
        cv.put(BookingDetailsColumn_Transport, confirmDetails.Transport)
        cv.put(BookingDetailsColumn_LocationFrom, confirmDetails.LocationFrom)
        cv.put(BookingDetailsColumn_LocationTo, confirmDetails.LocationTo)
        cv.put(BookingDetailsColumn_DepartTime, confirmDetails.DepartTime)
        cv.put(BookingDetailsColumn_NoOfPeople, confirmDetails.NoOfPeople)


        val success  =  db.insert(BookingDetailsTableName, null, cv)

        db.close()
        return success != -1L
    }

}
