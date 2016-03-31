package cn.kairun.kairunsport.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;
import java.util.Map;

import cn.kairun.kairunsport.R;

/**
 * Created by ZengRong on 2016/3/31.
 */
public class ListViewMenuAdapter extends BaseAdapter {

    private Context context;
    private List<Map<String, Object>> listItems;
    private LayoutInflater listContainer;

    public ListViewMenuAdapter(Context context, List<Map<String, Object>> listItems) {
        this.context = context;
        this.listItems = listItems;
        this.listContainer = LayoutInflater.from(context);
    }

    public final class ListItemView{ //自定义控件集合
        public ImageView imageTitle;
        public TextView title;
        public ImageView smallArrow;
    }

    @Override
    public int getCount() {
        return listItems.size();
    }

    @Override
    public Object getItem(int position) {
        return listItems.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ListItemView  listItemView = null;
        if (convertView == null) {
            listItemView = new ListItemView();
            convertView = listContainer.inflate(R.layout.rightmenu_item_layout, null);
            listItemView.imageTitle = (ImageView)convertView.findViewById(R.id.iv_title);
            listItemView.title = (TextView)convertView.findViewById(R.id.tv_title);
            listItemView.smallArrow = (ImageView)convertView.findViewById(R.id.iv_smallarrow);
            convertView.setTag(listItemView);
        }else{
            listItemView = (ListItemView)convertView.getTag();
        }
        listItemView.imageTitle.setBackgroundResource((int) listItems.get(position).get("imagetitle"));
        listItemView.title.setText(listItems.get(position).get("title").toString());
        listItemView.smallArrow.setBackgroundResource((int) listItems.get(position).get("smallarrow"));
        return convertView;
    }
}
