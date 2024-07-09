package com.arijeng.core.domain


/**
 * Created by {Bennette Molepo} on {2024/06/24}.
 * Company {Multichoice}
 * Email address {bennette.molepo@multichoice.com}
 */
interface SessionStorage {
    suspend fun get(): AuthInfo?
    suspend fun set(info: AuthInfo?)
    suspend fun getUserInfo(): UserInfo?
    suspend fun setUserInfo(info: UserInfo?)
}