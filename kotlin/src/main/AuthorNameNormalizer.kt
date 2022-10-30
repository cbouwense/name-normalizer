class AuthorNameNormalizer() {
    fun normalize(name: String): String {
        if (name == "") return ""

        var tokens = trimAndTokenize(name)
        var suffix: String? = null
        if (tokens.last().last() == '.') {
            suffix = tokens.last()
            tokens = tokens.dropLast(1)
        }

        var nameToReturn = ""
        when (tokens.size) {
            1 -> nameToReturn = name.trim()
            2 -> nameToReturn = swapFirstAndLastNames(tokens)
            else -> nameToReturn = normalizeNameWithMiddleNames(tokens)
        }
        if (suffix != null) return "${nameToReturn}, ${suffix}"
        return nameToReturn
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
        return trimmedName.split(" ").map {
            it.trimEnd(',')
        }
    }
}
