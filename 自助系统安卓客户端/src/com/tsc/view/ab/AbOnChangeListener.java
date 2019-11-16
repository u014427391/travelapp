package com.tsc.view.ab;


/**
 * The listener interface for receiving abOnChange events.
 * The class that is interested in processing a abOnChange
 * event implements this interface, and the object created
 * with that class is registered with a component using the
 * component's <code>addAbOnChangeListener<code> method. When
 * the abOnChange event occurs, that object's appropriate
 * method is invoked.
 *
 * @see AbOnChangeEvent
 */
public interface AbOnChangeListener {
    
    /**
     * On change.
     *
     * @param position the position
     */
    public void onChange(int position); 

}
