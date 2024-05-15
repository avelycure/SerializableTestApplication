package com.avelycure.serializabletestapplication

import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import com.avelycure.serializabletestapplication.ui.NotificationMeta
import com.avelycure.serializabletestapplication.ui.NotificationUpdater
import com.avelycure.serializabletestapplication.ui.theme.SerializableTestApplicationTheme
import java.io.File
import java.io.FileInputStream
import java.io.FileOutputStream
import java.io.ObjectInputStream
import java.io.ObjectOutputStream

/**
 * Чтобы воспроизвести прикол:
 * 1. Запускаем приложение
 * 2. Создаем файл
 * 3. Сериализуем объект
 * 4. Десериализуем объект
 * 5. Видим в логах: NotificationMeta(pushType=SINGLE_MESSAGE, hasSmartReplies=true, isReminder=true)
 * 6. Убираем комментарии из файла PushMessageType, а в файле NotificationUpdater их наоборот добавляем
 * 7. Запускаем приложение
 * 8. Десериализуем объект
 * 9. Видим в логах: NotificationMeta(pushType=null, hasSmartReplies=true, isReminder=true)
 */
class MainActivity : ComponentActivity() {
    lateinit var dir: File
    lateinit var file: File

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        dir = this@MainActivity.filesDir
        file = File(dir, "serializable.bin")
        setContent {
            SerializableTestApplicationTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    Box(
                        modifier = Modifier
                            .fillMaxSize()
                    ) {
                        Column(
                            modifier = Modifier.align(Alignment.Center)
                        ) {
                            Button(onClick = {
                                file.createNewFile()
                            }) {
                                Text(text = "Create new file")
                            }

                            Button(onClick = {
                                val serializableObject = NotificationMeta(
                                    pushType = NotificationUpdater.PushMessageType.SINGLE_MESSAGE,
                                    hasSmartReplies = true,
                                    isReminder = true
                                )
                                val fileOutputStream = FileOutputStream(file)
                                ObjectOutputStream(fileOutputStream).use {
                                    it.writeObject(serializableObject)
                                    it.flush()
                                }
                            }) {
                                Text(text = "Serialize object")
                            }

                            Button(onClick = {
                                val fileInputStream = FileInputStream(file)
                                ObjectInputStream(fileInputStream).use {
                                    Log.d("mytag", "${it.readObject() as NotificationMeta}")
                                }
                            }) {
                                Text(text = "Deserialize object")
                            }
                        }
                    }
                }
            }
        }
    }
}