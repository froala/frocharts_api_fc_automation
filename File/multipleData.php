<?php
	date_default_timezone_set("Asia/Calcutta");
	$now =  date("H:i:s", time());
	//Get random numbers
	$randomValueOne = floor(rand(0,100));
	$randomValueTwo = floor(rand(-50,-100));
	$randomValueThree = floor(rand(0,60));
	$randomValueFour = floor(rand(60,100));
	// $randomValueFour = floor(rand(400,360));
	$id = "id".$randomValueOne;
	$target = $randomValueOne+100;

	$l1 = $randomValueOne - 400;
	$l2 = $randomValueTwo - 400;
	$l3 = $randomValueThree - 400;
	$l4 = $randomValueFour - 400;

	// // //ERROR MESSAGE
	// if ($randomValueOne < 10) {
    	echo "&label=".$now."&value=".$randomValueOne."|".$randomValueTwo."&msgId=".$id."&msgTitle=Football at : ".$now."&msgText=Spectators : ".$randomValueOne."&msgType=ERROR";
	// }

	// // // //LINK MESSAGE
	// else if($randomValueOne >= 11 && $randomValueOne <= 20){
		// echo "&label=".$randomValueOne."&value=".$randomValueOne."|".$randomValueTwo."&msgId=".$id."&msgTitle=Football at : ".$now."&msgText=link"."&msgLink=http://www.fusioncharts.com"."&msgType=LINK";
	// }

	// // // //LITERAL MESSAGE
	// else if($randomValueOne >= 21 && $randomValueOne <= 30){
		// echo "&label=".$randomValueOne."&value=".$randomValueOne."|".$randomValueTwo."&msgId=".$id."&msgTitle=Football at : ".$now."&msgText=Literal"."&msgType=LITERAL";
	// }

	// // // //INFO MESSAGE
	// else if($randomValueOne > 30){
	// 	echo "&label=".$randomValueOne."&value=".$randolteomValueOne."|".$randomValueTwo."&msgId=".$id."&msgTitle=Football at : ".$now."&msgText=Spectators : ".$randomValueOne."&msgType=INFO";
	// }

	//Simple Output
   // echo  "&label=".$now."&value=".$randomValueOne."|".$randomValueTwo."|".$randomValueThree."|".$randomValueFour;
   // echo  "&label=".$now;

   // echo  "&label=".$randomValueOne."&value=".$randomValueOne.",".$randomValueOne.",".$randomValueOne.",".$randomValueOne."|".$randomValueTwo.",".$randomValueTwo.",".$randomValueTwo.",".$randomValueTwo;

   //."|".$randomValueThree.",".$randomValueThree.",".$randomValueThree.",".$randomValueThree."|".$randomValueFour.",".$randomValueFour.",".$randomValueFour.",".$randomValueFour

    // echo  "&label=".$now."&value=".$randomValueOne."&target=".$target;

   //VLINE
//    echo  "&label=".$now."&value=".$randomValueOne."|".$randomValueTwo."|".$randomValueThree."|".$randomValueFour."&vline="."1"."&vLineLabel=".$now."&vLineColor="."ff0000"."&vLineThickness="."0.5"."&vLineDashed="."0";

   //SHOWLABEL & VALUE
   // echo  "&label=".$now."&showLabel="."1"."&showValue="."0|1"."&value=".$randomValueOne."|".$randomValueTwo."|".$randomValueThree."|".$randomValueFour;

   // TOOLTEXT COLOR LINK
   // echo  "&label=".$now."&toolText="."TOOLTEXT1|tooltext2"."&link="."n-http://www.google.com|n-http://www.fusioncharts.com"."&color="."ff0000|0000ff"."&value=".$randomValueOne."|".$randomValueTwo."|".$randomValueThree."|".$randomValueFour;

// echo  "&_label=".$now."&toolText=\$value"."&link="."n-http://www.google.com|n-http://www.fusioncharts.com"."&color="."ff0000|0000ff"."&value=".$randomValueOne."|".$randomValueTwo."|".$randomValueThree."|".$randomValueFour;
   //CLEAR
 //   	if ($randomValueOne < 30) {
 //    	echo  "&label=".$now."&value=".$randomValueOne."|".$randomValueTwo."|".$randomValueThree."|".$randomValueFour;
	// }
	// else{
		// echo  "&clear="."1";
	// }

	// echo  "&clear="."1";

	//STOPUPDATE
 //   if ($randomValueOne < 30) {
    	// echo  "&label=".$randomValueOne."&value=".$randomValueOne."|".$randomValueTwo."|".$randomValueThree."|".$randomValueFour;
	// }
	// else{
	// 	echo  "&stopUpdate="."1";
	// }

	// echo  "&stopUpdate="."1";

	//WIDGETS

	//ANGULAR GAUGE
// echo  "&label=hjagsdjhgsakashj".$l1.",".$l2.",".$l3.",".$l4."&value=".$randomValueOne.",".$randomValueTwo.",".$randomValueThree.",".$randomValueFour;
// echo  "&label=".$now.",".$now.",".$now.",".$now."&value=".$randomValueOne."|".$randomValueTwo."|".$randomValueThree."|".$randomValueFour;
   // echo  "&label=".$now."&value=".$randomValueOne."|".$randomValueTwo."|".$randomValueThree."|".$randomValueFour;
	// echo  "&label=".$randomValueOne."&target=".$target."&value=".$randomValueOne;

   // echo  "&label=".$now."&value=".$randomValueOne."&target=".$target;
	// echo  "&label=".$randomValueOne."&toolText=".$target."&link="."n-http://www.google.com"."&color="."ff0000"."&value=".$randomValueOne;
	// echo  "&label=".$now."&dial1=".$randomValueOne."&dial2=".$randomValueTwo."&dial3=".$randomValueThree."&dial4=".$randomValueFour;

	//HLINEARGAUGE
// echo  "&label=".$l1.",".$l2.",".$l3.",".$l4."&value=".$randomValueOne.",".$randomValueTwo.",".$randomValueThree.",".$randomValueFour;
// echo  "&label=".$now.",".$now.",".$now.",".$now."&value=".$randomValueOne.",".$randomValueTwo.",".$randomValueThree.",".$randomValueFour;
   // echo  "&label=".$now."&value=".$randomValueOne."|".$randomValueTwo."|".$randomValueThree."|".$randomValueFour;
	// echo  "&value=".$randomValueOne;

   // echo  "&label=".$now."&value=".$randomValueOne."&target=".$target;
	// echo  "&label=".$now."&toolText="."TOOLTEXT1|dghdfhgd"."&link="."n-http://www.google.com"."&color="."ff0000"."&value=".$randomValueOne."&target=".$target;
	// echo  "&pointer1=".$randomValueOne."&pointer2=".$randomValueTwo."&pointer3=".$randomValueThree."&pointer4=".$randomValueFour;

	// echo  "&label=kishan,subhash"."&value=".$randomValueOne."|".$randomValueTwo."|".$randomValueThree."|".$randomValueFour;
   	
   	// echo  "&label=kishan,subhash"."&value=".$randomValueOne."|".$randomValueTwo."|".$randomValueThree."|".$randomValueFour;
   	

   	// echo  "&toolText="."tooltext1"."&link="."n-http://www.google.com|n-http://www.fusioncharts.com"."&color="."ff0000|0000ff"."&value=".$randomValueOne."|".$randomValueTwo."|".$randomValueThree."|".$randomValueFour;

   	// echo "&label=".$now."&value=".$randomValueOne."|".$randomValueTwo."&tooltext=\\&\\|\!\@\#\$\%\^\*\(\)\_\+\-\=\[\]\{\}\;\:\'\"\<\\,\>\.\?\/\`\`|\\&\\|\!\@\#\$\%\^\*\(\)\_\+\-\=\[\]\{\}\;\:\'\"\<\\,\>\.\?\/\`\`"."&color="."00ff00|000000";
?>


