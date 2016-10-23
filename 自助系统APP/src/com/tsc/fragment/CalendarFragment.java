package com.tsc.fragment;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

import android.app.DatePickerDialog.OnDateSetListener;
import android.app.DialogFragment;
import android.graphics.Color;
import android.graphics.Typeface;
import android.os.Bundle;
import android.util.Log;
import android.view.GestureDetector;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.View.OnTouchListener;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.GridView;
import android.widget.TextView;

import com.tsc.activities.R;
import com.tsc.adapter.CalendarAdapter;

public class CalendarFragment extends DialogFragment implements
OnDateSetListener {
	private GestureDetector gestureDetector = null;
	private CalendarAdapter calV = null;
	private GridView gridView = null;
	private TextView topText = null;
	private int jumpMonth = 0; // 每次滑动，增加或减去一个月,默认为0（即显示当前月）
	private int jumpYear = 0; // 滑动跨越一年，则增加或者减去一年,默认为0(即当前年)
	private int year_c = 0;
	private int month_c = 0;
	private int day_c = 0;

	private Date currentDate;

	private TextView nextMonth; // 下一月文本框
	private TextView preMonth; // 上一月文本框
	private Button cancelBtn; // 取消按钮
	//private Calendar calendar_c; // 当前日历

	public CalendarFragment(Date date) {
		super();
		currentDate = date;
//		calendar_c = Calendar.getInstance();
//		calendar_c.setTime(date);
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-M-d", Locale.CHINA);
		String currentDateString = sdf.format(date); // 当期日期
		year_c = Integer.parseInt(currentDateString.split("-")[0]);
		month_c = Integer.parseInt(currentDateString.split("-")[1]);
		day_c = Integer.parseInt(currentDateString.split("-")[2]);
		// year_c=calendar_c.get(Calendar.YEAR);
		// month_c=calendar_c.get(Calendar.MONTH)+1;
		// day_c=calendar_c.get(Calendar.DAY_OF_MONTH);
	}

	public CalendarFragment() {
		// TODO Auto-generated constructor stub
	}

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		final View calendarV = inflater.inflate(R.layout.fragment_calendar, container,
				false);
		gestureDetector = new GestureDetector(this.getActivity(),
				new MyGestureListener());
		calV = new CalendarAdapter(this.getActivity(), getResources(),
				currentDate, jumpMonth, jumpYear, year_c, month_c, day_c);
		addGridView(calendarV);
		gridView.setAdapter(calV);

		topText = (TextView) calendarV.findViewById(R.id.tv_month);
		addTextToTopTextView(topText);
		nextMonth = (TextView) calendarV.findViewById(R.id.right_img);
		preMonth = (TextView) calendarV.findViewById(R.id.left_img);
		nextMonth.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				Log.i("Hwd", "kjkkjk");
				addGridView(calendarV); // 添加一个gridView
				jumpMonth++; // 下一个月

				calV = new CalendarAdapter(CalendarFragment.this.getActivity(),
						getResources(), currentDate, jumpMonth, jumpYear,
						year_c, month_c, day_c);
				gridView.setAdapter(calV);
				addTextToTopTextView(topText);
			}
		});

		preMonth.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				addGridView(calendarV); // 添加一个gridView
				jumpMonth--; // 上一个月

				calV = new CalendarAdapter(CalendarFragment.this.getActivity(),
						getResources(), currentDate, jumpMonth, jumpYear,
						year_c, month_c, day_c);
				gridView.setAdapter(calV);
				addTextToTopTextView(topText);
			}
		});
		cancelBtn = (Button) calendarV.findViewById(R.id.cancelBtn);
		cancelBtn.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				CalendarFragment.this.dismiss();
			}
		});
		return calendarV;
	}

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		// 如果setCancelable()中参数为true，若点击dialog覆盖不到的activity的空白或者按返回键，则进行cancel，状态检测依次onCancel()和onDismiss()。如参数为false，则按空白处或返回键无反应。缺省为true
		setCancelable(false);
		// 可以设置dialog的显示风格，如style为STYLE_NO_TITLE，将被显示title。theme为0，表示由系统选择合适的theme。
		int style = DialogFragment.STYLE_NO_TITLE, theme = 0;
		setStyle(style, theme);
	}

	// 添加头部的年份 闰哪月等信息
	public void addTextToTopTextView(TextView view) {
		StringBuffer textDate = new StringBuffer();
		textDate.append(calV.getShowYear()).append("年")
				.append(calV.getShowMonth()).append("月").append("\t");
		view.setText(textDate);
		view.setTextColor(Color.WHITE);
		view.setTypeface(Typeface.DEFAULT_BOLD);
	}

	// 添加gridview
	private void addGridView(View view) {

		gridView = (GridView) view.findViewById(R.id.gridview);
		gridView.setOnTouchListener(new OnTouchListener() {

			@Override
			public boolean onTouch(View v, MotionEvent event) {
				v.performClick();
				// 将GridView中的触摸事件回传给gestureDetector
				return CalendarFragment.this.gestureDetector
						.onTouchEvent(event);
			}
		});
		gridView.setOnItemClickListener(new OnItemClickListener() {
			// gridView中的每一个item的点击事件

			@Override
			public void onItemClick(AdapterView<?> arg0, View arg1,
					int position, long arg3) {
				// 点击任何一个item，得到这个item的日期(排除点击的是周日到周六(点击不响应))
				int startPosition = calV.getStartPositon();
				int endPosition = calV.getEndPosition();
				if (startPosition <= position + 7
						&& position <= endPosition - 7) {
					String scheduleDay = calV.getDateByClickItem(position)
							.split("\\.")[0]; // 这一天的阳历
					// String scheduleLunarDay =
					// calV.getDateByClickItem(position).split("\\.")[1];
					// //这一天的阴历
					// String scheduleYear = calV.getShowYear();
					String scheduleMonth = calV.getShowMonth();

//					calendar_c.set(Calendar.YEAR,
//							Integer.parseInt(calV.getShowYear()));
//					calendar_c.set(Calendar.MONTH,
//							Integer.parseInt(scheduleMonth) - 1);
//					calendar_c.set(Calendar.DAY_OF_MONTH,
//							Integer.parseInt(scheduleDay));
					onDateSet(null, Integer.parseInt(calV.getShowYear()), Integer.parseInt(scheduleMonth) - 1, Integer.parseInt(scheduleDay));
					CalendarFragment.this.dismiss();
				}
			}
		});
	}

	private class MyGestureListener extends
			GestureDetector.SimpleOnGestureListener {
		// 用户（轻触触摸屏后）松开，由一个1个MotionEvent ACTION_UP触发
		@Override
		public boolean onSingleTapUp(MotionEvent e) {
			return super.onSingleTapUp(e);
		}

		// 用户长按触摸屏，由多个MotionEvent ACTION_DOWN触发
		@Override
		public void onLongPress(MotionEvent e) {
			super.onLongPress(e);
		}

		// 用户按下触摸屏，并拖动，由1个MotionEvent ACTION_DOWN,
		// 多个ACTION_MOVE触发
		@Override
		public boolean onScroll(MotionEvent e1, MotionEvent e2,
				float distanceX, float distanceY) {
			return super.onScroll(e1, e2, distanceX, distanceY);
		}

		// 用户按下触摸屏、快速移动后松开，由1个MotionEvent ACTION_DOWN,
		// 多个ACTION_MOVE, 1个ACTION_UP触发
		@Override
		public boolean onFling(MotionEvent e1, MotionEvent e2, float velocityX,
				float velocityY) {
			if (e1.getX() - e2.getX() > 120) {
				// 向左滑动
				nextMonth.performClick();
				return true;
			} else if (e1.getX() - e2.getX() < -120) {
				// 向右滑动
				preMonth.performClick();
				return true;
			}
			return false;
		}

		// 用户轻触触摸屏，尚未松开或拖动，由一个1个MotionEvent ACTION_DOWN触发
		// 注意和onDown()的区别，强调的是没有松开或者拖动的状态
		@Override
		public void onShowPress(MotionEvent e) {
			super.onShowPress(e);
		}

		// 用户轻触触摸屏，由1个MotionEvent ACTION_DOWN触发
		@Override
		public boolean onDown(MotionEvent e) {
			return super.onDown(e);
		}

		// 双击的第二下Touch down时触发
		@Override
		public boolean onDoubleTap(MotionEvent e) {
			return super.onDoubleTap(e);
		}

		// 双击的第二下Touch down和up都会触发，可用e.getAction()区分
		@Override
		public boolean onDoubleTapEvent(MotionEvent e) {
			return super.onDoubleTapEvent(e);
		}

		// 点击一下稍微慢点的(不滑动)Touch Up:
		// onDown->onShowPress->onSingleTapUp->onSingleTapConfirmed
		@Override
		public boolean onSingleTapConfirmed(MotionEvent e) {
			return super.onSingleTapConfirmed(e);
		}
	}

	@Override
	public void onDateSet(DatePicker view, int year, int monthOfYear,
			int dayOfMonth) {
		
	}
}
