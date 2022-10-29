







class AuthorNameNormalizer() {
    fun normalize(name: String): String {
        if (name == "") return ""

        val trimmedName = trimWhitespace(name)

        val tokens = trimmedName.split(" ")

        if (tokens.size == 2) return swapFirstAndLastNames(tokens)
        return trimmedName
    }

    private fun swapFirstAndLastNames(tokens: List<String>): String {
        return tokens[1] + ", " + tokens[0]
    }

    private fun trimWhitespace(name: String): String {
        val trimmed = name.trim(' ')
        println("trimmed: $trimmed")
        return trimmed
    }
}
