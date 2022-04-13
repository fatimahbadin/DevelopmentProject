package com.example.easy2book.Model

import android.content.ContentValues
import android.content.Context
import android.database.Cursor
import android.database.SQLException
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper
import com.example.easy2book.Nav.ProfileFragment

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
    public val BookingDetailsColumn_Username = "Username"
    public val BookingDetailsColumn_Email = "Email"
    public val BookingDetailsColumn_Activity = "Activity"
    public val BookingDetailsColumn_TimeBooked = "TimeBooked"
    public val BookingDetailsColumn_MovieName = "MovieName"
    public val BookingDetailsColumn_Exhibition = "Exhibition"
    public val BookingDetailsColumn_Transport = "Transport"
    public val BookingDetailsColumn_LocationFrom = "LocationFrom"
    public val BookingDetailsColumn_LocationTo = "LocationTo"
    public val BookingDetailsColumn_DepartTime = "DepartTime"
    public val BookingDetailsColumn_NoOfPeople = "NoOfPeople"
    public val BookingDetailsColumn_Date = "Date"

    /****************************************/

    /* User Logged Table */
    public val UserLoggedTableName = "UserLogged"
    public val UserLoggedColumn_ID = "ID"
    public val UserLoggedColumn_Username = "Username"
    public val UserLoggedColumn_Password = "Password"
    public val UserLoggedColumn_Email = "Email"

    /****************************************/

    /* Activity Table */
    public val ActivityTableName = "Activity"
    public val ActivityColumn_ID = "ID"
    public val ActivityColumn_Activity = "Activity"

    /****************************************/

    /* Cinema Table */
    public val CinemaTableName = "Cinema"
    public val CinemaColumn_ID = "ID"
    public val CinemaColumn_MovieName = "MovieName"
    public val CinemaColumn_MovieStartTime1 = "MovieStartTime1"
    public val CinemaColumn_MovieStartTime2 = "MovieStartTime2"
    public val CinemaColumn_Capacity = "Capacity"

    /****************************************/

    /* Museum Table */
    public val MuseumTableName = "Museum"
    public val MuseumColumn_ID = "ID"
    public val MuseumColumn_VisitTime1 = "VisitTime1"
    public val MuseumColumn_VisitTime2 = "VisitTime"
    public val MuseumColumn_Exhibitions = "Exhibitions"
    public val MuseumColumn_Capacity = "Capacity"

    /****************************************/

    /* Transport Table */
    public val TransportTableName = "Transport"
    public val TransportColumn_ID = "ID"
    public val TransportColumn_Transport = "Transport"

    /****************************************/

    /* Bus Table */
    public val BusTableName = "Bus"
    public val BusColumn_ID = "ID"
    public val BusColumn_LocationFrom = "LocationFrom"
    public val BusColumn_LocationTo1 = "LocationTo1"
    public val BusColumn_LocationTo2 = "LocationTo2"
    public val BusColumn_DepartTime1 = "DepartTime1"
    public val BusColumn_DepartTime2 = "DepartTime2"
    public val BusColumn_DepartTime3 = "DepartTime3"
    public val BusColumn_Capacity = "Capacity"

    /****************************************/

    /* Train Table */
    public val TrainTableName = "Train"
    public val TrainColumn_ID = "ID"
    public val TrainColumn_LocationFrom = "LocationFrom"
    public val TrainColumn_LocationTo = "LocationTo"
    public val TrainColumn_DepartTime1 = "DepartTime1"
    public val TrainColumn_DepartTime2 = "DepartTime2"
    public val TrainColumn_DepartTime3 = "DepartTime3"
    public val TrainColumn_Capacity = "Capacity"

    /****************************************/


    // This is called the first time a database is accessed
    override fun onCreate(db: SQLiteDatabase?) {
        try {
            var sqlCreateStatement: String =
                "CREATE TABLE " + UserTableName + " ( " +
                        UserColumn_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                        UserColumn_Username + " TEXT NOT NULL, " +
                        UserColumn_Password + " TEXT NOT NULL, " +
                        UserColumn_Email + " TEXT NOT NULL )"

            db?.execSQL(sqlCreateStatement)


            sqlCreateStatement = "CREATE TABLE " + BookingDetailsTableName + " ( " +
                    BookingDetailsColumn_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                    BookingDetailsColumn_Username + " TEXT NOT NULL " +
                    BookingDetailsColumn_Email + " TEXT NOT NULL" +
                    BookingDetailsColumn_Activity + " TEXT, " +
                    BookingDetailsColumn_TimeBooked + " TEXT, " +
                    BookingDetailsColumn_MovieName + " TEXT, " +
                    BookingDetailsColumn_Exhibition + " TEXT, " +
                    BookingDetailsColumn_Transport + " TEXT, " +
                    BookingDetailsColumn_LocationFrom + " TEXT, " +
                    BookingDetailsColumn_LocationTo + " TEXT, " +
                    BookingDetailsColumn_DepartTime + " TEXT, " +
                    BookingDetailsColumn_NoOfPeople + " TEXT NOT NULL, " +
                    BookingDetailsColumn_Date + " TEXT NOT NULL ) "

            db?.execSQL(sqlCreateStatement)


            sqlCreateStatement = "CREATE TABLE " + MuseumTableName + " ( " +
                    UserLoggedColumn_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                    UserLoggedColumn_Username + " TEXT NOT NULL, " +
                    UserLoggedColumn_Password + " TEXT NOT NULL, " +
                    UserLoggedColumn_Email + " TEXT NOT NULL )"

            db?.execSQL(sqlCreateStatement)


            sqlCreateStatement = "CREATE TABLE " + ActivityTableName + " ( " +
                    ActivityColumn_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                    ActivityColumn_Activity + " TEXT NOT NULL ) "

            db?.execSQL(sqlCreateStatement)


            sqlCreateStatement = "CREATE TABLE " + CinemaTableName + " ( " +
                    CinemaColumn_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                    CinemaColumn_MovieName + " TEXT NOT NULL, " +
                    CinemaColumn_MovieStartTime1 + " TEXT NOT NULL, " +
                    CinemaColumn_MovieStartTime2 + " TEXT NOT NULL, " +
                    CinemaColumn_Capacity + " INTEGER NOT NULL ) "

            db?.execSQL(sqlCreateStatement)


            sqlCreateStatement = "CREATE TABLE " + CinemaTableName + " ( " +
                    MuseumColumn_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                    MuseumColumn_VisitTime1 + " TEXT NOT NULL, " +
                    MuseumColumn_VisitTime2 + " TEXT NOT NULL, " +
                    MuseumColumn_Exhibitions + " TEXT NOT NULL, " +
                    MuseumColumn_Capacity + " INTEGER NOT NULL ) "

            db?.execSQL(sqlCreateStatement)


            sqlCreateStatement = "CREATE TABLE " + TransportTableName + " ( " +
                    TransportColumn_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                    TransportColumn_Transport + " TEXT NOT NULL ) "

            db?.execSQL(sqlCreateStatement)


            sqlCreateStatement = "CREATE TABLE " + BusTableName + " ( " +
                    BusColumn_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                    BusColumn_LocationFrom + " TEXT NOT NULL, " +
                    BusColumn_LocationTo1 + " TEXT NOT NULL, " +
                    BusColumn_LocationTo2 + " TEXT NOT NULL, " +
                    BusColumn_DepartTime1 + " TEXT NOT NULL, " +
                    BusColumn_DepartTime2 + " TEXT NOT NULL, " +
                    BusColumn_DepartTime3 + " TEXT NOT NULL, " +
                    BusColumn_Capacity + " INTEGER NOT NULL ) "

            db?.execSQL(sqlCreateStatement)


            sqlCreateStatement = "CREATE TABLE " + TrainTableName + " ( " +
                    TrainColumn_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                    TrainColumn_LocationFrom + " TEXT NOT NULL, " +
                    TrainColumn_LocationTo + " TEXT NOT NULL, " +
                    TrainColumn_DepartTime1 + " TEXT NOT NULL, " +
                    TrainColumn_DepartTime2 + " TEXT NOT NULL, " +
                    TrainColumn_DepartTime3 + " TEXT NOT NULL, " +
                    TrainColumn_Capacity + " INTEGER NOT NULL ) "

            db?.execSQL(sqlCreateStatement)

        } catch (e: SQLException) {

        }

    }

    // This is called if the database ver. is changed
    override fun onUpgrade(db: SQLiteDatabase?, oldVersion: Int, newVersion: Int) {
        TODO("Not yet implemented")
    }

//  functions for the user table
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


//  functions for the userLogged table
    fun getAllLoggedUsers(): ArrayList<UserLogged> {
        val userLoggedList = ArrayList<UserLogged>()
        val db: SQLiteDatabase = this.readableDatabase
        val sqlStatement = "SELECT * FROM $UserLoggedTableName"

        val cursor: Cursor = db.rawQuery(sqlStatement, null)

        if (cursor.moveToFirst())
            do {
                val id: Int = cursor.getInt(0)
                val username: String = cursor.getString(1)
                val password: String = cursor.getString(2)
                val email: String = cursor.getString(3)
                val b = UserLogged(id, username, password, email)
                userLoggedList.add(b)
            } while (cursor.moveToNext())

        cursor.close()
        db.close()

        return userLoggedList
    }

    fun addLoggedUser(userL : UserLogged) : Boolean {

        val db: SQLiteDatabase = this.writableDatabase
        val cv: ContentValues = ContentValues()

        cv.put(UserColumn_Username, userL.Username)
        cv.put(UserColumn_Password, userL.Password)
        cv.put(UserColumn_Email, userL.Email)

        val success  =  db.insert(UserLoggedTableName, null, cv)

        db.close()
        return success != -1L
    }

//  functions for the booking details table
    fun getAllConfirmDetails(): ArrayList<ConfirmDetails> {
        val detailsList = ArrayList<ConfirmDetails>()
        val db: SQLiteDatabase = this.readableDatabase
        val sqlStatement = "SELECT * FROM $BookingDetailsTableName"

        val cursor: Cursor = db.rawQuery(sqlStatement, null)

        if (cursor.moveToFirst())
            do {
                val id: Int = cursor.getInt(0)
                val username: String = cursor.getString(1)
                val email: String = cursor.getString(2)
                val activity: String = cursor.getString(3)
                val timeBooked: String = cursor.getString(4)
                val movieName: String = cursor.getString(5)
                val exhibition: String = cursor.getString(6)
                val transport: String = cursor.getString(7)
                val locationFrom: String = cursor.getString(8)
                val locationTo: String = cursor.getString(9)
                val departTime: String = cursor.getString(10)
                val noOfPeople: String = cursor.getString(11)
                val date: String = cursor.getString(12)

                val b = ConfirmDetails(id, username, email, activity, timeBooked,
                    movieName, exhibition, transport, locationFrom, locationTo,
                    departTime, noOfPeople, date)
                detailsList.add(b)
            } while (cursor.moveToNext())

        cursor.close()
        db.close()

        return detailsList
    }

    fun addConfirmDetails(confirmDetails : ConfirmDetails) : Boolean {

        val db: SQLiteDatabase = this.writableDatabase
        val cv: ContentValues = ContentValues()

        cv.put(BookingDetailsColumn_Username, confirmDetails.Username)
        cv.put(BookingDetailsColumn_Email, confirmDetails.Email)
        cv.put(BookingDetailsColumn_Activity, confirmDetails.Activity)
        cv.put(BookingDetailsColumn_TimeBooked, confirmDetails.TimeBooked)
        cv.put(BookingDetailsColumn_MovieName, confirmDetails.MovieName)
        cv.put(BookingDetailsColumn_Exhibition, confirmDetails.Exhibition)
        cv.put(BookingDetailsColumn_Transport, confirmDetails.Transport)
        cv.put(BookingDetailsColumn_LocationFrom, confirmDetails.LocationFrom)
        cv.put(BookingDetailsColumn_LocationTo, confirmDetails.LocationTo)
        cv.put(BookingDetailsColumn_DepartTime, confirmDetails.DepartTime)
        cv.put(BookingDetailsColumn_NoOfPeople, confirmDetails.NoOfPeople)
        cv.put(BookingDetailsColumn_Date, confirmDetails.Date)


        val success  =  db.insert(BookingDetailsTableName, null, cv)

        db.close()
        return success != -1L
    }


//  functions for the Activity and Transport tables
    fun getAllActivity(): ArrayList<Activity> {
        val activityList = ArrayList<Activity>()
        val db: SQLiteDatabase = this.readableDatabase
        val sqlStatement = "SELECT * FROM $ActivityTableName"

        val cursor: Cursor = db.rawQuery(sqlStatement, null)

        if (cursor.moveToFirst())
            do {
                val id: Int = cursor.getInt(0)
                val activity: String = cursor.getString(1)
                val b = Activity(id, activity)
                activityList.add(b)
            } while (cursor.moveToNext())

        cursor.close()
        db.close()

        return activityList
    }


    fun getAllTransport(): ArrayList<Transport> {
        val transportList = ArrayList<Transport>()
        val db: SQLiteDatabase = this.readableDatabase
        val sqlStatement = "SELECT * FROM $TransportTableName"

        val cursor: Cursor = db.rawQuery(sqlStatement, null)

        if (cursor.moveToFirst())
            do {
                val id: Int = cursor.getInt(0)
                val transport: String = cursor.getString(1)
                val b = Transport(id, transport)
                transportList.add(b)
            } while (cursor.moveToNext())

        cursor.close()
        db.close()

        return transportList
    }


    fun getAllCinema(): ArrayList<CinemaClass> {
        val cinemaList = ArrayList<CinemaClass>()
        val db: SQLiteDatabase = this.readableDatabase
        val sqlStatement = "SELECT * FROM $CinemaTableName"

        val cursor: Cursor = db.rawQuery(sqlStatement, null)

        if (cursor.moveToFirst())
            do {
                val id: Int = cursor.getInt(0)
                val movieName: String = cursor.getString(1)
                val movieStartTime1: String = cursor.getString(2)
                val movieStartTime2: String = cursor.getString(3)
                val capacity: Int = cursor.getInt(4)
                val b = CinemaClass(id, movieName, movieStartTime1,
                    movieStartTime2, capacity)
                cinemaList.add(b)
            } while (cursor.moveToNext())

        cursor.close()
        db.close()

        return cinemaList
    }


    fun getAllMuseum(): ArrayList<MuseumClass> {
        val museumList = ArrayList<MuseumClass>()
        val db: SQLiteDatabase = this.readableDatabase
        val sqlStatement = "SELECT * FROM $MuseumTableName"

        val cursor: Cursor = db.rawQuery(sqlStatement, null)

        if (cursor.moveToFirst())
            do {
                val id: Int = cursor.getInt(0)
                val visitTime1: String = cursor.getString(1)
                val visitTime2: String = cursor.getString(2)
                val exhibition: String = cursor.getString(3)
                val capacity: Int = cursor.getInt(4)
                val b = MuseumClass(id, visitTime1, visitTime2, exhibition, capacity)
                museumList.add(b)
            } while (cursor.moveToNext())

        cursor.close()
        db.close()

        return museumList
    }


    fun getAllBus(): ArrayList<BusClass> {
        val busList = ArrayList<BusClass>()
        val db: SQLiteDatabase = this.readableDatabase
        val sqlStatement = "SELECT * FROM $BusTableName"

        val cursor: Cursor = db.rawQuery(sqlStatement, null)

        if (cursor.moveToFirst())
            do {
                val id: Int = cursor.getInt(0)
                val locationFrom: String = cursor.getString(1)
                val locationTo1: String = cursor.getString(2)
                val locationTo2: String = cursor.getString(3)
                val departTime1: String = cursor.getString(4)
                val departTime2: String = cursor.getString(5)
                val departTime3: String = cursor.getString(6)
                val capacity: Int = cursor.getInt(7)
                val b = BusClass(id, locationFrom, locationTo1, locationTo2,
                    departTime1, departTime2, departTime3, capacity)
                busList.add(b)
            } while (cursor.moveToNext())

        cursor.close()
        db.close()

        return busList
    }


    fun getAllTrain(): ArrayList<TrainClass> {
        val trainList = ArrayList<TrainClass>()
        val db: SQLiteDatabase = this.readableDatabase
        val sqlStatement = "SELECT * FROM $TrainTableName"

        val cursor: Cursor = db.rawQuery(sqlStatement, null)

        if (cursor.moveToFirst())
            do {
                val id: Int = cursor.getInt(0)
                val locationFrom: String = cursor.getString(1)
                val locationTo: String = cursor.getString(2)
                val departTime1: String = cursor.getString(3)
                val departTime2: String = cursor.getString(4)
                val departTime3: String = cursor.getString(5)
                val capacity: Int = cursor.getInt(6)
                val b = TrainClass(id, locationFrom, locationTo, departTime1,
                    departTime2, departTime3, capacity)
                trainList.add(b)
            } while (cursor.moveToNext())

        cursor.close()
        db.close()

        return trainList
    }

}
