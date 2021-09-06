package com.lilcode.example.roomdatabasedemo

import androidx.annotation.NonNull
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "products")
data class Product(

    @PrimaryKey(autoGenerate = true) @NonNull @ColumnInfo(name = "productId") var id: Int = 0,
    @ColumnInfo(name = "productName") var productName: String? = null,
    var quantity: Int = 0 // SQL 쿼리에서 사용하지 않으므로 애노테이션 지정 x.
) {
}
