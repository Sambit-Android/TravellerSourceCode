package com.bcits.utils;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.DialogInterface.OnMultiChoiceClickListener;
import android.util.AttributeSet;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.SpinnerAdapter;

import com.bcits.cecsmrfinder.R;
 
/**
 * Created by Jamal on 03/8/2015.
 */
public class MultiSelectionSpinner extends Spinner implements
        OnMultiChoiceClickListener {
    String[] _items = {"No Crop","Ragi","Jowar","Areca","Paddy","Sugarcane","Coconut","Nursuries","Others"};
    boolean[] mSelection = {false,false,false,false,false,false,false,false,false};
 
    ArrayAdapter<String> simple_adapter;
 
    public MultiSelectionSpinner(Context context) {
        super(context);
 
        simple_adapter = new ArrayAdapter<String>(context,R.layout.doublespinner);
        simple_adapter.setDropDownViewResource(R.layout.doublespinneritems);
        super.setAdapter(simple_adapter);
    }
 
    public MultiSelectionSpinner(Context context, AttributeSet attrs) {
        super(context, attrs);
 
        simple_adapter = new ArrayAdapter<String>(context, R.layout.doublespinner);
        simple_adapter.setDropDownViewResource(R.layout.doublespinneritems);
        super.setAdapter(simple_adapter);
    }
 
    public void onClick(DialogInterface dialog, int which, boolean isChecked) {
        if (mSelection != null && which < mSelection.length) {
            mSelection[which] = isChecked;
 
            simple_adapter.clear();
            simple_adapter.add(buildSelectedItemString());
        } else {
            throw new IllegalArgumentException("Argument 'which' is out of bounds.");
        }
    }
 
    @Override
    public boolean performClick() {
        AlertDialog.Builder builder = new AlertDialog.Builder(getContext());
        builder.setMultiChoiceItems(_items, mSelection, this);
        builder.setPositiveButton(getResources().getString(R.string.OK), null);
        builder.show();
        return true;
    }
 
    @Override
    public void setAdapter(SpinnerAdapter adapter) {
        throw new RuntimeException(     "setAdapter is not supported by MultiSelectSpinner.");
    }
 
    public void setItems(String[] items) {
        _items = items;
        mSelection = new boolean[_items.length];
        simple_adapter.clear();
        simple_adapter.add(_items[0]);
        Arrays.fill(mSelection, false);
    }
 
    public void setItems(List<String> items) {
        _items = items.toArray(new String[items.size()]);
        mSelection = new boolean[_items.length];
        simple_adapter.clear();
        simple_adapter.add(_items[0]);
        Arrays.fill(mSelection, false);
    }
 
    public void setSelection(String[] selection) {
        for (String cell : selection) {
            for (int j = 0; j < _items.length; ++j) {
                if (_items[j].equals(cell)) {
                    mSelection[j] = true;
                }
            }
        }
    }
 
    public void setSelection(List<String> selection) {
        for (int i = 0; i < mSelection.length; i++) {
            mSelection[i] = false;
        }
        for (String sel : selection) {
            for (int j = 0; j < _items.length; ++j) {
                if (_items[j].equals(sel)) {
                    mSelection[j] = true;
                }
            }
        }
        simple_adapter.clear();
        simple_adapter.add(buildSelectedItemString());
    }
 
    public void setSelection(int index) {
        for (int i = 0; i < mSelection.length; i++) {
            mSelection[i] = false;
        }
        if (index >= 0 && index < mSelection.length) {
            mSelection[index] = true;
        } else {
            throw new IllegalArgumentException("Index " + index
                    + " is out of bounds.");
        }
        simple_adapter.clear();
        simple_adapter.add(buildSelectedItemString());
    }
 
    public void setSelection(int[] selectedIndicies) {
        for (int i = 0; i < mSelection.length; i++) {
            mSelection[i] = false;
        }
        for (int index : selectedIndicies) {
            if (index >= 0 && index < mSelection.length) {
                mSelection[index] = true;
            } else {
                throw new IllegalArgumentException("Index " + index
                        + " is out of bounds.");
            }
        }
        simple_adapter.clear();
        simple_adapter.add(buildSelectedItemString());
    }
 
    public List<String> getSelectedStrings() {
        List<String> selection = new LinkedList<String>();
        for (int i = 0; i < _items.length; ++i) {
            if (mSelection[i]) {
                selection.add(_items[i]);
            }
        }
        return selection;
    }
 
    public List<Integer> getSelectedIndicies() {
        List<Integer> selection = new LinkedList<Integer>();
        for (int i = 0; i < _items.length; ++i) {
            if (mSelection[i]) {
                selection.add(i);
            }
        }
        return selection;
    }
 
    private String buildSelectedItemString() {
        StringBuilder sb = new StringBuilder();
        boolean foundOne = false;
 
        for (int i = 0; i < _items.length; ++i) {
            if (mSelection[i]) {
                if (foundOne) {
                    sb.append(", ");
                }
                foundOne = true;
 
                sb.append(_items[i]);
            }
        }
        return sb.toString();
    }
 
    public String getSelectedItemsAsString() {
        StringBuilder sb = new StringBuilder();
        boolean foundOne = false;
 
        for (int i = 0; i < _items.length; ++i) {
            if (mSelection[i]) {
                if (foundOne) {
                    sb.append(", ");
                }
                foundOne = true;
                sb.append(_items[i]);
            }
        }
        return sb.toString();
    }
}