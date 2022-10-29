class AuthorNameNormalizer() {
    fun normalize(name: String): String {
        if (name == "") return ""

        val trimmedName = name.trim(' ')

        val tokens = trimmedName.split(" ")

        if (tokens.size == 2) return swapFirstAndLastNames(tokens)
        return trimmedName
    }

    private fun swapFirstAndLastNames(tokens: List<String>): String {
        return tokens[1] + ", " + tokens[0]
    }
}
