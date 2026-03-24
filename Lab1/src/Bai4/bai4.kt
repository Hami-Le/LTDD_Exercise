package Bai4

import kotlinx.coroutines.*
import kotlin.random.Random

// 1) Khai báo hàm tạm ngưng (suspend function) theo yêu cầu
suspend fun getValue(): Double {
    // Giả lập công việc tốn thời gian mà không chặn luồng (non-blocking)
    delay(500)
    return Random.nextDouble()
}

fun main() = runBlocking {

    // 2) Chạy một hàm tạm ngưng trong GlobalScope (hoặc launch)
    val job: Job = GlobalScope.launch {
        try {
            val value = getValue()
            println("GlobalScope: Got value $value")
        } catch (e: Exception) {
            println("GlobalScope Error: ${e.message}")
        }
    }

    // 3) Sử dụng async/await để lấy kết quả không đồng bộ
    val deferredValue = async {
        getValue()
    }
    println("Async/Await: Waiting for output...")
    println("Async/Await: Output is ${deferredValue.await()}")

    // 4) Phát hiện ngoại lệ (try-catch)
    try {
        riskyWork()
    } catch (e: Exception) {
        println("Caught exception: ${e.message}")
    }

    // 5) Object (Singleton)
    DataProviderManager.log("Hello from object singleton")

    // 6) Enum + When
    val direction = Direction.NORTH
    checkDirection(direction)

    println("Done.")

    // Đảm bảo GlobalScope hoàn tất trước khi kết thúc main (chỉ dùng cho mục đích demo)
    job.join()
}

fun riskyWork() {
    throw IllegalStateException("Something went wrong")
}

fun checkDirection(direction: Direction) {
    when (direction) {
        Direction.NORTH -> println("Go up")
        Direction.SOUTH -> println("Go down")
        Direction.WEST  -> println("Go left")
        Direction.EAST  -> println("Go right")
    }
}

// Khai báo đối tượng (Object)
object DataProviderManager {
    fun log(message: String) = println("DataProviderManager: $message")
}

// Khai báo lớp Enum
enum class Direction {
    NORTH, SOUTH, WEST, EAST
}