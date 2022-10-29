







class AuthorNameNormalizer() {
    fun normalize(name: String): String {
        if (name == "") return ""
        
        val tokens = name.split(" ")

        if (tokens.size == 1) return name

        val reversedName = tokens[1] + ", " + tokens[0]

        return reversedName
    }
}
