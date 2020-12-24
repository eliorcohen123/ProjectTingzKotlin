package com.eliorcohen.projecttingzkotlin.ModelsPackage

data class TotalModel(
    var title: String? = null,
    var image: String? = null,
    var rating: Double? = null,
    var releaseYear: Int? = null,
    var genre: List<String>? = null
)
