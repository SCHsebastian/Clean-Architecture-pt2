package es.queryinformatica.data_remote.networking.user

import retrofit2.http.GET
import retrofit2.http.Path

interface UserService {

    @GET("/users")
    suspend fun getUsers(): List<UserApiModel>

    @GET("/users/{userId}")
    suspend fun getUserById(@Path("userId") userId: Long): UserApiModel

}