package com.tsc.adapter;
import java.util.List;

import android.content.Context;
import android.graphics.Color;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.tsc.entity.MenuItem;

public class TabListAdapter extends ArrayAdapter<MenuItem> {

	private Context mContext;
	private List<MenuItem> mListData; 
	private String[] mArrayData;
	private int selectedPos = 0; 
	private String selectedText;
	private int normalResId;
	private int selectedResId;
	private OnClickListener onClickListener;
	private OnItemClickListener mOnItemClickListener;

	public TabListAdapter(Context context, List<MenuItem> listData,int selectedResId,int normalResId) {
		super(context, -1, listData);
		mContext = context;
		mListData = listData;
		this.normalResId = normalResId;
		this.selectedResId = selectedResId;
		init();
	}

	private void init() {
		onClickListener = new OnClickListener() {

			@Override
			public void onClick(View view) {
				selectedPos = (Integer) view.getTag();
				setSelectedPosition(selectedPos);
				if (mOnItemClickListener != null) {
					mOnItemClickListener.onItemClick(view, selectedPos);
				}
			}
		};
	}


	/**
	 * 设置选中的position,并�?�知列表刷新
	 */
	public void setSelectedPosition(int pos) {
		if (mListData != null && pos < mListData.size()) {
			selectedPos = pos;
			selectedText = mListData.get(pos).getText();
			notifyDataSetChanged();
		} else if (mArrayData != null && pos < mArrayData.length) {
			selectedPos = pos;
			selectedText = mArrayData[pos];
			notifyDataSetChanged();
		}

	}

	/**
	 * 设置选中的position,但不通知刷新
	 */
	public void setSelectedPositionNoNotify(int pos) {
		selectedPos = pos;
		if (mListData != null && pos < mListData.size()) {
			selectedText = mListData.get(pos).getText();
		} else if (mArrayData != null && pos < mArrayData.length) {
			selectedText = mArrayData[pos];
		}
	}

	/**
	 * 获取选中的position
	 */
	public int getSelectedPosition() {
		if (mArrayData != null && selectedPos < mArrayData.length) {
			return selectedPos;
		}
		if (mListData != null && selectedPos < mListData.size()) {
			return selectedPos;
		}

		return -1;
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		TextView view;
		if (convertView == null) {
			view = new TextView(mContext);
			view.setTextColor(Color.BLACK);
			view.setTextSize(13);
		} else {
			view = (TextView) convertView;
		}
		view.setTag(position);
		view.setBackgroundResource(normalResId);
		String mString = "";
		if (mListData != null) {
			if (position < mListData.size()) {
				mString = mListData.get(position).getText();
			}
		} else if (mArrayData != null) {
			if (position < mArrayData.length) {
				mString = mArrayData[position];
			}
		}
		
		view.setText(mString);
		
		if (selectedText != null && selectedText.equals(mString)) {
			//设置选中的背景图�?
			
			view.setBackgroundResource(selectedResId);
		} else {
			//设置未�?�中状�?�背景图�?
			view.setBackgroundResource(normalResId);
		}
		view.setPadding(20, 25, 20, 25);
		view.setOnClickListener(onClickListener);
		return view;
	}

	public void setOnItemClickListener(OnItemClickListener l) {
		mOnItemClickListener = l;
	}

	/**
	 * 重新定义菜单选项单击接口
	 */
	public interface OnItemClickListener {
		public void onItemClick(View view, int position);
	}
}