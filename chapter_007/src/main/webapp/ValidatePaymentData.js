function getUrlParameter(sParam) {
    var sPageURL = decodeURIComponent(window.location.search.substring(1)),
        sURLVariables = sPageURL.split('&'),
        sParameterName,
        i;

    for (i = 0; i < sURLVariables.length; i++) {
        sParameterName = sURLVariables[i].split('=');

        if (sParameterName[0] === sParam) {
            return sParameterName[1] === undefined ? true : sParameterName[1];
        }
    }
}

function validateData() {
    var result = true;
    if (!(/^((8|\+7)[\- ]?)?(\(?\d{3}\)?[\- ]?)?[\d\- ]{7,10}$/).test($('#phone').val())) {
        alert($('#phone').attr('title'));
        result = false;
    }
    if (!(/^[a-zA-Z]+$/).test($('#username').val())) {
        alert($('#username').attr('title'));
        result = false;
    }
    return result;
}

$(document).ready(function () {
    var row = getUrlParameter('row'),
        place = getUrlParameter('place');
    $('#row').attr('value', row);
    $('#place').attr('value', place);
    if (row === undefined || place === undefined) {
        $('h3').html('Вы не выбрали ряд и место');
        $('#submit').attr('disabled', true);
    } else {
        $('h3').html('Вы выбрали ряд ' + getUrlParameter('row') + ' место ' + getUrlParameter('place')
            + ', Сумма: 500 рублей.');
    }
});

