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

    //Year Start - End dropdown menus
    var yearStartSelect = document.querySelector('#year_start'); //Year start dropdown menu
    var yearEndSelect = document.querySelector('#year_end'); //Year end dropdown menu
    var yearEndOptions = document.querySelectorAll('#year_end option'); //Year end dropdown options
    var submitBtt = document.querySelector("button[type = 'submit']");

    //Listens for when the year_start dropdown is selected
    yearStartSelect.addEventListener("change", function() {
        //Selected option of year_start
        var yearStartSelected = yearStartSelect.options[yearStartSelect.selectedIndex].value;
        console.log("Selected year: " + yearStartSelected); //Checking on console if action is detected

        //Hide options that are smaller than selected year_start
        yearEndOptions.forEach(function(option) {
            //console.log(option.value);

            if (parseInt(option.value) < parseInt(yearStartSelected)) {
                option.setAttribute("hidden", '');
                console.log("Hidden: " + option.value);
            }
            else {
                option.removeAttribute("hidden");
                console.log("Not hidden: " + option.value);
            }
        });

        //Enable year_end dropdown
        yearEndSelect.disabled = false;
    });

    //Enable submit button when year range is selected
    yearEndSelect.addEventListener("change", function(){
        submitBtt.disabled = false;
        submitBtt.innerHTML = "Submit";
    });
})