package com.kaetee.covid19india.tracker

data class StateWiseData(
    var confirmedCasesIndian: Int = 0,
    var confirmedCasesForeign: Int = 0,
    var discharged: Int = 0,
    var deaths: Int =0,
    var loc: String = "India",
    var totalConfirmed: Int = 0
)