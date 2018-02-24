package com.WordInTouch.Utils;

import java.util.regex.Pattern;

/**
 * Created by cloner on 12/6/17.
 */

public class Validation {
    public boolean phoneNumber(String phone_number){
        return Pattern.compile("(0|\\+98)?([ ]|-|[()]){0,2}9[1|2|3|4]([ ]|-|[()]){0,2}(?:[0-9]([ ]|-|[()]){0,2}){8}")
                .matcher(phone_number).matches();
    }
}
