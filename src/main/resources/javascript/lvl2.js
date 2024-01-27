document.addEventListener('DOMContentLoaded', function(){
    var toggle = document.getElementById("percent_toggle");
    var dataNum = document.getElementsByClassName('data_num');
    var j = dataNum.length;
    var dataPercent = document.getElementsByClassName('data_percent');

    toggle.addEventListener("change", function (event) {
        if (event.target.checked) {
            for(var i = 0; i < j; i++){
                dataNum[i].className = "data_num check";
                dataPercent[i].className = "data_percent check";
            }
        } else {
            for(var i = 0; i < j; i++){
                dataNum[i].className = "data_num uncheck";
                dataPercent[i].className = "data_percent uncheck";
            }
        }
    });
})