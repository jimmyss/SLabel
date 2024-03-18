package com.jimmyss.slabel.component;

import lombok.Data;

@Data
public class TokenParser {
    private String token;

    public TokenParser(String token){
        this.token=token;
    }
}
