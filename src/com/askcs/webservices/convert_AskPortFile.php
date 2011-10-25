<?php
	
	$contents=file("ReconnectingAskPort.java");
	$returnType="";
	$functionName="";
	$argumentString="";
	$arguments=array("");
	
	foreach ($contents as $key => &$value) {
		if (preg_match("/.*wrapper.*/",$value) == 1){
			//already done
			$returnType="";
			$functionName="";
			$arguments=array();
			continue;
		}
		
		if (preg_match("/.*public (.*Response)[ ]*([^(]*).*/",$value,$matches) == 1){
			$returnType=$matches[1];
			$functionName=$matches[2];
			$argumentString = "";
		}
		$argumentString.=$value;
		if (preg_match("/.*\(([^)]*)\).*/",$argumentString,$matches) == 1){
			$argumentArray=explode(",",$matches[1]);
			$arguments=array();
			array_map(function($elem){
				global $arguments;
				$res=explode(" ",trim($elem));
				if (isset($res[1])){
					array_push($arguments,$res[1]);
				}
			}, $argumentArray);
			$argumentString="";
		}
		if (preg_match("/.*TODO Auto-generated method stub.*/",$value) == 1){
			if ($functionName != "" && $returnType != ""){
				$value="		return (".$returnType.") wrapper(\"".$functionName."\",".implode(",",$arguments).");\n";
			}
			continue;
		}
		if (preg_match("/.*return null.*/",$value) == 1){
			$value="";
			continue;
		}
	}
	echo implode("",$contents);
?>