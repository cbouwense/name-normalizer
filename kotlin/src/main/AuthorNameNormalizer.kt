class AuthorNameNormalizer() {
    fun normalize(name: String): String {
        if (name == "") return ""

        val tokens = trimAndTokenize(name)

        when (tokens.size) {
            3 -> return normalizeNameWithMiddleInitial(tokens)
            2 -> return swapFirstAndLastNames(tokens)
            else -> return name
        }
    }

    private fun normalizeNameWithMiddleInitial(tokens: List<String>): String {
        return buildString {
            append(swapFirstAndLastNames(listOf(tokens[0], tokens[2])))
            append(" ${tokens[1].first()}.")
        }
    }

    private fun swapFirstAndLastNames(tokens: List<String>): String {
        return tokens[1] + ", " + tokens[0]
    }

    private fun trimAndTokenize(name: String): List<String> {
        val trimmedName = name.trim(' ')
        return trimmedName.split(" ")
    }
}
