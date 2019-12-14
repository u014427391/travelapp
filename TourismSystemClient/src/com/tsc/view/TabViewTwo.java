package com.tsc.view;

import java.util.ArrayList;
import java.util.List;

import android.content.Context;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.util.AttributeSet;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.ListView;

import com.tsc.activities.R;
import com.tsc.adapter.TabListAdapter;
import com.tsc.entity.MenuItem;

public class TabViewTwo extends LinearLayout {

	private ListView listView1;
	private ListView listView2;

	private TabListAdapter listViewAdapter1;
	private TabListAdapter listViewAdapter2;

	private List<MenuItem> groups;
	private List<ArrayList<MenuItem>> childrens;

	/** 当前显示的分�?2 */
	private List<MenuItem> childrenItem = new ArrayList<MenuItem>();

	private OnItemSelectListener mOnItemSelectListener;
	private int selectGroupResId, selectorGroupResId;
	private int selectChildrenResId, selectorChildrenResId;

	private int defaultGroupId = -1;
	private int defaultChildrenId = -1;

	private int defaultGroupPosition = 0;
	private int defaultChildrenPosition = 0;

	private String showString;

	/**
	 * 构造
	 * 
	 * @param context
	 *            上下文
	 * @param groups
	 *            第一个listView要显示的数据
	 * @param childrens
	 *            第二个listView要显示的数据
	 * @param defaultGroupId
	 *            //第一个listView默认选中条目ID 此ID不是Position 而是实体类中getID
	 * @param defaultChildrenId
	 *            第二个listView默认选中条目ID 此ID不是Position 而是实体类中getID
	 * @param selectGroupResId
	 *            第一个listView选中的背景色
	 * @param selectorGroupResId
	 *            第一个listView未选中的背景色
	 * @param selectChildrenResId
	 *            第二个listView选中的背景色
	 * @param selectorChildrenResId
	 *            第二个listView未选中的背景色
	 */
	public TabViewTwo(Context context, List<MenuItem> groups,
			List<ArrayList<MenuItem>> childrens, int defaultGroupId,
			int defaultChildrenId, int selectGroupResId,
			int selectorGroupResId, int selectChildrenResId,
			int selectorChildrenResId) {
		super(context);
		this.groups = groups;
		this.childrens = childrens;
		this.defaultGroupId = defaultGroupId;
		this.defaultChildrenId = defaultChildrenId;
		this.selectGroupResId = selectGroupResId;
		this.selectorGroupResId = selectorGroupResId;
		this.selectChildrenResId = selectChildrenResId;
		this.selectorChildrenResId = selectorChildrenResId;
		init(context);
	}

	public TabViewTwo(Context context, AttributeSet attrs) {
		super(context, attrs);
		init(context);
	}

	public void updateShowText(int groupId, int childId) {
		if (groupId == -1 || childId == -1) {
			return;
		}
		for (int i = 0; i < groups.size(); i++) {
			if (groups.get(i).getId() == groupId) {
				listViewAdapter1.setSelectedPosition(i);
				childrenItem.clear();
				if (i < childrens.size()) {
					childrenItem.addAll(childrens.get(i));
				}
				defaultGroupPosition = i;
				break;
			}
		}
		for (int j = 0; j < childrenItem.size(); j++) {
			if (childrenItem.get(j).getId() == childId) {
				listViewAdapter2.setSelectedPosition(j);
				defaultChildrenPosition = j;
				break;
			}
		}

		if (defaultChildrenPosition < childrenItem.size()) {
			showString = childrenItem.get(defaultChildrenPosition).getText();
		}

		setDefaultSelect();
	}

	private void init(Context context) {
		this.setOrientation(LinearLayout.HORIZONTAL);
		listView1 = new ListView(context);
		listView1.setCacheColorHint(Color.parseColor("#00000000"));
		listView1.setDivider(new ColorDrawable(Color.parseColor("#D3D3D3")));
		listView1.setDividerHeight(1);
		listView1.setHorizontalScrollBarEnabled(false);
		listView1.setVerticalScrollBarEnabled(false);
		listView1.setBackgroundResource(R.color.white);
		this.addView(listView1, new LayoutParams(0, android.view.ViewGroup.LayoutParams.MATCH_PARENT,
				1));

		/*
		 * View line = new View(context);
		 * line.setBackgroundColor(Color.parseColor("#ebebeb"));
		 * this.addView(line,new LayoutParams(1,LayoutParams.MATCH_PARENT));
		 */

		listView2 = new ListView(context);
		listView2.setCacheColorHint(Color.parseColor("#00000000"));
		listView2.setDivider(new ColorDrawable(Color.parseColor("#D3D3D3")));
		listView2.setDividerHeight(1);
		listView2.setHorizontalScrollBarEnabled(false);
		listView2.setVerticalScrollBarEnabled(false);
		listView2.setBackgroundResource(R.color.choose_eara_item_press_color);
		this.addView(listView2, new LayoutParams(0, android.view.ViewGroup.LayoutParams.MATCH_PARENT,
				1));

		listViewAdapter1 = new TabListAdapter(context, groups,
				selectGroupResId, selectorGroupResId);

		if (defaultGroupId != -1) {
			for (int i = 0; i < groups.size(); i++) {
				if (groups.get(i).getId() == defaultGroupId) {
					listViewAdapter1.setSelectedPositionNoNotify(i);
					break;
				}
			}
		}
		listViewAdapter1.setSelectedPositionNoNotify(defaultGroupPosition);
		listView1.setAdapter(listViewAdapter1);
		listViewAdapter1
				.setOnItemClickListener(new TabListAdapter.OnItemClickListener() {
					@Override
					public void onItemClick(View view, int position) {
						if (position < groups.size()) {
							childrenItem.clear();
							if (childrens.size() > position) {
								childrenItem.addAll(childrens.get(position));
							}
							listViewAdapter2.notifyDataSetChanged();
						}
					}
				});

		if (defaultChildrenId != -1) {
			if (defaultGroupId == -1) {

			} else {
				for (int i = 0; i < childrens.get(defaultGroupPosition).size(); i++) {
					if (childrens.get(i).get(defaultGroupPosition).getId() == defaultChildrenId) {
						defaultChildrenPosition = i;
						listViewAdapter2
								.setSelectedPositionNoNotify(defaultChildrenPosition);
						break;
					}
				}
			}
		}

		if (defaultChildrenPosition < childrens.size()) {
			childrenItem.addAll(childrens.get(defaultChildrenPosition));
		}
		listViewAdapter2 = new TabListAdapter(context, childrenItem,
				selectChildrenResId, selectorChildrenResId);

		listView2.setAdapter(listViewAdapter2);
		listViewAdapter2
				.setOnItemClickListener(new TabListAdapter.OnItemClickListener() {

					@Override
					public void onItemClick(View view, final int position) {
						showString = childrenItem.get(position).getText();
						if (mOnItemSelectListener != null) {
							mOnItemSelectListener.itemSelected(position);
						}

					}
				});

		if (defaultChildrenPosition < childrenItem.size()) {
			showString = childrenItem.get(defaultChildrenPosition).getText();
		}

		setDefaultSelect();

	}

	public void setDefaultSelect() {
		listView1.setSelection(defaultGroupPosition);
		listView2.setSelection(defaultChildrenPosition);
	}

	public String getShowText() {
		return showString;
	}

	public void setOnItemSelectListener(
			OnItemSelectListener onItemSelectListener) {
		mOnItemSelectListener = onItemSelectListener;
	}

	public interface OnItemSelectListener {
		public void itemSelected(int position);
	}

	public void notifyDataSetChangedGroup() {
		listViewAdapter1.notifyDataSetChanged();
	}

	public void notifyDataSetChangedChildren() {
		listViewAdapter2.notifyDataSetChanged();
	}

}
