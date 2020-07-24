package network.helper

import hosseinzafari.github.retrofit_startup2.lib.util.CGson

/*
@created in 27/04/2020 - 4:40 PM
@project Retrofit_startup2
@author Hossein Zafari 
@email  hosseinzafari2000@gmail.com 
*/


@CGson
data class ErrorModel(
  val statusCode: Int? = null ,
  val message: String? = null,
  val name: String? = null
)