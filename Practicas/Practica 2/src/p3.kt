fun main() {
    // 1. Lista inmutable del Top 10 de canciones
    val top10Canciones: List<String> = listOf(
        "I Just Might", "Man I Need", "SUPERESTRELLA ", "Homewrecker ", "Die On This Hill", "CAMBIARE ", "The Fate of Ophelia", "Loco X Verte", "bandaids", "Las Muñequitas"
    )

    // 2. Playlists del usuario (Mutables porque las vamos a modificar)
    val playlistMaxLey = mutableListOf("PRETTY PLEASE", "Crush", "Oh No!", "Lights Out", "All I Wanted", "Man I Need", "Headbangeeeeerrrrr!!!!!", "RATATATA")
    val playlistStar = mutableListOf("Nightmare", "Headbangeeeeerrrrr!!!!!", "The Fate of Ophelia", "Homewrecker ", "RATATATA", "I Just Might")
    val playlistPop = mutableListOf("SUPERESTRELLA ", "Die On This Hill", "CAMBIARE", "Loco X Verte", "bandaids", "Las Muñequitas")

    coincidenciasTop10(playlistMaxLey, top10Canciones)
    compararRemoverDuplicados(playlistMaxLey, playlistStar)

    val sub = obtenerSublista(playlistMaxLey, 0, 3)
    println("Sublista: $sub")

    ordenarPlaylist(playlistPop, ascendente = true)
    revolverPlaylist(playlistPop)
}

// Función que muestra las coincidencias de X playlist con el top10
fun coincidenciasTop10(playlist: List<String>, top10: List<String>) {
    println("\nComparando lista con Top 10...")
    val coincidencias = playlist.filter { it in top10 }
    println("Coincidencias encontradas: $coincidencias")
}

// Función que compara dos playlists y remueve de la primera los duplicados encontrados en la segunda
fun compararRemoverDuplicados(listaA: MutableList<String>, listaB: MutableList<String>) {
    println("\nComparando listas...")
    println("Lista A: $listaA")
    println("Lista B: $listaB")
    
    val seEliminaron = listaA.removeAll(listaB)
    
    if (seEliminaron) {
        println("Se eliminaron los duplicados. Lista A ahora: $listaA")
    } else {
        println("No se encontraron canciones duplicadas.")
    }
}

// Función que debuelve una sublista dandole dos indices
fun obtenerSublista(lista: List<String>, inicio: Int, fin: Int): List<String> {
    println("\nObteniendo sublista...")
    return if (inicio >= 0 && fin <= lista.size && inicio < fin) {
        lista.subList(inicio, fin)
    } else {
        println("Error: Indices fuera de rango.")
        emptyList()
    }
}

// Función que ordena la playlist alfabéticamente
fun ordenarPlaylist(lista: MutableList<String>, ascendente: Boolean) {
    println("\nOrdenando playlist...")
    if (ascendente) {
        lista.sort()
        println("Playlist ordenada de A-Z: $lista")
    } else {
        lista.sortDescending()
        println("Playlist ordenada de Z-A: $lista")
    }
}

// Función que revuelve (shuffle) las canciones
fun revolverPlaylist(lista: MutableList<String>) {
    println("\nRevolviendo playlist...")
    lista.shuffle()
    println("Playlist en modo aleatorio: $lista")
}