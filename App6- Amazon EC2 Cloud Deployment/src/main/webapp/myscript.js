// /**
//  * 
//  */

function myFunction() 
{
    var content;
    var url = new XMLHttpRequest();
    url.open("GET", "/js/incidents/traffic_count", false);
    url.send();

    document.getElementById("demo").innerHTML = "";
    content = url.response;
    
    var str = content;
    var res = str.split("$");
    
    document.getElementById("demo").border = 1;
    document.getElementById("demo").innerHTML += "<td>"+ "Issue" +"</td>" + "<td>"+ "Count" +"</td>";
    for(i=0;i<res.length-1;i+=2)
    {
        //count
        if(i+1 != null) 
        {
            document.getElementById("demo").innerHTML += "<td>"+ res[i] +"</td>" + "<td>"+ res[i+1] +"</td>";
        }
    }
        
}