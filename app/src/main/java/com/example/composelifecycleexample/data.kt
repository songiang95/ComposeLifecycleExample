package com.example.composelifecycleexample

//Stable
data class Person1(
    val name: String = "Nguyen Van A",
    val age: Int = 18
)

//Not Stable
data class Person2(
    val name: String = "Nguyen Van A",
    var age: Int = 18
)

//Not stable
data class MyData2(
    val id: Long = System.currentTimeMillis(),
    val person: Person2 = Person2()
)

//Stable
data class MyData1(
    val id: Long = System.currentTimeMillis(),
    val person: Person1 = Person1()
)


