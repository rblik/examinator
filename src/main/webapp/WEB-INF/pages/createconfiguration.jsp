<!DOCTYPE html>
<html>
<p>
	Set title of course:
	<input id="title" type="text" />
</p>
<p>
	Set emails (divide by commas)
	<input id="emails" type="text" />
</p>
<p>
	Set amount of expressions:<br>
	Columns: <input id="pictureCols" type="number" />
	Rows: <input id="pictureRows" type="number" />
</p>

<hr>


<!-- PLUS -->
<p>
<h2><input type="checkbox" id="plusConstraints" onchange="showCheckedBlock(this)"/> Include expressions with plus (+) operation</h2>
</p>
<div id="plusConstraintsDiv" style="display:none">
	<p>
		<input type="checkbox" id="plusAnswerConstraints" onchange="showCheckedBlock(this)" /> Set answer constraints
	<div id="plusAnswerConstraintsDiv" style="display:none">
		<input type="number" value="1" id="plusMinAnswer" /> : <input type="number" value="10" id="plusMaxAnswer" />
	</div>
	</p>
	<p>
		<table border=1>
			<thead>
			<tr>
				<td>A</td>
				<td>B</td>
			</tr>
			</thead>
			<tbody>
			<tr>
				<td>
					<select onchange="showSelectedBlock(this)" id="plusAConstraints" >
						<option value="RangeAndExcept">Range of allowed values, except</option>
						<option value="Specific">Specific values</option>
					</select>

					<div id="plusAConstraintsRangeAndExcept" style="display:block">
	<p>
		Range: <input type="number" value="1" id="plusMinA"/> : <input type="number" value="10" id="plusMaxA" /><br>
		Except: <input type="text" placeholder="numbers divided by commas" id="plusAExcept" />
	</p>
</div>
<div id="plusAConstraintsSpecific" style="display:none">
	<p>
		Only specific values: <input type="text" placeholder="numbers divided by commas"  id="plusASpecific"/>
	</p>
</div>
</td>
<td>
	<select onchange="showSelectedBlock(this)" id="plusBConstraints" >
		<option value="RangeAndExcept">Range of allowed values, except</option>
		<option value="Specific">Specific values</option>
	</select>

	<div id="plusBConstraintsRangeAndExcept" style="display:block">
		<p>
			Range: <input type="number" value="1" id="plusMinB" /> : <input type="number" value="10" id="plusMaxB" /><br>
			Except: <input type="text" placeholder="numbers divided by commas" id="plusBExcept" />
		</p>
	</div>
	<div id="plusBConstraintsSpecific" style="display:none">
		<p>
			Only specific values: <input type="text" placeholder="numbers divided by commas"  id="plusBSpecific" />
		</p>
	</div>
</td>
</tr>
</tbody>
</table>
</p>
</div>
<hr>


<!-- MINUS -->
<p>
<h2><input type="checkbox" id="minusConstraints" onchange="showCheckedBlock(this)"/> Include expressions with minus (-) operation</h2>
</p>
<div id="minusConstraintsDiv" style="display:none">
	<p>
		<input type="checkbox" id="minusAnswerCanBeNegative" /> Answers can be negative?
	</p>
	<p>
		<table border=1>
			<thead>
			<tr>
				<td>A</td>
				<td>B</td>
			</tr>
			</thead>
			<tbody>
			<tr>
				<td>
					<select onchange="showSelectedBlock(this)" id="minusAConstraints" >
						<option value="RangeAndExcept">Range of allowed values, except</option>
						<option value="Specific">Specific values</option>
					</select>

					<div id="minusAConstraintsRangeAndExcept" style="display:block">
	<p>
		Range: <input type="number" value="1" id="minusMinA"/> : <input type="number" value="10" id="minusMaxA" /><br>
		Except: <input type="text" placeholder="numbers divided by commas" id="minusAExcept" />
	</p>
</div>
<div id="minusAConstraintsSpecific" style="display:none">
	<p>
		Only specific values: <input type="text" placeholder="numbers divided by commas"  id="minusASpecific"/>
	</p>
</div>
</td>
<td>
	<select onchange="showSelectedBlock(this)" id="minusBConstraints" >
		<option value="RangeAndExcept">Range of allowed values, except</option>
		<option value="Specific">Specific values</option>
	</select>

	<div id="minusBConstraintsRangeAndExcept" style="display:block">
		<p>
			Range: <input type="number" value="1" id="minusMinB" /> : <input type="number" value="10" id="minusMaxB" /><br>
			Except: <input type="text" placeholder="numbers divided by commas" id="minusBExcept" />
		</p>
	</div>
	<div id="minusBConstraintsSpecific" style="display:none">
		<p>
			Only specific values: <input type="text" placeholder="numbers divided by commas"  id="minusBSpecific" />
		</p>
	</div>
</td>
</tr>
</tbody>
</table>
</p>
</div>
<hr>


<!-- MULTI -->
<p>
<h2><input type="checkbox" id="multiConstraints" onchange="showCheckedBlock(this)"/> Include expressions with multiplection (*) operation</h2>
</p>
<div id="multiConstraintsDiv" style="display:none">
	<p>
		<input type="checkbox" id="multiAnswerConstraints" onchange="showCheckedBlock(this)" /> Set answer constraints
	<div id="multiAnswerConstraintsDiv" style="display:none">
		<input type="number" value="1" id="multiMinAnswer" /> : <input type="number" value="10" id="multiMaxAnswer" />
	</div>
	</p>
	<p>
		<table border=1>
			<thead>
			<tr>
				<td>A</td>
				<td>B</td>
			</tr>
			</thead>
			<tbody>
			<tr>
				<td>
					<select onchange="showSelectedBlock(this)" id="multiAConstraints" >
						<option value="RangeAndExcept">Range of allowed values, except</option>
						<option value="Specific">Specific values</option>
					</select>

					<div id="multiAConstraintsRangeAndExcept" style="display:block">
	<p>
		Range: <input type="number" value="1" id="multiMinA"/> : <input type="number" value="10" id="multiMaxA" /><br>
		Except: <input type="text" placeholder="numbers divided by commas" id="multiAExcept" />
	</p>
</div>
<div id="multiAConstraintsSpecific" style="display:none">
	<p>
		Only specific values: <input type="text" placeholder="numbers divided by commas"  id="multiASpecific"/>
	</p>
</div>
</td>
<td>
	<select onchange="showSelectedBlock(this)" id="multiBConstraints" >
		<option value="RangeAndExcept">Range of allowed values, except</option>
		<option value="Specific">Specific values</option>
	</select>

	<div id="multiBConstraintsRangeAndExcept" style="display:block">
		<p>
			Range: <input type="number" value="1" id="multiMinB" /> : <input type="number" value="10" id="multiMaxB" /><br>
			Except: <input type="text" placeholder="numbers divided by commas" id="multiBExcept" />
		</p>
	</div>
	<div id="multiBConstraintsSpecific" style="display:none">
		<p>
			Only specific values: <input type="text" placeholder="numbers divided by commas"  id="multiBSpecific" />
		</p>
	</div>
</td>
</tr>
</tbody>
</table>
</p>
</div>
<hr>


<!-- DIVIDE -->
<p>
<h2><input type="checkbox" id="divideConstraints" onchange="showCheckedBlock(this)"/> Include expressions with  division (/) operation</h2>
</p>
<div id="divideConstraintsDiv" style="display:none">
	<p>
		<input type="checkbox" id="divideAnswerConstraints" onchange="showCheckedBlock(this)" /> Set answer constraints
	<div id="divideAnswerConstraintsDiv" style="display:none">
		<input type="number" value="1" id="divideMinAnswer" /> : <input type="number" value="10" id="divideMaxAnswer" />
	</div>
	</p>
	<p>
		<input type="checkbox" id="divideAnswerWithoutRemainder" /> Answers always without remainders?
	</p>
	<p>
		<table border=1>
			<thead>
			<tr>
				<td>A</td>
				<td>B</td>
			</tr>
			</thead>
			<tbody>
			<tr>
				<td>
					<select onchange="showSelectedBlock(this)" id="divideAConstraints" >
						<option value="RangeAndExcept">Range of allowed values, except</option>
						<option value="Specific">Specific values</option>
					</select>

					<div id="divideAConstraintsRangeAndExcept" style="display:block">
	<p>
		Range: <input type="number" value="1" id="divideMinA"/> : <input type="number" value="10" id="divideMaxA" /><br>
		Except: <input type="text" placeholder="numbers divided by commas" id="divideAExcept" />
	</p>
</div>
<div id="divideAConstraintsSpecific" style="display:none">
	<p>
		Only specific values: <input type="text" placeholder="numbers divided by commas"  id="divideASpecific"/>
	</p>
</div>
</td>
<td>
	<select onchange="showSelectedBlock(this)" id="divideBConstraints" >
		<option value="RangeAndExcept">Range of allowed values, except</option>
		<option value="Specific">Specific values</option>
	</select>

	<div id="divideBConstraintsRangeAndExcept" style="display:block">
		<p>
			Range: <input type="number" value="1" id="divideMinB" /> : <input type="number" value="10" id="divideMaxB" /><br>
			Except: <input type="text" placeholder="numbers divided by commas" id="divideBExcept" />
		</p>
	</div>
	<div id="divideBConstraintsSpecific" style="display:none">
		<p>
			Only specific values: <input type="text" placeholder="numbers divided by commas"  id="divideBSpecific" />
		</p>
	</div>
</td>
</tr>
</tbody>
</table>
</p>
</div>
<hr>

<input type="button" value="Save" onclick="save()"/>
<script src="https://ajax.googleapis.com/ajax/libs/jquery/1.12.4/jquery.min.js"></script>
<script>
	function showSelectedBlock(select) {
		var selectId = select.id;
		document.getElementById(selectId + 'RangeAndExcept').style.display = "none";
		document.getElementById(selectId + 'Specific').style.display = "none";
		document.getElementById(selectId + select.value).style.display = "block";
	}
	function showCheckedBlock(checkbox){
		var checkboxId = checkbox.id;
		document.getElementById(checkboxId+'Div').style.display = (document.getElementById(checkboxId+'Div').style.display == 'none')?'block':'none'
	}

	function getParamsOfOperation(operation, sign)
	{
		var json = {};
		json["sign"] = sign;
		if(document.getElementById(operation + 'AnswerConstraints') && document.getElementById(operation + 'AnswerConstraints').checked)
		{
			json["minAnswer"] = document.getElementById(operation + 'MinAnswer').value;
			json["maxAnswer"] = document.getElementById(operation + 'MaxAnswer').value;
		}
		if(document.getElementById(operation + 'AnswerCanBeNegative') && document.getElementById(operation + 'AnswerCanBeNegative').checked)
		{
			json["allowedNegativeAnswer"] = "true";
		}
		if(document.getElementById(operation + 'AnswerWithoutRemainder') && document.getElementById(operation + 'AnswerWithoutRemainder').checked)
		{
			json["divisionWithoutRemainder"] = "true";
		}
		if(document.getElementById(operation + 'AConstraints').value == "RangeAndExcept") {
			json["minA"] = document.getElementById(operation + 'MinA').value;
			json["maxA"] = document.getElementById(operation + 'MaxA').value;
		}
		json["exceptA"] = getArrayFromStringSplittedByComma(document.getElementById(operation + 'AExcept').value);
		json["specialA"] = getArrayFromStringSplittedByComma(document.getElementById(operation + 'ASpecific').value);


		if(document.getElementById(operation + 'BConstraints').value == "RangeAndExcept")
		{
			json["minB"] = document.getElementById(operation + 'MinB').value;
			json["maxB"] = document.getElementById(operation + 'MaxB').value;

		}
		json["exceptB"] = getArrayFromStringSplittedByComma(document.getElementById(operation + 'BExcept').value);
		json["specialcB"] = getArrayFromStringSplittedByComma(document.getElementById(operation + 'BSpecific').value);

		return json;
	}

	function save()
	{
		var allSettings = {};
		allSettings["title"] = document.getElementById('title').value;
		allSettings["emails"] = document.getElementById('emails').value.split(',');
		allSettings["frameRows"] = document.getElementById('pictureRows').value;
		allSettings["frameCols"] = document.getElementById('pictureCols').value;

		var operationsSettings = [];

		var operations = ["plus", "minus", "multi", "divide"];
		var signs = ["+", "-", "*", "/"];

		var arrayLength = operations.length;
		for(var i = 0; i < arrayLength; i++) {
			var sign = signs[i];
			var operation = operations[i];
			if(document.getElementById(operation + 'Constraints') && document.getElementById(operation + 'Constraints').checked) {
				operationsSettings.push(getParamsOfOperation(operation, sign));
			}
		}
		allSettings["operationConstraints"] = operationsSettings;
		$.ajax({
			headers: {
				'Accept': 'application/json',
				'Content-Type': 'application/json'
			},
			type: "POST",
			url:  "http://localhost:8080/add",
			data: "configuration="+JSON.stringify(allSettings),
			//Output php feedback
			success: function(html)
			{
				//Clear input box
				alert("done");
			}
		});

		//alert(JSON.stringify(allSettings));
		document.write(JSON.stringify(allSettings));
	}

	function getArrayFromStringSplittedByComma(str)
	{
		if(str==null || str==" " || str.length == 0)
		{
			return [];
		}
		else return str.split(',');
	}
</script>
</html>