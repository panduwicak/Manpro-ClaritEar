package com.socializer.utalk;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentPagerAdapter;

/**
 * Created by wicakcak on 12/13/2015.
 */
public class WhatThisAdapter extends FragmentPagerAdapter {

    public WhatThisAdapter(android.support.v4.app.FragmentManager fm) {
        super(fm);
    }

    @Override
    public Fragment getItem(int position) {
        switch (position){
            case 0:
                return new WhatThisAppOne();
            case 1:
                return new WhatThisAppTwo();
            case 2:
                return new WhatThisAppThree();
            case 3:
                return new WhatThisAppFour();
            default:
                break;
        }
        return null;
    }

    @Override
    public int getCount() {
        return 4;
    }
}
