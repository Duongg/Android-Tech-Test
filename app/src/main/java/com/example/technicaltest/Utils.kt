package com.example.technicaltest

import java.text.DecimalFormat

fun numberFormat(number: Int): String{
    var formatter = DecimalFormat("#,###,###")
    return formatter.format(number)
}
