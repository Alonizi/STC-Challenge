package com.mohammedsendi.senators.model

data class Senator(
    val bioGuidedId : String ,
    val firstName : String ,
    val lastName : String,
    val party : String,
    val description : String,
    val address : String= "HARDCODED ADDRESS",
    val office : String= "",
    val birthday : String = "",
    val endDate : String = "",
    val gender : String ="",
    val sortName : String = "",
    val website : String = ""
) {
}


