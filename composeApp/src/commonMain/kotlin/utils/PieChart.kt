package utils


import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.tween
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.size
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.rotate
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.StrokeCap
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import kotlinx.coroutines.delay

data class SliceMetaData(
    val startAngle: Float,
    val sweepAngle: Float,
    val color: Color,
    val radius: Dp,
)

private fun buildPieChartSlices(sliceData: List<PieChartSliceData>): List<SliceMetaData> {
    var lastValue = 0f
    val totalSum = sliceData.sumOf { it.value }
    val pieChartSlices = mutableListOf<SliceMetaData>()
    sliceData.forEach { value ->
        val sweepAngle = 360 * value.value / totalSum.toFloat()
        val slice = SliceMetaData(
            startAngle = lastValue,
            sweepAngle = sweepAngle,
            color = value.color,
            radius = value.radius
        )
        pieChartSlices.add(slice)
        lastValue += sweepAngle
    }
    return pieChartSlices.toList()
}

@Composable
fun PieChart(
    sliceData: List<PieChartSliceData>,
    modifier: Modifier = Modifier,
    chartRadius: Dp = 50.dp,
) {
    val chartSlices = remember { buildPieChartSlices(sliceData) }
    // Pie Chart
    Box(modifier = modifier) {
        chartSlices.forEachIndexed { index, slice ->
            var animate by remember { mutableStateOf(false) }
            val animatedSweepAngle by animateFloatAsState(targetValue = if (animate) slice.sweepAngle else 0f, label = "", animationSpec = tween(600))
            LaunchedEffect(key1 = Unit) {
                delay(300L * index)
                animate = true
            }
            Canvas(
                modifier = Modifier
                    .size(chartRadius * 2f)
                    .rotate(-90f)
            ) {
                drawArc(
                    color = slice.color,
                    startAngle = slice.startAngle,
                    sweepAngle = animatedSweepAngle,
                    useCenter = false,
                    style = Stroke(slice.radius.toPx(), cap = StrokeCap.Butt)
                )
            }
        }

        Column(modifier = Modifier.align(Alignment.Center), horizontalAlignment = Alignment.CenterHorizontally) {
            Text("29.1", color = Color.White, fontWeight = FontWeight.Bold)
            Text("Of 128GB", color = Color.White, fontSize = 12.sp, modifier = Modifier.offset(y = -10.dp))

        }
    }

}

data class PieChartSliceData(
    val radius: Dp,
    val value: Int,
    val color: Color,
)
