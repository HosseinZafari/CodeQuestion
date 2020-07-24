package network.util


import java.util.concurrent.TimeUnit

/*
@created in 06/05/2020 - 5:09 PM
@project Retrofit_startup2
@author Hossein Zafari 
@email  hosseinzafari2000@gmail.com 
*/

// @formatter:off

sealed class
CTimeUnit(
    var
    number: Number
)

class CNano(number: Number) : CTimeUnit(number)
class CMicro(number: Number) : CTimeUnit(number)
class CMillis(number: Number) : CTimeUnit(number)
class CSecond(number: Number) : CTimeUnit(number)
class CMinute(number: Number) : CTimeUnit(number)
class CHour(number: Number) : CTimeUnit(number)
class CDay(number: Number) : CTimeUnit(number)

val Number.n: CTimeUnit get() = CNano(this)
val Number.mc: CTimeUnit get() = CMicro(this)
val Number.ms: CTimeUnit get() = CMillis(this)
val Number.s: CTimeUnit get() = CSecond(this)
val Number.m: CTimeUnit get() = CMinute(this)
val Number.h: CTimeUnit get() = CHour(this)

val Number.d: CTimeUnit get() = CDay(this)

fun mergeTimeAndUnit(time: Number, unit: TimeUnit) =
    when (unit) {
        TimeUnit.MICROSECONDS -> time.mc
        TimeUnit.MILLISECONDS -> time.ms
        TimeUnit.SECONDS -> time.s
        TimeUnit.MINUTES -> time.m
        TimeUnit.HOURS -> time.h
        TimeUnit.DAYS -> time.d
        else -> time.s
    }


inline fun detectTime(timeUnit: CTimeUnit, block: (Number, TimeUnit) -> Unit) =
    when (timeUnit) {
        is CMicro -> block(timeUnit.number, TimeUnit.MICROSECONDS)
        is CMillis -> block(timeUnit.number, TimeUnit.MILLISECONDS)
        is CSecond -> block(timeUnit.number, TimeUnit.SECONDS)
        is CMinute -> block(timeUnit.number, TimeUnit.MINUTES)
        is CHour -> block(timeUnit.number, TimeUnit.HOURS)
        is CDay -> block(timeUnit.number, TimeUnit.DAYS)
        else -> Unit
    }


