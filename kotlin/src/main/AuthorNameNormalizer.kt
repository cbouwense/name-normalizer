







class AuthorNameNormalizer() {
    fun normalize(name: String): String {
        if (name == "") return ""

        val tokens = name.split(" ")

        if (tokens.size == 2) return swapFirstAndLastNames(tokens)
        return name
    }

    private fun swapFirstAndLastNames(tokens: List<String>): String {
        return tokens[1] + ", " + tokens[0]
    }
}
