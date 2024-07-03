package com.arijeng.auth.domain

import com.arijeng.core.domain.util.DataError
import com.arijeng.core.domain.util.EmptyResult


/**
 * Created by {Bennette Molepo} on {2024/05/19}.
 * Company {Multichoice}
 * Email address {bennette.molepo@multichoice.com}
 */
interface AuthRepository {

    suspend fun login(email: String, password: String): EmptyResult<DataError.Network>
    suspend fun register(email: String, password: String): EmptyResult<DataError.Network>
}