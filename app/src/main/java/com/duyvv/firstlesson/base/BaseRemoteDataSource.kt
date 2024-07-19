package com.duyvv.firstlesson.base

import com.duyvv.firstlesson.utils.DEFAULT_MESSAGE_ERROR
import kotlinx.coroutines.CancellationException
import retrofit2.HttpException
import java.io.IOException
import java.net.SocketTimeoutException

open class BaseRemoteDataSource {
    suspend fun <T> safeCallApi(call: suspend () -> T): NetworkResponse<T> {
        return try {
            val response = call.invoke()
            NetworkResponse.Success(response)
        } catch (e: HttpException) {
            NetworkResponse.Error(e.message ?: DEFAULT_MESSAGE_ERROR)
        } catch (e: IOException) {
            NetworkResponse.Error(e.message ?: DEFAULT_MESSAGE_ERROR)
        } catch (e: SocketTimeoutException) {
            NetworkResponse.Error(e.message ?: DEFAULT_MESSAGE_ERROR)
        } catch (e: CancellationException) {
            NetworkResponse.Error(e.message ?: DEFAULT_MESSAGE_ERROR)
        } catch (e: Exception) {
            NetworkResponse.Error(e.message ?: DEFAULT_MESSAGE_ERROR)
        }
    }
}
