package com.example.project_basketballstats;

import java.util.ArrayList;
import java.util.List;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;
import android.widget.Toast;

public class MainDatabase {

	private final static String TABLE_NAME = "LOGIN";
	private final static String TABLE_NAME1 = "TEAM";
	private final static String TABLE_NAME2 = "PLAYER";
	private final static String TABLE_NAME3 = "GAME";
	private final static String TABLE_NAME4 = "PLAYER_STATISTICS";
	private final static String TABLE_NAME5 = "WIN";
	public final static String _ID = "_id";
	final static String Username = "Username";
	final static String Userid = "Userid";
	final static String Teamid = "Teamid";
	final static String Team1 = "Team1name";
	final static String Team2 = "Team2name";
	final static String Teamname = "Teamname";
	final static String Playername = "Playername";
	final static String Playerpos = "Playerpos";
	final static String Numofplayers = "numofplayers";
	final static String Password = "Password";
	final static String Date = "Date";
	final static String Location = "Location";
	final static String Time = "Time";
	final static String Email = "Email";
	final static String THREEPMADE = "THREEPM";
	final static String THREEPMISS = "THREEPA";
	final static String FTMISS = "FTA";
	final static String FTMADE = "FTM";
	final static String FGMISS = "FGA";
	final static String FGMADE = "FGM";
	final static String D_RBD = "D_RBD";
	final static String O_RBD = "R_RBD";
	final static String ASSIST = "ASSIST";
	final static String FOUL = "FOUL";
	final static String TURNOVER = "TURNOVER";
	final static String BLOCK = "BLOCK";
	final static String STEAL = "STEAL";
	final static String TEAM1SCORE = "TEAM1SCORE";
	final static String TEAM2SCORE = "TEAM2SCORE";
	

	
	final public static String CREATE_CMD_GAME =

			"CREATE TABLE " + TABLE_NAME3 + " (" + _ID 
			+ " INTEGER PRIMARY KEY AUTOINCREMENT, " + Userid
		    + " INTEGER FORIEGN KEY REFERENCES LOGIN, " + Team1
		    + " INTEGER FORIEGN KEY REFERENCES TEAM, "  + Team2
		    + " INTEGER FORIEGN KEY REFERENCES TEAM, " + Date
					+ " TEXT NOT NULL, " + Time
					+ " TEXT NOT NULL, " 
				 + Location + " TEXT NOT NULL);";
	
	final public static String CREATE_CMD_WIN =

			"CREATE TABLE " + TABLE_NAME5 + " (" + _ID 
		    + " INTEGER FORIEGN KEY REFERENCES GAME, " + TEAM1SCORE
					+ " INTEGER, " + TEAM2SCORE
					+ " INTEGER);";
	
	final public static String CREATE_CMD_PLAYER_STATISTICS =

			"CREATE TABLE " + TABLE_NAME4 + " (" + _ID
		    + " INTEGER FORIEGN KEY REFERENCES PLAYER, " + THREEPMADE
		    + " INTEGER, " + THREEPMISS
					+ " INTEGER, " + FTMISS
					+ " INTEGER, " + FTMADE
					+ " INTEGER, " + FGMADE
					+ " INTEGER, " + FGMISS
					+ " INTEGER, " + D_RBD
					+ " INTEGER, " + O_RBD
					+ " INTEGER, " + ASSIST
					+ " INTEGER, " + FOUL
					+ " INTEGER, " + TURNOVER
					+ " INTEGER, " + BLOCK
					+ " INTEGER, " 
				 + STEAL + " INTEGER);";
	
	final public static String CREATE_CMD_PLAYER =

			"CREATE TABLE " + TABLE_NAME2 + " (" + _ID
		    + " INTEGER PRIMARY KEY AUTOINCREMENT, " + Teamid
		    + " INTEGER FORIEGN KEY REFERENCES TEAM, " + Playername
					+ " TEXT NOT NULL, "
				 + Playerpos + " TEXT NOT NULL);";
	
	final public static String CREATE_CMD_TEAM =

	"CREATE TABLE " + TABLE_NAME1 + " (" + _ID
    + " INTEGER PRIMARY KEY AUTOINCREMENT, "  + Userid
    + " INTEGER FORIEGN KEY REFERENCES LOGIN, " + Teamname
			+ " TEXT NOT NULL, "
			+ Numofplayers + " TEXT NOT NULL);";
	
	final public static String CREATE_CMD_LOGIN =

			"CREATE TABLE " + TABLE_NAME + " (" + _ID
		    + " INTEGER PRIMARY KEY AUTOINCREMENT, " + Username
					+ " TEXT NOT NULL, "
					+ Password + " TEXT NOT NULL, " + Email + " TEXT NOT NULL);";

	final private static String NAME = "projectbasketball_db";
	final private static Integer VERSION = 110;
	private SQLiteDatabase db;
    private final Context context;
    private DataBaseHelper dbHelper;

    private static class DataBaseHelper extends SQLiteOpenHelper {

    	public DataBaseHelper(Context context) {
    		super(context, NAME, null, VERSION);
    		// TODO Auto-generated constructor stub
    	}

    	@Override
    	public void onCreate(SQLiteDatabase db) {
    		// TODO Auto-generated method stub
    		db.execSQL(CREATE_CMD_LOGIN);
    		db.execSQL(CREATE_CMD_TEAM);
    		db.execSQL(CREATE_CMD_PLAYER);
    		db.execSQL(CREATE_CMD_GAME);
    		db.execSQL(CREATE_CMD_PLAYER_STATISTICS);
    		db.execSQL(CREATE_CMD_WIN);
    		Log.i("create database", "created");

    	}

    	@Override
    	public void onUpgrade(SQLiteDatabase db, int arg1, int arg2) {
    		db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME);
    		db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME1);
    		db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME2);
    		db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME3);
    		db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME4);
    		db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME5);
            // Create a new one.
            onCreate(db);
    	}
    }



    public  MainDatabase(Context _context)
    {
        context = _context;
    }


    public MainDatabase open() throws SQLException
    {
    	dbHelper = new DataBaseHelper(context);
        db = dbHelper.getWritableDatabase();
        return this;
    }

    public void close()
    {
        dbHelper.close();
    }

    public long insertEntry(String userName,String password,String mailId )
    {
        ContentValues newValues = new ContentValues();
        newValues.put(Username, userName);
        newValues.put(Password,password);
        newValues.put(Email,mailId);
        Log.i("Tag", "in insert");
        Log.d("values", userName + " " + password + " " + mailId);
        return db.insert(TABLE_NAME, null, newValues);
    }
    
    public long insertStatistics(String id,String threepmade,String threepmiss, String ftmiss, String ftmade, String fgmade,String fgmiss ,String d_rbd,String o_rbd, String assist, String foul, String turnover, String block, String Steal)
    {
        ContentValues newValues = new ContentValues();
        newValues.put(_ID, id);
        newValues.put(THREEPMADE,threepmade);
        newValues.put(THREEPMISS,threepmiss);
        newValues.put(FTMISS,ftmiss);
        newValues.put(FTMADE,ftmade);
        newValues.put(FGMADE,fgmade);
        newValues.put(FGMISS,fgmiss);
        newValues.put(D_RBD,d_rbd);
        newValues.put(O_RBD,o_rbd);
        newValues.put(ASSIST,assist);
        newValues.put(TURNOVER,turnover);
        newValues.put(FOUL,foul);
        newValues.put(STEAL,Steal);
        newValues.put(BLOCK, block);
        return db.insert(TABLE_NAME4, null, newValues);
    }

    
    public long insertGame(String UserID, String team1,String team2,String date2, Integer integer, String type, String loc )
    {
        ContentValues newValues = new ContentValues();
        newValues.put(Userid, UserID);
        newValues.put(Team1, team1);
        newValues.put(Team2,team2);
        newValues.put(Date,date2);
        newValues.put(Time,integer);
        newValues.put(Location,loc);
        return db.insert(TABLE_NAME3, null, newValues);
    }
    
    public List<String> getgame(String UserId) {
    	Cursor c = db.rawQuery("SELECT * FROM " + TABLE_NAME3 + " WHERE " + Userid + " = '" + UserId + "'", null );
    	List<String> list = new ArrayList<String>();
    	if(c.getCount()>0) {
    	for(c.moveToFirst();!c.isAfterLast();c.moveToNext()) {
    	     list.add(c.getString(c.getColumnIndex(Team1)).replace("\n", "") + " vs " + c.getString(c.getColumnIndex(Team2)).replace("\n", "") +"\n"+ c.getString(c.getColumnIndex(Date))+"\n"+c.getString(c.getColumnIndex(Time))+"\n"+c.getString(c.getColumnIndex(Location)));
    	}
    	}
    	return list;
    }
    
    public String getPlayerId(String PlayerName) {
    	String r = "";
		Cursor c = db.query(TABLE_NAME2, null, Playername + " = ?", new String[]{PlayerName}, null, null, null);
		if(c.getCount()>0) {
			Log.d("Tag", "not null");
    	for(c.moveToFirst(); !c.isAfterLast(); c.moveToNext()) {
    		r = r + c.getString(0) + "\n";
    	}
		} else {
			Log.d("Tag", "null");
		}
		return r;
    }
    
    public String getUsername(String User) {
		Cursor c = db.query(TABLE_NAME, null, Username + " = ?", new String[]{User}, null, null, null);
		if(c.getCount()>0) {
    	return "exists";
		}
		return "not exists";
    }
    
    public String getPlayername(String PlayerId) {
    	String r = "";
		Cursor c = db.query(TABLE_NAME2, null, _ID + " = ?", new String[]{PlayerId}, null, null, null);
		if(c.getCount()>0) {
    	for(c.moveToFirst(); !c.isAfterLast(); c.moveToNext()) {
    		r = r + c.getString(2) + "\n";
		}
		}
		return r;
    }
    
    public String getPlayerpos(String PlayerId) {
    	String r = "";
		Cursor c = db.query(TABLE_NAME2, null, _ID + " = ?", new String[]{PlayerId}, null, null, null);
		if(c.getCount()>0) {
    	for(c.moveToFirst(); !c.isAfterLast(); c.moveToNext()) {
    		r = r + c.getString(3) + "\n";
		}
		}
		return r;
    }
    
    public String getGame(String UserId) {
    	Cursor c = db.rawQuery("SELECT * FROM " + TABLE_NAME3 + " WHERE " + Userid + " = '" + UserId + "'", null );
    	String r = "";
    	if(c.getCount()>0) {
    	for(c.moveToFirst();!c.isAfterLast();c.moveToNext()) {
    	     r = r + c.getString(c.getColumnIndex(Team1)).replace("\n", "") + " vs " + c.getString(c.getColumnIndex(Team2)).replace("\n", "") + "\n";
    	}
    	}
    	return r;
    }
    
    public long insertPlayer(String playerName,String pos, String Teami)
    {
        ContentValues newValues = new ContentValues();
        newValues.put(Playername, playerName);
        newValues.put(Playerpos,pos);
        newValues.put(Teamid,Teami);
        return db.insert(TABLE_NAME2, null, newValues);
    }
    
    public long insertWin(String team1,String team2)
    {
        ContentValues newValues = new ContentValues();
        newValues.put(TEAM1SCORE, 0);
        newValues.put(TEAM2SCORE, 0);
        newValues.put(_ID, getGameID(team1,team2));
        return db.insert(TABLE_NAME5, null, newValues);
    }

    private String getGameID(String team12, String team22) {
    	//Log.d("ingetgameid","getgame=" + getGame());
    	Cursor c = db.rawQuery("SELECT * FROM " + TABLE_NAME3 + " WHERE " + Team1 + " = '" + team12 + "' AND " + Team2 + " = '" + team22 + "'", null );
    	String res = "";
    	for(c.moveToFirst(); !c.isAfterLast(); c.moveToNext()) {
    		res = res + c.getString(0) + "\n";
    	}
    	Log.d("ingetgameid","res=" + res);
    	return res;
	}
    
    public String getGame() {
    	Cursor c = db.rawQuery("SELECT * FROM " + TABLE_NAME4, null );
    	String res = "";
    	for(c.moveToFirst(); !c.isAfterLast(); c.moveToNext()) {
    		res = res + c.getString(0) + " " + c.getString(1) + " " + c.getString(2) + " " + c.getString(3) + " " + c.getString(4) + " " + c.getString(5) + " " + c.getString(6) + " " + c.getString(7) + " " + c.getString(8) + " " + c.getString(9) + " " + c.getString(10) + " " + c.getString(11) + " "  + c.getString(12) + " " + c.getString(13) + "\n";
    	}
    	return res;
    }
    
    public String getPlayerStats(String PlayerId) {
    	Cursor c = db.rawQuery("SELECT * FROM " + TABLE_NAME4 + " WHERE _ID = '" + PlayerId + "'", null );
    	String res = "";
    	for(c.moveToFirst(); !c.isAfterLast(); c.moveToNext()) {
    		res = res + " 3PMADE" + c.getString(1) + "\n3PMISS" + c.getString(2) + "\nFTMADE" + c.getString(3) + "\nFTMISS" + c.getString(4) + "\nFGMADE" + c.getString(5) + "\nFGMISS" + c.getString(6) + "\nD_RBD" + c.getString(7) + "\nO_RBD" + c.getString(8) + "\nASSIST" + c.getString(9) + "\nFOUL" + c.getString(10) + "\nTURNOVER" + c.getString(11) + "\nBLOCK"  + c.getString(12) + "\nSTEAL" + c.getString(13) + "\n";
    	}
    	return res;
    }
    
    public String getWIN() {
    	Cursor c = db.rawQuery("SELECT * FROM " + TABLE_NAME5, null );
    	String res = "";
    	for(c.moveToFirst(); !c.isAfterLast(); c.moveToNext()) {
    		res = res + c.getString(0) + " " + c.getString(1) + " " + c.getString(2) +  "\n";
    	}
    	return res;
    }


	public String get(String UserId) {
    	Cursor c = db.rawQuery("SELECT * FROM " + TABLE_NAME1 + " WHERE " + Userid + " = '" + UserId + "'", null );
    	String res = "";
    	for(c.moveToFirst(); !c.isAfterLast(); c.moveToNext()) {
    		res = res + c.getString(0) + " " + c.getString(1) + " " + c.getString(2) + " " + c.getString(3) + "\n";
    	}
    	return res;
    }
    
    public String getUser() {
    	String[] column = new String[]{_ID, Username, Password, Email};
    	Cursor c = db.query(TABLE_NAME, column, null, null, null, null, null);
    	int i = c.getColumnIndex(_ID);
    	int j = c.getColumnIndex(Username);
    	int l = c.getColumnIndex(Password);
    	int k = c.getColumnIndex(Email);
    	String res = "";
    	for(c.moveToFirst();!c.isAfterLast();c.moveToNext()) {
    		res = res + c.getString(i) + " " + c.getString(j) + " " + c.getString(l) + " " + c.getString(k) + "\n";
    	}
    	return res;
    }
    
    public String getPlayer(String TeamId) {
    	Cursor c = db.rawQuery("SELECT * FROM " + TABLE_NAME2 + " WHERE " + Teamid + " = '" + TeamId + "'", null );
    	String res = "";
    	for(c.moveToFirst(); !c.isAfterLast(); c.moveToNext()) {
    		res = res + c.getString(0) + " " + c.getString(1) + " " + c.getString(2) + " " + c.getString(3) + "\n";
    	}
    	return res;
    }
    
    public String getPlayerID(String TeamId) {
    	Cursor c = db.rawQuery("SELECT * FROM " + TABLE_NAME2 + " WHERE " + Teamid + " = '" + TeamId + "'", null );
    	String res = "";
    	for(c.moveToFirst(); !c.isAfterLast(); c.moveToNext()) {
    		res = res + c.getString(0) + " ";
    	}
    	return res;
    }
    
    public String getPlayer1(String TeamId) {
    	Cursor c = db.rawQuery("SELECT * FROM " + TABLE_NAME2 + " WHERE " + Teamid + " = '" + TeamId + "'", null );
    	String res = "";
    	c.moveToFirst();
    	res = res + "Player name : " + c.getString(2) + "\n Player position: " + c.getString(3) + "\n";
    	return res;
    }
    
    public String getPlayer2(String TeamId) {
    	Cursor c = db.rawQuery("SELECT * FROM " + TABLE_NAME2 + " WHERE " + Teamid + " = '" + TeamId + "'", null );
    	String res = "";
    	c.moveToFirst();
    	c.moveToNext();
    	res = res + "Player name : " + c.getString(2) + "\n Player position: " + c.getString(3) + "\n";
    	return res;
    }
    
    public String getPlayer3(String TeamId) {
    	Cursor c = db.rawQuery("SELECT * FROM " + TABLE_NAME2 + " WHERE " + Teamid + " = '" + TeamId + "'", null );
    	String res = "";
    	c.moveToFirst();
    	c.moveToNext();
    	c.moveToNext();
    	res = res + "Player name : " + c.getString(2) + "\n Player position: " + c.getString(3) + "\n";
    	return res;
    }
    
    public String getPlayer4(String TeamId) {
    	Cursor c = db.rawQuery("SELECT * FROM " + TABLE_NAME2 + " WHERE " + Teamid + " = '" + TeamId + "'", null );
    	String res = "";
    	c.moveToFirst();
    	c.moveToNext();
    	c.moveToNext();
    	c.moveToNext();
    	res = res + "Player name : " + c.getString(2) + "\n Player position: " + c.getString(3) + "\n";
    	return res;
    }
    
    public String getPlayer5(String TeamId) {
    	Cursor c = db.rawQuery("SELECT * FROM " + TABLE_NAME2 + " WHERE " + Teamid + " = '" + TeamId + "'", null );
    	String res = "";
    	c.moveToFirst();
    	c.moveToNext();
    	c.moveToNext();
    	c.moveToNext();
    	c.moveToNext();
    	res = res + "Player name : " + c.getString(2) + "\n Player position: " + c.getString(3) + "\n";
    	return res;
    }

    public String getSinlgeEntry(String userName)
    {
        Cursor cursor=db.rawQuery("SELECT * FROM " + TABLE_NAME + " WHERE " + Username + " = '" + userName + "'", null );
        if(cursor.getCount()<1) // UserName Not Exist
        {
        	Log.d("maindata", "cursor==null");
            return "NOT EXIST";
        } else {
        cursor.moveToFirst();
        String password= cursor.getString(cursor.getColumnIndex(Password));
        return password;
        }
    }
    
    public long insertTeam(String TeamName,String numofplayers, String userid )
    {
        ContentValues newValues = new ContentValues();
        newValues.put(Teamname, TeamName);
        newValues.put(Userid, userid);
        newValues.put(Numofplayers,numofplayers);
        return db.insert(TABLE_NAME1, null, newValues);
    }
    
    public void updateGame1(String TeamName,String a )
    {
    	ContentValues newValues = new ContentValues();
        newValues.put(Team1, a);
        String where = Team1+"=?";
        String x[] = {TeamName};
        int k = db.update(TABLE_NAME3, newValues,where, x);
        Toast.makeText(context, String.valueOf(k), Toast.LENGTH_LONG).show();
    }
    
    public void updateGame2(String TeamName,String a )
    {
    	ContentValues newValues = new ContentValues();
        newValues.put(Team2, a);
        String where = Team2+"=?";
        String x[] = {TeamName};
        int k = db.update(TABLE_NAME3, newValues,where, x);
        Toast.makeText(context, String.valueOf(k), Toast.LENGTH_LONG).show();
    }
    
    public void updateTeam(String TeamName,String a )
    {
    	ContentValues newValues = new ContentValues();
        newValues.put(Teamname, a);
        String where = Teamname+"=?";
        String x[] = {TeamName};
        int k = db.update(TABLE_NAME1, newValues,where, x);
        Toast.makeText(context, String.valueOf(k), Toast.LENGTH_LONG).show();
        updateGame1(TeamName,a);
        updateGame2(TeamName,a);
    }
    
    public void updateGame(String DaTe,String a, String TiMe, String b, String loc, String c)
    {
    	ContentValues newValues = new ContentValues();
        newValues.put(Date, a);
        newValues.put(Time, a);
        newValues.put(Location, a);
        String where = Date+"=? AND " + Time +"=? AND " + Location + "=?";
        String x[] = {DaTe,TiMe,loc};
        db.update(TABLE_NAME3, newValues,where, x);
    }
    
    public String get2pmade(String Playerid) {
    	Cursor c = db.rawQuery("SELECT * FROM " + TABLE_NAME4 + " WHERE _ID = '" + Playerid + "'", null );
    	String res = "";
    	if(c.moveToFirst()) {
    		res = res + c.getString(1);
    	}
    	return res;
    }
    
    public String get2pmiss(String Playerid) {
    	Cursor c = db.rawQuery("SELECT * FROM " + TABLE_NAME4 + " WHERE _ID = '" + Playerid + "'", null );
    	String res = "";
    	if(c.moveToFirst()) {
    		res = res + c.getString(2);
    	}
    	return res;
    }
    
    public String getftmade(String Playerid) {
    	Cursor c = db.rawQuery("SELECT * FROM " + TABLE_NAME4 + " WHERE _ID = '" + Playerid + "'", null );
    	String res = "";
    	if(c.moveToFirst()) {
    		res = res + c.getString(3);
    	}
    	return res;
    }
    
    public String getftmiss(String Playerid) {
    	Cursor c = db.rawQuery("SELECT * FROM " + TABLE_NAME4 + " WHERE _ID = '" + Playerid + "'", null );
    	String res = "";
    	if(c.moveToFirst()) {
    		res = res + c.getString(4);
    	}
    	return res;
    }
    
    public String getfgmade(String Playerid) {
    	Cursor c = db.rawQuery("SELECT * FROM " + TABLE_NAME4 + " WHERE _ID = '" + Playerid + "'", null );
    	String res = "";
    	if(c.moveToFirst()) {
    		res = res + c.getString(5);
    	}
    	return res;
    }
    
    public String getfgmiss(String Playerid) {
    	Cursor c = db.rawQuery("SELECT * FROM " + TABLE_NAME4 + " WHERE _ID = '" + Playerid + "'", null );
    	String res = "";
    	if(c.moveToFirst()) {
    		res = res + c.getString(6);
    	}
    	return res;
    }
    
    public String getdrbd(String Playerid) {
    	Cursor c = db.rawQuery("SELECT * FROM " + TABLE_NAME4 + " WHERE _ID = '" + Playerid + "'", null );
    	String res = "";
    	if(c.moveToFirst()) {
    		res = res + c.getString(7);
    	}
    	return res;
    }
    
    public String getorbd(String Playerid) {
    	Cursor c = db.rawQuery("SELECT * FROM " + TABLE_NAME4 + " WHERE _ID = '" + Playerid + "'", null );
    	String res = "";
    	if(c.moveToFirst()) {
    		res = res + c.getString(8);
    	}
    	return res;
    }
    
    public String getassist(String Playerid) {
    	Cursor c = db.rawQuery("SELECT * FROM " + TABLE_NAME4 + " WHERE _ID = '" + Playerid + "'", null );
    	String res = "";
    	if(c.moveToFirst()) {
    		res = res + c.getString(9);
    	}
    	return res;
    }
    
    public String getfoul(String Playerid) {
    	Cursor c = db.rawQuery("SELECT * FROM " + TABLE_NAME4 + " WHERE _ID = '" + Playerid + "'", null );
    	String res = "";
    	if(c.moveToFirst()) {
    		res = res + c.getString(10);
    	}
    	return res;
    }
    
    public String getturnover(String Playerid) {
    	Cursor c = db.rawQuery("SELECT * FROM " + TABLE_NAME4 + " WHERE _ID = '" + Playerid + "'", null );
    	String res = "";
    	if(c.moveToFirst()) {
    		res = res + c.getString(11);
    	}
    	return res;
    }
    
    public String getblock(String Playerid) {
    	Cursor c = db.rawQuery("SELECT * FROM " + TABLE_NAME4 + " WHERE _ID = '" + Playerid + "'", null );
    	String res = "";
    	if(c.moveToFirst()) {
    		res = res + c.getString(12);
    	}
    	return res;
    }
    
    public String getsteal(String Playerid) {
    	Cursor c = db.rawQuery("SELECT * FROM " + TABLE_NAME4 + " WHERE _ID = '" + Playerid + "'", null );
    	String res = "";
    	if(c.moveToFirst()) {
    		res = res + c.getString(13);
    	}
    	return res;
    }
    
    public void update2pmade(String Playerid)
    {
    	ContentValues newValues = new ContentValues();
        newValues.put(THREEPMADE, String.valueOf((Integer.valueOf(get2pmade(Playerid))+1)));
        String where = _ID+"=?";
        String x[] = {Playerid};
        int k = db.update(TABLE_NAME4, newValues,where, x);
        Toast.makeText(context, String.valueOf(k), Toast.LENGTH_LONG).show();
    }
    
    public void update2pmiss(String Playerid)
    {
    	ContentValues newValues = new ContentValues();
        newValues.put(THREEPMISS, String.valueOf((Integer.valueOf(get2pmiss(Playerid))+1)));
        String where = _ID+"=?";
        String x[] = {Playerid};
        int k = db.update(TABLE_NAME4, newValues,where, x);
        Toast.makeText(context, String.valueOf(k), Toast.LENGTH_LONG).show();
    }
    
    public void updateftmade(String Playerid)
    {
    	ContentValues newValues = new ContentValues();
        newValues.put(FTMADE, String.valueOf((Integer.valueOf(getftmade(Playerid))+1)));
        String where = _ID+"=?";
        String x[] = {Playerid};
        int k = db.update(TABLE_NAME4, newValues,where, x);
        Toast.makeText(context, String.valueOf(k), Toast.LENGTH_LONG).show();
    }
    
    public void updateftmiss(String Playerid)
    {
    	ContentValues newValues = new ContentValues();
        newValues.put(FTMISS, String.valueOf((Integer.valueOf(getftmiss(Playerid))+1)));
        String where = _ID+"=?";
        String x[] = {Playerid};
        int k = db.update(TABLE_NAME4, newValues,where, x);
        Toast.makeText(context, String.valueOf(k), Toast.LENGTH_LONG).show();
    }
    
    public void updatefgmade(String Playerid)
    {
    	ContentValues newValues = new ContentValues();
        newValues.put(FGMADE, String.valueOf((Integer.valueOf(getfgmade(Playerid))+1)));
        String where = _ID+"=?";
        String x[] = {Playerid};
        int k = db.update(TABLE_NAME4, newValues,where, x);
        Toast.makeText(context, String.valueOf(k), Toast.LENGTH_LONG).show();
    }
    
    public void updatefgmiss(String Playerid)
    {
    	ContentValues newValues = new ContentValues();
        newValues.put(FGMISS, String.valueOf((Integer.valueOf(getfgmiss(Playerid))+1)));
        String where = _ID+"=?";
        String x[] = {Playerid};
        int k = db.update(TABLE_NAME4, newValues,where, x);
        Toast.makeText(context, String.valueOf(k), Toast.LENGTH_LONG).show();
    }
    
    public void updatedbrd(String Playerid)
    {
    	ContentValues newValues = new ContentValues();
        newValues.put(D_RBD, String.valueOf((Integer.valueOf(getdrbd(Playerid))+1)));
        String where = _ID+"=?";
        String x[] = {Playerid};
        int k = db.update(TABLE_NAME4, newValues,where, x);
        Toast.makeText(context, String.valueOf(k), Toast.LENGTH_LONG).show();
    }
    
    public void updateassist(String Playerid)
    {
    	ContentValues newValues = new ContentValues();
        newValues.put(ASSIST, String.valueOf((Integer.valueOf(getassist(Playerid))+1)));
        String where = _ID+"=?";
        String x[] = {Playerid};
        int k = db.update(TABLE_NAME4, newValues,where, x);
        Toast.makeText(context, String.valueOf(k), Toast.LENGTH_LONG).show();
    }
    
    public void updateobrd(String Playerid)
    {
    	ContentValues newValues = new ContentValues();
        newValues.put(O_RBD, String.valueOf((Integer.valueOf(getorbd(Playerid))+1)));
        String where = _ID+"=?";
        String x[] = {Playerid};
        int k = db.update(TABLE_NAME4, newValues,where, x);
        Toast.makeText(context, String.valueOf(k), Toast.LENGTH_LONG).show();
    }
    
    public void updateturnover(String Playerid)
    {
    	ContentValues newValues = new ContentValues();
        newValues.put(TURNOVER, String.valueOf((Integer.valueOf(getturnover(Playerid))+1)));
        String where = _ID+"=?";
        String x[] = {Playerid};
        int k = db.update(TABLE_NAME4, newValues,where, x);
        Toast.makeText(context, String.valueOf(k), Toast.LENGTH_LONG).show();
    }
    
    public void updatefoul(String Playerid)
    {
    	ContentValues newValues = new ContentValues();
        newValues.put(TURNOVER, String.valueOf((Integer.valueOf(getfoul(Playerid))+1)));
        String where = _ID+"=?";
        String x[] = {Playerid};
        int k = db.update(TABLE_NAME4, newValues,where, x);
        Toast.makeText(context, String.valueOf(k), Toast.LENGTH_LONG).show();
    }
    
    public void updateblock(String Playerid)
    {
    	ContentValues newValues = new ContentValues();
        newValues.put(BLOCK, String.valueOf((Integer.valueOf(getblock(Playerid))+1)));
        String where = _ID+"=?";
        String x[] = {Playerid};
        int k = db.update(TABLE_NAME4, newValues,where, x);
        Toast.makeText(context, String.valueOf(k), Toast.LENGTH_LONG).show();
    }
    
    public void updatesteal(String Playerid)
    {
    	ContentValues newValues = new ContentValues();
        newValues.put(STEAL, String.valueOf((Integer.valueOf(getsteal(Playerid))+1)));
        String where = _ID+"=?";
        String x[] = {Playerid};
        int k = db.update(TABLE_NAME4, newValues,where, x);
        Toast.makeText(context, String.valueOf(k), Toast.LENGTH_LONG).show();
    }
    
    public Integer getScore(String Teamid) {
    	String k = getPlayerID(Teamid);
    	String[] Player = k.split(" ");
    	int i;
    	int res=0;
    	for(i=0;i<5;i++) {
    		Cursor c = db.rawQuery("SELECT * FROM " + TABLE_NAME4 + " WHERE _ID = '" + Player[i] + "'", null );
        	if(c.moveToFirst()) {
        		res = res + Integer.valueOf(c.getString(1)) + Integer.valueOf(c.getString(5));
        	}
    	}
    	Toast.makeText(context, "" + res, Toast.LENGTH_LONG).show();
    	return res;
    }

	public List<String> getteam(String UserId) {
		List<String> list = new ArrayList<String>();
		Cursor c = db.rawQuery("SELECT * FROM " + TABLE_NAME1 + " WHERE " + Userid + " = '" + UserId + "'", null );
    	for(c.moveToFirst(); !c.isAfterLast(); c.moveToNext()) {
    		list.add(c.getString(2) + "\n");
    	}
		return list;
	}
	
	public List<String> getteamlist() {
		List<String> list = new ArrayList<String>();
		String[] column = new String[]{_ID, Teamname, Numofplayers};
    	Cursor c = db.query(TABLE_NAME1, column, null, null, null, null, null);
    	int j = c.getColumnIndex(Teamname);
    	for(c.moveToFirst();!c.isAfterLast();c.moveToNext()) {
    		list.add(c.getString(j) + "\n");
    	}
		return list;
	}
	
	public String getTeam() {
		String r = "";
		String[] column = new String[]{_ID, Teamname, Numofplayers};
    	Cursor c = db.query(TABLE_NAME1, column, null, null, null, null, null);
    	int j = c.getColumnIndex(Teamname);
    	for(c.moveToFirst();!c.isAfterLast();c.moveToNext()) {
    		r = r + c.getString(j) + " " + c.getString(c.getColumnIndex(_ID))+ "\n";
    	}
		return r;
	}
	
	public String getTeamId(String TeamName) {
		String r = "";
		Cursor c = db.query(TABLE_NAME1, null, Teamname + " = ?", new String[]{TeamName}, null, null, null);
		if(c.getCount()>0) {
			Log.d("Tag", TeamName + "not null");
    	for(c.moveToFirst(); !c.isAfterLast(); c.moveToNext()) {
    		r = r + c.getString(0) + " ";
    	}
		} else {
			Log.d("Tag", TeamName + "null");
		}
		return r;
	}
	
	public String getUserId(String UserName) {
		Cursor cursor=db.rawQuery("SELECT * FROM " + TABLE_NAME + " WHERE " + Username + " = '" + UserName + "'", null );
        if(cursor==null) // UserName Not Exist
        {
            return "NOT EXIST";
        } else {
        cursor.moveToFirst();
        String id = cursor.getString(0);
        return id;
        }
	}
	
	public void deleteGame(String TeamName) throws SQLException {
		db.delete(TABLE_NAME3,"Team1name = '" + TeamName + "'",null);
		db.delete(TABLE_NAME3,"Team2name = '" + TeamName + "'",null);
	}
	
	public void deleteTeam(String TeamName) throws SQLException {
		db.delete(TABLE_NAME1,"Teamname = '" + TeamName + "'",null);
		deleteGame(TeamName);
	}
}
