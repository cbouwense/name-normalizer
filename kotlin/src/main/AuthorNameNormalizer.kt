class AuthorNameNormalizer() {
    fun normalize(name: String): String {
        if (name == "") return ""
        throwIfNameContainsMultipleCommas(name)

        val tokens = trimAndTokenize(name)
        val suffix = getSuffixIfPresent(tokens)

        if (suffix != null) return "${reorderName(tokens.dropLast(1))}, ${suffix}"
        return reorderName(tokens)
    }

    private fun throwIfNameContainsMultipleCommas(name: String) {
        if (name.filter { it == ',' }.length > 1) throw IllegalArgumentException()
    }

    private fun getSuffixIfPresent(tokens: List<String>): String? {
        if (tokens.last().last() == '.') return tokens.last()
        return null
    }

    private fun reorderName(tokens: List<String>): String {
        when (tokens.size) {
            1 -> return tokens.first().toString()
            2 -> return swapFirstAndLastNames(tokens)
            else -> return normalizeNameWithMiddleNames(tokens)
        }
    }

    private fun normalizeNameWithMiddleNames(tokens: List<String>): String {
        val middleNames = tokens.drop(1).dropLast(1)
        val middleInitials = middleNames.map {
            buildString{
                append(it.first())
                if (it.length > 1) append(".")
            }
        }

        return buildString {
            append(swapFirstAndLastNames(tokens))
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
