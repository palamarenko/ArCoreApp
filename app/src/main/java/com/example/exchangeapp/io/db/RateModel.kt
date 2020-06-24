package com.example.exchangeapp.io.db

import androidx.room.*
import io.reactivex.Flowable
import io.reactivex.Single

@Entity
data class RateModel(
    @PrimaryKey
    val currency: String,
    val rate: Double
)


@Dao
interface RateModelDao {

    @Query("DELETE FROM RateModel")
    fun clear()

    @Query("SELECT * FROM RateModel")
    fun getList(): Single<List<RateModel>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(model: List<RateModel>)

    @Transaction
    fun updateAndDelete(model: List<RateModel>) {
        clear()
        insert(model)
    }

}