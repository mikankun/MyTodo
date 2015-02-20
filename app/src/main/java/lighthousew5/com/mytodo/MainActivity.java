package lighthousew5.com.mytodo;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.provider.ContactsContract;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import java.util.List;


public class MainActivity extends ActionBarActivity {

    DatabaseHelper helper;
    TodoListAdapter adapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        helper = new DatabaseHelper(this);
        List<TodoList> list = helper.select();

        ListView  listView = (ListView) findViewById(R.id.listView);
        adapter = new TodoListAdapter(this,list);
        listView.setAdapter(adapter);

        listView.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {


                final TodoList todo = (TodoList)parent.getItemAtPosition(position);

                new AlertDialog.Builder(MainActivity.this)
                        .setTitle("確認")
                        .setMessage(todo.title  + "を完了にしますか？")
                        .setPositiveButton("OK",new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {

                                todo.status = getResources().getString(R.string.status_complete);
                                helper.update( todo );
                            }
                        })
                        .setNegativeButton("NO",null)
                        .create()
                        .show();


                Toast.makeText(MainActivity.this, "変更しました",Toast.LENGTH_LONG).show();


                return false;
            }
        });
    }

    private void refresh(){
        List<TodoList> list = helper.select();
        adapter.clear();
        adapter.addAll(list);
        adapter.notifyDataSetChanged();

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
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
        if( id == R.id.newtoto){
            Intent intent = new Intent(this,EntryTodo.class);
            startActivity(intent);

        }
        if( id == R.id.refresh){
            refresh();
        }
        return super.onOptionsItemSelected(item);
    }
}
