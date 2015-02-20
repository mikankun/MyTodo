package lighthousew5.com.mytodo;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by wataru on 15/02/08.
 */
public class DatabaseHelper extends SQLiteOpenHelper {


    public DatabaseHelper(Context context) {
        super(context, "todo.db", null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String SQL = "create table todolist(" +
                "_id integer primary key autoincrement,"+
                "title text,"+
                "due_date text,"+
                "memo text,"+
                "status text"+
                ");";

        db.execSQL(SQL);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }

    //insert
    public void insert(TodoList todo){
        ContentValues values = new ContentValues();
        values.put("title",todo.title);
        values.put("due_date",todo.dueDate);
        values.put("memo",todo.memo);
        values.put("status",todo.status);

        SQLiteDatabase db = getWritableDatabase();
        db.insert("todolist",null,values);

    }

    //get the todolist
    public List<TodoList> select(){
        SQLiteDatabase db = getReadableDatabase();
        String[] columns = { "_id","title","due_date","memo","status"};
        Cursor cur = db.query("todolist",columns,null,null,null,null,null);

        List<TodoList> list = new ArrayList<>();

        while( cur.moveToNext()){
            TodoList todo = new TodoList();
            todo.id = cur.getLong(cur.getColumnIndex("_id"));
            todo.title = cur.getString(cur.getColumnIndex("title"));
            todo.dueDate = cur.getString(cur.getColumnIndex("due_date"));
            todo.memo = cur.getString(cur.getColumnIndex("memo"));
            todo.status = cur.getString(cur.getColumnIndex("status"));
            list.add( todo );

        }
        cur.close();
        return list;
    }

    public void update( TodoList todo){
        ContentValues values = new ContentValues();
        values.put("status",todo.status);

        SQLiteDatabase db = getWritableDatabase();

        String[] whereArgs = {String.valueOf(todo.id)};
        db.update("todolist",values,"_id = ?", whereArgs);

    }

    public void delete( long id){
        SQLiteDatabase db = getWritableDatabase();

        String[] whereArgs = {String.valueOf(id)};
        db.delete("todolist","_id = ?", whereArgs);
    }

}
