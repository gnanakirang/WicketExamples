var r = Raphael("holder"), lines, rtextarray = [], rpatharray = [], rtitle;

function drawRaphaelLineChart(y_values, x_values, graphTitle, lineNames, colorsarray, x_labels, axisxstep){
	if (arguments.length < 2){
		x_values = [0, 1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11];
	}
	
	if (arguments.length < 3){
		graphTitle = "Symbols, axis and hover effect";
	}
	
	if (arguments.length < 4){
		lineNames = ["First Line", "Second Line"];
	}
	
	if (arguments.length < 5){
		colorsarray = ['#CC0000', '#660033'];
	}
	
	if (arguments.length < 6){
		x_labels =  ['Jan','Feb','Mar','Apr','May','Jun','Jul','Aug','Sep','Oct','Nov','Dec'];
	}
	
	if (arguments.length < 7){
		axisxstep = 11;
	}
	
	var txtattr = { font: "20px 'Fontin Sans', Fontin-Sans, sans-serif" },
		labeltxtattr = { font: "10px 'Fontin Sans', Fontin-Sans, sans-serif" };
	
    
	
	if(lines != undefined) {
		lines.remove();
		rtitle.remove();
		for (var removeindex = 0; removeindex < rtextarray.length; removeindex++){
			rtextarray[removeindex].remove();
			rpatharray[removeindex].remove();
		}
	}
	
	rtitle = r.text(320, 40, graphTitle).attr(txtattr);	
	
	for	(var lineNamesIndex = 0; lineNamesIndex < lineNames.length && lineNamesIndex < colorsarray.length; lineNamesIndex++) {
		var currentPt = 100 + (20 *lineNamesIndex);
		
		rpatharray[lineNamesIndex] = r.path( "M100,"+currentPt+" L150,"+currentPt ).attr({stroke:colorsarray[lineNamesIndex]});
		rtextarray[lineNamesIndex] = r.text(50, currentPt, lineNames[lineNamesIndex]).attr(labeltxtattr); 
	}     
	
    lines = r.linechart(100, 130, 450, 320, 
    		x_values, 
    		y_values, 
        { axisxstep: axisxstep, nostroke: false, axis: "0 0 1 1", symbol: "circle", smooth: true, colors: colorsarray});
        
    lines.hoverColumn(function () {                    
        this.tags = r.set();

        for (var i = 0, ii = this.y.length; i < ii; i++) {
            this.tags.push(r.tag(this.x, this.y[i], this.values[i], 160, 10).insertBefore(this).attr([{ fill: "#fff" }, 
                                                                                                      { fill: this.symbols[i].attr("fill") }]));
        }
    }, function () {
        this.tags && this.tags.remove();
    });

    lines.symbols.attr({ r: 6 });

    //lines.axis[0]  is x-axis  And lines.axis[1]  is y-axis
    for(var ii = 0; ii < lines.axis[0].text.length && ii < x_labels.length; ii++){
        var x_label = x_labels[ii];
        lines.axis[0].text[ii].rotate(75);
        lines.axis[0].text[ii].attr({'text': x_label});
    }
  	      
    alert('ok-end');
    return r;
}