package es.queryinformatica.data_remote.networking.user

import retrofit2.http.GET

interface UserService {

    @GET("/users")
    suspend fun getUsers(): List<UserApiModel>

    @GET("/users/{userId}")
    suspend fun getUserById(userId: Long): UserApiModel

}