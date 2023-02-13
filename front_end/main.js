document.querySelector("#b1").addEventListener("click", function () {
  make_queue("numconc");
});
document.querySelector("#b2").addEventListener("click", function () {
    make_queue("sum");
});
document.querySelector("#b3").addEventListener("click", function () {
    make_queue("rest");
});
document.querySelector("#b4").addEventListener("click", function () {
    make_queue("multiplication");
});
function make_queue(operation){
    const input_first = document.querySelector("#first_i");
    const input_second = document.querySelector("#second_i");
    const result = document.querySelector("#result");
    if(input_first != null && input_second != null && result != null){
       fetch("http://localhost:8080/" + operation + "?n1=" + input_first.value + "&n2=" + input_second.value).then(response => response.text()).then(data => result.value = data);
    }
}