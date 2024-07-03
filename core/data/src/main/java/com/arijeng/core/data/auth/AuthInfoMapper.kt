package com.arijeng.core.data.auth

import com.arijeng.core.domain.AuthInfo
import com.arijeng.core.data.auth.AuthInfoSerializable


/**
 * Created by {Bennette Molepo} on {2024/05/20}.
 * Company {Multichoice}
 * Email address {bennette.molepo@multichoice.com}
 */

fun AuthInfo.toAuthInfoSerializable(): AuthInfoSerializable {
    return AuthInfoSerializable(
        accessToken = accessToken,
        refreshToken = refreshToken,
        userId = userId
    )
}

fun AuthInfoSerializable.toAuthInfo(): AuthInfo{
    return AuthInfo(
        accessToken = accessToken,
        refreshToken = refreshToken,
        userId = userId
    )
}