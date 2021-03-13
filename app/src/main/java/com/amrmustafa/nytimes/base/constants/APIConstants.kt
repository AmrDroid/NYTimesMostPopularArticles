package com.amrmustafa.nytimes.base.constants

enum class Period(val value: String) {
    DAILY("1"), WEEKLY("7"), MONTHLY("30");

    companion object {
        fun getPeriod(index: Int): Period {
            return when (index) {
                0 -> {
                    DAILY
                }
                1 -> {
                    WEEKLY
                }
                else -> {
                    MONTHLY
                }
            }
        }
    }
}