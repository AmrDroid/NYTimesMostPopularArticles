package com.amrmustafa.nytimes.base.constants

const val EndpointUrl = "https://api.nytimes.com/svc/mostpopular/v2/mostviewed/all-sections/"
const val APIKey = "sdvErtZ4L3wzAacQ3d3amv7bOky6vzQP"

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