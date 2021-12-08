package com.example.composelifecycleexample

interface IColor {

}

data class XColor(
    var x: String = ""
) : IColor

data class MyColor(
    val id: Long = System.currentTimeMillis(),
//    val name: UUID = UUID.randomUUID()
    var name: IColor = XColor()
)
