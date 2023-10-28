package com.pacifique;

import java.util.Calendar;

public record Invoice(String id, float number) {
    static String prefix = String.valueOf(Calendar.getInstance().get(Calendar.YEAR)) +
            (Calendar.getInstance().get(Calendar.WEEK_OF_MONTH) + 1);




    public Invoice {
        id =prefix +id.trim();
    }
}
