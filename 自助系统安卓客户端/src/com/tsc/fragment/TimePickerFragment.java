package com.tsc.fragment;

import java.util.Calendar;
import java.util.Date;

import android.app.Dialog;
import android.app.DialogFragment;
import android.app.TimePickerDialog;
import android.app.TimePickerDialog.OnTimeSetListener;
import android.os.Bundle;
import android.text.format.DateFormat;
import android.widget.TimePicker;

public class TimePickerFragment extends DialogFragment implements
		OnTimeSetListener {
	private Date currentDate;

	public TimePickerFragment(Date date) {
		super();
		this.currentDate = date;
	}

	@Override
	public Dialog onCreateDialog(Bundle savedInstanceState) {
		final Calendar c = Calendar.getInstance();
		c.setTime(currentDate);
		int hour = c.get(Calendar.HOUR_OF_DAY);
		int minute = c.get(Calendar.MINUTE);
		TimePickerDialog timePickerDialog = new TimePickerDialog(getActivity(),
				this, hour, minute, DateFormat.is24HourFormat(getActivity()));
		return timePickerDialog;
	}

	@Override
	public void onTimeSet(TimePicker view, int hourOfDay, int minute) {

	}

}
