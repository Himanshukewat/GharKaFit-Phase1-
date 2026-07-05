package com.example.gharkafit.data.user

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update

@Dao
interface UserDao {

    @Insert
    suspend fun insertUser(user: UserEntity)
// TODO for future make multiple user with login page
    @Query("SELECT * FROM users ORDER BY user_id DESC LIMIT 1")
    suspend fun getUser(): UserEntity?

    @Update
    suspend fun updateUser(user: UserEntity)

    @Query("DELETE FROM users")
    suspend fun deleteAllUsers()
}