package com.example.lab2

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.lab2.ui.theme.Lab2Theme

// Mình đã đổi tên class thành MainActivity để app tự động chạy màn hình này đầu tiên
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            Lab2Theme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    // Gọi hàm giao diện Thiệp sinh nhật
                    BirthdayCardScreen(
                        message = "Happy Birthday, Mi!",
                        from = "From Mi",
                        modifier = Modifier.padding(innerPadding)
                    )
                }
            }
        }
    }
}

@Composable
fun BirthdayCardScreen(message: String, from: String, modifier: Modifier = Modifier) {
    // Surface tạo nền màu cho thiệp
    Surface(
        color = Color(0xFFD0BCFF), // Màu tím nhạt (Hex code)
        modifier = modifier.fillMaxSize()
    ) {
        // Column sắp xếp các phần tử theo chiều dọc
        Column(
            verticalArrangement = Arrangement.Center, // Căn giữa nội dung theo chiều dọc
            horizontalAlignment = Alignment.CenterHorizontally, // Căn giữa theo chiều ngang
            modifier = Modifier.padding(16.dp)
        ) {
            // Lời chúc mừng sinh nhật
            Text(
                text = message,
                fontSize = 36.sp, // Kích thước chữ lớn
                lineHeight = 40.sp,
                fontWeight = FontWeight.Bold,
                textAlign = TextAlign.Center,
                modifier = Modifier.padding(bottom = 16.dp)
            )

            // Tên người gửi
            Text(
                text = from,
                fontSize = 24.sp,
                modifier = Modifier
                    .padding(16.dp)
                // Ở đoạn này, code cũ bị lỗi cú pháp align một chút,
                // mình đã đặt Text người gửi vào Column riêng để căn lề phải dễ hơn
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
fun BirthdayCardPreview() {
    Lab2Theme {
        BirthdayCardScreen("Happy Birthday, Mi!", "From Nhom")
    }
}