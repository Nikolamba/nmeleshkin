function ajaxFunction() {
    $.ajax({
        type: 'get',
        url: 'http://localhost:8080/chapter_007/places',
        dataType: 'json',
        error: [function(xhr, status, error) {
            alert(xhr.responseText + '|\n' + status + '|\n' + error);
        }],
        success: [function (response) {
            $.each(response, function () {
                var id_place = 'p_' + this.row + this.placeNum;
                var id_cell = 'c_' + this.row + this.placeNum;
                if (this.account !== 0) {
                    document.getElementById(id_place).setAttribute('disabled', true);
                    document.getElementById(id_cell).setAttribute('bgcolor', '#FF8080');
                } else {
                    document.getElementById(id_place).removeAttribute('disabled');
                    document.getElementById(id_cell).setAttribute('bgcolor', '#80FF00');
                }
            });
        }]
    });
}

$(document).ready(function () {
    ajaxFunction();
    setInterval(ajaxFunction, 5000);
});


function payment() {
    var select = $('input:radio[name="place"]:checked').val();
    var row = Math.floor(select / 10);
    var place = select % 10;
    window.location.href = 'http://localhost:8080/chapter_007/payment.html?row=' + row + '&' + 'place=' + place;
}