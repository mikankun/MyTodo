package lighthousew5.com.mytodo;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

/**
 * Created by wataru on 15/02/08.
 */
public class TodoListAdapter extends ArrayAdapter<TodoList> {

    public TodoListAdapter(Context context, List<TodoList> objects) {
        super(context, 0,objects);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        if(convertView == null){
            LayoutInflater layout
                    = (LayoutInflater) getContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);

            convertView = layout.inflate(R.layout.list,null);
        }
        TodoList bean = getItem(position);

        TextView title = (TextView) convertView.findViewById(R.id.textView4);
        TextView dueDate = (TextView) convertView.findViewById(R.id.textView5);
        ImageView checked = (ImageView) convertView.findViewById(R.id.imageView);

        title.setText(bean.title);
        dueDate.setText(bean.dueDate);
        if( bean.status.equals(getContext().getResources().getString(R.string.status_complete))){
            checked.setImageResource(android.R.drawable.checkbox_on_background);
        }else{
            checked.setImageResource(android.R.drawable.checkbox_off_background);
        }

        return convertView;
    }
}
