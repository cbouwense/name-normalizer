class AuthorNameNormalizer() {
    fun normalize(name: String): String {
        if (name == "") return ""

        val tokens = trimAndTokenize(name)

        when (tokens.size) {
            1 -> return name.trim()
            2 -> return swapFirstAndLastNames(tokens)
            else -> return normalizeNameWithMiddleNames(tokens)
        }
    }

    private fun normalizeNameWithMiddleNames(tokens: List<String>): String {
        val middleNames = tokens.drop(1).dropLast(1)
        val middleInitials = middleNames.map {
            buildString{
                append("${it.first()}")
                if (it.length > 1) append(".")
            }
        }

        return buildString {
            append(swapFirstAndLastNames(listOf(tokens.first(), tokens.last())))
            append(" ")
            append(middleInitials.joinToString(" "))
        }
    }

    private fun swapFirstAndLastNames(tokens: List<String>): String {
        return tokens.last() + ", " + tokens.first()
    }

    private fun trimAndTokenize(name: String): List<String> {
        val trimmedName = name.trim(' ')
        return trimmedName.split(" ")
    }
}
