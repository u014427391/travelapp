package com.tsc.fragment;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.tsc.activities.R;

/**
 * 
 * FoundFragment
 *
 */
public class FoundFragment extends Fragment{
	  private View view;

	    //@Nullable
	    @Override
	    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
	        view = inflater.inflate(R.layout.fragment_found,container,false);
	        return view;
	    }
	
}
