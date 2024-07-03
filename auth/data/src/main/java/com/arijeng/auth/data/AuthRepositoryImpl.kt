package com.arijeng.auth.data

import com.arijeng.auth.domain.AuthRepository
import com.arijeng.core.data.networking.post
import com.arijeng.core.domain.AuthInfo
import com.arijeng.core.domain.SessionStorage
import com.arijeng.core.domain.util.DataError
import com.arijeng.core.domain.util.EmptyResult
import com.arijeng.core.domain.util.Result
import com.arijeng.core.domain.util.asEmptyDataResult
import io.ktor.client.HttpClient


/**
 * Created by {Bennette Molepo} on {2024/05/19}.
 * Company {Multichoice}
 * Email address {bennette.molepo@multichoice.com}
 */
class AuthRepositoryImpl(
    private val httpClient: HttpClient,
    private val sessionStorage: SessionStorage
): AuthRepository {
    override suspend fun login(email: String, password: String): EmptyResult<DataError.Network> {
        val result = httpClient.post<LoginRequest,LoginResponse>(
            route = "/auth/login",
            body = LoginRequest(
                email, password
            )
        )
        if(result is Result.Success){
            sessionStorage.set(
                AuthInfo(
                    accessToken = result.data.accessToken,
                    refreshToken = result.data.refreshToken,
                    userId = result.data.userId
                )
            )
        }
        return result.asEmptyDataResult()

    }

    override suspend fun register(email: String, password: String): EmptyResult<DataError.Network> {
       return httpClient.post<RegisterRequest, Unit>(
           route = "auth/register",
           body = RegisterRequest(
               email = email,
               password = password
           )
       )
    }
}