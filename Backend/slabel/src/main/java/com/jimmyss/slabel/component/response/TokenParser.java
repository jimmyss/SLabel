package com.jimmyss.slabel.component.response;

import lombok.Data;

@Data
public class TokenParser {
    private String token;

    public TokenParser(String token){
        this.token=token;
    }
}
