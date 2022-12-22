package utils

import java.io.File

class IOTool {
    companion object {
        fun readAlphabet(file: String = "src/main/res/alphabet_mtf"): Array<String> {
            val charList = mutableListOf<String>()
            File(file).forEachLine { line ->
                line.split(" ").forEach {
                    charList.add(it)
                }
            }
            return charList.toTypedArray()
        }

        fun readAlphabetFano(file: String = "src/main/res/alphabet_fano"): Pair<Array<String>, Array<String>> {
            val lists = listOf<MutableList<String>>(mutableListOf(), mutableListOf())
            var flag = true
            File(file).forEachLine { line ->
                if (flag) {
                    line.split(" ").forEach {
                        lists[0].add(it)
                    }
                    flag = false
                } else {
                    line.split(" ").forEach {
                        lists[1].add(it)
                    }
                }
            }
            File(file)
            return Pair(lists[0].toTypedArray(), lists[1].toTypedArray())
        }



        fun readTargetString(defaultFile: String = "src/main/res/example_string_mtf") : String {
            print("Enter the target string: ")
            var str: String = readLine() ?: ""
            if (str.isNotEmpty()) {
                return str
            }
            println("Input is empty. String will be read from file")
            File(defaultFile).forEachLine { str = it }
            return str
        }
    }
}