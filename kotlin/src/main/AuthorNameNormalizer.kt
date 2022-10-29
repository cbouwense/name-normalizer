class AuthorNameNormalizer() {
    fun normalize(name: String): String {
        if (name == "") return ""

        val tokens = trimAndTokenize(name)

        when {
            tokens.size > 3 -> return normalizeNameWithMultipleMiddleNames(tokens)
            tokens.size == 3 -> return normalizeNameWithOneMiddleName(tokens)
            tokens.size == 2 -> return swapFirstAndLastNames(tokens)
            else -> return name
        }
    }

    private fun normalizeNameWithMultipleMiddleNames(tokens: List<String>): String {
        val middleNames = tokens.drop(1).dropLast(1)
        val middleInitials = middleNames.map {
            "${it.first()}."
        }

        return buildString {
            append(swapFirstAndLastNames(listOf(tokens.first(), tokens.last())))
            append(" ")
            append(middleInitials.joinToString(" "))
        }
    }

    private fun normalizeNameWithOneMiddleName(tokens: List<String>): String {
        val middleNameIsAbbreviated = tokens[1].length == 1

        return buildString {
            append(swapFirstAndLastNames(listOf(tokens[0], tokens[2])))
            append(" ${tokens[1].first()}")
            if (!middleNameIsAbbreviated) append(".")
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
