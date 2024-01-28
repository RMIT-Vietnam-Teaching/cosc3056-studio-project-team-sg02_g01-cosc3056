document.addEventListener('DOMContentLoaded', function(){
    //Toggle switch function: change between raw data num and percent
        var toggle = document.getElementById("percent_toggle");
        var dataNum = document.getElementsByClassName('data_num');
        var j = dataNum.length;
        var dataPercent = document.getElementsByClassName('data_percent');

        toggle.addEventListener("change", function (event) {
            //When switch is on (check)
            if (event.target.checked) {
                //Switch to percent value (Set classes to "check" status - See CSS)
                for(var i = 0; i < j; i++){
                    dataNum[i].className = "data_num check";
                    dataPercent[i].className = "data_percent check";
                }
            }
            //When switch is off (uncheck)
            else {
                //Switch to numeric value (Set classes to "uncheck" status - See CSS)
                for(var i = 0; i < j; i++){
                    dataNum[i].className = "data_num uncheck";
                    dataPercent[i].className = "data_percent uncheck";
                }
            }
        });
})