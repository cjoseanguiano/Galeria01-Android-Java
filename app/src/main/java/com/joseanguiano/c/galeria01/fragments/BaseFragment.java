package com.joseanguiano.c.galeria01.fragments;

import horaapps.org.liz.Themed;
import horaapps.org.liz.ThemedFragment;

/**
 * Created by Carlos Anguiano on 01/09/17.
 * For more info contact: c.joseanguiano@gmail.com
 */

public abstract class BaseFragment extends ThemedFragment implements IFragment, Themed {
    public boolean onBackPressed(){
        if (editMode()){
            clearSelected();
            return true;
        }
        return false;
    }
}
