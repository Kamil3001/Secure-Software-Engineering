<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE HTML>
<html>

<head>
    <title>UoS - Statistics</title>
    <meta http-equiv="content-type" content="text/html; charset=windows-1252" />
    <link href="https://fonts.googleapis.com/css?family=Montserrat&display=swap" rel="stylesheet">
    <link rel="stylesheet" href="../../resources/theme/css/pages.css" type="text/css">
</head>

<body>
<div id="main">
    <div id="header">
        <c:import url="nested/header.jsp"/>
    </div>
    <div id="content_header"></div>
    <div id="site_content">
        <c:import url="nested/sidebar.jsp"/>
        <div id="content">
            <h1>Nationalities in UoS</h1>
            <div id="nationality_data"></div>
                <script src="https://d3js.org/d3-scale-chromatic.v1.min.js"></script>
                <script src="https://d3js.org/d3.v4.js"></script>

                <script>
                    d3.select('h2').attr('align',"center");
                    d3.select('h3').style('color', 'darkblue');
                    d3.select('h3').style('font-size', '24px');
                    d3.select('h3').attr('align',"center");
                    d3.select("#nationality_data").attr("align","center");

                    // set the dimensions and margins of the graph
                    var width = 450
                    height = 450
                    margin = 40

                    // The radius of the pieplot is half the width or half the height (smallest one). I subtract a bit of margin.
                    var radius = Math.min(width, height) / 2 - margin

                    // append the svg object to the div called 'my_dataviz'
                    var svg = d3.select("#nationality_data")
                        .append("svg")
                        .attr("width", width)
                        .attr("height", height)
                        .append("g")
                        .attr("transform", "translate(" + width / 2 + "," + height / 2 + ")");

                    // set the color scale
                    var color = d3.scaleOrdinal()
                        .domain(${nationality})
                        .range(d3.schemeCategory20c);

                    // Compute the position of each group on the pie:
                    var pie = d3.pie()
                        .value(function(d) {
                            return d.value;
                        })

                    var data_ready = pie(d3.entries(${nationality}))

                    // Build the pie chart: Basically, each part of the pie is a path that we build using the arc function.
                    svg
                        .selectAll('whatever')
                        .data(data_ready)
                        .enter()
                        .append('path')
                        .attr('d', d3.arc()
                            .innerRadius(0)
                            .outerRadius(radius)
                        )
                        .attr('fill', function(d){
                            return(color(d.data.key)) })
                        .attr("stroke", "white")
                        .style("stroke-width", "2px")
                        .style("opacity", 0.7)

                    // shape helper to build arcs:
                    var arcGenerator = d3.arc()
                        .innerRadius(0)
                        .outerRadius(radius)

                    // Now add the annotation. Use the centroid method to get the best coordinates
                    svg
                        .selectAll('mySlices')
                        .data(data_ready)
                        .enter()
                        .append('text')
                        .text(function(d){

                            return d.data.key +": "+d.data.value;
                        })
                        .attr("fill", "#ffffff")
                        .attr("transform", function(d) { return "translate(" + arcGenerator.centroid(d) + ")";  })
                        .style("text-anchor", "middle")
                        .style("font-size", 14)

                </script>

            <h1>Gender in UoS</h1>
            <div id="gender_data"></div>
            <script src="https://d3js.org/d3-scale-chromatic.v1.min.js"></script>
            <script src="https://d3js.org/d3.v4.js"></script>

            <script>
                d3.select('h2').attr('align',"center");
                d3.select('h3').style('color', 'darkblue');
                d3.select('h3').style('font-size', '24px');
                d3.select('h3').attr('align',"center");
                d3.select("#gender_data").attr("align","center");

                // set the dimensions and margins of the graph
                var width = 450
                height = 450
                margin = 40

                // The radius of the pieplot is half the width or half the height (smallest one). I subtract a bit of margin.
                var radius = Math.min(width, height) / 2 - margin

                // append the svg object to the div called 'my_dataviz'
                var svg = d3.select("#gender_data")
                    .append("svg")
                    .attr("width", width)
                    .attr("height", height)
                    .append("g")
                    .attr("transform", "translate(" + width / 2 + "," + height / 2 + ")");

                // set the color scale
                var color = d3.scaleOrdinal()
                    .domain(${gender})
                    .range(d3.schemeCategory20c);

                // Compute the position of each group on the pie:
                var pie = d3.pie()
                    .value(function(d) {
                        return d.value;
                    })

                var data_ready = pie(d3.entries(${gender}))

                // Build the pie chart: Basically, each part of the pie is a path that we build using the arc function.
                svg
                    .selectAll('whatever')
                    .data(data_ready)
                    .enter()
                    .append('path')
                    .attr('d', d3.arc()
                        .innerRadius(0)
                        .outerRadius(radius)
                    )
                    .attr('fill', function(d){
                        return(color(d.data.key)) })
                    .attr("stroke", "white")
                    .style("stroke-width", "2px")
                    .style("opacity", 0.7)

                // shape helper to build arcs:
                var arcGenerator = d3.arc()
                    .innerRadius(0)
                    .outerRadius(radius)

                // Now add the annotation. Use the centroid method to get the best coordinates
                svg
                    .selectAll('mySlices')
                    .data(data_ready)
                    .enter()
                    .append('text')
                    .text(function(d){

                        return d.data.key +": "+d.data.value;
                    })
                    .attr("fill", "#ffffff")
                    .attr("transform", function(d) { return "translate(" + arcGenerator.centroid(d) + ")";  })
                    .style("text-anchor", "middle")
                    .style("font-size", 14)

            </script>
        </div>
    </div>
    <div id="content_footer"></div>
    <div id="footer">
        University of Springfield
    </div>
</div>
</body>
</html>
