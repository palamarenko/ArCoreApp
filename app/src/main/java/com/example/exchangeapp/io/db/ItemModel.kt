package com.example.exchangeapp.io.db

import androidx.room.*
import io.reactivex.Completable
import io.reactivex.Flowable
import io.reactivex.Single

@Entity
data class ItemModel(
    @PrimaryKey
    val id: String,
    var name : String,
    var imagePlace : String
)


@Dao
interface ItemModelDao {

    @Query("DELETE FROM ItemModel")
    fun clear()

    @Query("SELECT * FROM ItemModel")
    fun getList(): Single<List<ItemModel>>

    @Query("SELECT * FROM ItemModel")
    fun getListSink(): List<ItemModel>

    @Query("SELECT * FROM ItemModel WHERE id  =:id")
    fun get(id : String): Single<ItemModel>

    @Query("DELETE FROM ItemModel WHERE id  =:id")
    fun delete(id : String): Completable

    @Query("SELECT * FROM ItemModel")
    fun listenList(): Flowable<List<ItemModel>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(model: List<ItemModel>)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(model: ItemModel)

    @Transaction
    fun updateAndDelete(model: List<ItemModel>) {
        clear()
        insert(model)
    }

}