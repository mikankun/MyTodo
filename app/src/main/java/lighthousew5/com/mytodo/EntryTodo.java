package lighthousew5.com.mytodo;

import android.app.DatePickerDialog;
import android.content.Context;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.CheckBox;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Toast;


public class EntryTodo extends ActionBarActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_entry_todo);


    }

    public void insert(View v){
        EditText edTitle = (EditText) findViewById(R.id.etTitle);
        EditText edDueDate = (EditText) findViewById(R.id.etDueDate);
        EditText edMemo = (EditText) findViewById(R.id.etMemo);
        CheckBox check = (CheckBox) findViewById(R.id.status);

        TodoList todo = new TodoList();
        todo.title = edTitle.getText().toString();
        todo.dueDate = edDueDate.getText().toString();
        todo.memo = edMemo.getText().toString();
        if( check.isChecked()){
            todo.status = getResources().getString(R.string.status_complete);
        }else{
            todo.status = getResources().getString(R.string.status_imcomplete);
        }



        DatabaseHelper helper = new DatabaseHelper(this);
        helper.insert(todo);

        Toast.makeText(this,"登録完了しました",Toast.LENGTH_LONG).show();

    }



    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_entry_todo, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if(id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
