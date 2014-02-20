<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<script>
$(function(){
	var $qssj=$("input[name$='QSSJ']",$.pdialog.getCurrent());
	var $zzsj=$("input[name$='ZZSJ']",$.pdialog.getCurrent());
	var $ls=$("input[name$='LS']");
	$qssj.change(function(){
		if($zzsj!=''&&$qssj!==''){
			$ls.val(DiffLong($qssj.val(),$zzsj.val()));
			}
		});
	$zzsj.change(function(){
		if($zzsj!=''&&$qssj!==''){
			$ls.val(DiffLong($qssj.val(),$zzsj.val()));
			}
		});
}); 
//Get days and other datetime
//diffrence two datetime
//date1 :更早的日期 小的日期
//date2 :后面的日期 大的日期
//返回两个时间差的天数小时数分数秒数和毫秒数
function DiffLong(datestr1, datestr2) {

  var date1 = new Date(Date.parse(datestr1.replace(/-/g, "/")));
  var date2 = new Date(Date.parse(datestr2.replace(/-/g, "/")));
  var datetimeTemp;
  var difference;
  var thishours;
  var thisminutes;
  var thisseconds;
  var isLater = true;

  if (date1.getTime() > date2.getTime()) {
      isLater = false;
      datetimeTemp = date1;
      date1 = date2;
      date2 = datetimeTemp;
  }

  difference = date2.getTime() - date1.getTime();
  thisdays = Math.floor(difference / (1000 * 60 * 60 * 24));
  
  difference = difference - thisdays * (1000 * 60 * 60 * 24);
  thishours = Math.floor(difference / (1000 * 60 * 60));

  difference = difference - thishours * (1000 * 60 * 60 );
  thisminutes = Math.floor(difference / (1000 * 60 ));
  
  difference=difference-thisminutes*(1000*60);
  thisseconds=Math.floor(difference/1000);


  var strRet = (thisdays*24  + thishours) + ':'+thisminutes+':'+thisseconds;

  return strRet;
}


</script>