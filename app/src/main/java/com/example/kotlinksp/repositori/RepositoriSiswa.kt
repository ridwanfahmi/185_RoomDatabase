package com.example.kotlinksp.repositori

import com.example.kotlinksp.room.Siswa
import com.example.kotlinksp.room.SiswaDao
import kotlinx.coroutines.flow.Flow

interface RepositoriSiswa{
    fun getAllSiswaStream(): Flow<List<Siswa>>
    suspend fun insertSiswa(siswa: Siswa)
    //tambahan

    fun getSiswaStream(id: Int): Flow<Siswa?>
}

class OfflineRepositoriSiswa(
    private val siswaDao: SiswaDao
): RepositoriSiswa{
    override fun getAllSiswaStream(): Flow<List<Siswa>> = siswaDao.getAllSiswa()
    override suspend fun insertSiswa(siswa: Siswa) = siswaDao.insert(siswa)
}