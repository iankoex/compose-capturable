# compose-capturable
Demo of how to convert A composable to Bitmap
## Usage

``` kotlin
    var controller by remember { mutableStateOf(CaptureController()) }
    var bitmap: ImageBitmap? by remember { mutableStateOf<ImageBitmap?>(null) }

    Capturable(controller) {
        Column {
            Text("All of this will be Captured")
            Text("Event this button and the divider")
            Divider(thickness = 3.dp)
            Button(onClick = {
                controller.captureBitmap()?.let {
                    bitmap = it.asImageBitmap()
                }
            }) {
                Text(text = "Capture")
            }
            bitmap?.let {
                Image(
                    bitmap = it,
                    contentDescription = null,
                    modifier = Modifier.size(300.dp)
                )
            }
        }
    }
```
