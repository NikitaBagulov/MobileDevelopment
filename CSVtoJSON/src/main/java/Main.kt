import com.google.gson.GsonBuilder
import com.opencsv.CSVReaderBuilder
import java.io.FileReader
import java.io.FileWriter

data class AppInfoJson(
        val name: String,
        val category: String?,
        val rating: String?,
        val reviews: String?,
        val size: String?,
        val installs: Int?,
        val type: String?,
        val price: Boolean?,
        val contentRating: String?,
        val genres: List<String>?,
        val lastUpdated: String?,
        val currentVer: String?,
        val androidVer: Int?
)

fun main() {
    val csvFile = "src/main/resources/googleplaystore.csv"
    val fileReader = FileReader(csvFile)
    val csvReader = CSVReaderBuilder(fileReader).withSkipLines(1).build()

    val gson = GsonBuilder().setPrettyPrinting().create()
    val apps = mutableListOf<AppInfoJson>()

    csvReader.use { reader ->
        var record = reader.readNext()
        while (record != null) {
            val androidVer = if (record.size > 12) {
                try {
                    val index = record[12]?.indexOf(" ") ?: -1
                    val version = if (index != -1) {
                        record[12]?.indexOf(" ")?.let { record[12]?.substring(0, it) }
                    } else {
                        record[12]
                    }
                    version?.toFloat()?.toInt() ?: -1
                } catch (e: NumberFormatException) {
                    -1
                }
            } else {
                -1
            }

            val installs = try {
                record[5]?.replace("[+,]".toRegex(), "")?.toInt() ?: -1
            } catch (e: NumberFormatException) {
                -1
            }

            val price = try {
                (record[7]?.replace("$", "")?.toFloat() ?: -1.0f) > 0
            } catch (e: NumberFormatException) {
                false
            }

            val category = record[1] ?: "n/d"

            val app = AppInfoJson(
                    name = record[0],
                    category = category,
                    rating = record[2],
                    reviews = record[3],
                    size = record[4],
                    installs = installs,
                    type = record[6],
                    price = price,
                    contentRating = record[8],
                    genres = record[9]?.split(";"),
                    lastUpdated = record[10],
                    currentVer = record[11],
                    androidVer = androidVer
            )
            apps.add(app)
            record = reader.readNext()
        }
    }

    val json = gson.toJson(apps)
    val outputFile = "src/main/resources/googleplaystore.json"

    FileWriter(outputFile).use { it.write(json) }
    println("Сохранено в $outputFile")
}
