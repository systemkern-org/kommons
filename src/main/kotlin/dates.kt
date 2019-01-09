package com.systemkern.kommons

import java.time.LocalDate
import java.time.format.DateTimeFormatter

/** @return the ISO 8601 yyyy-mm-dd string representation of this date */
val LocalDate.isoStr: String
    get() = DateTimeFormatter.ISO_LOCAL_DATE.format(this)
