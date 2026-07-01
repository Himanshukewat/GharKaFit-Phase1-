package com.example.gharkafit.data.user

class UserRepository(
    private val userDao: UserDao
) {

    suspend fun insertUser(user: UserEntity) {
        userDao.insertUser(user)
    }

    suspend fun getUser(): UserEntity? {
        return userDao.getUser()
    }

    suspend fun updateUser(user: UserEntity) {
        userDao.updateUser(user)
    }

    suspend fun hasUser(): Boolean {
        return userDao.getUser() != null
    }
}
