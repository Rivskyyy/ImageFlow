package com.rivskyinc.imageflow.domain.entities.PhotoX

data class Gift(
    val eligible_durations: List<String>,
    val gift_eligible: Boolean,
    val new_flow: Boolean
)