package com.arijeng.core.data.auth

import com.arijeng.core.domain.AuthInfo
import com.arijeng.core.data.auth.AuthInfoSerializable
import com.arijeng.core.domain.UserInfo


/**
 * Created by {Bennette Molepo} on {2024/05/20}.
 * Company {Multichoice}
 * Email address {bennette.molepo@multichoice.com}
 */

fun UserInfo.toUserInfoSerializable(): UserInfoSerializable {
    return UserInfoSerializable(
        id = id,
        firstName = firstName,
        lastName = lastName,
        email = email,
        phone = phone,
        status = status,
        roles = roles,
        accessToken = accessToken
    )
}


fun UserInfoSerializable.toUserInfo(): UserInfo{
    return UserInfo(
        id = id,
        firstName = firstName,
        lastName = lastName,
        email = email,
        phone = phone,
        status = status,
        roles = roles,
        accessToken = accessToken
    )
}