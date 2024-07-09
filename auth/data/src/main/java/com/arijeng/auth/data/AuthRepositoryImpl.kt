package com.arijeng.auth.data

import com.arijeng.auth.data.dto.UserCreateDTO
import com.arijeng.auth.data.dto.UserDTO
import com.arijeng.auth.domain.AuthRepository
import com.arijeng.core.data.networking.post
import com.arijeng.core.domain.AuthInfo
import com.arijeng.core.domain.SessionStorage
import com.arijeng.core.domain.UserInfo
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
) : AuthRepository {
    override suspend fun login(email: String, password: String): EmptyResult<DataError.Network> {
        val result = httpClient.post<LoginRequest, LoginResponse>(
            route = "/auth/login",
            body = LoginRequest(
                email, password
            )
        )
        if (result is Result.Success) {
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

    override suspend fun register(
        firstName: String,
        lastName: String,
        email: String,
        phone: String,
        password: String
    ): EmptyResult<DataError.Network> {
        val result = httpClient.post<UserCreateDTO, UserDTO>(
            route = "auth/register",
            body = UserCreateDTO(
                firstName = firstName,
                lastName = lastName,
                email = email,
                phone = phone,
                pwd = password.toCharArray()
            )
        )

        if (result is Result.Success) {
            sessionStorage.setUserInfo(
                UserInfo(
                    id = result.data.id,
                    firstName = result.data.firstName,
                    lastName = result.data.lastName,
                    email = result.data.email,
                    phone = result.data.phone,
                    status = result.data.status,
                    roles = result.data.roles,
                    accessToken = result.data.token
                )
            )
        }

        return result.asEmptyDataResult()
    }

    override suspend fun verifyOTP(
        mobileNumber: String,
        otpCode: String
    ): EmptyResult<DataError.Network> {
        return httpClient.post<VerifyOTPRequest, Unit>(
            route = "auth/verify/{mobile}/{code}",
            body = VerifyOTPRequest(
                mobileNumber = mobileNumber,
                otpCode = otpCode
            )
        )
    }
}