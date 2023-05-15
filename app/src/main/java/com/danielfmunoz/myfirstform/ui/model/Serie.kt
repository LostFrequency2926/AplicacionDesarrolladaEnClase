package com.danielfmunoz.myfirstform.ui.model

data class Serie (
    var id: String? = null,
    var name: String? = null,
    var season: String? = null,
    var summary: String? = null,
    var score: String? = null,
    var isTerrorSelected: Boolean = false,
    var isSuspenseSelected: Boolean = false,
    var isDramaSelected: Boolean = false,
    var isFiccionSelected: Boolean = false,
    var urlPicture: String? = null
)


