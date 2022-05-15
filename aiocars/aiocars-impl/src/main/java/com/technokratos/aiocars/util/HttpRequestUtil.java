package com.technokratos.aiocars.util;

import lombok.experimental.UtilityClass;
import org.apache.commons.lang3.StringUtils;

import java.util.Optional;

import static com.technokratos.aiocars.constant.AiocarsConstants.BEARER;

@UtilityClass
public class HttpRequestUtil {

    public String getTokenFromAuthorizationHeader(String authorizationHeader){
        return Optional.of(authorizationHeader)
                .filter(StringUtils::isNotBlank)
                .map(bearer -> StringUtils.removeStart(bearer, BEARER).trim())
                .filter(StringUtils::isNotBlank)
                .orElse(null);
    }
}
