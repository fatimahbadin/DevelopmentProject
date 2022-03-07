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

//    /****************************************/
//
//    /* Question table */
//    public val QuestionTableName = "Questions"
//    public val QuestionColumn_ID = "ID"
//    public val QuestionColumn_TopicID = "TopicID"
//    public val Column_Questions = "Questions"
//    //public val QuestionColumn_Image = "Image"
//
//    /****************************************/
//
//    /* Answer table */
//    public val AnswerTableName = "Answers"
//    public val AnswerColumn_ID = "ID"
//    public val AnswerColumn_QuestionID = "QuestionID"
//    public val Column_Answers = "Answers"
//    public val AnswersColumn_IsCorrect = "IsCorrect"
//   /* public val AnswersColumn_IncorrectAnswer2 = "IncorrectAnswer2"
//    public val AnswersColumn_IncorrectAnswer3 = "IncorrectAnswer3"
//    public val AnswersColumn_IncorrectAnswer4 = "IncorrectAnswer4"*/
//
//    /****************************************/
//
//    public val StudentTableName = "Students"
//    public val StudentColumn_ID = "ID"
//    public val StudentColumn_Name = "Name"
//    public val StudentColumn_Grade = "Grade"
//    public val StudentColumn_DateTaken = "DateTaken"
//
//    /****************************************/
//
//    public val AdminTableName = "Admin"
//    public val AdminColumn_ID = "ID"
//    public val AdminColumn_AdminNumber = "AdminNumber"
//    public val AdminColumn_Password = "Password"
//
//    /****************************************/

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


//            sqlCreateStatement = "CREATE TABLE " + QuestionTableName + " ( " + QuestionColumn_ID +
//                    " INTEGER PRIMARY KEY AUTOINCREMENT, " + QuestionColumn_TopicID +
//                    " INTEGER NOT NULL, " + Column_Questions + " TEXT ) "
//                    //QuestionColumn_Image + " BLOB )"
//
//            db?.execSQL(sqlCreateStatement)
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
}

//    fun getAllQuestions(): ArrayList<Question> {
//        val questionList = ArrayList<Question>()
//        val db: SQLiteDatabase = this.readableDatabase
//        val sqlStatement = "SELECT * FROM $QuestionTableName"
//
//        val cursor: Cursor = db.rawQuery(sqlStatement, null)
//
//        if (cursor.moveToFirst())
//            do {
//                val id: Int = cursor.getInt(0)
//                val topicID: Int = cursor.getInt(1)
//                val questions: String = cursor.getString(2)
//                //val image: ByteArray? = cursor.getBlob(3)
//                val b = Question(id, topicID, questions, /*image*/)
//                questionList.add(b)
//            } while (cursor.moveToNext())
//
//            cursor.close()
//        db.close()
//
//        return questionList
//    }
//
//    fun getAllAnswers(i: Int): ArrayList<Answer> {
//        val answerList = ArrayList<Answer>()
//        val db: SQLiteDatabase = this.readableDatabase
//        val sqlStatement = "SELECT * FROM $AnswerTableName WHERE $AnswerColumn_QuestionID == $i"
//
//        val cursor: Cursor = db.rawQuery(sqlStatement, null)
//
//        if (cursor.moveToFirst())
//            do {
//                val id: Int = cursor.getInt(0)
//                val questionID = cursor.getInt(1)
//                val answers = cursor.getString(2)
//                val isCorrect = cursor.getInt(3)
//                val b = Answer(id, questionID, answers, isCorrect)
//                answerList.add(b)
//            } while (cursor.moveToNext())
//
//            cursor.close()
//        db.close()
//
//        return answerList
//    }
//
//    fun getAllStudents(): ArrayList<Student> {
//
//        val studentList = ArrayList<Student>()
//        val db: SQLiteDatabase = this.readableDatabase
//        val sqlStatement = "SELECT * FROM $StudentTableName"
//
//        val cursor: Cursor = db.rawQuery(sqlStatement, null)
//
//        if (cursor.moveToFirst())
//            do {
//                val id: Int = cursor.getInt(0)
//                val name: String = cursor.getString(1)
//                val grade: Int = cursor.getInt(2)
//                val dateTaken: String = cursor.getString(3)
//                val b = Student(id, name, grade, dateTaken)
//                studentList.add(b)
//            } while (cursor.moveToNext())
//
//        cursor.close()
//        db.close()
//
//        return studentList
//    }
//
//    fun addStudent(student : Student) : Boolean {
//
//        val db: SQLiteDatabase = this.writableDatabase
//        val cv: ContentValues = ContentValues()
//
//        cv.put(StudentColumn_Name, student.Name)
//        cv.put(StudentColumn_Grade, student.Grade)
//        cv.put(StudentColumn_DateTaken, student.dateTaken)
//
//        val success  =  db.insert(StudentTableName, null, cv)
//
//        db.close()
//        return success != -1L
//
//    }
//
//    fun updateStudent(student: Student) : Boolean {
//
//        val db: SQLiteDatabase = this.writableDatabase
//        val cv: ContentValues = ContentValues()
//
//        cv.put(StudentColumn_Name, student.Name)
//        cv.put(StudentColumn_Grade, student.Grade)
//        cv.put(StudentColumn_DateTaken, student.dateTaken)
//
//        val success = db.update(StudentTableName, cv, "$StudentColumn_ID = ${student.ID}", null) == 1
//        db.close()
//        return success
//
//    }
//
//    fun getAllAdmins(): ArrayList<Admin> {
//        val adminList = ArrayList<Admin>()
//
//        val db: SQLiteDatabase = this.readableDatabase
//        val sqlStatement = "SELECT * FROM $AdminTableName"
//
//        val cursor: Cursor = db.rawQuery(sqlStatement, null)
//        if(cursor.moveToFirst())
//            do{
//                val id: Int = cursor.getInt(0)
//                val adminNumber: Int = cursor.getInt(1)
//                val password: Int = cursor.getInt(2)
//
//                val b = Admin(id, adminNumber, password)
//                adminList.add(b)
//            } while (cursor.moveToNext())
//
//            cursor.close()
//        db.close()
//
//        return adminList
//    }
//
//    fun addAdminQuestion(q: Question): Boolean {
//        val db: SQLiteDatabase = this.writableDatabase
//        val cv: ContentValues = ContentValues()
//
//        cv.put(QuestionColumn_TopicID, q.ID)
//        cv.put(Column_Questions, q.Questions)
//
//        val success = db.insert(QuestionTableName, null, cv)
//        db.close()
//        return success != -1L
//    }
//
//    fun addAdminAnswers(a : Answer): Boolean {
//        val db: SQLiteDatabase = this.writableDatabase
//        val cv: ContentValues = ContentValues()
//
//        cv.put(AnswerColumn_QuestionID, a.QuestionID)
//        cv.put(Column_Answers, a.Answers)
//        cv.put(AnswersColumn_IsCorrect, a.IsCorrect)
//
//        val success = db.insert(AnswerTableName, null, cv)
//        db.close()
//        return success != -1L
//    }
//}
