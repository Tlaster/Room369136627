package moe.tlaster.room369136627

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.paging.PagingSource
import androidx.room.Dao
import androidx.room.Database
import androidx.room.Embedded
import androidx.room.Entity
import androidx.room.PrimaryKey
import androidx.room.Query
import androidx.room.Relation
import androidx.room.RoomDatabase
import androidx.room.Transaction

class MainActivity : ComponentActivity()


@Database(
    entities = [
        Foo::class,
        Bar::class,
    ],
    version = 1
)
abstract class Database : RoomDatabase() {
    abstract fun fooWithBarDao(): FooWithBarDao
}

@Dao
interface FooWithBarDao {
    @Transaction
    @Query("SELECT * FROM Foo")
    fun getFooWithBar(): PagingSource<Int, FooWithBar>
}

data class FooWithBar(
    @Embedded
    val foo: Foo,
    @Relation(
        parentColumn = "id",
        entityColumn = "id",
    )
    val bar: Bar,
)

@Entity
data class Bar(
    @PrimaryKey val id: Int,
)

@Entity
data class Foo(
    @PrimaryKey val id: Int,
)