package top.jplayer.baseprolibrary.utils;


import java.util.Comparator;

import top.jplayer.baseprolibrary.mvp.model.bean.ModelContactCity;


public class ComparatorLetter implements Comparator<ModelContactCity> {

    @Override
    public int compare(ModelContactCity l, ModelContactCity r) {
        if (l == null || r == null) {
            return 0;
        }

        String lhsSortLetters = l.pys.substring(0, 1).toUpperCase();
        String rhsSortLetters = r.pys.substring(0, 1).toUpperCase();
        if (lhsSortLetters == null || rhsSortLetters == null) {
            return 0;
        }
        return lhsSortLetters.compareTo(rhsSortLetters);
    }
}