package w.nicky.customizedview;

import android.app.Activity;
import android.content.res.TypedArray;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class MainActivity extends Activity
{
    private static final String ITEM_TITLE = "Item title";
    private static final String ITEM_ICON = "Item icon";
    private TextView mTxtR;
    private ListView lsv_main;
    private ListAdapter mListAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mTxtR = (TextView) findViewById(R.id.txtR);

        lsv_main = (ListView)findViewById(R.id.lsv_main);
        lsv_main.setOnItemClickListener(listViewOnItemClickListener);

        //定義 ListView 每個 Item 的資料
        List<Map<String, Object>> itemList = new ArrayList<Map<String, Object>>();

        String[] regionList = {"1", "2", "3", "4"};
        TypedArray regionIconList = getResources().obtainTypedArray(R.array.region_icon_list);

        for (int i = 0; i < regionList.length; i++)
        {
            Map<String, Object> item = new HashMap<String, Object>();
            item.put(ITEM_TITLE, regionList[i]);
            item.put(ITEM_ICON, regionIconList.getResourceId(i, 0));
            itemList.add(item);
        }

        // ListView 中所需之資料參數可透過修改 Adapter 的建構子傳入
        mListAdapter = new ListAdapter(MainActivity.this, itemList);

 //設定 ListView 的 Adapter
        lsv_main.setAdapter(mListAdapter);
    }

 //設定 Item 的 OnClick 事件
    private AdapterView.OnItemClickListener listViewOnItemClickListener
            = new AdapterView.OnItemClickListener()
    {
        @Override
        public void onItemClick(AdapterView<?> parent, View view, int position, long id)
        {
            TextView txtItemTitle = (TextView) view.findViewById(R.id.txtView);
            mTxtR.setText(txtItemTitle.getText());
        }
    };
}