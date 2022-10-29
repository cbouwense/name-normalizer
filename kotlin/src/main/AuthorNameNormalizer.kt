class AuthorNameNormalizer() {
    fun normalize(name: String): String {
        if (name == "") return ""

        val trimmedName = name.trim(' ')

        val tokens = trimmedName.split(" ")

        if (tokens.size == 3) return normalizeNameWithMiddleInitial(tokens)
        if (tokens.size == 2) return swapFirstAndLastNames(tokens)
        return trimmedName
    }

    private fun normalizeNameWithMiddleInitial(tokens: List<String>): String {
        return buildString {
            append(swapFirstAndLastNames(listOf(tokens[0], tokens[2])))
            append(" ")
            append(tokens[1].first())
            append(".")
        }
    }

    private fun swapFirstAndLastNames(tokens: List<String>): String {
        return tokens[1] + ", " + tokens[0]
    }
}
