package com.jimmyss.slabel.service;

import com.jimmyss.slabel.component.BaseResponse;

public interface UserService {

    BaseResponse loginService(String username, String password);

    BaseResponse registerService(String username, String password);

}
