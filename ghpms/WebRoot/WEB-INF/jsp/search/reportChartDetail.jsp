<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
	<head>
		<title>报表统计图</title>
		<style>
		.chartBtn{
			border:0px;
			cursor:hand;
			display:none;
		}
		.pageBtn{
			width:30px;
			cursor:hand;
		}
		</style>
		<script type="text/javascript">
		 
	function createBasicBar(chart_type,op){
		var $data_down=$('#data_down',navTab.getCurrentPanel()); 
		var $data_up=$('#data_up',navTab.getCurrentPanel());
		var $data_size=$('#data_size',navTab.getCurrentPanel());
		var cxc=$("#chartdataForm input[name='Chart.xAxis.categories']",navTab.getCurrentPanel());
		if((parseInt($data_size.val())+parseInt($data_down.val()))<(cxc.length-1))
			var datasource=_chartdata(chart_type,parseInt($data_down.val()),
		
		parseInt($data_up.val()),parseInt($data_size.val()));
		if(op=='-1'){
			if(parseInt($data_down.val())>0){
					$data_up.val(parseInt($data_up.val())-1);
					$data_down.val(parseInt($data_down.val())-1); 
				}
		}else if(op=='+1'){ 
			if((parseInt($data_size.val())+parseInt($data_down.val()))<(cxc.length-1)){
				$data_up.val(1+parseInt($data_up.val()));
				$data_down.val(1+parseInt($data_down.val())); 
			}
		}
		
		//绘图
    	var chart;  
    	var _chart={
            chart: {
                renderTo: 'containerDiv',
                type: 'column'
            },
            title: {
                text: 'Historic World Population by Region'
            },
            subtitle: {
                text: '部分显示'
            },
            xAxis: {
                categories: ['Africa', 'America', 'Asia', 'Europe', 'Oceania'],
                title: {
                    text: null
                }
            },
            yAxis: {
                min: 0,
                minRange: 4,
                title: {
                    text: 'Population (millions)',
                    align: 'high'
                },
                labels: {
                    overflow: 'justify'
                }
            },
            tooltip: {
                formatter: function() {
                    return ''+
                        this.series.name +': '+ this.y +'';
                }
            },
            plotOptions: {
                bar: {
                    dataLabels: {
                        enabled: true
                    }
                }
            },
            legend: {
                layout: 'vertical',
                align: 'right',
                verticalAlign: 'top',
                x: -100,
                y: 100,
                floating: true,
                borderWidth: 1,
                backgroundColor: '#FFFFFF',
                shadow: true
            },
            credits: {
                enabled: false
            },
            series: [{
                name: ' 1800年',
                data: [11071, 31, 635, 203, 2]
            }, {
                name: 'Year 1900',
                data: [11331, 156, 947, 408, 6]
            }, {
                name: 'Year 2008',
                data: [19731, 914, 4054, 732, 34]
            }]
        };
        _chart.title.text=datasource.Chart_title_text;
        _chart.xAxis.categories=datasource.Chart_xAxis_categories;
        _chart.yAxis.title.text=datasource.Chart_yAxis_title; 
      	_chart.series=datasource.Chart_series; 
      	if(datasource.Chart_chart_type!=""){
      		_chart.chart.type=datasource.Chart_chart_type;
      	}  
        chart=new Highcharts.Chart(_chart); 
        
     }
		 
		 
		 //处理数据源
		function _chartdata(chart_type,d_down,d_up,d_size){
			var datasource={};//初始化datasource
			datasource.Chart_title_text="";
			datasource.Chart_yAxis_title="";
			datasource.Chart_xAxis_categories=new Array();
			datasource.Chart_series=new Array();
			  
			var Chart_title_text=$("#chartdataForm input[name='Chart.title.text']",navTab.getCurrentPanel()).val(); 
			var Chart_yAxis_title=$("#chartdataForm input[name='Chart.yAxis.title']",navTab.getCurrentPanel()).val();
			var Chart_xAxis_categories=$("#chartdataForm input[name='Chart.xAxis.categories']",navTab.getCurrentPanel());
			var Chart_series_name=$("#chartdataForm input[name='Chart.series.name']",navTab.getCurrentPanel());
			var Chart_series_data=$("#chartdataForm input[name='Chart.series.data']",navTab.getCurrentPanel());
			
			datasource.Chart_title_text=Chart_title_text;
			datasource.Chart_yAxis_title=Chart_yAxis_title; 
			datasource.Chart_chart_type=chart_type;
			
			//分类值   修改:length-1 ,取消最有一列的统计值  2013/1/8
			//  修改:分页式显示,每组8(d_size)个 2013/1/10
			for(var i=0;i<d_size;i++){
				datasource.Chart_xAxis_categories[i]=Chart_xAxis_categories[i+d_down].value;
			} 
			//data数组初始化
			for(var i=0;i<Chart_series_name.length;i++){ 
				datasource.Chart_series[i]={};
				datasource.Chart_series[i].name="";
				datasource.Chart_series[i].data=new Array();
			}
			//name属性值
			for(var i=0;i<Chart_series_name.length;i++){
				datasource.Chart_series[i].name=Chart_series_name[i].value; 
			}  
			//data属性值 修改:.length-1   取消合计 2013/1/8
			for(var i=0;i<d_size;i++){ //i代表图标组,j代表每组的分类至
				for(var j=0;j<Chart_series_name.length;j++){ 
				if(Chart_series_data[(i+d_down)*Chart_series_name.length+j].value==""){
					datasource.Chart_series[j].data[i]="0";
				}else{
					 datasource.Chart_series[j].data[i]=parseInt(Chart_series_data[(i+d_down)*Chart_series_name.length+j].value);
					}  
				}
			}   
			return datasource; 
		} 
		
		$(function(){ 
			createBasicBar('column','0');
		});
		
		</script>
	</head>

	<body>
	
	<div><button class="pageBtn"  onclick="createBasicBar('column','-1')">-</button> 
	<input type="hidden" id="data_down" name="data_size" style="width:25px;" value="0"/>
	<input type="hidden" id="data_size" name="data_size" style="width:25px;" value="7"/>
	<input type="hidden" id="data_up" name="data_size" style="width:25px;" value="8"/>
	<button class="pageBtn" onclick="createBasicBar('column','+1')">+</button></div>
		<br>
		<div id="containerDiv"
			style="min-width: 400px; height: 400px; margin: 0 auto"></div>
		<form action="" name="chartdataForm" id="chartdataForm">
			<input type="hidden" name="Chart.title.text" value="信息统计情况" /> 
			<input type="hidden" name="Chart.yAxis.title" value=""/> 
			<c:forEach var="result" items="${_resultList}" begin="1"> 
				<input type="hidden" name="Chart.xAxis.categories" value="${result[0].value}" />
			</c:forEach>
			<c:forEach var="result" items="${_resultList}" end="0">
					<c:forEach var="th" items="${result}" begin="1">
						<input type="hidden" name="Chart.series.name" value="${th.value}" /> 
					</c:forEach>
			</c:forEach>   
			<c:forEach var="result" items="${_resultList}" begin="1">
				<c:forEach var="th" items="${result}" begin="1">
					<input type="hidden" name="Chart.series.data" value="${th.value }"/>
				</c:forEach> 
			</c:forEach> 
		</form>
		<div> </div> </body>
</html>
