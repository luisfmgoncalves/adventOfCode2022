package aoc.day7

open class Folder(val name: String, val parent: Folder? = null) {
    private val folders = mutableMapOf<String, Folder>()
    private val files = mutableMapOf<String, File>()
    private var totalSize: Int = 0

    fun getSize(): Int {
        return totalSize + folders.values.sumOf { it.getSize() }
    }

    fun addFile(name: String, size: Int) {
        files[name] = File(name, size, this)
        totalSize += size
    }

    fun addFolder(name: String) {
        folders[name] = Folder(name, this)
    }

    fun findFolder(toFind: String): Folder? {
        return folders[toFind]
    }

    fun find(predicate: (Folder) -> Boolean): List<Folder> {
        return folders.values.filter(predicate) + folders.values.flatMap { it.find(predicate) }
    }
}