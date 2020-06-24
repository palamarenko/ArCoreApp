package com.example.exchangeapp.ui.single

import com.anychart.AnyChart
import com.anychart.chart.common.dataentry.ValueDataEntry
import com.anychart.charts.Cartesian
import com.anychart.enums.Anchor
import com.anychart.enums.HoverMode
import com.anychart.enums.Position
import com.anychart.enums.TooltipPositionMode
import com.example.exchangeapp.R
import com.example.exchangeapp.ui.base.BaseFragment
import com.example.exchangeapp.ui.main.BASE_CURRENCY
import kotlinx.android.synthetic.main.fragment_singlechart.*
import ua.palamarenko.cozyandroid2.tools.listen

const val CURRENCY_KEY = "CURRENCY_KEY"


class SingleChartFragment : BaseFragment<SingleChartViewModel>() {

    override val layout = R.layout.fragment_singlechart

    override fun onStartScreen() {
        super.onStartScreen()
        vm().loadChartData(getArgumentString(CURRENCY_KEY)).listen(this){
            setChart(it)
        }

    }



    private fun setChart(list : List<ValueDataEntry>){
        val cartesian: Cartesian = AnyChart.column()


        val column = cartesian.column(list)
        column.tooltip()
            .titleFormat("{%X}")
            .position(Position.CENTER_BOTTOM)
            .anchor(Anchor.CENTER_BOTTOM)
            .offsetX(0.0)
            .offsetY(5.0)
            .format("\${%Value}{groupsSeparator: }")

        cartesian.animation(true)
        cartesian.title("Exchange rate ${BASE_CURRENCY}/${getArgumentString(CURRENCY_KEY)}")

        cartesian.yScale().minimum(0.0)

        cartesian.yAxis(0).labels().format("\${%Value}{groupsSeparator: }")

        cartesian.tooltip().positionMode(TooltipPositionMode.POINT)
        cartesian.interactivity().hoverMode(HoverMode.BY_X)

        cartesian.xAxis(0).title("Time")
        cartesian.yAxis(0).title("Exchange rate")

        vChart.setChart(cartesian)
    }


}